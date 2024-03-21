package com.forestory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forestory.domain.BoardComment;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

}