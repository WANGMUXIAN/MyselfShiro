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

public class HourlygFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] GLONASStations = ReadStations.readFile(ReadStations.getPath("BRDC_G.CLU"));
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		int currentmonth = c.get(Calendar.MONTH)+1;
		int currentdate = c.get(Calendar.DATE);
		int currenthour = c.get(Calendar.HOUR_OF_DAY);
		String[] IGSfileName = null;
		String[] GLONASsuccess = null;
		String[] GLONASfailed = null;
		//�õ��û������ʱ��
		String stringyear = request.getParameter("year");
		String stringmonth = request.getParameter("month");
		String stringdate = request.getParameter("date");
		String stringhour = request.getParameter("hour");
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null||stringhour==""||stringhour==null){
			request.setAttribute("message", "���ڲ���Ϊ�գ�����������");
			request.getRequestDispatcher("/Hourly-g.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		int hour = Integer.parseInt(stringhour);
		
		int date1 = MonthDays.days(year, month);   //�����û�������꣬�£���������µ�����
		
		if(year>currentyear||month>12||date>date1||hour>23){
			request.setAttribute("message", "������������������");
			request.getRequestDispatcher("/Hourly-g.jsp").forward(request, response);
			return ;
		}
		if(year==currentyear){
			if(month>currentmonth||date>date1||hour>23){
				request.setAttribute("message", "������������������");
				request.getRequestDispatcher("/Hourly-g.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&hour>currenthour){
				request.setAttribute("message", "������������������");
				request.getRequestDispatcher("/Hourly-g.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&(hour>currenthour-3&hour<=currenthour)){
				request.setAttribute("message", "�ļ��������أ����Ժ�����");
				request.getRequestDispatcher("/Hourly-g.jsp").forward(request, response);
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
		String s[] = null;
		try {
			s = ParseXML.ReadXML();
			if(s[2].equals("")){          //��ȡIGSСʱ�ļ����ؿ�ʼʱ�䣬���ޣ���ʲôҲ����				               
			}
			else{
				//��ϳ�IGS RINEX 2.X stations�ļ��Ĵ��·��
				String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
				//�õ�IGS�ļ����µ��ļ����а�����վ����
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
				session.setAttribute("hGLONASsuNum", GLONASsuccess.length);
				session.setAttribute("hGLONASfaNum", GLONASfailed.length);
				session.setAttribute("hourlyGLONASsuccess", GLONASsuccess);
				session.setAttribute("hourlyGLONASfailed", GLONASfailed);
			}
		} catch (Exception e) {
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
