package com.forestory.admin.service;

import java.util.List;

import com.forestory.dto.GoogleChartVO;

public interface DashboardService {
	public List<GoogleChartVO> findUserCount();
}
