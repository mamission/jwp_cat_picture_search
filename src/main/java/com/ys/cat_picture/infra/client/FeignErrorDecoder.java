package com.ys.cat_picture.infra.client;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Request;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Getter;

public class FeignErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

	private final ObjectMapper objectMapper;

	public FeignErrorDecoder(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public Exception decode(String methodKey, Response response) {

		int status = response.status();

		if (status >= 200 && status <= 399) {
			return defaultDecoder.decode(methodKey, response);
		}

		throw new FeignErrorException(status, response.headers(), message(response.body()), response.request());
	}

	private String message(Response.Body body) {
		try {
			byte[] bytes = body.asInputStream().readAllBytes();
			String originalMessage = new String(bytes);

			return originalMessage;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Getter
	static class FeignErrorException extends RuntimeException {
		private final int status;
		private final Map<String, Collection<String>> responseHeaders;

		private final String body;

		private final Request request;

		FeignErrorException(int status, Map<String, Collection<String>> responseHeaders, String body, Request request) {
			this.status = status;
			this.responseHeaders = responseHeaders;
			this.body = body;
			this.request = request;
		}

	}

}
