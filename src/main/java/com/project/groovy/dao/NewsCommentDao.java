package com.project.groovy.dao;

import java.util.List;

import com.project.groovy.model.NewsComment;

public interface NewsCommentDao {

	int deleteAll(Integer nno) throws Exception;

	int count(Integer nno) throws Exception;

	int delete(Integer cno, String commenter) throws Exception;

	int insert(NewsComment newsComment) throws Exception;

	List<NewsComment> selectAll(Integer nno) throws Exception;

	NewsComment select(Integer cno) throws Exception;

	int update(NewsComment newsComment) throws Exception;

}