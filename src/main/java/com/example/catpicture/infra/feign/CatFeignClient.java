package com.example.catpicture.infra.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.catpicture.infra.client.CatClient;
import com.example.catpicture.infra.dto.ClientBreedResponse;
import com.example.catpicture.infra.dto.ClientPictureResponse;

@FeignClient(name = "CatPictureClient", url = "${api.url}")
public interface CatFeignClient extends CatClient {

	@GetMapping("/images/search")
	List<ClientPictureResponse> searchPictures(
		@RequestHeader(value = "x-api-key") String apiKey,
		@RequestParam Integer limit,
		@RequestParam(value = "breed_id") String breedId
	);

	@GetMapping("/breeds")
	List<ClientBreedResponse> searchBreeds(
		@RequestParam Integer limit
	);
}
