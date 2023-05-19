package com.cat.picture_search.domain.storage;

import com.cat.picture_search.domain.storage.service.CatPictureSaveService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
public class CatPictureSaveRunner implements CommandLineRunner {

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
