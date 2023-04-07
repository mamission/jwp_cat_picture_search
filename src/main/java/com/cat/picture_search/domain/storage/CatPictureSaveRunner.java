package com.cat.picture_search.domain.storage;

import org.springframework.stereotype.Component;

import com.cat.picture_search.domain.storage.service.CatPictureSaveService;

@Component
public class CatPictureSaveRunner implements org.springframework.boot.CommandLineRunner {

	private final CatPictureSaveService catPictureSaveService;

	public CatPictureSaveRunner(CatPictureSaveService catPictureSaveService) {
		this.catPictureSaveService = catPictureSaveService;
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 3; i++) {
			catPictureSaveService.savePictures();
		}
	}

}
