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
		
		//最终产品分为按天计算的、按周计算的和按月计算的，这个处理的事按月计算的，如果超过本月最后一天的24点，则加上本月的最终产品
		/*****下面一共分为了两种判断条件1.不同年 2.同年不同月*****/
		/*****   1   *****/
		/*****第一月处理***********/
		/*****这里的判断条件是“或”，所以这个判断分支既处理了同年不同月第一月的情况和不同年的第一月的情况****/
		if(IntYearFrom!=IntYearTo||IntMonthFrom!=IntMonthTo)
		{
			//第一个月肯定要，这个跟快速产品的处理相同，超快速针对天，
			String StoreFileName1=speed+YearFrom+MonthFrom+endString;
			WriteProductFileName.write(StoreFileName1+"\r\n");
		}
		/********开始年内除开始月外，处理年内其它月份********/
		if(IntYearFrom!=IntYearTo)
		{
			String iS="";
			for(i=IntMonthFrom+1;i<=12;i++)
			{
				//将月份处理为两位字符串
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
		/********首年、末年除外，处理中间年********/
		for(m=IntYearFrom+1;m<=IntYearTo-1;m++)
		{
			for(n=0;n<=12;n++)
			{
				//将月份处理为两位的字符串
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
		/********"结束年内除最后一月外，处理年内其它月份********/
		if(IntYearFrom!=IntYearTo)
		{
			for(i=0;i<=IntMonthTo-1;i++)
			{
				//将月份处理为两位的字符串
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
		/********结束,最后一月处理********/
		if(IntYearFrom!=IntYearTo)
		{
			if(IntMonthTo==1&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"01"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==2&&(IntDayTo==28||IntDayTo==29)&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"02"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==3&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"03"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==4&&IntDayTo==30&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"04"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==5&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"05"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==6&&IntDayTo==30&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"06"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==7&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"07"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==8&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"08"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==9&&IntDayTo==30&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"09"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==10&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"10"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==11&&IntDayTo==30&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"11"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==12&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"12"+endString;
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
		/********同年不同月，这种情况的第一月已经在上面写过********/
		/********起始时间在同一年内，计算除了第一月和最后一月外的其他时间********/
		if(IntYearFrom==IntYearTo&&IntMonthFrom!=IntMonthTo)
		{
			for(i=IntMonthFrom+1;i<=IntMonthTo-1;i++)
			{
				//将月份处理为两位的字符串
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
		/********起始时间在同一年内，计算最后一月********/
		if(IntYearFrom==IntYearTo&&IntMonthFrom!=IntMonthTo)
		{
			if(IntMonthTo==1&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"01"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==2&&(IntDayTo==28||IntDayTo==29)&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"02"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==3&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"03"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==4&&IntDayTo==30&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"04"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==5&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"05"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==6&&IntDayTo==30&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"06"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==7&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"07"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==8&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"08"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==9&&IntDayTo==30&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"09"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==10&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"10"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==11&&IntDayTo==30&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"11"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
			if(IntMonthTo==12&&IntDayTo==31&&IntHourTo==24)
			{	
				//计算当月的文件名
				String StoreFileName1=speed+YearTo+"12"+endString;
				WriteProductFileName.write(StoreFileName1+"\r\n");
			}
		}
	}

}
