package service.impl;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.ParseXML;

/**
 * Servlet implementation class ChaoKuaiPath
 */
@WebServlet("/ChaoKuaiPath")
public class GetPicPathServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPicPathServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		String s = request.getParameter("type");
//		HttpSession hs = request.getSession();		
//		hs.setAttribute("name", "wmx");
		if(s.equals("1"))
		{	
			JSONObject ret = new JSONObject();
			JSONArray ja = new JSONArray();
			String[] filepath = null;
			try {
				filepath = ParseXML.ReadChaoKuaiXML();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			for(int i=1; i<=filepath.length; i++)
			{
				ja.put(filepath[i-1]);
			}
			ret.put("ret", ja);
			response.getOutputStream().write(ret.toString().getBytes("utf-8"));
		}
		else if(s.equals("2"))
		{
			JSONObject ret = new JSONObject();
			JSONArray ja = new JSONArray();
			String[] filepath = null;
			try {
				filepath = ParseXML.ReadKuaiSuXML();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			for(int i=1; i<=filepath.length; i++)
			{
				ja.put(filepath[i-1]);
			}
			ret.put("ret", ja);
			response.getOutputStream().write(ret.toString().getBytes("utf-8"));
		}
		else
		{
			JSONObject ret = new JSONObject();
			JSONArray ja = new JSONArray();
			String[] filepath = null;
			try {
				filepath = ParseXML.ReadZuiZhongXML();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			for(int i=1; i<=filepath.length; i++)
			{
				ja.put(filepath[i-1]);
			}
			ret.put("ret", ja);
			response.getOutputStream().write(ret.toString().getBytes("utf-8"));
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
