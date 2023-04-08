package com.pfk.thecats.domain.cat.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfk.thecats.domain.cat.domain.CatBreed;

public interface CatBreedRepository extends JpaRepository<CatBreed, Long> {

	Optional<CatBreed> findCatBreedByName(String name);
}
