package com.forestory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forestory.domain.Forestory;

public interface ForestoryRepository extends JpaRepository<Forestory, Long>{

}
