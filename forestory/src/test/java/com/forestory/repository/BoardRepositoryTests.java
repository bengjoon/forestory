package com.forestory.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.forestory.domain.Board;
import com.forestory.domain.User;

import jakarta.transaction.Transactional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class BoardRepositoryTests {

	@Setter(onMethod_ = @Autowired)
	private BoardRepository boardRepository;	
	
	@Setter(onMethod_ = @Autowired)
	private UserRepository userRepository;
	
	@Test
	@Transactional
	public void getBoards() {
		User user = userRepository.findByUserNo(1);
		
//		List<Board> boards = boardRepository.findByUser(user);
		List<Board> boards = user.getBoards();
		
		for(Board board : boards) {
			log.info(board.getBoardTitle());
		}
	}
}
