package com.project.groovy.service;

import java.util.List;

import com.project.groovy.model.NewsComment;

public interface NewsCommentService {

	int getCount(Integer nno) throws Exception;

	int remove(Integer cno, String commenter, Integer nno) throws Exception;

	int write(NewsComment newsComment) throws Exception;

	List<NewsComment> getList(Integer nno) throws Exception;

	NewsComment read(Integer cno) throws Exception;

	int modify(NewsComment newsComment) throws Exception;

}