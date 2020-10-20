package com.starlight.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TweetPage {
	private int page;
	private int pageCount;
	
	private List<Tweet> content;
	
	public int getPage() {
		return page;
	}
	public int getPageCount() {
		return pageCount;
	}
	public List<Tweet> getContent(){
		return content;
	}
	public void setContent(List<Tweet> content) {
		this.content = content;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setPageCount(int count) {
		this.pageCount = count;
	}

}
