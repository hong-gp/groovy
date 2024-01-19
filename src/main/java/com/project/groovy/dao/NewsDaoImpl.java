package com.project.groovy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.groovy.model.News;

@Repository
public class NewsDaoImpl implements NewsDao {

	@Autowired
	SqlSession session;
	String namespace = "com.project.groovy.News.";
	
	@Override
	public int count() throws Exception {
		return session.selectOne(namespace + "count");
	}
	
	@Override
	public List<News> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public News select(Integer num) throws Exception {
		return session.selectOne(namespace + "select", num);
	}
	
	@Override
	public int insert(News news) throws Exception {
		return session.insert(namespace + "insert", news);
	}
	
	@Override
	public int update(News news) throws Exception {
		return session.update(namespace + "update", news);
	}
	
	@Override
	public int delete(Integer num) throws Exception {
		return session.delete(namespace + "delete", num);
	}
	
	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace + "deleteAll");
	}
	
	@Override
	public int updateCommentCnt(Integer comment_cnt, Integer num) throws Exception {
		Map map = new HashMap();
		map.put("comment_cnt", comment_cnt);
		map.put("num", num);
		return session.update(namespace + "updateCommentCnt", map);
	}
}
