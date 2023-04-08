package com.cat.grabclass.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * 
 * @author zx
 * @email 3117383594@qq.com
 */
@Data
@TableName("grab_class_records")
public class GrabClassRecords implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 抢课课程id
	 */
	@TableId
	private Long id;
	/**
	 * 学生id
	 */
	private Long studentId;
	/**
	 * 记录id
	 */
	private Long recordId;
	/**
	 * 课程id
	 */
	private Long coursesId;

}
