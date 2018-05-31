package com.autotrading.base.util;

/**
 * Created by yy on 2016/10/14
 * .手机注册生成随机验证码
 */
public class PhoneRandomUtil {
    /**
     * 手机注册验证码 生成
     * @param numberFlag  ture只生成数字 false生成数字带字母
     * @param length     验证码长度
     * @return 验证字符串
     */
    public static String createRandom(boolean numberFlag, int length){
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }
}
