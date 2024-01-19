package com.project.groovy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.groovy.dao.UserDao;
import com.project.groovy.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public int regist(User user) throws Exception {
		return userDao.insert(user);
	}
	
	@Override
	public int update(User user) throws Exception {
		return userDao.update(user);
	}
	
	@Override
	public int delete (String id) throws Exception {
		return userDao.delete(id);
	}
	
	@Override
	public User select(String id) throws Exception {
		return userDao.select(id);
	}
	
	@Override
	public List<User> selectAll() throws Exception {
		return userDao.selectAll();
	}
	
	@Override
	public int deleteAll() throws Exception {
		return userDao.deleteAll();
	}

	@Override
	public User findByNickname(String nickname) throws Exception {
		return userDao.findByNickname(nickname);
	}

	@Override
	public User findUserId(User user) throws Exception {
		return userDao.findUserId(user);
	}

	@Override
	public User findUserPw(User user) throws Exception {
		return userDao.findUserPw(user);
	}

	@Override
	public int updateAll(User user) throws Exception {
		return userDao.updateAll(user);
	}
}
