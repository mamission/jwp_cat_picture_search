package com.ys.cat_picture.infra.client;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.infra.client.response.BreedResponse;
import com.ys.cat_picture.infra.client.response.CatOneResponse;

import jakarta.validation.constraints.Max;

public interface CatApiClient {

	List<BreedResponse> getBreeds();

	List<CatOneResponse> getRandomImages(int limit, boolean hasBreeds);

	CatOneResponse getImageById(String catId);

	List<CatOneResponse> findAllByBreedId(String breedId, int limit);

}
