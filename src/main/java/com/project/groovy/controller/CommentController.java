package com.project.groovy.controller;

import java.util.List;

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

import com.project.groovy.model.Comment;
import com.project.groovy.service.CommentService;
import com.project.groovy.service.UserService;

@Controller
public class CommentController {

	CommentService commentService;
	UserService userService;
	
	@Autowired
	public CommentController(CommentService commentService, UserService userService) {
		super();
		this.commentService = commentService;
		this.userService = userService;
	}
	
	@GetMapping("/comments")
	@ResponseBody
	public ResponseEntity<List<Comment>> list(Integer bno) {
		List<Comment> list = null;
		
		try {
			list = commentService.getList(bno);
			return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Comment>>(list, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/comments/{cno}")
	@ResponseBody
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
		String commenter = (String)session.getAttribute("id");
		System.out.println(cno + ", " + bno);
		
		try {
			int rowCnt = commentService.remove(cno, bno);
			if (rowCnt != 1) {
				throw new Exception("delete failed");
			}
			return new ResponseEntity<String>("DEL_OK", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("DEL_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@PostMapping("/comments")
	public ResponseEntity<String> write(@RequestBody Comment comment, HttpSession session) {
		String commenter = (String)session.getAttribute("id");
		comment.setCommenter(commenter);
		
		try {
			String nickname = userService.select(commenter).getNickname();
			comment.setCommenter_nickname(nickname);
			System.out.println("comment = " + comment);
			int cnt = commentService.write(comment);
			System.out.println(comment);
			
			if (cnt != 1) throw new Exception("write Error");
			
			return new ResponseEntity<String>("WRITE_OK", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("WRITE_ERROR", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// 같은 부모의 같은 레벨을 가진 Step에 +1을 해준다
	@ResponseBody
	@PostMapping("/reply")
	public ResponseEntity<String> reply(@RequestBody Comment comment, Integer bno, HttpSession session) {
		String commenter = (String)session.getAttribute("id");
		comment.setCommenter(commenter);
		
		try {
			Comment pComment = commentService.read(comment.getPcno());
			comment.setRef(pComment.getRef());
			comment.setRe_level(pComment.getRe_level() + 1);
			Integer step = pComment.getRe_step() + 1;
			comment.setRe_step(step);
			String nickname = userService.select(commenter).getNickname();
			comment.setCommenter_nickname(nickname);
			System.out.println("comment = " + comment);

			Integer s = commentService.step(comment.getPcno(), comment.getRe_level());
			
			if (s != null ) {
				comment.setRe_step(s + 1);
			}
			commentService.updateStep(comment);
			
			
			int cnt = commentService.write(comment);
			System.out.println(comment);
			
			if (cnt != 1) throw new Exception("reply Error");
			return new ResponseEntity<String>("REPLY_OK", HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("REPLY_ERROR", HttpStatus.BAD_REQUEST);
		}
	}
	
	@ResponseBody
	@PatchMapping("/comments/{cno}")
	public ResponseEntity<String> write(@PathVariable Integer cno, @RequestBody Comment comment, HttpSession session) {
		String commenter = (String)session.getAttribute("id");
		comment.setCommenter(commenter);
		comment.setCno(cno);
		System.out.println("comment = " + comment);
		
		try {
			int cnt = commentService.modify(comment);
			
			if (cnt != 1) throw new Exception("Modify Error");
			
			return new ResponseEntity<String>("MODIFY_OK" ,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("MODIFY_ERROR", HttpStatus.BAD_REQUEST);
		}
	}
}
