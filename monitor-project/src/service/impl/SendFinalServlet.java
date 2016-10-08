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
		LinkedList<String> listfinsp3 = new LinkedList<String>();  //�����ϴ��ɹ��Ŀ��ٹ����Ʒ
		LinkedList<String> listfinerp = new LinkedList<String>();  //�����ϴ��ɹ��Ŀ��ٵ�����ת�����ļ�
		LinkedList<String> listfinclk = new LinkedList<String>();  //�����ϴ��ɹ��Ŀ����Ӳ��ļ�
		LinkedList<String> listfinion = new LinkedList<String>();  //�����ϴ��ɹ��Ŀ��ٵ�����ļ�
		LinkedList<String> listfinsnx = new LinkedList<String>();  //�����ϴ��ɹ������ո���վ�ļ�
		LinkedList<String> listfintro = new LinkedList<String>();  //�����ϴ��ɹ������ն������ļ�
		LinkedList<String> listfindcb = new LinkedList<String>();  //�����ϴ��ɹ�������Ƶ��ƫ���ļ�
		LinkedList<String> listfinsum = new LinkedList<String>();  //�����ϴ��ɹ��������ܽ���Ϣ�ļ�
		LinkedList<String> finallylistfinsp3 = new LinkedList<String>();  //����10.12.15.6�ϴ��ɹ��Ŀ��ٹ����Ʒ
		LinkedList<String> finallylistfinerp = new LinkedList<String>();  //����10.12.15.6�ϴ��ɹ��Ŀ��ٵ�����ת�����ļ�
		LinkedList<String> finallylistfinclk = new LinkedList<String>();  //����10.12.15.6�ϴ��ɹ��Ŀ����Ӳ��ļ�
		LinkedList<String> finallylistfinion = new LinkedList<String>();  //����10.12.15.6�ϴ��ɹ��Ŀ��ٵ�����ļ�
		LinkedList<String> finallylistfinsnx = new LinkedList<String>();  //����10.12.15.6�ϴ��ɹ������ո���վ�ļ�
		LinkedList<String> finallylistfintro = new LinkedList<String>();  //����10.12.15.6�ϴ��ɹ������ն������ļ�
		LinkedList<String> finallylistfindcb = new LinkedList<String>();  //����10.12.15.6�ϴ��ɹ�������Ƶ��ƫ���ļ�
		LinkedList<String> finallylistfinsum = new LinkedList<String>();  //����10.12.15.6�ϴ��ɹ��������ܽ���Ϣ�ļ�
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //��ʾ�����1980.1.6Ϊ�ڼ���
		int weeknum = GetWeek.getWeek(new Date());    //��ʾ����Ϊ��һ�ܵĵڼ��죬����Ϊ������
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
		} //���������ļ���ȷ����ȡ��Ҫ��ȡ�ļ��е�·��
		String path = filepath[11];
		String finallyPath = filepath[16];
		String logPath = path+week+"_Sended.log";
		String finallyLogPath = finallyPath+week+"_Sended.log";
		String encoding="GBK";
        File file=new File(logPath);
        File finallyFile = new File(finallyLogPath);
        if(file.isFile() && file.exists()){
        	InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);          //���ǵ������ʽ
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
                    new FileInputStream(finallyFile),encoding);          //���ǵ������ʽ
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
