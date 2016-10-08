package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CountDay;
import utils.GetFoldFileNames;
import utils.MonthDays;
import utils.ParseXML;
import utils.ReadStations;
import utils.StringArray;


public class HourlygServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] GLONASStations = ReadStations.readFile(ReadStations.getPath("BRDC_G.CLU"));		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int daynum = 0;
		String[] IGSfileName = null;
		String[] GLONASsuccess = null;
		String[] GLONASfailed = null;
		try {
			String s[] = ParseXML.ReadXML();
			if(s[2].equals("")){          //获取IGS小时文件下载开始时间，若无，则什么也不做				               
			}
			else{
				int IGSStartTime = Integer.parseInt(s[2]);   //得到IGS小时文件开始下载时间
				int IGSEndTime = IGSStartTime + 30;        //得到IGS小时文件结束下载时间
				if(IGSEndTime<60){
					if(IGSEndTime<=minute)
						hour = hour-1;
					else
						hour = hour-2;
					if(hour<0){
						hour = 24+hour;
						date = date-1;
					}
					if(date<=0){					
						month = month-1;
						if(month<=0){
							month = 12+month;
							year = year-1;
						}
						date = MonthDays.days(year, month);					
					}
					daynum = CountDay.countDay(year, month, date);
					String d = null;
					String h = null;
					if(daynum<10){
						d = "00" + daynum;
					}
					if(daynum>=10&daynum<100){
						d = "0" + daynum;
					}
					if(daynum>=100){
						d= daynum + "";
					}
					if(hour<10){
						h = "0" + hour;
					}
					if(hour>=10){
						h = hour + "";
					}
					//组合出IGS RINEX 2.X stations文件的存放路径
					String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
					//得到IGS文件夹下的文件名中包含的站点名
					IGSfileName = GetFoldFileNames.getFileName(igs);
					List<String> IGSList = new ArrayList<String>();
					for(int i=0;i<IGSfileName.length;i++){
						if(IGSfileName[i].substring(11, 12).equals("G")){
							IGSList.add(IGSfileName[i].substring(0, 4));
						}
					}
					String[] IGS = IGSList.toArray(new String[IGSList.size()]);
					GLONASsuccess = StringArray.intersect(GLONASStations, IGS);
					GLONASfailed = StringArray.minus(GLONASStations, GLONASsuccess);
				}
				else{
					if((IGSEndTime-60)<=minute)
						hour = hour-2;
					else
						hour = hour-3;
					if(hour<0){
						hour = 24+hour;
						date = date-1;
					}
					if(date<=0){					
						month = month-1;
						if(month<=0){
							month = 12+month;
							year = year-1;
						}
						date = MonthDays.days(year, month);					
					}
					daynum = CountDay.countDay(year, month, date);
					String d = null;
					String h = null;
					if(daynum<10){
						d = "00" + daynum;
					}
					if(daynum>=10&daynum<100){
						d = "0" + daynum;
					}
					if(daynum>=100){
						d= daynum + "";
					}
					if(hour<10){
						h = "0" + hour;
					}
					if(hour>=10){
						h = hour + "";
					}
					//组合出IGS RINEX 2.X stations文件的存放路径
					String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
					//得到IGS文件夹下的文件名中包含的站点名
					IGSfileName = GetFoldFileNames.getFileName(igs);
					List<String> IGSList = new ArrayList<String>();
					for(int i=0;i<IGSfileName.length;i++){
						if(IGSfileName[i].substring(11, 12).equals("G")){
							IGSList.add(IGSfileName[i].substring(0, 4));
						}
					}
					String[] IGS = IGSList.toArray(new String[IGSList.size()]);
					GLONASsuccess = StringArray.intersect(GLONASStations, IGS);
					GLONASfailed = StringArray.minus(GLONASStations, GLONASsuccess);
				}
				session.setAttribute("hGLONASsuNum", GLONASsuccess.length);
				session.setAttribute("hGLONASfaNum", GLONASfailed.length);
				session.setAttribute("hourlyGLONASsuccess", GLONASsuccess);
				session.setAttribute("hourlyGLONASfailed", GLONASfailed);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("hour", hour);
		request.getRequestDispatcher("/Hourly-g.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
