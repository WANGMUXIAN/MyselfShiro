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


public class HourlylServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//得到L站点
		String[] lStations = ReadStations.readFile(ReadStations.getPath("iGMAS_brdr.CLU"));
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int daynum = 0;
		String[] multiGNSSfileName = null;
		String[] lFileSuccess = null;
		String[] lFileFailed = null;
		String s[];
		try {
			s = ParseXML.ReadXML();
			int MultiStartTime = Integer.parseInt(s[1]);   //得到Multi-GNSS小时文件开始下载时间
			int MultiEndTime = MultiStartTime + 30;        //得到Multi-GNSS小时文件结束下载时间,30为文件下载时间
			if(MultiEndTime<60){
				if(MultiEndTime<=minute)
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
				//组合出Multi-GNSS navigation message file的存放路径
				String multignss = "Multi-GNSS/hourly/" + year + "/" + d + "/" + h;
				List<String> list = new ArrayList<String>();
				multiGNSSfileName = GetFoldFileNames.getFileName(multignss);
				for(int i=0;i<multiGNSSfileName.length;i++){
					if(multiGNSSfileName[i].substring(11, 12).equals("L")){
						list.add(multiGNSSfileName[i].substring(0, 4));
					}
				}
				lFileSuccess = list.toArray(new String[list.size()]);
				lFileFailed = StringArray.minus(lStations, lFileSuccess);				
			}
			else{
				if((MultiEndTime-60)<=minute)
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
				//组合出BDS navigation message file的存放路径
				String multignss = "Multi-GNSS/hourly/" + year + "/" + d + "/" + h;
				List<String> list = new ArrayList<String>();
				multiGNSSfileName = GetFoldFileNames.getFileName(multignss);
				for(int i=0;i<multiGNSSfileName.length;i++){
					if(multiGNSSfileName[i].substring(11, 12).equals("L")){
						list.add(multiGNSSfileName[i].substring(0, 4));
					}
				}
				lFileSuccess = list.toArray(new String[list.size()]);
				lFileFailed = StringArray.minus(lStations, lFileSuccess);				
			}
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("date", date);
			request.setAttribute("hour", hour);
			session.setAttribute("hLFileSuccessNum", lFileSuccess.length);
			session.setAttribute("hLFileFailedNum", lFileFailed.length);
			session.setAttribute("hourlyLFileSuccess", lFileSuccess);
			session.setAttribute("hourlyLFileFailed", lFileFailed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Hourly-l.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
