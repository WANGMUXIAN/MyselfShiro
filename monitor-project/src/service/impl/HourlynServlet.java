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

public class HourlynServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String[] GPSStations = ReadStations.readFile(ReadStations.getPath("BRDC_N.CLU"));
		
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int daynum = 0;
		String[] IGSfileName = null;
		String[] GPSStationssuccess = null;
		String[] GPSStationsfailed = null;
		try {
			String s[] = ParseXML.ReadXML();
			if(s[2].equals("")){          //��ȡIGSСʱ�ļ����ؿ�ʼʱ�䣬���ޣ���ʲôҲ����				               
			}
			else{
				int IGSStartTime = Integer.parseInt(s[2]);   //�õ�IGSСʱ�ļ���ʼ����ʱ��
				int IGSEndTime = IGSStartTime + 30;        //�õ�IGSСʱ�ļ���������ʱ��
				if(IGSEndTime<60){
					if(IGSEndTime<=minute)
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
					//��ϳ�GPS navigation message file�Ĵ��·��
					String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
					//�õ�IGS�ļ����µ��ļ����а�����վ����
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
				else{
					if((IGSEndTime-60)<=minute)
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
					//��ϳ�IGS RINEX 2.X stations�ļ��Ĵ��·��
					String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
					//�õ�IGS�ļ����µ��ļ����а�����վ����
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
				request.setAttribute("year", year);
				request.setAttribute("month", month);
				request.setAttribute("date", date);
				request.setAttribute("hour", hour);
				session.setAttribute("hGPSStationssuNum", GPSStationssuccess.length);
				session.setAttribute("hGPSStationsfaNum", GPSStationsfailed.length);
				session.setAttribute("hourlyGPSStationssuccess", GPSStationssuccess);
				session.setAttribute("hourlyGPSStationsfailed", GPSStationsfailed);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("hour", hour);
		request.getRequestDispatcher("/Hourly-n.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
