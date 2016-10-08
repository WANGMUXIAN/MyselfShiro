package timemail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import utils.CountDay;
import utils.GetFoldFileNames;
import utils.MonthDays;
import utils.ParseXML;
import utils.ReadStations;
import utils.StringArray;

public class CountFileNum {

	public static int dIGS2successNum;
	public static int dMGEXIGS3successNum;
	public static int diGMASsuccessNum;
	public static int dGLONASsuccessNum;
	public static int dGPSStationssuccessNum;
	public static int dPFileStationssuccessNum;
	public static int dBDSStationssuccessNum;
	public static int dLFileStationssuccessNum;
	
	public static int hIGS2successNum;
	public static int hMGEXIGS3successNum;
	public static int hiGMASsuccessNum;
	public static int hGLONASsuccessNum;
	public static int hGPSStationssuccessNum;
	public static int hPFileStationssuccessNum;
	public static int hBDSStationssuccessNum;
	public static int hLFileStationssuccessNum;
	public static String dpath;            //���ļ�·��
	public static String hpath;            //Сʱ�ļ�·��
	
	public static void getDFileNum(){
		//�õ�IGS RINEX 2.Xվ��
		String[] IGS2 = ReadStations.readFile(ReadStations.getPath("IGS_FNL.CLU"));
		//�õ�MGEX and IGS RINEX 3.X stations
		String[] MGEXIGS3 = ReadStations.readFile(ReadStations.getPath("MGEX_FULL.CLU"));
		//�õ�iGMAS stations
		String[] iGMAS = ReadStations.readFile(ReadStations.getPath("iGMAS_20150520.CLU"));
		//�õ� GLONASS navigation
		String[] GLONASStations = ReadStations.readFile(ReadStations.getPath("BRDC_G.CLU"));
		//�õ� GPS navigation
		String[] GPSStations = ReadStations.readFile(ReadStations.getPath("BRDC_N.CLU"));
		//�õ�Multi-GNSS navigation
		String[] PFileStations = ReadStations.readFile(ReadStations.getPath("BRDM_P.CLU"));
		//�õ�BDS navigation
		String[] BDSStations = ReadStations.readFile(ReadStations.getPath("iGMAS_brdr_no_54.CLU"));
		//�õ�lStations
		String[] lStations = ReadStations.readFile(ReadStations.getPath("iGMAS_brdr.CLU"));
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		date = date-1;
		if(date<=0){
			month = month-1;
			if(month<=0){
				month = 12;
				year = year-1;
			}
			date = MonthDays.days(year, month);
		}
		int daynum = CountDay.countDay(year, month, date);
		String s = null;;
		if(daynum<10){
			s = "00" + daynum;
		}
		if(10<=daynum&daynum<100)
		{
			s = "0" + daynum;
		}
		if(daynum>=100){
			s = daynum + "";
		}
		//��ϳ�IGS�ļ��Ĵ��·��
		String igs = "IGS/daily/" + year + "/" + s;
		//��ϳ�Multi-GNSS�ļ��Ĵ��·��
		String multignss = "Multi-GNSS/daily/" + year + "/" + s;
		dpath = igs +"\n"+ multignss;
		try{
			
			//�õ�IGS�ļ����µ��ļ����а�����վ����
			String[] IGSfileName = GetFoldFileNames.getFileName(igs);
			//�õ�Multi-GNSS�ļ����µ��ļ����а�����վ����
			String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
			List<String> dFileList = new ArrayList<String>();
			List<String> rFileList = new ArrayList<String>();
			List<String> nFileList = new ArrayList<String>();
			List<String> gFileList = new ArrayList<String>();
			List<String> pFileList = new ArrayList<String>();
			List<String> lFileList = new ArrayList<String>();
			for(int i=0;i<IGSfileName.length;i++){
				if(IGSfileName[i].substring(11, 12).equals("D")){
					dFileList.add(IGSfileName[i].substring(0, 4));
				}
				if(IGSfileName[i].substring(11, 12).equals("N")){
					nFileList.add(IGSfileName[i].substring(0, 4));
				}
				if(IGSfileName[i].substring(11, 12).equals("G")){
					gFileList.add(IGSfileName[i].substring(0, 4));
				}
				if(IGSfileName[i].substring(11, 12).equals("L")){
					lFileList.add(IGSfileName[i].substring(0, 4));
				}
			}
			for(int i=0;i<MultiGNSSfileName.length;i++){
				if(MultiGNSSfileName[i].substring(11, 12).equals("D")){
					dFileList.add(MultiGNSSfileName[i].substring(0, 4));
				}
				if(MultiGNSSfileName[i].substring(11, 12).equals("R")){
					rFileList.add(MultiGNSSfileName[i].substring(0, 4));
				}
				if(MultiGNSSfileName[i].substring(11, 12).equals("P")){
					pFileList.add(MultiGNSSfileName[i].substring(0, 4));
				}
				if(MultiGNSSfileName[i].substring(11, 12).equals("L")){
					lFileList.add(MultiGNSSfileName[i].substring(0, 4));
				}
			}
			
			String[] dFileSuccess = dFileList.toArray(new String[dFileList.size()]);
			String[] rFileSuccess = rFileList.toArray(new String[rFileList.size()]);
			String[] nFileSuccess = nFileList.toArray(new String[nFileList.size()]);
			String[] gFileSuccess = gFileList.toArray(new String[gFileList.size()]);
			String[] pFileSuccess = pFileList.toArray(new String[pFileList.size()]);
			String[] lFileSuccess = lFileList.toArray(new String[lFileList.size()]);
			
			String[] IGS2success = StringArray.intersect(IGS2, dFileSuccess);
			String[] GLONASsuccess = StringArray.intersect(GLONASStations, gFileSuccess);
			String[] GPSStationssuccess = StringArray.intersect(GPSStations, nFileSuccess);
			
			String[] MGEXIGS3success = StringArray.intersect(MGEXIGS3, dFileSuccess);
			String[] iGMASsuccess = StringArray.intersect(iGMAS, dFileSuccess);
			String[] PFileStationssuccess = StringArray.intersect(PFileStations, pFileSuccess);
			String[] BDSStationssuccess = StringArray.intersect(BDSStations, rFileSuccess);
			String[] LFileStationsSuccess = StringArray.intersect(lStations, lFileSuccess);
			
			dIGS2successNum = IGS2success.length;
			dMGEXIGS3successNum = MGEXIGS3success.length;
			diGMASsuccessNum = iGMASsuccess.length;
			dGLONASsuccessNum = GLONASsuccess.length;
			dGPSStationssuccessNum = GPSStationssuccess.length;
			dPFileStationssuccessNum = PFileStationssuccess.length;
			dBDSStationssuccessNum = BDSStationssuccess.length;
			dLFileStationssuccessNum = LFileStationsSuccess.length;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getHFileNum(){
		//�õ�IGS RINEX 2.Xվ��
		String[] IGS2 = ReadStations.readFile(ReadStations.getPath("IGS_FNL.CLU"));
		//�õ�MGEX and IGS RINEX 3.X stations
		String[] MGEXIGS3 = ReadStations.readFile(ReadStations.getPath("MGEX_FULL.CLU"));
		//�õ�iGMAS stations
		String[] iGMAS = ReadStations.readFile(ReadStations.getPath("iGMAS_20150520.CLU"));
		//�õ� GLONASS navigation
		String[] GLONASStations = ReadStations.readFile(ReadStations.getPath("BRDC_G.CLU"));
		//�õ� GPS navigation
		String[] GPSStations = ReadStations.readFile(ReadStations.getPath("BRDC_N.CLU"));
		//�õ�Multi-GNSS navigation
		String[] PFileStations = ReadStations.readFile(ReadStations.getPath("BRDM_P.CLU"));
		//�õ�BDS navigation
		String[] BDSStations = ReadStations.readFile(ReadStations.getPath("iGMAS_brdr_no_54.CLU"));
		//�õ�lStations
		String[] lStations = ReadStations.readFile(ReadStations.getPath("iGMAS_brdr.CLU"));
		
		String[] MGEXIGS3success = null;				
		String[] iGMASsuccess = null;
		String[] PFileStationssuccess = null;
		String[] BDSStationssuccess =  null;
		String[] IGS2success = null;
		String[] GLONASsuccess = null;
		String[] GPSStationssuccess = null;
		String[] LFileStationssuccess = null;
		
		List<String> dFileList = new ArrayList<String>();
		List<String> rFileList = new ArrayList<String>();
		List<String> nFileList = new ArrayList<String>();
		List<String> gFileList = new ArrayList<String>();
		List<String> pFileList = new ArrayList<String>();
		List<String> lFileList = new ArrayList<String>();
				
		Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int daynum = 0;
		try {
			String s[] = ParseXML.ReadXML();
			int MultiStartTime = Integer.parseInt(s[1]);   //�õ�Multi-GNSSСʱ�ļ���ʼ����ʱ��
			int MultiEndTime = MultiStartTime + 30;        //�õ�Multi-GNSSСʱ�ļ���������ʱ��
			if(MultiEndTime<60){
				if(MultiEndTime<=minute)
					hour = hour-1;
				else
					hour = hour-2;
				if(hour<0){
					hour = 24+hour;
					date = date-1;
				}
				if(date<=0){					
					month = month-1;
					if(month<=0){
						month = 12+month;
						year = year-1;
					}
					date = MonthDays.days(year, month);					
				}
				daynum = CountDay.countDay(year, month, date);
				String d = null;
				String h = null;
				if(daynum<10){
					d = "00" + daynum;
				}
				if(daynum>=10&daynum<100){
					d = "0" + daynum;
				}
				if(daynum>=100){
					d= daynum + "";
				}
				if(hour<10){
					h = "0" + hour;
				}
				if(hour>=10){
					h = hour + "";
				}
				//��ϳ�MGEX and IGS RINEX 3.X stations�ļ��Ĵ��·��
				String multignss = "Multi-GNSS/hourly/" + year + "/" + d + "/" + h;
				hpath = multignss;
				String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
				for(int i=0;i<MultiGNSSfileName.length;i++){
					if(MultiGNSSfileName[i].substring(11, 12).equals("D")){
						dFileList.add(MultiGNSSfileName[i].substring(0, 4));
					}
					if(MultiGNSSfileName[i].substring(11, 12).equals("R")){
						rFileList.add(MultiGNSSfileName[i].substring(0, 4));
					}
					if(MultiGNSSfileName[i].substring(11, 12).equals("P")){
						pFileList.add(MultiGNSSfileName[i].substring(0, 4));
					}
					if(MultiGNSSfileName[i].substring(11, 12).equals("L")){
						lFileList.add(MultiGNSSfileName[i].substring(0, 4));
					}
				}
				String[] dFileSuccess = dFileList.toArray(new String[dFileList.size()]);
				String[] rFileSuccess = rFileList.toArray(new String[rFileList.size()]);
				String[] pFileSuccess = pFileList.toArray(new String[pFileList.size()]);
				String[] lFileSuccess = lFileList.toArray(new String[lFileList.size()]);
				
				MGEXIGS3success = StringArray.intersect(MGEXIGS3, dFileSuccess);				
				iGMASsuccess = StringArray.intersect(iGMAS, dFileSuccess);
				PFileStationssuccess = StringArray.intersect(PFileStations, pFileSuccess);
				BDSStationssuccess = StringArray.intersect(BDSStations, rFileSuccess);
				LFileStationssuccess = StringArray.intersect(lStations, lFileSuccess);	
			}
			else{
				if((MultiEndTime-60)<=minute)
					hour = hour-2;
				else
					hour = hour-3;
				if(hour<0){
					hour = 24+hour;
					date = date-1;
				}
				if(date<=0){					
					month = month-1;
					if(month<=0){
						month = 12+month;
						year = year-1;
					}
					date = MonthDays.days(year, month);					
				}
				daynum = CountDay.countDay(year, month, date);
				String d = null;
				String h = null;
			if(daynum<10){
					d = "00" + daynum;
				}
				if(daynum>=10&daynum<100){
					d = "0" + daynum;
				}
				if(daynum>=100){
					d= daynum + "";
				}
				if(hour<10){
					h = "0" + hour;
				}
				if(hour>=10){
					h = hour + "";
				}
				//��ϳ�MGEX and IGS RINEX 3.X stations�ļ��Ĵ��·��
				String multignss = "Multi-GNSS/hourly/" + year + "/" + d + "/" + h;
				hpath = multignss;
				String[] MultiGNSSfileName = GetFoldFileNames.getFileName(multignss);
				for(int i=0;i<MultiGNSSfileName.length;i++){
					if(MultiGNSSfileName[i].substring(11, 12).equals("D")){
						dFileList.add(MultiGNSSfileName[i].substring(0, 4));
					}
					if(MultiGNSSfileName[i].substring(11, 12).equals("R")){
						rFileList.add(MultiGNSSfileName[i].substring(0, 4));
					}
					if(MultiGNSSfileName[i].substring(11, 12).equals("P")){
						pFileList.add(MultiGNSSfileName[i].substring(0, 4));
					}
					if(MultiGNSSfileName[i].substring(11, 12).equals("L")){
						lFileList.add(MultiGNSSfileName[i].substring(0, 4));
					}
				}				
				
				String[] dFileSuccess = dFileList.toArray(new String[dFileList.size()]);
				String[] rFileSuccess = rFileList.toArray(new String[rFileList.size()]);
				String[] pFileSuccess = pFileList.toArray(new String[pFileList.size()]);
				String[] lFileSuccess = lFileList.toArray(new String[lFileList.size()]);
				
				MGEXIGS3success = StringArray.intersect(MGEXIGS3, dFileSuccess);				
				iGMASsuccess = StringArray.intersect(iGMAS, dFileSuccess);
				PFileStationssuccess = StringArray.intersect(PFileStations, pFileSuccess);
				BDSStationssuccess = StringArray.intersect(BDSStations, rFileSuccess);
				LFileStationssuccess = StringArray.intersect(lStations, lFileSuccess);
			}
			if(s[2].equals("")){//��ȡIGSСʱ�ļ����ؿ�ʼʱ�䣬���ޣ���ʲôҲ����				               
			}
			else{
				int IGSStartTime = Integer.parseInt(s[2]);   //�õ�IGSСʱ�ļ���ʼ����ʱ��
				int IGSEndTime = IGSStartTime + 30;        //�õ�IGSСʱ�ļ���������ʱ��
				if(IGSEndTime<60){
					if(IGSEndTime<=minute)
						hour = hour-1;
					else
						hour = hour-2;
					if(hour<0){
						hour = 24+hour;
						date = date-1;
					}
					if(date<=0){					
						month = month-1;
						if(month<=0){
							month = 12+month;
							year = year-1;
						}
						date = MonthDays.days(year, month);					
					}
					daynum = CountDay.countDay(year, month, date);
					String d = null;
					String h = null;
					if(daynum<10){
						d = "00" + daynum;
					}
					if(daynum>=10&daynum<100){
						d = "0" + daynum;
					}
					if(daynum>=100){
						d= daynum + "";
					}
					if(hour<10){
						h = "0" + hour;
					}
					if(hour>=10){
						h = hour + "";
					}
					//��ϳ�IGS RINEX 2.X stations�ļ��Ĵ��·��
					String igs = "IGS/hourly/" + year + "/" + d + "/" + h;
					hpath = hpath +"\n"+igs;
					//�õ�IGS�ļ����µ��ļ����а�����վ����
					String[] IGSfileName = GetFoldFileNames.getFileName(igs);
					for(int i=0;i<IGSfileName.length;i++){
						if(IGSfileName[i].substring(11, 12).equals("D")){
							dFileList.add(IGSfileName[i].substring(0, 4));
						}
						if(IGSfileName[i].substring(11, 12).equals("N")){
							nFileList.add(IGSfileName[i].substring(0, 4));
						}
						if(IGSfileName[i].substring(11, 12).equals("G")){
							gFileList.add(IGSfileName[i].substring(0, 4));
						}
					}
					String[] dFileSuccess = dFileList.toArray(new String[dFileList.size()]);
					String[] nFileSuccess = nFileList.toArray(new String[nFileList.size()]);
					String[] gFileSuccess = gFileList.toArray(new String[gFileList.size()]);
					
					IGS2success = StringArray.intersect(IGS2, dFileSuccess);
					GLONASsuccess = StringArray.intersect(GLONASStations, gFileSuccess);
					GPSStationssuccess = StringArray.intersect(GPSStations, nFileSuccess);
				}
				else{
					if((IGSEndTime-60)<=minute)
						hour = hour-2;
					else
						hour = hour-3;
					if(hour<0){
						hour = 24+hour;
						date = date-1;
					}
					if(date<=0){					
						month = month-1;
						if(month<=0){
							month = 12+month;
							year = year-1;
						}
						date = MonthDays.days(year, month);					
					}
					daynum = CountDay.countDay(year, month, date);
					String d = null;
					String h = null;
					if(daynum<10){
						d = "00" + daynum;
					}
					if(daynum>=10&daynum<100){
						d = "0" + daynum;
					}
					if(daynum>=100){
						d= daynum + "";
					}
					if(hour<10){
						h = "0" + hour;
					}
					if(hour>=10){
						h = hour + "";
					}
					//��ϳ�IGS RINEX 2.X stations�ļ��Ĵ��·��
					String igs = "IGS/hourly/" + year + "/" + d + "/" + h; 
					hpath = hpath +"\n"+igs;
					//�õ�IGS�ļ����µ��ļ����а�����վ����
					String[] IGSfileName = GetFoldFileNames.getFileName(igs);
					
					for(int i=0;i<IGSfileName.length;i++){
						if(IGSfileName[i].substring(11, 12).equals("D")){
							dFileList.add(IGSfileName[i].substring(0, 4));
						}
						if(IGSfileName[i].substring(11, 12).equals("N")){
							nFileList.add(IGSfileName[i].substring(0, 4));
						}
						if(IGSfileName[i].substring(11, 12).equals("G")){
							gFileList.add(IGSfileName[i].substring(0, 4));
						}
					}
					String[] dFileSuccess = dFileList.toArray(new String[dFileList.size()]);
					String[] nFileSuccess = nFileList.toArray(new String[nFileList.size()]);
					String[] gFileSuccess = gFileList.toArray(new String[gFileList.size()]);
					
					IGS2success = StringArray.intersect(IGS2, dFileSuccess);
					GLONASsuccess = StringArray.intersect(GLONASStations, gFileSuccess);
					GPSStationssuccess = StringArray.intersect(GPSStations, nFileSuccess);
				}
				hIGS2successNum = IGS2success.length;
				hGLONASsuccessNum = GLONASsuccess.length;
				hGPSStationssuccessNum = GPSStationssuccess.length;	
			}
			
			hMGEXIGS3successNum = MGEXIGS3success.length;
			hiGMASsuccessNum = iGMASsuccess.length;			
			hPFileStationssuccessNum = PFileStationssuccess.length;
			hBDSStationssuccessNum = BDSStationssuccess.length;
			hLFileStationssuccessNum = LFileStationssuccess.length;
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
//	public static void main(String[] args){
//		CountFileNum.getDFileNum();
//		CountFileNum.getHFileNum();
//		System.out.println(CountFileNum.dpath);
//		System.out.println(CountFileNum.hpath);
//	}
}
