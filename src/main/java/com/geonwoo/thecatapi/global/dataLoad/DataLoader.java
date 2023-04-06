package com.geonwoo.thecatapi.global.dataLoad;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.geonwoo.thecatapi.domain.catImage.model.CatImage;
import com.geonwoo.thecatapi.domain.catImage.repository.CatImageRepository;
import com.geonwoo.thecatapi.feign.CatOpenFeign;
import com.geonwoo.thecatapi.feign.converter.CatImageFeignConverter;
import com.geonwoo.thecatapi.feign.response.ImageFeignResponse;

import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final CatOpenFeign catOpenFeign;
	private final CatImageRepository catImageRepository;

	@Override
	@Transactional
	public void run(String... args) {
		try {
			if (catImageRepository.count() < 50) {
				List<ImageFeignResponse> imageFeignResponses = catOpenFeign.getRandomCatImages(50, 1);
				for (ImageFeignResponse imageFeignResponse : imageFeignResponses) {
					CatImage catImage = CatImageFeignConverter.toCatImage(imageFeignResponse);
					catImageRepository.save(catImage);
				}
			}
		} catch (RetryableException e) {
			log.error("이미지를 가져오는데 실패했어요!");
		}
	}
}
