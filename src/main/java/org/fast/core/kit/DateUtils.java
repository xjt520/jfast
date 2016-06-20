package org.fast.core.kit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jfinal.kit.StrKit;

/**
 * 日期工具类
 * 
 */
public class DateUtils {

	/**
	 * 日期格式化模式（日期类型数据）
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012-10-08”，一般用于日期类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String PATTERN_YYYY_MM_DD_SLASH = "yyyy/MM/dd";

    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
	/**
	 * 日期格式化模式（日期类型数据）
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“10-08”，一般用于日期类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_MM_DD = "MM-dd";
	/**
	 * 日期格式化模式（时间类型数据）
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012-10-08 10:10:08”，一般用于时间类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式化模式（时间类型数据），精确到分
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012-10-08 10:10”，一般用于时间类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	/**
	 * 日期格式化模式（时间类型数据），精确到时
	 * <p>
	 * 日期格式化模式，使用此模式将日期格式化为“2012-10-08 10”，一般用于时间类型数据格式化
	 * </p>
	 */
	public static final String PATTERN_YYYY_MM_DD_HH = "yyyy-MM-dd HH";

	public static final String PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	/**
	 * 其他时间值转换为毫秒单位：秒
	 */
	public static final long UNIT_SECOND_TIME = 1000;
	/**
	 * 其他时间值转换为毫秒单位：分钟
	 */
	public static final long UNIT_MINUS_TIME = 60 * UNIT_SECOND_TIME;
	/**
	 * 其他时间值转换为毫秒单位：小时
	 */
	public static final long UNIT_HOUR_TIME = 60 * UNIT_MINUS_TIME;
	/**
	 * 其他时间值转换为毫秒单位：天
	 */
	public static final long UNIT_DAY_TIME = 24 * UNIT_HOUR_TIME;
	/**
	 * 时间单位名称：秒
	 */
	public static final String UNIT_SECOND_NAME = "秒";
	/**
	 * 时间单位名称：分钟
	 */
	public static final String UNIT_MINUS_NAME = "分钟";
	/**
	 * 时间单位名称：小时
	 */
	public static final String UNIT_HOUR_NAME = "小时";
	/**
	 * 时间单位名称：天
	 */
	public static final String UNIT_DAY_NAME = "天";
	
	public static final String PATTERN_YYYY_MM_DD_E_HH_MM_SS = "yyyy-MM-dd(E) HH:mm:ss";


	/**
	 * 根据特定模式，将字符串型日期对象解析成Date对象
	 * 
	 * @param source
	 *            要解析的字符串
	 * @param pattern
	 *            解析模式，默认为{@value #PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @return 解析结果
	 * @throws ParseException
	 *             如果要解析的字符串格式不匹配，则抛出此异常
	 */
	public static Date parse(final String source, String pattern)
			throws ParseException {
		// 检查value是否为空
		if (StrKit.isBlank(source)) {
			return null;
		}
		// 如果pattern为空
		if (pattern == null) {
			// 设置pattern为PATTERN_YYY_MM_DD_HH_MM_SS
			pattern = PATTERN_YYYY_MM_DD_HH_MM_SS;
		}
		// 初始化一个format类
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		// 开始解析
		return format.parse(source);
	}

	/**
	 * 将日期对象根据特定格式格式化成字符串
	 * 
	 * @param source
	 *            要格式化的日期对象
	 * @param pattern
	 *            格式化模式，默认为{@value #PATTERN_YYYY_MM_DD_HH_MM_SS}
	 * @return 格式化后的字符串
	 */
	public static String format(final Date source, String pattern) {
		// 检查value是否为空
		if (source == null) {
			return null;
		}
		// 如果pattern为空
		if (pattern == null) {
			// 设置pattern为PATTERN_YYYY_MM_DD_HH_MM_SS
			pattern = PATTERN_YYYY_MM_DD_HH_MM_SS;
		}
		// 初始化一个format类
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(source);
	}

	/**
	 * 格式化到天
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToDay(final Date source) {

		return format(source, PATTERN_YYYY_MM_DD);
	}

	/**
	 * 格式化日期到秒
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToSecond(final Date source) {
		return format(source, PATTERN_YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * 格式化日期到分钟
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToMinute(final Date source) {
		return format(source, PATTERN_YYYY_MM_DD_HH_MM);
	}

	/**
	 * 格式化日期到小时
	 * 
	 * @param source
	 * @return
	 */
	public static String formatToHour(final Date source) {
		return format(source, PATTERN_YYYY_MM_DD_HH);
	}


	/**
	 * 获取日期相对于1970/01/01的时间戳
	 * 
	 * @param source
	 *            要比较的时间
	 * @return 时间戳
	 */
	public static long getTime(final Date source) {
		if (source == null) {
			return -1;
		}
		return source.getTime();
	}

