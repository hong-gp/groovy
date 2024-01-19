package com.project.groovy.model;

import java.util.Objects;

public class User {

	private String id;
	private String password;
	private String name;
	private String nickname;
	private String tel;
	private String email;
	private String birth;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public User() {
		super();
	}
	
	public User(String id, String password, String name, String nickname, String tel, String email, String birth) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.tel = tel;
		this.email = email;
		this.birth = birth;
	}
	
	public User(String name, String tel, String email) {
		super();
		this.name = name;
		this.tel = tel;
		this.email = email;
	}

	public User(String id, String name, String tel, String email) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(birth, email, id, name, nickname, password, tel);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(birth, other.birth) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(nickname, other.nickname)
				&& Objects.equals(password, other.password) && Objects.equals(tel, other.tel);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", nickname=" + nickname + ", tel="
				+ tel + ", email=" + email + ", birth=" + birth + "]";
	}
	
}
