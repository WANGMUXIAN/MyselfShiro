package timemail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import utils.CountDay;
import utils.CountWeeks;
import utils.GetWeek;
import utils.ParseXML;
import utils.SendMail;

public class SendFinalMailTask extends TimerTask {
	
	private ServletContext context = null;
	public SendFinalMailTask() {
		// TODO Auto-generated constructor stub
	}
	public SendFinalMailTask(ServletContext context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //��ʾ�����1980.1.6Ϊ�ڼ���
		int weeknum = GetWeek.getWeek(new Date());    //��ʾ����Ϊ��һ�ܵĵڼ��죬����Ϊ������
		int days = CountDay.countDay(year, month, date);//�õ�����Ϊ�����ڵĵڼ���
		if(weeknum==2){
			String shouldSend = "Should send ";
			String sended = "Already send ";
			String sp3 = "";
			String sendedsp3 = "";
			String clk = "";
			String sendedclk = "";
			String snx = "";
			String sendedsnx = "";
			String erp = "";
			String sendederp = "";
			String tro = "";
			String sendedtro = "";
			String ion = "";
			String sendedion = "";
			String dcb = "";
			String sendeddcb = "";
			String sum = "";
			String sendedsum = "";
			weeks = weeks-2;
			String week = null;
			if(weeks<1000)
				week = "0"+weeks;
			for(int i=0;i<=6;i++){
				sp3 = sp3+"nts"+week+i+".sp3.Z"+"  ";
			}
			clk = "nts"+week+7+".clk.Z";
			snx = "nts"+week+7+".snx.Z";
			erp = "nts"+week+7+".erp.Z";
			for(int i=0;i<=6;i++){
				tro = tro+"nts"+week+i+".tro.Z"+"  ";
			}
			
			//����Ӧ���ϴ��ĵ�����Ʒ
			days = days-16;
			int sumdays = 0;
			if(year%4==0){
				sumdays = 366;
			}
			else
				sumdays = 365;
			if(days<=0){
				year = year-1;
				days = days+sumdays;   //�õ����ڼ�ȥ16��������
			}
			year=year-2000;
			for(int i=0;i<=6;i++){
				if(days>sumdays){   //�������մ��ڵ�������������1��days��1
					year = year+1;
					days = 1;
				}
				days = days+1;
				String strdays = null;
				if(days<10)
					strdays = "00"+days;
				if(10<=days&days<100)
					strdays = "0"+days;
				if(days>=100)
					strdays = ""+days;
				ion = ion+"ntsg"+strdays+"0"+"."+year+"i"+".Z"+"  ";
			}
			
			sum = "nts"+week+7+".sum.Z";
			//Ӧ��Ҫ���͵Ĳ�Ʒ
			shouldSend = shouldSend+"\n"+sp3+"\n"+clk+"\n"+snx+"\n"+erp+"\n"+tro+"\n"+ion+"\n"+sum+"\n";
			
			String[] filepath = null;
			try {
				filepath = ParseXML.ReadXML();
			} catch (Exception e) {
				e.printStackTrace();
			} //���������ļ���ȷ����ȡ��Ҫ��ȡ�ļ��е�·��
			String path = filepath[16];
			String logPath = path+week+"_Sended.log";
			String encoding="GBK";
	        File file=new File(logPath);
	        if(file.isFile() && file.exists()){
	        	InputStreamReader read;
				try {
					read = new InputStreamReader(new FileInputStream(file),encoding);//���ǵ������ʽ
					BufferedReader bufferedReader = new BufferedReader(read);
	                String lineTxt = null;
	                try {
						while((lineTxt = bufferedReader.readLine()) != null){
							if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SP3")&lineTxt.substring(3, 7).equals(week)){
								String arr[] = lineTxt.split(" ");
								sendedsp3 = sendedsp3+arr[0]+"  ";
							}
							if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("CLK")&lineTxt.substring(3, 7).equals(week)){
								String arr[] = lineTxt.split(" ");
								sendedclk = sendedclk+arr[0]+"  ";
							}
							if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("TRO")&lineTxt.substring(3, 7).equals(week)){
								String arr[] = lineTxt.split(" ");
								sendedtro  = sendedtro+arr[0]+"  ";
							}
							if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("I")){
								String arr[] = lineTxt.split(" ");
								sendedion  = sendedion+arr[0]+"  ";
							}
							if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SNX")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
								String arr[] = lineTxt.split(" ");
								sendedsnx  = sendedsnx+arr[0]+"  ";
							}
							if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("SUM")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
								String arr[] = lineTxt.split(" ");
								sendedsum  = sendedsum+arr[0]+"  ";
							}
							if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("ERP")&lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals("7")){
								String arr[] = lineTxt.split(" ");
								sendederp  = sendederp+arr[0]+"  ";
							}
						}
						read.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                     
	        }
	        sended = sended+"\n"+sendedsp3+"\n"+sendedclk+"\n"+sendedtro+"\n"+sendedion+"\n"+sendedsnx+"\n"+sendedsum+"\n"+sendederp+"\n";
	        String to = filepath[15];     //�ʼ����͵�ַ
			String title = "final product";
			String content = shouldSend+sended;
			List<String> toList = new ArrayList();
			toList.add(to);
			boolean res = SendMail.send(toList, title, content);
		}
	}
}
