package com.forestory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forestory.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
   
   boolean existsByUserEmail(String userEmail);
   boolean existsByUserNick(String userNick);
   
   User findByUserEmail(String userEmail);
   User findByUserNo(long userNo);
}