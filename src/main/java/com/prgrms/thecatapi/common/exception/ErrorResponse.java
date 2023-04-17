package com.prgrms.thecatapi.common.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ErrorResponse(
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	LocalDateTime timestamp,
	String message
) {

	public static ErrorResponse of(String message) {
		return new ErrorResponse(LocalDateTime.now(), message);
	}
}
