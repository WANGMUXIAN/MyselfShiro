package service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ParseXML;

public class ShowImageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//设置response使用的码表，控制response以某种码表发送数据
		response.setCharacterEncoding("UTF-8");
		//指定浏览器以某种码表打开数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String[] filepath = null;
		try {
			filepath = ParseXML.ReadXML();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String s = filepath[14];
		System.out.println(s);
		FileInputStream fis = new FileInputStream(s);
		OutputStream os = response.getOutputStream();
		try{
			int len=0;
			byte[] buffer=new byte[1024];
			while((len=fis.read(buffer))>0){
				os.write(buffer, 0, len);
			}
		}finally{
			if(fis!=null){
				try{
					fis.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(os!=null){
				try{
					os.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
