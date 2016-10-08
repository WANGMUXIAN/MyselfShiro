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
		
		//���������������ļ�����
		Calendar calendar1 = Calendar.getInstance();
	    calendar1.set(Calendar.HOUR_OF_DAY, 12); // ����ʱ
	    calendar1.set(Calendar.MINUTE, 20);       // ���Ʒ�
	    calendar1.set(Calendar.SECOND, 0);       // ������
	    Date time1 = calendar1.getTime();         // �ó�ִ�������ʱ��,�˴�Ϊÿ���12��20��00
	    if(time1.before(new Date())){
	    	time1 = this.addDay(time1, 1);
	    }
		timer.schedule(new DailyMailTask(event.getServletContext()), time1, 1000*60*60*24);		
		
		//������������Сʱ�ļ�����
		Calendar calendar2 = Calendar.getInstance();
	    calendar2.set(Calendar.MINUTE, 0);       // ���Ʒ�
	    calendar2.set(Calendar.SECOND, 0);       // ������
	    Date time2 = calendar2.getTime();
		if(time2.before(new Date())){
			time2 = this.addHour(time2, 1);
		}
	    timer.schedule(new HourlyMailTask(event.getServletContext()), time2, 1000*60*60);
	    
	    //��Ʒ�ϴ������Ʒ�ʼ�
	    Calendar calendar3 = Calendar.getInstance();
	    calendar3.set(Calendar.MINUTE, 45);       // ���Ʒ�
	    calendar3.set(Calendar.SECOND, 0);       // ������
	    Date time3 = calendar3.getTime();         // �ó�ִ�������ʱ��
	    if(time3.before(new Date())){
	 	    time3 = this.addHour(time3, 1);
	 	}	    	 
	    timer.schedule(new SendSuFastMailTask(event.getServletContext()), time3, 1000*60*60);
	    
	    //��Ʒ�ϴ����ٲ�Ʒ�ʼ�
	    Calendar calendar4 = Calendar.getInstance();
	    calendar4.set(Calendar.HOUR_OF_DAY,11); // ����ʱ
	    calendar4.set(Calendar.MINUTE, 10);       // ���Ʒ�
	    calendar4.set(Calendar.SECOND, 0);       // ������
	    Date time4 = calendar4.getTime();         // �ó�ִ�������ʱ��,
	    if(time4.before(new Date())){
	    	time4 = this.addDay(time4, 1);
	    }
	    timer.schedule(new SendFastMailTask(event.getServletContext()), time4, 1000*60*60*24);
	    
	    //��Ʒ�ϴ����ղ�Ʒ�ʼ�
	    Calendar calendar5 = Calendar.getInstance();
	    calendar5.set(Calendar.HOUR_OF_DAY, 10); // ����ʱ
	    calendar5.set(Calendar.MINUTE, 35);       // ���Ʒ�
	    calendar5.set(Calendar.SECOND, 0);       // ������
	    Date time5 = calendar5.getTime();         // �ó�ִ�������ʱ��,
	    if(time5.before(new Date())){
	    	time5 = this.addDay(time5, 1);
	    }
	    timer.schedule(new SendFinalMailTask(event.getServletContext()), time5, 1000*60*60*24);
	    
	    //��Ʒ�ϴ����ղ�Ʒ��dcb���ʼ�
	    Calendar calendar6 = Calendar.getInstance();
	    calendar6.set(Calendar.HOUR_OF_DAY, 10); // ����ʱ
	    calendar6.set(Calendar.MINUTE, 35);       // ���Ʒ�
	    calendar6.set(Calendar.SECOND, 0);       // ������
	    Date time6 = calendar6.getTime();         // �ó�ִ�������ʱ��,
	    if(time6.before(new Date())){
	    	time6 = this.addDay(time6, 1);
	    }
	    timer.schedule(new SendDCBMailTask(event.getServletContext()), time6, 1000*60*60*24);
	    
	    //��Ʒ�ϴ�ʧ���ʼ�
//	    Calendar calendar7 = Calendar.getInstance();
//	    calendar7.set(Calendar.HOUR_OF_DAY, 23); // ����ʱ
//	    calendar7.set(Calendar.MINUTE, 35);       // ���Ʒ�
//	    calendar7.set(Calendar.SECOND, 0);       // ������
//	    Date time7 = calendar7.getTime();         // �ó�ִ�������ʱ��,
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