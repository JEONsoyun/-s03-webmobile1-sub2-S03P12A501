package com.web.blog.controller.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dao.user.BoardDao;
import com.web.blog.model.user.Board;

import io.swagger.annotations.ApiOperation;
@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
@RequestMapping("feature/board")
public class BoardController {
	@Autowired
	BoardDao boardDao;
	

	@ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("list")
	public List<Board> getBoardList() throws Exception {
		List<Board> list  = boardDao.findAll();
		return list;
	}
	@ApiOperation(value = "게시글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardController.class)    
	@GetMapping("list/detail/")
	public Optional<Board> detailBoard(@RequestParam("id") int id) {
		
		return boardDao.findById(id);
	}


	@ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/write")
	public Board writeBoard(@RequestBody Board board) {
		//board = new Board(0, "test", "test", null, 0, "unknown", 300);
		
		return boardDao.save(board);
	}

	@ApiOperation(value = "게시글번호에 해당하는 게시글의 정보를 삭제한다.", response = String.class)    
	@DeleteMapping("{id}")
	public Optional<Board> deleteBoard(@PathVariable("id") int id) {
		boardDao.deleteById(id);
		return null;
	}
	
	

}
