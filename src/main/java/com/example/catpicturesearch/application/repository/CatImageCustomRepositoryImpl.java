package com.example.catpicturesearch.application.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.catpicturesearch.application.domain.CatImage;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CatImageCustomRepositoryImpl implements CatImageCustomRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void bulkInsert(List<CatImage> catImages) {
		jdbcTemplate.batchUpdate(
				"INSERT INTO cat_image (id, width, height, url) VALUES (?, ?, ?, ?)",
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, catImages.get(i).getId());
						ps.setInt(2, catImages.get(i).getWidth());
						ps.setInt(3, catImages.get(i).getHeight());
						ps.setString(4, catImages.get(i).getUrl());
					}

					@Override
					public int getBatchSize() {
						return catImages.size();
					}
				}
		);
	}

}
