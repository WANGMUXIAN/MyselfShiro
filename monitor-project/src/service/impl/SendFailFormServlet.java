package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CountWeeks;
import utils.MonthDays;
import utils.ParseXML;


public class SendFailFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LinkedList<String> listfail = new LinkedList<String>();  //保存上传失败的文件
		LinkedList<String> finallylistfail = new LinkedList<String>();  //保存10.12.5.6上传失败的文件
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		int currentmonth = c.get(Calendar.MONTH)+1;
		int currentdate = c.get(Calendar.DATE);
		//得到用户输入的时间
		String stringyear = request.getParameter("year");
		String stringmonth = request.getParameter("month");
		String stringdate = request.getParameter("date");
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null){
			request.setAttribute("message", "日期不能为空，请重新输入");
			request.getRequestDispatcher("/SendFailServlet.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		
		int date1 = MonthDays.days(year, month);   //根据用户输入的年，月，计算出该月的天数
		if(year == currentyear){
			if(month>currentmonth||date>date1){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/SendFailServlet.jsp").forward(request, response);
				return ;
			}
			if(month==currentmonth&date>currentdate){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/SendFailServlet.jsp").forward(request, response);
				return ;
			}
		}
		if(year>currentyear||month>12||date>date1){
			request.setAttribute("message", "日期有误，请重新输入");
			request.getRequestDispatcher("/SendFailServlet.jsp").forward(request, response);
			return ;
			}
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //表示用户输入日期距1980.1.6为第几周
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
		String logPath = path+week+"_UnSended.log";
		String finallyLogPath = finallyPath+week+"_UnSended.log";
		String encoding="GBK";
        File file=new File(logPath);
        File finallyFile = new File(finallyLogPath);
        if(file.isFile() && file.exists()){
        	InputStreamReader read = new InputStreamReader(
            new FileInputStream(file),encoding);          //考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
            	listfail.add(lineTxt);          	
            }
        }
        if(finallyFile.isFile() && finallyFile.exists()){
        	InputStreamReader read = new InputStreamReader(
            new FileInputStream(finallyFile),encoding);          //考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
            	finallylistfail.add(lineTxt);          	
            }
        }
        request.setAttribute("weeks", weeks);
        session.setAttribute("listfail", listfail);
        session.setAttribute("finallylistfail", finallylistfail);
        request.getRequestDispatcher("/SendFailServlet.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
