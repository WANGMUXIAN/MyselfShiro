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
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import utils.CountWeeks;
import utils.GetWeek;
import utils.ParseXML;
import utils.SendMail;

public class SendFailMailTask extends TimerTask {
	
	private ServletContext context = null;
	public SendFailMailTask() {
		// TODO Auto-generated constructor stub
	}
	public SendFailMailTask(ServletContext context) {
		this.context = context;
	}
	
	@Override
	public void run() {
		LinkedList<String> listfail = new LinkedList<String>();  //�����ϴ�ʧ�ܵ��ļ�
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
		if(weeknum==2){
			String[] filepath = null;
			try {
				filepath = ParseXML.ReadXML();
			} catch (Exception e) {
				e.printStackTrace();
			} //���������ļ���ȷ����ȡ��Ҫ��ȡ�ļ��е�·��
			String path = filepath[16];
			String logPath = path+week+"_UnSended.log";
			//System.out.println(logPath);
			String encoding="GBK";
	        File file=new File(logPath);
	        if(file.isFile() && file.exists()){
	        	InputStreamReader read;
				try {
					read = new InputStreamReader(new FileInputStream(file),encoding);
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
		            try {
						while((lineTxt = bufferedReader.readLine()) != null){
							String arr[] = lineTxt.split(" ");
							listfail.add(arr[0]);
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
				}          //���ǵ������ʽ  
	        }
	        int size = listfail.size();
			String[] fail = (String[])listfail.toArray(new String[size]);
			String content = "";
			for(int i=0;i<fail.length;i++){
				content = content+fail[i]+"  ";
				if(i%5==0){
					content = content+"\n";
				}
			}
			String to = filepath[15];     //�ʼ����͵�ַ
			String title = "send fail product";
			//System.out.println(content);
			List<String> toList = new ArrayList();
			toList.add(to);
			boolean res = SendMail.send(toList, title, content);
		}
		//System.out.println("ʧ�ܲ�Ʒ");
	}
}
