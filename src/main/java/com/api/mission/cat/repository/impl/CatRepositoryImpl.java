package com.api.mission.cat.repository.impl;


import static com.api.mission.cat.entity.QCat.cat;

import com.api.mission.cat.dto.CatSimpleResDto;
import com.api.mission.cat.entity.Cat;
import com.api.mission.cat.repository.querydsl.CatRepositoryQuerydsl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CatRepositoryImpl extends QuerydslRepositorySupport implements
    CatRepositoryQuerydsl {

  private final JPAQueryFactory jpaQueryFactory;

  public CatRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    super(Cat.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<CatSimpleResDto> getRandomImages(int limit) {
    return jpaQueryFactory.select(
            Projections.constructor(
                CatSimpleResDto.class,
                cat.displayId,
                cat.image.url,
                cat.breeds.name
            )
        )
        .from(cat)
        .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
        .limit(limit)
        .fetch();
  }

}
