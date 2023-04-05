package com.ys.cat_picture.infra.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ys.cat_picture.infra.client.response.BreedResponse;

@FeignClient(name = "CatApiFeignClient", url = "https://api.thecatapi.com")
public interface CatApiFeignClient {

	@GetMapping("/v1/breeds")
	List<BreedResponse> getBreeds();

}
