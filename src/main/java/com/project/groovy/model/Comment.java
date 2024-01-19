package com.project.groovy.model;

import java.util.Date;
import java.util.Objects;

public class Comment {

	private Integer cno;
	private Integer bno;
	private String comment;
	private String commenter;
	private String commenter_nickname;
	private Date reg_date;
	private Integer pcno;
	private Integer ref;
	private Integer re_step;
	private Integer re_level;
	private boolean isdel;
	private Integer recnt;
	
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
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
	public Integer getPcno() {
		return pcno;
	}
	public void setPcno(Integer pcno) {
		this.pcno = pcno;
	}
	public Integer getRef() {
		return ref;
	}
	public void setRef(Integer ref) {
		this.ref = ref;
	}
	public Integer getRe_step() {
		return re_step;
	}
	public void setRe_step(Integer re_step) {
		this.re_step = re_step;
	}
	public Integer getRe_level() {
		return re_level;
	}
	public void setRe_level(Integer re_level) {
		this.re_level = re_level;
	}
	public boolean isIsdel() {
		return isdel;
	}
	public void setIsdel(boolean isdel) {
		this.isdel = isdel;
	}
	public Integer getRecnt() {
		return recnt;
	}
	public void setRecnt(Integer recnt) {
		this.recnt = recnt;
	}
	@Override
	public String toString() {
		return "Comment [cno=" + cno + ", bno=" + bno + ", comment=" + comment + ", commenter=" + commenter
				+ ", commenter_nickname=" + commenter_nickname + ", reg_date=" + reg_date + ", pcno=" + pcno + ", ref="
				+ ref + ", re_step=" + re_step + ", re_level=" + re_level + ", isdel=" + isdel + ", recnt=" + recnt
				+ "]";
	}
	public Comment() {
		super();
	}
	
	public Comment(Integer bno, String comment, String commenter, String commenter_nickname, Integer pcno, Integer ref,
			Integer re_step, Integer re_level) {
		super();
		this.bno = bno;
		this.comment = comment;
		this.commenter = commenter;
		this.commenter_nickname = commenter_nickname;
		this.pcno = pcno;
		this.ref = ref;
		this.re_step = re_step;
		this.re_level = re_level;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bno, comment, commenter, commenter_nickname, pcno, re_level, re_step);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return bno == other.bno && Objects.equals(comment, other.comment) && Objects.equals(commenter, other.commenter)
				&& Objects.equals(commenter_nickname, other.commenter_nickname) && Objects.equals(pcno, other.pcno)
				&& re_level == other.re_level && re_step == other.re_step;
	}
	
	
}
