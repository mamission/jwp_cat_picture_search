package com.cat.mission.catImage.repository.impl;

import static com.cat.mission.catBreeds.entity.QBreeds.breeds;
import static com.cat.mission.catImage.entity.QCatImage.catImage;

import com.cat.mission.catImage.dto.CatImageDetailResDto;
import com.cat.mission.catImage.dto.CatImageResDto;
import com.cat.mission.catImage.entity.CatImage;
import com.cat.mission.catImage.repository.querydsl.CatImageRepositoryQuerydsl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CatImageRepositoryImpl extends QuerydslRepositorySupport implements
    CatImageRepositoryQuerydsl {

  private final JPAQueryFactory jpaQueryFactory;

  public CatImageRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    super(CatImage.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }


  @Override
  public List<CatImageResDto> getRandomImages(int limit) {
    return jpaQueryFactory.select(
            Projections.constructor(
                CatImageResDto.class,
                breeds.catImage.displayId,
                breeds.catImage.url,
                breeds.name
            )
        )
        .from(breeds)
        .leftJoin(breeds.catImage, catImage)
        .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
        .limit(limit)
        .fetch();
  }

  @Override
  public List<CatImageResDto> getSearchListBy(String breedsName) {

    List<Long> breedsIds = jpaQueryFactory.select(breeds.id)
        .from(breeds)
        .where(breedsNameEq(breedsName))
        .fetch();

    System.out.println(breedsIds.size());

    if (breedsIds.isEmpty()) {
      return new ArrayList<>();
    }

    List<CatImageResDto> temp = jpaQueryFactory.select(
            Projections.constructor(
                CatImageResDto.class,
                breeds.catImage.displayId,
                breeds.catImage.url,
                breeds.name
            )
        )
        .from(breeds)
        .leftJoin(breeds.catImage, catImage)
        .where(breeds.id.in(breedsIds))
        .fetch();

    System.out.println(temp.size());
    return temp;
  }

  private BooleanExpression breedsNameEq(String breedsName) {
    return Objects.isNull(breedsName) ? null : breeds.name.eq(breedsName);
  }

  public CatImageDetailResDto getByDisplayId(String id) {
    return jpaQueryFactory.select(
            Projections.constructor(
                CatImageDetailResDto.class,
                breeds.name,
                breeds.catImage.displayId,
                breeds.catImage.url,
                breeds.catImage.width,
                breeds.catImage.height,
                breeds.temperament,
                breeds.origin
            )
        )
        .from(breeds)
        .leftJoin(breeds.catImage, catImage)
        .where(catImage.displayId.eq(id))
        .fetchOne();
  }

}
