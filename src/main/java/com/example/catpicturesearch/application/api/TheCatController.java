package com.example.catpicturesearch.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.catpicturesearch.application.service.TheCatService;
import com.example.catpicturesearch.global.api.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class TheCatController {

	private final TheCatService theCatService;

	@GetMapping("/random50")
	public ResponseEntity<ApiResponse> getRandom50() {
		return ApiResponse.ok(theCatService.getRandom50());
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse> getCatImagesByBreedId(@RequestParam("q") String breedId) {
		return ApiResponse.ok(theCatService.getCatImagesByBreedId(breedId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getCatImagesByCatImageId(@PathVariable String id) {
		return ApiResponse.ok(theCatService.getCatImagesByCatImageId(id));
	}

}
