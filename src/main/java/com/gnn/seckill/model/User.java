package com.gnn.seckill.model;

import lombok.*;

import java.util.Date;

/**
 *用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Long id;
	private String username;
	private String phone;
	private String password;
	private String email;
	private String salt;
	private String head;
	private Date registerDate;
	private Date lastLoginDate;
	private Integer loginCount;

}
