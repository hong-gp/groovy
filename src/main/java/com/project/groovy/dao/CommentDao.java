package com.project.groovy.dao;

import java.util.List;

import com.project.groovy.model.Comment;
import com.project.groovy.model.SearchCondition;

public interface CommentDao {

	int deleteAll(Integer bno) throws Exception;

	int count(Integer bno) throws Exception;

	int delete(Integer cno) throws Exception;

	int insert(Comment comment);

	List<Comment> selectAll(Integer bno) throws Exception;

	Comment select(Integer cno);

	int update(Comment comment);
	
	int maxRef(Integer bno) throws Exception;
	
	int maxStep(Integer ref) throws Exception;
	
	Integer step(Integer pcno, Integer re_level) throws Exception;
	
	int updateStep(Comment comment) throws Exception;
	
	int selectLast() throws Exception;
	
	int isdel(Integer cno, String commenter) throws Exception;
	
	List<Comment> selectPcno(Integer pcno) throws Exception;
	
	List<Comment> searchSelectPage(Integer bno, SearchCondition sc) throws Exception;
}