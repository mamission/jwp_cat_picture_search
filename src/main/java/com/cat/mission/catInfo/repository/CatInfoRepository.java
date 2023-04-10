package com.cat.mission.catInfo.repository;

import com.cat.mission.catInfo.dto.CatInfoDetailResDto;
import com.cat.mission.catInfo.dto.CatInfoSimpleResDto;
import com.cat.mission.catInfo.entity.CatInfo;
import com.cat.mission.catInfo.repository.querydsl.CatInfoRepositoryQuerydsl;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CatInfoRepository extends JpaRepository<CatInfo, Long>,
    CatInfoRepositoryQuerydsl {

  @Query(value = "select new com.cat.mission.catInfo.dto.CatInfoSimpleResDto( "
      + "c.displayId, c.image.url, c.breeds.name) "
      + "from CatInfo c "
      + "where c.breeds.name = :breedsName")
  List<CatInfoSimpleResDto> getSearchListBy(@Param("breedsName") String breedsName);

  @Query(value = "select new com.cat.mission.catInfo.dto.CatInfoDetailResDto("
      + "c.breeds.name, c.displayId, c.image.url, c.image.width, c.image.height, c.breeds.temperament, c.breeds.origin) "
      + "from CatInfo c "
      + "where c.displayId = :id")
  CatInfoDetailResDto getByDisplayId(@Param("id") String id);
}
