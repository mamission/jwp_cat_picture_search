package com.prgrms.thecatapi.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgrms.thecatapi.cat.Breed;
import com.prgrms.thecatapi.cat.Photo;
import com.prgrms.thecatapi.cat.PhotoBreed;
import com.prgrms.thecatapi.common.converter.DataConverter;
import com.prgrms.thecatapi.common.dto.BreedResponse;
import com.prgrms.thecatapi.common.external.dto.DetailDto;
import com.prgrms.thecatapi.common.dto.DetailResponse;
import com.prgrms.thecatapi.common.external.dto.PhotoDto;
import com.prgrms.thecatapi.common.dto.SimpleResponse;
import com.prgrms.thecatapi.common.dto.SimpleResponses;
import com.prgrms.thecatapi.common.exception.PhotoNotFoundException;
import com.prgrms.thecatapi.repository.PhotoBreedRepository;
import com.prgrms.thecatapi.repository.PhotoRepository;
import com.prgrms.thecatapi.common.external.CatApiClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatService {

	private final PhotoRepository photoRepository;
	private final PhotoBreedRepository photoBreedRepository;
	private final CatApiClient catApiClient;
	private final DataConverter converter;

	public DetailResponse findById(String photoId) {

		if (photoBreedRepository.getCountByPhoto(photoId) > 0) {
			return findDetailResponseFromRepository(photoId);
		}

		DetailDto detailDto = catApiClient.getById(photoId);

		Photo photo = converter.toPhoto(detailDto);
		List<Breed> breeds = converter.toBreeds(detailDto);
		List<PhotoBreed> photoBreeds = converter.toPhotoBreeds(photo, breeds);

		photoBreedRepository.saveAll(photoBreeds);

		List<BreedResponse> breedResponses = converter.toBreedResponse(breeds);

		return new DetailResponse(photo, breedResponses);
	}

	public SimpleResponses findRandom50Photo(Pageable pageable) {
		List<SimpleResponse> random50Photos = photoRepository.findRandom50Photos(pageable);

		return new SimpleResponses(random50Photos);
	}

	public SimpleResponses findByKeyword(String keyword, Pageable pageable) {
		List<SimpleResponse> result = photoRepository.findByKeyword(keyword, pageable);

		if (!result.isEmpty()) {
			return new SimpleResponses(result);
		}

		List<PhotoDto> photoDtoList = catApiClient.getByBreedName(keyword, pageable.getPageSize());

		List<SimpleResponse> simpleResponses = converter.toSimpleResponses(keyword, photoDtoList);

		return new SimpleResponses(simpleResponses);
	}

	private DetailResponse findDetailResponseFromRepository(String photoId) {
		List<BreedResponse> breedResponses = photoBreedRepository.findBreedByPhotoId(photoId);

		Photo photo = photoRepository.findById(photoId)
			.orElseThrow(() -> new PhotoNotFoundException("이미지를 찾지 못했습니다."));

		return new DetailResponse(photo, breedResponses);
	}
}
