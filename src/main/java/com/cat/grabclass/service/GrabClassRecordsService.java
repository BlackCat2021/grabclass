package com.cat.grabclass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.entity.GrabClassRecords;
import com.cat.grabclass.excption.BusinessException;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 * @date 2023-03-28 12:29:39
 */

public interface GrabClassRecordsService extends IService<GrabClassRecords> {

    PageUtils queryPage(Map<String, Object> params);

    Long createRecord(Long studentId, Long courseId) throws BusinessException, ExecutionException;

    int getRecordStatus(Long recordId);
}

