package com.autotrading.base.util;

/**
 * Created by yy on 2016/10/19
 * .
 */

/*
 *   银联卡卡号由三部分组成：发卡机构标识码(bin)、发卡机构自定义位、校验码。其中，
 *   卡号左起前六位是发卡机构标识代码(BIN)，由6位数字组成。BIN号由中国银联复制分配、
 *   确认和管理；卡号第七位起事发卡机构自定义位，由6至12位数字组成，发卡机构自行赋值；
 *   卡号最后一位是校验码，根据校验位前的数字（含BIN）取值Luhn方程计算得出。

 *   Luhn计算模10 “隔位2倍加”校验数的公式 计算步骤如下：
 *   1、从右边第一个数字（低序）开始每隔一位乘以2。
 *   2、把在步骤1中获得的乘积的各位数字与原号码中位乘2的各位数字相加求和。
 *   3、从步骤2中获得的数字邻近的较高的以0结尾的数字减去该数字（即求这个总和的低位数字
 *   （个位数）的“10的补数”）。如果在步骤2中得到的总和是以0结尾的数，则校验数字就是0
 */
public class Luhn {
        /**
         * Luhn算法
         * 根据卡号获取校验位
         * @param cardNumber
         * @return
         */
        public static int getCheckNumber(String cardNumber){
            int totalNumber = 0;
            int lastNumber = 0;
            for (int i = cardNumber.length()-1; i >= 0; i-=2) {
                int tmpNumber = calculate(Integer.parseInt(String.valueOf(cardNumber.charAt(i))) *  2);
                if (i==0) {
                    totalNumber += tmpNumber;
                }else {
                    totalNumber += tmpNumber + Integer.parseInt(String.valueOf(cardNumber.charAt(i-1)));
                }

            }
            if (totalNumber >= 0 && totalNumber < 9) {
                return (10 - totalNumber);
            }else {
                String str = String.valueOf(totalNumber);
                if (Integer.parseInt(String.valueOf(str.charAt(str.length()-1))) == 0) {
                    return 0;
                }else {
                    return (10 - Integer.parseInt(String.valueOf(str.charAt(str.length()-1))));
                }
            }

        }

        /**
         * 计算数字各位和
         * @param number
         * @return
         */
        public static int calculate(int number){
            String str = String.valueOf(number);
            int total = 0;
            for (int i = 0; i < str.length(); i++) {
                total += Integer.valueOf(Integer.parseInt(String.valueOf(str.charAt(i))));
            }
            return total;
        }

        public static final void main(String[] args) {
     //       String cardNumber = "621339090898100011";
            String cardNumber = "621339003888002561";
            System.out.println(cardNumber+String.valueOf(getCheckNumber(cardNumber)));
        }

}
