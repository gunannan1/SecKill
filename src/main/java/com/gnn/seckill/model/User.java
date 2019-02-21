package com.gnn.seckill.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor //生成一个无参数的构造方法
@NoArgsConstructor //生成一个包含所有参数的构造方法
public class User {
	private Long id;
	private String username;
	private long phone;
	private String password;
	private String email;
	private String salt;
	private String head;
	private Date registerDate;
	private Date lastLoginDate;
	private Integer loginCount;
}