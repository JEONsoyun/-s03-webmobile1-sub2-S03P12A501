package com.web.blog.model.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@Entity (name="board")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Board {
	@Id
	private int id;
	
	private String subject;
	private String content;
	private Date created;
	private String uid;
	private String email;
	private String tag;
	private int hit;
	
}
