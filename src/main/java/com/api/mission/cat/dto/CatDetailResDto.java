package com.api.mission.cat.dto;

public record CatDetailResDto(
    String name,
    String id,
    String url,
    int width,
    int height,
    String temperament,
    String origin
) {

}
