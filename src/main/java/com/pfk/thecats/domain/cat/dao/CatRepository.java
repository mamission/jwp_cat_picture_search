package com.pfk.thecats.domain.cat.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfk.thecats.domain.cat.entity.Cat;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

	Optional<Cat> findFirstByOrderByIdAsc();
}
