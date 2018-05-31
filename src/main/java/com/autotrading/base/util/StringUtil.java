package com.autotrading.base.util;




import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/*
 * @author 	:YC
 * email	:37933599@qq.com
 * fileName :StringUtil.java
 * 日期		:2013-12-11
 * 详细注释 :字符串工具、商业精度运算工具
 *
 */
public class StringUtil {


	/**
	 * 拆分catch到的msg信息
	 * @param msg [PB000001]提示信息
	 * @return PB000001
	 */
	public static String getMsgRegCode(String msg){
		return msg.split("\\[")[1].split("\\]")[0];
	}
	/**
	 * 取子字符串
	 * @param oriStr   原字符串
	 * @param beginIndex  取子串的起始位置
	 * @param len    取子串的长度
	 * @return  子字符串
	 */
	public static String subString(String oriStr,int beginIndex,int len){
		String str="";
		int strlen = oriStr.length();
		beginIndex = beginIndex -1;
		if(strlen <= beginIndex){

		}else if(strlen <= beginIndex+len){
			str = oriStr.substring(beginIndex);
		}else{
			str = oriStr.substring(beginIndex, beginIndex+len);
		}
		return str;
	}

	/**
	 * 右补位，左对齐
	 * @param oriStr  原字符串
	 * @param len  目标字符串长度
	 * @param alexin  补位字符
	 * @return  目标字符串
	 */
	public static String padRight(String oriStr,int len,char alexin){
		String str="";
		int strlen = oriStr.length();
		if(strlen < len){
			for(int i=0;i<len-strlen;i++){
				str = str+alexin;
			}
		}
		str = str + oriStr;
		return str;
	}

	/**
	 * 左补位，右对齐
	 * @param oriStr  原字符串
	 * @param len  目标字符串长度
	 * @param alexin  补位字符
	 * @return  目标字符串
	 */
	public  static String padLeft(String oriStr,int len,char alexin){
		String str="";
		int strlen = oriStr.length();
		if(strlen < len){
			for(int i=0;i<len-strlen;i++){
				str = str+alexin;
			}
		}
		str = oriStr + str;
		return str;
	}




	/**
	 * @ 计算未登折记录的相信打印页数和页行数
	 * @param printRow 客户信息里的总行
	 * @return list list.get(0) 是bookPage list.get(1)是bookPageRow
	 */
	public static List<Integer> getprintItem(int printRow){
		List<Integer> list=new ArrayList<Integer>();
		if (printRow>12&&printRow<=24) {
			list.add(1);
			list.add(printRow);
		}
		else if (printRow>24&&printRow<=48) {
			list.add(2);
			list.add(printRow-24);
		}
		else if (printRow>48&&printRow<=72) {
			list.add(3);
			list.add(printRow-48);
		}
		else if (printRow>72&&printRow<=96) {
			list.add(4);
			list.add(printRow-72);
		}
		else if (printRow>96&&printRow<=120) {
			list.add(5);
			list.add(printRow-96);
		}
		else {
			list.add(1);
			list.add(13);
		}



		return list;

	}

	//进行商业运算
	/**
	 * 提供精确的加法运算。
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * 提供精确的减法运算。
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * 提供精确的乘法运算。
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * 提供精确的乘法运算。
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @param v3 乘数
	 * @return 三个参数的积
	 */
	public static double mul3(double v1,double v2, double v3){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		BigDecimal b3 = new BigDecimal(Double.toString(v3));
		BigDecimal b4 = new BigDecimal(b1.multiply(b2).doubleValue());
		return b3.multiply(b4).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @paramscale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1,double v2){
		int  scale=6;
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}




