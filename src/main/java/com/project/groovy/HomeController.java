package com.project.groovy;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.groovy.model.News;
import com.project.groovy.model.Review;
import com.project.groovy.service.NewsService;
import com.project.groovy.service.ReviewService;
import com.project.groovy.service.SpotifyService;

import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	ReviewService reviewService;
	SpotifyService spotifyService;
	NewsService newsService;
	
	@Autowired
	public HomeController(ReviewService reviewService, SpotifyService spotifyService, NewsService newsService) {
		super();
		this.reviewService = reviewService;
		this.spotifyService = spotifyService;
		this.newsService = newsService;
	}

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		try {
			Review review = reviewService.selectRandomReview();
			review.setComment(review.getComment().replace("\n", "<br>"));
			Album album = spotifyService.searchAlbumById(review.getAlbum_id());
			List<AlbumSimplified> latestAlbum = spotifyService.getLatestAlbums(5);
			List<News> news = newsService.getNewsAll();
			
			model.addAttribute("review", review);
			model.addAttribute("album", album);
			model.addAttribute("latestAlbum", latestAlbum);
			model.addAttribute("news", news);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "index";
		}
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
}
