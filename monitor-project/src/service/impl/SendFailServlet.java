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

import utils.CountWeeks;
import utils.GetWeek;
import utils.ParseXML;


public class SendFailServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LinkedList<String> listfail = new LinkedList<String>();  //保存上传失败的文件
		LinkedList<String> finallylistfail = new LinkedList<String>();  //保存10.12.5.6上传失败的文件
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //北斗周
		String week = null;
		if(weeks<1000)
			week = "0"+weeks;
		int weeknum = GetWeek.getWeek(new Date());    //表示今天为这一周的第几天，周日为第零天
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} //解析配置文件，确定读取索要读取文件夹的路径
		String path = filepath[11];          //192.168.7.10-->10.12.5.6log文件路径
		String finallyPath = filepath[16];   //10.12.5.6-->综合服务中心  log文件路径
		String logPath = path+week+"_UnSended.log";
		String finallyLogPath = finallyPath+week+"_UnSended.log";
		//System.out.println(logPath);
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
        	Long time =file.lastModified();   //返回此文件最后一次被修改的时间，单位为毫秒
        	Calendar currentTime = Calendar.getInstance();   //得到当前时间
        	long timeBlank = currentTime.getTimeInMillis()-time;    //得到时间间隔
        	if(timeBlank<=1000*60*10){
        		InputStreamReader read = new InputStreamReader(
        	            new FileInputStream(finallyFile),encoding);          //考虑到编码格式
        	            BufferedReader bufferedReader = new BufferedReader(read);
        	            String lineTxt = null;
        	            while((lineTxt = bufferedReader.readLine()) != null){
        	            	finallylistfail.add(lineTxt);
        	            }
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
