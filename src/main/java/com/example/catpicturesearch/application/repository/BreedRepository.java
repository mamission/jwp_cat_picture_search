package com.example.catpicturesearch.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catpicturesearch.application.domain.Breed;

public interface BreedRepository extends BreedCustomRepository, JpaRepository<Breed, String> {
}
