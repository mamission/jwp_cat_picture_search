package com.cat.mission.catBreeds.repository;

import com.cat.mission.catBreeds.entity.Breeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatBreedsRepository extends JpaRepository<Breeds, Long> {

}
