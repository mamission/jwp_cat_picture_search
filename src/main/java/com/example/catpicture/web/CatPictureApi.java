package com.example.catpicture.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.catpicture.domain.CatPictureService;
import com.example.catpicture.domain.dto.GetCatsByBreedResponse;
import com.example.catpicture.domain.dto.GetRandomCatsResponse;

@RestController
@RequestMapping("/cats")
public class CatPictureApi {

	private final CatPictureService catPictureService;

	public CatPictureApi(CatPictureService catPictureService) {
		this.catPictureService = catPictureService;
	}

	@GetMapping("/random50")
	public GetRandomCatsResponse getRandomCats() {
		return catPictureService.getRandom(50);
	}

	@GetMapping("/cats/search")
	public GetCatsByBreedResponse getCatsByBreed(@RequestParam String q) {
		return catPictureService.getByBreed(q);
	}
}
