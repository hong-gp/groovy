package com.project.groovy.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groovy.dao.BoardDao;
import com.project.groovy.model.Board;
import com.project.groovy.model.SearchCondition;
import com.project.groovy.model.User;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;
	
	@Override
	public int count() throws Exception {
		return boardDao.count();
	}
	
	@Override
	public List<Board> selectAll() throws Exception {
		return boardDao.selectAll();
	}
	
	@Override
	public Board select(int num) throws Exception {
		return boardDao.select(num);
	}
	
	@Override
	public int deleteAll() {
		return boardDao.deleteAll();
	}
	
	@Override
	public int delete(Integer num, String writer) throws Exception {
		return boardDao.delete(num, writer);
	}
	
	@Override
	public int insert(Board board) throws Exception {
		return boardDao.insert(board);
	}
	
	@Override
	public int increaseViewCnt(int num) throws Exception {
		return boardDao.increaseViewCnt(num);
	}
	
	@Override
	public int update(Board board) throws Exception {
		return boardDao.update(board);
	}
	
	@Override
	public List<Board> selectPage(Map map) throws Exception {
		return boardDao.selectPage(map);
	}
	
	@Override
	public List<Board> searchSelectPage(SearchCondition sc) throws Exception {
		return boardDao.searchSelectPage(sc);
	}
	
	@Override
	public int searchResultCnt(SearchCondition sc) throws Exception {
		return boardDao.searchResultCnt(sc);
	}
	
	@Override
	public int updateLikeCnt(Integer num, Integer like_cnt) throws Exception {
		return boardDao.updateLikeCnt(num, like_cnt);
	}
	
	@Override
	public int updateCommentCnt(Integer num, Integer comment_cnt) throws Exception {
		return boardDao.updateCommentCnt(num, comment_cnt);
	}

	@Override
	public int insertLike(Integer bno, String id) throws Exception {
		return boardDao.insertLike(bno, id);
	}

	@Override
	public int deleteLike(Integer bno, String id) throws Exception {
		return boardDao.deleteLike(bno, id);
	}

	@Override
	public int selectLike(Integer bno, String id) throws Exception {
		return boardDao.selectLike(bno, id);
	}

	@Override
	public List<Map> getBestBoard() throws Exception {
		return boardDao.selectBestBoard();
	}
}
