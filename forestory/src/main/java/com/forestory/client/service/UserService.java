package com.forestory.client.service;

import java.util.Map;

import org.springframework.validation.BindingResult;

import com.forestory.domain.User;
import com.forestory.dto.UserDTO;

public interface UserService {
   public User signUp(UserDTO userDto);
   public boolean existsByUserEmail(UserDTO userDto);
   public boolean existsByUserNick(UserDTO userDto);
   public Map<String, String> validateHandling(UserDTO userDto, BindingResult result);
}