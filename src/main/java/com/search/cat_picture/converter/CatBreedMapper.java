package com.search.cat_picture.converter;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.search.cat_picture.entity.CatBreed;
import com.search.cat_picture.global.openFeign.theCat.TheCatBreedResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatBreedMapper {

	List<CatBreed> theCatResponsesToEntities(List<TheCatBreedResponse> breedResponses);
}
