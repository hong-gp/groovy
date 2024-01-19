package com.project.groovy.model;

import java.util.Date;
import java.util.Objects;

public class Board {

	private int num;
	private String category;
	private String title;
	private String content;
	private String writer;
	private String writer_nickname;
	private Date postdate;
	private int view_cnt;
	private int comment_cnt;
	private int like_cnt;
	private boolean is_update;
	private String img_src;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriter_nickname() {
		return writer_nickname;
	}

	public void setWriter_nickname(String writer_nickname) {
		this.writer_nickname = writer_nickname;
	}

	public Date getPostdate() {
		return postdate;
	}

	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public boolean isIs_update() {
		return is_update;
	}

	public void setIs_update(boolean is_update) {
		this.is_update = is_update;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public Board() {
		super();
	}

	public Board(String category, String title, String content, String writer, String writer_nickname, String img_src) {
		super();
		this.category = category;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writer_nickname = writer_nickname;
		this.img_src = img_src;
	}

	@Override
	public String toString() {
		return "Board [num=" + num + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", writer=" + writer + ", writer_nickname=" + writer_nickname + ", postdate=" + postdate
				+ ", view_cnt=" + view_cnt + ", comment_cnt=" + comment_cnt + ", like_cnt=" + like_cnt + ", is_update="
				+ is_update + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, content, num, title, writer, writer_nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return Objects.equals(category, other.category) && Objects.equals(content, other.content) && num == other.num
				&& Objects.equals(title, other.title) && Objects.equals(writer, other.writer)
				&& Objects.equals(writer_nickname, other.writer_nickname);
	}

}
