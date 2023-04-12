package com.api.mission.infra.catApi.dto;

import com.api.mission.cat.entity.data.Breeds;
import com.api.mission.cat.entity.data.Weight;

public record CatApiBreedsResDto(
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
    int indoor,
    int lap,
    String alt_names,
    int adaptability,
    int affection_level,
    int child_friendly,
    int dog_friendly,
    int energy_level,
    int grooming,
    int health_issues,
    int intelligence,
    int shedding_level,
    int social_needs,
    int stranger_friendly,
    int vocalisation,
    int experimental,
    int hairless,
    int natural,
    int rare,
    int rex,
    int suppressed_tail,
    int short_legs,
    String wikipedia_url,
    int hypoallergenic,
    String reference_image_id
) {

  public Breeds toBreeds() {
    return new Breeds(id, name, temperament, origin, weight);
  }
}
