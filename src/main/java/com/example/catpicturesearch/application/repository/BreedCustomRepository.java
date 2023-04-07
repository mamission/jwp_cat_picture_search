package com.example.catpicturesearch.application.repository;

import java.util.List;

import com.example.catpicturesearch.application.domain.Breed;

public interface BreedCustomRepository {

	void bulkInsert(List<Breed> entities);

}
