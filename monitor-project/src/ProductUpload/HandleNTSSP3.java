package ProductUpload;

import java.io.IOException;

import utils.WriteProductFileName;

//卫星轨道的最终产品
public class HandleNTSSP3 {
	public static void HandleNtsSp3(int IntHourFrom,int IntHourTo,long WeeksFrom,long WeeksTo,int DayNumWeekFrom,int DayNumWeekTo) throws IOException
	{
		String speed="NTS";
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
		
		//最终产品分为按天计算的、按周计算的和按月计算的，这个处理的是按天计算的，这个按天计算和超快速产品的类似，但是判别条件只是当天的24点
		/*****下面一共分为了两种判断条件1.不同周 2.同周不同天*****/
		/*****   1   *****/
		/*****第一天时间处理***********/
		/*****这里的判断条件是“或”，所以这个判断分支既处理了同周不同天第一天的情况和不同周的第一天的情况****/
		if(WeeksFrom!=WeeksTo||DayNumWeekFrom!=DayNumWeekTo)
		{
			if(IntHourFrom<=24)
			{
				//计算当天的文件名
				String StoreFileName1=speed+WeekFromBeiDouS+DayNumWeekFrom+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
		/********开始周内除第一天外，处理周内其它时间********/
		//起始时间不在同一周内
		if(WeeksFrom!=WeeksTo)
		{
			for(i=DayNumWeekFrom+1;i<=6;i++)
			{
				String StoreFileName1=speed+WeekFromBeiDouS+i+endString;
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
		
			for(n=0;n<=6;n++)
			{
				String StoreFileName2=speed+mS+n+endString;
				WriteProductFileName.write(StoreFileName2+"\r\n");
			}
		}
		//起始时间不在同一周内
		/********"结束周内除最后一天外，处理周内其它时间********/
		if(WeeksFrom!=WeeksTo)
		{
			for(i=0;i<=DayNumWeekTo-1;i++)
			{
				String StoreFileName1=speed+WeekToBeiDouS+i+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
		/********结束,最后一天处理********/
		if(WeeksFrom!=WeeksTo)
		{
			if(IntHourTo==24)
			{	
				//计算当天的文件名
				String StoreFileName1=speed+WeekToBeiDouS+DayNumWeekTo+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
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
				String StoreFileName1=speed+WeekFromBeiDouS+i+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");

			}
		}
		/********起始时间在同一周内，计算最后一天********/
		if(WeeksFrom==WeeksTo&&DayNumWeekFrom!=DayNumWeekTo)
		{
			if(IntHourTo==24)
			{
				String StoreFileName2=speed+WeekToBeiDouS+DayNumWeekTo+endString;
				WriteProductFileName.write(StoreFileName2+"\r\n");
			}
		}
	}

}
