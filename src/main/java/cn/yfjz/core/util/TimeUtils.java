package cn.yfjz.core.util;

import java.util.Date;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeUtils {

	public static final String PATTERN_DEFAULT_DAYTIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DEFAULT_DAY = "yyyy-MM-dd";
	/**
	 * DateTimeFormatter 是线程安全的
	 */
	public static final DateTimeFormatter DEFAULT_FMT_DAYTIME = DateTimeFormat.forPattern(PATTERN_DEFAULT_DAYTIME);
	public static final DateTimeFormatter DEFAULT_FMT_DAY = DateTimeFormat.forPattern(PATTERN_DEFAULT_DAY);
	
	
	public static String getStrByDefaultDayPattern(Date date){
		return DEFAULT_FMT_DAY.print(date.getTime());
	}
	
	public static String getStrByDefaultDayPattern(long longtime){
		return DEFAULT_FMT_DAY.print(longtime);
	}
	
	public static String getStrByDefaultDaytimePattern(Date date){
		return DEFAULT_FMT_DAYTIME.print(date.getTime());
	}
	
	public static String getStrByDefaultDaytimePattern(long longtime){
		return DEFAULT_FMT_DAYTIME.print(longtime);
	}
	
	public static String getStrByPattern(long longtime,String pattern){
		return DateTimeFormat.forPattern(pattern).print(longtime);
	}
	
	private static String getStrByFormatter(long longtime,DateTimeFormatter formatter){
		return formatter.print(longtime);
	}
	
	public static String getStrByDefaultDayPattern(Object date){
		return getStrByFormatter(date,DEFAULT_FMT_DAY);
	}
	
	public static String getStrByDefaultDaytimePattern(Object date){
		return getStrByFormatter(date,DEFAULT_FMT_DAYTIME);
	}
	
	/**
	 * 转换日期为字符串
	 * 适合java.util.Date , java.sql.Date , java.sql.Timestamp , oracle.sql.TIMESTAMP
	 */
	private static String getStrByFormatter(Object date,DateTimeFormatter formatter){
		String result = null;
		if(null != date){
			try{
				if(null != Reflections.getAccessibleMethodByName(date,"getTime")){
					// java.util.Date , java.sql.Date , java.sql.Timestamp
					long longTime = (Long)Reflections.invokeMethodByName(date, "getTime", null);
					result = getStrByFormatter(longTime,formatter);
				}else if(null != Reflections.getAccessibleMethodByName(date,"timestampValue")){
					// oracle.sql.TIMESTAMP
					return getStrByFormatter(Reflections.invokeMethodByName(date, "timestampValue", null),formatter);
				}else{
					result = date.toString();
				}
				
			}catch(Exception e){
				result = date.toString();
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
//		Date d = new Date();
//		TIMESTAMP ts = new TIMESTAMP(new java.sql.Timestamp(d.getTime()));
//		String str = getStrByDefaultDaytimePattern(ts);
//		System.out.println(str);
	}

}
