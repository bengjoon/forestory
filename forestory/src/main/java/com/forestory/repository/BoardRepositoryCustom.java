package com.forestory.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.forestory.domain.Board;

public interface BoardRepositoryCustom {
	Page<Board> findByCategoryAndTitleOrContentWithPaging(String category, String title, String content, Pageable pageable);
	List<Board> findByCategoryAndTitleOrContent(String category, String title, String content);
}