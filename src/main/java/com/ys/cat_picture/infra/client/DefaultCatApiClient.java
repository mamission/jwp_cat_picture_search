package com.ys.cat_picture.infra.client;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ys.cat_picture.infra.client.response.BreedResponse;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultCatApiClient implements CatApiClient {

	private final CatApiFeignClient catApiFeignClient;

	@Override
	public List<BreedResponse> getBreeds() {
		return catApiFeignClient.getBreeds();
	}

	@Override
	public List<CatOneResponse> getRandomImages(int limit, boolean hasBreeds) {
		return catApiFeignClient.getRandomImages(limit, hasBreeds ? 1 : 0);
	}

	@Override
	public CatOneResponse getImageById(String catImageId) {
		return catApiFeignClient.getImageById(catImageId);
	}

}
