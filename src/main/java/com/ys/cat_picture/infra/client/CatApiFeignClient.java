package com.ys.cat_picture.infra.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ys.cat_picture.infra.client.response.BreedResponse;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import jakarta.validation.constraints.Max;

@FeignClient(name = "CatApiFeignClient", url = "${cat-api.url}",
	configuration = FeignClientHeaderConfiguration.class)
public interface CatApiFeignClient {

	@GetMapping("/v1/breeds")
	List<BreedResponse> getBreeds();

	@GetMapping(value = "/v1/images/search", consumes = MediaType.APPLICATION_JSON_VALUE)
	List<CatOneResponse> getRandomImages(
		@RequestParam("limit") @Max(100) int limit,
		@RequestParam("has_breeds") int hasBreeds);

	@GetMapping(value = "/v1/images/{imageId}")
	CatOneResponse getImageById(@PathVariable String imageId);

}
