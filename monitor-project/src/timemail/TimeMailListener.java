package timemail;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimeMailListener implements ServletContextListener {
	
	private Timer timer = null;
	public void contextInitialized(ServletContextEvent event) {
		timer = new Timer();
		
		//数据主动下载天文件报警
		Calendar calendar1 = Calendar.getInstance();
	    calendar1.set(Calendar.HOUR_OF_DAY, 12); // 控制时
	    calendar1.set(Calendar.MINUTE, 20);       // 控制分
	    calendar1.set(Calendar.SECOND, 0);       // 控制秒
	    Date time1 = calendar1.getTime();         // 得出执行任务的时间,此处为每天的12：20：00
	    if(time1.before(new Date())){
	    	time1 = this.addDay(time1, 1);
	    }
		timer.schedule(new DailyMailTask(event.getServletContext()), time1, 1000*60*60*24);		
		
		//数据主动下载小时文件报警
		Calendar calendar2 = Calendar.getInstance();
	    calendar2.set(Calendar.MINUTE, 0);       // 控制分
	    calendar2.set(Calendar.SECOND, 0);       // 控制秒
	    Date time2 = calendar2.getTime();
		if(time2.before(new Date())){
			time2 = this.addHour(time2, 1);
		}
	    timer.schedule(new HourlyMailTask(event.getServletContext()), time2, 1000*60*60);
	    
	    //产品上传超快产品邮件
	    Calendar calendar3 = Calendar.getInstance();
	    calendar3.set(Calendar.MINUTE, 45);       // 控制分
	    calendar3.set(Calendar.SECOND, 0);       // 控制秒
	    Date time3 = calendar3.getTime();         // 得出执行任务的时间
	    if(time3.before(new Date())){
	 	    time3 = this.addHour(time3, 1);
	 	}	    	 
	    timer.schedule(new SendSuFastMailTask(event.getServletContext()), time3, 1000*60*60);
	    
	    //产品上传快速产品邮件
	    Calendar calendar4 = Calendar.getInstance();
	    calendar4.set(Calendar.HOUR_OF_DAY,11); // 控制时
	    calendar4.set(Calendar.MINUTE, 10);       // 控制分
	    calendar4.set(Calendar.SECOND, 0);       // 控制秒
	    Date time4 = calendar4.getTime();         // 得出执行任务的时间,
	    if(time4.before(new Date())){
	    	time4 = this.addDay(time4, 1);
	    }
	    timer.schedule(new SendFastMailTask(event.getServletContext()), time4, 1000*60*60*24);
	    
	    //产品上传最终产品邮件
	    Calendar calendar5 = Calendar.getInstance();
	    calendar5.set(Calendar.HOUR_OF_DAY, 10); // 控制时
	    calendar5.set(Calendar.MINUTE, 35);       // 控制分
	    calendar5.set(Calendar.SECOND, 0);       // 控制秒
	    Date time5 = calendar5.getTime();         // 得出执行任务的时间,
	    if(time5.before(new Date())){
	    	time5 = this.addDay(time5, 1);
	    }
	    timer.schedule(new SendFinalMailTask(event.getServletContext()), time5, 1000*60*60*24);
	    
	    //产品上传最终产品（dcb）邮件
	    Calendar calendar6 = Calendar.getInstance();
	    calendar6.set(Calendar.HOUR_OF_DAY, 10); // 控制时
	    calendar6.set(Calendar.MINUTE, 35);       // 控制分
	    calendar6.set(Calendar.SECOND, 0);       // 控制秒
	    Date time6 = calendar6.getTime();         // 得出执行任务的时间,
	    if(time6.before(new Date())){
	    	time6 = this.addDay(time6, 1);
	    }
	    timer.schedule(new SendDCBMailTask(event.getServletContext()), time6, 1000*60*60*24);
	    
	    //产品上传失败邮件
//	    Calendar calendar7 = Calendar.getInstance();
//	    calendar7.set(Calendar.HOUR_OF_DAY, 23); // 控制时
//	    calendar7.set(Calendar.MINUTE, 35);       // 控制分
//	    calendar7.set(Calendar.SECOND, 0);       // 控制秒
//	    Date time7 = calendar7.getTime();         // 得出执行任务的时间,
//	    if(time7.before(new Date())){
//	    	time7 = this.addDay(time7, 1);
//	    }
//	    timer.schedule(new SendFailMailTask(event.getServletContext()), time7, 1000*60*60*24);
	    timer.schedule(new ErrorMailTask(event.getServletContext()), 0, 1000*60);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
	public Date addDay(Date date,int num){
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	public Date addHour(Date date,int num){
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.HOUR_OF_DAY, num);
		return startDT.getTime();
	}
}