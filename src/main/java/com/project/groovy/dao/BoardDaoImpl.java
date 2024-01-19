package com.project.groovy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.groovy.model.Board;
import com.project.groovy.model.SearchCondition;
import com.project.groovy.model.User;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	SqlSession session;
	String namespace = "com.project.groovy.Board.";
	
	@Override
	public int count() throws Exception {
		return session.selectOne(namespace + "count");
	}
	
	@Override
	public List<Board> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public Board select(int num) throws Exception {
		return session.selectOne(namespace + "select", num);
	}
	
	@Override
	public int deleteAll() {
		return session.delete(namespace + "deleteAll");
	}
	
	@Override
	public int delete(Integer num, String writer) throws Exception {
		Map map = new HashMap();
		map.put("num", num);
		map.put("writer", writer);
		return session.delete(namespace + "delete", map);
	}
	
	@Override
	public int insert(Board board) throws Exception {
		return session.insert(namespace + "insert", board);
	}
	
	@Override
	public int increaseViewCnt(int num) throws Exception {
		return session.update(namespace + "increaseViewCnt", num);
	}
	
	@Override
	public int update(Board board) throws Exception {
		return session.update(namespace + "update", board);
	}
	
	@Override
	public List<Board> selectPage(Map map) throws Exception {
		return session.selectList(namespace + "selectPage", map);
	}
	
	@Override
	public List<Board> searchSelectPage(SearchCondition sc) throws Exception {
		return session.selectList(namespace + "searchSelectPage", sc);
	}
	
	@Override
	public int searchResultCnt(SearchCondition sc) throws Exception {
		return session.selectOne(namespace + "searchResultCnt", sc);
	}
	
	@Override
	public int updateLikeCnt(Integer num, Integer like_cnt) throws Exception {
		Map map = new HashMap();
		map.put("like_cnt", like_cnt);
		map.put("num", num);
		return session.update(namespace + "updateLikeCnt", map);
	}
	
	@Override
	public int updateCommentCnt(Integer num, Integer comment_cnt) throws Exception {
		Map map = new HashMap();
		map.put("num", num);
		map.put("comment_cnt", comment_cnt);
		return session.update(namespace + "updateCommentCnt", map);
	}

	@Override
	public int insertLike(Integer bno, String id) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("id", id);
		return session.insert(namespace + "insertLike", map); 
	}

	@Override
	public int deleteLike(Integer bno, String id) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("id", id);
		return session.delete(namespace + "deleteLike", map); 
	}

	@Override
	public int deleteLikeAll() throws Exception {
		return session.delete(namespace + "deleteLikeAll");
	}

	@Override
	public int selectLike(Integer bno, String id) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("id", id);
		return session.selectOne(namespace + "selectLike", map);
	}

	@Override
	public List<Map> selectBestBoard() throws Exception {
		return session.selectList(namespace + "selectBestBoard");
	}
	
}
