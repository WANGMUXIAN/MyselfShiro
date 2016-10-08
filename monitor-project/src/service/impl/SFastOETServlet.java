package service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ParseXML;
import utils.ReadSoftStateTxt;

public class SFastOETServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");  //指定浏览器以某种码表打开数据
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		final HttpSession session = request.getSession();
		try {
			String[] filepath = ParseXML.ReadXML(); //解析配置文件，确定读取索要读取文件夹的路径
			final String path = filepath[3];
			Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            public void run() {
	            	session.setAttribute("list1", ReadSoftStateTxt.readTxtFile(path));
	            }
	        }, 0, 2000);
		} catch (Exception e) {
			e.printStackTrace();
		}         		 
		request.getRequestDispatcher("/SFastOETServlet.jsp").forward(request, response);
		
		/*String data = null;
		for(int i=0;i<list.size();i++){
			data = list.get(i) + "</br>";
			PrintWriter out=response.getWriter();
			out.write(data);
		}*/		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}
}
