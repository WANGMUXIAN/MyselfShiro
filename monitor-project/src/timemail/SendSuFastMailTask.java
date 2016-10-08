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

import utils.CountWeeks;
import utils.GetWeek;
import utils.ParseXML;
import utils.SendMail;

public class SendSuFastMailTask extends TimerTask {
	
	private ServletContext context = null;
	public SendSuFastMailTask(){
		super();
	}
	
	public SendSuFastMailTask(ServletContext context){
		this.context = context;
	}
	@Override
	public void run() {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //表示今天距1980.1.6为第几周
		int weeknum = GetWeek.getWeek(new Date());    //表示今天为这一周的第几天，周日为第零天
//		if(hour==0){
//			weeknum = weeknum-1;
//			if(weeknum<0){
//				weeks = weeks-1;
//				weeknum = weeknum+7;
//			}		
//		}
//		if(hour==1){
//			weeknum = weeknum-1;
//			if(weeknum<0){
//				weeks = weeks-1;
//				weeknum = weeknum+7;
//			}
//		}
		String week = null;
		if(weeks<1000)
			week = "0"+weeks;
		String shouldSend = "Should send ";
		String sended = "Already sended ";
		String sendedsp3 = "";
		String sendederp = "";
		String sendedtro = "";
		if(hour==1){
			String sp3 = "ntu"+week+weeknum+"_"+"00"+".sp3.z";
			String erp = "ntu"+week+weeknum+"_"+"00"+".erp.z";
			String tro = "ntu"+week+weeknum+"_"+"00"+".tro.z";
			shouldSend = shouldSend+sp3+" "+erp+" "+tro;
		}
		if(hour==7){
			String sp3 = "ntu"+week+weeknum+"_"+"06"+".sp3.z";
			String erp = "ntu"+week+weeknum+"_"+"06"+".erp.z";
			String tro = "ntu"+week+weeknum+"_"+"06"+".tro.z";
			shouldSend = shouldSend+sp3+" "+erp+" "+tro;
		}
		if(hour==13){
			
			String sp3 = "ntu"+week+weeknum+"_"+"12"+".sp3.z";
			String erp = "ntu"+week+weeknum+"_"+"12"+".erp.z";
			String tro = "ntu"+week+weeknum+"_"+"12"+".tro.z";
			shouldSend = shouldSend+sp3+" "+erp+" "+tro;	
		}
		if(hour==19){
			String sp3 = "ntu"+week+weeknum+"_"+"18"+".sp3.z";
			String erp = "ntu"+week+weeknum+"_"+"18"+".erp.z";
			String tro = "ntu"+week+weeknum+"_"+"18"+".tro.z";
			shouldSend = shouldSend+sp3+" "+erp+" "+tro;		
		}
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} //解析配置文件，确定读取所要读取文件夹的路径
		String to = filepath[15];     //邮件发送地址
		String title = "super fast product";
		String content = "";
		String path = filepath[16];
		String logPath = path+week+"_Sended.log";
		String encoding="GBK";
        File file=new File(logPath);
        if(file.isFile() && file.exists()){ //判断文件是否存在
            InputStreamReader read;
			try {
				read = new InputStreamReader(new FileInputStream(file),encoding);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("SP3")){             	
	                	if(hour==1){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendedsp3 = arr[0];
	                    	}
	                	}
	                	if(hour==7){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendedsp3 = arr[0];
	                    	}
	                	}
	                	
	                	if(hour==13){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendedsp3 = arr[0];
	                    	}
	                	}
	                	
	                	if(hour==19){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendedsp3 = arr[0];
	                    	}
	                	}
	                	
	                }
	                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("ERP")){
	                	
	                	if(hour==1){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendederp = arr[0];
	                    	}
	                	}
	                	
	                	if(hour==7){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendederp = arr[0];
	                    	}
	                	}
	                	
	                	if(hour==13){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendederp = arr[0];
	                    	}
	                	}
	                	
	                	if(hour==19){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendederp = arr[0];
	                    	}
	                	}
	                	
	                }
	                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("TRO")){
	                	
	                	if(hour==1){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendedtro = arr[0];
	                    	}
	                	}
	                	
	                	if(hour==7){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendedtro = arr[0];
	                    	}
	                	}
	                	
	                	if(hour==13){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendedtro = arr[0];
	                    	}
	                	}
	                	
	                	if(hour==19){
	                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
	                			String arr[] = lineTxt.split(" ");
	                			sendedtro = arr[0];
	                    	}
	                	}
	                	
	                }              
	            }
	            read.close();
			} catch (Exception e) {
				e.printStackTrace();
			}            
        }
        content = shouldSend+"\n"+sended+"   "+sendedsp3+"   "+sendederp+"   "+sendedtro;
        //System.out.println(content);
        List<String> toList = new ArrayList();
        toList.add(to);
        if(hour==1||hour==7||hour==13||hour==19){      //只在时间为1、7、13、19时发送邮件
        	boolean res = SendMail.send(toList, title, content);
    		if( res == true )
    			System.out.println("发送成功");
        }	
	}		
}
