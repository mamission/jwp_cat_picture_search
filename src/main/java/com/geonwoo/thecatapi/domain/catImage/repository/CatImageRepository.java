package com.geonwoo.thecatapi.domain.catImage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geonwoo.thecatapi.domain.catImage.model.CatImage;

public interface CatImageRepository extends JpaRepository<CatImage, String> {

	@Query(value = """
		SELECT *
		FROM cat_image
		ORDER BY RAND()
		LIMIT 50
		""", nativeQuery = true)
	List<CatImage> getRandom50Images();

	List<CatImage> findAllByName(@Param("name") String name);
}
