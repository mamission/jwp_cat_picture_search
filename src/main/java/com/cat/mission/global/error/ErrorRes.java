package com.cat.mission.global.error;

import org.springframework.http.HttpStatus;

public record ErrorRes(
   HttpStatus status,
   String message
) {
}
