package com.pfk.thecats.domain.cat.application;

import org.springframework.stereotype.Service;

import com.pfk.thecats.domain.cat.dao.CatRepository;

@Service
public class CatReader {

	private final CatRepository catRepository;

	public CatReader(CatRepository catRepository) {
		this.catRepository = catRepository;
	}

	public boolean anyCatExists() {
		return catRepository
			.findFirstByOrderByIdAsc()
			.isPresent();
	}
}
