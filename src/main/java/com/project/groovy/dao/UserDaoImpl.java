package com.project.groovy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.groovy.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SqlSession session;
	String namespace = "com.project.groovy.User.";
	
	@Override
	public int insert(User user) throws Exception {
		return session.insert(namespace + "insert", user);
	}
	
	@Override
	public int update(User user) throws Exception {
		return session.update(namespace + "update", user);
	}
	
	@Override
	public int delete(String id) throws Exception {
		return session.delete(namespace + "delete", id);
	}
	
	@Override
	public User select(String id) throws Exception {
		return session.selectOne(namespace + "select", id);
	}
	
	@Override
	public List<User> selectAll() throws Exception {
		return session.selectList(namespace + "selectAll");
	}
	
	@Override
	public int deleteAll() {
		return session.delete(namespace + "deleteAll");
	}

	@Override
	public User findByNickname(String nickname) throws Exception {
		return session.selectOne(namespace + "findByNickname", nickname);
	}

	@Override
	public User findUserId(User user) throws Exception {
		return session.selectOne(namespace + "findUserId", user);
	}

	@Override
	public User findUserPw(User user) throws Exception {
		return session.selectOne(namespace + "findUserPw", user);
	}

	@Override
	public int updateAll(User user) throws Exception {
		return session.update(namespace + "updateAll", user);
	}
}
