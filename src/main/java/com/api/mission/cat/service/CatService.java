package com.api.mission.cat.service;

import com.api.mission.cat.dto.CatDetailResDto;
import com.api.mission.cat.dto.CatSimpleResDto;
import com.api.mission.cat.repository.CatRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CatService {

  private final CatRepository catRepository;

  private static final int LIMIT = 50;

  public CatService(CatRepository catInfoRepository) {
    this.catRepository = catInfoRepository;
  }

  public List<CatSimpleResDto> getRandomImages() {
    return catRepository.getRandomImages(LIMIT);
  }

  public List<CatSimpleResDto> getSearchListBy(String breedsName) {
    return catRepository.getSearchListBy(breedsName);
  }

  public CatDetailResDto getByDisplayId(String id) {
    return catRepository.getByDisplayId(id);
  }


}
