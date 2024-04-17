package com.forestory.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.forestory.domain.Board;
import com.forestory.domain.User;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom{

	List<Board> findByBoardNo(long boardNo);
	Page<Board> findByBoardCategory(String boardCategory, Pageable pageable);
	Page<Board> findByBoardTitleContaining(String keyword, Pageable pageable);
	Page<Board> findByBoardContentContaining(String keyword, Pageable pageable);
	Page<Board> findByBoardTitleContainingOrBoardContentContaining(String title, String content, Pageable pageable);
	Page<Board> findByBoardCategoryAndBoardTitleContaining(String boardCategory, String title, Pageable pageable);
	Page<Board> findByBoardCategoryAndBoardContentContaining(String boardCategory, String title, Pageable pageable);
	List<Board> findTop5ByOrderByBoardNoDesc();

	List<Board> findByUser(User user);
}