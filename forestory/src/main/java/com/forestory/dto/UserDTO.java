package com.forestory.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.forestory.domain.Board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
	
	//@Email(message = "이메일 형식이 올바르지 않습니다.")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식이 올바르지 않습니다.")
	@NotEmpty(message = "이메일을 입력해주세요.")
	private String userEmail;
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}"
			, message = "비밀번호는 8~16자 영문, 숫자, 특수문자를 사용하세요.")
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String userPw;
	
	private String userRole;
	
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
	@NotEmpty(message = "닉네임을 입력해주세요.")
	private String userNick;
	
	@Pattern(regexp= "^([0-9]{2,3})-([0-9]{3,4})-([0-9]{4})$", message="올바른 전화번호를 입력해주세요.")
	@NotEmpty(message = "전화번호를 입력해주세요.")
	private String userPhone;
	
	private LocalDateTime userLeavedate;
	
	private LocalDateTime userRegdate;
	
	private Boolean userState;
	
	private List<Board> boards;
}
