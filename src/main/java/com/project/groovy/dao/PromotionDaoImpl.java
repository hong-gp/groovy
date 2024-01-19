package com.project.groovy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.groovy.model.Promotion;

@Repository
public class PromotionDaoImpl implements PromotionDao {

	@Autowired
	SqlSession session;
	String namespace = "com.project.groovy.Promotion.";
	
	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace + "deleteAll");
	}
	
	@Override
	public int delete(Integer num) throws Exception {
		return session.delete(namespace + "delete", num);
	}
	
	@Override
	public List<Promotion> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public Promotion select(Integer num) throws Exception {
		return session.selectOne(namespace + "select", num);
	}
	
	@Override
	public Promotion selectRandom() throws Exception {
		return session.selectOne(namespace + "selectRandom");
	}
	
	@Override
	public int update(Promotion promotion) throws Exception {
		return session.update(namespace + "update", promotion);
	}

	@Override
	public int insert(Promotion promotion) throws Exception {
		return session.insert(namespace + "insert", promotion);
	}
	
	
}
