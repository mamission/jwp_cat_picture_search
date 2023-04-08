package com.prgrms.thecatapi.common.external.dto;

import java.util.List;

public record DetailDto(
	String id,
	String url,
	Integer width,
	Integer height,
	List<BreedDto> breeds
) {
}
