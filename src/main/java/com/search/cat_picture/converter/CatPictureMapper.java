package com.search.cat_picture.converter;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;

import com.search.cat_picture.dto.CatPictureResponse;
import com.search.cat_picture.dto.CatPictureResponses;
import com.search.cat_picture.entity.CatBreed;
import com.search.cat_picture.entity.CatPicture;
import com.search.cat_picture.global.openFeign.theCat.TheCatBreedResponse;
import com.search.cat_picture.global.openFeign.theCat.TheCatImageResponse;
import com.search.cat_picture.global.openFeign.theCat.TheCatSimpleImageResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatPictureMapper {

	@Mapping(target = "breed", source = "breeds", qualifiedByName = "getBreedByTheCat")
	CatPicture theCatResponseToEntity(TheCatImageResponse theCatImageResponse);

	@Mapping(target = "name", source = "breed.name")
	@Mapping(target = "temperament", source = "breed.temperament")
	@Mapping(target = "origin", source = "breed.origin")
	CatPictureResponse entityToResponse(CatPicture catPicture);

	@Mapping(target = "name", source = "breeds", qualifiedByName = "getBreedNameByTheCat")
	List<CatPictureResponses.SimpleCatPictureResponse> theCatResponsesToSimpleResponse(
		List<TheCatSimpleImageResponse> responses);

	@Named("getBreedByTheCat")
	default CatBreed getBreedByTheCat(List<TheCatBreedResponse> breeds) {
		if (breeds == null || breeds.isEmpty()) {
			return null;
		}
		TheCatBreedResponse theCatBreed = breeds.get(0);
		return new CatBreed(theCatBreed.id(), theCatBreed.name(), theCatBreed.temperament(), theCatBreed.origin());
	}

	@Named("getBreedNameByTheCat")
	default String getBreedNameByTheCat(List<CatPictureResponses.SimpleCatPictureResponse> breeds) {
		if (breeds == null || breeds.isEmpty()) {
			return null;
		}
		return breeds.get(0).name();
	}
}
