package com.cat.grabclass.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.entity.User;

import java.util.Map;

/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 */
public interface UserService extends IService<User> {

    PageUtils queryPage(Map<String, Object> params);

    User validateLogin(String telephone, String password);
}

