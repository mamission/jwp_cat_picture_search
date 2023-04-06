package com.example.catpicture.infra.client;

import java.util.List;

import com.example.catpicture.infra.dto.ClientBreedResponse;
import com.example.catpicture.infra.dto.ClientPictureResponse;

public interface CatPictureLoader {

	List<ClientPictureResponse> loadPictures(ClientBreedResponse breedDetails);

	List<ClientBreedResponse> loadBreeds();
}
