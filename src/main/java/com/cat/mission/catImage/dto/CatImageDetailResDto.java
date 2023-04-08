package com.cat.mission.catImage.dto;

public record CatImageDetailResDto(
    String name,
    String id,
    String url,
    int width,
    int height,
    String temperament,
    String origin
) {

}
