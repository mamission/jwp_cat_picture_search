package com.example.catpicturesearch.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catpicturesearch.application.domain.CatImage;

public interface CatImageRepository extends CatImageCustomRepository, JpaRepository<CatImage, String> {
}
