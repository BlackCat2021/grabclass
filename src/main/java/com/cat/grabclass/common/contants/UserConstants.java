package com.cat.grabclass.common.contants;

import com.cat.grabclass.entity.User;

/**
 * @author Mr.xin
 */
public class UserConstants {
    public static final String USER_PREFIX = "user_";
    public static final ThreadLocal<User> USER_INFO = new ThreadLocal<>();
}
