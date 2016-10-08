package timemail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class SendFastMailTask extends TimerTask {
	
	private ServletContext context = null;
	public SendFastMailTask() {
		// TODO Auto-generated constructor stub
	}
	public SendFastMailTask(ServletContext context) {
		this.context = context;
	}
	
	@Override
	public void run() {
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
//		int hour = c.get(Calendar.HOUR_OF_DAY);
//		int minute = c.get(Calendar.MINUTE);
		int days = CountDay.countDay(year, month, date);//�õ�����Ϊ�����ڵĵڼ���
		int lowyear = year-2000;    //���������Ϊ2015�꣬����15��ʾ���		
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //��ʾ�����1980.1.6Ϊ�ڼ���
		int weeknum = GetWeek.getWeek(new Date());    //��ʾ����Ϊ��һ�ܵĵڼ��죬����Ϊ������
		weeknum = weeknum-1;
		days = days-1;
		if(weeknum<0){
			weeknum = weeknum+7;
			weeks = weeks-1;
		}
		if(days<=0){
			year = year-1;
			lowyear = lowyear-1;
			days = CountDay.countDay(year, 12, 31);
		}		
		String week = null;
		if(weeks<1000)
			week = "0"+weeks;
		String strdays = null;
		if(days<10)
			strdays = "00"+days;
		if(10<=days&days<100)
			strdays = "0"+days;
		if(days>=100)
			strdays = ""+days;
		String shouldSend = "Should send ";
		String sended = "Already sended ";
		String sendedsp3 = "";
		String sendedclk = "";
		String sendederp = "";
		String sendedion = "";
		String sp3 = "ntr"+week+weeknum+".sp3.Z";
		String clk = "ntr"+week+weeknum+".clk.Z";
		String erp = "ntr"+week+weeknum+".erp.Z";
		String ion = "ntrg"+strdays+"0"+"."+lowyear+"i"+".Z";
		shouldSend = shouldSend+sp3+"  "+clk+"  "+erp+"  "+ion;
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
        if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
        	InputStreamReader read;
			try {
				read = new InputStreamReader(new FileInputStream(file),encoding);  //���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("SP3")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+"")){
	            			String arr[] = lineTxt.split(" ");
	            			sendedsp3 = arr[0];
	            		}	
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("CLK")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+"")){
	            			String arr[] = lineTxt.split(" ");
	            			sendedclk = arr[0];
	            		}		
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("ERP")){
	            		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(7, 8).equals(weeknum+"")){
	            			String arr[] = lineTxt.split(" ");
	            			sendederp = arr[0];
	            		}
	            			
	            	}
	            	if(lineTxt.toUpperCase().contains("NTR")&lineTxt.toUpperCase().contains("I")){
	            		
	            		if(lineTxt.substring(4, 8).equals(strdays+"0")&lineTxt.substring(9, 11).equals(lowyear+"")){
	            			String arr[] = lineTxt.split(" ");
	            			sendedion = arr[0];
	            		}	
	            	}
	            }
	            read.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}                    
        }
        String to = filepath[15];     //�ʼ����͵�ַ
		String title = "send fast product";
		String content = shouldSend+"\n"+sended+"   "+sendedsp3+"   "+sendedclk+"   "+sendederp+"  "+sendedion;
		List<String> toList = new ArrayList();
		toList.add(to);
		boolean res = SendMail.send(toList, title, content);
		if( res == true )
			System.out.println("���ͳɹ�");
	}
}
