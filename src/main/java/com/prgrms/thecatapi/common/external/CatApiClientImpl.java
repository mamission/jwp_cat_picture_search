package com.prgrms.thecatapi.common.external;

import java.util.List;

import org.springframework.stereotype.Component;

import com.prgrms.thecatapi.common.external.dto.DetailDto;
import com.prgrms.thecatapi.common.external.dto.PhotoDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CatApiClientImpl implements CatApiClient {

	private final CatApiFeignClient catApiFeignClient;

	@Override
	public DetailDto getById(String id) {
		return catApiFeignClient.getById(id);
	}

	@Override
	public List<PhotoDto> getRandomImage(int limit) {
		return catApiFeignClient.getRandomImage(limit);
	}

	@Override
	public List<PhotoDto> getByBreedName(String breed, int limit) {
		return catApiFeignClient.getByBreedName(breed, limit);
	}
}
