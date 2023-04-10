package com.cat.mission.catInfo.repository.impl;


import com.cat.mission.catInfo.dto.CatInfoSimpleResDto;
import com.cat.mission.catInfo.entity.CatInfo;
import com.cat.mission.catInfo.entity.QCatInfo;
import com.cat.mission.catInfo.repository.querydsl.CatInfoRepositoryQuerydsl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CatInfoRepositoryImpl extends QuerydslRepositorySupport implements
    CatInfoRepositoryQuerydsl {

  private final JPAQueryFactory jpaQueryFactory;

  public CatInfoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    super(CatInfo.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<CatInfoSimpleResDto> getRandomImages(int limit) {
    return jpaQueryFactory.select(
            Projections.constructor(
                CatInfoSimpleResDto.class,
                QCatInfo.catInfo.displayId,
                QCatInfo.catInfo.image.url,
                QCatInfo.catInfo.breeds.name
            )
        )
        .from(QCatInfo.catInfo)
        .orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
        .limit(limit)
        .fetch();
  }

}
