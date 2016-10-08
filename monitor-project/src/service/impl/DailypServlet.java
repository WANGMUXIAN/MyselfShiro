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

public class DailypServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] PFileStations = ReadStations.readFile(ReadStations.getPath("BRDM_P.CLU"));		
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
		String multignss = "Multi-GNSS/daily/" + year + "/" + s;
		try {
			String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
			List<String> MultiList = new ArrayList<String>();
			for(int i=0;i<MultiGNSSfileName.length;i++){
				if(MultiGNSSfileName[i].substring(11, 12).equals("P")){
					MultiList.add(MultiGNSSfileName[i].substring(0, 4));
				}
			}
			String[] Multi = MultiList.toArray(new String[MultiList.size()]);
			String[] PFileStationssuccess = StringArray.intersect(PFileStations, Multi);
			String[] PFileStationsfailed = StringArray.minus(PFileStations, PFileStationssuccess);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("date", date);
			session.setAttribute("dPFileStationssuNum", PFileStationssuccess.length);
			session.setAttribute("dPFileStationsfaNum", PFileStationsfailed.length);
			session.setAttribute("dailyPFileStationssuccess", PFileStationssuccess);
			session.setAttribute("dailyPFileStationsfailed", PFileStationsfailed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Daily-p.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
