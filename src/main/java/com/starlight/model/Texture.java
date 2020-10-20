package com.starlight.model;

import javax.persistence.*;

public class Texture {
	private long id;
	private long tweetid;
	private String type;
	
	public Texture() {
		super();
	}
	public Texture(long id, long tweetid, String type) {
		this.id=id;this.tweetid=tweetid;this.type=type;
	}
	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	
}
