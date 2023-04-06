package com.example.catpicture.infra.client;

import java.util.List;

import com.example.catpicture.infra.dto.ClientBreedResponse;
import com.example.catpicture.infra.dto.ClientPictureResponse;

public interface CatClient {

	List<ClientPictureResponse> searchPictures(
		String apiKey,
		Integer limit,
		String breedId
	);

	List<ClientBreedResponse> searchBreeds(
		Integer limit
	);
}
