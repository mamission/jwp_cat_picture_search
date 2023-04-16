package com.example.catpicture.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.catpicture.domain.entity.CatPicture;

public interface CatPictureRepository extends JpaRepository<CatPicture, Long> {

	@Query("""
			select cp
			from CatPicture cp
			where cp.breedDetails.name = :q
		""")
	List<CatPicture> findAllByName(String q);

	Optional<CatPicture> findByPhotoId(String photoId);
}
