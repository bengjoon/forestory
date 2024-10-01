package com.forestory.repository;

import java.util.List;

import com.forestory.dto.UserDTO;

public interface UserRepositoryCustom {
	
	List<UserDTO> findByMonthCount();
}
