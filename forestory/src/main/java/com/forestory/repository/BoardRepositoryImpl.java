package com.forestory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static com.forestory.domain.QBoard.board;

import java.util.List;

import com.forestory.domain.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Board> findByCategoryAndTitleOrContent(String category, String title, String content) {
		
		List<Board> result = jpaQueryFactory.selectFrom(board)
											.where(board.boardTitle.contains(title)
													.or(board.boardContent.contains(content))
													.and(board.boardCategory.eq(category)))
											.fetch();
		
		return result;
	}

	@Override
	public Page<Board> findByCategoryAndTitleOrContentWithPaging(String category,
			String title, String content, Pageable pageable) {
		List<Board> results = jpaQueryFactory
										.selectFrom(board)
										.where(board.boardTitle.contains(title)
												.or(board.boardContent.contains(content))
												.and(board.boardCategory.eq(category)))
										.offset(pageable.getOffset())
										.limit(pageable.getPageSize())
										.orderBy(board.boardNo.desc())
										.fetch();
		
		@SuppressWarnings("deprecation")
		long total = jpaQueryFactory
						.selectFrom(board)
						.where(board.boardTitle.contains(title)
								.or(board.boardContent.contains(content))
								.and(board.boardCategory.eq(category)))
						.fetchCount();

		
		return new PageImpl<Board>(results, pageable, total);
	}
	
	
}