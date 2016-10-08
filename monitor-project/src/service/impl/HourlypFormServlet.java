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


public class HourlypFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] PFileStations = ReadStations.readFile(ReadStations.getPath("BRDM_P.CLU"));
		String[] MultiGNSSfileName = null;
		String[] PFileStationssuccess = null;
		String[] PFileStationsfailed = null;
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		int currentmonth = c.get(Calendar.MONTH)+1;
		int currentdate = c.get(Calendar.DATE);
		int currenthour = c.get(Calendar.HOUR_OF_DAY);
		//�õ��û������ʱ��
		String stringyear = request.getParameter("year");
		String stringmonth = request.getParameter("month");
		String stringdate = request.getParameter("date");
		String stringhour = request.getParameter("hour");
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null||stringhour==""||stringhour==null){
			request.setAttribute("message", "���ڲ���Ϊ�գ�����������");
			request.getRequestDispatcher("/Hourly-p.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		int hour = Integer.parseInt(stringhour);
		
		int date1 = MonthDays.days(year, month);   //�����û�������꣬�£���������µ�����
		
		if(year>currentyear||month>12||date>date1||hour>23){
			request.setAttribute("message", "������������������");
			request.getRequestDispatcher("/Hourly-p.jsp").forward(request, response);
			return ;
		}
		if(year==currentyear){
			if(month>currentmonth||date>date1||hour>23){
				request.setAttribute("message", "������������������");
				request.getRequestDispatcher("/Hourly-p.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&hour>currenthour){
				request.setAttribute("message", "������������������");
				request.getRequestDispatcher("/Hourly-p.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&(hour>currenthour-3&hour<=currenthour)){
				request.setAttribute("message", "�ļ��������أ����Ժ�����");
				request.getRequestDispatcher("/Hourly-p.jsp").forward(request, response);
				return ;
			}
		}
		
		int daynum = CountDay.countDay(year, month, date); //������û�����������Ǹ���ĵڼ���
		
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
		//��ϳ�Multi-GNSS navigation message file�Ĵ��·��
		String multignss = "Multi-GNSS/hourly/" + year + "/" + d + "/" + h;
		try {
			MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
			List<String> MultiList = new ArrayList<String>();
			for(int i=0;i<MultiGNSSfileName.length;i++){
				if(MultiGNSSfileName[i].substring(11, 12).equals("P")){
					MultiList.add(MultiGNSSfileName[i].substring(0, 4));
				}
			}
			String[] Multi = MultiList.toArray(new String[MultiList.size()]);
			PFileStationssuccess = StringArray.intersect(PFileStations, Multi);
			PFileStationsfailed = StringArray.minus(PFileStations, PFileStationssuccess);					
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("hour", hour);
		session.setAttribute("hPFileStationssuNum", PFileStationssuccess.length);
		session.setAttribute("hPFileStationsfaNum", PFileStationsfailed.length);
		session.setAttribute("hourlyPFileStationssuccess", PFileStationssuccess);
		session.setAttribute("hourlyPFileStationsfailed", PFileStationsfailed);
		request.getRequestDispatcher("/Hourly-p.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
