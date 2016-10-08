package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.net.ftp.FTPClient;

import utils.ParseXML;
import utils.ReadSoftStateTxt;


public class ReadConfigFileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		response.setCharacterEncoding("utf-8");  //指定浏览器以某种码表打开数据
		response.setHeader("Content-type", "text/html;utf-8");
		HttpSession session = request.getSession();
		String path = "C:/Users/lyf/Desktop/3.txt";
		LinkedList<String> list = new LinkedList<String>();
		String encoding="utf-8";
        try {
            File file=new File(path);
            if(file.isFile() && file.exists()){         //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);    //考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;  
                while((lineTxt = bufferedReader.readLine()) != null){
                	String str = lineTxt+"\n";
                    list.add(str);
                }
                read.close();
    }else{
        String message = "找不到指定文件";
        list.add(message);
    }
    } catch (Exception e) {
    	String message = "读取文件内容出错";
        list.add(message);
    }
		session.setAttribute("configList", list);
		request.getRequestDispatcher("/FileTest.jsp").forward(request, response);
//		//设置response使用的码表，控制response以某种码表发送数据
//				response.setCharacterEncoding("UTF-8");
//				//指定浏览器以某种码表打开数据
//				//response.setHeader("Content-type", "text/html;charset=UTF-8");
//				response.setContentType("text/html;charset=UTF-8");
//				String[] filepath = null;
//				try {
//					filepath = ParseXML.ReadXML();
//				} catch (Exception e) {
//					e.printStackTrace();
//				} 
//				String s = filepath[4];
//				FileInputStream fis = new FileInputStream(s);
//				OutputStream os = response.getOutputStream();
//				try{
//					int len=0;
//					byte[] buffer=new byte[1024];
//					while((len=fis.read(buffer))>0){
//						os.write(buffer, 0, len);
//					}
//				}finally{
//					if(fis!=null){
//						try{
//							fis.close();
//						}catch(Exception e){
//							e.printStackTrace();
//						}
//					}
//					if(os!=null){
//						try{
//							os.close();
//						}catch(Exception e){
//							e.printStackTrace();
//						}
//					}
//				}
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
}
