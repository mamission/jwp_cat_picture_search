package com.prgrms.thecatapi.common.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.prgrms.thecatapi.cat.Breed;
import com.prgrms.thecatapi.cat.Photo;
import com.prgrms.thecatapi.cat.PhotoBreed;
import com.prgrms.thecatapi.cat.Weight;
import com.prgrms.thecatapi.common.external.dto.BreedDto;
import com.prgrms.thecatapi.common.dto.BreedResponse;
import com.prgrms.thecatapi.common.external.dto.DetailDto;
import com.prgrms.thecatapi.common.external.dto.PhotoDto;
import com.prgrms.thecatapi.common.dto.SimpleResponse;
import com.prgrms.thecatapi.common.external.dto.WeightDto;

@Component
public class DataConverter {

	public Photo toPhoto(DetailDto detailDto) {
		return new Photo(detailDto.id(), detailDto.url(), detailDto.width(), detailDto.height());
	}

	public Weight toWeight(WeightDto weightDto) {
		return new Weight(weightDto.imperial(), weightDto.metric());
	}

	public Breed toBreed(BreedDto breedDto) {
		return new Breed(breedDto.id(), toWeight(breedDto.weight()), breedDto.name(), breedDto.temperament(), breedDto.origin());
	}

	public List<Breed> toBreeds(DetailDto detailDto) {
		List<BreedDto> breeds = detailDto.breeds();
		return breeds.stream()
			.map(this::toBreed)
			.toList();
	}

	public List<PhotoBreed> toPhotoBreeds(Photo photo, List<Breed> breeds) {
		return breeds.stream()
			.map(breed -> new PhotoBreed(photo, breed))
			.toList();
	}

	public List<BreedResponse> toBreedResponse(List<Breed> breeds) {
		return breeds.stream()
			.map(breed -> new BreedResponse(breed.getId(), breed.getName(), breed.getTemperament(), breed.getOrigin()))
			.toList();
	}

	public List<SimpleResponse> toSimpleResponses(String keyword, List<PhotoDto> photoDtoList) {
		return photoDtoList.stream()
			.map(photoDto -> new SimpleResponse(photoDto.id(), photoDto.url(), keyword))
			.toList();
	}

}
