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

public class DailydFormServlet extends HttpServlet {
	
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
		int currentyear = c.get(Calendar.YEAR);
		int currentmonth = c.get(Calendar.MONTH)+1;
		int currentdate = c.get(Calendar.DATE);
		//得到用户输入的时间
		String stringyear = request.getParameter("year");
		String stringmonth = request.getParameter("month");
		String stringdate = request.getParameter("date");
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null){
			request.setAttribute("message", "日期不能为空，请重新输入");
			request.getRequestDispatcher("/Daily-d.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		
		int date1 = MonthDays.days(year, month);   //根据用户输入的年，月，计算出该月的天数
		if(year == currentyear){
			if(month>currentmonth||date>date1){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/Daily-d.jsp").forward(request, response);
				return ;
			}
			if(month==currentmonth&date>currentdate){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/Daily-d.jsp").forward(request, response);
				return ;
			}
		}
		if(year>currentyear||month>12||date>date1){
			request.setAttribute("message", "日期有误，请重新输入");
			request.getRequestDispatcher("/Daily-d.jsp").forward(request, response);
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
		//组合出IGS RINEX 2.X stations文件的存放路径
		String igs = "IGS/daily/" + year + "/" + s;
		//组合出MGEX and IGS RINEX 3.X stations文件的存放路径
		String multignss = "Multi-GNSS/daily/" + year + "/" + s;     
		try{
			
			//得到IGS文件夹下的文件名中包含的站点名
			String[] IGSfileName = GetFoldFileNames.getFileName(igs);
			List<String> IGSList = new ArrayList<String>();
			for(int i=0;i<IGSfileName.length;i++){
				if(IGSfileName[i].substring(11, 12).equals("D")){
					IGSList.add(IGSfileName[i].substring(0, 4));
				}
			}
			
			//得到Multi-GNSS文件夹下的文件名中包含的站点名
			String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
			List<String> MultiList = new ArrayList<String>();
			for(int i=0;i<MultiGNSSfileName.length;i++){
				if(MultiGNSSfileName[i].substring(11, 12).equals("D")){
					MultiList.add(MultiGNSSfileName[i].substring(0, 4));
				}
			}
			
			String[] IGS = IGSList.toArray(new String[IGSList.size()]);
			String[] Multi = MultiList.toArray(new String[MultiList.size()]);
			
			String[] IGS2success = StringArray.intersect(IGS2, IGS);
			String[] IGS2failed = StringArray.minus(IGS2, IGS2success);
			
			
			String[] MGEXIGS3success = StringArray.intersect(MGEXIGS3, Multi);
			String[] MGEXIGS3failed = StringArray.minus(MGEXIGS3, MGEXIGS3success);
			
			String[] iGMASsuccess = StringArray.intersect(iGMAS, Multi);
			String[] iGMASfailed = StringArray.minus(iGMAS, iGMASsuccess);
			/*for(int i = 0;i<fileName.length;i++){
				System.out.println(fileName[i]);
			}*/
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("date", date);
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
