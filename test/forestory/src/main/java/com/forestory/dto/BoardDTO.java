package com.forestory.dto;


import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

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
public class BoardDTO {

	private long boardNo;
	
	private String boardCategory;
	
	@NotBlank(message = "* 제목은 필수 입력 값입니다.")
	private String boardTitle;
	
	@NotBlank(message = "* 내용은 필수 입력 값입니다.")
	private String boardContent;
	
	private int boardReadcnt;
	
	private Date boardRegdate;
	
	private List<BoardComment> comments;
	
	public static Board toEntity(BoardDTO boardDTO) {
        return Board.builder()
        			.boardNo(boardDTO.getBoardNo())
                    .boardCategory(boardDTO.getBoardCategory())
                    .boardTitle(boardDTO.getBoardTitle())
                    .boardContent(boardDTO.getBoardContent())
                    .boardReadcnt(boardDTO.getBoardReadcnt())
        			.boardRegdate(boardDTO.getBoardRegdate())
        			.comments(boardDTO.getComments())
        			.build();
    }
}
