package com.lwl.willowmall.common.vo;

import com.lwl.willowmall.common.constant.CommonConst;
import com.lwl.willowmall.common.em.ResponseCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * author liuweilong
 * date 2020/1/23 10:40 下午
 * desc
 */
@Data
public class Response<T> {
    @ApiModelProperty("具体数据")
    private T data;
    @ApiModelProperty("状态码，0 表示调用成功")
    private int code;
    @ApiModelProperty("消息")
    private String msg;

    public static <T> Response<T> success(){
        Response<T> response = new Response<>();
        response.setCode(ResponseCodeEnum.SUCCESS.getCode());
        response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
        return response;
    }

    public static Response<?> error(ResponseCodeEnum responseCodeEnum) {
        Response<?> response = new Response<>();
        response.setCode(responseCodeEnum.getCode());
        response.setMsg(responseCodeEnum.getMsg());
        return response;
    }
}
