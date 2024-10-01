package com.forestory.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forestory.dto.GoogleChartVO;
import com.forestory.repository.UserRepository;

import lombok.Setter;

@Transactional
@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Setter(onMethod_=@Autowired)
	private UserRepository userRepository;
	
	
	@Override
	public List<GoogleChartVO> findUserCount() {
		
		return userRepository.findUserCount();
	}
}
