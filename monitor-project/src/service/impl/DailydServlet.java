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


public class DailydServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();	
		//得到IGS RINEX 2.X站点
		String[] IGS2 = ReadStations.readFile(ReadStations.getPath("IGS_FNL.CLU"));
		//得到MGEX and IGS RINEX 3.X stations
		String[] MGEXIGS3 = ReadStations.readFile(ReadStations.getPath("MGEX_FULL.CLU"));
		//得到iGMAS stations
		String[] iGMAS = ReadStations.readFile(ReadStations.getPath("iGMAS_20150520.CLU"));
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
		//组合出IGS RINEX 2.X stations文件的存放路径
		String igs = "IGS/daily/" + year + "/" + s;
		//组合出MGEX and IGS RINEX 3.X stations文件的存放路径
		String multignss = "Multi-GNSS/daily/" + year + "/" + s;
		
		List<String> IGSList = new ArrayList<String>();
		List<String> MultiList = new ArrayList<String>();
		
		String[] IGS2success = null;
		String[] IGS2failed = null;
		
		
		String[] MGEXIGS3success = null;
		String[] MGEXIGS3failed = null;
		
		String[] iGMASsuccess = null;
		String[] iGMASfailed = null;
		
		try{			
			//得到IGS文件夹下的文件名中包含的站点名
			String[] IGSfileName = GetFoldFileNames.getFileName(igs);
			for(int i=0;i<IGSfileName.length;i++){
				if(IGSfileName[i].substring(11, 12).equals("D")){
					IGSList.add(IGSfileName[i].substring(0, 4));
				}
			}
			
			//得到Multi-GNSS文件夹下的文件名中包含的站点名
			String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
			for(int i=0;i<MultiGNSSfileName.length;i++){
				if(MultiGNSSfileName[i].substring(11, 12).equals("D")){
					MultiList.add(MultiGNSSfileName[i].substring(0, 4));
				}
			}
			
			String[] IGS = IGSList.toArray(new String[IGSList.size()]);
			String[] Multi = MultiList.toArray(new String[MultiList.size()]);
			
			IGS2success = StringArray.intersect(IGS, IGS2);
			IGS2failed = StringArray.minus(IGS2, IGS2success);
			
			MGEXIGS3success = StringArray.intersect(Multi, MGEXIGS3);
			MGEXIGS3failed = StringArray.minus(MGEXIGS3, MGEXIGS3success);
			
			iGMASsuccess = StringArray.intersect(iGMAS, Multi);
			iGMASfailed = StringArray.minus(iGMAS, iGMASsuccess);
			
			session.setAttribute("dIGS2successNum", IGS2success.length);
			session.setAttribute("dIGS2failedNum", IGS2failed.length);
			session.setAttribute("dMGEXIGS3successNum", MGEXIGS3success.length);
			session.setAttribute("dMGEXIGS3failedNum", MGEXIGS3failed.length);
			session.setAttribute("diGMASsuccessNum", iGMASsuccess.length);
			session.setAttribute("diGMASfailedNum", iGMASfailed.length);
			session.setAttribute("dailyIGS2success", IGS2success);
			session.setAttribute("dailyIGS2failed", IGS2failed);
			session.setAttribute("dailyMGEXIGS3success", MGEXIGS3success);
			session.setAttribute("dailyMGEXIGS3failed", MGEXIGS3failed);
			session.setAttribute("dailyiGMASsuccess", iGMASsuccess);
			session.setAttribute("dailyiGMASfailed", iGMASfailed);
			}catch (Exception e) {
				e.printStackTrace();
			}		
		request.getRequestDispatcher("/Daily-d.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
