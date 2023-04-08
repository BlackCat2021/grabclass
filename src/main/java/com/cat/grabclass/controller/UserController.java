package com.cat.grabclass.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.cat.grabclass.common.Result;
import com.cat.grabclass.common.annotations.Access;
import com.cat.grabclass.common.utils.*;
import com.cat.grabclass.entity.User;
import com.cat.grabclass.excption.BusinessException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cat.grabclass.service.UserService;

import javax.validation.constraints.NotNull;


/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 * @date 2023-03-28 12:29:39
 */
@RestController
@RequestMapping("grabclass/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    private static final String USER_PREFIX = "user_";

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		User user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody User user){
		userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody User user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }



    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    @Access
    public Result<Object> login(@NotNull(message = "用户学号不能为空") @RequestParam(name = "uid") String telephone,
                                @NotNull(message = "用户密码不能为空") @RequestParam(name = "password") String password) throws BusinessException {

        //用户登陆服务,用来校验用户登陆是否合法
        User user = userService.validateLogin(telephone, password);

        if (user == null) {
            throw new BusinessException(500, "用户不存在或密码错误");
        }
        RedisUtils.set(USER_PREFIX + user.getId(), user, 60, TimeUnit.MINUTES);
        log.info("学号:{}", user.getId());
        String token = JwtUtils.createToken(user.getId(), 60 * 60);
        Map<String, Object> data = new HashMap<>(4);
        data.put("userId", user.getId());
        data.put("header", "token");
        data.put("token", token);
        return Result.build(200, "登录成功", data);
    }




}
