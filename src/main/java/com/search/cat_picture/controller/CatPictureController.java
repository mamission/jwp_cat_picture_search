package com.search.cat_picture.controller;

import static org.springframework.http.MediaType.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.cat_picture.dto.CatPictureResponse;
import com.search.cat_picture.dto.CatPictureResponses;
import com.search.cat_picture.service.CatPictureService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/cats")
@RestController
@RequiredArgsConstructor
public class CatPictureController {

	private final CatPictureService catPictureService;

	@GetMapping(value = "/random50", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CatPictureResponses> getRandom50CatPictures() {
		return ResponseEntity.ok(catPictureService.getRandomPictures(50));
	}

	@GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CatPictureResponses> getCatPicturesByBreed(@RequestParam("q") @NotBlank String breedName) {
		return ResponseEntity.ok(catPictureService.getPicturesByBreedName(breedName));
	}

	@GetMapping(value = "/{imageId}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<CatPictureResponse> getCatPictureById(@PathVariable String imageId) {
		return ResponseEntity.ok(catPictureService.getPictureById(imageId));
	}
}
