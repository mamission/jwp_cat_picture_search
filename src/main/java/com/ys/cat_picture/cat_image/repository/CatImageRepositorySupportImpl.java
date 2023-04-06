package com.ys.cat_picture.cat_image.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ys.cat_picture.cat_breed.domain.CatBreed;
import com.ys.cat_picture.cat_breed.repository.CatBreedRepository;
import com.ys.cat_picture.cat_image.domain.CatImage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CatImageRepositorySupportImpl
	implements CatImageRepositorySupport {

	private final JdbcTemplate jdbcTemplate;

	private final CatBreedRepository catBreedRepository;

	@Override
	public List<CatImage> saveAllIgnore(List<CatImage> catImages) {

		if (catImages.isEmpty()) {
			return catImages;
		}

		String sql =
			"""
				insert ignore into cat_images(external_id, url, width, height, cat_breed_id)
				values(?, ?, ?, ?, ?)
					""";

		jdbcTemplate.batchUpdate(
			sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					CatImage catImage = catImages.get(i);

					ps.setString(1, catImage.getExternalId());
					ps.setString(2, catImage.getUrl());
					ps.setInt(3, catImage.getWidth());
					ps.setInt(4, catImage.getHeight());

					if (catImage.getBreed() != null) {
						String externalId = catImage.getBreed().getExternalId();
						Optional<CatBreed> catBreedOptional = catBreedRepository.findByExternalId(externalId);

						if (catBreedOptional.isPresent()) {
							ps.setLong(5, catBreedOptional.get().getId());
						} else {
							ps.setString(5, null);
						}

					} else {
						ps.setString(5, null);
					}

				}

				@Override
				public int getBatchSize() {
					return catImages.size();
				}
			}
		);

		return catImages;
	}

}
