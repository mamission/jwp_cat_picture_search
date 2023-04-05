package com.ys.cat_picture.infra.client;

import java.util.List;

import com.ys.cat_picture.infra.client.response.BreedResponse;

public interface CatApiClient {

	List<BreedResponse> getBreeds();

}
