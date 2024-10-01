package com.forestory.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import static com.forestory.domain.QUser.user;

import com.forestory.dto.UserDTO;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserReopsitoryImpl implements UserRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<UserDTO> findByMonthCount() {
		
		
//		DateTemplate<String> formattedDate = Expressions.dateTemplate(
//                String.class
//                , "DATE_FORMAT({0}, {1})"
//                , user.userRegdate
//                , ConstantImpl.create("%Y-%m"));
		
		StringTemplate formattedDate = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , user.userRegdate
                , ConstantImpl.create("%Y-%m"));
		
		List<UserDTO> result = jpaQueryFactory
										.select(Projections.fields(UserDTO.class, formattedDate, user.count()))
										.from(user)
										.groupBy(formattedDate)
										.fetch();
		
		return result;
	}
	
	

}
