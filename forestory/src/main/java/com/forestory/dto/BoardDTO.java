package com.forestory.dto;


import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardDTO {

	private long boardNo;
	
	private String boardCategory;
	
	@NotBlank(message = "제목은 필수 입력 값입니다.")
	private String boardTitle;
	
	@NotBlank(message = "내용은 필수 입력 값입니다.")
	private String boardContent;
	
	private int boardReadcnt;
	
	private Date boardRegdate;
	
}
