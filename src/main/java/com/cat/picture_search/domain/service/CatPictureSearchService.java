package com.cat.picture_search.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cat.picture_search.domain.dto.res.CatPictureDetailRes;
import com.cat.picture_search.domain.dto.res.CatPictureSimpleRes;
import com.cat.picture_search.domain.repository.CatPictureRepository;
import com.cat.picture_search.domain.storage.data.CatPicture;
import com.cat.picture_search.global.catApi.CatPictureOpenFeignService;

@Service
public class CatPictureSearchService {

	private static final int PICTURE_LIMIT = 50;

	private final CatPictureRepository catPictureRepository;
	private final CatPictureOpenFeignService catPictureOpenFeignService;

	public CatPictureSearchService(CatPictureRepository catPictureRepository,
		CatPictureOpenFeignService catPictureOpenFeignService) {
		this.catPictureRepository = catPictureRepository;
		this.catPictureOpenFeignService = catPictureOpenFeignService;
	}

	public List<CatPictureSimpleRes> getRandomPic50() {
		List<CatPicture> catPictureRes = catPictureRepository.getRandomCatPictures(PICTURE_LIMIT);

		return catPictureRes.stream()
			.map(CatPictureSimpleRes::of)
			.toList();
	}

	public List<CatPictureSimpleRes> search(String q) {
		return catPictureRepository.findCatPicturesByKeyWord(q)
			.stream()
			.map(CatPictureSimpleRes::of)
			.toList();
	}

	public CatPictureDetailRes getOne(String id) {
		CatPicture catPicture = catPictureRepository.findById(id)
			.orElseGet(() -> catPictureRepository.save(
				catPictureOpenFeignService.getOne(id).toEntity()
			));

		return CatPictureDetailRes.of(catPicture);
	}
}
