package com.web.blog.dao.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.model.user.Board;
import com.web.blog.model.user.User;

public interface BoardDao extends JpaRepository<Board, Integer> {
	Optional<Board> findById(int id);

}
