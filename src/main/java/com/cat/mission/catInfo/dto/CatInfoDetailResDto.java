package com.cat.mission.catInfo.dto;

public record CatInfoDetailResDto(
    String name,
    String id,
    String url,
    int width,
    int height,
    String temperament,
    String origin
) {

}
