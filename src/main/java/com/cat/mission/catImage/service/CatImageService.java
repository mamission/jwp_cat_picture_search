package com.cat.mission.catImage.service;

import com.cat.mission.catImage.dto.CatImageDetailResDto;
import com.cat.mission.catImage.dto.CatImageResDto;
import com.cat.mission.catImage.repository.CatImageRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CatImageService {

  private final CatImageRepository catImageRepository;

  private static final int LIMIT = 50;

  public CatImageService(CatImageRepository catImageRepository) {
    this.catImageRepository = catImageRepository;
  }

  public List<CatImageResDto> getRandomImages() {
    return catImageRepository.getRandomImages(LIMIT);
  }

  public List<CatImageResDto> getSearchListBy(String breedsName) {
    return catImageRepository.getSearchListBy(breedsName);
  }

  public CatImageDetailResDto getByDisplayId(String id) {
    return catImageRepository.getByDisplayId(id);
  }


}
