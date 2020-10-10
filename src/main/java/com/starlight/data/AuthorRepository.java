package com.starlight.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	List<Author> findByName(String name);
	List<Author> findByNameLike(String name);
	Optional<Author> findById(Long id);
}
