package com.starlight.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long>{
	List<Tweet> findByAuthor(Author author);
	List<Tweet> findByContentLike(String content);
	List<Tweet> findByAuthorLike(Author author);
	List<Tweet> findAllByOrderByIdDesc();
}
