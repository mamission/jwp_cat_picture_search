package com.search.cat_picture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.search.cat_picture.entity.CatPicture;

public interface CatPictureRepository extends JpaRepository<CatPicture, String> {

	@Query(value = "SELECT * FROM cat_picture order by RAND() desc limit :limit", nativeQuery = true)
	List<CatPicture> findAllByRandom(@Param("limit") int limit);
}
