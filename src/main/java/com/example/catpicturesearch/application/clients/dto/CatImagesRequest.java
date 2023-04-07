package com.example.catpicturesearch.application.clients.dto;

import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class CatImagesRequest {

	@Max(value = 100, message = "요청 데이터 개수는 ${max}를 초과할 수 없습니다.")
	private final Integer limit;

	private final String breed_ids;

	private final Boolean has_breeds;

	private final Boolean include_breeds;

}
