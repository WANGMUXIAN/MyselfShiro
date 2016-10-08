package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.*;

public class SendSuFastServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		LinkedList<String> listssp3 = new LinkedList<String>();  //保存上传成功的超快轨道产品
		LinkedList<String> listserp = new LinkedList<String>();  //保存上传成功的超快地球自转参数文件
		LinkedList<String> liststro = new LinkedList<String>();  //保存上传成功的超快对流层文件
		LinkedList<String> finallylistssp3 = new LinkedList<String>();  //保存10.12.5.6上传成功的超快轨道产品
		LinkedList<String> finallylistserp = new LinkedList<String>();  //保存10.12.5.6上传成功的超快地球自转参数文件
		LinkedList<String> finallyliststro = new LinkedList<String>();  //保存10.12.5.6上传成功的超快对流层文件
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		String beginDateStr = "1980-1-6";
		String endDateStr = year+"-"+month+"-"+date;
		long weeks =  CountWeeks.getWeekSub(beginDateStr, endDateStr)-1356;      //表示今天距1980.1.6为第几周
		int weeknum = GetWeek.getWeek(new Date());    //表示今天为这一周的第几天，周日为第零天
		if(hour>=0&hour<2){
			weeknum = weeknum-1;
			if(weeknum<0){
				weeks = weeks-1;
				weeknum = weeknum+7;
			}
		}		
		if(hour>=0&hour<2)
			request.setAttribute("hour", "18");
		if(hour>=2&hour<8)
			request.setAttribute("hour", "00");
		if(hour>=8&hour<14)
			request.setAttribute("hour", "06");
		if(hour>=14&hour<20)
			request.setAttribute("hour", "12");
		if(hour>=20&hour<=23)
			request.setAttribute("hour", "18");
		request.setAttribute("weeks", weeks);
		request.setAttribute("weeknum", weeknum);
		String week = null;
		if(weeks<1000)
			week = "0"+weeks;
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} //解析配置文件，确定读取所要读取文件夹的路径
		String path = filepath[11];
		String finallyPath = filepath[16];
		String logPath = path+week+"_Sended.log";
		String finallyLogPath = finallyPath+week+"_Sended.log";
		String encoding="GBK";
        File file=new File(logPath);
        File finallyFile = new File(finallyLogPath);
        if(file.isFile() && file.exists()){ //判断文件是否存在
            InputStreamReader read = new InputStreamReader(
            new FileInputStream(file),encoding);          //考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("SP3")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listssp3.add(lineTxt);
                		}                		
                	}
                }
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("ERP")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			listserp.add(lineTxt);
                		}                		
                	}
                }
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("TRO")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			liststro.add(lineTxt);
                		}                		
                	}
                }                
            }
            read.close();
            }
        if(finallyFile.isFile() && finallyFile.exists()){ //判断文件是否存在
            InputStreamReader read = new InputStreamReader(
            new FileInputStream(finallyFile),encoding);          //考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("SP3")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistssp3.add(lineTxt);
                		}                		
                	}
                }
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("ERP")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallylistserp.add(lineTxt);
                		}                		
                	}
                }
                if(lineTxt.toUpperCase().contains("NTU")&lineTxt.toUpperCase().contains("TRO")){
                	if(hour>=0&hour<2){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		} 
                	}
                	if(hour>=2&hour<8){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("00")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=8&hour<14){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("06")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=14&hour<20){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("12")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		}                		
                	}
                	if(hour>=20&hour<=23){
                		if(lineTxt.substring(3, 7).equals(week)&lineTxt.substring(9, 11).equals("18")&lineTxt.substring(7, 8).equals(weeknum+"")){
                			finallyliststro.add(lineTxt);
                		}                		
                	}
                }                
            }
            read.close();
            }
        session.setAttribute("listssp3", listssp3);
        session.setAttribute("listserp", listserp);
        session.setAttribute("liststro", liststro);
        session.setAttribute("finallylistssp3", finallylistssp3);
        session.setAttribute("finallylistserp", finallylistserp);
        session.setAttribute("finallyliststro", finallyliststro);
        request.getRequestDispatcher("/SendSuFastServlet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
