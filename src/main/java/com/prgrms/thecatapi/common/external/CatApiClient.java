package com.prgrms.thecatapi.common.external;

import java.util.List;

import com.prgrms.thecatapi.common.external.dto.DetailDto;
import com.prgrms.thecatapi.common.external.dto.PhotoDto;

public interface CatApiClient {

	DetailDto getById(String id);

	List<PhotoDto> getRandomImage(int limit);

	List<PhotoDto> getByBreedName(String breed, int limit);
}
