package com.project.groovy.service;

import java.util.List;
import java.util.Map;

import com.project.groovy.model.Board;
import com.project.groovy.model.SearchCondition;
import com.project.groovy.model.User;

public interface BoardService {

	int count() throws Exception;

	List<Board> selectAll() throws Exception;

	Board select(int num) throws Exception;

	int deleteAll();

	int delete(Integer num, String writer) throws Exception;

	int insert(Board board) throws Exception;

	int increaseViewCnt(int num) throws Exception;

	int update(Board board) throws Exception;

	List<Board> selectPage(Map map) throws Exception;

	List<Board> searchSelectPage(SearchCondition sc) throws Exception;

	int searchResultCnt(SearchCondition sc) throws Exception;

	int updateLikeCnt(Integer num, Integer like_cnt) throws Exception;

	int updateCommentCnt(Integer num, Integer comment_cnt) throws Exception;

	int insertLike(Integer bno, String id) throws Exception;
	
	int deleteLike(Integer bno, String id) throws Exception;
	
	int selectLike(Integer bno, String id) throws Exception;
	
	List<Map> getBestBoard() throws Exception;
}