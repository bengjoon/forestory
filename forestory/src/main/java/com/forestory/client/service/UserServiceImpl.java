package com.forestory.client.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.forestory.domain.User;
import com.forestory.dto.UserDTO;
import com.forestory.repository.UserRepository;

import lombok.Setter;

@Transactional
@Service
public class UserServiceImpl implements UserService {
   
   @Setter(onMethod_=@Autowired)
   private UserRepository userRepository;
   
   @Setter(onMethod_=@Autowired)
   private BCryptPasswordEncoder bCryptPasswordEncoder;
   
   // 회원가입 로직
   @Override
   public User signUp(UserDTO userDto) {
	   
	   // 회원정보 입력
	   User user = User.builder()
			   			.userEmail(userDto.getUserEmail())
						.userPw(bCryptPasswordEncoder.encode(userDto.getUserPw()))
						.userNick(userDto.getUserNick())
						.userPhone(userDto.getUserPhone().replaceAll("[-]", ""))
						.userRole("ROLE_USER")
						.build();
	   
	   return userRepository.save(user);
   }
   
   // 유효성 검사 에러 메세지
   @Override
   public Map<String, String> validateHandling(UserDTO userDto, BindingResult result) {
	   
	   Map<String, String> validErrors = new HashMap<>();
	   
	   // 오류가 있을경우 회원가입 화면에서 값 유지
	   validErrors.put("keepEmail",String.valueOf(userDto.getUserEmail()));
	   validErrors.put("keepNick", String.valueOf(userDto.getUserNick()));
	   validErrors.put("keepPhone", String.valueOf(userDto.getUserPhone()));
	   
	   for (FieldError error : result.getFieldErrors()) {
		   // 필드명과 오류 메시지를 HashMap에 추가
		   validErrors.put(error.getField(), error.getDefaultMessage());
	   }
	   
	   if(existsByUserEmail(userDto)) {
		   validErrors.put("userEmail", "이미 사용중인 이메일입니다.");
	   }
	   
	   if(existsByUserNick(userDto)) {
		   validErrors.put("userNick", "이미 사용중인 닉네임입니다.");
	   }
	   
	   return validErrors;
   }
   
   // 이메일 중복 검사
   @Override
   public boolean existsByUserEmail(UserDTO userDto) {
	   User user = User.builder().userEmail(userDto.getUserEmail()).build();
      return userRepository.existsByUserEmail(user.getUserEmail());
   }
   
   // 닉네임 중복 검사
   @Override
   public boolean existsByUserNick(UserDTO userDto) {
	   User user = User.builder().userNick(userDto.getUserNick()).build();
      return userRepository.existsByUserNick(user.getUserNick());
   }

   // 유저 정보 가져오기
	@Override
	public User getUser(long userNo) {
		User user = userRepository.findById(userNo).orElseThrow(() -> new IllegalArgumentException("해당 유저 번호가 존재하지 않습니다."));
		return user;
	}


}