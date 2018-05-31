package com.autotrading.base.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IBM on 2016/8/1.
 * 通用工具类
 */
public class Tools {
    /**金额为分的格式 */
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 获取对象中空字段的字段名称
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 判断对象不是空的集合或者不是空的字符串或空格
     */
    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof Collection<?>){
            return obj!=null&&!((Collection) obj).isEmpty();
        }else if(obj instanceof Map<?, ?>){

            return obj!=null&&!((Map) obj).isEmpty();
        }else {
            return obj != null && !"".equals(obj.toString().trim());
        }
    }

    /**
     * 已utf-8编码进行base64加密
     * @param data      数据
     * @return          密文
     * @throws UnsupportedEncodingException
     */
    public static String encodeByBASE64(String data) throws UnsupportedEncodingException{
        return Tools.encodeByBASE64(data,"utf-8");
    }

    /**
     * base64加密
     * @param data      原数据
     * @param encoding  编码格式
     * @return          密文
     * @throws UnsupportedEncodingException
     */
    public static String encodeByBASE64(String data,String encoding) throws UnsupportedEncodingException {
        if(!Tools.isNotEmpty(data)){
            return "";
        }

        return Tools.encodeByBASE64(data.getBytes(encoding));
    }

    public static String encodeByBASE64(byte[] data){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 通过BASE64进行解码
     * @param data  数据
     * @return      密文
     * @throws IOException
     */
    public static byte[] decodeByBASE64(String data) throws IOException{
        if(!Tools.isNotEmpty(data)){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(data);
    }
    /**
     * 解密
     * @param data  密文数据
     * @return      明文
     * @throws IOException
     */
    public static String decodeByBASE64(String data,String encoding) throws IOException {
        if(!Tools.isNotEmpty(data)){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(data),encoding);
    }

    /**
     * 对象转换为Map
     * @param data          数据
     * @param enableNull    是否添加null对象值
     * @return              map
     */
    public static Map<String, Object> toMap(Object data,boolean enableNull) {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj;
            try {
                obj = field.get(data);
                if (obj != null) {
                    map.put(field.getName(), obj);
                }else if(enableNull) {
                    map.put(field.getName(),null);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     */
    public static Timestamp getTimeNow() {
        Timestamp dateNow = new Timestamp(System.currentTimeMillis());
        return dateNow;// new Date()为获取当前系统时间
    }

    /**
     * 字符串转Date（yyyyMMdd格式）
     */
    public static Date stringToDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date tranDate = sdf.parse(dateString);
            return tranDate;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    //把yyyymmdd转成yyyy-MM-dd格式
    public static String formatDate(String str){
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 =new SimpleDateFormat("yyyy-MM-dd");
        String sfstr = "";
        try {
            sfstr = sf2.format(sf1.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sfstr;
    }

    /**
     * Date（yyyyMMdd格式）转字符串
     */
    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取当前的前一天时间（yyyyMMdd格式）
     */
    public static String beforeDate() {
        Date date=new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取指定日期的前一天时间（yyyyMMdd格式）
     */
    public static String beforeDateOfCur(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }
    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static String changeF2Y(String amount) throws Exception{
        if(!amount.matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }
        return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).toString();
    }
    /**
     * 生成UUid
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    private Tools(){
        //私有化构造函数，防止继承。
    }



}
