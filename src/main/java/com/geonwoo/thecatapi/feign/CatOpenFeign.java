package com.geonwoo.thecatapi.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.geonwoo.thecatapi.feign.response.ImageFeignResponse;

@FeignClient(name = "CatOpenFeign", url = "${the-cat-api.url}")
public interface CatOpenFeign {

	@GetMapping(value = "/v1/images/search")
	List<ImageFeignResponse> getRandomCatImages(@RequestParam("limit") int limit,
		@RequestParam("has_breeds") int hasBreeds);

}
