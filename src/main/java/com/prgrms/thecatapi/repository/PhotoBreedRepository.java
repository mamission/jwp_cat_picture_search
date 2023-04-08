package com.prgrms.thecatapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prgrms.thecatapi.cat.PhotoBreed;
import com.prgrms.thecatapi.common.dto.BreedResponse;

public interface PhotoBreedRepository extends JpaRepository<PhotoBreed, Long> {
	@Query("""
		SELECT new com.prgrms.thecatapi.common.dto.BreedResponse(
		b.id, b.name, b.temperament, b.origin)
		FROM PhotoBreed pb
		LEFT JOIN pb.breed b
		LEFT JOIN pb.photo p
		WHERE p.id = :id
		""")
	List<BreedResponse> findBreedByPhotoId(@Param("id") String id);

	@Query(value = """
		SELECT COUNT(*) 
		FROM photo_breed pb
		WHERE photo_id = :photoId
	""", nativeQuery = true)
	Long getCountByPhoto(@Param("photoId") String photoId);

}
