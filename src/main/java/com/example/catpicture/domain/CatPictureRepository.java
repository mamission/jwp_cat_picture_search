package com.example.catpicture.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.catpicture.domain.entity.CatPicture;

public interface CatPictureRepository extends JpaRepository<CatPicture, String> {
}
