package com.prgrms.thecatapi.common.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import com.prgrms.thecatapi.common.external.dto.DetailDto;
import com.prgrms.thecatapi.common.external.dto.PhotoDto;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "thecatapi", url = "https://api.thecatapi.com/v1")
public interface CatApiFeignClient {

	@RequestLine("GET /images/{id}")
	DetailDto getById(@Param("id") String id);

	@RequestLine("GET /images/search?limit={limit}")
	List<PhotoDto> getRandomImage(@Param("limit") int limit);

	@RequestLine("GET /images/search?breed_ids={breed}&limit={limit}")
	List<PhotoDto> getByBreedName(@Param("breed") String breed, @Param("limit") int limit);

}

