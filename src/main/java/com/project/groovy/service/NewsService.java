package com.project.groovy.service;

import java.util.List;

import com.project.groovy.model.News;

public interface NewsService {

	int getCount() throws Exception;

	List<News> getNewsAll() throws Exception;

	News getNews(Integer num) throws Exception;

	int writeNews(News news) throws Exception;

	int modifyNews(News news) throws Exception;

	int modifyCommentCnt(Integer comment_cnt, Integer num) throws Exception;
	
	int remove(Integer num) throws Exception;

}