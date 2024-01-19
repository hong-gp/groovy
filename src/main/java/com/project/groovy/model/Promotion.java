package com.project.groovy.model;

public class Promotion {

	private Integer num;
	private String title;
	private String img_src;
	private String link;

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

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Promotion() {
		super();
	}

	public Promotion(String title, String img_src, String link) {
		super();
		this.title = title;
		this.img_src = img_src;
		this.link = link;
	}

	@Override
	public String toString() {
		return "Promotion [num=" + num + ", title=" + title + ", img_src=" + img_src + ", link=" + link + "]";
	}

}
