package com.example.catpicture.domain;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.catpicture.domain.dto.GetRandomCatsResponse;
import com.example.catpicture.domain.entity.CatPicture;

@Service
public class CatPictureService {

	private final CatPictureRepository catPictureRepository;

	public CatPictureService(CatPictureRepository catPictureRepository) {
		this.catPictureRepository = catPictureRepository;
	}

	public GetRandomCatsResponse getRandom(int numberOfPictures) {
		long count = catPictureRepository.count();

		GetRandomCatsResponse response = new GetRandomCatsResponse(new ArrayList<>());
		Random random = new Random();
		for (int i = 0; i < numberOfPictures; i++) {
			long sequence = random.nextLong(count) + 1;
			CatPicture catPicture = catPictureRepository.findById(sequence)
				.orElseThrow(() -> new CatPictureNotFoundException(sequence));
			response.addCatResponse(catPicture);
		}

		return response;
	}
}
