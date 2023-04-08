package com.cat.mission.infra.catApi.dto;

import com.cat.mission.catBreeds.entity.Breeds;
import com.cat.mission.catBreeds.entity.data.Weight;
import com.cat.mission.catImage.entity.CatImage;

public record ApiBreedsResDto(
    Weight weight,
    String id,
    String name,
    String cfa_url,
    String vetstreet_url,
    String vcahospitals_url,
    String temperament,
    String origin,
    String country_codes,
    String country_code,
    String description,
    String life_span,
    String indoor,
    String lap,
    String alt_names,
    String adaptability,
    String affection_level,
    String child_friendly,
    String dog_friendly,
    String energy_level,
    String grooming,
    String health_issues,
    String intelligence,
    String shedding_level,
    String social_needs,
    String stranger_friendly,
    String vocalisation,
    String experimental,
    String hairless,
    String natural,
    String rare,
    String rex,
    String suppressed_tail,
    String short_legs,
    String wikipedia_url,
    String hypoallergenic,
    String reference_image_id
) {

  public Breeds toBreeds(CatImage catImage) {
    return new Breeds(id, name, temperament, origin, weight, catImage);
  }
}
