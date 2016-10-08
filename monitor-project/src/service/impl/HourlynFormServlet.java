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

public class HourlynFormServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] GPSStations = ReadStations.readFile(ReadStations.getPath("BRDC_N.CLU"));
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		int currentmonth = c.get(Calendar.MONTH)+1;
		int currentdate = c.get(Calendar.DATE);
		int currenthour = c.get(Calendar.HOUR_OF_DAY);
		String[] IGSfileName = null;
		String[] GPSStationssuccess = null;
		String[] GPSStationsfailed = null;
		//得到用户输入的时间
		String stringyear = request.getParameter("year");
		String stringmonth = request.getParameter("month");
		String stringdate = request.getParameter("date");
		String stringhour = request.getParameter("hour");
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null||stringhour==""||stringhour==null){
			request.setAttribute("message", "日期不能为空，请重新输入");
			request.getRequestDispatcher("/Hourly-n.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		int hour = Integer.parseInt(stringhour);
		
		int date1 = MonthDays.days(year, month);   //根据用户输入的年，月，计算出该月的天数
		
		if(year>currentyear||month>12||date>date1||hour>23){
			request.setAttribute("message", "日期有误，请重新输入");
			request.getRequestDispatcher("/Hourly-n.jsp").forward(request, response);
			return ;
		}
		if(year==currentyear){
			if(month>currentmonth||date>date1||hour>23){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/Hourly-n.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&hour>currenthour){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/Hourly-n.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&(hour>currenthour-3&hour<=currenthour)){
				request.setAttribute("message", "文件正在下载，请稍后再试");
				request.getRequestDispatcher("/Hourly-n.jsp").forward(request, response);
				return ;
			}
		}
		int daynum = CountDay.countDay(year, month, date); //计算出用户输入的日期是该年的第几天
		
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
		String s[] = null;
		try {
			s = ParseXML.ReadXML();
			if(s[2].equals("")){          //获取IGS小时文件下载开始时间，若无，则什么也不做				               
			}
			else{
				//组合出IGS RINEX 2.X stations文件的存放路径
				String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
				//得到IGS文件夹下的文件名中包含的站点名
				IGSfileName = GetFoldFileNames.getFileName(igs);
				List<String> IGSList = new ArrayList<String>();
				for(int i=0;i<IGSfileName.length;i++){
					if(IGSfileName[i].substring(11, 12).equals("N")){
						IGSList.add(IGSfileName[i].substring(0, 4));
					}
				}
				String[] IGS = IGSList.toArray(new String[IGSList.size()]);
				GPSStationssuccess = StringArray.intersect(GPSStations, IGS);
				GPSStationsfailed = StringArray.minus(GPSStations, GPSStationssuccess);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("hour", hour);
		session.setAttribute("hGPSStationssuNum", GPSStationssuccess.length);
		session.setAttribute("hGPSStationsfaNum", GPSStationsfailed.length);
		session.setAttribute("hourlyGPSStationssuccess", GPSStationssuccess);
		session.setAttribute("hourlyGPSStationsfailed", GPSStationsfailed);
		request.getRequestDispatcher("/Hourly-n.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
