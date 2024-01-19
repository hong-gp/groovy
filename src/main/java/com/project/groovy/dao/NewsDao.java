package com.project.groovy.dao;

import java.util.List;

import com.project.groovy.model.News;

public interface NewsDao {

	int count() throws Exception;

	List<News> selectAll() throws Exception;

	News select(Integer num) throws Exception;

	int insert(News news) throws Exception;

	int update(News news) throws Exception;

	int delete(Integer num) throws Exception;

	int deleteAll() throws Exception;

	int updateCommentCnt(Integer comment_cnt, Integer num) throws Exception;

}