package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import utils.MonthDays;
import utils.ParseXML;

public class SendFastFormServlet extends HttpServlet {
	
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
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		int currentmonth = c.get(Calendar.MONTH)+1;
		int currentdate = c.get(Calendar.DATE);
		int currenthour = c.get(Calendar.HOUR_OF_DAY);
		//得到用户输入的时间
		String stringyear = request.getParameter("year");
		String stringmonth = request.getParameter("month");
		String stringdate = request.getParameter("date");
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null){
			request.setAttribute("message", "日期不能为空，请重新输入");
			request.getRequestDispatcher("/SendFastServlet.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		int days = CountDay.countDay(year, month, date);//得到用户输入的日期为该年内的第几天
		int lowyear = year-2000;    //例如若年份为2015年，则用15表示年份
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null){
			request.setAttribute("message", "日期不能为空，请重新输入");
			request.getRequestDispatcher("/SendFastServlet.jsp").forward(request, response);
			return ;
		}		
		int date1 = MonthDays.days(year, month);   //根据用户输入的年，月，计算出该月的天数
		if(year == currentyear){
			if(month>currentmonth||date>date1){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/SendFastServlet.jsp").forward(request, response);
				return ;
			}
			if(month==currentmonth&date>currentdate){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/SendFastServlet.jsp").forward(request, response);
				return ;
			}
		}
		if(year>currentyear||month>12||date>date1){
			request.setAttribute("message", "日期有误，请重新输入");
			request.getRequestDispatcher("/SendFastServlet.jsp").forward(request, response);
			return ;
			}
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //表示用户输入时间距1980.1.6为第几周
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = new Date();
		try {
			endDate= format.parse(endDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int weeknum = GetWeek.getWeek(endDate);    //表示今天为这一周的第几天，周日为第零天		
		request.setAttribute("weeks", weeks);
		request.setAttribute("weeknum", weeknum);
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
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} //解析配置文件，确定读取索要读取文件夹的路径
		String path = filepath[11];
		String finallyPath = filepath[16];
		String logPath = path+week+"_Sended.log";
		String finallyLogPath = finallyPath+week+"_Sended.log";
		System.out.println(logPath);
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
