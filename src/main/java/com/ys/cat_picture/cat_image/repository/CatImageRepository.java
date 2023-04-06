package com.ys.cat_picture.cat_image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ys.cat_picture.cat_image.domain.CatImage;

public interface CatImageRepository extends JpaRepository<CatImage, Long>, CatImageRepositorySupport{
}
