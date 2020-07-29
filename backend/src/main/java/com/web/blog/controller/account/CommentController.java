package com.web.blog.controller.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dao.user.BoardDao;
import com.web.blog.dao.user.CommentDao;
import com.web.blog.model.user.Board;
import com.web.blog.model.user.Comment;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
@RequestMapping("feature/board")
public class CommentController {
	@Autowired
	CommentDao commentDao;
	


	@ApiOperation(value = "게시글번호에 해당하는 댓글의 정보를 반환한다.", response = CommentController.class)    
	@GetMapping("list/details/{id}/comments")
	public List<Comment> detailComment(@RequestParam("id") int id) {
		List<Comment> list = commentDao.findAll();
		ArrayList<Comment> rlist = new ArrayList<>();
		System.out.println(list.size() +"asd");
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getBoard_idx() == id) {
				System.out.println(list.get(i).toString());
				rlist.add(new Comment(list.get(i).getIdx(), list.get(i).getBoard_idx(), list.get(i).getContent(), list.get(i).getWriter()));
			}
		}
		
		return rlist;
	}


	@ApiOperation(value = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("list/details/comments/write")
	public Comment writeComment(@RequestBody Comment comment) {
		//board = new Board(0, "test", "test", null, 0, "unknown", 300);

		return commentDao.save(comment);
	}

	@ApiOperation(value = "게시글번호에 해당하는 게시글의 정보를 삭제한다.", response = String.class)    
	@DeleteMapping("list/details/comments/{idx}")
	public Optional<Comment> deleteComment(@PathVariable("idx") int idx) {
		commentDao.deleteById(idx);
		return null;
	}
	
	

}