package com.project.groovy.admin;

import java.util.List;

public interface AdminService {

	int regist(Admin admin) throws Exception;

	List<Admin> getAdminAll() throws Exception;

	Admin getAdmin(String id) throws Exception;

	int delete(String id) throws Exception;

}