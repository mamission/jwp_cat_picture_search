package com.example.catpicturesearch.application.repository;

import java.util.List;

import com.example.catpicturesearch.application.domain.CatBreed;

public interface CatBreedCustomRepository {

	void bulkInsert(List<CatBreed> entities);

	boolean existsAny();

	List<CatBreed> findAllOrderByRandom(int limit);

	List<CatBreed> findAllByBreedId(String breedId);

	CatBreed findAllByCatImageId(String catImageId);

}
