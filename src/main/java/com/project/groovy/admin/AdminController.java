package com.project.groovy.admin;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.groovy.model.Board;
import com.project.groovy.model.News;
import com.project.groovy.model.Promotion;
import com.project.groovy.model.Review;
import com.project.groovy.model.User;
import com.project.groovy.service.BoardService;
import com.project.groovy.service.NewsService;
import com.project.groovy.service.PromotionService;
import com.project.groovy.service.ReviewService;
import com.project.groovy.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {

	AdminService adminService;
	NewsService newsService;
	UserService userService;
	ReviewService reviewService;
	BoardService boardService;
	PromotionService promotionService;

	@Autowired
	public AdminController(AdminService adminService, NewsService newsService, UserService userService, ReviewService reviewService, BoardService boardService, PromotionService promotionService) {
		super();
		this.adminService = adminService;
		this.newsService = newsService;
		this.userService = userService;
		this.reviewService = reviewService;
		this.boardService = boardService;
		this.promotionService = promotionService;
	}
	
	@GetMapping("/login")
	public String adminLoginForm() {
		return "admin/adminLogin";
	}
	
	@PostMapping("/login")
	public String login(String id, String password, HttpServletResponse resp, HttpServletRequest req) {
		try {
			if (!loginCheck(id, password)) {
				String msg = URLEncoder.encode("아이디또는 비밀번호가 일치하지 않습니다.", "utf-8");
				return "redirect:/admin/login?msg=" + msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "/";
		}
		HttpSession session = req.getSession();
		session.setAttribute("adminId", id);
		
		return "redirect:/admin/news/management";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("세션 종료");
		return "redirect:/";
	}
	
	private boolean loginCheck(String id, String pwd) throws Exception {
		Admin admin = adminService.getAdmin(id);
		if (admin == null) return false;
		
		return admin.getPassword().equals(pwd) && admin.getId().equals(id);
	}
	
	/**
	 * 뉴스 관리
	 * @return
	 */
	@GetMapping("/news/management")
	public String newsManagement(Model model) {
		try {
			List<News> list = newsService.getNewsAll();
			System.out.println(list);
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/adminNews";
	}
	
	@GetMapping("news/write")
	public String newsWrite() {
		return "admin/adminNewsWrite";
	}
	
	@PostMapping("news/write")
	public String newsWrite(News news, String fileName, MultipartFile[] uploadFile, Model model, HttpSession session) {
		String uploadFolder = "D:\\springWorkspace\\groovy\\groovy\\src\\main\\webapp\\resources\\images\\news";
		
		for (MultipartFile multipartFile : uploadFile) {
			String uploadFileName = multipartFile.getOriginalFilename();
			System.out.println("uploadFileName: " + uploadFileName);
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			System.out.println("last file name: " + uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			System.out.println("변환 후 파일이름 " + uploadFileName);
			
			File saveFile = new File(uploadFolder, uploadFileName);
			try {
				multipartFile.transferTo(saveFile);
				news.setImg_src(uploadFileName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			String writer = (String)session.getAttribute("adminId");
			news.setWriter(writer);
			int res = newsService.writeNews(news);
			
			if (res == 1) {
				return "redirect:/admin/news/management";
			} else {
				throw new Exception("news write error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "redirect:/admin/news/management";
		}
	}
	
	@GetMapping("news/modify")
	public String newsModify(Integer num, Model model) {
		try {
			News news = newsService.getNews(num);
			news.setTitle(news.getTitle().replace("\"", "&quot;"));
			model.addAttribute("news", news);
			
			if (news == null) {
				throw new Exception("news modify error");
			}
			return "/admin/adminNewsModify";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/news/management";
		}
	}
	
	@PostMapping("news/modify")
	public String newsModify(News news, String fileName, Integer del, MultipartFile[] uploadFile, Model model, HttpSession session) throws Exception {
		String uploadFolder = "D:\\springWorkspace\\groovy\\groovy\\src\\main\\webapp\\resources\\images\\news";
		String str = "";
		
		if (del == 1) {
			News newsDto = newsService.getNews(news.getNum());
			news.setImg_src(newsDto.getImg_src());
		}
		else if (del == 3) {
			news.setImg_src(null);
		}
		else {
			for (MultipartFile multipartFile : uploadFile) {
				String uploadFileName = multipartFile.getOriginalFilename();
				System.out.println("uploadFileName: " + uploadFileName);
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				System.out.println("last file name: " + uploadFileName);
				
				UUID uuid = UUID.randomUUID();
				uploadFileName = uuid.toString() + "_" + uploadFileName;
				System.out.println("변환 후 파일이름 " + uploadFileName);
				
				File saveFile = new File(uploadFolder, uploadFileName);
				try {
					multipartFile.transferTo(saveFile);
					news.setImg_src(uploadFileName);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("수정 post: " + news);
		
		try {
			String writer = (String)session.getAttribute("adminId");
			news.setWriter(writer);
			int res = newsService.modifyNews(news);
			
			if (res == 1) {
				return "redirect:/admin/news/management";
			} else {
				throw new Exception("news write error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "redirect:/admin/news/management";
		}
	}
	
	@GetMapping("/news/delete")
	public String delete(Integer num, Model model) {
		try {
			int res = newsService.remove(num);
			if (res != 1) {
				throw new Exception("news write error");
			}
			return "redirect:/admin/news/management";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "redirect:/admin/news/management";
		}
	}
	
	/**
	 * 유저 관리
	 * @return
	 */
	@GetMapping("/user/management")
	public String userManagement(Model model) {
		try {
			List<User> list = userService.selectAll();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/adminUser";
	}
	
	@GetMapping("/user/modify")
	public String userModify(String id, Model model) {
		try {
			User user = userService.select(id);
			model.addAttribute("user", user);
			
			if (user == null) {
				throw new Exception("user modify error");
			}
			return "/admin/adminUserModify";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/user/management";
		}
	}
	
	@PostMapping("/user/modify")
	public String userModify(User user, Model model) {
		try {
			int res = userService.updateAll(user);
			System.out.println(user);
			
			if (res != 1) {
				throw new Exception("user modify error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/user/management";
	}
	
	@GetMapping("/user/delete")
	public String userDelete(String id, Model model) {
		try {
			int res = userService.delete(id);
			if (res != 1) {
				throw new Exception("user delete error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}
		return "redirect:/admin/user/management";
	}
	
	/**
	 * 리뷰 관리
	 */
	@GetMapping("/review/management")
	public String reviewManagement(Model model) {
		try {
			List<Review> list = reviewService.selectA();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/adminReview";
	}
	
	@GetMapping("/review/modify")
	public String reviewModify(Integer num, Model model) {
		try {
			Review review = reviewService.select(num);
			model.addAttribute("review", review);
			
			if (review == null) {
				throw new Exception("review modify error");
			}
			return "/admin/adminReviewModify";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/admin/review/management";
		}
	}
	
	@PostMapping("/review/modify")
	public String reviewModify(Review review, Model model) {
		try {
			int res = reviewService.update(review);
			System.out.println(review);
			
			if (res != 1) {
				throw new Exception("review modify error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/review/management";
	}
	
	@GetMapping("/review/delete")
	public String deleteReview(Integer num, String id, Model model) {
		System.out.println(num);
		try {
			int res = reviewService.delete(num, id);
			
			if (res != 1) {
				throw new Exception("review delete error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}
		return "redirect:/admin/review/management";
	}
	
	/**
	 * 커뮤니티 관리
	 */
	@GetMapping("/board/management")
	public String boardManagement(Model model) {
		try {
			List<Board> list = boardService.selectAll();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/adminBoard";
	}
	
	@GetMapping("/board/modify")
	public String boardModify(Integer num, Model model) {
		try {
			Board board = boardService.select(num);
			
			if (board == null) {
				throw new Exception("board modify error");
			}
			board.setContent(board.getContent().replace("\n\r", "<br>"));
			model.addAttribute("board", board);
			return "admin/adminBoardModify";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "redirect:/admin/board/management";
		}
	}
	
	@PostMapping("/board/modify")
	public String boardModify(Board board, String fileName, Integer del, MultipartFile[] uploadFile, Model model) throws Exception {
		
		String uploadFolder = "D:\\springWorkspace\\groovy\\groovy\\src\\main\\webapp\\resources\\images\\board";
		String str = "";
		
		if (del == 1) {
			Board boardDto = boardService.select(board.getNum());
			board.setImg_src(boardDto.getImg_src());
		}
		else if (del == 3) {
			board.setImg_src(null);
		}
		else {
			for (MultipartFile multipartFile : uploadFile) {
				String uploadFileName = multipartFile.getOriginalFilename();
				System.out.println("uploadFileName: " + uploadFileName);
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				System.out.println("last file name: " + uploadFileName);
				
				UUID uuid = UUID.randomUUID();
				uploadFileName = uuid.toString() + "_" + uploadFileName;
				System.out.println("변환 후 파일이름 " + uploadFileName);
				
				File saveFile = new File(uploadFolder, uploadFileName);
				try {
					multipartFile.transferTo(saveFile);
					board.setImg_src(uploadFileName);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			int res = boardService.update(board);
			
			if (res != 1) {
				throw new Exception("board modify error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}
		return "redirect:/admin/board/management";
	}
	
	@GetMapping("/board/delete")
	public String boardDelete(Integer num, String id, Model model) {
		try {
			int res = boardService.delete(num, id);
			
			if (res != 1) {
				throw new Exception("board delete error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}
		return "redirect:/admin/board/management";
	}
	
	@GetMapping("/promotion/management")
	public String promotionManagement(Model model) {
		try {
			List<Promotion> list = promotionService.selectAll();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/adminPromotion";
	}
	
	@GetMapping("/promotion/add")
	public String promotionAdd() {
		return "admin/adminPromotionAdd";
	}
	
	@PostMapping("/promotion/add")
	public String promotionAdd(Promotion promotion, String fileName, MultipartFile[] uploadFile, Model model, HttpSession session) {
		String uploadFolder = "D:\\springWorkspace\\groovy\\groovy\\src\\main\\webapp\\resources\\images\\promotion";
		
		for (MultipartFile multipartFile : uploadFile) {
			String uploadFileName = multipartFile.getOriginalFilename();
			System.out.println("uploadFileName: " + uploadFileName);
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			System.out.println("last file name: " + uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			System.out.println("변환 후 파일이름 " + uploadFileName);
			
			File saveFile = new File(uploadFolder, uploadFileName);
			try {
				multipartFile.transferTo(saveFile);
				promotion.setImg_src(uploadFileName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(promotion);
		
		try {
			int res = promotionService.insert(promotion);
			
			if (res == 1) {
				return "redirect:/admin/promotion/management";
			} else {
				throw new Exception("news write error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "redirect:/admin/promotion/management";
		}
	}
	
	@GetMapping("/promotion/modify")
	public String promotionModify(Integer num, Model model) {
		try {
			Promotion promotion = promotionService.select(num);
			
			if (promotion == null) {
				throw new Exception("promotion modify error");
			}
			model.addAttribute("promotion", promotion);
			return "admin/adminPromotionModify";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "redirect:/admin/promotion/management";
		}
	}
	
	@PostMapping("/promotion/modify")
	public String promotionModify(Promotion promotion, String fileName, Integer del, MultipartFile[] uploadFile, Model model) throws Exception {
		
		String uploadFolder = "D:\\springWorkspace\\groovy\\groovy\\src\\main\\webapp\\resources\\images\\promotion";
		String str = "";
		
		if (del == 1) {
			Promotion promotionDto = promotionService.select(promotion.getNum());
			promotion.setImg_src(promotionDto.getImg_src());
		}
		else if (del == 3) {
			promotion.setImg_src(null);
		}
		else {
			for (MultipartFile multipartFile : uploadFile) {
				String uploadFileName = multipartFile.getOriginalFilename();
				System.out.println("uploadFileName: " + uploadFileName);
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				System.out.println("last file name: " + uploadFileName);
				
				UUID uuid = UUID.randomUUID();
				uploadFileName = uuid.toString() + "_" + uploadFileName;
				System.out.println("변환 후 파일이름 " + uploadFileName);
				
				File saveFile = new File(uploadFolder, uploadFileName);
				try {
					multipartFile.transferTo(saveFile);
					promotion.setImg_src(uploadFileName);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			int res = promotionService.update(promotion);
			
			if (res != 1) {
				throw new Exception("promotion modify error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}
		return "redirect:/admin/promotion/management";
	}
	
	@GetMapping("/promotion/delete")
	public String promotionDelete(Integer num, Model model) {
		try {
			int res = promotionService.remove(num);
			
			if (res != 1) {
				throw new Exception("promotion delete error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
		}
		return "redirect:/admin/promotion/management";
	}
}
