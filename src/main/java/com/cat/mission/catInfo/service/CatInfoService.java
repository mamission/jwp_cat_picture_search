package com.cat.mission.catInfo.service;

import com.cat.mission.catInfo.dto.CatInfoDetailResDto;
import com.cat.mission.catInfo.dto.CatInfoSimpleResDto;
import com.cat.mission.catInfo.repository.CatInfoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CatInfoService {

  private final CatInfoRepository catInfoRepository;

  private static final int LIMIT = 50;

  public CatInfoService(CatInfoRepository catInfoRepository) {
    this.catInfoRepository = catInfoRepository;
  }

  public List<CatInfoSimpleResDto> getRandomImages() {
    return catInfoRepository.getRandomImages(LIMIT);
  }

  public List<CatInfoSimpleResDto> getSearchListBy(String breedsName) {
    return catInfoRepository.getSearchListBy(breedsName);
  }

  public CatInfoDetailResDto getByDisplayId(String id) {
    return catInfoRepository.getByDisplayId(id);
  }


}
