package com.gnn.seckill.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiaoshaUser {

	private Long id;
	private String nickname;
	private String password;
	private String salt;
	private String head;
	private Date registerDate;
	private Date lastLoginDate;
	private Integer loginCount;
	@Override
	public String toString() {
		return "MiaoshaUser{" +
				"id=" + id +
				", nickname='" + nickname + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", head='" + head + '\'' +
				", registerDate=" + registerDate +
				", lastLoginDate=" + lastLoginDate +
				", loginCount=" + loginCount +
				'}';
	}
}
