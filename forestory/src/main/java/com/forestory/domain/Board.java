package com.forestory.domain;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.forestory.dto.BoardDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamicInsert
@Entity
@Data
@Builder(builderMethodName = "BoardBuilder")
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
	
	public static BoardBuilder builder(BoardDTO boardDTO) {
        return BoardBuilder()
                    .boardCategory(boardDTO.getBoardCategory())
                    .boardTitle(boardDTO.getBoardTitle())
                    .boardContent(boardDTO.getBoardContent())
                    .boardReadcnt(boardDTO.getBoardReadcnt());
    }
	
}
