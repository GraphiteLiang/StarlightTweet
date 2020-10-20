package com.starlight.mapper;

import java.util.List;
import com.starlight.model.*;
public interface AuthorMapper {
	List<Author> getAllAuthor();

	Author getById(long authorid);

	void save(Author a);
}
