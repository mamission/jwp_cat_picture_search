package com.example.catpicturesearch.application.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.catpicturesearch.application.clients.dto.BreedResponse;
import com.example.catpicturesearch.application.clients.dto.CatImageResponse;
import com.example.catpicturesearch.application.clients.dto.CatImagesRequest;
import com.example.catpicturesearch.global.config.FeignConfig;

@FeignClient(name = "TheCatClient", url = "${the-cat.base_url}", configuration = FeignConfig.class)
public interface TheCatClient {

	@GetMapping("/breeds")
	List<BreedResponse> getBreeds();

	@GetMapping("/images/search")
	List<CatImageResponse> getCatImages(@SpringQueryMap CatImagesRequest request);

}
