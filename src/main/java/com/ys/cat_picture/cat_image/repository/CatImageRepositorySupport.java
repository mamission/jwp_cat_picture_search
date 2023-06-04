package com.ys.cat_picture.cat_image.repository;

import java.util.List;

import com.ys.cat_picture.cat_image.domain.CatImage;

public interface CatImageRepositorySupport {

	List<CatImage> saveAllIgnore(List<CatImage> catImages);
}
