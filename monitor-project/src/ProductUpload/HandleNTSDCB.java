package ProductUpload;

import java.io.IOException;

import utils.WriteProductFileName;

public class HandleNTSDCB {
	public static void HandleNtsDcb(String YearFrom,int IntYearFrom,String MonthFrom,int IntMonthFrom,String DayFrom,int IntDayFrom,String HourFrom,int IntHourFrom,String YearTo,int IntYearTo,String MonthTo,int IntMonthTo,String DayTo,int IntDayTo,String HourTo,int IntHourTo,long WeeksFrom,long WeeksTo,int DayNumWeekFrom,int DayNumWeekTo) throws IOException
	{
		String speed="NTS";
		String endString=".dcb.Z";
		int i,j;
		long m,n;
		String mS="";
		//����ʼ������ϳ���λ�ַ���,һ���Ǳ����ܣ�����һ���Ǳ�����ǰһ��
		long WeekFromBeiDouPre,WeekFromBeiDou;
		WeekFromBeiDouPre=WeeksFrom-1357;
		WeekFromBeiDou=WeeksFrom-1356;
		String WeekFromBeiDouSPre="";
		String WeekFromBeiDouS="";
		//���㱱����ǰһ�ܵ�ǰ��λ�ַ���
		if(WeekFromBeiDouPre<1000)
		{
			WeekFromBeiDouSPre=new String("0"+WeekFromBeiDouPre);
		}
		if(WeekFromBeiDouPre<100)
		{
			WeekFromBeiDouSPre=new String("00"+WeekFromBeiDouPre);
		}
		if(WeekFromBeiDouPre<10)
		{
			WeekFromBeiDouSPre=new String("000"+WeekFromBeiDouPre);
		}
		//���㱱���ܵ���λ�ַ���
		if(WeekFromBeiDou<1000)
		{
			WeekFromBeiDouS=new String("0"+WeekFromBeiDou);
		}
		if(WeekFromBeiDou<100)
		{
			WeekFromBeiDouS=new String("00"+WeekFromBeiDou);
		}
		if(WeekFromBeiDou<10)
		{
			WeekFromBeiDouS=new String("000"+WeekFromBeiDou);
		}
		
		
		//������������ϳ���λ�ַ���,һ���Ǳ����ܣ�����һ���Ǳ�����ǰһ��
		long WeekToBeiDouPre,WeekToBeiDou;
		WeekToBeiDouPre=WeeksTo-1357;
		WeekToBeiDou=WeeksTo-1356;
		String WeekToBeiDouSPre="";
		String WeekToBeiDouS="";
		//�������ʱ�䱱����ǰһ�ܵ���λ�ַ���
		if(WeekToBeiDouPre<1000)
		{
			WeekToBeiDouSPre=new String("0"+WeekToBeiDouPre);
		}
		if(WeekToBeiDouPre<100)
		{
			WeekToBeiDouSPre=new String("00"+WeekToBeiDouPre);
		}
		if(WeekToBeiDouPre<10)
		{
			WeekToBeiDouSPre=new String("000"+WeekToBeiDouPre);
		}
		//�������ʱ�䱱���ܵ���λ�ַ���
		if(WeekToBeiDou<1000)
		{
			WeekToBeiDouS=new String("0"+WeekToBeiDou);
		}
		if(WeekToBeiDou<100)
		{
			WeekToBeiDouS=new String("00"+WeekToBeiDou);
		}
		if(WeekToBeiDou<10)
		{
			WeekToBeiDouS=new String("000"+WeekToBeiDou);
		}
		
		//���ղ�Ʒ��Ϊ�������ġ����ܼ���ĺͰ��¼���ģ����������°��¼���ģ���������������һ���24�㣬����ϱ��µ����ղ�Ʒ
		/*****����һ����Ϊ�������ж�����1.��ͬ�� 2.ͬ�겻ͬ��*****/
		/*****   1   *****/
		/*****��һ�´���***********/
		/*****������ж������ǡ��򡱣���������жϷ�֧�ȴ�����ͬ�겻ͬ�µ�һ�µ�����Ͳ�ͬ��ĵ�һ�µ����****/
		if(IntYearFrom!=IntYearTo||IntMonthFrom!=IntMonthTo)
		{
			//��һ���¿϶�Ҫ����������ٲ�Ʒ�Ĵ�����ͬ������������죬
			String StoreFileName1=speed+YearFrom+MonthFrom+endString;
			WriteProductFileName.write(StoreFileName1+"\r\n");
		}
		/********��ʼ���ڳ���ʼ���⣬�������������·�********/
		if(IntYearFrom!=IntYearTo)
		{
			String iS="";
			for(i=IntMonthFrom+1;i<=12;i++)
			{
				//���·ݴ���Ϊ��λ�ַ���
				if(i<10)
				{
					iS="0"+i;
				}
				else{
					iS=""+i;
				}
				String StoreFileName1=speed+YearFrom+iS+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
		/********���ꡢĩ����⣬�����м���********/
		for(m=IntYearFrom+1;m<=IntYearTo-1;m++)
		{
			for(n=0;n<=12;n++)
			{
				//���·ݴ���Ϊ��λ���ַ���
				String nS="";
				if(n<10)
				{
					nS="0"+n;
				}
				else{
					nS=""+n;
				}
				String StoreFileName2=speed+m+nS+endString;
				WriteProductFileName.write(StoreFileName2+"\r\n");
			}
		}
		/********"�������ڳ����һ���⣬�������������·�********/
		if(IntYearFrom!=IntYearTo)
		{
			for(i=0;i<=IntMonthTo-1;i++)
			{
				//���·ݴ���Ϊ��λ���ַ���
				String iS="";
				if(i<10)
				{
					iS="0"+i;
				}
				else{
					iS=""+i;
				}
					
				String StoreFileName1=speed+YearTo+iS+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
		/********����,���һ�´���********/
		if(IntYearFrom!=IntYearTo)
		{
			if(IntMonthTo==1&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"01"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==2&&(IntDayTo==28||IntDayTo==29)&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"02"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==3&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"03"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==4&&IntDayTo==30&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"04"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==5&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"05"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==6&&IntDayTo==30&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"06"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==7&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"07"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==8&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"08"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==9&&IntDayTo==30&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"09"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==10&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"10"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==11&&IntDayTo==30&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"11"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==12&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"12"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
//		/*******        2       ********/
//		/*******��ʼʱ����ͬһ����********/
//		if(WeeksFrom==WeeksTo&&DayNumWeekFrom==DayNumWeekTo)
//		{
//			//��ʼʱ��Сʱ��λ��0-2֮�䣬������������Ϊ0����ô����������������Ҫ��һ
//			if(IntHourFrom>=0&&IntHourFrom<2&&DayNumWeekFrom==0)
//			{	
//				//����ǰһ����ļ���
//				String StoreFileName1=speed+WeekFromBeiDouSPre+"6"+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//���㵱����ļ���
//				if(IntHourTo>=0&&IntHourTo<2)
//				{	
//				}
//				if(IntHourTo>=2&&IntHourTo<8)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+0+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//				}
//				if(IntHourTo>8&&IntHourTo<14)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+0+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+0+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//				}
//				if(IntHourTo>14&&IntHourTo<20)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+0+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+0+endString;
//					String StoreFileName4=speed+WeekFromBeiDouS+0+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//				}
//				if(IntHourTo>=20&&IntHourTo<=23)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+0+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+0+endString;
//					String StoreFileName4=speed+WeekFromBeiDouS+0+endString;
//					String StoreFileName5=speed+WeekFromBeiDouS+0+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//					WriteProductFileName.write(StoreFileName5+"\r\n");
//				}
//			}
//			//��ʼʱ��Сʱ��λ��0-2֮�䣬��������������Ϊ0����ô��������Ҫ��һ���������ü�һ
//			if(IntHourFrom>=0&&IntHourFrom<2&&DayNumWeekFrom!=0)
//			{
//				//����ǰһ����ļ���
//				String StoreFileName1=speed+WeekFromBeiDouS+(DayNumWeekFrom-1)+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//���㵱����ļ���
//				if(IntHourTo>=0&&IntHourTo<2)
//				{	
//				}
//				if(IntHourTo>=2&&IntHourTo<8)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//				}
//				if(IntHourTo>8&&IntHourTo<14)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//				}
//				if(IntHourTo>14&&IntHourTo<20)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//				}
//				if(IntHourTo>=20&&IntHourTo<=23)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//					WriteProductFileName.write(StoreFileName5+"\r\n");
//				}
//			}
//			if(IntHourFrom>=2&IntHourFrom<8)
//			{
//				//���㵱����ļ���
//				if(IntHourTo>=0&&IntHourTo<2)
//				{	
//				}
//				if(IntHourTo>=2&&IntHourTo<8)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//				}
//				if(IntHourTo>8&&IntHourTo<14)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//				}
//				if(IntHourTo>14&&IntHourTo<20)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//				}
//				if(IntHourTo>=20&&IntHourTo<=23)
//				{	
//					String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName2+"\r\n");
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//					WriteProductFileName.write(StoreFileName5+"\r\n");
//				}
//			}
//			if(IntHourFrom>=8&IntHourFrom<14)
//			{
//				if(IntHourTo>=0&&IntHourTo<2)
//				{	
//				}
//				if(IntHourTo>=2&&IntHourTo<8)
//				{	
//				}
//				if(IntHourTo>8&&IntHourTo<14)
//				{	
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//			
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//				}
//				if(IntHourTo>14&&IntHourTo<20)
//				{	
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//				}
//				if(IntHourTo>=20&&IntHourTo<=23)
//				{	
//					String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName3+"\r\n");
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//					WriteProductFileName.write(StoreFileName5+"\r\n");
//				}
//				
//			}	
//			if(IntHourFrom>=14&IntHourFrom<20)
//			{
//				if(IntHourTo>=0&&IntHourTo<2)
//				{	
//				}
//				if(IntHourTo>=2&&IntHourTo<8)
//				{	
//				}
//				if(IntHourTo>8&&IntHourTo<14)
//				{	
//				}
//				if(IntHourTo>14&&IntHourTo<20)
//				{	
//					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//				}
//				if(IntHourTo>=20&&IntHourTo<=23)
//				{	
//					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//					WriteProductFileName.write(StoreFileName4+"\r\n");
//					WriteProductFileName.write(StoreFileName5+"\r\n");
//				}
//			}
//			if(IntHourFrom>=20&IntHourFrom<=23)
//			{
//				//���㵱����ļ���
//				String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				WriteProductFileName.write(StoreFileName5+"\r\n");
//			}
//
//		}
		/********      2       *********/
		/********ͬ�겻ͬ�£���������ĵ�һ���Ѿ�������д��********/
		/********��ʼʱ����ͬһ���ڣ�������˵�һ�º����һ���������ʱ��********/
		if(IntYearFrom==IntYearTo&&IntMonthFrom!=IntMonthTo)
		{
			for(i=IntMonthFrom+1;i<=IntMonthTo-1;i++)
			{
				//���·ݴ���Ϊ��λ���ַ���
				String iS="";
				if(i<10)
				{
					iS="0"+i;
				}
				else{
					iS=""+i;
				}
				String StoreFileName1=speed+YearFrom+iS+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");

			}
		}
		/********��ʼʱ����ͬһ���ڣ��������һ��********/
		if(IntYearFrom==IntYearTo&&IntMonthFrom!=IntMonthTo)
		{
			if(IntMonthTo==1&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"01"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==2&&(IntDayTo==28||IntDayTo==29)&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"02"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==3&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"03"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==4&&IntDayTo==30&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"04"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==5&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"05"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==6&&IntDayTo==30&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"06"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==7&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"07"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==8&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"08"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==9&&IntDayTo==30&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"09"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==10&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"10"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==11&&IntDayTo==30&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"11"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==12&&IntDayTo==31&&IntHourTo==24)
			{	
				//���㵱�µ��ļ���
				String StoreFileName1=speed+YearTo+"12"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
	}

}
