package com.pfk.thecats.domain.cat.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pfk.thecats.domain.cat.domain.Cat;
import com.pfk.thecats.domain.cat.domain.CatBreed;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

	@Query(value = """
		select *
		from cat
		order by rand()
		limit :limit""", nativeQuery = true)
	List<Cat> findCatsOrderByRandom(@Param("limit") int limit);

	List<Cat> findCatsByBreed(CatBreed breed);

	Page<Cat> findAll(Pageable pageable);

	Optional<Cat> findCatBySourceId(String sourceId);

	default boolean anyCatExists() {
		return findAll(PageRequest.of(0, 1)).getNumberOfElements() == 1;
	}
}
