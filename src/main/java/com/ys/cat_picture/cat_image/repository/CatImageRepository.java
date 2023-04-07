package com.ys.cat_picture.cat_image.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ys.cat_picture.cat_image.domain.CatImage;

public interface CatImageRepository extends JpaRepository<CatImage, Long>, CatImageRepositorySupport {

	Optional<CatImage> findByExternalId(@Param("externalId") String externalId);

	@Query("select ci from CatImage ci join fetch ci.breed cb where cb.name = :query ")
	List<CatImage> findAllByBreedNameWithBreed(@Param("query") String query, Pageable pageable);

}
