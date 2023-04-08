package com.prgrms.thecatapi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prgrms.thecatapi.cat.Photo;
import com.prgrms.thecatapi.common.dto.SimpleResponse;

public interface PhotoRepository extends JpaRepository<Photo, String> {

	@Override
	boolean existsById(String id);

	@Query(value = """ 
		SELECT new com.prgrms.thecatapi.common.dto.SimpleResponse(
		p.id, p.url, b.name
		)
		FROM Photo p
		LEFT JOIN p.photoBreeds pb
		LEFT JOIN pb.breed b
		ORDER BY FUNCTION('RAND')
		""")
	List<SimpleResponse> findRandom50Photos(Pageable pageable);

	@Query(value = """
		SELECT new com.prgrms.thecatapi.common.dto.SimpleResponse(
		p.id, p.url, b.name
		)
		FROM Photo p
		JOIN p.photoBreeds pb
		JOIN pb.breed b
		WHERE b.name = :keyword
		""")
	List<SimpleResponse> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
