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
@TableName("courses")
public class Courses implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课程id
	 */
	@TableId
	private Long id;
	/**
	 * 课程名称
	 */
	private String coursesName;
	/**
	 * 课程标题
	 */
	private String coursesTitle;
	/**
	 * 课程封面
	 */
	private String coursesImg;
	/**
	 * 课程详情
	 */
	private String coursesDetail;
	/**
	 * 任课老师
	 */
	private String coursesTeacher;
	/**
	 * 课程余量
	 */
	private Integer coursesStock;
	/**
	 * 课程容量
	 */
	private Integer coursesCapacity;

}
