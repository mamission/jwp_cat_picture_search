package com.pfk.thecats.domain.cat.application;

import org.springframework.stereotype.Service;

import com.pfk.thecats.domain.cat.dao.CatBreedRepository;
import com.pfk.thecats.domain.cat.domain.CatBreed;
import com.pfk.thecats.domain.cat.exception.CatBreedNotFoundException;

@Service
public class CatBreedReader {

	private final CatBreedRepository catBreedRepository;

	public CatBreedReader(CatBreedRepository catBreedRepository) {
		this.catBreedRepository = catBreedRepository;
	}

	public CatBreed findOneByName(String name) {
		return catBreedRepository.findCatBreedByName(name)
			.orElseThrow(() -> new CatBreedNotFoundException("Cannot find CatBreed for name=%s".formatted(name)));
	}
}
