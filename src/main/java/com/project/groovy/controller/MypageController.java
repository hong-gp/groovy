package com.project.groovy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.groovy.model.Review;
import com.project.groovy.model.User;
import com.project.groovy.service.ReviewService;
import com.project.groovy.service.SpotifyService;
import com.project.groovy.service.UserService;

import se.michaelthelin.spotify.model_objects.specification.Album;

@Controller
@RequestMapping("mypage")
public class MypageController {

	UserService userService;
	ReviewService reviewService;
	SpotifyService spotifyService;

	@Autowired
	public MypageController(UserService userService, ReviewService reviewService, SpotifyService spotifyService) {
		super();
		this.userService = userService;
		this.reviewService = reviewService;
		this.spotifyService = spotifyService;
	}
	
	@GetMapping("/userInfo")
	public String userInfo(Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		try {
			User user = userService.select(id);
			model.addAttribute("user", user);
			return "mypageUserInfo";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "index";
		}
	}
	
	@GetMapping("/myReview")
	public String myReview(Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		
		try {
			User user = userService.select(id);
			List<Review> reviews = reviewService.getReviewUser(id);
			List<Album> albums = new ArrayList<>();
			
			for (Review review : reviews) {
				review.setComment(review.getComment().replaceAll("\n", "<br>"));
				albums.add(spotifyService.searchAlbumById(review.getAlbum_id()));
			}
			
			model.addAttribute("user", user);
			model.addAttribute("reviews", reviews);
			model.addAttribute("albums", albums);
			return "mypageMyReview";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "redirect:/";
		}
	}
}
