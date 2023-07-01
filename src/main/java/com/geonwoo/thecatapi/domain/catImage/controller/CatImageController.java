package com.geonwoo.thecatapi.domain.catImage.controller;

import static org.springframework.http.MediaType.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geonwoo.thecatapi.domain.catImage.dto.response.ImageResponse;
import com.geonwoo.thecatapi.domain.catImage.service.CatImageService;
import com.geonwoo.thecatapi.global.common.dto.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CatImageController {

	private final CatImageService catImageService;

	/**
	 * <pre>
	 *     랜덤한 50개의 고양이 사진을 가져옵니다.
	 * </pre>
	 * @return 고양이 이미지아이디, 품종이름, 사진 url
	 */
	@GetMapping(value = "/cats/random50", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<ApiResponse<List<ImageResponse>>> getRandom50Images() {

		List<ImageResponse> random50Images = catImageService.getRandom50Images();

		return ResponseEntity.ok().body(new ApiResponse<>(random50Images));
	}

	/**
	 * <pre>
	 *     파라미터인 고양이 품종과 일치하는 고양이 사진을 가져옵니다.
	 * </pre>
	 * @param breedName : 고양이 품종 이름
	 * @return 고양이 이미지아이디, 품종이름, 사진 url
	 */
	@GetMapping(value = "/cats/search/{q}", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<ApiResponse<List<ImageResponse>>> getImagesByBreed(@PathVariable("q") String breedName) {
		List<ImageResponse> imagesByBreed = catImageService.getImagesByBreed(breedName);

		return ResponseEntity.ok().body(new ApiResponse<>(imagesByBreed));
	}

	/**
	 * <pre>
	 *     이미지 id로 고양이 사진을 가져옵니다.
	 * </pre>
	 * @param imageId : 이미지 아이디
	 * @return 고양이 이미지아이디, 품종이름, 사진 url, 이미지 높이, 이미지 넓이, 고양이 성격, 고양이 출신지
	 */
	@GetMapping(value = "/cats", produces = APPLICATION_JSON_VALUE)
	ResponseEntity<ApiResponse<ImageResponse>> getImageById(@RequestParam("id") String imageId) {

		ImageResponse imageById = catImageService.getImageById(imageId);

		return ResponseEntity.ok().body(new ApiResponse<>(imageById));
	}

}
