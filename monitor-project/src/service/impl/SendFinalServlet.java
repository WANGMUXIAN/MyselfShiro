package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CountDay;
import utils.CountWeeks;
import utils.GetWeek;
import utils.ParseXML;

public class SendFinalServlet extends HttpServlet {
	
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
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //表示今天距1980.1.6为第几周
		int weeknum = GetWeek.getWeek(new Date());    //表示今天为这一周的第几天，周日为第零天
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
        session.setAttribute("finallylistfinsp3", finallylistfinsp3);
        session.setAttribute("finallylistfinerp", finallylistfinerp);
        session.setAttribute("finallylistfinclk", finallylistfinclk);
        session.setAttribute("finallylistfinion", finallylistfinion);
        session.setAttribute("finallylistfinsnx", finallylistfinsnx);
        session.setAttribute("finallylistfintro", finallylistfintro);
        session.setAttribute("finallylistfindcb", finallylistfindcb);
        session.setAttribute("finallylistfinsum", finallylistfinsum);
        request.getRequestDispatcher("/SendFinalServlet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
