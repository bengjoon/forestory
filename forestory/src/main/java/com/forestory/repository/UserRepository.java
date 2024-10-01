package com.forestory.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.forestory.domain.User;
import com.forestory.dto.GoogleChartVO;

import jakarta.transaction.Transactional;


public interface UserRepository extends JpaRepository<User, Long> {
   
   boolean existsByUserEmail(String userEmail);
   boolean existsByUserNick(String userNick);
   User findByUserEmail(String userEmail);
   User findByUserNo(long userNo);
   
   @Transactional
   @Query(value = "select DATE_FORMAT(user.user_regdate, '%Y-%m') as month, count(*) as count " +
		  "from User user " +
		  "group by month ", nativeQuery = true)
   List<GoogleChartVO> findUserCount();
}