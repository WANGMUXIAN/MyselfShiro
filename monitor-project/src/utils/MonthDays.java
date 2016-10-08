package utils;
   
public class MonthDays {  
  
    /*public static void main(String[] args) {  
    // 定义年份  
    int year = 2000;  
    // 定义月份  
    int month = 2;  
 
  
    System.out.println(year + "年" + month + "月有" + days(year, month) + "天");  
  
    }*/  
  
    public static int days(int year, int month) {  
    int days = 0;  
    if (month != 2) {  
        switch (month) {  
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
  
        }  
    } else {  
        //闰年  
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)  
        days = 29;  
        else  
        days = 28;  
  
    }  
    return days;  
  
    }  
  
}  