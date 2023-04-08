package com.pfk.thecats.domain.cat.application;

import org.springframework.stereotype.Service;

import com.pfk.thecats.domain.cat.dao.CatRepository;

@Service
public class CatReader {

	private final CatRepository catRepository;

	public CatReader(CatRepository catRepository) {
		this.catRepository = catRepository;
	}

	public CatQueryResponse getOneBySourceId(String sourceId) {
		Cat findCat = findOneBySourceId(sourceId);
		return CatQueryResponse.from(findCat);
	}

	private Cat findOneBySourceId(String sourceId) {
		return catRepository.findCatBySourceId(sourceId)
			.orElseThrow(() -> new CatNotFoundException("Cannot find Cat for sourceId=%s".formatted(sourceId)));
	}
}
