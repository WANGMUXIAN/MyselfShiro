package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CountDay;
import utils.CountWeeks;
import utils.GetWeek;
import utils.ParseXML;

public class SendFastServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LinkedList<String> listfasp3 = new LinkedList<String>();  //保存上传成功的快速轨道产品
		LinkedList<String> listfaerp = new LinkedList<String>();  //保存上传成功的快速地球自转参数文件
		LinkedList<String> listfaclk = new LinkedList<String>();  //保存上传成功的快速钟差文件
		LinkedList<String> listfaion = new LinkedList<String>();  //保存上传成功的快速电离层文件
		LinkedList<String> finallylistfasp3 = new LinkedList<String>();  //保存最终上传成功的快速轨道产品
		LinkedList<String> finallylistfaerp = new LinkedList<String>();  //保存最终上传成功的快速地球自转参数文件
		LinkedList<String> finallylistfaclk = new LinkedList<String>();  //保存最终上传成功的快速钟差文件
		LinkedList<String> finallylistfaion = new LinkedList<String>();  //保存最终上传成功的快速电离层文件
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int days = CountDay.countDay(year, month, date);//得到日期为该年内的第几天
		int lowyear = year-2000;    //例如若年份为2015年，则用15表示年份		
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //表示今天距1980.1.6为第几周
		int weeknum = GetWeek.getWeek(new Date());    //表示今天为这一周的第几天，周日为第零天
		request.setAttribute("weeks", weeks);
		request.setAttribute("weeknum", weeknum);
		request.setAttribute("hour", hour);
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} //解析配置文件，确定读取索要读取文件夹的路径
		String path = filepath[11];
		String finallyPath = filepath[16];
		if(hour>=0&hour<13){
			String week = null;
			weeknum = weeknum-2;
			days = days-2;
			if(weeknum<0){
				weeknum = weeknum+7;
				weeks = weeks-1;
			}
			if(days<=0)
				lowyear = lowyear-1;
			if(weeks<1000)
				week = "0"+weeks;
			String logPath = path+week+"_Sended.log";
			String finallyLogPath = finallyPath+week+"_Sended.log";
			String encoding="GBK";
	        File file=new File(logPath);
	        File finallyFile = new File(finallyLogPath);
	        if(file.isFile() && file.exists()){ //判断文件是否存在
	        	InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file),encoding);          //考虑到编码格式
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("SP3")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			listfasp3.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("CLK")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			listfaclk.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("ERP")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			listfaerp.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("I")){
	            		String strdays = null;
	            		if(days<10)
	            			strdays = "00"+days;
	            		if(10<=days&days<100)
	            			strdays = "0"+days;
	            		if(lineTxt.substring(4, 8).equals(strdays+"0")&lineTxt.substring(9, 11).equals(lowyear+""))
	            			listfaion.add(lineTxt);
	            	}
	            }
	        }
	        if(finallyFile.isFile() && finallyFile.exists()){ //判断文件是否存在
	        	InputStreamReader read = new InputStreamReader(
	            new FileInputStream(finallyFile),encoding);          //考虑到编码格式
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("SP3")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			finallylistfasp3.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("CLK")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			finallylistfaclk.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("ERP")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			finallylistfaerp.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("I")){
	            		String strdays = null;
	            		if(days<10)
	            			strdays = "00"+days;
	            		if(10<=days&days<100)
	            			strdays = "0"+days;
	            		if(lineTxt.substring(4, 8).equals(strdays+"0")&lineTxt.substring(9, 11).equals(lowyear+""))
	            			finallylistfaion.add(lineTxt);
	            	}
	            }
	        }
		}
		if(hour>=13&hour<18){
			int weeknum1 = weeknum-1;
			long weeks1 = weeks;
			if(weeknum1<0){
				weeknum1 = weeknum1+7;
				weeks1 = weeks1-1;
			}
			String week1 = null;
			if(weeks1<1000)
				week1 = "0"+weeks1;
			//System.out.println(weeks1);
			//System.out.println(weeknum1);
			String logPath1 = path+week1+"_Sended.log";
			String finallyLogPath1 = finallyPath+week1+"_Sended.log";
			String encoding="GBK";			
	        File file1 = new File(logPath1);
	        File fianllyFile1 = new File(finallyLogPath1);
	        if(file1.isFile() && file1.exists()){
	        	InputStreamReader read = new InputStreamReader(
	    	    new FileInputStream(file1),encoding);          //考虑到编码格式
	    	    BufferedReader bufferedReader = new BufferedReader(read);
	    	    String lineTxt1 = null;
	    	    while((lineTxt1 = bufferedReader.readLine()) != null){
	    	    	//System.out.println(lineTxt1);
	    	    	if(lineTxt1.toUpperCase().contains("NTR")&lineTxt1.toUpperCase().contains("SP3")){
	            		if(lineTxt1.substring(3, 7).equals(week1)&lineTxt1.substring(7, 8).equals(weeknum1+""))
	            			listfasp3.add(lineTxt1);
	            	}
	            	if(lineTxt1.toUpperCase().contains("NTR")&lineTxt1.toUpperCase().contains("CLK")){
	            		if(lineTxt1.substring(3, 7).equals(week1)&lineTxt1.substring(7, 8).equals(weeknum1+""))
	            			listfaclk.add(lineTxt1);
	            	}
	            	if(lineTxt1.toUpperCase().contains("NTR")&lineTxt1.toUpperCase().contains("ERP")){
	            		if(lineTxt1.substring(3, 7).equals(week1)&lineTxt1.substring(7, 8).equals(weeknum1+""))
	            			listfaerp.add(lineTxt1);
	            	}
	    	    }  
	        }
	        if(fianllyFile1.isFile() && fianllyFile1.exists()){
	        	InputStreamReader read = new InputStreamReader(
	    	    new FileInputStream(fianllyFile1),encoding);          //考虑到编码格式
	    	    BufferedReader bufferedReader = new BufferedReader(read);
	    	    String lineTxt1 = null;
	    	    while((lineTxt1 = bufferedReader.readLine()) != null){
	    	    	//System.out.println(lineTxt1);
	    	    	if(lineTxt1.toUpperCase().contains("NTR")&lineTxt1.toUpperCase().contains("SP3")){
	            		if(lineTxt1.substring(3, 7).equals(week1)&lineTxt1.substring(7, 8).equals(weeknum1+""))
	            			finallylistfasp3.add(lineTxt1);
	            	}
	            	if(lineTxt1.toUpperCase().contains("NTR")&lineTxt1.toUpperCase().contains("CLK")){
	            		if(lineTxt1.substring(3, 7).equals(week1)&lineTxt1.substring(7, 8).equals(weeknum1+""))
	            			finallylistfaclk.add(lineTxt1);
	            	}
	            	if(lineTxt1.toUpperCase().contains("NTR")&lineTxt1.toUpperCase().contains("ERP")){
	            		if(lineTxt1.substring(3, 7).equals(week1)&lineTxt1.substring(7, 8).equals(weeknum1+""))
	            			finallylistfaerp.add(lineTxt1);
	            	}
	    	    }  
	        }
	        int weeknum2 = weeknum-2;
	        days = days-2;
			long weeks2 = weeks;
			if(weeknum2<0){
				weeknum2 = weeknum2+7;
				weeks2 = weeks2-1;
			}
			if(days<=0)
				lowyear = lowyear-1;
			String week2 = null;
			if(weeks2<1000)
				week2 = "0"+weeks2;
			String logPath2 = path+week2+"_Sended.log";
			String finallyLogPath2 = finallyPath+week2+"_Sended.log";
	        File file2=new File(logPath2);
	        File finallyFile2 = new File(finallyLogPath2);
	        if(file2.isFile() && file2.exists()){
	        	InputStreamReader read = new InputStreamReader(
	    	    new FileInputStream(file2),encoding);          //考虑到编码格式
	    	    BufferedReader bufferedReader = new BufferedReader(read);
	    	    String lineTxt2 = null;
	    	    while((lineTxt2 = bufferedReader.readLine()) != null){
	    	    	if(lineTxt2.toUpperCase().contains("NTR")&lineTxt2.toUpperCase().contains("I")){
	    	    		String strdays = null;
	            		if(days<10)
	            			strdays = "00"+days;
	            		if(10<=days&days<100)
	            			strdays = "0"+days;
	            		if(lineTxt2.substring(4, 8).equals(strdays+"0")&lineTxt2.substring(9, 11).equals(lowyear+""))
	            			listfaion.add(lineTxt2);
	            	}
	    	    }
	        }
	        if(finallyFile2.isFile() && finallyFile2.exists()){
	        	InputStreamReader read = new InputStreamReader(
	    	    new FileInputStream(finallyFile2),encoding);          //考虑到编码格式
	    	    BufferedReader bufferedReader = new BufferedReader(read);
	    	    String lineTxt2 = null;
	    	    while((lineTxt2 = bufferedReader.readLine()) != null){
	    	    	if(lineTxt2.toUpperCase().contains("NTR")&lineTxt2.toUpperCase().contains("I")){
	    	    		String strdays = null;
	            		if(days<10)
	            			strdays = "00"+days;
	            		if(10<=days&days<100)
	            			strdays = "0"+days;
	    	    		if(lineTxt2.substring(4, 8).equals(strdays+"0")&lineTxt2.substring(9, 11).equals(lowyear+""))
	            			finallylistfaion.add(lineTxt2);
	            	}
	    	    }
	        }
		}
		if(hour>=18){
			weeknum = weeknum-1;
			days = days-1;
			if(weeknum<0){
				weeknum = weeknum+7;
				weeks = weeks-1;
			}
			if(days<=0)
				lowyear = lowyear-1;
			String week = null;
			if(weeks<1000)
				week = "0"+weeks;
//			System.out.println(week);
//			System.out.println(weeknum);
			String logPath = path+week+"_Sended.log";
			String finallyLogPath = finallyPath+week+"_Sended.log";
			String encoding="GBK";
	        File file=new File(logPath);
	        File finallyFile = new File(finallyLogPath);
	        if(file.isFile() && file.exists()){ //判断文件是否存在
	        	InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file),encoding);          //考虑到编码格式
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("SP3")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			listfasp3.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("CLK")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			listfaclk.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("ERP")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			listfaerp.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("I")){
	            		String strdays = null;
	            		if(days<10)
	            			strdays = "00"+days;
	            		if(10<=days&days<100)
	            			strdays = "0"+days;
	            		if(lineTxt.substring(4, 8).equals(strdays+"0")&lineTxt.substring(9, 11).equals(lowyear+""))
	            			listfaion.add(lineTxt);
	            	}
	            }
	        }
	        if(finallyFile.isFile() && finallyFile.exists()){ //判断文件是否存在
	        	InputStreamReader read = new InputStreamReader(
	            new FileInputStream(finallyFile),encoding);          //考虑到编码格式
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("SP3")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			finallylistfasp3.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("CLK")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			finallylistfaclk.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("ERP")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+""))
	            			finallylistfaerp.add(lineTxt);
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("I")){
	            		String strdays = null;
	            		if(days<10)
	            			strdays = "00"+days;
	            		if(10<=days&days<100)
	            			strdays = "0"+days;
	            		if(lineTxt.substring(4, 8).equals(strdays+"0")&lineTxt.substring(9, 11).equals(lowyear+""))
	            			finallylistfaion.add(lineTxt);
	            	}
	            }
	        }
		}
		session.setAttribute("listfasp3", listfasp3);
		session.setAttribute("listfaclk", listfaclk);
		session.setAttribute("listfaerp", listfaerp);
		session.setAttribute("listfaion", listfaion);
		session.setAttribute("finallylistfasp3", finallylistfasp3);
		session.setAttribute("finallylistfaclk", finallylistfaclk);
		session.setAttribute("finallylistfaerp", finallylistfaerp);
		session.setAttribute("finallylistfaion", finallylistfaion);
		request.getRequestDispatcher("/SendFastServlet.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
