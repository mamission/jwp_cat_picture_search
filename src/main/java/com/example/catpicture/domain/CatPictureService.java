package com.example.catpicture.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.catpicture.domain.dto.GetCatByIdResponse;
import com.example.catpicture.domain.dto.GetCatsByBreedResponse;
import com.example.catpicture.domain.dto.GetRandomCatsResponse;
import com.example.catpicture.domain.dto.RandomCatResponse;
import com.example.catpicture.domain.entity.CatPicture;

@Service
public class CatPictureService {

	private final CatPictureRepository catPictureRepository;

	public CatPictureService(CatPictureRepository catPictureRepository) {
		this.catPictureRepository = catPictureRepository;
	}

	public GetRandomCatsResponse getRandom(int numberOfPictures) {
		long count = catPictureRepository.count();

		List<RandomCatResponse> data = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < numberOfPictures; i++) {
			long sequence = random.nextLong(count) + 1;
			CatPicture catPicture = catPictureRepository.findById(sequence)
				.orElseThrow(() -> new CatPictureNotFoundException(
					"Cannot found cat picture. [picture seq]: %d".formatted(sequence)));
			data.add(new RandomCatResponse(catPicture));
		}

		return new GetRandomCatsResponse(data);
	}

	public GetCatsByBreedResponse getByBreed(String q) {
		return GetCatsByBreedResponse.from(catPictureRepository.findAllByName(q));
	}

	public GetCatByIdResponse getByPhotoId(String photoId) {
		return catPictureRepository.findByPhotoId(photoId)
			.map(GetCatByIdResponse::new)
			.orElseThrow(
				() -> new CatPictureNotFoundException("Cannot found cat picture. [picture id]: %s".formatted(photoId)));
	}
}
