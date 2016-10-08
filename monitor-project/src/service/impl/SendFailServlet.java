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

import utils.CountWeeks;
import utils.GetWeek;
import utils.ParseXML;


public class SendFailServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LinkedList<String> listfail = new LinkedList<String>();  //�����ϴ�ʧ�ܵ��ļ�
		LinkedList<String> finallylistfail = new LinkedList<String>();  //����10.12.5.6�ϴ�ʧ�ܵ��ļ�
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //������
		String week = null;
		if(weeks<1000)
			week = "0"+weeks;
		int weeknum = GetWeek.getWeek(new Date());    //��ʾ����Ϊ��һ�ܵĵڼ��죬����Ϊ������
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} //���������ļ���ȷ����ȡ��Ҫ��ȡ�ļ��е�·��
		String path = filepath[11];          //192.168.7.10-->10.12.5.6log�ļ�·��
		String finallyPath = filepath[16];   //10.12.5.6-->�ۺϷ�������  log�ļ�·��
		String logPath = path+week+"_UnSended.log";
		String finallyLogPath = finallyPath+week+"_UnSended.log";
		//System.out.println(logPath);
		String encoding="GBK";
        File file=new File(logPath);
        File finallyFile = new File(finallyLogPath);
        if(file.isFile() && file.exists()){
        	InputStreamReader read = new InputStreamReader(
            new FileInputStream(file),encoding);          //���ǵ������ʽ
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
            	listfail.add(lineTxt);
            }
        }
        if(finallyFile.isFile() && finallyFile.exists()){
        	Long time =file.lastModified();   //���ش��ļ����һ�α��޸ĵ�ʱ�䣬��λΪ����
        	Calendar currentTime = Calendar.getInstance();   //�õ���ǰʱ��
        	long timeBlank = currentTime.getTimeInMillis()-time;    //�õ�ʱ����
        	if(timeBlank<=1000*60*10){
        		InputStreamReader read = new InputStreamReader(
        	            new FileInputStream(finallyFile),encoding);          //���ǵ������ʽ
        	            BufferedReader bufferedReader = new BufferedReader(read);
        	            String lineTxt = null;
        	            while((lineTxt = bufferedReader.readLine()) != null){
        	            	finallylistfail.add(lineTxt);
        	            }
        	}       	
        }
        request.setAttribute("weeks", weeks);
        session.setAttribute("listfail", listfail);
        session.setAttribute("finallylistfail", finallylistfail);
        request.getRequestDispatcher("/SendFailServlet.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
