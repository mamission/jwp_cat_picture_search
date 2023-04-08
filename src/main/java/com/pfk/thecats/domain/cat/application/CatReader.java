package com.pfk.thecats.domain.cat.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pfk.thecats.domain.cat.dao.CatRepository;
import com.pfk.thecats.domain.cat.domain.Cat;
import com.pfk.thecats.domain.cat.domain.CatBreed;
import com.pfk.thecats.domain.cat.dto.CatImageQueryResponse;
import com.pfk.thecats.domain.cat.dto.CatImageQueryResponses;
import com.pfk.thecats.domain.cat.dto.CatQueryResponse;
import com.pfk.thecats.domain.cat.exception.CatNotFoundException;

@Service
public class CatReader {

	private final CatRepository catRepository;
	private final CatBreedReader catBreedReader;

	public CatReader(CatRepository catRepository, CatBreedReader catBreedReader) {
		this.catRepository = catRepository;
		this.catBreedReader = catBreedReader;
	}

	public CatImageQueryResponses getAllCatImagesByRandom(int quantity) {
		List<Cat> findCats = catRepository.findCatsOrderByRandom(quantity);
		return new CatImageQueryResponses(CatImageQueryResponse.from(findCats));
	}

	public CatImageQueryResponses getAllCatImagesByBreedName(String breedName) {
		CatBreed findCatBreed = catBreedReader.findOneByName(breedName);
		List<Cat> findCats = catRepository.findCatsByBreed(findCatBreed);
		return new CatImageQueryResponses(CatImageQueryResponse.from(findCats));
	}

	public CatQueryResponse getOneBySourceId(String sourceId) {
		Cat findCat = findOneBySourceId(sourceId);
		return CatQueryResponse.from(findCat);
	}

	private Cat findOneBySourceId(String sourceId) {
		return catRepository.findCatBySourceId(sourceId)
			.orElseThrow(() -> new CatNotFoundException("Cannot find Cat for sourceId=%s".formatted(sourceId)));
	}
}
