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

public class SendDCBMailTask extends TimerTask {
	
	private ServletContext context = null;
	public SendDCBMailTask() {
		// TODO Auto-generated constructor stub
	}
	public SendDCBMailTask(ServletContext context) {
		this.context = context;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //表示今天距1980.1.6为第几周
		int weeknum = GetWeek.getWeek(new Date());    //表示今天为这一周的第几天，周日为第零天
		int days = CountDay.countDay(year, month, date);//得到日期为该年内的第几天
		String content = "";
		if(date==1){
			String shouldSend = "Should send "+"\n";
			String sended = "Already send "+"\n";
			String sendeddcb = "";
			String dcb = "";
			if(month<10){
				dcb = dcb+"nts"+year+"0"+month+".dcb.Z";
			}
			else
				dcb = dcb+"nts"+year+month+".dcb.Z";
			shouldSend = shouldSend +dcb;
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
			String path = filepath[16];
			String logPath = path+week+"_Sended.log";
			String encoding="GBK";
	        File file=new File(logPath);
	        if(file.isFile() && file.exists()){
	        	InputStreamReader read;
				try {
					read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
	                String lineTxt = null;
	                try {
						while((lineTxt = bufferedReader.readLine()) != null){
							if(lineTxt.toUpperCase().contains("NTS")&lineTxt.toUpperCase().contains("DCB")){
								String arr[] = lineTxt.split(" ");
								sendeddcb = sendeddcb+arr[0]+"  ";
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
	        String to = filepath[15];     //邮件发送地址
			String title = "fianl dcb product";
			sended = sended+sendeddcb;
			content = shouldSend+"\n"+sended;
			List<String> toList = new ArrayList();
			toList.add(to);
			boolean res = SendMail.send(toList, title, content);
		}
	}
}
