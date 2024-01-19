package com.project.groovy.controller;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.groovy.model.User;
import com.project.groovy.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	/**
	 * 로그인 폼
	 * @param sessionId
	 * @return
	 */
	@GetMapping("login")
	public String loginForm(@CookieValue(value="JSESSIONID", required=false) String sessionId) {
		System.out.println("쿠키에 저장된 세션 아이디: " + sessionId);
		return "login";
	}
	
	/**
	 * 로그인
	 * @param id
	 * @param password
	 * @param rememberId
	 * @param resp
	 * @param req
	 * @param toUrl
	 * @return
	 * @throws Exception
	 */
	@PostMapping("login")
	public String login(String id, String password, boolean rememberId, HttpServletResponse resp, HttpServletRequest req, String toUrl) throws Exception {
		if (!loginCheck(id, password)) {
			String msg = URLEncoder.encode("아이디또는 비밀번호가 일치하지 않습니다.", "utf-8");
			return "redirect:/login?msg=" + msg;
		}
		String nickname = userService.select(id).getNickname();
		
		HttpSession session = req.getSession();
		session.setAttribute("id", id);
		session.setAttribute("nickname", nickname);
		
		if (rememberId) {
			Cookie cookie = new Cookie("id", id);
			resp.addCookie(cookie);
		} else {
			Cookie cookie = new Cookie("id", "");
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
		}
		
		if (toUrl == null || toUrl.equals(""))
			toUrl = "/";
		
		return "redirect:" + toUrl;
	}
	
	/**
	 * 로그아웃
	 * @param session
	 * @return
	 */
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("세션 종료");
		return "redirect:/";
	}
	
	/**
	 * 로그인 체크 함수
	 * @param id
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	private boolean loginCheck(String id, String pwd) throws Exception {
		User user = userService.select(id);
		if (user == null) return false;
		
		return user.getPassword().equals(pwd) && user.getId().equals(id);
	}
	
	/**
	 * 회원가입 폼
	 * @return
	 */
	@GetMapping("/register")
	public String register() {
		
		return "register";
	}
	
	/**
	 * 회원가입
	 * @param user
	 * @param model
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<String> save(@RequestBody User user, Model model) {
		
		try {
			User check = userService.select(user.getId());
			if (check != null) {
				return new ResponseEntity<String>("REGIST_CHECK", HttpStatus.OK);
			}
			int rowCnt = userService.regist(user);
			if (rowCnt != 1) throw new Exception("regist error");
			return new ResponseEntity<String>("REGIST_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("REGIST_ERROR", HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 아이디 찾기
	 * @return
	 */
	@GetMapping("findUser")
	public String findUser() {
		return "findUser";
	}
	
	@PostMapping("findUserId")
	public String findUserId(User user, Model model) {
		try {
			User res = userService.findUserId(user);
			
			if (res != null) {
				model.addAttribute("find", "id");
				model.addAttribute("user", res);
				return "findUserResult";
			} else {
				throw new Exception("findUserId error"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("findid", user);
			model.addAttribute("id_msg", "아이디가 존재하지 않습니다.");
			return "findUser";
		}
	}
	
	/**
	 * 비밀번호 찾기
	 * @param user
	 * @param model
	 * @return
	 */
	@PostMapping("findUserPw")
	public String findUserPw(User user, Model model) {
		try {
			User res = userService.findUserId(user);
			
			if (res != null) {
				model.addAttribute("find", "pw");
				model.addAttribute("user", res);
				return "findUserResult";
			} else {
				throw new Exception("findUserPw error"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("findpw", user);
			model.addAttribute("pw_msg", "잘못된 정보를 입력했습니다.");
			return "findUser";
		}
	}
	
	@GetMapping("findUserResult")
	public String findUserResult() {
		return "findUserResult";
	}
}
