package com.pfk.thecats.web.api.cat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfk.thecats.domain.cat.application.CatReader;
import com.pfk.thecats.domain.cat.dto.CatImageQueryResponses;

@RestController
@RequestMapping("/cats")
public class CatImageQueryApi {

	private final CatReader catReader;

	public CatImageQueryApi(CatReader catReader) {
		this.catReader = catReader;
	}

	@GetMapping("/random50")
	public ResponseEntity<CatImageQueryResponses> getRandom50() {
		CatImageQueryResponses responses = catReader.getAllCatImagesByRandom(50);
		return ResponseEntity.ok(responses);
	}

	@GetMapping("/search")
	public ResponseEntity<CatImageQueryResponses> getAllByBreedName(
		@RequestParam(value = "q") String breedName
	) {
		CatImageQueryResponses responses = catReader.getAllCatImagesByBreedName(breedName);
		return ResponseEntity.ok(responses);
	}
}
