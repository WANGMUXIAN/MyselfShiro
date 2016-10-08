package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;

public class CountDay {
//	public static void main(String[] args){
//		int a = countDay(2016, 3, 3);
//		System.out.println(a);
//	}
//	
	public static int countDay(int year,int month,int day) {
		int count = 0;
		int days = 0;
		if (year > 0 && month > 0 && month < 13 && day > 0 && day < 32) {
			for (int i = 1; i < month; i++) {
				switch (i) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					days = 31;
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					days = 30;
					break;
				case 2: {
					if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
						days = 29;
					} else {
						days = 28;
					}
					break;
				}
				}
				count = count + days;
			}
			count = count + day;
			return count;
		} else
			return (Integer) null;
	}	
	public static int orderDate(Date date){
		int dateSum = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = format.format(date);
		int year = Integer.valueOf(dateStr.substring(0,4));
		int month = Integer.valueOf(dateStr.substring(5,7));
		int day = Integer.valueOf(dateStr.substring(8,10));
		for (int i = 1; i < month; i++){
			switch(i){
			case 1: 
			case 3: 
			case 5: 
			case 7: 
			case 8: 
			case 10: 
			case 12:
				dateSum += 31; 
				break;
			case 4: 
			case 6: 
			case 9: 
			case 11:
				dateSum += 30; 
				break;
			case 2: 
				if(((year % 4 == 0) & (year % 100 != 0)) | (year % 400 == 0))
					dateSum += 29;
				else dateSum += 28;    
			}
		}
		return dateSum = dateSum + day;
	}
}


