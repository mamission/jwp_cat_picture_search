package com.search.cat_picture.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.search.cat_picture.converter.CatBreedMapper;
import com.search.cat_picture.entity.CatBreed;
import com.search.cat_picture.global.openFeign.theCat.TheCatBreedClient;
import com.search.cat_picture.global.openFeign.theCat.TheCatBreedResponse;
import com.search.cat_picture.repository.CatBreedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TheCatBreedService implements CatBreedService {

	private final TheCatBreedClient theCatBreedClient;

	private final CatBreedRepository catBreedRepository;

	private final CatBreedMapper catBreedMapper;

	@Override
	public int saveAllBreeds(Integer maximum) {
		List<TheCatBreedResponse> breedResponses = theCatBreedClient.findBreeds(maximum);
		catBreedRepository.saveAll(catBreedMapper.theCatResponsesToEntities(breedResponses));
		return breedResponses.size();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<String> findIdByName(String name) {
		return catBreedRepository.findByName(name).flatMap(catBreed -> Optional.of(catBreed.getId()));
	}
}
