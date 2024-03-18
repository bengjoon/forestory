package com.forestory.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.forestory.domain.Board;

@SpringBootTest
public class BoardRepositoryTests {

	@Autowired
	BoardRepository boardRepository;
	
//	@Test
//	public void findByBoardTitleContainingTest() {
//		Page<Board> list = boardRepository.findByBoardTitleContainingOrBoardContentContaining("오", "오", null);
//		
//		for(Board board : list) {
//			System.out.println(board.toString());
//		}
//	}
	
//	@Test
//	public void findByBoardCategoryAndBoardTitleContainingTest() {
//		Page<Board> list = boardRepository.findByBoardCategoryAndBoardTitleContaining("공지사항", "오", null);
//		
//		for(Board board : list) {
//			System.out.println(board.toString());
//		}
//	}
	
//	@Test
//	public void findByCategoryAndTitleOrContent() {
//		List<Board> list = boardRepository.findByCategoryAndTitleOrContent("공지사항", "오", "오");
//		
//		for(Board board : list) {
//			System.out.println(board.toString());
//		}
//	}


}
