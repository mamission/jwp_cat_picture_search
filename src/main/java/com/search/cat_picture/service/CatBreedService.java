package com.search.cat_picture.service;

import java.util.Optional;

public interface CatBreedService {

	int saveAllBreeds(Integer maximum);

	Optional<String> findIdByName(String name);
}
