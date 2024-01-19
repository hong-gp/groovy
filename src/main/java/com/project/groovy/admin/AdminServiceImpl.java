package com.project.groovy.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	
	@Override
	public int regist(Admin admin) throws Exception {
		return adminDao.insert(admin);
	}
	
	@Override
	public List<Admin> getAdminAll() throws Exception {
		return adminDao.selectAll();
	}
	
	@Override
	public Admin getAdmin(String id) throws Exception {
		return adminDao.select(id);
	}
	
	@Override
	public int delete(String id) throws Exception {
		return adminDao.delete(id);
	}
}