	//四舍五入 v1除以v2 小数点保留2位后的double值
	public static double getDouble(double v1,double v2){
		int scale=2;
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	//四舍五入 v1除以v2 小数点保留4位后的double值
	public static double getDouble4(double v1,double v2){
		int scale=4;
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();

	}
	//四舍五入 v1除以v2 小数点保留15位后的double值
	public static double getDouble15(double v1,double v2){
		int scale=15;
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * java生成随机数字和字母组合
	 * @paramlength[生成随机数的长度]
	 * @return
	 */
	public static String getCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7","8", "9", "a", "b", "c", "d", "e", "f"};

	public static String arrayConvertString(String[] params)
	{
		if(params==null||params.length==0)
		{
			return null;
		}
		StringBuffer menus = new StringBuffer();
		for(int i=0;i<params.length;i++){
			String param = params[i];
			menus.append(param);
			if(i!=params.length-1){
				menus.append(",");
			}
		}
		return menus.toString();
	}

	/**
	 * 登录密码加密
	 * @param明文密码
	 * @return 加密后的密码
	 */
	public static String loginMD5(String origin) {
		return MD5Encode(origin+"LYkj+Qydt=sj100");
	}

	/**
6	 * @param明文密码
	 * @return 加密后的密码
	 */
	public static String tranMD5(String origin) {
		return MD5Encode(origin+"Secuirity@kd$%QYDT");
	}
	/**
	 * 用户密码加密
	 * @param明文密码
	 * @return加密后的密码
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString=new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString=byteArrayToHexString(md.digest(resultString.getBytes()));
		}catch (Exception ex) {

		}
		return resultString;
	}
	public static String[] stringConvertArray(String param,String flag)
	{
		if(param==null||param.length()==0)
		{
			return null;
		}
		return param.split(flag);
	}
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	private static String byteToHexString(byte b) {
		int n = b;
		int d1 = 0;
		int d2 = 0;
		if (n < 0){
			n = 256 + n;
			d1 = n / 16;
			d2 = n % 16;
		}
		return hexDigits[d1] + hexDigits[d2];
	}
	public static String getString(List list)
	{
		String listString = list.toString().replace("[", "(").replace("]", ")");
		System.out.println(listString);
		return listString;
	}

	/**
	 *List数组保存的是网点号
	 * @param list
	 * @return
	 */
	public static String getListToString(List list)
	{
		String brchLiString = list.toString().replace("[", "").replace("]", "");
		return brchLiString;
	}

	/*
	@param src 数据源
	* @param key 密钥，长度必须是8的倍数
	* @return 返回加密后的数据
	* @throws Exception
	*/
	//private static final String PASSWORD_CRYPT_KEY = "__jDlog_";
	private final static String DES = "DES";

	public static String DESencrypt(String str)throws Exception {
		byte[] src=str.getBytes();
		byte[] dsp=null;
		byte[] key="12345678".getBytes();
//	DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
//	 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
//	 创建一个密匙工厂，然后用它把DESKeySpec转换成
//	 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
//	 Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
//	 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
//	 现在，获取数据并加密
//	 正式执行加密操作
		//  return cipher.doFinal(HEX_2_DSP(src,dsp));
		return byteArrayToHexString(cipher.doFinal(src));
	}

	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
	//字符串转BCD
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length()/2; p++) {
			if ( (abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ( (abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ( (abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ( (abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			}else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}


	public static String byteToString(byte b) {
		byte high, low;
		byte maskHigh = (byte)0xf0;
		byte maskLow = 0x0f;

		high = (byte)((b & maskHigh) >> 4);
		low = (byte)(b & maskLow);

		StringBuffer buf = new StringBuffer();
		buf.append(findHex(high));
		buf.append(findHex(low));

		return buf.toString();
	}

	private static char findHex(byte b) {
		int t = new Byte(b).intValue();
		t = t < 0 ? t + 16 : t;

		if ((0 <= t) &&(t <= 9)) {
			return (char)(t + '0');
		}

		return (char)(t-10+'A');
	}


	static byte[]  HEX_2_DSP(byte[] hex, byte[] dsp)
	{
		int i;
		char ch;

		for (i = 0; i < 8; i++) {
			ch = (char) ((hex[i] & 0xf0)>> 4);
			dsp[i * 2] = (byte) ((ch > 9) ? ch + 0x41 - 10 : ch + 0x30);
			ch = (char) (hex[i] & 0xf);
			dsp[i * 2 + 1] = (byte) ((ch > 9) ? ch + 0x41 - 10 : ch + 0x30);
		}
		return dsp;
	}



	// 字符串金额格式化
	public static Double transFormMoney(String str) {

		if (null == str || "".equals(str.trim()) || 0 >= str.length()) {

			return 0.00;
		} else {

			return Double.valueOf(str.replaceAll("\\,", ""));
		}
	}
	//字符串金额格式
	public static Double transFormMoneyNull(String str) {

		if (null == str || "".equals(str.trim()) || 0 >= str.length()) {

			return null;
		} else {

			return Double.valueOf(str.replaceAll("\\,", ""));
		}
	}

	// 字符串金额格式化
	public static String transFormMoneyStr(String str) {

		if (null == str || "".equals(str.trim()) || 0 >= str.length()) {

			return null;
		} else {

			return str.replaceAll("\\,", "");
		}
	}
	//将数字格式化成字符串
	public static String getDecimalFormat(Double d) {
		DecimalFormat fmt = new DecimalFormat("##,###,###,###,##0.00");
		String outStr = null;
		outStr = fmt.format(d);
		return outStr;
	}
	//判断字符是否为空
	public static boolean checkStringIsNull(String str)throws Exception{
		if(null == str || str.trim().equals("") || str.trim().length()<=0){
			return false;
		}else{
			return true;
		}
	}

	//打印实体值


	//判断是否包含忽略大小写的子字符串
	public static boolean isString(String source,String target){
		String str=source.toLowerCase();
		String sub=target.toLowerCase();
		if(str.indexOf(sub)!=-1){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 生成一个25位随机数，前缀自定义 不能大于8位 +时间抽17位
	 */
	public static String generateGiftCode(String prefix){

		long now = System.currentTimeMillis();
		//获取4位年份数字
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
		//获取时间戳
		String time=dateFormat.format(now);

		StringBuffer sb = new StringBuffer();
		sb.append(prefix);
		for(int i = 0;i < 8-prefix.length(); i++){
			Random random = new Random();
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if("char".equalsIgnoreCase(charOrNum)){
				//int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				char val = (char)(97 + random.nextInt(26));
				sb.append(val);
			}else if("num".equalsIgnoreCase(charOrNum)){
				int num = random.nextInt(9);
				sb.append(num);
			}
		}
		sb.append(time).append(now);
		return sb.toString();
	}

}
