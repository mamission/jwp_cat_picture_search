package com.api.mission.cat.controller;

import com.api.mission.cat.dto.CatDetailResDto;
import com.api.mission.cat.dto.CatSimpleResDto;
import com.api.mission.cat.service.CatService;
import com.api.mission.common.dto.ResDto;
import com.api.mission.common.dto.ResDto.ListResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
public class CatController {

  private final CatService catService;

  public CatController(CatService catService) {
    this.catService = catService;
  }

  @GetMapping("/random50")
  public ResponseEntity<ListResDto<CatSimpleResDto>> getRandomImages() {
    return ResponseEntity.ok(new ListResDto<>(catService.getRandomImages()));
  }

  @GetMapping("/search")
  public ResponseEntity<ListResDto<CatSimpleResDto>> getSearchListBy(
      @RequestParam("q") String breedsName) {
    return ResponseEntity.ok(new ListResDto<>(catService.getSearchListBy(breedsName)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResDto<CatDetailResDto>> getByDisplayId(
      @PathVariable("id") String id) {
    return ResponseEntity.ok(new ResDto<>(catService.getByDisplayId(id)));
  }

}
