package com.ys.cat_picture.infra.client;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ys.cat_picture.infra.client.response.BreedResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultCatApiClient implements CatApiClient {

	private final CatApiFeignClient catApiFeignClient;

	@Override
	public List<BreedResponse> getBreeds() {
		return catApiFeignClient.getBreeds();
	}

}
