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
import utils.ReadStations;
import utils.StringArray;

public class DailynFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] GPSStations = ReadStations.readFile(ReadStations.getPath("BRDC_N.CLU"));
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
			request.getRequestDispatcher("/Daily-n.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		
		int date1 = MonthDays.days(year, month);   //根据用户输入的年，月，计算出该月的天数
		if(year == currentyear){
			if(month>currentmonth||date>date1){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/Daily-n.jsp").forward(request, response);
				return ;
			}
			if(month==currentmonth&date>currentdate){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/Daily-n.jsp").forward(request, response);
				return ;
			}
		}
		if(year>currentyear||month>12||date>date1){
			request.setAttribute("message", "日期有误，请重新输入");
			request.getRequestDispatcher("/Daily-n.jsp").forward(request, response);
			return ;
			}
		int daynum = CountDay.countDay(year, month, date);
		String s = null;;
		if(daynum<10){
			s = "00" + daynum;
		}
		if(10<=daynum&daynum<100)
		{
			s = "0" + daynum;
		}
		if(daynum>=100){
			s = daynum + "";
		}
		String igs = "IGS/daily/" + year + "/" + s;
		try {
			String[] IGSfileName = GetFoldFileNames.getFileName(igs);
			List<String> IGSList = new ArrayList<String>();
			for(int i=0;i<IGSfileName.length;i++){
				if(IGSfileName[i].substring(11, 12).equals("N")){
					IGSList.add(IGSfileName[i].substring(0, 4));
				}
			}
			String[] IGS = IGSList.toArray(new String[IGSList.size()]);
			String[] GPSStationssuccess = StringArray.intersect(GPSStations, IGS);
			String[] GPSStationsfailed = StringArray.minus(GPSStations, GPSStationssuccess);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("date", date);
			session.setAttribute("dGPSStationssuNum", GPSStationssuccess.length);
			session.setAttribute("dGPSStationsfaNum", GPSStationsfailed.length);
			session.setAttribute("dailyGPSStationssuccess", GPSStationssuccess);
			session.setAttribute("dailyGPSStationsfailed", GPSStationsfailed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Daily-n.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
