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

public class HourlydFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//�õ�IGS RINEX 2.Xվ��
		String[] IGS2 = ReadStations.readFile(ReadStations.getPath("IGS_FNL.CLU"));
		//�õ�MGEX and IGS RINEX 3.X stations
		String[] MGEXIGS3 = ReadStations.readFile(ReadStations.getPath("MGEX_FULL.CLU"));
		//�õ�iGMAS stations
		String[] iGMAS = ReadStations.readFile(ReadStations.getPath("iGMAS_20150520.CLU"));
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		int currentmonth = c.get(Calendar.MONTH)+1;
		int currentdate = c.get(Calendar.DATE);
		int currenthour = c.get(Calendar.HOUR_OF_DAY);
		int currentminute = c.get(Calendar.MINUTE);
		//�õ��û������ʱ��
		String stringyear = request.getParameter("year");
		String stringmonth = request.getParameter("month");
		String stringdate = request.getParameter("date");
		String stringhour = request.getParameter("hour");
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null||stringhour==""||stringhour==null){
			request.setAttribute("message", "���ڲ���Ϊ�գ�����������");
			request.getRequestDispatcher("/Hourly-d.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		int hour = Integer.parseInt(stringhour);
		
		int date1 = MonthDays.days(year, month);   //�����û�������꣬�£���������µ�����
		
		if(year>currentyear||month>12||date>date1||hour>23){
			request.setAttribute("message", "������������������");
			request.getRequestDispatcher("/Hourly-d.jsp").forward(request, response);
			return ;
		}
		if(year==currentyear){
			if(month>currentmonth||date>date1||hour>23){
				request.setAttribute("message", "������������������");
				request.getRequestDispatcher("/Hourly-d.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&hour>currenthour){
				request.setAttribute("message", "������������������");
				request.getRequestDispatcher("/Hourly-d.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&(hour>currenthour-3&hour<=currenthour)){
				request.setAttribute("message", "�ļ��������أ����Ժ�����");
				request.getRequestDispatcher("/Hourly-d.jsp").forward(request, response);
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
		//��ϳ�MGEX and IGS RINEX 3.X stations�ļ��Ĵ��·��
		String multignss = "Multi-GNSS/hourly/" + year + "/" + d + "/" + h;
		String[] MultiGNSSfileName = null;
		String[] MGEXIGS3success = null;
		String[] MGEXIGS3failed = null;
		String[] iGMASsuccess = null;
		String[] iGMASfailed = null;
		
		try {
			MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
			List<String> MultiList = new ArrayList<String>();
			for(int i=0;i<MultiGNSSfileName.length;i++){
				if(MultiGNSSfileName[i].substring(11, 12).equals("D")){
					MultiList.add(MultiGNSSfileName[i].substring(0, 4));
				}
			}
			String[] Multi = MultiList.toArray(new String[MultiList.size()]);
			MGEXIGS3success = StringArray.intersect(MGEXIGS3, Multi);
			MGEXIGS3failed = StringArray.minus(MGEXIGS3, MGEXIGS3success);
			
			iGMASsuccess = StringArray.intersect(iGMAS, Multi);
			iGMASfailed = StringArray.minus(iGMAS, iGMASsuccess);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String[] IGS2success = null;
		String[] IGS2failed = null;
		String[] IGSfileName = null;
		try {
			String s[] = ParseXML.ReadXML();
			if(s[2].equals("")){          //��ȡIGSСʱ�ļ����ؿ�ʼʱ�䣬���ޣ���ʲôҲ����				               
			}
			else{
				String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
				//�õ�IGS�ļ����µ��ļ����а�����վ����
				IGSfileName = GetFoldFileNames.getFileName(igs); 
				List<String> IGSList = new ArrayList<String>();
				for(int i=0;i<IGSfileName.length;i++){
					if(IGSfileName[i].substring(11, 12).equals("D")){
						IGSList.add(IGSfileName[i].substring(0, 4));
					}
				}
				String[] IGS = IGSList.toArray(new String[IGSList.size()]);
				IGS2success = StringArray.intersect(IGS2, IGS);
				IGS2failed = StringArray.minus(IGS2, IGS2success);
				session.setAttribute("hIGS2suNum", IGS2success.length);
				session.setAttribute("hIGS2faNum", IGS2failed.length);
				session.setAttribute("hourlyIGS2success", IGS2success);
				session.setAttribute("hourlyIGS2failed", IGS2failed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("hour", hour);
		session.setAttribute("hMGEXIGS3suNum", MGEXIGS3success.length);
		session.setAttribute("hMGEXIGS3faNum", MGEXIGS3failed.length);
		session.setAttribute("hiGMASsuNum", iGMASsuccess.length);
		session.setAttribute("hiGMASfaNum", iGMASfailed.length);
		session.setAttribute("hourlyMGEXIGS3success", MGEXIGS3success);
		session.setAttribute("hourlyMGEXIGS3failed", MGEXIGS3failed);
		session.setAttribute("hourlyiGMASsuccess", iGMASsuccess);
		session.setAttribute("hourlyiGMASfailed", iGMASfailed);
		request.getRequestDispatcher("/Hourly-d.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
