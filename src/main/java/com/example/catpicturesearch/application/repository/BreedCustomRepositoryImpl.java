package com.example.catpicturesearch.application.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.catpicturesearch.application.domain.Breed;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BreedCustomRepositoryImpl implements BreedCustomRepository {

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void bulkInsert(List<Breed> breeds) {
		jdbcTemplate.batchUpdate(
				"INSERT INTO breed (id, name, temperament, origin) VALUES (?, ?, ?, ?)",
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, breeds.get(i).getId());
						ps.setString(2, breeds.get(i).getName());
						ps.setString(3, breeds.get(i).getTemperament());
						ps.setString(4, breeds.get(i).getOrigin());
					}

					@Override
					public int getBatchSize() {
						return breeds.size();
					}
				}
		);
	}

}
