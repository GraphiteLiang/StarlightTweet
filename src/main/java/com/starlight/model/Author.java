package com.starlight.model;

import java.util.List;

import javax.persistence.*;

public class Author {
	private Long id;
	private String name;
	private String project;
	private String name_chn;
	private List<Tweet> tweets;
	
	public Author() {
		super();
	}
	public Author(Long id, String name,String project, String name_chn) {
		this.id=id;this.name=name;this.project=project;this.name_chn=name_chn;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getProject() {
		return project;
	}
	public String getName_chn() {
		return name_chn;
	}
}
