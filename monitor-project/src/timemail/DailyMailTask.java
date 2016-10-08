package timemail;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import utils.ParseXML;
import utils.SendMail;

public class DailyMailTask extends TimerTask {
	
	private ServletContext context = null;
	public DailyMailTask(){
		super();
	}
	
	public DailyMailTask(ServletContext context){
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
		String title = "Daily File Error Message";
		String content = "";
		String s = filepath[13];
		String[] number = s.split(",");
		int dIGS2successNum = Integer.parseInt(number[0]);
		int dMGEXIGS3successNum = Integer.parseInt(number[1]);
		int diGMASsuccessNum = Integer.parseInt(number[2]);
		int dBDSStationssuccessNum = Integer.parseInt(number[3]);
		int dGPSStationssuccessNum = Integer.parseInt(number[4]);
		int dGLONASsuccessNum = Integer.parseInt(number[5]);	
		int dPFileStationssuccessNum = Integer.parseInt(number[6]);
		int dLFileStationssuccessNum = Integer.parseInt(number[7]);
		CountFileNum.getDFileNum();
		content = content+CountFileNum.dpath+"\n";
		if(CountFileNum.dIGS2successNum<dIGS2successNum){
			content = content+"IGS RINEX 2.X stations"+"<"+dIGS2successNum+"\n";
		}
		if(CountFileNum.dMGEXIGS3successNum<dMGEXIGS3successNum){
			content = content+"MGEX and IGS RINEX 3.X stations"+"<"+dMGEXIGS3successNum+"\n";
		}
		if(CountFileNum.diGMASsuccessNum<diGMASsuccessNum){
			content = content+"iGMAS stations"+"<"+diGMASsuccessNum+"\n";
		}
		if(CountFileNum.dGLONASsuccessNum<=dGLONASsuccessNum){
			content = content+"GLONASS navigation message file"+"<="+dGLONASsuccessNum+"\n";
		}
		if(CountFileNum.dGPSStationssuccessNum<=dGPSStationssuccessNum){
			content = content+"GPS navigation message file"+"<="+dGPSStationssuccessNum+"\n";
		}
		if(CountFileNum.dPFileStationssuccessNum<=dPFileStationssuccessNum){
			content = content+"Multi-GNSS navigation message file"+"<="+dPFileStationssuccessNum+"\n";
		}
		if(CountFileNum.dBDSStationssuccessNum<dBDSStationssuccessNum){
			content = content+"BDS navigation message"+"<"+dBDSStationssuccessNum+"\n";
		}
		//L文件暂时未下载，不报错
//		if(CountFileNum.dLFileStationssuccessNum<dLFileStationssuccessNum){
//			content = content+"L File Suceess"+"<"+dLFileStationssuccessNum+"\n";
//		}
		if(!content.equals(CountFileNum.dpath+"\n")){             //如果content等于CountFileNum.dpath +"\n"，说明下载成功个数大于阈值，不用报警
			List<String> toList = new ArrayList();
			toList.add(to);
			boolean res = SendMail.send(toList, title, content);
			if( res == true )
				System.out.println("发送成功");
		}
		//System.out.println("天文件");
	}
}
