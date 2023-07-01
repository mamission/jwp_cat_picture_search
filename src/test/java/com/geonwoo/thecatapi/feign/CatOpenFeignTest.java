package com.geonwoo.thecatapi.feign;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geonwoo.thecatapi.feign.response.BreedFeignResponse;
import com.geonwoo.thecatapi.feign.response.ImageFeignResponse;

@ExtendWith(MockitoExtension.class)
class CatOpenFeignTest {

	@Mock
	private CatOpenFeign catOpenFeign;

	@Test
	public void testGetRandomCatImages() {
		//given
		List<BreedFeignResponse> breeds = new ArrayList<>();
		BreedFeignResponse breed = new BreedFeignResponse("Australian Mist'",
			"Lively, Social, Fun-loving, Relaxed, Affectionate",
			"United States");
		breeds.add(breed);

		List<ImageFeignResponse> images = new ArrayList<>();
		ImageFeignResponse response = new ImageFeignResponse("_6x-3TiCA",
			"https://cdn2.thecatapi.com/images/_6x-3TiCA.jpg"
			, 1231, 1165, breeds);
		images.add(response);
		when(catOpenFeign.getRandomCatImages(1, 1)).thenReturn(images);

		//when
		List<ImageFeignResponse> result = catOpenFeign.getRandomCatImages(1, 1);

		//then
		assertThat(result.size()).isEqualTo(1);
		assertThat(result.get(0).id()).isEqualTo("_6x-3TiCA");
		verify(catOpenFeign).getRandomCatImages(1, 1);
	}

}