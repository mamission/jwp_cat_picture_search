package com.cat.picture_search.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cat.picture_search.domain.dto.api.CatPictureDetail;
import com.cat.picture_search.domain.dto.api.CatPictureSimple;

@Service
public class CatPictureOpenFeignService {

	private static final int RANDOM_LIMIT = 50;
	private final CatPictureOpenFeign catPictureOpenFeign;
	@Value("${cat.openapi.key}")
	private String API_KEY;

	public CatPictureOpenFeignService(CatPictureOpenFeign catPictureOpenFeign) {
		this.catPictureOpenFeign = catPictureOpenFeign;
	}

	public List<CatPictureSimple> getRandom50() {
		List<CatPictureSimple> catPictureRes = catPictureOpenFeign.getRandom(
			API_KEY,
			RANDOM_LIMIT
		);

		return catPictureRes;
	}

	public CatPictureDetail getOne(String id) {
		CatPictureDetail catPictureDetailRes = catPictureOpenFeign.getOne(
			API_KEY,
			id
		);

		return catPictureDetailRes;
	}
}
