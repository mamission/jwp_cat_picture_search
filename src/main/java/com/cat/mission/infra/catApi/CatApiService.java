package com.cat.mission.infra.catApi;

import com.cat.mission.infra.catApi.dto.CatApiResDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatApiService {

  @Autowired
  CatApiFeignClient catApiFeignClient;

  public List<CatApiResDto> getRandomCatImages(int limit, boolean hasBreeds) {
    return catApiFeignClient.getImage(limit, hasBreeds);
  }

}
