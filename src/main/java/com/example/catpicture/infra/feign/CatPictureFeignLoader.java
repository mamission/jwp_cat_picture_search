package com.example.catpicture.infra.feign;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.catpicture.infra.client.CatClient;
import com.example.catpicture.infra.client.CatPictureLoader;
import com.example.catpicture.infra.dto.ClientBreedResponse;
import com.example.catpicture.infra.dto.ClientPictureResponse;

@Component
public class CatPictureFeignLoader implements CatPictureLoader {

	public static final int PICTURE_DEFAULT_LIMIT = 10;
	public static final int BREED_DEFAULT_LIMIT = 30;
	private static final Logger log = LoggerFactory.getLogger(CatPictureFeignLoader.class);

	private final CatClient catClient;
	private final String apiKey;

	public CatPictureFeignLoader(
		@Value("${api.key}") String apiKey,
		CatClient catClient
	) {
		this.apiKey = apiKey;
		this.catClient = catClient;
	}

	@Override
	public List<ClientPictureResponse> loadPictures(ClientBreedResponse breedDetails) {

		return catClient.searchPictures(
			apiKey,
			PICTURE_DEFAULT_LIMIT,
			breedDetails.id()
		);
	}

	@Override
	public List<ClientBreedResponse> loadBreeds() {
		log.info("load breeds");

		return catClient.searchBreeds(BREED_DEFAULT_LIMIT);
	}
}
