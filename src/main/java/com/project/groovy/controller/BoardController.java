package com.project.groovy.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.groovy.model.Board;
import com.project.groovy.model.PageHandler;
import com.project.groovy.model.Promotion;
import com.project.groovy.model.SearchCondition;
import com.project.groovy.model.User;
import com.project.groovy.service.BoardService;
import com.project.groovy.service.PromotionService;
import com.project.groovy.service.UserService;
import com.project.groovy.util.Time;

@Controller
@RequestMapping("board")
public class BoardController {
	
	private BoardService boardService;
	private UserService userService;
	private PromotionService promotionService;
	
	public BoardController() {
		super();
	}

	@Autowired
	public BoardController(BoardService boardService, UserService userService, PromotionService promotionService) {
		super();
		this.boardService = boardService;
		this.userService = userService;
		this.promotionService = promotionService;
	}

	@GetMapping("list")
	public String board(SearchCondition sc, HttpServletRequest req, Model model) {
		
		if (!loginCheck(req)) {
			return "redirect:/login?toUrl=" + req.getRequestURL();
		}
		
		try {
			int totalCnt = boardService.searchResultCnt(sc);
			List<Map> tmp = boardService.getBestBoard();
			List<Board> bestBoard = new ArrayList<>();
			
			for (Map map : tmp) {
				bestBoard.add(boardService.select((int)map.get("bno")));
			}
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			System.out.println(sc);
			
			List<Board> list = boardService.searchSelectPage(sc);
			model.addAttribute("ph", pageHandler);
			model.addAttribute("list", list);
			todayBoard(model);
			
			Date now = new Date();
			model.addAttribute("now", now);
			model.addAttribute("time", Time.calculateTime(now));
			model.addAttribute("bestBoard", bestBoard);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board";
	}
	
	private boolean loginCheck(HttpServletRequest req) {
		HttpSession session = req.getSession();
		
		return session.getAttribute("id") != null;
	}
	
	@GetMapping("write")
	public String write(SearchCondition sc, Model model) throws Exception {
		model.addAttribute("sc", sc);
		todayBoard(model);
		return "boardWrite";
	}
	
	@PostMapping("write")
	public String write(Board board, Integer page, Integer pageSize, String fileName, 
			MultipartFile[] uploadFile, Model model, HttpSession session, RedirectAttributes redatt, HttpServletRequest req) {
		
		if (!loginCheck(req)) {
			return "redirect:/login?toUrl=" + req.getRequestURL();
		}
		
		String uploadFolder = "D:\\springWorkspace\\groovy\\groovy\\src\\main\\webapp\\resources\\images\\board";

		for (MultipartFile multipartFile : uploadFile) {
			String uploadFileName = multipartFile.getOriginalFilename();
			if (uploadFileName.equals("")) break;
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
		try {
			redatt.addAttribute("page", page);
			redatt.addAttribute("pageSize", pageSize);
			
			String writer = (String)session.getAttribute("id");
			User user = userService.select(writer);
			board.setWriter_nickname(user.getNickname());
			board.setWriter(writer);
			
			
			int rowCnt = boardService.insert(board);
			
			if (rowCnt == 1) {
				redatt.addFlashAttribute("msg", "write_ok");
				return "redirect:/board/list";
			}
			else {
				throw new Exception("board write error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("board", board);
			model.addAttribute("msg", "write_error");
			return "boardWrite";
		}
	}
	
	@GetMapping("read")
	public String read(SearchCondition sc, Integer num, Model model, HttpServletRequest req) {
		if (!loginCheck(req)) {
			return "redirect:/login?toUrl=" + req.getRequestURL();
		}
		try {
			boardService.increaseViewCnt(num);
			Board board = boardService.select(num);
			board.setContent(board.getContent().replace("\n", "<br>"));
			model.addAttribute("board", board);
			model.addAttribute("sc", sc);
			todayBoard(model);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board/list" + sc.getQueryString();
		}
		return "boardView";
	}
	
	@GetMapping("modify")
	public String modify(Integer num, Model model, HttpSession session, RedirectAttributes reatt) {
		
		try {
			Board board = boardService.select(num);
			todayBoard(model);
			
			if (!board.getWriter().equals(session.getAttribute("id")+"") || session.getAttribute("id") == null) {
				reatt.addAttribute("msg", "modify_error");
				throw new Exception("modify error");
			}
			model.addAttribute("board", board);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/board/list";
		}
		return "boardEdit";
	}
	
	@PostMapping("modify")
	public String modify(Board board, SearchCondition sc, String fileName, Integer del, 
			MultipartFile[] uploadFile, Model model, HttpSession session, RedirectAttributes reatt) throws Exception {
		String writer = (String)session.getAttribute("id");
		board.setWriter(writer);
		
		String uploadFolder = "D:\\springWorkspace\\groovy\\groovy\\src\\main\\webapp\\resources\\images\\board";
		
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
				if (uploadFileName.equals("")) break;
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
		System.out.println("수정 post: " + board);
		
		try {
			System.out.println(board);
			int rowCnt = boardService.update(board);
			
			if (rowCnt == 1) {
				reatt.addFlashAttribute("msg", "modify_ok");
				return "redirect:/board/list" + sc.getQueryString();
			} else {
				throw new Exception("modify error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("board", board);
			model.addAttribute("msg", "modify_error");
			return "boardEdit";
		}
	}
	
	@PostMapping("remove")
	public String remove(Integer num, SearchCondition sc, Model model, HttpSession session, RedirectAttributes reatt, MultipartFile[] deleteFile) {
		
//		String uploadFolder = "D:\\springWorkspace\\groovy\\groovy\\src\\main\\webapp\\resources\\images\\board";
//
//		for (MultipartFile multipartFile : deleteFile) { // 여러개의 파일일 경우 향상된 for문 이용
//
//			System.out.println("------------------------");
//			System.out.println("Upload file name : " + multipartFile.getOriginalFilename()); // 파일 이름
//			System.out.println("Upload file size : " + multipartFile.getSize()); // 파일 크기
//
//			String deleteFileName = multipartFile.getOriginalFilename(); 
//			System.out.println("uplodaFileName : "+deleteFile);
//			deleteFileName = deleteFileName.substring(deleteFileName.lastIndexOf("\\") + 1); // 경로가 있다면 원래 이름만 가져올 수 있도록
//			System.out.println("last file name : " + deleteFileName);
//			File delFile = new File(uploadFolder, deleteFileName); //uploadFolder 위치에 uploadFileName으로 생성
//
//			try {	
//				if(delFile.exists()) { // 파일이 존재하면
//					delFile.delete(); // 파일 삭제	
//				}
//
//			} catch (Exception e) {
//				e.getMessage();
//			}
//		}
		
		try {
			String writer = (String)session.getAttribute("id");
			int rowCnt = boardService.delete(num, writer);
			
			if (rowCnt == 1) {
				reatt.addFlashAttribute("msg", "del");
				return "redirect:/board/list" + sc.getQueryString();
			}
			else {
				System.out.println(num + "/"  +writer);
				throw new Exception("board remove error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			reatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/list" + sc.getQueryString();
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
}
