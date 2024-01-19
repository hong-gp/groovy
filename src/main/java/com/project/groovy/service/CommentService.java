package com.project.groovy.service;

import java.util.List;

import com.project.groovy.model.Comment;
import com.project.groovy.model.SearchCondition;

public interface CommentService {

	int getCount(Integer bno) throws Exception;

	int remove(Integer cno, Integer bno) throws Exception;

	int write(Comment comment) throws Exception;
	
	List<Comment> getList(Integer bno) throws Exception;

	Comment read(Integer cno) throws Exception;

	int modify(Comment comment) throws Exception;
	
	int maxStep(Integer ref) throws Exception;
	
	int updateStep(Comment comment) throws Exception;
	
	int lastIndex() throws Exception;
	
	int maxRef(Integer bno) throws Exception;
	
	Integer step(Integer pcno, Integer re_level) throws Exception;
	
	int isdel(Integer cno, Integer bno, String commenter) throws Exception;
	
	List<Comment> selectPcno(Integer pcno) throws Exception;
	
	List<Comment> searchSelectPage(Integer bno, SearchCondition sc) throws Exception;
}