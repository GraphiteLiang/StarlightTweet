package com.starlight.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class Tweet {
	private long id;
	private String content;
	private Author author;
	private List<Texture> textures;
	public Tweet() {
		super();
	}
	public Tweet(long id, String content,Author author) {
		this.id=id;this.content=content;this.author=author;
	}
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public Author getAuthor() {
		return author;
	}
	public List<Texture> getTextures(){
		return textures;
	}
	public void setTexture(List<Texture> textures) {
		this.textures = textures;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
}
