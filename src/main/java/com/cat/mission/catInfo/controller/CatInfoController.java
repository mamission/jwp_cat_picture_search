package com.cat.mission.catInfo.controller;

import com.cat.mission.catInfo.dto.CatInfoDetailResDto;
import com.cat.mission.catInfo.dto.CatInfoSimpleResDto;
import com.cat.mission.catInfo.service.CatInfoService;
import com.cat.mission.common.dto.ResDto;
import com.cat.mission.common.dto.ResDto.ListResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
public class CatInfoController {

  private final CatInfoService catImageService;

  public CatInfoController(CatInfoService catImageService) {
    this.catImageService = catImageService;
  }

  @GetMapping("/random50")
  public ResponseEntity<ListResDto<CatInfoSimpleResDto>> getRandomImages() {
    return ResponseEntity.ok(new ListResDto<>(catImageService.getRandomImages()));
  }

  @GetMapping("/search")
  public ResponseEntity<ListResDto<CatInfoSimpleResDto>> getSearchListBy(
      @RequestParam("q") String breedsName) {
    return ResponseEntity.ok(new ListResDto<>(catImageService.getSearchListBy(breedsName)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResDto<CatInfoDetailResDto>> getByDisplayId(
      @PathVariable("id") String id) {
    return ResponseEntity.ok(new ResDto<>(catImageService.getByDisplayId(id)));
  }

}
