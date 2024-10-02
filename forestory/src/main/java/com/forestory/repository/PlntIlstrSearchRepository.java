package com.forestory.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forestory.domain.PlntIlstrSearch;

@Repository
public interface PlntIlstrSearchRepository extends JpaRepository<PlntIlstrSearch, Long> {

	Page<PlntIlstrSearch> findByPlantGnrlNmContaining(String keyword, Pageable pageable);
	Page<PlntIlstrSearch> findByEngNmContaining(String keyword, Pageable pageable);
	PlntIlstrSearch findByPlantPilbkNo(long plantPilbkNo);
	
}
