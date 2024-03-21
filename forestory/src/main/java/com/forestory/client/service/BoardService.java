package com.forestory.client.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.forestory.domain.Board;
import com.forestory.domain.BoardComment;
import com.forestory.dto.BoardCommentDTO;
import com.forestory.dto.BoardDTO;

public interface BoardService {
	
	public Page<Board> boardList(String boardCategory, String searchType, String keyword, Pageable pageable);
	public BoardDTO boardDetail(long boardNo);
	public void plusHit(long boardNo);
	public Board save(BoardDTO boardDTO);
	public Board update(long boardNo, BoardDTO boardDTO);
	public void delete(long boardNo);
	public String saveComment(long boardNo, BoardCommentDTO boardCommentDTO);
	public List<BoardComment> getComments(long boardNo);
	public void updateComment(long boardCommentNo, BoardCommentDTO boardCommentDTO);
	public void deleteComment(long boardCommentNo);
	
}