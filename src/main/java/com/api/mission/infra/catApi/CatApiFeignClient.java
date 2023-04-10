package com.api.mission.infra.catApi;

import com.api.mission.infra.catApi.config.HeaderConfiguration;
import com.api.mission.infra.catApi.dto.CatApiResDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feign", url = "${cat-api.url}",
    configuration = HeaderConfiguration.class)
public interface CatApiFeignClient {

  @GetMapping("/search")
  List<CatApiResDto> getImage(@RequestParam("limit") int limit,
      @RequestParam("has_breeds") boolean hasBreeds);

}
