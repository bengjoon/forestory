package com.forestory.client.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.forestory.client.service.BoardService;
import com.forestory.domain.Board;
import com.forestory.domain.BoardComment;
import com.forestory.dto.BoardCommentDTO;
import com.forestory.dto.BoardDTO;

import lombok.Setter;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Setter(onMethod_ = @Autowired)
	private BoardService boardService;

	@GetMapping("/list")
	public String boardList(String boardCategory, String searchType, String keyword, Model model,
			@PageableDefault(page = 0, size = 10, sort = "boardNo", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boardList = boardService.boardList(boardCategory, searchType, keyword, pageable);

		int startPage = Math.max(1, boardList.getPageable().getPageNumber() - 10);
		int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber() + 10);

		model.addAttribute("boardList", boardList);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchType", searchType);
		model.addAttribute("category", boardCategory);

		return "client/board/boardList";
	}

	@GetMapping("/list/{boardNo}")
	public String boardDetail(@PathVariable long boardNo, Model model) {
		BoardDTO boardDto = boardService.boardDetail(boardNo);

		List<BoardComment> comments = boardDto.getComments();
		Collections.reverse(comments);
		
		boardService.plusHit(boardNo);

		model.addAttribute("board", boardDto);
		model.addAttribute("comments", comments);

		return "client/board/boardDetail";
	}

	@GetMapping("/insert")
	public String boardInsertForm(Model model) {
		model.addAttribute("boardDto", new BoardDTO());
		return "client/board/boardInsertForm";
	}

	@PostMapping("/insert")
	public String boardInsert(@Valid BoardDTO boardDTO, BindingResult bindingResult, Model model) {

		Map<String, String> errors = new HashMap<>();
		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}

			model.addAttribute("errors", errors);
			model.addAttribute("boardDto", boardDTO);

			return "client/board/boardInsertForm";
		}

		boardService.save(boardDTO);

		return "redirect:/board/list";
	}

	@GetMapping("/edit/{boardNo}")
	public String boardUpdateForm(@PathVariable long boardNo, Model model) {
		BoardDTO boardDto = boardService.boardDetail(boardNo);

		model.addAttribute("boardDto", boardDto);

		return "client/board/boardUpdateForm";
	}

	@PostMapping("/edit/{boardNo}")
	public String boardUpdate(@PathVariable long boardNo, @Valid BoardDTO boardDTO, Model model) {

		boardService.update(boardNo, boardDTO);

		return "redirect:/board/list/" + boardNo;
	}

	@GetMapping("/delete/{boardNo}")
	public String boardDelete(@PathVariable long boardNo) {
		boardService.delete(boardNo);
		
		return "redirect:/board/list";
	}
	
	
	// -- 댓글 --//
	@ResponseBody
	@PostMapping("/comment/{boardNo}")
	public String boardCommentSave(@PathVariable long boardNo, BoardCommentDTO boardCommentDTO) {

		String result = boardService.saveComment(boardNo, boardCommentDTO);
		
		return result;
	}

	@ResponseBody
	@GetMapping("/comment/{boardNo}")
	public List<BoardComment> getComments(@PathVariable long boardNo){
		List<BoardComment> comments = boardService.getComments(boardNo);

		return comments;
	}
	
	@ResponseBody
	@PostMapping("/comment/edit/{boardCommentNo}")
	public String boardCommentUpdate(@PathVariable long boardCommentNo, BoardCommentDTO boardCommentDTO) {
		boardService.updateComment(boardCommentNo, boardCommentDTO);
		
		return "updateSuccess";
	}
	
	@ResponseBody
	@DeleteMapping("/comment/{boardCommentNo}")
	public String boardCommentDelete(@PathVariable long boardCommentNo) {
		boardService.deleteComment(boardCommentNo);
		
		return "deleteSuccess";
	}
	
//	
//	// -- 댓글 --//
//	@PostMapping("/comment/{boardNo}")
//	public String boardCommentSave(@PathVariable long boardNo, BoardCommentDTO boardCommentDTO, Model model) {
//		
//		boardService.saveComment(boardNo, boardCommentDTO);
//		
//		List<BoardComment> comments = boardService.getComments(boardNo);
//		
//		model.addAttribute("comments", comments);
//		
//		return "client/board/boardDetail :: #boardCommentList";
//	}
//	
//	@PostMapping("/comment/edit/{boardNo}/{boardCommentNo}") 
//	public String boardCommentUpdate(@PathVariable long boardNo, @PathVariable long boardCommentNo, BoardCommentDTO boardCommentDTO, Model model) {
//		
//		boardService.updateComment(boardCommentNo, boardCommentDTO);
//		
//		List<BoardComment> comments = boardService.getComments(boardNo);
//		
//		model.addAttribute("comments", comments);
//		
//		return "client/board/boardDetail :: #boardCommentList";
//	}
	 

}
