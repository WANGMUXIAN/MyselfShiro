package timemail;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import utils.ParseXML;
import utils.SendMail;

public class HourlyMailTask extends TimerTask {
	
	private ServletContext context = null;
	public HourlyMailTask(){
		super();
	}
	
	public HourlyMailTask(ServletContext context){
		this.context = context;
	}

	@Override
	public void run() {
		
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String to = filepath[15];
		String title = "Hourly File Error Message";
		String content = "";
		String s = filepath[13];
		String[] number = s.split(",");
		int hIGS2successNum = Integer.parseInt(number[8]);
		int hMGEXIGS3successNum = Integer.parseInt(number[9]);
		int hiGMASsuccessNum = Integer.parseInt(number[10]);
		int hBDSStationssuccessNum = Integer.parseInt(number[11]);
		int hGPSStationssuccessNum = Integer.parseInt(number[12]);
		int hGLONASsuccessNum = Integer.parseInt(number[13]);
		//int hPFileStationssuccessNum = Integer.parseInt(number[14]);
		int hLFileStationssuccessNum = Integer.parseInt(number[15]);
		
		CountFileNum.getHFileNum();
		if(filepath[2].equals("")){
			content = content + CountFileNum.hpath +"\n";
			if(CountFileNum.hMGEXIGS3successNum<hMGEXIGS3successNum){
				content = content+"MGEX and IGS RINEX 3.X stations"+"<"+hMGEXIGS3successNum+"\n";
			}
			if(CountFileNum.hiGMASsuccessNum<hiGMASsuccessNum){
				content = content+"iGMAS stations"+"<"+hiGMASsuccessNum+"\n";
			}
			if(CountFileNum.hBDSStationssuccessNum<hBDSStationssuccessNum){
				content = content+"BDS navigation message"+"<"+hBDSStationssuccessNum+"\n";
			}
//			if(CountFileNum.hPFileStationssuccessNum<=hPFileStationssuccessNum){
//				content = content+"Multi-GNSS navigation message file"+"<="+hPFileStationssuccessNum+"\n";
//			}
			if(CountFileNum.hLFileStationssuccessNum<hLFileStationssuccessNum){
				content = content+"L File Success Number"+"<"+hLFileStationssuccessNum+"\n";
			}
		}
		else{
			content = content + CountFileNum.hpath +"\n";
			if(CountFileNum.hIGS2successNum<hIGS2successNum){
				content = content+"IGS RINEX 2.X stations"+"<"+hIGS2successNum+"\n";
			}
			if(CountFileNum.hMGEXIGS3successNum<hMGEXIGS3successNum){
				content = content+"MGEX and IGS RINEX 3.X stations"+"<"+hMGEXIGS3successNum+"\n";
			}
			if(CountFileNum.hiGMASsuccessNum<hiGMASsuccessNum){
				content = content+"iGMAS stations"+"<"+hiGMASsuccessNum+"\n";
			}
			if(CountFileNum.hGLONASsuccessNum<=hGLONASsuccessNum){
				content = content+"GLONASS navigation message file"+"<="+hGLONASsuccessNum+"\n";
			}
			if(CountFileNum.hGPSStationssuccessNum<=hGPSStationssuccessNum){
				content = content+"GPS navigation message file"+"<="+hGPSStationssuccessNum+"\n";
			}
//			if(CountFileNum.hPFileStationssuccessNum<=hPFileStationssuccessNum){
//				content = content+"Multi-GNSS navigation message file"+"<="+hPFileStationssuccessNum+"\n";
//			}
			if(CountFileNum.hBDSStationssuccessNum<hBDSStationssuccessNum){
				content = content+"BDS navigation message"+"<"+hBDSStationssuccessNum+"\n";
			}
			if(CountFileNum.hLFileStationssuccessNum<hLFileStationssuccessNum){
				content = content+"L File Success Number"+"<"+hLFileStationssuccessNum+"\n";
			}
		}
		//System.out.println("小时文件");
		if(!content.equals(CountFileNum.hpath +"\n")){      //如果content等于CountFileNum.hpath +"\n"，说明下载成功个数大于阈值，不用报警
			List<String> toList = new ArrayList();
			toList.add(to);
			boolean res = SendMail.send(toList, title, content);
			if( res == true )
				System.out.println("发送成功");
		}
		
	}
}
