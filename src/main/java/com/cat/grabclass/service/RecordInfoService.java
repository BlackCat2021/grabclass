package com.cat.grabclass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.entity.RecordInfo;

import java.util.Map;

/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 */
public interface RecordInfoService extends IService<RecordInfo> {

    PageUtils queryPage(Map<String, Object> params);
}

