package com.autotrading.base.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @author 	:YC
 * email	:37933599@qq.com
 * fileName :DateUtil.java
 * 日期		:2013-12-11
 * 详细注释 :日期计算工具
 *
 */
public class DateUtil {

	/**
	 *
	 *   功能:   得到按月结息还款次数
	 *   @param开始日期 到日日期
	 *   @return   按月结息的还款次数
	 * @throws Exception
	 */
	public   static   int   getReturnTimesOfMonth(Date beginDate,Date endDate) throws Exception{
		DateFormat aa = DateFormat.getDateInstance();

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		List list = new ArrayList();
		list.add("2009-10");
		c1.setTime(beginDate);
		c2.setTime(endDate);
		while (c1.compareTo(c2)<0)
		{
			c1.add(Calendar.MONTH,1);// 开始日期加一个月直到等于结束日期为止
			Date ss =c1.getTime();
			String str =aa.format(ss);
			str =str.substring(0,str.lastIndexOf("-"));
			list.add(str);
		}
		String[] str= new String[list.size()];
		for (int i=0;i<list.size();i++ )
		{
			str[i] =(String)list.get(i);
		}
		return str.length;
	}

	/**
	 *
	 *   功能:   得到一个月的最后一天
	 *   @param日期
	 *   @return   月末那一天Date
	 * @throws Exception
	 */
	public   static   Date   getLastDayOfMonth(Date   curDate) throws Exception{
		Calendar   c=Calendar.getInstance();
		c.setTime(curDate);
		int myear = c.get(c.YEAR);
		int mmonth =c.get(c.MONTH) + 1;
		int mday=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return getDate(Integer.valueOf(myear)+"-"+Integer.valueOf(mmonth)+"-"+Integer.valueOf(mday));
	}

	/**
	 *
	 *   功能:   得到上个月的第一天
	 *   @param日期
	 *   @return   上个月第一天Date
	 * @throws Exception
	 */
	public  static  Date getPreDayOfMonth(Date curDate,int step) throws Exception{
		Calendar   c=Calendar.getInstance();
		c.setTime(curDate);
		int myear = c.get(c.YEAR);
		int mmonth =c.get(c.MONTH)+1+step;
		int lday=c.getActualMinimum(Calendar.DAY_OF_MONTH);
		return getDate(Integer.valueOf(myear)+"-"+Integer.valueOf(mmonth)+"-"+Integer.valueOf(lday));
	}

	/**
	 * 获取上月第一天日期
	 * @return 上个月第一天String
	 * @throws Exception
	 */
	public static String getPreviousMonthFirst() throws Exception{
		String str = "";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE,1);//设为当前月的1 号
		lastDate.add(Calendar.MONTH,-1);//减一个月，变为下月的1 号
//lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天
		str=sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 *
	 *   功能:   判断是否是月末
	 *   @param日期
	 *   @return   true月末,false不是月末
	 */
	public   static   boolean   isLastOfMonth(Date   curDate){
		Calendar   c=Calendar.getInstance();
		c.setTime(curDate);

		if(c.get(Calendar.DATE)==c.getActualMaximum(Calendar.DAY_OF_MONTH))
			return   true;
		else
			return   false;
	}

	/**
	 *
	 * @param贷款发放日期beginDate 贷款收回日期endDate
	 * @return 还款次数
	 * @throws Exception
	 */
	public static int getReturnDay(Date beginDate,Date endDate) throws Exception {

		int times=1;
		String s1="03-21";
		String s2="06-21";
		String s3="09-21";
		String s4="12-21";

		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		begin.setTime(beginDate);
		end.setTime(endDate);
		int beginYear = begin.get(begin.YEAR);
		int endYear = end.get(end.YEAR);

		for (int i=beginYear; i <= endYear; i++) {
			if(DateUtil.getDate(String.valueOf(i)+"-"+s1).before(endDate))
			{
				times=times+1;
			}
			if(DateUtil.getDate(String.valueOf(i)+"-"+s2).before(endDate))
			{times=times+1;}
			if(DateUtil.getDate(String.valueOf(i)+"-"+s3).before(endDate))
			{times=times+1;}
			if(DateUtil.getDate(String.valueOf(i)+"-"+s4).before(endDate))
			{times=times+1;}
		}
		return times;
	}





