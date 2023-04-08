package com.cat.mission.infra.catApi;

import com.cat.mission.infra.catApi.dto.ApiResponseDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatApiService {

  @Autowired
  CatApiFeignClient catApiFeignClient;

  public List<ApiResponseDto> getRandomCatImages(int limit, boolean hasBreeds) {
    return catApiFeignClient.getImage(limit, hasBreeds);
  }

}
