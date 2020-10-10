package com.starlight.data;

import javax.persistence.Id;

public class STweet {
	private Long id;
	private String content;
	private Long authorid;
	private String authorname;
	public STweet() {
		super();
	}
	public STweet(Long id, String content, Long authorid, String authorname) {
		this.id=id;this.content=content;this.authorid=authorid;this.authorname=authorname;
	}
	public Long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public Long getAuthorid() {
		return authorid;
	}
	public String getAuthorname() {
		return authorname;
	}
}