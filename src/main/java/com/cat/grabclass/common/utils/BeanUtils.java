package com.cat.grabclass.common.utils;

/**
 * @author Mr.xin
 */
public class BeanUtils {

    public static void copyProperties(Object source, Object target){
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}
