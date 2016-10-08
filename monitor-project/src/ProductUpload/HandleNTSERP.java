package ProductUpload;

import java.io.IOException;

import utils.WriteProductFileName;

//处理地球自转参数文件的最终产品
public class HandleNTSERP {
	public static void HandleNtsErp(int IntHourFrom,int IntHourTo,long WeeksFrom,long WeeksTo,int DayNumWeekFrom,int DayNumWeekTo) throws IOException
	{
		String speed="NTS";
		String endString=".erp.Z";
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
		
		//最终产品分为按天计算的、按周计算的和按月计算的，这个处理的事按周计算的，如果超过本周周六的24点，则加上本周的最终产品
		/*****下面是一种判断条件不同周*****/
		/*****   1   *****/
		/*****第一周处理***********/
		
		//首周肯定要
		String StoreFileName1=speed+WeekFromBeiDouS+"7"+endString;
		WriteProductFileName.write(StoreFileName1+"\r\n");
		
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
			String StoreFileName2=speed+mS+"6"+endString;
			WriteProductFileName.write(StoreFileName2+"\r\n");
		}
		/********处理结束周********/
		if(WeeksFrom!=WeeksTo&&DayNumWeekTo==6&&IntHourTo==24)
		{
			String StoreFileName2=speed+WeekToBeiDouS+"7"+endString;
			WriteProductFileName.write(StoreFileName2+"\r\n");
		}
	}

}
