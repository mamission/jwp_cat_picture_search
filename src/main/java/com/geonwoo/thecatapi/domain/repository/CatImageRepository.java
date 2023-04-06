package com.geonwoo.thecatapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geonwoo.thecatapi.domain.catImage.model.CatImage;

public interface CatImageRepository extends JpaRepository<CatImage, String> {
}
