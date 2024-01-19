package com.project.groovy.model;

import java.util.Date;

public class News {

	private Integer num;
	private String title;
	private String content;
	private String img_src;
	private Date reg_date;
	private String writer;
	private Integer comment_cnt;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Integer getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(Integer comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public News() {
		super();
	}

	public News(String title, String content, String img_src, String writer) {
		super();
		this.title = title;
		this.content = content;
		this.img_src = img_src;
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "News [num=" + num + ", title=" + title + ", content=" + content + ", img_src=" + img_src + ", reg_date="
				+ reg_date + ", writer=" + writer + ", comment_cnt=" + comment_cnt + "]";
	}

	
}
