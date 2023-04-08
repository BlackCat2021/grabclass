package com.cat.grabclass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.common.utils.Query;
import com.cat.grabclass.entity.User;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cat.grabclass.dao.UserDao;
import com.cat.grabclass.service.UserService;

import javax.annotation.Resource;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


    @Resource
    private UserDao userDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<User> page = this.page(
                new Query<User>().getPage(params),
                new QueryWrapper<User>()
        );

        return new PageUtils(page);
    }

    @Override
    public User validateLogin(String uid, String password) {
        User user = new User();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,uid).eq(User::getPassword,password);
        return userDao.selectOne(queryWrapper);
    }

}