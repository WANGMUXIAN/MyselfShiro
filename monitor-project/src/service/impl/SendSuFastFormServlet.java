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


public class SendSuFastFormServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LinkedList<String> listssp3 = new LinkedList<String>();  //�����ϴ��ɹ��ĳ�������Ʒ
		LinkedList<String> listserp = new LinkedList<String>();  //�����ϴ��ɹ��ĳ��������ת�����ļ�
		LinkedList<String> liststro = new LinkedList<String>();  //�����ϴ��ɹ��ĳ���������ļ�
		LinkedList<String> finallylistssp3 = new LinkedList<String>();  //����10.12.5.6�ϴ��ɹ��ĳ�������Ʒ
		LinkedList<String> finallylistserp = new LinkedList<String>();  //����10.12.5.6�ϴ��ɹ��ĳ��������ת�����ļ�
		LinkedList<String> finallyliststro = new LinkedList<String>();  //����10.12.5.6�ϴ��ɹ��ĳ���������ļ�
		Calendar c = Calendar.getInstance();
		int currentyear = c.get(Calendar.YEAR);
		int currentmonth = c.get(Calendar.MONTH)+1;
		int currentdate = c.get(Calendar.DATE);
		int currenthour = c.get(Calendar.HOUR_OF_DAY);
		//�õ��û������ʱ��
		String stringyear = request.getParameter("year");
		String stringmonth = request.getParameter("month");
		String stringdate = request.getParameter("date");
		String stringhour = request.getParameter("hour");
		if(stringyear==""||stringyear==null||stringmonth==""||stringmonth==null||stringdate==""||stringdate==null||stringhour==""||stringhour==null){
			request.setAttribute("message", "���ڲ���Ϊ�գ�����������");
			request.getRequestDispatcher("/SendSuFastServlet.jsp").forward(request, response);
			return ;
		}
		int year = Integer.parseInt(stringyear);
		int month = Integer.parseInt(stringmonth);
		int date = Integer.parseInt(stringdate);
		int hour = Integer.parseInt(stringhour);
		int date1 = MonthDays.days(year, month);   //�����û�������꣬�£���������µ�����		
		if(year>currentyear||month>12||date>date1||hour>23){
			request.setAttribute("message", "������������������");
			request.getRequestDispatcher("/SendSuFastServlet.jsp").forward(request, response);
			return ;
		}
		if(year==currentyear){
			if(month>currentmonth||date>date1||hour>23){
				request.setAttribute("message", "������������������");
				request.getRequestDispatcher("/SendSuFastServlet.jsp").forward(request, response);
				return ;
			}
			if((month==currentmonth&date==currentdate)&hour>currenthour){
				request.setAttribute("message", "������������������");
				request.getRequestDispatcher("/SendSuFastServlet.jsp").forward(request, response);
				return ;
			}
		}
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //��ʾ�û�����ʱ���1980.1.6Ϊ�ڼ���
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date endDate = new Date();
		try {
			endDate= format.parse(endDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int weeknum = GetWeek.getWeek(endDate);    //��ʾ����Ϊ��һ�ܵĵڼ��죬����Ϊ������
		if(hour>=0&hour<2){
			weeknum = weeknum-1;
			if(weeknum<0){
				weeks = weeks-1;
				weeknum = weeknum+7;
			}
		}
		if(hour>=0&hour<2)
			request.setAttribute("hour", "18");
		if(hour>=2&hour<8)
			request.setAttribute("hour", "00");
		if(hour>=8&hour<14)
			request.setAttribute("hour", "06");
		if(hour>=14&hour<20)
			request.setAttribute("hour", "12");
		if(hour>=20&hour<=23)
			request.setAttribute("hour", "18");
		request.setAttribute("weeks", weeks);
		request.setAttribute("weeknum", weeknum);
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
        if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
            InputStreamReader read = new InputStreamReader(
            new FileInputStream(file),encoding);          //���ǵ������ʽ
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("SP3")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		}                		
                	}
                }
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("ERP")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		}                		
                	}
                }
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("TRO")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		}                		
                	}
                }                
            }
            read.close();
            }
        if(finallyFile.isFile() && finallyFile.exists()){ //�ж��ļ��Ƿ����
            InputStreamReader read = new InputStreamReader(
            new FileInputStream(finallyFile),encoding);          //���ǵ������ʽ
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("SP3")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		}                		
                	}
                }
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("ERP")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		}                		
                	}
                }
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("TRO")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		}                		
                	}
                }                
            }
            read.close();
            }
        session.setAttribute("listssp3", listssp3);
        session.setAttribute("listserp", listserp);
        session.setAttribute("liststro", liststro);
        session.setAttribute("finallylistssp3", finallylistssp3);
        session.setAttribute("finallylistserp", finallylistserp);
        session.setAttribute("finallyliststro", finallyliststro);
        request.getRequestDispatcher("/SendSuFastServlet.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
