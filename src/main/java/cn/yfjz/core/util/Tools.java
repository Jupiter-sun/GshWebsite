package cn.yfjz.core.util;

import cn.yfjz.core.exception.WebException;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

	public final static String DATA_FORMAT = "yyyy-MM-dd";

	/**
	 * 判断当前日期是否处于两个日期之间
	 * 	<li>如果设置开始日期和结束日期，即参数都为空，则返回true
	 * 	<li>如果只设置开始日期，只要当前日期在开始日期之后(包括开始日期当天)则返回true
	 * 	<li>如果只设置结束日期，只要当前日期在结束日期之前(包括结束日期当天)则返回true
	 * 	<li>如果开始和结束日期都设置，只要当前日期在这两个日期之间（包括开始和结束日期两天）则返回true
	 * @param kssj 开始日期
	 * @param jssj 结束日期
	 * @return
	 */
	public static boolean compareDate(String kssj, String jssj) throws ParseException {
		SimpleDateFormat sf;
		long cur_second;
		long ks_second;
		long js_second;
		//开始日期和结束日期都不为空
		if(!StringUtils.isBlank(kssj) && !StringUtils.isBlank(jssj)){
			sf= Tools.getDateFormat(kssj);
			cur_second = sf.parse(sf.format(new Date())).getTime();
			ks_second = sf.parse(kssj).getTime();
			js_second = sf.parse(jssj).getTime();
			if ( (cur_second < ks_second) || (cur_second > js_second) ) {
				return false;
			}else{
				return true;
			}
		}
		//开始时间不为空，结束日期为空
		if(!StringUtils.isBlank(kssj)){
			sf= Tools.getDateFormat(kssj);
			cur_second = sf.parse(sf.format(new Date())).getTime();
			ks_second = sf.parse(kssj).getTime();
			//当前日期在开始日期之前
			if(ks_second > cur_second){
				return false;
			}else{
				return true;
			}

		}
		//开始时间为空，结束时间不为空
		if(!StringUtils.isBlank(jssj)){
			sf= Tools.getDateFormat(jssj);
			cur_second = sf.parse(sf.format(new Date())).getTime();
			js_second = sf.parse(jssj).getTime();
			//当前日期在结束日期之后
			if(js_second < cur_second){
				return false;
			}else{
				return true;
			}
		}
		// 两者都为空
		return true;
	}

	public static boolean compareDate(Date kssj, Date jssj) throws ParseException {
		long cur_second = System.currentTimeMillis();
		long ks_second;
		long js_second;
		//开始日期和结束日期都不为空
		if(kssj!=null && jssj!=null){
			ks_second = kssj.getTime();
			js_second = jssj.getTime();
			if ( (cur_second < ks_second) || (cur_second > js_second) ) {
				return false;
			}else{
				return true;
			}
		}
		//开始时间不为空，结束日期为空
		if(kssj!=null){
			ks_second = kssj.getTime();
			//当前日期在开始日期之前
			if(ks_second > cur_second){
				return false;
			}else{
				return true;
			}

		}
		//开始时间为空，结束时间不为空
		if(jssj!=null){
			js_second = jssj.getTime();
			//当前日期在结束日期之后
			if(js_second < cur_second){
				return false;
			}else{
				return true;
			}
		}
		// 两者都为空
		return true;
	}

	/**
	 * 判断当前日期是否处于两个日期之间
	 * 	<li>如果设置开始日期和结束日期，即参数都为空，则返回true
	 * 	<li>如果只设置开始日期，只要当前日期在开始日期之后(包括开始日期当天)则返回true
	 * 	<li>如果只设置结束日期，只要当前日期在结束日期之前(包括结束日期当天)则返回true
	 * 	<li>如果开始和结束日期都设置，只要当前日期在这两个日期之间（包括开始和结束日期两天）则返回true
	 * @param kssj 开始日期
	 * @param jssj 结束日期
	 * @return
	 */
	public static String compareDateBet(String kssj, String jssj) throws ParseException {
		SimpleDateFormat sf;
		long cur_second;
		long ks_second;
		long js_second;
		//开始日期和结束日期都不为空
		if(!StringUtils.isBlank(kssj) && !StringUtils.isBlank(jssj)){
			sf= Tools.getDateFormat(kssj);
			cur_second = sf.parse(sf.format(new Date())).getTime();
			ks_second = sf.parse(kssj).getTime();
			js_second = sf.parse(jssj).getTime();
			if ( (cur_second < ks_second) || (cur_second > js_second) ) {
				return "before";// 时间未开始
			}else if ( cur_second > js_second ){
				return "after";// 时间已结束
			}else{
				return "yes";
			}
		}
		//开始时间不为空，结束日期为空
		if(!StringUtils.isBlank(kssj)){
			sf= Tools.getDateFormat(kssj);
			cur_second = sf.parse(sf.format(new Date())).getTime();
			ks_second = sf.parse(kssj).getTime();
			//当前日期在开始日期之前
			if(ks_second > cur_second){
				return "before";// 时间未开始
			}else{
				return "yes";
			}

		}
		//开始时间为空，结束时间不为空
		if(!StringUtils.isBlank(jssj)){
			sf= Tools.getDateFormat(jssj);
			cur_second = sf.parse(sf.format(new Date())).getTime();
			js_second = sf.parse(jssj).getTime();
			//当前日期在结束日期之后
			if(js_second < cur_second){
				return "after";// 时间已结束
			}else{
				return "yes";
			}
		}
		// 两者都为空
		return "yes";
	}

	/**
	 * 根据传入时间的格式返回不同的SimpleDateFormat(yyyyMMddHHmm/yyyy-MM-dd/yyyy-MM-dd HH:mm)
	 * @param time
	 * @return
	 */
	public static SimpleDateFormat getDateFormat(String time){
		SimpleDateFormat sf = null;
		if (time.length() == 6) {
			sf = new SimpleDateFormat("yyyyMM");
		}else if (time.length() == 7) {
			sf = new SimpleDateFormat("yyyy-MM");
		}else if (time.length() == 8) {
			sf = new SimpleDateFormat("yyyyMMdd");
		}else if (time.length() == 12) {
			sf = new SimpleDateFormat("yyyyMMddHHmm");
		} else if (time.length() == 10) {
			sf = new SimpleDateFormat("yyyy-MM-dd");
		} else if (time.length() == 16) {
			sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		} else if(time.length() == 19) {
			sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		if (sf == null)
			throw new WebException("时间格式不正确！");
		return sf;
	}
	/**
	 * @param time
	 * @param patt
	 * @return
	 * @throws ParseException
	 */
	public static String getDateFormat(String time, String patt) throws ParseException {
		SimpleDateFormat sdf = getDateFormat(time);
		Date d  =  sdf.parse(time);
		SimpleDateFormat p = new SimpleDateFormat(patt);
		return p.format(d);
	}

	/**
	 * 转换日期格式类型 将YYYYMM转换成YYYY-MM，将YYYYMMDD转换成YYYY-MM-DD
	 * @param date
	 * @return
	 */
	public static String transferDateFormat(String date){
		if(date.length()==6)
			return date.substring(0,4)+ "-" + date.substring(4,6);
		if (date.length()==8)
			return date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6);
		else
			return date;
	}

	public static boolean isInt(String str){
		try{
			Integer.parseInt(str);
			return true;
		}catch(NumberFormatException ex){
			return false;
		}
	}

	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMAT);
		return sdf.format(new Date());
	}

	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}


}
