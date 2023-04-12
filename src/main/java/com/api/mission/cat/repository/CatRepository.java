package com.api.mission.cat.repository;

import com.api.mission.cat.dto.CatDetailResDto;
import com.api.mission.cat.dto.CatSimpleResDto;
import com.api.mission.cat.entity.Cat;
import com.api.mission.cat.repository.querydsl.CatRepositoryQuerydsl;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long>,
    CatRepositoryQuerydsl {

  @Query(value = "select new com.api.mission.cat.dto.CatSimpleResDto( "
      + "c.displayId, c.image.url, c.breeds.name) "
      + "from Cat c "
      + "where c.breeds.name = :breedsName")
  List<CatSimpleResDto> getSearchListBy(@Param("breedsName") String breedsName);

  @Query(value = "select new com.api.mission.cat.dto.CatDetailResDto("
      + "c.breeds.name, c.displayId, c.image.url, c.image.width, c.image.height, c.breeds.temperament, c.breeds.origin) "
      + "from Cat c "
      + "where c.displayId = :id")
  Optional<CatDetailResDto> getByDisplayId(@Param("id") String id);
}
