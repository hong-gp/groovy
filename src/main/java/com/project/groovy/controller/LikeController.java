package com.project.groovy.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.groovy.dao.BoardDao;
import com.project.groovy.model.Board;
import com.project.groovy.service.BoardService;

@Controller
public class LikeController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/like")
	@ResponseBody
	public ResponseEntity<Integer> like(Integer bno, HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		try {
			int result = boardService.select(bno).getLike_cnt();
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/like")
	@ResponseBody
	public ResponseEntity<Integer> likeYes(Integer bno, HttpSession session) {
		String id = (String)session.getAttribute("id");
		System.out.println(bno + "/" + id);
		try {
			int rowCnt = boardService.selectLike(bno, id);
			System.out.println(rowCnt);
			int res = 0;
			int likeCnt = 0;
			if (rowCnt != 0) {
				res = boardService.deleteLike(bno, id);
				likeCnt = -1;
			} else {
				res = boardService.insertLike(bno, id);
				likeCnt = 1;
			}
			System.out.println(res);
			
			if (res == 1) {
				boardService.updateLikeCnt(bno, likeCnt);
				int result = boardService.select(bno).getLike_cnt();
				System.out.println(result);
				return new ResponseEntity<Integer>(result, HttpStatus.OK);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/check")
	@ResponseBody
	public ResponseEntity<Integer> check(Integer bno, HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		try {
			int res = boardService.selectLike(bno, id);
			return new ResponseEntity<Integer>(res, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
}
