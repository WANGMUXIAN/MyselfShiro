package service.impl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CountWeeks;
import utils.GetWeek;
import ProductUpload.HandleNTRCLK;
import ProductUpload.HandleNTRERP;
import ProductUpload.HandleNTRION;
import ProductUpload.HandleNTRSP3;
import ProductUpload.HandleNTSDCB;
import ProductUpload.HandleNTSERP;
import ProductUpload.HandleNTSION;
import ProductUpload.HandleNTSSNX;
import ProductUpload.HandleNTSSP3;
import ProductUpload.HandleNTSSUM;
import ProductUpload.HandleNTSTRO;
import ProductUpload.HandleNTUERP;
import ProductUpload.HandleNTUSP3;
import ProductUpload.HandleNTUTRO;
import utils.WriteProductFileName;


/**
 * Servlet implementation class ProductUploadServlet
 */
public class ProductUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String TimeFrom=request.getParameter("TimeFrom");
		String TimeTo=request.getParameter("TimeTo");
		String s1=request.getParameter("s1");
		String s2=request.getParameter("s2");
		
		//��ʼ�����
		String YearFrom=TimeFrom.substring(0, 4);
		int IntYearFrom=Integer.parseInt(YearFrom);
		
		String MonthFrom=TimeFrom.substring(5, 7);
		int IntMonthFrom=Integer.parseInt(MonthFrom);
		
		String DayFrom=TimeFrom.substring(8, 10);
		int IntDayFrom=Integer.parseInt(DayFrom);
		
		String HourFrom=TimeFrom.substring(11,13);
		int IntHourFrom=Integer.parseInt(HourFrom);
		//���������
		String YearTo=TimeTo.substring(0, 4);
		int IntYearTo=Integer.parseInt(YearTo);
		
		String MonthTo=TimeTo.substring(5, 7);
		int IntMonthTo=Integer.parseInt(MonthTo);
		
		String DayTo=TimeTo.substring(8, 10);
		int IntDayTo=Integer.parseInt(DayTo);
		
		String HourTo=TimeTo.substring(11,13);
		int IntHourTo=Integer.parseInt(HourTo);
		
		//����ʼ�·ݻ�Ϊ��λ�ַ���,��ΪƵ��ƫ������ļ��õ�����λ���·��ַ���
		if(IntMonthFrom<10)
		{
			MonthFrom="0"+IntMonthFrom;
		}
		//�������·ݻ�Ϊ��λ�ַ�������ΪƵ��ƫ������ļ��õ�����λ���·��ַ���
		if(IntMonthTo<10)
		{
			MonthTo="0"+IntMonthTo;
		}
		
		String IntialDateStr = "1980-1-6";
		//��ʼʱ�����800106������
		String DateStrFrom = IntYearFrom+"-"+IntMonthFrom+"-"+IntDayFrom;
		long WeeksFrom =  CountWeeks.getWeekSub(IntialDateStr, DateStrFrom);
		//����ʱ�����800106������
		String DateStrTo = IntYearTo+"-"+IntMonthTo+"-"+IntDayTo;
		long WeeksTo =  CountWeeks.getWeekSub(IntialDateStr, DateStrTo);
		
		//������ʼ�ͽ���ʱ���������������
		int DayNumWeekFrom=0;
		int DayNumWeekTo=0;
		try {
			
			SimpleDateFormat Format=new SimpleDateFormat("yyyy-MM-dd");
			//��ʼʱ�������ܵĵڼ���
			Date DateFrom;
			DateFrom = Format.parse(DateStrFrom);
			DayNumWeekFrom = GetWeek.getWeek(DateFrom);
			//����ʱ�������ܵĵڼ���
			Date DateTo = Format.parse(DateStrTo);
			DayNumWeekTo = GetWeek.getWeek(DateTo);
		} catch (ParseException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//�������ǹ���ĳ����ٲ�Ʒ
		if(s1.equals("NTU")&&s2.equals("sp3"))
		{
			HandleNTUSP3.HandleNtuSp3(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//���������ת�����ļ��ĳ����ٲ�Ʒ
		if(s1.equals("NTU")&&s2.equals("erp"))
		{
			HandleNTUERP.HandleNtuErp(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		
		//���������ת�����ļ��ĳ����ٲ�Ʒ
		if(s1.equals("NTU")&&s2.equals("tro"))
		{
			HandleNTUTRO.HandleNtuTro(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//�������ǹ�����ٲ�Ʒ
		if(s1.equals("NTR")&&s2.equals("sp3"))
		{
			HandleNTRSP3.HandleNtrSp3(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//�����в��ļ����ٲ�Ʒ
		if(s1.equals("NTR")&&s2.equals("clk"))
		{
			HandleNTRCLK.HandleNtrClk(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//���������ת�������ٲ�Ʒ
		if(s1.equals("NTR")&&s2.equals("erp"))
		{
			HandleNTRERP.HandleNtrErp(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//������������Ŀ��ٲ�Ʒ
		if(s1.equals("NTR")&&s2.equals("ion"))
		{
			HandleNTRION.HandleNtrIon(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//�������ǹ�������ղ�Ʒ
		if(s1.equals("NTS")&&s2.equals("sp3"))
		{
			HandleNTSSP3.HandleNtsSp3(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		////�����в��ļ������ղ�Ʒ
		if(s1.equals("NTS")&&s2.equals("clk"))
		{
			HandleNTSSP3.HandleNtsSp3(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//����վ���������ļ������ղ�Ʒ
		if(s1.equals("NTS")&&s2.equals("snx"))
		{
			HandleNTSSNX.HandleNtsSnx(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//���������ת�����ļ������ղ�Ʒ
		if(s1.equals("NTS")&&s2.equals("erp"))
		{
			HandleNTSERP.HandleNtsErp(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//�������������ļ������ղ�Ʒ
		if(s1.equals("NTS")&&s2.equals("tro"))
		{
			HandleNTSTRO.HandleNtsTro(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//������������ļ����ղ�Ʒ
		if(s1.equals("NTS")&&s2.equals("ion"))
		{
			HandleNTSION.HandleNtsIon(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//����Ƶ��ƫ����������ղ�Ʒ
		if(s1.equals("NTS")&&s2.equals("dcb"))
		{
			HandleNTSDCB.HandleNtsDcb(YearFrom,IntYearFrom,MonthFrom,IntMonthFrom,DayFrom,IntDayFrom,HourFrom,IntHourFrom,YearTo,IntYearTo,MonthTo,IntMonthTo,DayTo,IntDayTo,HourTo,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		//�����ܽ���Ϣ�����ļ�
		if(s1.equals("NTS")&&s2.equals("sum"))
		{
			HandleNTSSUM.HandleNtsSum(IntHourFrom,IntHourTo,WeeksFrom,WeeksTo,DayNumWeekFrom,DayNumWeekTo);
		}
		request.getRequestDispatcher("/ProductUploadDisplay.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
