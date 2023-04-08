package com.pfk.thecats.web.api.cat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfk.thecats.domain.cat.application.CatReader;
import com.pfk.thecats.domain.cat.dto.CatQueryResponse;

@RestController
@RequestMapping("/cats")
public class CatQueryApi {

	private final CatReader catReader;

	public CatQueryApi(CatReader catReader) {
		this.catReader = catReader;
	}

	@GetMapping("/{sourceId}")
	public ResponseEntity<CatQueryResponse> getOneBySourceId(
		@PathVariable String sourceId
	) {
		CatQueryResponse response = catReader.getOneBySourceId(sourceId);
		return ResponseEntity.ok(response);
	}
}
