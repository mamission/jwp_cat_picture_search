package com.cat.mission.catImage.repository;

import com.cat.mission.catImage.entity.CatImage;
import com.cat.mission.catImage.repository.querydsl.CatImageRepositoryQuerydsl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatImageRepository extends JpaRepository<CatImage, Long>,
    CatImageRepositoryQuerydsl {

}
