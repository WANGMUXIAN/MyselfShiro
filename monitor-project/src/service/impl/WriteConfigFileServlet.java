package service.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WriteConfigFileServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String config = request.getParameter("config");
		System.out.println(config);
		String crontabFilepath = "C:/Users/lyf/Desktop/1.txt";
		
		FileOutputStream fos = new FileOutputStream(crontabFilepath);
        OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
        osw.write(config);
        osw.flush(); 
        fos.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request,response);
	}

}
