package com.example.catpicturesearch.application.repository;

import static com.example.catpicturesearch.application.domain.QBreed.breed;
import static com.example.catpicturesearch.application.domain.QCatBreed.catBreed;
import static com.example.catpicturesearch.application.domain.QCatImage.catImage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.catpicturesearch.application.domain.CatBreed;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CatBreedCustomRepositoryImpl implements CatBreedCustomRepository {

	private final JdbcTemplate jdbcTemplate;

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public void bulkInsert(List<CatBreed> catBreeds) {
		jdbcTemplate.batchUpdate(
				"INSERT INTO cat_breed (breed_id, cat_image_id) VALUES (?, ?)",
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, catBreeds.get(i).getBreed().getId());
						ps.setString(2, catBreeds.get(i).getCatImage().getId());
					}

					@Override
					public int getBatchSize() {
						return catBreeds.size();
					}
				}
		);
	}

	@Override
	public boolean existsAny() {
		Integer exist = jpaQueryFactory.selectOne()
				.from(catBreed)
				.fetchFirst();

		return exist != null;
	}

	@Override
	public List<CatBreed> findAllOrderByRandom(int limit) {
		return jpaQueryFactory.selectFrom(catBreed)
				.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
				.leftJoin(catBreed.breed, breed)
				.fetchJoin()
				.leftJoin(catBreed.catImage, catImage)
				.fetchJoin()
				.limit(limit)
				.fetch();
	}

	@Override
	public List<CatBreed> findAllByBreedId(String breedId) {
		return jpaQueryFactory.selectFrom(catBreed)
				.where(catBreed.breed.id.eq(breedId))
				.leftJoin(catBreed.breed, breed)
				.fetchJoin()
				.leftJoin(catBreed.catImage, catImage)
				.fetchJoin()
				.fetch();
	}

	@Override
	public CatBreed findAllByCatImageId(String catImageId) {
		return jpaQueryFactory.selectFrom(catBreed)
				.where(catBreed.catImage.id.eq(catImageId))
				.leftJoin(catBreed.breed, breed)
				.fetchJoin()
				.leftJoin(catBreed.catImage, catImage)
				.fetchJoin()
				.fetchOne();
	}

}
