package com.cat.grabclass.common.utils;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * @author Mr.xin
 */
public class JsonUtils {
    public static String objectToJson(Object o){
        return JSONObject.toJSONString(o);
    }
    public static <T> T jsonToObject(String json, Class<T> beanClass){
        return JSONObject.parseObject(json,beanClass);
    }

    public static <T> T jsonToObject(String json, TypeReference<T> typeReference){
        return JSONObject.parseObject(json,typeReference);
    }
}
