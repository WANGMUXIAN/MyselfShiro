package ProductUpload;

import java.io.IOException;

import utils.WriteProductFileName;

//��������sp3�ļ�,���ǹ���ĳ����ٲ�Ʒ
//�����ٲ�Ʒ�������ӳ�
public class HandleNTUSP3 {
	public static void HandleNtuSp3(int IntHourFrom,int IntHourTo,long WeeksFrom,long WeeksTo,int DayNumWeekFrom,int DayNumWeekTo) throws IOException
	{
			String speed="NTU";
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
			/*****����һ����Ϊ�������ж�����1.��ͬ�� 2.ͬһ�� 3.ͬ�ܲ�ͬ��*****/
			/*****   1   *****/
			/*****��һ��ʱ�䴦��***********/
			/*****������ж������ǡ��򡱣���������жϷ�֧�ȴ�����ͬ�ܲ�ͬ���һ������������Ͳ�ͬ�� ��һ������****/
			if(WeeksFrom!=WeeksTo||DayNumWeekFrom!=DayNumWeekTo)
			{
				if(IntHourFrom==0)
				{
					String StoreFileName2=speed+WeekFromBeiDouS+"0"+"_"+"00"+endString;
					String StoreFileName3=speed+WeekFromBeiDouS+"0"+"_"+"06"+endString;
					String StoreFileName4=speed+WeekFromBeiDouS+"0"+"_"+"12"+endString;
					String StoreFileName5=speed+WeekFromBeiDouS+"0"+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName2+"\r\n");
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
				}
				//��ʼʱ��Сʱ��λ��0-6֮��
				if(IntHourFrom>0&&IntHourFrom<=6)
				{	
					//���㵱����ļ���
					String StoreFileName3=speed+WeekFromBeiDouS+"0"+"_"+"06"+endString;
					String StoreFileName4=speed+WeekFromBeiDouS+"0"+"_"+"12"+endString;
					String StoreFileName5=speed+WeekFromBeiDouS+"0"+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
					
				}
				//��ʼʱ��λ��6-12֮��
				if(IntHourFrom>6&&IntHourFrom<=12)
				{
					//���㵱����ļ���
					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+"_"+"12"+endString;
					String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
				}
				//��ʼʱ��λ��12-18֮��
				if(IntHourFrom>12&&IntHourFrom<=18)
				{
					//���㵱����ļ���
					String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName5+"\r\n");
					
				}
				//��ʼʱ��λ��18-24֮��	
				if(IntHourFrom>18&&IntHourFrom<=24)
				{
				}
			}
			/********��ʼ���ڳ���һ���⣬������������ʱ��********/
			//��ʼʱ�䲻��ͬһ����
			if(WeeksFrom!=WeeksTo)
			{
				for(i=DayNumWeekFrom+1;i<=6;i++)
				{
					String StoreFileName1=speed+WeekFromBeiDouS+i+"_"+"00"+endString;
					String StoreFileName2=speed+WeekFromBeiDouS+i+"_"+"06"+endString;
					String StoreFileName3=speed+WeekFromBeiDouS+i+"_"+"12"+endString;
					String StoreFileName4=speed+WeekFromBeiDouS+i+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName1+"\r\n");
					WriteProductFileName.write(StoreFileName2+"\r\n");
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
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
				for(n=0;n<=6;n++)
				{
					String StoreFileName1=speed+mS+n+"_"+"00"+endString;
					String StoreFileName2=speed+mS+n+"_"+"06"+endString;
					String StoreFileName3=speed+mS+n+"_"+"12"+endString;
					String StoreFileName4=speed+mS+n+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName1+"\r\n");
					WriteProductFileName.write(StoreFileName2+"\r\n");
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");	
				}
			}
			/********"�������ڳ����һ���⣬������������ʱ��********/
			if(WeeksFrom!=WeeksTo)
			{
				for(i=0;i<=DayNumWeekTo-1;i++)
				{
					String StoreFileName1=speed+WeekToBeiDouS+i+"_"+"00"+endString;
					String StoreFileName2=speed+WeekToBeiDouS+i+"_"+"06"+endString;
					String StoreFileName3=speed+WeekToBeiDouS+i+"_"+"12"+endString;
					String StoreFileName4=speed+WeekToBeiDouS+i+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName1+"\r\n");
					WriteProductFileName.write(StoreFileName2+"\r\n");
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
				}
			}
			/********����,���һ�촦��********/
			if(WeeksFrom!=WeeksTo){
				//����ʱ��Сʱ��λ��0-6֮��
				if(IntHourTo>=0&IntHourTo<6)
				{
					//���㵱����ļ���
					String StoreFileName2=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					WriteProductFileName.write(StoreFileName2+"\r\n");
				}
				//����ʱ��Сʱ��λ��6-12֮��
				if(IntHourTo>=6&IntHourTo<12)
				{
					//���㵱����ļ���
					String StoreFileName3=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName4=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
					
				}
				//����ʱ��Сʱ��λ��12-18֮��
				if(IntHourTo>=12&IntHourTo<18)
				{
					//���㵱����ļ���
					String StoreFileName4=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName5=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					String StoreFileName6=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"12"+endString;
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
					WriteProductFileName.write(StoreFileName6+"\r\n");
				}
				//����ʱ��Сʱ��λ��18-24֮��
				if(IntHourTo>=18&IntHourTo<=24)
				{
					//���㵱����ļ���
					String StoreFileName5=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName6=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					String StoreFileName7=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"12"+endString;
					String StoreFileName8=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName5+"\r\n");
					WriteProductFileName.write(StoreFileName6+"\r\n");
					WriteProductFileName.write(StoreFileName7+"\r\n");
					WriteProductFileName.write(StoreFileName8+"\r\n");
				}
			}
			/*******        2       ********/
			/*******��ʼʱ����ͬһ����********/
			if(WeeksFrom==WeeksTo&&DayNumWeekFrom==DayNumWeekTo)
			{
				//��ʼʱ��СʱΪ0
				if(IntHourFrom==0)
				{
					//����ʱ��Сʱ��λ��0-6֮��
					if(IntHourTo>=0&&IntHourTo<6)
					{	
						
					}
					//����ʱ��Сʱ��λ��6-12֮��
					if(IntHourTo>=6&&IntHourTo<12)
					{	
						String StoreFileName1=speed+WeekFromBeiDouS+0+"_"+"00"+endString;
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						WriteProductFileName.write(StoreFileName1+"\r\n");
						WriteProductFileName.write(StoreFileName2+"\r\n");
					}
					//����ʱ��Сʱ��λ��6-12֮��
					if(IntHourTo>=12&&IntHourTo<18)
					{	
						String StoreFileName1=speed+WeekFromBeiDouS+0+"_"+"00"+endString;
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						WriteProductFileName.write(StoreFileName1+"\r\n");
						WriteProductFileName.write(StoreFileName2+"\r\n");
						WriteProductFileName.write(StoreFileName3+"\r\n");
					}
					//����ʱ��Сʱ��λ��18-24֮��
					if(IntHourTo>=18&&IntHourTo<=24)
					{	
						String StoreFileName1=speed+WeekFromBeiDouS+0+"_"+"00"+endString;
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						String StoreFileName4=speed+WeekFromBeiDouS+0+"_"+"18"+endString;
						WriteProductFileName.write(StoreFileName1+"\r\n");
						WriteProductFileName.write(StoreFileName2+"\r\n");
						WriteProductFileName.write(StoreFileName3+"\r\n");
						WriteProductFileName.write(StoreFileName4+"\r\n");
					}
				}
				//��ʼʱ��Сʱ��λ��0-6֮��
				if(IntHourFrom>0&&IntHourFrom<=6)
				{	
					//����ʱ��Сʱ��λ��0-6֮��
					if(IntHourTo>=0&&IntHourTo<6)
					{	
						
					}
					//����ʱ��Сʱ��λ��6-12֮��
					if(IntHourTo>=6&&IntHourTo<12)
					{	
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						WriteProductFileName.write(StoreFileName2+"\r\n");
					}
					//����ʱ��Сʱ��λ��6-12֮��
					if(IntHourTo>=12&&IntHourTo<18)
					{	
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						WriteProductFileName.write(StoreFileName2+"\r\n");
						WriteProductFileName.write(StoreFileName3+"\r\n");
					}
					//����ʱ��Сʱ��λ��18-24֮��
					if(IntHourTo>=18&&IntHourTo<=24)
					{	
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						String StoreFileName4=speed+WeekFromBeiDouS+0+"_"+"18"+endString;
						WriteProductFileName.write(StoreFileName2+"\r\n");
						WriteProductFileName.write(StoreFileName3+"\r\n");
						WriteProductFileName.write(StoreFileName4+"\r\n");
					}
				}
				//��ʼʱ��Сʱ��λ��6-12
				if(IntHourFrom>6&&IntHourFrom<=12)
				{
					//����ʱ��Сʱ��λ��0-6֮��
					if(IntHourTo>=0&&IntHourTo<6)
					{	
						
					}
					//����ʱ��Сʱ��λ��6-12֮��
					if(IntHourTo>=6&&IntHourTo<12)
					{	
						
					}
					//����ʱ��Сʱ��λ��6-12֮��
					if(IntHourTo>=12&&IntHourTo<18)
					{	
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						WriteProductFileName.write(StoreFileName3+"\r\n");
					}
					//����ʱ��Сʱ��λ��18-24֮��
					if(IntHourTo>=18&&IntHourTo<=24)
					{	
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						String StoreFileName4=speed+WeekFromBeiDouS+0+"_"+"18"+endString;
						WriteProductFileName.write(StoreFileName3+"\r\n");
						WriteProductFileName.write(StoreFileName4+"\r\n");
					}
					
				}
				//��ʼʱ��Сʱ��λ��12-18
				if(IntHourFrom>12&&IntHourFrom<=18)
				{
					//����ʱ��Сʱ��λ��0-6֮��
					if(IntHourTo>=0&&IntHourTo<6)
					{	
						
					}
					//����ʱ��Сʱ��λ��6-12֮��
					if(IntHourTo>=6&&IntHourTo<12)
					{	
		
					}
					//����ʱ��Сʱ��λ��6-12֮��
					if(IntHourTo>=12&&IntHourTo<18)
					{	
				
					}
					//����ʱ��Сʱ��λ��18-24֮��
					if(IntHourTo>=18&&IntHourTo<=24)
					{	
						
						String StoreFileName4=speed+WeekFromBeiDouS+0+"_"+"18"+endString;
						WriteProductFileName.write(StoreFileName4+"\r\n");
					}
				}
				//��ʼʱ��Сʱ��λ��18-24
				if(IntHourFrom>18&IntHourFrom<=24)
				{
					
				}

			}
			/********      3       *********/
			/********ͬ�ܲ�ͬ�죬��������ĵ�һ���Ѿ�������д��********/
			/********��ʼʱ����ͬһ���ڣ�������˵�һ������һ���������ʱ��********/
			if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
			{
				for(i=DayNumWeekFrom+1;i<=DayNumWeekTo-1;i++)
				{
					String StoreFileName1=speed+WeekFromBeiDouS+i+"_"+"00"+endString;
					String StoreFileName2=speed+WeekFromBeiDouS+i+"_"+"06"+endString;
					String StoreFileName3=speed+WeekFromBeiDouS+i+"_"+"12"+endString;
					String StoreFileName4=speed+WeekFromBeiDouS+i+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName1+"\r\n");
					WriteProductFileName.write(StoreFileName2+"\r\n");
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");

				}
			}
			/********��ʼʱ����ͬһ���ڣ��������һ��********/
			if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
			{
				//����ʱ��Сʱ��λ��0-6֮��
				if(IntHourTo>=0&IntHourTo<6)
				{
					//���㵱����ļ���
					String StoreFileName2=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					WriteProductFileName.write(StoreFileName2+"\r\n");
				}
				//����ʱ��Сʱ��λ��6-12֮��
				if(IntHourTo>=6&IntHourTo<12)
				{
					//���㵱����ļ���
					String StoreFileName3=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName4=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
					
				}
				//����ʱ��Сʱ��λ��12-18֮��
				if(IntHourTo>=12&IntHourTo<18)
				{
					//���㵱����ļ���
					String StoreFileName4=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName5=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					String StoreFileName6=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"12"+endString;
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
					WriteProductFileName.write(StoreFileName6+"\r\n");
				}
				//����ʱ��Сʱ��λ��18-24֮��
				if(IntHourTo>=18&IntHourTo<=24)
				{
					//���㵱����ļ���
					String StoreFileName5=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName6=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					String StoreFileName7=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"12"+endString;
					String StoreFileName8=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName5+"\r\n");
					WriteProductFileName.write(StoreFileName6+"\r\n");
					WriteProductFileName.write(StoreFileName7+"\r\n");
					WriteProductFileName.write(StoreFileName8+"\r\n");
				}
			}
	}

}
