package com.search.cat_picture.global.openFeign.theCat;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.search.cat_picture.dto.CatPictureResponses;

import feign.Headers;

@FeignClient(name = "TheCatImageClient", url = "${THE_CAT_CLIENT_URL}/images")
@Headers("x-api-key: ${THE_CAT_CLIENT_KEY}")
public interface TheCatImageClient {

	@GetMapping(value = "/search")
	List<TheCatSimpleImageResponse> findRandomPictures(@RequestParam("limit") Integer limit);

	@GetMapping(value = "/{imagesId}")
	TheCatImageResponse findCatPictureById(@PathVariable String imagesId);

	@GetMapping(value = "/search")
	List<TheCatSimpleImageResponse> findPicturesByBreed(@RequestParam("breed_ids") String breedId);
}
