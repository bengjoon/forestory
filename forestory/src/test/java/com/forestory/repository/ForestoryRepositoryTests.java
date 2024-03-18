package com.forestory.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.forestory.domain.Forestory;

@SpringBootTest
public class ForestoryRepositoryTests {
	
	@Autowired
	ForestoryRepository forestoryRepository;

	@Test
	public void forestorySave() {
		forestoryRepository.save(Forestory.builder()
					.forestoryTitle("제목 테스트1")
					.forestoryContent("내용 테스트1")
					.build());
	}
}
