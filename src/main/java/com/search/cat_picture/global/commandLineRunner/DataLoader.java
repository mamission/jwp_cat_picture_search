package com.search.cat_picture.global.commandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.search.cat_picture.service.CatBreedService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

	private final CatBreedService catBreedService;

	@Override
	public void run(String... args) throws Exception {
		int count = catBreedService.saveAllBreeds(100);
		log.info("total breed count : {}", count);
	}
}
