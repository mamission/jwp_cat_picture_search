package com.search.cat_picture.global.openFeign;

import com.search.cat_picture.global.exception.BadRequestException;
import com.search.cat_picture.global.exception.OpenFeignServerException;
import com.search.cat_picture.global.exception.ResourceNotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecider implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		return switch (response.status()) {
			case 400 -> new BadRequestException();
			case 404 -> new ResourceNotFoundException();
			default -> new OpenFeignServerException();
		};
	}
}
