package com.project.groovy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.groovy.model.NewsComment;

@Repository
public class NewsCommentDaoImpl implements NewsCommentDao {

	@Autowired
	SqlSession session;
	String namespace = "com.project.groovy.newsComment.";
	
	@Override
	public int deleteAll(Integer nno) throws Exception {
		return session.delete(namespace + "deleteAll", nno);
	}
	
	@Override
	public int count(Integer nno) throws Exception {
		return session.selectOne(namespace + "count", nno);
	}
	
	@Override
	public int delete(Integer cno, String commenter) throws Exception {
		Map map = new HashMap();
		map.put("cno", cno);
		map.put("commenter", commenter);
		return session.delete(namespace + "delete", map);
	}
	
	@Override
	public int insert(NewsComment newsComment) throws Exception {
		return session.insert(namespace + "insert", newsComment);
	}
	
	@Override
	public List<NewsComment> selectAll(Integer nno) throws Exception {
		return session.selectList(namespace + "selectAll", nno);
	}
	
	@Override
	public NewsComment select(Integer cno) throws Exception {
		return session.selectOne(namespace + "select", cno);
	}
	
	@Override
	public int update(NewsComment newsComment) throws Exception {
		return session.update(namespace + "update", newsComment);
	}
}
