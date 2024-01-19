package com.project.groovy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.groovy.model.Board;
import com.project.groovy.model.News;
import com.project.groovy.model.Promotion;
import com.project.groovy.service.BoardService;
import com.project.groovy.service.NewsService;
import com.project.groovy.service.PromotionService;

@Controller
@RequestMapping("news")
public class NewsController {
	
	BoardService boardService;
	NewsService newsService;
	PromotionService promotionService;

	@Autowired
	public NewsController(BoardService boardService, NewsService newsService, PromotionService promotionService) {
		super();
		this.boardService = boardService;
		this.newsService = newsService;
		this.promotionService = promotionService;
	}

	@GetMapping("list")
	public String news(Model model) {
		try {
			todayBoard(model);
			
			List<News> list = newsService.getNewsAll();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "error");
			return "/";
		}
		return "news";
	}
	
	@GetMapping("view")
	public String news(Integer num, Model model) {
		try {
			todayBoard(model);
			News news = newsService.getNews(num);
			news.setContent(news.getContent().replace("\n", "<br>"));
			model.addAttribute("news", news);
			return "newsView";
		} catch (Exception e) {
			e.printStackTrace();
			return "news";
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
	
	
}
