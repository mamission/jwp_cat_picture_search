package com.ys.cat_picture.cat_image.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ys.cat_picture.cat_image.dto.CatImageResponse;
import com.ys.cat_picture.cat_image.service.CatImageService;
import com.ys.cat_picture.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatImageController {

	private final CatImageService catImageService;

	@GetMapping(value = "/random50")
	public ResponseEntity<ApiResponse<List<CatImageResponse>>> getRandom50Images() {

		List<CatImageResponse> imageResponses = catImageService.getRandomImages();

		return ResponseEntity.ok(new ApiResponse<>(imageResponses));
	}

}
