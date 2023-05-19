package com.cat.picture_search.presentation;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cat.picture_search.domain.dto.res.CatPictureDetailRes;
import com.cat.picture_search.domain.dto.res.CatPictureSimpleRes;
import com.cat.picture_search.domain.dto.res.Response;
import com.cat.picture_search.domain.service.CatPictureSearchService;

@RestController
@RequestMapping("/v1/cats")
public class CatPictureSearchController {

	private final CatPictureSearchService catPictureSearchService;

	public CatPictureSearchController(CatPictureSearchService catPictureSearchService) {
		this.catPictureSearchService = catPictureSearchService;
	}

	@GetMapping("/random50")
	public ResponseEntity<Response<List<CatPictureSimpleRes>>> getRandom50() {

		List<CatPictureSimpleRes> catPictureRes = catPictureSearchService.getRandomPic50();

		return ResponseEntity.ok(Response.of(catPictureRes));
	}

	@GetMapping("/search")
	public ResponseEntity<Response<List<CatPictureSimpleRes>>> getSearch(
		@RequestParam String q
	) {
		List<CatPictureSimpleRes> catPictureRes = catPictureSearchService.search(q);

		return ResponseEntity.ok(Response.of(catPictureRes));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response<CatPictureDetailRes>> getOne(@PathVariable("id") String id) {

		CatPictureDetailRes catPictureDetailRes = catPictureSearchService.getOne(id);

		return ResponseEntity.ok(Response.of(catPictureDetailRes));
	}
}
