package ProductUpload;

import java.io.IOException;

import utils.WriteProductFileName;

//处理超快速sp3文件,卫星轨道的超快速产品
//超快速产品不考虑延迟
public class HandleNTUSP3 {
	public static void HandleNtuSp3(int IntHourFrom,int IntHourTo,long WeeksFrom,long WeeksTo,int DayNumWeekFrom,int DayNumWeekTo) throws IOException
	{
			String speed="NTU";
			String endString=".sp3.Z";
			int i,j;
			long m,n;
			String mS="";
			//将开始周数组合成四位字符串,一个是北斗周，另外一个是北斗周前一周
			long WeekFromBeiDouPre,WeekFromBeiDou;
			WeekFromBeiDouPre=WeeksFrom-1357;
			WeekFromBeiDou=WeeksFrom-1356;
			String WeekFromBeiDouSPre="";
			String WeekFromBeiDouS="";
			//计算北斗周前一周的前四位字符串
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
			//计算北斗周的四位字符串
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
			
			
			//将结束周数组合成四位字符串,一个是北斗周，另外一个是北斗周前一周
			long WeekToBeiDouPre,WeekToBeiDou;
			WeekToBeiDouPre=WeeksTo-1357;
			WeekToBeiDou=WeeksTo-1356;
			String WeekToBeiDouSPre="";
			String WeekToBeiDouS="";
			//计算结束时间北斗周前一周的四位字符串
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
			//计算结束时间北斗周的四位字符串
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
			/*****下面一共分为了三种判断条件1.不同周 2.同一天 3.同周不同天*****/
			/*****   1   *****/
			/*****第一天时间处理***********/
			/*****这里的判断条件是“或”，所以这个判断分支既处理了同周不同天第一天的情况的情况和不同周 第一天的情况****/
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
				//开始时间小时数位于0-6之间
				if(IntHourFrom>0&&IntHourFrom<=6)
				{	
					//计算当天的文件名
					String StoreFileName3=speed+WeekFromBeiDouS+"0"+"_"+"06"+endString;
					String StoreFileName4=speed+WeekFromBeiDouS+"0"+"_"+"12"+endString;
					String StoreFileName5=speed+WeekFromBeiDouS+"0"+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
					
				}
				//开始时间位于6-12之间
				if(IntHourFrom>6&&IntHourFrom<=12)
				{
					//计算当天的文件名
					String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+"_"+"12"+endString;
					String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
				}
				//开始时间位于12-18之间
				if(IntHourFrom>12&&IntHourFrom<=18)
				{
					//计算当天的文件名
					String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+"_"+"18"+endString;
					WriteProductFileName.write(StoreFileName5+"\r\n");
					
				}
				//开始时间位于18-24之间	
				if(IntHourFrom>18&&IntHourFrom<=24)
				{
				}
			}
			/********开始周内除第一天外，处理周内其它时间********/
			//起始时间不在同一周内
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
			/********首周、末周除外，处理中间周********/
			for(m=WeekFromBeiDou+1;m<=WeekToBeiDou-1;m++)
			{
				//因为中间周也需要周的字符串，所以也要单独计算
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
			/********"结束周内除最后一天外，处理周内其它时间********/
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
			/********结束,最后一天处理********/
			if(WeeksFrom!=WeeksTo){
				//结束时间小时数位于0-6之间
				if(IntHourTo>=0&IntHourTo<6)
				{
					//计算当天的文件名
					String StoreFileName2=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					WriteProductFileName.write(StoreFileName2+"\r\n");
				}
				//结束时间小时数位于6-12之间
				if(IntHourTo>=6&IntHourTo<12)
				{
					//计算当天的文件名
					String StoreFileName3=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName4=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
					
				}
				//结束时间小时数位于12-18之间
				if(IntHourTo>=12&IntHourTo<18)
				{
					//计算当天的文件名
					String StoreFileName4=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName5=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					String StoreFileName6=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"12"+endString;
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
					WriteProductFileName.write(StoreFileName6+"\r\n");
				}
				//结束时间小时数位于18-24之间
				if(IntHourTo>=18&IntHourTo<=24)
				{
					//计算当天的文件名
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
			/*******起始时间在同一天中********/
			if(WeeksFrom==WeeksTo&&DayNumWeekFrom==DayNumWeekTo)
			{
				//开始时间小时为0
				if(IntHourFrom==0)
				{
					//结束时间小时数位于0-6之间
					if(IntHourTo>=0&&IntHourTo<6)
					{	
						
					}
					//结束时间小时数位于6-12之间
					if(IntHourTo>=6&&IntHourTo<12)
					{	
						String StoreFileName1=speed+WeekFromBeiDouS+0+"_"+"00"+endString;
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						WriteProductFileName.write(StoreFileName1+"\r\n");
						WriteProductFileName.write(StoreFileName2+"\r\n");
					}
					//结束时间小时数位于6-12之间
					if(IntHourTo>=12&&IntHourTo<18)
					{	
						String StoreFileName1=speed+WeekFromBeiDouS+0+"_"+"00"+endString;
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						WriteProductFileName.write(StoreFileName1+"\r\n");
						WriteProductFileName.write(StoreFileName2+"\r\n");
						WriteProductFileName.write(StoreFileName3+"\r\n");
					}
					//结束时间小时数位于18-24之间
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
				//开始时间小时数位于0-6之间
				if(IntHourFrom>0&&IntHourFrom<=6)
				{	
					//结束时间小时数位于0-6之间
					if(IntHourTo>=0&&IntHourTo<6)
					{	
						
					}
					//结束时间小时数位于6-12之间
					if(IntHourTo>=6&&IntHourTo<12)
					{	
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						WriteProductFileName.write(StoreFileName2+"\r\n");
					}
					//结束时间小时数位于6-12之间
					if(IntHourTo>=12&&IntHourTo<18)
					{	
						String StoreFileName2=speed+WeekFromBeiDouS+0+"_"+"06"+endString;
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						WriteProductFileName.write(StoreFileName2+"\r\n");
						WriteProductFileName.write(StoreFileName3+"\r\n");
					}
					//结束时间小时数位于18-24之间
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
				//开始时间小时数位于6-12
				if(IntHourFrom>6&&IntHourFrom<=12)
				{
					//结束时间小时数位于0-6之间
					if(IntHourTo>=0&&IntHourTo<6)
					{	
						
					}
					//结束时间小时数位于6-12之间
					if(IntHourTo>=6&&IntHourTo<12)
					{	
						
					}
					//结束时间小时数位于6-12之间
					if(IntHourTo>=12&&IntHourTo<18)
					{	
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						WriteProductFileName.write(StoreFileName3+"\r\n");
					}
					//结束时间小时数位于18-24之间
					if(IntHourTo>=18&&IntHourTo<=24)
					{	
						String StoreFileName3=speed+WeekFromBeiDouS+0+"_"+"12"+endString;
						String StoreFileName4=speed+WeekFromBeiDouS+0+"_"+"18"+endString;
						WriteProductFileName.write(StoreFileName3+"\r\n");
						WriteProductFileName.write(StoreFileName4+"\r\n");
					}
					
				}
				//开始时间小时数位于12-18
				if(IntHourFrom>12&&IntHourFrom<=18)
				{
					//结束时间小时数位于0-6之间
					if(IntHourTo>=0&&IntHourTo<6)
					{	
						
					}
					//结束时间小时数位于6-12之间
					if(IntHourTo>=6&&IntHourTo<12)
					{	
		
					}
					//结束时间小时数位于6-12之间
					if(IntHourTo>=12&&IntHourTo<18)
					{	
				
					}
					//结束时间小时数位于18-24之间
					if(IntHourTo>=18&&IntHourTo<=24)
					{	
						
						String StoreFileName4=speed+WeekFromBeiDouS+0+"_"+"18"+endString;
						WriteProductFileName.write(StoreFileName4+"\r\n");
					}
				}
				//开始时间小时数位于18-24
				if(IntHourFrom>18&IntHourFrom<=24)
				{
					
				}

			}
			/********      3       *********/
			/********同周不同天，这种情况的第一天已经在上面写过********/
			/********起始时间在同一周内，计算除了第一天和最后一天外的其他时间********/
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
			/********起始时间在同一周内，计算最后一天********/
			if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
			{
				//结束时间小时数位于0-6之间
				if(IntHourTo>=0&IntHourTo<6)
				{
					//计算当天的文件名
					String StoreFileName2=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					WriteProductFileName.write(StoreFileName2+"\r\n");
				}
				//结束时间小时数位于6-12之间
				if(IntHourTo>=6&IntHourTo<12)
				{
					//计算当天的文件名
					String StoreFileName3=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName4=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					WriteProductFileName.write(StoreFileName3+"\r\n");
					WriteProductFileName.write(StoreFileName4+"\r\n");
					
				}
				//结束时间小时数位于12-18之间
				if(IntHourTo>=12&IntHourTo<18)
				{
					//计算当天的文件名
					String StoreFileName4=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"00"+endString;
					String StoreFileName5=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"06"+endString;
					String StoreFileName6=speed+WeekToBeiDouS+DayNumWeekTo+"_"+"12"+endString;
					WriteProductFileName.write(StoreFileName4+"\r\n");
					WriteProductFileName.write(StoreFileName5+"\r\n");
					WriteProductFileName.write(StoreFileName6+"\r\n");
				}
				//结束时间小时数位于18-24之间
				if(IntHourTo>=18&IntHourTo<=24)
				{
					//计算当天的文件名
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
