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
 * @date 2023-03-28 12:29:39
 */
@Data
@TableName("record_info")
public class RecordInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 选课id
	 */
	@TableId
	private Long id;
	/**
	 * 学生id
	 */
	private Long studentId;
	/**
	 * 课程id
	 */
	private Long coursesId;
	/**
	 * 课程名称
	 */
	private String coursesName;
	/**
	 * 选课时间
	 */
	private Date createDate;
	/**
	 * 
	 */
	private Integer status;

}
