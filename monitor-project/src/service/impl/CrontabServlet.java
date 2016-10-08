package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Crontab;
import utils.ParseXML;
import utils.ReadSoftStateTxt;


public class CrontabServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String currentpage = (String) request.getAttribute("currentpage");
		String totalpage = (String) request.getAttribute("totalpage");
		if(currentpage==null)
		{
			currentpage = "1";
		}		
		
		List list = new ArrayList();
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String crontabFilepath = filepath[12]; 
		LinkedList<String> stringList = new LinkedList<String>();
		stringList = ReadSoftStateTxt.readTxtFile(crontabFilepath);
//		for(String tem : stringList)
//		{
//			System.out.println(tem);
//		}
		System.out.println(stringList.size());
		if(totalpage==null)
		{
			totalpage = String.valueOf(stringList.size()%10==0?stringList.size()/10:stringList.size()/10+1);
		}
		Iterator iterator = stringList.iterator();
		while(iterator.hasNext()){
			String s = iterator.next().toString();
			String command = "";
			if(!(s.equals(""))){
				Crontab crontab = new Crontab();
				String[] b = s.split(" ");
				ArrayList<String> ar = new ArrayList<String>();
				for(int i=0; i<=b.length-1; i++)
				{
					if(b[i].equals(""))
					{
						
					}
					else
					{
						ar.add(b[i]);
					}
					
				}
				Object[] a = ar.toArray();
				crontab.minute = (String)a[0];
				crontab.hour = (String)a[1];
				crontab.day = (String)a[2];
				crontab.month = (String)a[3];
				crontab.week = (String)a[4];
				for(int i=5;i<a.length;i++){
					command += (String)a[i]+" ";
				}
				crontab.command = command;
				list.add(crontab);
			}
		}
		System.out.println(currentpage);
		System.out.println(totalpage);
		//request.setAttribute("listSize", list.size());
		request.setAttribute("crontablist", list);
		System.out.println(list.size());
		request.setAttribute("currentpage", currentpage);
		request.setAttribute("totalpage", totalpage);
		request.getRequestDispatcher("/CronTab.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
