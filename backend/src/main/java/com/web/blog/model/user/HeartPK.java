package com.web.blog.model.user;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeartPK implements Serializable {
	private String bid;
	private String uid;
	public String getUid() {
      return uid;
   }

	public void setUid(String uid) {
		this.uid = uid;
	}
    public String getUid() {
      return uid;
   }
   public void setUid(String uid) {
      this.uid = uid;
   }
}