	/*
	 * 查询当前日期的 下个结息日 curDate 当前日期
	 *
	 *
	 */
	public static String getReturnDate(Date curDate) throws Exception {
		StringBuilder re1 = new StringBuilder();
		re1.append("03-21");
		StringBuilder re2 = new StringBuilder();
		re2.append("06-21");
		StringBuilder re3 = new StringBuilder();
		re3.append("09-21");
		StringBuilder re4 = new StringBuilder();
		re4.append("12-21");
		Calendar mDate = Calendar.getInstance();
		mDate.setTime(curDate);
		int mYear = mDate.get(mDate.YEAR);
		StringBuilder myearString = new StringBuilder();
		myearString.append(mYear).append('-');
		if (curDate.before(getDate(myearString.append(re1.toString())
				.toString())) == true) {
			myearString.setLength(0);
			return myearString.append(mYear).append('-').append(re1).toString();
		} else {
			myearString.setLength(0);
			myearString.append(mYear).append('-');
			if (curDate.before(getDate(myearString.append(re2).toString())) == true) {
				myearString.setLength(0);
				return myearString.append(mYear).append('-').append(re2)
						.toString();
			} else {
				myearString.setLength(0);
				myearString.append(mYear).append('-');

				if (curDate.before(getDate(myearString.append(re3).toString())) == true) {
					myearString.setLength(0);
					return myearString.append(mYear).append('-').append(re3)
							.toString();
				} else {
					myearString.setLength(0);
					myearString.append(mYear).append('-');

					if (curDate.before(getDate(myearString.append(re4)
							.toString())) == true) {
						myearString.setLength(0);
						return myearString.append(mYear).append('-')
								.append(re4).toString();
					} else {
						myearString.setLength(0);

						return myearString.append(mYear + 1).append('-')
								.append(re1).toString();
					}
				}
			}

		}
	}

	public static int getMonth(Date beginDate, Date endDate) throws Exception {
		if (!beginDate.after(endDate)) {
			Calendar begin = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			begin.setTime(beginDate);
			end.setTime(endDate);
			int beginYear = begin.get(begin.YEAR);
			int endYear = end.get(end.YEAR);
			int beginMonth = begin.get(begin.MONTH) + 1;
			int endMonth = end.get(end.MONTH) + 1;
			int beginDay=begin.get(begin.DAY_OF_MONTH);
			int endDay=end.get(end.DAY_OF_MONTH);
			int resMou=(endYear - beginYear) * 12 + (endMonth) - beginMonth;
			if (beginDay>endDay) {
				resMou=resMou-1;
			}
			return resMou;
		}
		else {
			throw new Exception("开始日期不能小于结束日期!");
		}

	}
	/**
	 * 储蓄日期计算 取两个日期之间的天数 对年对月法 用于定期提前和延期支取中的活期部分
	 *
	 * （1）对年对月计算 ①不论大月、小月、平月、闰月，每月均按３０天计算存期。 ②对年按３６０天计算，对月按３０天计算。
	 * ③存期有不足月的零头天数，按实际存期天数计算。 （2）特殊情况的存期计算 ①定期储蓄存入日为到期月份所没有的，即以到期月的月末日为到期日；
	 */
	public static int getDayErr(Date beginDate, Date endDate) {
		int resultDay = 0;
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		begin.setTime(beginDate);
		end.setTime(endDate);
		int beginYear = begin.get(begin.YEAR);
		int endYear = end.get(end.YEAR);
		int beginMonth = begin.get(begin.MONTH) + 1;
		int endMonth = end.get(end.MONTH) + 1;
		int beginDay = begin.get(begin.DAY_OF_MONTH);
		int endDay = end.get(end.DAY_OF_MONTH);
		if (endDay==31) {
			endDay=30;
		}
		if (beginYear == endYear) {
			if (endDay < beginDay) {
				resultDay = Math.abs(endMonth - beginMonth - 1) * 30
						+ (30 - Math.abs(beginDay - endDay));
			} else {
				resultDay = Math.abs(endMonth - beginMonth) * 30
						+ (endDay - beginDay);
			}

		} else {
			if (endDay < beginDay) {
				resultDay = (Math.abs(endYear - beginYear - 1) * 12 + (12 -1- beginMonth + endMonth))
						* 30 + (30 - Math.abs(beginDay - endDay));
			} else {
				resultDay = (Math.abs(endYear - beginYear - 1) * 12 + (12  - beginMonth + endMonth))
						* 30 + (endDay - beginDay);

			}

		}

		return resultDay;
	}
	/**
	 * 专用保险股 每月提取
	 * 储蓄日期计算 （1）对年对月计算 ①不论大月、小月、平月、闰月，每月均按３０天计算存期。 ②对年按３６０天计算，对月按３０天计算。
	 * ③存期有不足月的零头天数，按实际存期天数计算。 （2）特殊情况的存期计算 ①定期储蓄存入日为到期月份所没有的，即以到期月的月末日为到期日；
	 * creatDate  保险部的开户日期
	 * beginDate  保险股的 当前分红日期
	 */

