/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.cat.grabclass.common.utils;


import java.util.Optional;
import java.util.stream.Stream;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     * 升序
     */
    public static final String ASC = "asc";

    public static final class Redis{

        public static final String COURSE_STOCK = "course_stock_";
        public static final String FAIL_MESSAGE = "fail_message";
        /**
         * 抢课安排
         */
        public static String Course_SELE_ARRAN =  "course_sele_arran_";
        public static String COURSE =  "course_";
        public static String MESSAGE = "message_";

    }

    public static final class MQ{
        public static final String TX_RECORD_TOPIC = "tx_topic_course_record";
        public static final String TX_RECORD_TAG = "tx_tag_course_record";

        public static final String TOPIC_CACHE_DELETE = "cmn_topic_delete_cache";
        public static final String TAG_CACHE_DELETE = "cmn_tag_delete_cache";
    }



}
