package com.web.blog.controller.account;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.mybatis.vo.UserInfo;
import com.web.blog.dao.user.UserDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;
import com.web.blog.service.MailSendService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.internal.org.jline.utils.Log;
import springfox.documentation.spi.service.contexts.SecurityContext;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
//port 2개를 열어놓을때 ... Vue를 3000번으로 열어줘야한다
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class AccountController {

	@Autowired
	UserDao userDao;
	
	@GetMapping("/update/{uid}")
    @ApiOperation(value = "회원정보조회")
    public Object search(@RequestBody User request, @PathVariable String id) throws Exception {
        ResponseEntity response = null;
        String uid = request.getUid();
        String email = request.getEmail();
        String password = request.getPassword();
        User user = new User(uid, email, password);
        userDao.
        
        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        System.out.println("변경 성공");
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "회원 탈퇴")
    public Object delete(@PathVariable String id) {
        String uid = id;
        if(mapper.search(uid)==null) {
        	System.out.println("존재하지 않는 회원");
   		 	return new ResponseEntity<>(null, HttpStatus.OK);
        }
        mapper.delete(uid);

        ResponseEntity response = null;

        final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        response = new ResponseEntity<>(result, HttpStatus.OK);

        return response;
    }

	@GetMapping("/user/{uid}")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password,HttpServletResponse res) {

		Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);

		ResponseEntity response = null;
		final BasicResponse result = new BasicResponse();
		if (userOpt.isPresent()) {
			User loginUser = userOpt.get();

			String token = jwtService.create(loginUser);

			res.setHeader("jwt-auth-token", token);

			result.status = true;
			result.data = "success";
			result.object = loginUser;
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}

	
	
	
	
}