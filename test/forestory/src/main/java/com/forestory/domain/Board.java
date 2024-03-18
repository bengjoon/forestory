package com.forestory.domain;


import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forestory.dto.BoardDTO;

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
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long boardNo;	// 자유게시판 글번호
	
	@Column(nullable = false)
	private String boardCategory, boardTitle;	// 분류, 제목
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String boardContent;	// 내용
	
	@ColumnDefault("0")
	private Integer boardReadcnt;	// 조회수
	
	@Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
	@Temporal(TemporalType.DATE)
	@Builder.Default
	private Date boardRegdate = new Date();		// 등록일
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("boardCommentRegdate desc")
	@JsonIgnore
	private List<BoardComment> comments;
	
	
	//== 빌더 ==//
	public static BoardDTO toDto(Board board) {
        return BoardDTO.builder()
        			.boardNo(board.getBoardNo())
                    .boardCategory(board.getBoardCategory())
                    .boardTitle(board.getBoardTitle())
                    .boardContent(board.getBoardContent())
                    .boardReadcnt(board.getBoardReadcnt())
        			.boardRegdate(board.getBoardRegdate())
        			.comments(board.getComments())
        			.build();
    }
	
	//== 조회수 증가 ==//
	public void plusHit() {
		boardReadcnt++;
	}
	
	
}
