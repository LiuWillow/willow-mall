package com.lwl.willowmall.common.util;

import java.util.UUID;

/**
 * author liuweilong
 * date 2020/1/24 2:07 下午
 * desc
 */
public class CommonUtils {
    private static final String SPACE = "";

    private static final String  HORIZONTAL_LINE= "-";

    private CommonUtils(){}

    public static String generateUUID(){
        return UUID.randomUUID().toString().replace(HORIZONTAL_LINE,SPACE).toUpperCase();
    }
}
