package com.test.until;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.github.pagehelper.StringUtil;


public class DateUtil {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	private final static SimpleDateFormat sdfYMDTime = new SimpleDateFormat(
			"yyyyMMdd");

	/**
	 * 
	 * @Title: getCurrentTime 
	 * @Description: 获取Timestamp类型的当前时间
	 * @return
	 * @return: Timestamp
	 */
	public static Timestamp getCurrentTime() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}
	
	/**
	 * DateUtil.getYMDTime()<BR>
	 * <P>Author :  zhaoyan  </P>  
	 * <P>Date : 2017年5月4日上午10:06:24</P>
	 * <P>Desc : 当前时间转化为 yyyyMMdd 字符串并返回  </P>
	 * @return
	 */
	public static String getYMDTime(){
		return sdfYMDTime.format(new Date());
	}
	
	
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	
	 /**
     * 获取上月1号
     */
	public static String getLastMonthByFormat(String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为上月的1号
		return sdf.format(lastDate.getTime());
	}
	

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}
	
	
	
	/**
	 * DateUtil.compare_date()<BR>
	 * <P>Author :  zhaoyan  </P>  
	 * <P>Date : 2017年6月26日下午6:48:36</P>
	 * <P>Desc : TODO </P>
	 * @return  1:DATE1>DATE2   
	 * @return1 0:DATE1<DATE2    
	 * @return2 -1:DATA1=DATE2
	 * @return
	 */
	public static int compare_date(String DATE1, String DATE2) {
		int result = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				result = 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2前");
				result = 0;
			}else {
				result = -1;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * DateUtil.fomatTime()<BR>
	 * <P>Author :  张天德 </P>  
	 * <P>Date : 2016年7月6日上午10:22:52</P>
	 * <P>Desc :字符串格式化日期 时分秒</P>
	 * @param date
	 * @return
	 */
	public static Date fomatTime(String date) {
		try {
			return sdfTime.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    public static String getAfterMMddDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("MMdd");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    
    
    public static String getFeatureDateByStr(String dateStr,String days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.setTime(date);
        canlendar.add(Calendar.HOUR, daysInt); // 日期减 如果不够减会将月变动
        
        String result = sdf.format(canlendar.getTime());
        
        return result;
    }
    
    public static String getAfterMMddStrDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("MM月dd日");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    
    public static String getAfterDayDate(String days,String str) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdfd.format(date).concat(StringUtil.isEmpty(str)?"":str);
        return dateStr;
    }
    
    
     
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    /**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * long 类型的字符串转化为 yyyy-MM-dd HH:mm:ss 形式字符串
	 * @Title: date2LongStr 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String date2LongStr(String str) {
		Long l=Long.parseLong(str);
		return date2Str(new Date(l),"yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
/*	public static Date str2Date(String date){
		if(StringUtil.notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}*/
	/**
	 * DateUtil.long2Date()<BR>
	 * <P>Author :  zhangyq </P>  
	 * <P>Date : 2015年10月6日下午4:32:14</P>
	 * <P>Desc : long 类型转换为Date </P>
	 * @param time
	 * @return
	 */
	public static Date long2Date(long time){
		if (time <= 0 ) {
			time = System.currentTimeMillis();
		}
		Date date = new Date(time);
		
		return date;
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("发表于：");
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	
	
	
	
	
	/** 
     * 得到本月第一天的日期 
     * @Methods Name getLastDayOfMonth 
     * @return Date 
     * 张宝文 2015-12-4
     */  
	public static Date getFirstDayOfMonth(Date date)   {     
        Calendar cDay = Calendar.getInstance();    
        cDay.setTime(date);  
        cDay.set(Calendar.DAY_OF_MONTH, 1);  
        //System.out.println(cDay.getTime());  
        return cDay.getTime();     
    }     
	
	
	/** 
     * 得到本月最后一天的日期 
     * @Methods Name getLastDayOfMonth 
     * @return Date 
     * 张宝文 2015-12-4
     */  
    public static Date getLastDayOfMonth(Date date)   {     
        Calendar cDay = Calendar.getInstance();     
        cDay.setTime(date);  
        cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMaximum(Calendar.DAY_OF_MONTH));  
        //System.out.println(cDay.getTime());  
        return cDay.getTime();     
    }  
    
    
    public static void main(String[] args) {
		System.out.println(getAfterMMddStrDate("-14"));
	}
    
}
