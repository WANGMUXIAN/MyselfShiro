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


public class DailynServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] GPSStations = ReadStations.readFile(ReadStations.getPath("BRDC_N.CLU"));
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		date = date-1;
		if(date<=0){
			month = month-1;
			if(month<=0){
				month = 12;
				year = year-1;
			}
			date = MonthDays.days(year, month);
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
