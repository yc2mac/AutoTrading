package com.autotrading.base.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yy on 2016/10/19
 * .
 */
public class Ip {
    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
