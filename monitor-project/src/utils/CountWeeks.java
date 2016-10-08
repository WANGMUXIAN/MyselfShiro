package utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date ;
import java.util.Date ;
//得到两个日期相隔的周数
public class CountWeeks {

	 public static long getWeekSub(String beginDateStr,String endDateStr)
	    {
	        long weeks=0;
	        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");   
	        try
	        {
	            Date beginDate = format.parse(beginDateStr);
	            Date endDate= format.parse(endDateStr);   
	            weeks=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000)/7;   
	            //System.out.println("相隔的天数="+day);  
	        } catch (ParseException e)
	        {
	            // TODO 自动生成 catch 块
	            e.printStackTrace();
	        }  
	        return weeks;
	    }
}
