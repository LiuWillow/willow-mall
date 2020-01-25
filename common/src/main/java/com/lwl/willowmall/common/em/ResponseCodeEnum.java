package com.lwl.willowmall.common.em;

import com.lwl.willowmall.common.constant.CommonConst;

/**
 * author liuweilong
 * date 2020/1/23 10:45 下午
 * desc 响应状态枚举
 */
public enum ResponseCodeEnum {
    SUCCESS(CommonConst.CODE_SUCCESS, "success"),
    FAILED(10001, "failed"),
    ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;
}
