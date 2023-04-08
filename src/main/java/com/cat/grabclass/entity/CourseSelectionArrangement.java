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
@TableName("course_selection_arrangement")
public class CourseSelectionArrangement implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 抢课课程id
	 */
	@TableId
	private Long id;
	/**
	 * 课程id
	 */
	private Long coursesId;
	/**
	 * 课程容量
	 */
	private Integer stockCount;
	/**
	 * 抢课开始时间
	 */
	private Date startDate;
	/**
	 * 抢课结束时间
	 */
	private Date endDate;

	/**
	 * 状态 0 未生效 1 生效
	 */
	private Integer state;

}
