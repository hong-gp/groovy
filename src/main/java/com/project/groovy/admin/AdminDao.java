package com.project.groovy.admin;

import java.util.List;

public interface AdminDao {

	int insert(Admin admin) throws Exception;

	List<Admin> selectAll() throws Exception;

	Admin select(String id) throws Exception;

	int delete(String id) throws Exception;

}