package timemail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

public class ErrorMailTask extends TimerTask {
	private ServletContext context = null;
	public ErrorMailTask(){
		super();
	}
	
	public ErrorMailTask(ServletContext context){
		this.context = context;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //北斗周
		String week = null;
		if(weeks<1000)
			week = "0"+weeks;
		int weeknum = GetWeek.getWeek(new Date());    //表示今天为这一周的第几天，周日为第零天
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} //解析配置文件
		String to = filepath[15];     //邮件发送地址
		String title = "send fail product";
		String content = "";    //邮件内容
		String finallyPath = filepath[16];   //10.12.5.6-->综合服务中心  log文件路径
		String finallyLogPath = finallyPath+week+"_UnSended.log";
		//System.out.println(logPath);
		String encoding="GBK";
        File finallyFile = new File(finallyLogPath);
        if(finallyFile.isFile() && finallyFile.exists()){ //判断文件是否存在
        	InputStreamReader read;
			try {
				read = new InputStreamReader(new FileInputStream(finallyFile),encoding);  //考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
	            String lineTxt = null;
	            while((lineTxt = bufferedReader.readLine()) != null){
	            	if(lineTxt.contentEquals(year+"")&lineTxt.contentEquals(month+"")&lineTxt.contentEquals(date+"")&lineTxt.contentEquals(hour+"")&lineTxt.contentEquals((minute-1)+"")){
	            		content = content+lineTxt+"\n";
	            	}
	            }
	            }catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        if(!content.equals("")){
        	List<String> toList = new ArrayList();
    		toList.add(to);
    		boolean res = SendMail.send(toList, title, content);
    		if( res == true )
    			System.out.println("发送成功");
        }
	}
}
