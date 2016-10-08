package service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
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

public class HourlydServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//得到IGS RINEX 2.X站点
		String[] IGS2 = ReadStations.readFile(ReadStations.getPath("IGS_FNL.CLU"));
		//得到MGEX and IGS RINEX 3.X stations
		String[] MGEXIGS3 = ReadStations.readFile(ReadStations.getPath("MGEX_FULL.CLU"));
		//得到iGMAS stations
		String[] iGMAS = ReadStations.readFile(ReadStations.getPath("iGMAS_20150520.CLU"));
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int daynum = 0;
//		String[] MultiGNSSfileName = null;
//		String[] MGEXIGS3success = null;
//		String[] MGEXIGS3failed = null;
//		String[] iGMASsuccess = null;
//		String[] iGMASfailed = null;
//		String[] IGSfileName = null;
//		String[] IGS2success = null;
//		String[] IGS2failed = null;
		try {
			String s[] = ParseXML.ReadXML();
			int MultiStartTime = Integer.parseInt(s[1]);   //得到Multi-GNSS小时文件开始下载时间
			int MultiEndTime = MultiStartTime + 30;        //得到Multi-GNSS小时文件结束下载时间
			if(MultiEndTime<60){
				if(MultiEndTime<=minute)
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
				//组合出MGEX and IGS RINEX 3.X stations文件的存放路径
				String multignss = "Multi-GNSS/hourly/" + year + "/" + d + "/" + h;
				//System.out.println(multignss);
				String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
				List<String> MultiList = new ArrayList<String>();
				for(int i=0;i<MultiGNSSfileName.length;i++){
					if(MultiGNSSfileName[i].substring(11, 12).equals("D")){
						MultiList.add(MultiGNSSfileName[i].substring(0, 4));
					}
				}
				String[] Multi = MultiList.toArray(new String[MultiList.size()]);
				String[] MGEXIGS3success = StringArray.intersect(MGEXIGS3, Multi);
				String[] MGEXIGS3failed = StringArray.minus(MGEXIGS3, MGEXIGS3success);
				
				String[] iGMASsuccess = StringArray.intersect(iGMAS, Multi);
				String[] iGMASfailed = StringArray.minus(iGMAS, iGMASsuccess);
				session.setAttribute("hMGEXIGS3suNum", MGEXIGS3success.length);
				session.setAttribute("hMGEXIGS3faNum", MGEXIGS3failed.length);
				session.setAttribute("hiGMASsuNum", iGMASsuccess.length);
				session.setAttribute("hiGMASfaNum", iGMASfailed.length);
				session.setAttribute("hourlyMGEXIGS3success", MGEXIGS3success);
				session.setAttribute("hourlyMGEXIGS3failed", MGEXIGS3failed);
				session.setAttribute("hourlyiGMASsuccess", iGMASsuccess);
				session.setAttribute("hourlyiGMASfailed", iGMASfailed);
			}
			else{
				if((MultiEndTime-60)<=minute)
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
				//组合出MGEX and IGS RINEX 3.X stations文件的存放路径
				String multignss = "Multi-GNSS/hourly/" + year + "/" + d + "/" + h;
//				System.out.println(multignss);
				String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
//				for(int i = 0;i<MultiGNSSfileName.length;i++){
//					System.out.println(MultiGNSSfileName[i]);
//				}
				List<String> MultiList = new ArrayList<String>();
				for(int i=0;i<MultiGNSSfileName.length;i++){
					if(MultiGNSSfileName[i].substring(11, 12).equals("D")){
						MultiList.add(MultiGNSSfileName[i].substring(0, 4));
					}
				}
				String[] Multi = MultiList.toArray(new String[MultiList.size()]);
				
				String[] MGEXIGS3success = StringArray.intersect(MGEXIGS3, Multi);
				String[] MGEXIGS3failed = StringArray.minus(MGEXIGS3, MGEXIGS3success);
				
				String[] iGMASsuccess = StringArray.intersect(iGMAS, Multi);
				String[] iGMASfailed = StringArray.minus(iGMAS, iGMASsuccess);
				session.setAttribute("hMGEXIGS3suNum", MGEXIGS3success.length);
				session.setAttribute("hMGEXIGS3faNum", MGEXIGS3failed.length);
				session.setAttribute("hiGMASsuNum", iGMASsuccess.length);
				session.setAttribute("hiGMASfaNum", iGMASfailed.length);
				session.setAttribute("hourlyMGEXIGS3success", MGEXIGS3success);
				session.setAttribute("hourlyMGEXIGS3failed", MGEXIGS3failed);
				session.setAttribute("hourlyiGMASsuccess", iGMASsuccess);
				session.setAttribute("hourlyiGMASfailed", iGMASfailed);
			}
			if(s[2].equals("")){          //获取IGS小时文件下载开始时间，若无，则什么也不做				               
			}
			else{
				int IGSStartTime = Integer.parseInt(s[2]);   //得到IGS小时文件开始下载时间
				int IGSEndTime = IGSStartTime + 30;        //得到IGS小时文件结束下载时间
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
					//组合出IGS RINEX 2.X stations文件的存放路径
					String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
					//得到IGS文件夹下的文件名中包含的站点名
					String[] IGSfileName = GetFoldFileNames.getFileName(igs);
					List<String> IGSList = new ArrayList<String>();
					for(int i=0;i<IGSfileName.length;i++){
						if(IGSfileName[i].substring(11, 12).equals("D")){
							IGSList.add(IGSfileName[i].substring(0, 4));
						}
					}
					String[] IGS = IGSList.toArray(new String[IGSList.size()]);
					String[] IGS2success = StringArray.intersect(IGS2, IGS);
					String[] IGS2failed = StringArray.minus(IGS2, IGS2success);
					session.setAttribute("hIGS2suNum", IGS2success.length);
					session.setAttribute("hIGS2faNum", IGS2failed.length);
					session.setAttribute("hourlyIGS2success", IGS2success);
					session.setAttribute("hourlyIGS2failed", IGS2failed);
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
					//组合出IGS RINEX 2.X stations文件的存放路径
					String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
					//得到IGS文件夹下的文件名中包含的站点名
					String[] IGSfileName = GetFoldFileNames.getFileName(igs);
					List<String> IGSList = new ArrayList<String>();
					for(int i=0;i<IGSfileName.length;i++){
						if(IGSfileName[i].substring(11, 12).equals("D")){
							IGSList.add(IGSfileName[i].substring(0, 4));
						}
					}
					String[] IGS = IGSList.toArray(new String[IGSList.size()]);
					String[] IGS2success = StringArray.intersect(IGS2, IGS);
					String[] IGS2failed = StringArray.minus(IGS2, IGS2success);
					session.setAttribute("hIGS2suNum", IGS2success.length);
					session.setAttribute("hIGS2faNum", IGS2failed.length);
					session.setAttribute("hourlyIGS2success", IGS2success);
					session.setAttribute("hourlyIGS2failed", IGS2failed);
				}
			}
//			session.setAttribute("hourlyIGS2success", IGS2success);
//			session.setAttribute("hourlyIGS2failed", IGS2failed);
//			session.setAttribute("hourlyMGEXIGS3success", MGEXIGS3success);
//			session.setAttribute("hourlyMGEXIGS3failed", MGEXIGS3failed);
//			session.setAttribute("hourlyiGMASsuccess", iGMASsuccess);
//			session.setAttribute("hourlyiGMASfailed", iGMASfailed);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("date", date);
		request.setAttribute("hour", hour);
		request.getRequestDispatcher("/Hourly-d.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
