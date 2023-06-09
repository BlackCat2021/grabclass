package com.cat.grabclass.common.contants;

/**
 * @author Mr.xin
 */

public enum ResultStatus {

    SUECCSS(200, "成功"),
    FAIL(501, "失败"),
    ERROR(500, "发生异常"),
    /*===================== 业务异常 =================*/
    COURSE_NOT_EXIST(4001, "课程不存在"),
    USER_NOT_FOUND(4002, "用户信息不存在"),
    QUANTITY_ERROR(4003, "数量只能在1~99之间"),
    ARRANGEMENT_NOT_EXIST(4004, "不在选课时间段"),
    ACTIVITY_NOT_STARTED(4005, "活动还没有开始"),
    ACTIVITY_HAS_ENDED(4006, "活动结束了"),
    STOCK_NOT_ENOUGH(4007, "剩余不足"),
//    ORDER_CREATE_SUCCESS(4008, "创建成功"),
    LOGIN_EXPIRED(4009, "登录过期"),
    RATELIMITE(4010, "你手速太快了,慢点"),
    CREATE_ORDER_FAIL(4011, "创建记录失败"),
    ORDER_CREATING(4012, "记录创建中"),
    ORDER_CREATE_SUCCESS(4013,"记录创建成功"),
    ORDER_CREATE_FAIL(4014,"记录创建失败");

    public Integer code;
    public String message;

    private ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
