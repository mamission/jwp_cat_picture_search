package com.cat.mission.catImage.controller;

import com.cat.mission.catImage.dto.CatImageDetailResDto;
import com.cat.mission.catImage.dto.CatImageResDto;
import com.cat.mission.catImage.service.CatImageService;
import com.cat.mission.common.dto.ApiResDto;
import com.cat.mission.common.dto.ApiResDto.ApiListResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
public class CatImageController {

  private final CatImageService catImageService;

  public CatImageController(CatImageService catImageService) {
    this.catImageService = catImageService;
  }

  @GetMapping("/random50")
  public ResponseEntity<ApiListResDto<CatImageResDto>> getRandomImages() {
    return ResponseEntity.ok(new ApiListResDto<>(catImageService.getRandomImages()));
  }

  @GetMapping("/search")
  public ResponseEntity<ApiListResDto<CatImageResDto>> getSearchListBy(
      @RequestParam("q") String breedsName) {
    return ResponseEntity.ok(new ApiListResDto<>(catImageService.getSearchListBy(breedsName)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResDto<CatImageDetailResDto>> getByDisplayId(@PathVariable("id") String id) {
    return ResponseEntity.ok(new ApiResDto<>(catImageService.getByDisplayId(id)));
  }

}
