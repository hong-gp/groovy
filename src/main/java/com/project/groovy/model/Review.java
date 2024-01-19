package com.project.groovy.model;

import java.util.Date;
import java.util.Objects;

public class Review {

	private int num;
	private String album_id;
	private String user_id;
	private String user_nickname;
	private String comment;
	private Date reg_date;
	private int like_cnt;
	private double rate;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public Review() {
		super();
	}
	
	public Review(String album_id, String user_id, String user_nickname, String comment, double rate) {
		super();
		this.album_id = album_id;
		this.user_id = user_id;
		this.user_nickname = user_nickname;
		this.comment = comment;
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "Review [num=" + num + ", album_id=" + album_id + ", user_id=" + user_id + ", user_nickname="
				+ user_nickname + ", comment=" + comment + ", reg_date=" + reg_date + ", like_cnt=" + like_cnt
				+ ", rate=" + rate + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(album_id, comment, like_cnt, num, rate, user_id, user_nickname);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(album_id, other.album_id) && Objects.equals(comment, other.comment)
				&& like_cnt == other.like_cnt && num == other.num
				&& Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
				&& Objects.equals(user_id, other.user_id) && Objects.equals(user_nickname, other.user_nickname);
	}
	
}
