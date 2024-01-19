package com.project.groovy.admin;

import java.util.Date;
import java.util.Objects;

public class Admin {

	private String id;
	private String password;
	private Date reg_date;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getReg_date() {
		return reg_date;
	}
	
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Admin() {
		super();
	}

	public Admin(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", password=" + password + ", reg_date=" + reg_date + "]";
	}
	
}
