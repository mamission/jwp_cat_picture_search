package com.cat.mission.infra.catApi;

import com.cat.mission.infra.catApi.config.HeaderConfiguration;
import com.cat.mission.infra.catApi.dto.ApiResponseDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feign", url = "${cat-api.url}",
    configuration = HeaderConfiguration.class)
public interface CatApiFeignClient {

  @GetMapping("/search")
  List<ApiResponseDto> getImage(@RequestParam("limit") int limit,
      @RequestParam("has_breeds") boolean hasBreeds);

}
