package com.project.groovy.model;

import java.util.Date;

public class NewsComment {

	private Integer cno;
	private Integer nno;
	private String comment;
	private String commenter;
	private String commenter_nickname;
	private Date reg_date;

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public Integer getNno() {
		return nno;
	}

	public void setNno(Integer nno) {
		this.nno = nno;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommenter() {
		return commenter;
	}

	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}

	public String getCommenter_nickname() {
		return commenter_nickname;
	}

	public void setCommenter_nickname(String commenter_nickname) {
		this.commenter_nickname = commenter_nickname;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	public NewsComment() {
		super();
	}

	public NewsComment(Integer nno, String comment, String commenter, String commenter_nickname) {
		super();
		this.nno = nno;
		this.comment = comment;
		this.commenter = commenter;
		this.commenter_nickname = commenter_nickname;
	}

	@Override
	public String toString() {
		return "NewsComment [cno=" + cno + ", nno=" + nno + ", comment=" + comment + ", commenter=" + commenter
				+ ", commenter_nickname=" + commenter_nickname + ", reg_date=" + reg_date + "]";
	}

	
}
