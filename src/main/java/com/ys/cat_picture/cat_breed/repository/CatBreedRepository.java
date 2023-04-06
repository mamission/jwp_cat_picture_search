package com.ys.cat_picture.cat_breed.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ys.cat_picture.cat_breed.domain.CatBreed;

public interface CatBreedRepository extends JpaRepository<CatBreed, String> {

	// @Query(nativeQuery = true, value = "select exists (select 1 from cat_breeds)")
	@Query(value = "select exists (select 1 from CatBreed c)")
	Boolean existsData();

	Optional<CatBreed> findByExternalId(@Param("externalId")String externalId);

}
