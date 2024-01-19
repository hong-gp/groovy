package com.project.groovy.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	SqlSession session;
	String namespace = "com.project.groovy.Admin.";
	
	@Override
	public int insert(Admin admin) throws Exception {
		return session.insert(namespace + "insert", admin);
	}
	
	@Override
	public List<Admin> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public Admin select(String id) throws Exception {
		return session.selectOne(namespace + "select", id);
	}
	
	@Override
	public int delete(String id) throws Exception {
		return session.selectOne(namespace  + "delete", id);
	}
}
