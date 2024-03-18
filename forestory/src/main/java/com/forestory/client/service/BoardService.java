package com.forestory.client.service;

import java.util.List;

import com.forestory.domain.Board;

public interface BoardService {
	
	public List<Board> boardList();
	public Board save(Board board);
}
