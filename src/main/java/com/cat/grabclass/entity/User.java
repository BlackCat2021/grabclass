package com.cat.grabclass.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author zx
 * @email 3117383594@qq.com
 */
@Data
@TableName("user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 学号\工号
	 */
	@TableId
	private Long id;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 暗文存储：MD5(MD5(pass明文, 固定SALT), 随机SALT)
	 */
	private String password;
	/**
	 * 注册时间
	 */
	private Date registerDate;
	/**
	 * 上一次登录时间
	 */
	private Date lastLoginDate;
	/**
	 * 登录次数
	 */
	private Integer loginCount;
	/**
	 * 0-student 1-teacher 3-admin
	 */
	private Integer role;

}
