package com.cat.grabclass.service.impl;

import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.common.utils.Query;
import com.cat.grabclass.dao.RecordInfoDao;
import com.cat.grabclass.entity.RecordInfo;
import com.cat.grabclass.service.RecordInfoService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("recordInfoService")
public class RecordInfoServiceImpl extends ServiceImpl<RecordInfoDao, RecordInfo> implements RecordInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RecordInfo> page = this.page(
                new Query<RecordInfo>().getPage(params),
                new QueryWrapper<RecordInfo>()
        );

        return new PageUtils(page);
    }

}