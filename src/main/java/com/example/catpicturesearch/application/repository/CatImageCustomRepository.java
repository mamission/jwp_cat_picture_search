package com.example.catpicturesearch.application.repository;

import java.util.List;

import com.example.catpicturesearch.application.domain.CatImage;

public interface CatImageCustomRepository {

	void bulkInsert(List<CatImage> entities);

}
