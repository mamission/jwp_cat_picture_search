package com.example.catpicture.infra.client;

import java.util.List;

import com.example.catpicture.infra.dto.ClientPictureResponse;

public interface CatPictureLoader {

	List<ClientPictureResponse> loadPictures();
}
