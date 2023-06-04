package com.ys.cat_picture.loader;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ys.cat_picture.cat_breed.repository.CatBreedRepository;
import com.ys.cat_picture.infra.client.CatApiClient;
import com.ys.cat_picture.infra.client.response.BreedResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final CatBreedRepository catBreedRepository;

	private final CatApiClient catApiClient;

	private final JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... args) throws Exception {

		if (!catBreedRepository.existsData()) {
			log.info("not exists data");
			List<BreedResponse> breeds = catApiClient.getBreeds();
			log.info("run init");
			bulkSave(breeds);
		}
	}

	@Transactional
	void bulkSave(List<BreedResponse> breeds) {
		jdbcTemplate.batchUpdate(
			"""
				insert
				into
				        cat_breeds
				        (external_id, name, temperament, origin)
				    values
						(?, ?, ?, ?)
				""", new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					BreedResponse breedResponse = breeds.get(i);
					ps.setString(1, breedResponse.id());
					ps.setString(2, breedResponse.name());
					ps.setString(3, breedResponse.temperament());
					ps.setString(4, breedResponse.origin());
				}

				@Override
				public int getBatchSize() {
					return breeds.size();
				}
			}
		);
	}
}
