package ProductUpload;

import java.io.IOException;

import utils.WriteProductFileName;
//�������sp3�ļ�,���ǹ���Ŀ��ٲ�Ʒ
public class HandleNTRSP3old {
	public static void HandleNtrSp3(int IntHourFrom,int IntHourTo,long WeeksFrom,long WeeksTo,int DayNumWeekFrom,int DayNumWeekTo) throws IOException
	{
		String speed="NTR";
		String endString=".sp3.Z";
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
		
		//���ֿ��ٲ�Ʒ��Ҫ������ǰһ��Ĳ�Ʒ�����С��13�㣬��ô������ǰ����ļ��ɣ���Ϊǰ����Ĳ�Ʒ��ǰһ��϶��ᴦ��
		/*****����һ����Ϊ�������ж�����1.��ͬ�� 2.ͬ�ܲ�ͬ��*****/
		/*****   1   *****/
		/*****��һ��ʱ�䴦��***********/
		/*****������ж������ǡ��򡱣���������жϷ�֧�ȴ�����ͬ�ܲ�ͬ���һ�������Ͳ�ͬ�ܵ�һ������****/
		if(WeeksFrom!=WeeksTo||DayNumWeekFrom!=DayNumWeekTo)
		{
			
			if(IntHourFrom>=13&&DayNumWeekFrom==0)
			{	
				//����ǰһ����ļ���
				String StoreFileName1=speed+WeekFromBeiDouSPre+"6"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//���㵱����ļ���
//				String StoreFileName2=speed+WeekFromBeiDouS+"0"+endString;
//				String StoreFileName3=speed+WeekFromBeiDouS+"0"+endString;
//				String StoreFileName4=speed+WeekFromBeiDouS+"0"+endString;
//				String StoreFileName5=speed+WeekFromBeiDouS+"0"+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
//				WriteProductFileName.write(StoreFileName3+"\r\n");
//				WriteProductFileName.write(StoreFileName4+"\r\n");
//				WriteProductFileName.write(StoreFileName5+"\r\n");
				
			}
			
			
			if(IntHourFrom>=13&&DayNumWeekFrom!=0)
			{
				//����ǰһ����ļ���
				String StoreFileName1=speed+WeekFromBeiDouS+(DayNumWeekFrom-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//���㵱����ļ���
//				String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
//				WriteProductFileName.write(StoreFileName3+"\r\n");
//				WriteProductFileName.write(StoreFileName4+"\r\n");
//				WriteProductFileName.write(StoreFileName5+"\r\n");
			}
			//С��13�㣬Ӧ���ϴ�ǰ����Ĳ�Ʒ������ǰ����Ĳ�Ʒ�Ѿ���ǰһ����ϴ��ˣ��д�����
			if(IntHourFrom<13&&DayNumWeekFrom==0)
			{
				//����ǰһ����ļ���
				String StoreFileName1=speed+WeekFromBeiDouSPre+"6"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntHourFrom<13&&DayNumWeekFrom==1)
			{
				//����ǰһ����ļ���
				String StoreFileName1=speed+WeekFromBeiDouS+(DayNumWeekFrom-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
				
			}
				
			if(IntHourFrom<13&&DayNumWeekFrom>1)
			{
				//����ǰһ����ļ���
				String StoreFileName1=speed+WeekFromBeiDouS+(DayNumWeekFrom-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
		/********��ʼ���ڳ���һ���⣬������������ʱ��********/
		//��ʼʱ�䲻��ͬһ����
		if(WeeksFrom!=WeeksTo)
		{
			for(i=DayNumWeekFrom+1;i<=6;i++)
			{
				String StoreFileName1=speed+WeekFromBeiDouS+(i-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
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
			
			//����ǰһ�ܵ��������ַ���
			long k=m-1;
			String KMS="";
			if(k<1000)
			{
				KMS=new String("0"+k);
			}
			if(k<100)
			{
				KMS=new String("00"+k);
			}
			if(k<10)
			{
				KMS=new String("000"+k);
			}
			
			
			for(n=0;n<=6;n++)
			{
				//��Ϊ���ֿ��ٲ�Ʒ����Ҫ��Ե���ǰ��Ĳ�Ʒ�����Ը����ܵĵ�һ�촦��������ܵ����һ��
				if(n==0)
				{
					String StoreFileName1=speed+KMS+6+endString;
					WriteProductFileName.write(StoreFileName1+"\r\n");
				}
				else{
					String StoreFileName2=speed+mS+(n-1)+endString;
					WriteProductFileName.write(StoreFileName2+"\r\n");
				}
			}
		}
		//��ʼʱ�䲻��ͬһ����
		/********"�������ڳ����һ���⣬������������ʱ��********/
		if(WeeksFrom!=WeeksTo)
		{
			for(i=0;i<=DayNumWeekTo-1;i++)
			{
				if(i==0)
				{
					String StoreFileName1=speed+WeekToBeiDouSPre+"6"+endString;
					WriteProductFileName.write(StoreFileName1+"\r\n");
				}
				else
				{
					String StoreFileName1=speed+WeekToBeiDouS+(i-1)+endString;
					WriteProductFileName.write(StoreFileName1+"\r\n");
				}
			}
		}
		/********����,���һ�촦��********/
		if(WeeksFrom!=WeeksTo)
		{
			if(IntHourTo>=13&&DayNumWeekTo==0)
			{	
				//����ǰһ����ļ���
				String StoreFileName1=speed+WeekToBeiDouSPre+"6"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//���㵱����ļ���
//				String StoreFileName2=speed+WeekFromBeiDouS+"0"+endString;
//				String StoreFileName3=speed+WeekFromBeiDouS+"0"+endString;
//				String StoreFileName4=speed+WeekFromBeiDouS+"0"+endString;
//				String StoreFileName5=speed+WeekFromBeiDouS+"0"+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
//				WriteProductFileName.write(StoreFileName3+"\r\n");
//				WriteProductFileName.write(StoreFileName4+"\r\n");
//				WriteProductFileName.write(StoreFileName5+"\r\n");
				
			}
			
			
			if(IntHourTo>=13&&DayNumWeekTo!=0)
			{
				//����ǰһ����ļ���
				String StoreFileName1=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//���㵱����ļ���
//				String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
//				WriteProductFileName.write(StoreFileName3+"\r\n");
//				WriteProductFileName.write(StoreFileName4+"\r\n");
//				WriteProductFileName.write(StoreFileName5+"\r\n");
			}
			//С��13�㣬Ӧ���ϴ�ǰ����Ĳ�Ʒ������ǰ����Ĳ�Ʒ�Ѿ���ǰһ����ϴ��ˣ��д�����
			if(IntHourTo<13&&DayNumWeekTo==0)
			{
//				//����ǰһ����ļ���
//				String StoreFileName1=speed+WeekToBeiDouSPre+"6"+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntHourTo<13&&DayNumWeekTo==1)
			{
//				//����ǰһ����ļ���
//				String StoreFileName1=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
				
			}
				
			if(IntHourTo<13&&DayNumWeekTo>1)
			{
//				//����ǰһ����ļ���
//				String StoreFileName1=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
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
		/********ͬ�ܲ�ͬ�죬��������ĵ�һ���Ѿ�������д��********/
		/********��ʼʱ����ͬһ���ڣ�������˵�һ������һ���������ʱ��********/
		if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
		{
			for(i=DayNumWeekFrom+1;i<=DayNumWeekTo-1;i++)
			{
				String StoreFileName1=speed+WeekFromBeiDouS+(i-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");

			}
		}
		/********��ʼʱ����ͬһ���ڣ��������һ��********/
		if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
		{
			if(IntHourTo>=13)
			{
				//����ǰһ����ļ���
				String StoreFileName2=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
				WriteProductFileName.write(StoreFileName2+"\r\n");
			}
			if(IntHourTo<13)
			{
//				//���㵱����ļ���
//				String StoreFileName2=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
				
			}
		}
	}

}
