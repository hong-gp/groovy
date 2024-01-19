package com.project.groovy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groovy.dao.NewsDao;
import com.project.groovy.model.News;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsDao newsDao;
	
	@Override
	public int getCount() throws Exception {
		return newsDao.count();
	}
	
	@Override
	public List<News> getNewsAll() throws Exception {
		return newsDao.selectAll();
	}
	
	@Override
	public News getNews(Integer num) throws Exception {
		return newsDao.select(num);
	}
	
	@Override
	public int writeNews(News news) throws Exception {
		return newsDao.insert(news);
	}
	
	@Override
	public int modifyNews(News news) throws Exception {
		return newsDao.update(news);
	}
	
	@Override
	public int modifyCommentCnt(Integer comment_cnt, Integer num) throws Exception {
		return newsDao.updateCommentCnt(comment_cnt, num);
	}

	@Override
	public int remove(Integer num) throws Exception {
		return newsDao.delete(num);
	}
}
