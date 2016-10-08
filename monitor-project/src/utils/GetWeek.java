package utils;

import java.util.Calendar;
import java.util.Date;

public class GetWeek {

	public static int getWeek(Date date){    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int weeknum = cal.get(Calendar.DAY_OF_WEEK) - 1;  
        return weeknum%7; 
    }
//	public static void main(String[] args){
//		System.out.println(getWeek(new Date()));
//	}
}
