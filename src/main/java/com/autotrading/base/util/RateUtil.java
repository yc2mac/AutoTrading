package com.autotrading.base.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 作者   	: YJJ
 * 电子邮件	:1175500739@qq.com
 * 文件名    :com.candy.util.RateUtil
 * 建立日期	:2017/2/23
 * 修改日期  :21:44
 * 详细注释  :
 */
public class RateUtil {

    //四舍五入，获取指定位数的小数
    public static Double keep2Dec(Double d,int num){
        BigDecimal bg = new BigDecimal(d).setScale(num, RoundingMode.UP);
        return bg.doubleValue();
    }
}
