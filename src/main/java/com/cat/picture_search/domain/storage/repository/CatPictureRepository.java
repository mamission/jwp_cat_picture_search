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

	@Query(
		"select new com.cat.picture_search.domain.storage.repository.CatPictureSearchSimple(c.id, c.url, c.breeds.name) "
			+ "from CatPicture c "
			+ "where c.breeds.name like %:keyword% or "
			+ "c.breeds.origin like %:keyword% or "
			+ "c.breeds.temperament like %:keyword% "
	)
	List<CatPictureSearchSimple> findCatPicturesByKeyWord(@Param(value = "keyword") String keyword);
}
