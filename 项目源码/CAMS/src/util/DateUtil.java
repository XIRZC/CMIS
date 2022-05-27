package util;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateUtil {
	private static Calendar calendar = Calendar.getInstance();
	
	private static int getstartweek_yearday() {
		calendar.set(2021, 3-1 , 1);
		Date startweekday=calendar.getTime();
		int yeardayno=calendar.get(Calendar.DAY_OF_YEAR);
		Date today=new Date();
		calendar.setTime(today);
		return yeardayno;
	}
	
	public static int getweekno() {  //获取当前日期在校历中为第几周
		int daystart=getstartweek_yearday();
		//System.out.println("9-14 yeardayno:"+daystart);
		
		int daynow=getyeardayno();
		//System.out.println("今天 yeardayno:"+daynow);
		return (daynow-daystart)/7+1;
	}
    
	public static int getyear() {
        //获取当前年
        return calendar.get(Calendar.YEAR);  
    }
	public static int getmonth() {
		 // 获取当前月
	    return calendar.get(Calendar.MONTH) + 1;  
	}
	public static int getday() {
		// 获取当前日
	    return calendar.get(Calendar.DATE);  
	}
	public static int gethour() {
		// 获取当前小时
	    return calendar.get(Calendar.HOUR_OF_DAY); 
	}
	public static int getminute() {
		// 获取当前分钟
	    return calendar.get(Calendar.MINUTE);  
	}
	public static int getsecond() {
		// 获取当前秒
	    return calendar.get(Calendar.SECOND);  
	}
	public static int getweekdayno() {
		// 获取当前是本周第几天
		int map[]= { 0,7,1,2,3,4,5,6 }; //周日到周六代表1-7
	    return map[calendar.get(Calendar.DAY_OF_WEEK)];
	}
	public static int getmonthdayno() {
		// 获取当前是本月第几天
	    return calendar.get(Calendar.DAY_OF_MONTH); 
	}
	public static int getyeardayno() {
		// 获取当前是本年第几天
	    return calendar.get(Calendar.DAY_OF_YEAR);
	}
}
/*注：格式化字符串存在区分大小写

对于创建SimpleDateFormat传入的参数：EEEE代表星期，如“星期四”；MMMM代表中文月份，如“十一月”；MM代表月份，如“11”；

yyyy代表年份，如“2010”；dd代表天，如“25”*/