package com.forestory.dto;

import java.time.LocalDateTime;

import com.forestory.domain.Board;
import com.forestory.domain.BoardComment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCommentDTO {
	
	private long boardCommentNo;
	
	private String boardCommentContent;
	
	@Builder.Default
	private LocalDateTime boardCommentRegdate = LocalDateTime.now();
	
	private Board board;
	
	public BoardComment toEntity(BoardCommentDTO boardCommentDTO) {
		return BoardComment.builder()
						   .boardCommentNo(boardCommentDTO.getBoardCommentNo())
						   .boardCommentContent(boardCommentDTO.getBoardCommentContent())
						   .boardCommentRegdate(boardCommentDTO.getBoardCommentRegdate())
						   .board(boardCommentDTO.getBoard())
						   .build();
	}
}
