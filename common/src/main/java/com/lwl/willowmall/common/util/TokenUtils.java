package com.lwl.willowmall.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * author liuweilong
 * date 2020/1/24 4:07 下午
 * desc
 */
@Slf4j
public class TokenUtils {
    /**
     * 加密解密的秘钥
     */
    private static final String SECRET = "sanjieke_teammark_secret@2019_11_18";

    private static final String SECRET_LOGIN = "sanjieke_teammark_secret_login@2019_11_18";

    private static final String ALG = "alg";

    private static final String ALG_VALUE = "HS256";

    private static final String TYP = "typ";

    private static final String TYP_VALUE = "JWT";

    private static final String IS_USER = "SERVICE";

    private static final String SUBJECT = "TOKEN";

    private static final String SUBJECT_LOGIN = "TOKEN_LOGIN";

    private static final String AUDIENCE = "APP";

    public static final String USER_REDIS_KEY = "userRedisKey";
    public static final String EMAIL = "email";
    public static final String PASS_WORD = "password";

    public static final String VERSION = "version";
    /**
     * 过期时效   当前设置为2小时
     */
    private static final int  EXPIRATION_YEAR = 0;
    private static final int  EXPIRATION_MONTH = 0;
    private static final int  EXPIRATION_DAY = 1;
    private static final int  EXPIRATION_HOUR = 0;
    private static final int  EXPIRATION_MINUTE = 0;
    private static final int  EXPIRATION_SECOND = 0;
    private static final String TIME_OUT = "timeOut";
    private static final String COOKIES_MAX = "cookiesMax";

    /**
     * 创建一个token,传入用户信息存储在redis中的key,解密之后获取用户信息
     * @param userRedisKey
     * @return
     */
    public static String createJWTToken(String userRedisKey,Long version,Integer day,Integer cookiesMax){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            Date expireDate = getAfterDate(nowDate,EXPIRATION_YEAR,EXPIRATION_MONTH,day,EXPIRATION_HOUR,EXPIRATION_MINUTE,EXPIRATION_SECOND);//2小过期
            map.put(ALG, ALG_VALUE);
            map.put(TYP, TYP_VALUE);
            String token = JWT.create()
                    /*设置头部信息 Header*/
                    .withHeader(map)
                    /*设置 载荷 Payload*/
                    .withClaim(USER_REDIS_KEY, userRedisKey)
                    .withClaim(VERSION,version)
                    .withClaim(TIME_OUT,day)
                    .withClaim(COOKIES_MAX,cookiesMax)
                    .withIssuer(IS_USER)//签名是有谁生成 例如 服务器
                    .withSubject(SUBJECT)//签名的主题
                    //.withNotBefore(new Date())//定义在什么时间之前，该jwt都是不可用的.
                    .withAudience(AUDIENCE)//签名的观众 也可以理解谁接受签名的
                    .withIssuedAt(nowDate) //生成签名的时间
                    .withExpiresAt(expireDate)//签名过期的时间
                    /*签名 Signature */
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            log.error("创建JWT异常:",exception);
        }
        return null;
    }

    public static Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second){
        if(date == null){
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if(year != 0){
            cal.add(Calendar.YEAR, year);
        }
        if(month != 0){
            cal.add(Calendar.MONTH, month);
        }
        if(day != 0){
            cal.add(Calendar.DATE, day);
        }
        if(hour != 0){
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if(minute != 0){
            cal.add(Calendar.MINUTE, minute);
        }
        if(second != 0){
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }
}
