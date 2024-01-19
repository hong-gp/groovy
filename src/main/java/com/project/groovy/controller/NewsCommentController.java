package com.project.groovy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.groovy.model.NewsComment;
import com.project.groovy.service.NewsCommentService;
import com.project.groovy.service.NewsService;
import com.project.groovy.service.UserService;

@Controller
public class NewsCommentController {

	NewsCommentService newsCommentService;
	NewsService newsService;
	UserService userService;
	
	@Autowired
	public NewsCommentController(NewsCommentService newsCommentService, UserService userService, NewsService newsService) {
		super();
		this.newsCommentService = newsCommentService;
		this.userService = userService;
		this.newsService = newsService;
	}
	
	@GetMapping("/newsComments")
	@ResponseBody
	public ResponseEntity<Map> list(Integer nno) {
		List<NewsComment> list = null;
		Map map = new HashMap();
		
		try {
			list = newsCommentService.getList(nno);
			Integer commentCnt = newsService.getNews(nno).getComment_cnt();
			map.put("list", list);
			map.put("commentCnt", commentCnt);
			return new ResponseEntity<Map>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/newsComments/{cno}")
	@ResponseBody
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer nno, HttpSession session) {
		String commenter = (String)session.getAttribute("id");
		
		try {
			int rowCnt = newsCommentService.remove(cno, commenter, nno);
			
			if (rowCnt != 1) {
				throw new Exception("delete failed");
			}
			return new ResponseEntity<String>("DEL_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("DEL_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/newsComments")
	@ResponseBody
	public ResponseEntity<String> write(@RequestBody NewsComment newsComment, HttpSession session) {
		
		String commenter = (String)session.getAttribute("id");
		newsComment.setCommenter(commenter);
		
		try {
			String nickname = userService.select(commenter).getNickname();
			newsComment.setCommenter_nickname(nickname);
			
			int rowCnt = newsCommentService.write(newsComment);
			
			if (rowCnt != 1) throw new Exception("write error");
			
			return new ResponseEntity<String>("WRITE_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("WRITE_ERROR", HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ResponseBody
	@PatchMapping("/newsComments/{cno}")
	public ResponseEntity<String> write(@PathVariable Integer cno, @RequestBody NewsComment newsComment, HttpSession session) {
		String commenter = (String)session.getAttribute("id");
		newsComment.setCommenter(commenter);
		newsComment.setCno(cno);
		System.out.println(newsComment);
		
		try {
			int rowCnt = newsCommentService.modify(newsComment);
			
			if (rowCnt != 1) throw new Exception("modify error");
			
			return new ResponseEntity<String>("MODIFY_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("MODIFY_ERROR", HttpStatus.BAD_REQUEST);
		}
	}
}
