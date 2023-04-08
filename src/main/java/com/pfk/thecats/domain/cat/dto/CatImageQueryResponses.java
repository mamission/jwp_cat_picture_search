package com.pfk.thecats.domain.cat.dto;

import java.util.List;

public record CatImageQueryResponses(
	List<CatImageQueryResponse> data
) {
}
