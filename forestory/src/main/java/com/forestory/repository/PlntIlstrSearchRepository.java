package com.forestory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forestory.domain.PlntIlstrSearch;

@Repository
public interface PlntIlstrSearchRepository extends JpaRepository<PlntIlstrSearch, Long> {

	PlntIlstrSearch findByPlantPilbkNo(long plantPilbkNo);
	
}
