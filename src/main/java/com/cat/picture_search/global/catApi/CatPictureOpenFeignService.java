package com.cat.picture_search.global.catApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cat.picture_search.domain.dto.CatPictureDetail;

@Service
public class CatPictureOpenFeignService {

	private static final int RANDOM_LIMIT = 50;

	private final CatPictureOpenFeignController catPictureOpenFeign;

	@Value("${cat.openapi.key}")
	private String API_KEY;

	public CatPictureOpenFeignService(CatPictureOpenFeignController catPictureOpenFeign) {
		this.catPictureOpenFeign = catPictureOpenFeign;
	}

	public List<CatPictureDetail> getRandom50() {
		List<CatPictureDetail> catPictureRes = catPictureOpenFeign.getRandom(
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
