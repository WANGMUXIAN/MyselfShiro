package ProductUpload;

import java.io.IOException;

import utils.WriteProductFileName;
//处理快速sp3文件,卫星轨道的快速产品
public class HandleNTRSP3old {
	public static void HandleNtrSp3(int IntHourFrom,int IntHourTo,long WeeksFrom,long WeeksTo,int DayNumWeekFrom,int DayNumWeekTo) throws IOException
	{
		String speed="NTR";
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
		
		//这种快速产品主要负责处理前一天的产品，如果小于13点，那么不考虑前两天的即可，因为前两天的产品，前一天肯定会处理
		/*****下面一共分为了两种判断条件1.不同周 2.同周不同天*****/
		/*****   1   *****/
		/*****第一天时间处理***********/
		/*****这里的判断条件是“或”，所以这个判断分支既处理了同周不同天第一天的情况和不同周第一天的情况****/
		if(WeeksFrom!=WeeksTo||DayNumWeekFrom!=DayNumWeekTo)
		{
			
			if(IntHourFrom>=13&&DayNumWeekFrom==0)
			{	
				//计算前一天的文件名
				String StoreFileName1=speed+WeekFromBeiDouSPre+"6"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//计算当天的文件名
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
				//计算前一天的文件名
				String StoreFileName1=speed+WeekFromBeiDouS+(DayNumWeekFrom-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//计算当天的文件名
//				String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
//				WriteProductFileName.write(StoreFileName3+"\r\n");
//				WriteProductFileName.write(StoreFileName4+"\r\n");
//				WriteProductFileName.write(StoreFileName5+"\r\n");
			}
			//小于13点，应该上传前两天的产品，但是前两天的产品已经被前一天的上传了，有待讨论
			if(IntHourFrom<13&&DayNumWeekFrom==0)
			{
				//计算前一天的文件名
				String StoreFileName1=speed+WeekFromBeiDouSPre+"6"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntHourFrom<13&&DayNumWeekFrom==1)
			{
				//计算前一天的文件名
				String StoreFileName1=speed+WeekFromBeiDouS+(DayNumWeekFrom-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
				
			}
				
			if(IntHourFrom<13&&DayNumWeekFrom>1)
			{
				//计算前一天的文件名
				String StoreFileName1=speed+WeekFromBeiDouS+(DayNumWeekFrom-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
		/********开始周内除第一天外，处理周内其它时间********/
		//起始时间不在同一周内
		if(WeeksFrom!=WeeksTo)
		{
			for(i=DayNumWeekFrom+1;i<=6;i++)
			{
				String StoreFileName1=speed+WeekFromBeiDouS+(i-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
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
			
			//计算前一周的周数的字符串
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
				//因为这种快速产品，主要针对的是前天的产品，所以各个周的第一天处理的是上周的最后一天
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
		//起始时间不在同一周内
		/********"结束周内除最后一天外，处理周内其它时间********/
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
		/********结束,最后一天处理********/
		if(WeeksFrom!=WeeksTo)
		{
			if(IntHourTo>=13&&DayNumWeekTo==0)
			{	
				//计算前一天的文件名
				String StoreFileName1=speed+WeekToBeiDouSPre+"6"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//计算当天的文件名
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
				//计算前一天的文件名
				String StoreFileName1=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//计算当天的文件名
//				String StoreFileName2=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName3=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName4=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
//				WriteProductFileName.write(StoreFileName3+"\r\n");
//				WriteProductFileName.write(StoreFileName4+"\r\n");
//				WriteProductFileName.write(StoreFileName5+"\r\n");
			}
			//小于13点，应该上传前两天的产品，但是前两天的产品已经被前一天的上传了，有待讨论
			if(IntHourTo<13&&DayNumWeekTo==0)
			{
//				//计算前一天的文件名
//				String StoreFileName1=speed+WeekToBeiDouSPre+"6"+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntHourTo<13&&DayNumWeekTo==1)
			{
//				//计算前一天的文件名
//				String StoreFileName1=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
				
			}
				
			if(IntHourTo<13&&DayNumWeekTo>1)
			{
//				//计算前一天的文件名
//				String StoreFileName1=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
			}		
		}
//		/*******        2       ********/
//		/*******起始时间在同一天中********/
//		if(WeeksFrom==WeeksTo&&DayNumWeekFrom==DayNumWeekTo)
//		{
//			//开始时间小时数位于0-2之间，并且周内天数为0，那么周内天数和周数都要减一
//			if(IntHourFrom>=0&&IntHourFrom<2&&DayNumWeekFrom==0)
//			{	
//				//计算前一天的文件名
//				String StoreFileName1=speed+WeekFromBeiDouSPre+"6"+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//计算当天的文件名
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
//			//开始时间小时数位于0-2之间，并且周内天数不为0，那么周内天数要减一，周数不用减一
//			if(IntHourFrom>=0&&IntHourFrom<2&&DayNumWeekFrom!=0)
//			{
//				//计算前一天的文件名
//				String StoreFileName1=speed+WeekFromBeiDouS+(DayNumWeekFrom-1)+endString;
//				WriteProductFileName.write(StoreFileName1+"\r\n");
//				//计算当天的文件名
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
//				//计算当天的文件名
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
//				//计算当天的文件名
//				String StoreFileName5=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
//				WriteProductFileName.write(StoreFileName5+"\r\n");
//			}
//
//		}
		/********      2       *********/
		/********同周不同天，这种情况的第一天已经在上面写过********/
		/********起始时间在同一周内，计算除了第一天和最后一天外的其他时间********/
		if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
		{
			for(i=DayNumWeekFrom+1;i<=DayNumWeekTo-1;i++)
			{
				String StoreFileName1=speed+WeekFromBeiDouS+(i-1)+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");

			}
		}
		/********起始时间在同一周内，计算最后一天********/
		if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
		{
			if(IntHourTo>=13)
			{
				//计算前一天的文件名
				String StoreFileName2=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
				WriteProductFileName.write(StoreFileName2+"\r\n");
			}
			if(IntHourTo<13)
			{
//				//计算当天的文件名
//				String StoreFileName2=speed+WeekToBeiDouS+(DayNumWeekTo-1)+endString;
//				WriteProductFileName.write(StoreFileName2+"\r\n");
				
			}
		}
	}

}
