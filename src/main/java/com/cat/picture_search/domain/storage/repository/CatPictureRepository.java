package com.cat.picture_search.domain.storage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cat.picture_search.domain.storage.data.CatPicture;

public interface CatPictureRepository extends JpaRepository<CatPicture, String> {

	boolean existsById(String id);

	@Query(value = "select * "
		+ "from `cat-picture` "
		+ "ORDER BY rand() "
		+ "limit :limit ",
		nativeQuery = true)
	List<CatPicture> getRandomCatPictures(@Param(value = "limit") int limit);
}
