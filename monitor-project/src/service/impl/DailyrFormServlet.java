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

public class DailyrFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] BDSStations = ReadStations.readFile(ReadStations.getPath("iGMAS_brdr_no_54.CLU"));
		//得到系统当前时间
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
			request.getRequestDispatcher("/Daily-r.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		
		int date1 = MonthDays.days(year, month);   //根据用户输入的年，月，计算出该月的天数
		if(year == currentyear){
			if(month>currentmonth||date>date1){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/Daily-r.jsp").forward(request, response);
				return ;
			}
			if(month==currentmonth&date>currentdate){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/Daily-r.jsp").forward(request, response);
				return ;
			}
		}
		if(year>currentyear||month>12||date>date1){
			request.setAttribute("message", "日期有误，请重新输入");
			request.getRequestDispatcher("/Daily-r.jsp").forward(request, response);
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
		String multignss = "Multi-GNSS/daily/" + year + "/" + s;
		try {
			String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
			List<String> MultiList = new ArrayList<String>();
			for(int i=0;i<MultiGNSSfileName.length;i++){
				if(MultiGNSSfileName[i].substring(11, 12).equals("R")){
					MultiList.add(MultiGNSSfileName[i].substring(0, 4));
				}
			}
			String[] Multi = MultiList.toArray(new String[MultiList.size()]);
			String[] BDSStationssuccess = StringArray.intersect(BDSStations, Multi);
			String[] BDSStationsfailed = StringArray.minus(BDSStations, BDSStationssuccess);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("date", date);
			session.setAttribute("dBDSStationssuNum", BDSStationssuccess.length);
			session.setAttribute("dBDSStationsfaNum", BDSStationsfailed.length);
			session.setAttribute("dailyBDSStationssuccess", BDSStationssuccess);
			session.setAttribute("dailyBDSStationsfailed", BDSStationsfailed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Daily-r.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