	public static Date getEndDateForTrLag15(Date creatDate,Date beginDate, int period) throws Exception {
		Calendar b = Calendar.getInstance();
		b.setTime(beginDate);
		int year = b.get(b.YEAR);
		int month = b.get(b.MONTH) + 1;
		int day = b.get(b.DAY_OF_MONTH);

		Calendar c = Calendar.getInstance();
		c.setTime(creatDate);

		int cday = c.get(c.DAY_OF_MONTH);


		int year11=year;
		int month11=month;
		switch (period) {
			case 1:
				period=1;
				if (month + period > 12) {
					month = month + period - 12;
					year = year + 1;
				} else {
					month = month + period;
				}
				Calendar l1 = Calendar.getInstance();
				l1.setTime(lastDate(year, month));
				int l1year = l1.get(l1.YEAR);
				int l1month = l1.get(l1.MONTH) + 1;
				int l1day = l1.get(l1.DAY_OF_MONTH);

				StringBuilder sb11 = new StringBuilder();
				sb11.append(year).append("-").append(month).append("-").append(day);
				if (isValidDate(sb11.toString())) {
					if(beginDate.equals(lastDate(year11, month11)))
					{

						if (cday<l1day) {
							return DateUtil.getDate(String.valueOf(l1year)+"-"+String.valueOf(l1month)+"-"+String.valueOf(cday));
						}
						else {
							return lastDate(year, month);
						}

					}
					else {
						return getDate(sb11.toString());
					}

				} else {
					if (cday<l1day) {
						return DateUtil.getDate(String.valueOf(l1year)+"-"+String.valueOf(l1month)+"-"+String.valueOf(cday));
					}
					else {
						return lastDate(year, month);
					}
				}


			default:
				return null;
		}

	}
	/**
	 * 储蓄日期计算 （1）对年对月计算 ①不论大月、小月、平月、闰月，每月均按３０天计算存期。 ②对年按３６０天计算，对月按３０天计算。
	 * ③存期有不足月的零头天数，按实际存期天数计算。 （2）特殊情况的存期计算 ①定期储蓄存入日为到期月份所没有的，即以到期月的月末日为到期日；
	 */
	// 储蓄日期计算1
	public static Date getEndDate(Date beginDate, int period) throws Exception {
		Calendar b = Calendar.getInstance();
		b.setTime(beginDate);
		int year = b.get(b.YEAR);
		int month = b.get(b.MONTH) + 1;
		int day = b.get(b.DAY_OF_MONTH);
		switch (period) {
			case 1:
				if (month + period > 12) {
					month = month + period - 12;
					year = year + 1;
				} else {
					month = month + period;
				}
				StringBuilder sb = new StringBuilder();
				sb.append(year).append("-").append(month).append("-").append(day);
				if (isValidDate(sb.toString())) {
					return getDate(sb.toString());
				} else {
					return lastDate(year, month);
				}
			case 3:
				if (month + period > 12) {
					month = month + period - 12;
					year = year + 1;
				} else {
					month = month + period;
				}
				StringBuilder sb3 = new StringBuilder();
				sb3.append(year).append("-").append(month).append("-").append(day);
				if (isValidDate(sb3.toString())) {
					return getDate(sb3.toString());
				} else {
					return lastDate(year, month);
				}

			case 6:
				if (month + period > 12) {
					month = month + period - 12;
					year = year + 1;
				} else {
					month = month + period;
				}
				StringBuilder sb6 = new StringBuilder();
				sb6.append(year).append("-").append(month).append("-").append(day);
				if (isValidDate(sb6.toString())) {
					return getDate(sb6.toString());
				} else {
					return lastDate(year, month);
				}
			case 12:
				StringBuilder sb12 = new StringBuilder();
				sb12.append(year + 1).append("-").append(month).append("-").append(
						day);
				if (isValidDate(sb12.toString())) {
					return getDate(sb12.toString());
				} else {
					return lastDate(year + 1, month);
				}

			case 24:
				StringBuilder sb24 = new StringBuilder();
				sb24.append(year + 2).append("-").append(month).append("-").append(
						day);
				if (isValidDate(sb24.toString())) {
					return getDate(sb24.toString());
				} else {
					return lastDate(year + 2, month);
				}
			case 36:
				StringBuilder sb36 = new StringBuilder();
				sb36.append(year + 3).append("-").append(month).append("-").append(
						day);
				if (isValidDate(sb36.toString())) {
					return getDate(sb36.toString());
				} else {
					return lastDate(year + 3, month);
				}
			case 60:
				StringBuilder sb60 = new StringBuilder();
				sb60.append(year + 5).append("-").append(month).append("-").append(
						day);
				if (isValidDate(sb60.toString())) {
					return getDate(sb60.toString());
				} else {
					return lastDate(year + 5, month);
				}
			default:
				return null;
		}

	}

