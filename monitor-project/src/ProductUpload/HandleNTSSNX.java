package ProductUpload;

import java.io.IOException;

import utils.WriteProductFileName;

//����վ���������ļ������ղ�Ʒ
public class HandleNTSSNX {
	public static void HandleNtsSnx(int IntHourFrom,int IntHourTo,long WeeksFrom,long WeeksTo,int DayNumWeekFrom,int DayNumWeekTo) throws IOException
	{
		String speed="NTS";
		String endString=".snx.Z";
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
		
		//���ղ�Ʒ��Ϊ�������ġ����ܼ���ĺͰ��¼���ģ����������°��ܼ���ģ������������������24�㣬����ϱ��ܵ����ղ�Ʒ
		/*****������һ���ж�������ͬ��*****/
		/*****   1   *****/
		/*****��һ�ܴ���***********/
		
		//���ܿ϶�Ҫ
		String StoreFileName1=speed+WeekFromBeiDouS+"7"+endString;
		WriteProductFileName.write(StoreFileName1+"\r\n");
		
		/********���ܡ�ĩ�ܳ��⣬�����м���********/
		for(m=WeekFromBeiDou+1;m<=WeekToBeiDou-1;m++)
		{
			//��Ϊ�м���Ҳ��Ҫ�ܵ��ַ���������ҲҪ��������
			if(m<1000)
			{
				mS=new String("0"+m);
			}
			if(m<100)
			{
				mS=new String("00"+m);
			}
			if(m<10)
			{
				mS=new String("000"+m);
			}
			String StoreFileName2=speed+mS+"7"+endString;
			WriteProductFileName.write(StoreFileName2+"\r\n");
		}
		/********���������********/
		if(WeeksFrom!=WeeksTo&&DayNumWeekTo==6&&IntHourTo==24)
		{
			String StoreFileName2=speed+WeekToBeiDouS+"7"+endString;
			WriteProductFileName.write(StoreFileName2+"\r\n");
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
//		/********      2       *********/
//		/********ͬ�ܲ�ͬ�죬��������ĵ�һ���Ѿ�������д��********/
//		/********��ʼʱ����ͬһ���ڣ�������˵�һ������һ���������ʱ��********/
//		if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
//		{
//			for(i=DayNumWeekFrom+1;i<=DayNumWeekTo-1;i++)
//			{
//				String StoreFileName1=speed+WeekFromBeiDouS+i+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
//
//			}
//		}
//		/********��ʼʱ����ͬһ���ڣ��������һ��********/
//		if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
//		{
//			if(IntHourTo==24)
//			{
//				String StoreFileName2=speed+WeekToBeiDouS+DayNumWeekTo+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
//			}
//		}
		
	}

}
