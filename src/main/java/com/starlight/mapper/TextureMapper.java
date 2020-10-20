package com.starlight.mapper;

import java.util.List;

import com.starlight.model.Texture;

public interface TextureMapper {

	List<Texture> getTextureByTweet(long tweetid);

}
