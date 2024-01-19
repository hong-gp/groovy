package com.project.groovy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groovy.dao.NewsCommentDao;
import com.project.groovy.dao.NewsDao;
import com.project.groovy.model.NewsComment;

@Service
public class NewsCommentServiceImpl implements NewsCommentService {

	NewsCommentDao newsCommentDao;
	NewsDao newsDao;
	
	@Autowired
	public NewsCommentServiceImpl(NewsCommentDao newsCommentDao, NewsDao newsDao) {
		super();
		this.newsCommentDao = newsCommentDao;
		this.newsDao = newsDao;
	}
	
	@Override
	public int getCount(Integer nno) throws Exception {
		return newsCommentDao.count(nno);
	}
	
	@Override
	public int remove(Integer cno, String commenter, Integer nno) throws Exception {
		int rowCnt = newsCommentDao.delete(cno, commenter);
		
		if (rowCnt == 1) {
			newsDao.updateCommentCnt(-1, nno);
		}
		return rowCnt;
	}
	
	@Override
	public int write(NewsComment newsComment) throws Exception {
		int rowCnt = newsCommentDao.insert(newsComment);
		
		if (rowCnt == 1) {
			newsDao.updateCommentCnt(1, newsComment.getNno());
		}
		return rowCnt;
	}
	
	@Override
	public List<NewsComment> getList(Integer nno) throws Exception {
		return newsCommentDao.selectAll(nno);
	}
	
	@Override
	public NewsComment read(Integer cno) throws Exception {
		return newsCommentDao.select(cno);
	}
	
	@Override
	public int modify(NewsComment newsComment) throws Exception {
		return newsCommentDao.update(newsComment);
	}
}