	// 储蓄日期计算2
	// 求该月的最后一天
	public static Date lastDate(int year, int month) throws Exception {
		Calendar b = Calendar.getInstance();
		b.setTime(getDate(String.valueOf(year) + "-" + String.valueOf(month)
				+ "-01"));
		int lastDay = b.getActualMaximum(Calendar.DAY_OF_MONTH);
		StringBuilder sb = new StringBuilder();
		sb.append(year).append("-").append(month).append("-").append(lastDay);
		return getDate(sb.toString());
	}

	// 储蓄日期计算3
	// 验证字符串日期 是否是一个有效的日期
	public static boolean isValidDate(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			df.setLenient(false);
			df.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;

		}
	}

	public static Date getDate(String str) throws Exception {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.parse(str);
	}
	public static Date getDateTime(String str) throws Exception {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.parse(str);
	}
	public static Date getDateTime14(String str) throws Exception {
		DateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
		return format1.parse(str);
	}
	public static Date getDateNow() throws Exception {
		String strDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.parse(strDate);
	}
	public static Date getDateTimeNow() throws Exception {
		String strDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.parse(strDate);
	}
	/**
	 * 获取timestamp类型的当前时间
	 */
	public static Timestamp getTimestampNow() throws Exception{
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	/**
	 * 将timestamp类型的当前时间转换为字符串
	 */
	public static String getTimestamp() throws Exception{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str=df.format(getTimestampNow());
		return str;
	}


	public static Timestamp getTimestampForLong(Long l) throws Exception{
		Date date = new Date(l);
		return new Timestamp(date.getTime());

	}


	/**
	 * 获得当前时间和当前时间前step秒时间
	 * @return
	 */
	public static Date getSecond(Date date,int step){
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = new GregorianCalendar();
		c.setTime(date);//设置参数时间
		c.add(Calendar.SECOND,-30);//把日期往后增加SECOND 秒.整数往后推,负数往前移动
		date=c.getTime(); //这个时间就是日期往后推一天的结果
		//String str = df.format(date);
		return date;
	}

	public static long getSecond(String str)throws Exception{
		Date date=DateUtil.getDateTime(str);
		Date date1=new Date();
		System.out.println("getSecond-date1"+date+"|"+date1.getTime()+"=="+"getSecond-date"+date1+"|"+date.getTime());
		long second=(date1.getTime()-date.getTime())/1000;

		return second;
	}
	/*手机短信日期*/
	public static String getPhoneDate() {
		return new SimpleDateFormat("dd").format(new Date());
	}

	public static Date getAutoDate(String str) throws Exception {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format1.parse(str);

	}

	public static Date getDate2(String str) throws Exception {
		DateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
		return format1.parse(str);

	}
	public static String getloanNumberDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public static String getNowDate() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdFormat1 = new SimpleDateFormat("yyyy/MM/dd");
		return (String) sdFormat1.format(cl.getTime());

	}
	//系统常用格式
	public static String getNowDate2() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		return (String) sdFormat1.format(cl.getTime());

	}


	public static String getNowTime() {
		Calendar c2 = Calendar.getInstance();
		SimpleDateFormat sdFormat2 = new SimpleDateFormat("HH:mm:ss");
		return sdFormat2.format(c2.getTime());

	}

	public static String getAutoNowTime() {
		Calendar c2 = Calendar.getInstance();
		SimpleDateFormat sdFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdFormat2.format(c2.getTime());

	}

	public static int getmonth() {
		GregorianCalendar g = new GregorianCalendar();
		return (int) g.get(Calendar.MONTH) + 1;

	}

	public static int getyear() {
		GregorianCalendar g = new GregorianCalendar();
		return (int) g.get(Calendar.YEAR);

	}
	public static String getYear(String date) throws ParseException{
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		return new SimpleDateFormat("yyyy").format(sm.parse(date));
	}

	public static String getYear(Date date){
		return new SimpleDateFormat("yyyy").format(date);
	}

	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	public static String getDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static String getDate(Date dateTime) {
		return new SimpleDateFormat("yyyy-MM-dd").format(dateTime);
	}
	public static String getDateTime(Date dateTime) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
	}
	public static String getDayForDate(Date dateTime) {
		return new SimpleDateFormat("dd").format(dateTime);
	}

	public static String getDateAcctInfo(Date dateTime) {
		return new SimpleDateFormat("yyMMdd").format(dateTime);
	}
	public static String getPrintDate(Date dateTime){

		return new SimpleDateFormat("yyyyMMdd").format(dateTime);
	}
	public static String getAtuoDate(Date dateTime) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTime);
	}

	// 2个日期之间的差 算头不算尾 当 开始日期不能晚于结束日期 有异常抛出
	public static int getDateWithLimit(Date beginDate, Date endDate) throws Exception {
		if (!beginDate.after(endDate)) {
			return (int) Math.abs((endDate.getTime() - beginDate.getTime())
					/ (24 * 60 * 60 * 1000));
		}
		else {
			throw new Exception("开始日期不能晚于结束日期!");
		}

	}

	// 2个日期之间的差 算头不算尾 当 开始日期不能晚于结束日期 返回0
	public static int getDate(Date beginDate, Date endDate) throws Exception {
		if (!beginDate.after(endDate)) {
			return (int) Math.abs((endDate.getTime() - beginDate.getTime())
					/ (24 * 60 * 60 * 1000));
		}
		else {
			 return  0;
		}

	}

	public static Long getDate(Date dDate, int nsetp) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dDate);
		cal.add(Calendar.DAY_OF_MONTH, nsetp);
		return cal.getTime().getTime();
	}

	// 计算 输入的一个日期到 1899-12-31相差的天数
	public long getDateForInt(String begin) {
		try {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");

			String old = "1899-12-31";

			Date d1 = sim.parse(old);
			Date d2 = sim.parse(begin);

			return ((d2.getTime() - d1.getTime()) / (3600L * 1000 * 24));
		} catch (Exception e) {
			return -1;
		}
	}

	// 计算1899-12-31 + 给定天数 得出 日期
	public Date getDate(int conut) {
		try {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			String old = "1899-12-31";
			Date d1 = sim.parse(old);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(d1);
			calendar.add(Calendar.DATE, conut);
			Date d2 = calendar.getTime();
			return d2;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 得到几天后的日期
	 *
	 * @param d 当前时间
	 * @param day 天数
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}


	/**
	 * 得到几天前的日期
	 *
	 * @param d 当前日期
	 * @param day 天数
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}
	//判断今天是一周的第几天 返回int 1(周一) 2(周二) 3(周三) 4 5 6 0(星期天)
	public static int  getDayForWeek(String temDateString) throws Exception {
		Date dt=getDate(temDateString);
		Calendar cal = Calendar.getInstance();
		//Locale.setDefault(Locale.CHINA);
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return w;
	}


	//判断当日日期是不是 贷款开始日的放款日 是返回true 错返回false
	public static boolean isLendDDay(Date ledgerBeginDatde,Date curDate) {

		String sdayString=DateUtil.getDayForDate(ledgerBeginDatde);
		Boolean bl=DateUtil.isLastOfMonth(curDate);
		String   scurDay=DateUtil.getDayForDate(curDate);


		if (sdayString.equalsIgnoreCase(scurDay)) {
			return true;

		}
		if (bl==true) {
			if (Integer.valueOf(sdayString)>Integer.valueOf(scurDay)) {
				return true;
			}
		}
		return false;
	}

	//取上年同期
	public static String getNowOfLastYear(Date d) {
		SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar aGregorianCalendar = new GregorianCalendar();
		aGregorianCalendar.setTime(d);
		aGregorianCalendar.set(Calendar.YEAR, aGregorianCalendar
				.get(Calendar.YEAR) - 1);
		String currentYearAndMonth = aSimpleDateFormat
				.format(aGregorianCalendar.getTime());
		return currentYearAndMonth;
	}
	//取上月同期
	public static String getNowOfLastMonth(Date d) {
		SimpleDateFormat aSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar aGregorianCalendar = new GregorianCalendar();
		aGregorianCalendar.setTime(d);
		aGregorianCalendar.set(Calendar.MONTH, aGregorianCalendar
				.get(Calendar.MONTH) - 1);
		String currentYearAndMonth = aSimpleDateFormat
				.format(aGregorianCalendar.getTime());
		return currentYearAndMonth;
	}
	//取本年天数
	public static int getDaysOfYear(){
		Calendar c = GregorianCalendar.getInstance();
		return c.getActualMaximum(Calendar.DAY_OF_YEAR);
	}
	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static Date now() {
		return new Date();
	}
	/**
	 * 获取指定时间的那天 00:00:00.000 的时间
	 *
	 * @param date
	 * @return
	 */
	public static Date dayBegin(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 获取今天 00:00:00.000 的时间
	 *
	 * @param
	 * @return
	 */
	public static Date dayBegin() {
		return dayBegin(now());
	}

	/**
	 * 获取指定时间的那天 23:59:59.999 的时间
	 *
	 * @param date
	 * @return
	 */
	public static Date dayEnd(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 获取今天 23:59:59.999 的时间
	 *
	 * @param
	 * @return
	 */
	public static Date dayEnd() {
		return dayEnd(now());
	}


	/**
	 * 是否是指定日期
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static boolean isTheDay(final Date date, final Date day) {
		return date.getTime() >= dayBegin(day).getTime()
				&& date.getTime() <= dayEnd(day).getTime();
	}
	/**
	 * 是否小于等于当前日期
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static boolean lessOrEqualsDay(final Date date, final Date day) {
		return date.getTime() >= dayBegin(day).getTime()
				&& date.getTime() <= dayEnd(day).getTime();
	}
	/**
	 * 是否是今天
	 *
	 * @param date
	 * @return
	 */
	public static boolean isToday(final Date date) {
		return isTheDay(date, now());
	}



}
