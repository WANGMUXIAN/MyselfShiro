package service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ParseXML;


public class CrontabFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  //指定浏览器以某种码表打开数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
//		String minute1 = request.getParameter("minute1");
//		String hour1 = request.getParameter("hour1");
//		String day1 = request.getParameter("day1");
//		System.out.println(minute1);
//		System.out.println(hour1);
//		System.out.println(day1);
		String[] flag = new String[32];
		String[] minute = new String[32];
		String[] hour = new String[32];
		String[] day = new String[32];
		String[] month = new String[32];
		String[] week = new String[32];
		String[] command = new String[32];
		for(int i=1;i<=32;i++){
			String s = "minute"+i;
			minute[i-1] = request.getParameter(s);
		}
		for(int i=1;i<=32;i++){
			String s = "hour"+i;
			hour[i-1] = request.getParameter(s);
		}
		for(int i=1;i<=32;i++){
			String s = "day"+i;
			day[i-1] = request.getParameter(s);
		}
		for(int i=1;i<=32;i++){
			String s = "month"+i;
			month[i-1] = request.getParameter(s);
		}
		for(int i=1;i<=32;i++){
			String s = "week"+i;
			week[i-1] = request.getParameter(s);
		}
		for(int i=1;i<=32;i++){
			String s = "command"+i;
			command[i-1] = request.getParameter(s);
		}
		for(int i=1;i<32;i++){
			System.out.println(command[i]);
		}
		//写入
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String crontabFilepath = filepath[12];
		
		FileWriter fw = new FileWriter(crontabFilepath);
		BufferedWriter writer = new BufferedWriter(fw);
		String sb = null;
		for(int i=0;i<32;i++){
			sb=minute[i]+" "+hour[i]+" "+day[i]+" "+month[i]+" "+week[i]+" "+command[i];			
			writer.write(sb);
			writer.newLine();
			writer.newLine();
		}
		writer.flush();
		writer.close();
		request.setAttribute("message", "CronTab修改成功");
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
}
