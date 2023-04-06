package com.ys.cat_picture.common.dto;

import java.util.List;

public record ApiResponses <T>(
	List<T> data
) {
}
