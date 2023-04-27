package com.search.cat_picture.global.openFeign.theCat;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Headers;

@FeignClient(name = "TheCatBreedClient", url = "${THE_CAT_CLIENT_URL}/breeds")
@Headers("x-api-key: ${THE_CAT_CLIENT_KEY}")
public interface TheCatBreedClient {

	@GetMapping()
	List<TheCatBreedResponse> findBreeds(@RequestParam("limit") Integer limit);

	@GetMapping(value = "/{breedId}")
	TheCatBreedResponse findBreedById(@PathVariable String breedId);
}
