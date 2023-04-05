package com.pfk.thecats.infra.thecatapi;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.pfk.thecats.infra.thecatapi.dto.CatImageSearchResponse;

@FeignClient(name = "CatOpenFeignClient", url = "${the-cat-api.url}")
public interface CatOpenFeignClient {

	@GetMapping("/v1/images/search")
	List<CatImageSearchResponse> searchCatImages(
		@RequestHeader(value = "x-api-key") String apiKey,
		@RequestParam(value = "limit", required = false, defaultValue = "100") int limit,
		@RequestParam(value = "has_breeds", required = false, defaultValue = "1") int hasBreeds
	);
}
