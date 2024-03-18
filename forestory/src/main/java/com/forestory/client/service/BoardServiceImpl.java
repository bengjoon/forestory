package com.forestory.client.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forestory.domain.Board;
import com.forestory.repository.BoardRepository;

import lombok.Setter;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardRepository boardRepository;

	@Override
	public List<Board> boardList() {
		List<Board> boardList = (List<Board>)boardRepository.findAll();
		
		return boardList;
	}
	
	@Override
	@Transactional
	public Board save(Board board) {
		Board result = boardRepository.save(board);
		
		return result;
	}


}
