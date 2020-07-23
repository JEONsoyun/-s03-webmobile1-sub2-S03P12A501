package com.web.blog.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dao.user.BoardDao;
import com.web.blog.model.user.Board;

import io.swagger.annotations.ApiOperation;
@RestController
public class BoardController {
	@Autowired
	 BoardDao boardDao;
	
	 @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
		@PostMapping("feature/board")
		public Board writeBoard(@RequestBody Board board) {
			//board = new Board(0, "test", "test", null, 0, "unknown", 300);
		 	
			
		 return boardDao.save(board);
		}

}
