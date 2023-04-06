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

		if (Boolean.FALSE.equals(catBreedRepository.existsData())) {
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
				        (adaptability, affection_level, alt_names, bidability, cat_friendly, cfa_url, 
				        child_friendly, country_code, country_codes, description, dog_friendly, energy_level, 
				        experimental, external_id, grooming, hairless, health_issues, hypoallergenic, indoor, 
				        intelligence, lap, life_span, name, natural_cat, origin, rare, reference_image_id, rex, 
				        shedding_level, short_legs, social_needs, stranger_friendly, suppressed_tail, temperament, 
				        vcahospitals_url, vetstreet_url, vocalisation, imperial, metric, wikipedia_url)
				    values
				        (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""", new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					BreedResponse breedResponse = breeds.get(i);
					BreedResponse.Weight weight = breedResponse.weight();
					ps.setInt(1, breedResponse.adaptability());
					ps.setInt(2, breedResponse.affectionLevel());
					ps.setString(3, breedResponse.altNames());
					ps.setInt(4, breedResponse.childFriendly());
					ps.setInt(5, breedResponse.dogFriendly());
					ps.setString(6, breedResponse.cfaUrl());
					ps.setInt(7, breedResponse.childFriendly());
					ps.setString(8, breedResponse.countryCode());
					ps.setString(9, breedResponse.countryCodes());
					ps.setString(10, breedResponse.description());
					ps.setInt(11, breedResponse.dogFriendly());
					ps.setInt(12, breedResponse.energyLevel());
					ps.setInt(13, breedResponse.experimental());
					ps.setString(14, breedResponse.id());
					ps.setInt(15, breedResponse.grooming());
					ps.setInt(16, breedResponse.hairless());
					ps.setInt(17, breedResponse.healthIssues());
					ps.setInt(18, breedResponse.hypoallergenic());
					ps.setInt(19, breedResponse.indoor());
					ps.setInt(20, breedResponse.intelligence());
					ps.setInt(21, breedResponse.lap());
					ps.setString(22, breedResponse.lifeSpan());
					ps.setString(23, breedResponse.name());
					ps.setInt(24, breedResponse.naturalCat());
					ps.setString(25, breedResponse.origin());
					ps.setInt(26, breedResponse.rare());
					ps.setString(27, breedResponse.referenceImageId());
					ps.setInt(28, breedResponse.rex());
					ps.setInt(29, breedResponse.sheddingLevel());
					ps.setInt(30, breedResponse.shortLegs());
					ps.setInt(31, breedResponse.socialNeeds());
					ps.setInt(32, breedResponse.strangerFriendly());
					ps.setInt(33, breedResponse.suppressedTail());
					ps.setString(34, breedResponse.temperament());
					ps.setString(35, breedResponse.vcahospitalsUrl());
					ps.setString(36, breedResponse.vetstreetUrl());
					ps.setInt(37, breedResponse.vocalisation());
					ps.setString(38, weight.imperial());
					ps.setString(39, weight.metric());
					ps.setString(40, breedResponse.wikipediaUrl());
				}

				@Override
				public int getBatchSize() {
					return breeds.size();
				}
			}
		);
	}
}
