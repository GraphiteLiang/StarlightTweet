package com.starlight.data;

import java.util.List;

import javax.persistence.*;

@Entity// 说明这是一个和数据库表映射的实体类
@Table(name = "author")
public class Author {
	@Id
	private Long id;
	private String name;
	private String project;
	private String name_chn;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
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
