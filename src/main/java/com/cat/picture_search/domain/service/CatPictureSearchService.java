package com.cat.picture_search.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cat.picture_search.api.CatPictureOpenFeignService;
import com.cat.picture_search.domain.dto.CatPictureDetailRes;
import com.cat.picture_search.domain.dto.CatPictureSimpleRes;
import com.cat.picture_search.domain.dto.api.CatPictureDetail;
import com.cat.picture_search.domain.dto.api.CatPictureSimple;

@Service
public class CatPictureSearchService {

	private final CatPictureOpenFeignService catPictureOpenFeignService;

	public CatPictureSearchService(CatPictureOpenFeignService catPictureOpenFeignService) {
		this.catPictureOpenFeignService = catPictureOpenFeignService;
	}

	public List<CatPictureSimpleRes> getRandomPic50() {
		List<CatPictureSimple> catPictureRes = catPictureOpenFeignService.getRandom50();

		return catPictureRes.stream()
			.map(CatPictureSimpleRes::of)
			.toList();
	}

	public List<CatPictureSimpleRes> search() {
		return null;
	}

	public CatPictureDetailRes getOne(String id) {
		CatPictureDetail catPictureDetail = catPictureOpenFeignService.getOne(id);

		return CatPictureDetailRes.of(catPictureDetail);
	}
}
