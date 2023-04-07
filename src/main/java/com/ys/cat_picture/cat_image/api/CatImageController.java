package com.ys.cat_picture.cat_image.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ys.cat_picture.cat_image.dto.CatImageDetailResponse;
import com.ys.cat_picture.cat_image.dto.CatImageResponse;
import com.ys.cat_picture.cat_image.service.CatImageService;
import com.ys.cat_picture.common.dto.ApiResponse;
import com.ys.cat_picture.common.dto.ApiResponses;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatImageController {

	private final CatImageService catImageService;

	@GetMapping(value = "/random50")
	public ResponseEntity<ApiResponses<CatImageResponse>> getRandom50Images() {
		return ResponseEntity.ok(new ApiResponses<>(catImageService.getRandomImages()));
	}

	@GetMapping("/{catId}")
	public ResponseEntity<ApiResponse<CatImageDetailResponse>> getById(
		@PathVariable String catId) {
		return ResponseEntity.ok(new ApiResponse<>(catImageService.getById(catId)));
	}

}
