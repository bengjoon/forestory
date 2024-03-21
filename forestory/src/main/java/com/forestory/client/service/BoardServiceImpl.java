
package com.forestory.client.service;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.forestory.domain.Board;
import com.forestory.domain.BoardComment;
import com.forestory.dto.BoardCommentDTO;
import com.forestory.dto.BoardDTO;
import com.forestory.repository.BoardCommentRepository;
import com.forestory.repository.BoardRepository;

import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardRepository boardRepository;

	@Setter(onMethod_ = @Autowired)
	private BoardCommentRepository boardCommentRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Board> boardList(String boardCategory, String searchType, String keyword, Pageable pageable) {
		Page<Board> boardList = null;
		
		if(boardCategory == null && keyword == null) {
			boardList = boardRepository.findAll(pageable);
		} else if (boardCategory != null && keyword == null){
			boardList = boardRepository.findByBoardCategory(boardCategory, pageable);
		} else if (boardCategory == null && keyword != null) {
			switch(searchType) {
			case "title" -> boardList = boardRepository.findByBoardTitleContaining(keyword, pageable);
			case "content" -> boardList = boardRepository.findByBoardContentContaining(keyword, pageable);
			case "titleOrContent" -> boardList = boardRepository.findByBoardTitleContainingOrBoardContentContaining(keyword, keyword, pageable);
			}
		} else {
			switch(searchType) {
			case "title" -> boardList = boardRepository.findByBoardCategoryAndBoardTitleContaining(boardCategory, keyword, pageable);
			case "content" -> boardList = boardRepository.findByBoardCategoryAndBoardContentContaining(boardCategory, keyword, pageable);
			case "titleOrContent" -> boardList = boardRepository.findByCategoryAndTitleOrContentWithPaging(boardCategory, keyword, keyword, pageable);
			}
			
		}
		
		return boardList;
	}
	
	@Override
	@Transactional
	public Board save(BoardDTO boardDTO) {
		Board board = BoardDTO.toEntity(boardDTO);
		Board result = boardRepository.save(board);
		
		return result;
	}

	@Override
	public BoardDTO boardDetail(long boardNo) {
		Board entity = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 글번호가 존재하지 않습니다."));
		
		BoardDTO boardDto = Board.toDto(entity);
		
		return boardDto;
	}
	
	@Override
	@Transactional
	public void plusHit(long boardNo) {
		Board entity = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 글번호가 존재하지 않습니다."));
		
		entity.plusHit();
	}

	@Override
	@Transactional
	public Board update(long boardNo, BoardDTO boardDTO) {
		Board findBoard = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 글번호가 존재하지 않습니다."));
		
		findBoard.setBoardCategory(boardDTO.getBoardCategory());
		findBoard.setBoardTitle(boardDTO.getBoardTitle());
		findBoard.setBoardContent(boardDTO.getBoardContent());
		
		return findBoard;
	}
	
	@Override
	@Transactional
	public void delete(long boardNo) {
		boardRepository.deleteById(boardNo);
	}

	@Override
	@Transactional
	public String saveComment(long boardNo, BoardCommentDTO boardCommentDTO) {
		Board board = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 글번호가 존재하지 않습니다."));
		boardCommentDTO.setBoard(board);
		
		BoardComment boardComment = boardCommentDTO.toEntity(boardCommentDTO);
		BoardComment savedBoardComment  = boardCommentRepository.save(boardComment);
		
		String result = "";
		if(savedBoardComment != null) {
			result = "insertSuccess";
		} else {
			result = "insertFailure";
		}
		
		entityManager.refresh(board);
		
		return result;
	}

	@Override
	public List<BoardComment> getComments(long boardNo){
		Board board = boardRepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해당 글번호가 존재하지 않습니다."));

		List<BoardComment> comments = board.getComments();
		
		Collections.reverse(comments);
		
		return comments;
	}
	
	@Override
	@Transactional
	public void updateComment(long boardCommentNo, BoardCommentDTO boardCommentDTO) {
		BoardComment findBoardComment = boardCommentRepository.findById(boardCommentNo).orElseThrow(() -> new IllegalArgumentException("해당 댓글번호가 존재하지 않습니다."));
		
		findBoardComment.setBoardCommentContent(boardCommentDTO.getBoardCommentContent());
	}

	@Override
	public void deleteComment(long boardCommentNo) {
		boardCommentRepository.deleteById(boardCommentNo);
	}


}
