package com.example.catpicturesearch.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.catpicturesearch.application.domain.CatBreed;
import com.example.catpicturesearch.application.dto.CatImageDetailResponse;
import com.example.catpicturesearch.application.dto.CatImageResponse;
import com.example.catpicturesearch.application.mapper.TheCatMapper;
import com.example.catpicturesearch.application.repository.CatBreedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TheCatService {

	private final CatBreedRepository catBreedRepository;

	public List<CatImageResponse> getRandom50() {
		List<CatBreed> catBreeds = catBreedRepository.findAllOrderByRandom(50);
		return TheCatMapper.toCatImageResponses(catBreeds);
	}

	public List<CatImageResponse> getCatImagesByBreedId(String breedId) {
		List<CatBreed> catBreeds = catBreedRepository.findAllByBreedId(breedId);
		return TheCatMapper.toCatImageResponses(catBreeds);
	}

	public CatImageDetailResponse getCatImagesByCatImageId(String catImageId) {
		CatBreed catBreed = catBreedRepository.findAllByCatImageId(catImageId);
		return TheCatMapper.toCatImageDetailResponse(catBreed);
	}

}
