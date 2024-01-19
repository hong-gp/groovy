package com.project.groovy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.groovy.model.Board;
import com.project.groovy.model.PageHandler;
import com.project.groovy.model.Promotion;
import com.project.groovy.model.Review;
import com.project.groovy.model.SearchCondition;
import com.project.groovy.service.BoardService;
import com.project.groovy.service.PromotionService;
import com.project.groovy.service.ReviewService;
import com.project.groovy.service.SpotifyService;
import com.project.groovy.service.UserService;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

@Controller
@RequestMapping("chart")
public class ReviewController {
	
	private SpotifyService spotifyService;
	private UserService userService;
	private ReviewService reviewService;
	private BoardService boardService;
	private PromotionService promotionService;
	
	@Autowired
	public ReviewController(SpotifyService spotifyService, UserService userService, ReviewService reviewService, BoardService boardService, PromotionService promotionService) {
		super();
		this.spotifyService = spotifyService;
		this.userService = userService;
		this.reviewService = reviewService;
		this.boardService = boardService;
		this.promotionService = promotionService;
	}

	@GetMapping("/list")
	public String getLatestAlbums(String order, String search, SearchCondition sc, Model model) {
		if (order == null || "".equals(order)) {
			order = "latest";
		}
		model.addAttribute("order", order);
		try {
			if ("latest".equals(order)) {
				List<AlbumSimplified> latestAlbums = spotifyService.getLatestAlbums(50);
				List<AlbumSimplified> list = new ArrayList<>();
				for (int i=sc.getPage()*10 - 10; i<sc.getPage()*10; i++) {
					list.add(latestAlbums.get(i));
				}
				List<Map<Double, Long>> avgs = new ArrayList<>();
				
				for (AlbumSimplified album : list) {
					String albumId = album.getId();
					avgs.add(reviewService.selectReviewAvg(albumId));
				}
				PageHandler ph = new PageHandler(latestAlbums.size(), sc);
				model.addAttribute("ph", ph);
				model.addAttribute("latestAlbums", list);
				model.addAttribute("rateList", avgs);
			} 
			else if ("review".equals(order)) {
					List<Review> list = reviewService.selectAllReview();
					List<Map<Double, Long>> rateList = new ArrayList<>();
					List<Album> albums = new ArrayList<>();
					for (Review review : list) {
						albums.add(spotifyService.searchAlbumById(review.getAlbum_id()));
						rateList.add(reviewService.selectReviewAvg(review.getAlbum_id()));
					}
					PageHandler ph = new PageHandler(albums.size(), sc);
					model.addAttribute("ph", ph);
					model.addAttribute("latestAlbums", albums);
					model.addAttribute("rateList", rateList);
			}
			else if ("rating".equals(order)) {
				List<Review> list = reviewService.selectAllRate();
				List<Map<Double, Long>> rateList = new ArrayList<>();
				List<Album> albums = new ArrayList<>();
				for (Review review : list) {
					albums.add(spotifyService.searchAlbumById(review.getAlbum_id()));
					rateList.add(reviewService.selectReviewAvg(review.getAlbum_id()));
				}
				PageHandler ph = new PageHandler(albums.size(), sc);
				model.addAttribute("ph", ph);
				model.addAttribute("latestAlbums", albums);
				model.addAttribute("rateList", rateList);
			}
			todayBoard(model);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "chart";
		}
		return "chart";
	}
	
	@GetMapping("/review")
	public String albumReview(String id, Model model, SearchCondition sc) {
		Album album = spotifyService.searchAlbumById(id);
		try {
			List<Review> list = reviewService.selectAll(album.getId());
			for (int i=0; i<list.size(); i++) {
				list.get(i).setComment(list.get(i).getComment().replace("\r\n", "<br>"));
			}
			int cnt = reviewService.count(id);
			Map map = reviewService.selectReviewAvg(id);
			
			PageHandler pageHandler = new PageHandler(cnt, sc);
			System.out.println(sc);
			
			model.addAttribute("ph", pageHandler);
			model.addAttribute("album", album);
			model.addAttribute("reviews", list);
			model.addAttribute("cnt", cnt);
			model.addAttribute("map", map);
			
			todayBoard(model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "reviewDetail";
	}
	
	@PostMapping("/write")
	public String write(Review review, HttpSession session, HttpServletRequest req, Model model) {
		if (!loginCheck(req)) {
			return "redirect:/login?toUrl=/chart/review?id=" + review.getAlbum_id();
		}
		String id = (String)session.getAttribute("id");
		review.setUser_id(id);
		try {
			review.setUser_nickname(userService.select(id).getNickname());
			System.out.println(review);
			
			int rowCnt = reviewService.insert(review);
			
			if (rowCnt != 0) {
				model.addAttribute("msg", "write_ok");
				return "redirect:/chart/review?id=" + review.getAlbum_id();
			} else {
				throw new Exception("write error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "write_error");
			return "redirect:/chart/review?id=" + review.getAlbum_id();
		}
	}
	
	@PostMapping("/modify")
	public String modify(Review review, HttpSession session, Model model) {
		String user_id = (String)session.getAttribute("id");
		review.setUser_id(user_id);
		System.out.println(review);
		
		try {
			int res = reviewService.update(review);
			
			if (res != 1) throw new Exception("Modify Error");
			
			model.addAttribute("msg", "modify_ok");
			
			todayBoard(model);
			
			return "redirect:/chart/review?id=" + review.getAlbum_id();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "modify_error");
			return "redirect:/chart/review?id=" + review.getAlbum_id();
		}
	}
	
	@PostMapping("/delete")
	public String delete(Review review, HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		try {
			int rowCnt = reviewService.delete(review.getNum(), id);
			
			if (rowCnt != 1) throw new Exception("Delete Error");
			
			model.addAttribute("msg", "delete_ok");
			return "redirect:/chart/review?id=" + review.getAlbum_id();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "delete_error");
			return "redirect:/chart/review?id=" + review.getAlbum_id();
		}
	}
	
	@PostMapping("/like")
	@ResponseBody
	public ResponseEntity<Integer> like(Integer review_num, HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		try {
			Review review = reviewService.selectReviewLike(id, review_num);
			System.out.println(review);
			int res = 0;
			int cnt = 0;
			
			if (review != null) {
				System.out.println(review.getNum());
				res = reviewService.deleteReviewLike(id, review.getNum());
				cnt = -1;
				System.out.println(res);
				System.out.println(cnt);
			}
			else {
				res = reviewService.insertReviewLike(id, review_num);
				cnt = 1;
			}
			
			if (res != 1) {
				throw new Exception("Like Error");
			}
			reviewService.updateLikeCnt(cnt, review_num);
			int rowCnt = reviewService.countReviewCnt(review_num);
			return new ResponseEntity<Integer>(rowCnt, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
	}
	
	private void todayBoard(Model model) throws Exception {
		Promotion promotion = promotionService.selectRandom();
		List<Map> tmp = boardService.getBestBoard(); 
		List<Board> bestBoard = new ArrayList<>();
		for (Map map : tmp) {
			bestBoard.add(boardService.select((int)map.get("bno"))); 
		}
		model.addAttribute("bestBoard", bestBoard);
		model.addAttribute("promotion", promotion);
	}
	
	private boolean loginCheck(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		return session.getAttribute("id") != null;
	}
}
