package com.prgrms.thecatapi.api;

import static org.springframework.http.MediaType.*;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prgrms.thecatapi.service.CatService;
import com.prgrms.thecatapi.common.dto.DetailResponse;
import com.prgrms.thecatapi.common.dto.SimpleResponses;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cats")
public class CatController {

	private final CatService catService;

	/**
	 * 1. GET /cats/random50
	 * 랜덤 50개의 고양이 사진 목록을 반환
	 */
	@GetMapping(value = "/random50", produces = APPLICATION_JSON_VALUE)
	public SimpleResponses getRandom(Pageable pageable) {
		return catService.findRandom50Photo(pageable);
	}


	/**
	 * 2. GET /cats/search?q=고양이의 품종(영어/한글)
	 * Keyword로 검색된 고양이 사진 목록을 반환
	 */
	@GetMapping(value = "/search", produces = APPLICATION_JSON_VALUE)
	public SimpleResponses getByKeyword(
		@RequestParam(value = "q") String keyword,
		Pageable pageable) {
		return catService.findByKeyword(keyword, pageable);
	}

	/**
	 * 3. GET /cats/{id}
	 * id로 검색된 고양이 사진 목록을 반환
	 */
	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	public DetailResponse getById(@PathVariable String id) {
		return catService.findById(id);
	}
}
