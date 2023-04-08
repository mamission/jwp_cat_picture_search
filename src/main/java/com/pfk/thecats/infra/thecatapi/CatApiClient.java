package com.pfk.thecats.infra.thecatapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pfk.thecats.infra.thecatapi.dto.CatImageSearchResponse;
import com.pfk.thecats.infra.thecatapi.openfeign.CatOpenFeignClient;

@Component
public class CatApiClient {

	private static final int MAX_IMAGE_NUMS = 100;
	private static final int HAS_BREEDS = 1;

	private final CatOpenFeignClient catOpenFeignClient;
	private final String apiKey;

	public CatApiClient(
		CatOpenFeignClient catOpenFeignClient,
		@Value("${the-cat-api.key}") String apiKey
	) {
		this.catOpenFeignClient = catOpenFeignClient;
		this.apiKey = apiKey;
	}

	public List<CatImageSearchResponse> searchCatImages() {
		return catOpenFeignClient.searchCatImages(apiKey, MAX_IMAGE_NUMS, HAS_BREEDS);
	}
}
