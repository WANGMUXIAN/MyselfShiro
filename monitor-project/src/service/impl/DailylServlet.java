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


public class DailylServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//得到L站点
		String[] lStations = ReadStations.readFile(ReadStations.getPath("iGMAS_brdr.CLU"));
		for(int i=0;i<lStations.length;i++){
			System.out.println(lStations[i]);
		}
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
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
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
		//组合出L stations文件的存放路径
		String multignss = "Multi-GNSS/daily/" + year + "/" + s;       //需要改正
		List<String> list = new ArrayList<String>();
		try {
			String[] multignssfileName = GetFoldFileNames.getFileName(multignss);
			for(int i=0;i<multignssfileName.length;i++){
				if(multignssfileName[i].substring(11, 12).equals("L")){
					list.add(multignssfileName[i].substring(0, 4));
				}
			}
			String[] lFileSuccess = list.toArray(new String[list.size()]);
			String[] lFileFailed = StringArray.minus(lStations, lFileSuccess);
			session.setAttribute("dlFileSuNum", lFileSuccess.length);
			session.setAttribute("dlFileFailedNum", lFileFailed.length);
			session.setAttribute("dailylFileSuccess", lFileSuccess);
			session.setAttribute("dailylFileFailed", lFileFailed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Daily-l.jsp").forward(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
}
