package com.starlight.mapper;

import java.util.List;

import com.starlight.model.Tweet;

public interface TweetMapper {
	public List<Tweet> getTweetByAuthor(long authorid);

	public List<Tweet> getAllByOrderByIdDesc();

	public List<Tweet> getByContent(String content);

	public void deleteAll();
	
	public int getCount();
}
