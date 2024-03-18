package com.forestory.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.forestory.domain.Forestory;

public interface ForestoryRepository extends CrudRepository<Forestory, Long>{

}
