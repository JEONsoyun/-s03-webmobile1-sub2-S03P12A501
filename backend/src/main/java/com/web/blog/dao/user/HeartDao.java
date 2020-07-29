
package com.web.blog.dao.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.model.user.Heart;
//User.java 참조 
public interface HeartDao extends JpaRepository<Heart, String> {
    
	Optional<Heart> findHeartByBidAndUid(String bid, String uid);
	
	List<Heart> findHeartByBid(String bid);
}
