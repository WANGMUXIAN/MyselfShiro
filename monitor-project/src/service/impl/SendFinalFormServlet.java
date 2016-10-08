package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CountWeeks;
import utils.GetWeek;
import utils.MonthDays;
import utils.ParseXML;

public class SendFinalFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LinkedList<String> listfinsp3 = new LinkedList<String>();  //保存上传成功的快速轨道产品
		LinkedList<String> listfinerp = new LinkedList<String>();  //保存上传成功的快速地球自转参数文件
		LinkedList<String> listfinclk = new LinkedList<String>();  //保存上传成功的快速钟差文件
		LinkedList<String> listfinion = new LinkedList<String>();  //保存上传成功的快速电离层文件
		LinkedList<String> listfinsnx = new LinkedList<String>();  //保存上传成功的最终跟踪站文件
		LinkedList<String> listfintro = new LinkedList<String>();  //保存上传成功的最终对流层文件
		LinkedList<String> listfindcb = new LinkedList<String>();  //保存上传成功的最终频间偏差文件
		LinkedList<String> listfinsum = new LinkedList<String>();  //保存上传成功的最终总结信息文件
		LinkedList<String> finallylistfinsp3 = new LinkedList<String>();  //保存10.12.15.6上传成功的快速轨道产品
		LinkedList<String> finallylistfinerp = new LinkedList<String>();  //保存10.12.15.6上传成功的快速地球自转参数文件
		LinkedList<String> finallylistfinclk = new LinkedList<String>();  //保存10.12.15.6上传成功的快速钟差文件
		LinkedList<String> finallylistfinion = new LinkedList<String>();  //保存10.12.15.6上传成功的快速电离层文件
		LinkedList<String> finallylistfinsnx = new LinkedList<String>();  //保存10.12.15.6上传成功的最终跟踪站文件
		LinkedList<String> finallylistfintro = new LinkedList<String>();  //保存10.12.15.6上传成功的最终对流层文件
		LinkedList<String> finallylistfindcb = new LinkedList<String>();  //保存10.12.15.6上传成功的最终频间偏差文件
		LinkedList<String> finallylistfinsum = new LinkedList<String>();  //保存10.12.15.6上传成功的最终总结信息文件
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
			request.getRequestDispatcher("/SendFinalServlet.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		
		int date1 = MonthDays.days(year, month);   //根据用户输入的年，月，计算出该月的天数
		
		if(year == currentyear){
			if(month>currentmonth||date>date1){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/SendFinalServlet.jsp").forward(request, response);
				return ;
			}
			if(month==currentmonth&date>currentdate){
				request.setAttribute("message", "日期有误，请重新输入");
				request.getRequestDispatcher("/SendFinalServlet.jsp").forward(request, response);
				return ;
			}
		}
		if(year>currentyear||month>12||date>date1){
			request.setAttribute("message", "日期有误，请重新输入");
			request.getRequestDispatcher("/SendFinalServlet.jsp").forward(request, response);
			return ;
			}
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //表示用户输入时间距1980.1.6为第几周
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = new Date();
		try {
			endDate= format.parse(endDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int weeknum = GetWeek.getWeek(endDate);    //表示今天为这一周的第几天，周日为第零天
		if(weeknum<=2)
			weeks = weeks-3;
		if(weeknum>2)
			weeks = weeks-2;
		String week = null;
		if(weeks<1000)
			week = "0"+weeks;
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} //解析配置文件，确定读取索要读取文件夹的路径
		String path = filepath[11];
		String finallyPath = filepath[16];
		String logPath = path+week+"_Sended.log";
		String finallyLogPath = finallyPath+week+"_Sended.log";
		String encoding="GBK";
        File file=new File(logPath);
        File finallyFile = new File(finallyLogPath);
        if(file.isFile() && file.exists()){
        	InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);          //考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SP3")&lineTxt.substring(3, 7).equals(week)){
                    		listfinsp3.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("CLK")&lineTxt.substring(3, 7).equals(week)){
                    		listfinclk.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("TRO")&lineTxt.substring(3, 7).equals(week)){
                    		listfintro.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("I")){
                    		listfinion.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("DCB")){
                    		listfindcb.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SNX")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
                    		listfinsnx.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SUM")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
                    		listfinsum.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("ERP")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
                    		listfinerp.add(lineTxt);
                    	}
                    }
        }
        if(finallyFile.isFile() && finallyFile.exists()){
        	InputStreamReader read = new InputStreamReader(
                    new FileInputStream(finallyFile),encoding);          //考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SP3")&lineTxt.substring(3, 7).equals(week)){
                    		finallylistfinsp3.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("CLK")&lineTxt.substring(3, 7).equals(week)){
                    		finallylistfinclk.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("TRO")&lineTxt.substring(3, 7).equals(week)){
                    		finallylistfintro.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("I")){
                    		finallylistfinion.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("DCB")){
                    		finallylistfindcb.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SNX")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
                    		finallylistfinsnx.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SUM")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
                    		finallylistfinsum.add(lineTxt);
                    	}
                    	if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("ERP")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
                    		finallylistfinerp.add(lineTxt);
                    	}
                    }
        }
        request.setAttribute("weeks", weeks);
        session.setAttribute("listfinsp3", listfinsp3);
        session.setAttribute("listfinerp", listfinerp);
        session.setAttribute("listfinclk", listfinclk);
        session.setAttribute("listfinion", listfinion);
        session.setAttribute("listfinsnx", listfinsnx);
        session.setAttribute("listfintro", listfintro);
        session.setAttribute("listfindcb", listfindcb);
        session.setAttribute("listfinsum", listfinsum);
        request.getRequestDispatcher("/SendFinalServlet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
