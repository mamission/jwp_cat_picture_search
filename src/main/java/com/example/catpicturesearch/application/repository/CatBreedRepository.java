package com.example.catpicturesearch.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catpicturesearch.application.domain.CatBreed;

public interface CatBreedRepository extends CatBreedCustomRepository, JpaRepository<CatBreed, Long> {
}