	/**
	 * 获取在某天的基础上增加或减少N天
	 * 
	 * @param date
	 *            某天
	 * @param num
	 *            加上或减少的天数，正数为加，负数为减。
	 * @return
	 */
	public static Date getDateAfterDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, days);
		return c.getTime();
	}

	/**
	 * 获取今天是今年中的第几天
	 * 
	 * @return
	 * @author ZhangYunHe
	 */
	public static int getTodayOfYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取指定日期是今年中的第几天
	 * 
	 * @param dateStr
	 *            日期字符串 格式：yyyy-MM-dd
	 * @return 指定日期是今年中的第几天
	 * @note 请不要输入2012-02-31、2012-13-32之类的非法日期字符串
	 */
	public static int getDayOfYear(String dateStr) {
		Calendar calendar = initCalendarByDateString(dateStr);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 使用yyyy-MM-dd格式的日期字符串实例化一个Calendar对象
	 * 
	 * @param dateStr
	 *            日期字符串 格式：yyyy-MM-dd
	 * @return
	 * @author ZhangYunHe
	 */
	public static Calendar initCalendarByDateString(String dateStr) {
		if (null != dateStr && !"".equals(dateStr.trim())
				&& dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
			String[] date = dateStr.split("-");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
			calendar.set(Calendar.MONTH, Integer.parseInt(date[1]) - 1);// 月份需要减1，Calendar从0开始算1月
			calendar.set(Calendar.DATE, Integer.parseInt(date[2]));
			return calendar;
		} else {
			throw new IllegalArgumentException(
					"传入的日期格式字符串非法！接受的日期格式字符串为：yyyy-MM-dd");
		}
	}

	/**
	 * 在当前时间的基础上添加特定时间
	 * <p>
	 * 根据type类型来区分添加的时间单位，type等同于Calendar.XXXX 如：小时Calendar.HOUR_OF_DAY。<br/>
	 * 暂只支持，DAY_OF_MONTH,HOUR_OF_DAY，MINUTE
	 * </p>
	 * 
	 * @param type
	 *            类型
	 * @param value
	 *            要新增的值（如果是负数则是减少的值）
	 * @return 处理后的时间
	 */
	public static Date addByToday(int type, int value) {
		return addDate(new Date(), type, value);
	}

	/**
	 * 在特定时间的基础上添加特定时间
	 * <p>
	 * 根据type类型来区分添加的时间单位，type等同于Calendar.XXXX 如：小时Calendar.HOUR_OF_DAY。<br/>
	 * 暂只支持，DAY_OF_MONTH,HOUR_OF_DAY，MINUTE
	 * </p>
	 * 
	 * @param date
	 *            特定时间
	 * @param type
	 *            类型
	 * @param value
	 *            要新增的值（如果是负数则是减少的值）
	 * @return 处理后的时间
	 */
	public static Date addDate(Date date, int type, int value) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.set(type, instance.get(type) + value);
		return instance.getTime();
	}

	/**
	 * 获取今天的日期
	 * 
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 */
	public static Date getNow() {
		try {
			return getToday(PATTERN_YYYY_MM_DD_HH_MM_SS);
		} catch (ParseException e) {
//			log.debug("格式化日期发送错误！", e);
			return null;
		}
	}

	/**
	 * 获取当前日期
	 * 
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date getToday(String pattern) throws ParseException {
		String sToday = format(new Date(), pattern);
		return parse(sToday, pattern);
	}

	/**
	 * 获取当天的最后一秒
	 * 
	 * @return
	 */
	public static String getTodayLastSecond() {
		String sToday = format(new Date(), PATTERN_YYYY_MM_DD);
		return sToday + " 23:59:59";
	}

	/**
	 * 获取当天的第一秒
	 * 
	 * @return
	 */
	public static String getTodayFirstSecond() {
		String sToday = format(new Date(), PATTERN_YYYY_MM_DD);
		return sToday + " 00:00:00";
	}

	/**
	 * 根据日期取得星期几
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

    /**
     * used to compare the origin date with target.<br>
     * if less than return -1; equal return 0; greater than return 1;
     * @param origin
     * @param target
     * @param interval
     * @return
     */
//    @SuppressWarnings("deprecation")
//	public static int dayCompare(Date origin, Date target, int interval){
//        JDateTime oriJdt = new JDateTime(origin.getYear(), origin.getMonth(), origin.getDay());
//        JDateTime targetJdt = new JDateTime(target.getYear(), target.getMonth(), target.getDay());
//        Period period = new Period(targetJdt, oriJdt);
//        if (period.getDays() < interval){
//            return -1;
//        } else if(period.getDays() == interval){
//            return 0;
//        } else {
//            return 1;
//        }
//    }

    /**
	* 得到二个日期间的间隔天数
	*/
	public static String getTwoDay(String end, String start) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(end);
			java.util.Date mydate = myFormatter.parse(start);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}
	
	/**
	* 得到二个日期间的所有日期
	*/
	public static String[] getTwoDayAll(String end, String start) {
		int day = Integer.parseInt(getTwoDay(end, start));
		Date startDate;
		try {
			startDate = DateUtils.parse(start, DateUtils.PATTERN_YYYY_MM_DD);
		} catch (ParseException e) {
			startDate = new Date();
		}
		String[] all = new String[day];
		for (int i = 0; i < day; i++) {
			String cutStr = DateUtils.formatToDay(getDateAfterDays(startDate,i));
			all[i]=cutStr;
		}
		return all;
	}
}
