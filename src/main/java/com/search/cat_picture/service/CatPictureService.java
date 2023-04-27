package com.search.cat_picture.service;

import com.search.cat_picture.dto.CatPictureResponse;
import com.search.cat_picture.dto.CatPictureResponses;

public interface CatPictureService {

	CatPictureResponses getRandomPictures(int limit);

	CatPictureResponses getPicturesByBreedName(String breedName);

	CatPictureResponse getPictureById(String imageId);

}
