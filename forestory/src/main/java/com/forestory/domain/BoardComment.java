package com.forestory.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import com.forestory.dto.BoardCommentDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DynamicInsert
@Entity
@Getter @Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long boardCommentNo;	// 자유게시판 댓글 번호
	
	@Column(nullable = false)
	private String boardCommentContent;		// 자유게시판 댓글 내용
	
	@Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	@Builder.Default
	private LocalDateTime boardCommentRegdate = LocalDateTime.now();	// 자유게시판 댓글 등록일
	
	@ManyToOne
	@JoinColumn(name = "boardNo")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "userNo")
	private User user;
	
	public BoardCommentDTO toDto(BoardComment boardComment) {
		return BoardCommentDTO.builder()
						.boardCommentNo(boardComment.getBoardCommentNo())
						.boardCommentContent(boardComment.getBoardCommentContent())
						.boardCommentRegdate(boardComment.getBoardCommentRegdate())
						.board(boardComment.getBoard())
						.user(boardComment.getUser())
						.build();
	}
	
}