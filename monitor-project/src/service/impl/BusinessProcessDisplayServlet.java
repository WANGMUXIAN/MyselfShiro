package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ParseXML;

/**
 * Servlet implementation class BusinessProcessDisplayServlet
 */

public class BusinessProcessDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessProcessDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ȡsession����
		HttpSession session = request.getSession();
		//��ȡ��ǰʱ��
		SimpleDateFormat DF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=DF.format(new Date(System.currentTimeMillis()));
		int nowYear=Integer.parseInt(now.substring(0,4));
		int nowMonth=Integer.parseInt(now.substring(5,7));
		int nowDay=Integer.parseInt(now.substring(8,10));
		int nowHour=Integer.parseInt(now.substring(11,13));
		int nowMinute=Integer.parseInt(now.substring(14,16));
		//�����ϴν���ʱ����´�����ʱ����ַ���
		//����
		String PodUltLastEndTime="";
		String PodUltNextBeginTime="";
		String ClkUltLastEndTime="";
		String ClkUltNextBeginTime="";
		//����
		String PodRpdLastEndTime="";
		String PodRpdNextBeginTime="";
		String ClkRpdLastEndTime="";
		String ClkRpdNextBeginTime="";
		String IonRpdLastEndTime="";
		String IonRpdNextBeginTime="";
		//����
		String PodFinLastEndTime="";
		String PodFinNextBeginTime="";
		String ClkFinLastEndTime="";
		String ClkFinNextBeginTime="";
		String IonFinLastEndTime="";
		String IonFinNextBeginTime="";
		//�ȴ������ļ���������ҵ�����crontab��run�ļ���·���������������ͳһ����
		String[] S=null;
		try {
			S=ParseXML.ReadXML();
		} catch (Exception e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		/****��������erp��������   90
	 	 ****�����Ӳ�  715
		 ****���ٹ����erp��������  834  
		 ****�����Ӳ�   1393
		 ****���ٵ����  
		 ****���չ����erp�������
		 ****�����Ӳ�
		 ****���յ����
		 ****/
		/*********�ȴ�����������erp��������״̬���ϴν���ʱ�䡢�´�����ʱ��**********/
		//�ȶ�run�ļ�,���ҽ�run�ļ��е�ǰ���з���lineTxt�ַ�������
		if(true){
			File file=new File(S[3]);
			String lineTxt[]=new String[2];
			/*********�ļ�����**********/
	        if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file));    //���ǵ������ʽ
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String tem="NULL";
	            int i=0;
	            while((tem=bufferedReader.readLine()) != null&&i<=1){
	                if(!tem.equals(""))
	                {
	                	lineTxt[i]=tem;
	                	i++;
	                }
	            }
	            read.close();
	        }
	        else
	        {
	        	System.out.println("No File!");
	        }
	        /*********�ļ�����**********/
	        
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("finished")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("PodUltLastStatus","finished");
	        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
	        	int length=lineTxt[0].length();
	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_ult_test_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }//sunbaoqi@ntsc.ac.cn
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int FirstHour=Integer.parseInt(lineTxtNext.substring(3,5));
		        int SecondHour=Integer.parseInt(lineTxtNext.substring(6,7));
		        int ThirdHour=Integer.parseInt(lineTxtNext.substring(8,10));
		        int FourthHour=Integer.parseInt(lineTxtNext.substring(11,13));
		        if((nowHour>SecondHour||(nowHour==SecondHour&&nowMinute>=minute))&&(nowHour<ThirdHour||(nowHour==ThirdHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+ThirdHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("running")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("PodUltLastStatus","running");
	        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_ult_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int FirstHour=Integer.parseInt(lineTxtNext.substring(3,5));
		        int SecondHour=Integer.parseInt(lineTxtNext.substring(6,7));
		        int ThirdHour=Integer.parseInt(lineTxtNext.substring(8,10));
		        int FourthHour=Integer.parseInt(lineTxtNext.substring(11,13));
		        if((nowHour>SecondHour||(nowHour==SecondHour&&nowMinute>=minute))&&(nowHour<ThirdHour||(nowHour==ThirdHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+ThirdHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("error")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("PodUltLastStatus","error");
	        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
	        	int length=lineTxt[0].length();
	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_ult_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int FirstHour=Integer.parseInt(lineTxtNext.substring(3,5));
		        int SecondHour=Integer.parseInt(lineTxtNext.substring(6,7));
		        int ThirdHour=Integer.parseInt(lineTxtNext.substring(8,10));
		        int FourthHour=Integer.parseInt(lineTxtNext.substring(11,13));
		        if((nowHour>SecondHour||(nowHour==SecondHour&&nowMinute>=minute))&&(nowHour<ThirdHour||(nowHour==ThirdHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+ThirdHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
	        }
		}
		/*********��������erp��������״̬���ϴν���ʱ�䡢�´�����ʱ�䴦�����**********/
		
		
		/*********���������Ӳ�״̬���ϴν���ʱ�䡢�´�����ʱ��**********/
		//�ȶ�run�ļ�,���ҽ�run�ļ��е�ǰ���з���lineTxt�ַ�������
		if(true){
			File file=new File(S[4]);
			String lineTxt[]=new String[2];
			/*********�ļ�����**********/
	        if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file));    //���ǵ������ʽ
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String tem="NULL";
	            int i=0;
	            while((tem=bufferedReader.readLine()) != null&&i<=1){
	                if(!tem.equals(""))
	                {
	                	lineTxt[i]=tem;
	                	i++;
	                }
	            }
	            read.close();
	        }
	        else
	        {
	        	System.out.println("No File!");
	        }
	        /*********�ļ�����**********/
	        
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("finished")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("ClkUltLastStatus","finished");
	        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
	        	int length=lineTxt[0].length();
	        	ClkUltLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("ClkUltLastEndTime", ClkUltLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_ult_test_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int FirstHour=Integer.parseInt(lineTxtNext.substring(3,5));
		        int SecondHour=Integer.parseInt(lineTxtNext.substring(6,7));
		        int ThirdHour=Integer.parseInt(lineTxtNext.substring(8,10));
		        int FourthHour=Integer.parseInt(lineTxtNext.substring(11,13));
		        if((nowHour>SecondHour||(nowHour==SecondHour&&nowMinute>=minute))&&(nowHour<ThirdHour||(nowHour==ThirdHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+ThirdHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        
	      //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("running")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("ClkUltLastStatus","running");
	        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("clk_ult_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int FirstHour=Integer.parseInt(lineTxtNext.substring(3,5));
		        int SecondHour=Integer.parseInt(lineTxtNext.substring(6,7));
		        int ThirdHour=Integer.parseInt(lineTxtNext.substring(8,10));
		        int FourthHour=Integer.parseInt(lineTxtNext.substring(11,13));
		        if((nowHour>SecondHour||(nowHour==SecondHour&&nowMinute>=minute))&&(nowHour<ThirdHour||(nowHour==ThirdHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+ThirdHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
		        
	        }
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("error")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("ClkUltLastStatus","error");
	        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
	        	int length=lineTxt[0].length();
	        	ClkUltNextBeginTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("ClkUltLastEndTime", ClkUltLastEndTime);
	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("clk_ult_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int FirstHour=Integer.parseInt(lineTxtNext.substring(3,5));
		        int SecondHour=Integer.parseInt(lineTxtNext.substring(6,7));
		        int ThirdHour=Integer.parseInt(lineTxtNext.substring(8,10));
		        int FourthHour=Integer.parseInt(lineTxtNext.substring(11,13));
		        if((nowHour>SecondHour||(nowHour==SecondHour&&nowMinute>=minute))&&(nowHour<ThirdHour||(nowHour==ThirdHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+ThirdHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		ClkUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		ClkUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkUltNextBeginTime", DF.format(DF.parse(ClkUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
	        }
		}
		/*********�����Ӳ�״̬���ϴν���ʱ�䡢�´�����ʱ�䴦�����**********/
		
		
		
		/*********�������ٹ����erp��������״̬���ϴν���ʱ�䡢�´�����ʱ��**********/
		//�ȶ�run�ļ�,���ҽ�run�ļ��е�ǰ���з���lineTxt�ַ�������
		if(true){
			File file=new File(S[5]);
			String lineTxt[]=new String[2];
			/*********�ļ�����**********/
	        if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file));    //���ǵ������ʽ
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String tem="NULL";
	            int i=0;
	            while((tem=bufferedReader.readLine()) != null&&i<=1){
	                if(!tem.equals(""))
	                {
	                	lineTxt[i]=tem;
	                	i++;
	                }
	            }
	            read.close();
	        }
	        else
	        {
	        	System.out.println("No File!");
	        }
	        /*********�ļ�����**********/
	        
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("finished")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("PodRpdLastStatus","finished");
	        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
	        	int length=lineTxt[0].length();
	        	PodRpdLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("PodRpdLastEndTime", PodRpdLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_rpd_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������	
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		    
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("running")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("PodRpdLastStatus","running");
//	        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
//	        	int length=lineTxt[0].length();
//	        	PodRpdLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("PodRpdLastEndTime", PodRpdLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_rpd_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		    
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("error")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("PodRpdLastStatus","error");
//	        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
	        	int length=lineTxt[0].length();
	        	PodRpdLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("PodRpdLastEndTime", PodRpdLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_rpd_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		    
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
	        }
		}
		/*********���ٹ����erp��������״̬���ϴν���ʱ�䡢�´�����ʱ�䴦�����**********/
		
		
//		/*********���������Ӳ�״̬���ϴν���ʱ�䡢�´�����ʱ��**********/
//		//�ȶ�run�ļ�,���ҽ�run�ļ��е�ǰ���з���lineTxt�ַ�������
		if(true){
			File file=new File(S[6]);
			String lineTxt[]=new String[2];
			/*********�ļ�����**********/
	        if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file));    //���ǵ������ʽ
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String tem="NULL";
	            int i=0;
	            while((tem=bufferedReader.readLine()) != null&&i<=1){
	                if(!tem.equals(""))
	                {
	                	lineTxt[i]=tem;
	                	i++;
	                }
	            }
	            read.close();
	        }
	        else
	        {
	        	System.out.println("No File!");
	        }
	        /*********�ļ�����**********/
	        
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("finished")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("ClkRpdLastStatus","finished");
	        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
	        	int length=lineTxt[0].length();
	        	ClkRpdLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("ClkRpdLastEndTime", ClkRpdLastEndTime);

	        	/*********�ļ�����**********/
	        }
	        
	      //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("running")!=-1)
	        {
	        	request.setAttribute("ClkRpdLastStatus","running"); 
	        }
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("error")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("ClkRpdLastStatus","error");
	        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
	        	int length=lineTxt[0].length();
	        	ClkRpdLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("ClkRpdLastEndTime", ClkRpdLastEndTime);
//	        	//�����´�������ʱ��
	        }
	        /*********�ļ�����**********/
        	File fileNext=new File(S[12]);
			String lineTxtNext="";
	        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(fileNext));    //���ǵ������ʽ
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String tem="NULL";
	            while((tem=bufferedReader.readLine()) != null){
	                if(tem.indexOf("pod_rpd_pcs.sh")!=-1)
	                {
	                	lineTxtNext=tem;
	                	break;
	                }
	            }
	            read.close();
	        }
	        /*********�ļ�����**********/
	        //������crontab���´�����������
	        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
	        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
	        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
	        {
	        	ClkRpdLastEndTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
	        	try {
					request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
				} catch (ParseException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
	        }
	    
	        else
	        {
	        	if(nowMonth==1&&nowDay==31)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==3&&nowDay==31)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==4&&nowDay==30)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==5&&nowDay==31)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==6&&nowDay==30)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==7&&nowDay==31)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==8&&nowDay==31)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==9&&nowDay==30)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==10&&nowDay==31)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==11&&nowDay==30)
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==12&&nowDay==31)
	        	{
	        		ClkRpdLastEndTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else
	        	{
	        		ClkRpdLastEndTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkRpdNextBeginTime", DF.format(DF.parse(ClkRpdLastEndTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        }
		}
		/*********�����Ӳ�״̬���ϴν���ʱ�䡢�´�����ʱ�䴦�����**********/
		
		
		/*********�������ٵ����״̬���ϴν���ʱ�䡢�´�����ʱ��**********/
		//�ȶ�run�ļ�,���ҽ�run�ļ��е�ǰ���з���lineTxt�ַ�������
		if(true){
			File file=new File(S[7]);
			String lineTxt[]=new String[2];
			/*********�ļ�����**********/
	        if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file));    //���ǵ������ʽ
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String tem="NULL";
	            int i=0;
	            while((tem=bufferedReader.readLine()) != null&&i<=1){
	                if(!tem.equals(""))
	                {
	                	lineTxt[i]=tem;
	                	i++;
	                }
	            }
	            read.close();
	        }
	        else
	        {
	        	System.out.println("No File!");
	        }
	        /*********�ļ�����**********/
	        
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("finished")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("IonRpdLastStatus","finished");
	        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
	        	int length=lineTxt[0].length();
	        	IonRpdLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("IonRpdLastEndTime", IonRpdLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/

		        
	        }
	        
	      //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("running")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("IonRpdLastStatus","running");
	        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
		        
		        
	        }
	        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
	        if(lineTxt[1].indexOf("error")!=-1)
	        {
	        	//��״̬����jsp��
	        	request.setAttribute("IonRpdLastStatus","error");
	        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
	        	int length=lineTxt[0].length();
	        	IonRpdLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("IonRpdLastEndTime", IonRpdLastEndTime);
//	        	//�����´�������ʱ��
	        	/*********�ļ�����**********/
	        }
	        File fileNext=new File(S[12]);
			String lineTxtNext="";
	        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(fileNext));    //���ǵ������ʽ
	            BufferedReader bufferedReader = new BufferedReader(read);
	            String tem="NULL";
	            while((tem=bufferedReader.readLine()) != null){
	                if(tem.indexOf("ion_rpd_pcs.sh")!=-1)
	                {
	                	lineTxtNext=tem;
	                	break;
	                }
	            }
	            read.close();
	        }
	        /*********�ļ�����**********/
	        //������crontab���´�����������
	        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
	        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
	        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
	        {
	        	IonRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
	        	try {
					request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
				} catch (ParseException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
	        }
	    
	        else
	        {
	        	if(nowMonth==1&&nowDay==31)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==3&&nowDay==31)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==4&&nowDay==30)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==5&&nowDay==31)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==6&&nowDay==30)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==7&&nowDay==31)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==8&&nowDay==31)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==9&&nowDay==30)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==10&&nowDay==31)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==11&&nowDay==30)
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else if(nowMonth==12&&nowDay==31)
	        	{
	        		IonRpdNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        	else
	        	{
	        		IonRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonRpdNextBeginTime", DF.format(DF.parse(IonRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
	        	}
	        }
		}
		/*********���ٵ����״̬���ϴν���ʱ�䡢�´�����ʱ�䴦�����**********/
		
		
		
		/*********�������չ����erp��������״̬���ϴν���ʱ�䡢�´�����ʱ��**********/
		//�ȶ�run�ļ�,���ҽ�run�ļ��е�ǰ���з���lineTxt�ַ�������
		if(true){
			if(true){
				File file=new File(S[8]);
				String lineTxt[]=new String[2];
				/*********�ļ�����**********/
		        if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(file));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            int i=0;
		            while((tem=bufferedReader.readLine()) != null&&i<=1){
		                if(!tem.equals(""))
		                {
		                	lineTxt[i]=tem;
		                	i++;
		                }
		            }
		            read.close();
		        }
		        else
		        {
		        	System.out.println("No File!");
		        }
		        /*********�ļ�����**********/
		        
		        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("finished")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("PodFinLastStatus","finished");
		        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
		        	int length=lineTxt[0].length();
		        	PodFinLastEndTime=lineTxt[0].substring(length-21,length);
		        	request.setAttribute("PodFinLastEndTime", PodFinLastEndTime);
//		        	//�����´�������ʱ��
		        	/*********�ļ�����**********/
			        
		        }
		        
		      //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("running")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("PodFinLastStatus","running");
		        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
//		        	int length=lineTxt[0].length();
//		        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//		        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//		        	//�����´�������ʱ��
			        
		        }
		        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("error")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("PodFinLastStatus","error");
		        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
		        	int length=lineTxt[0].length();
		        	PodFinLastEndTime=lineTxt[0].substring(length-21,length);
		        	request.setAttribute("PodFinLastEndTime", PodFinLastEndTime);
//		        	//�����´�������ʱ��
		        	/*********�ļ�����**********/
		        	
		        }
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_fnl.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	PodFinNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		    
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodFinNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodFinNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodFinNextBeginTime", DF.format(DF.parse(PodFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
			}
		}
		/*********���չ��״̬���ϴν���ʱ�䡢�´�����ʱ�䴦�����**********/
		
		/*********���������Ӳ�״̬���ϴν���ʱ�䡢�´�����ʱ��**********/
		//�ȶ�run�ļ�,���ҽ�run�ļ��е�ǰ���з���lineTxt�ַ�������
		if(true){
			if(true){
				File file=new File(S[9]);
				String lineTxt[]=new String[2];
				/*********�ļ�����**********/
		        if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(file));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            int i=0;
		            while((tem=bufferedReader.readLine()) != null&&i<=1){
		                if(!tem.equals(""))
		                {
		                	lineTxt[i]=tem;
		                	i++;
		                }
		            }
		            read.close();
		        }
		        else
		        {
		        	System.out.println("No File!");
		        }
		        /*********�ļ�����**********/
		        
		        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("finished")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("ClkFinLastStatus","finished");
		        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
		        	int length=lineTxt[0].length();
		        	ClkFinLastEndTime=lineTxt[0].substring(length-21,length);
		        	request.setAttribute("ClkFinLastEndTime", ClkFinLastEndTime);
//		        	//�����´�������ʱ��
		        	/*********�ļ�����**********/
			        
		        }
		        
		      //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("running")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("ClkFinLastStatus","running");
		        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
//		        	int length=lineTxt[0].length();
//		        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//		        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//		        	//�����´�������ʱ��
		        	
			      
			        
		        }
		        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("error")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("ClkFinLastStatus","error");
		        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
		        	int length=lineTxt[0].length();
		        	ClkFinLastEndTime=lineTxt[0].substring(length-21,length);
		        	request.setAttribute("ClkFinLastEndTime", ClkFinLastEndTime);
//		        	//�����´�������ʱ��
		        	/*********�ļ�����**********/
		        	
		        }
		        File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("pod_fnl.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	ClkFinNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		    
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		ClkFinNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		ClkFinNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("ClkFinNextBeginTime", DF.format(DF.parse(ClkFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
			}
		}
		/*********�����Ӳ�״̬���ϴν���ʱ�䡢�´�����ʱ�䴦�����**********/
		
		/*********�������յ����״̬���ϴν���ʱ�䡢�´�����ʱ��**********/
		//�ȶ�run�ļ�,���ҽ�run�ļ��е�ǰ���з���lineTxt�ַ�������
		if(true){
			if(true){
				File file=new File(S[10]);
				String lineTxt[]=new String[2];
				/*********�ļ�����**********/
		        if(file.isFile() && file.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(file));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            int i=0;
		            while((tem=bufferedReader.readLine()) != null&&i<=1){
		                if(!tem.equals(""))
		                {
		                	lineTxt[i]=tem;
		                	i++;
		                }
		            }
		            read.close();
		        }
		        else
		        {
		        	System.out.println("No File!");
		        }
		        /*********�ļ�����**********/
		        
		        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("finished")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("IonFinLastStatus","finished");
		        	//�����finished״̬�Ļ�����Ҫ��run�ļ��ĵ�һ�н������ϴν�����ʱ��
		        	int length=lineTxt[0].length();
		        	IonFinLastEndTime=lineTxt[0].substring(length-21,length);
		        	request.setAttribute("IonFinLastEndTime", IonFinLastEndTime);
//		        	//�����´�������ʱ��
		        	/*********�ļ�����**********/
			        
		        }
		        
		      //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("running")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("IonFinLastStatus","running");
		        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
//		        	int length=lineTxt[0].length();
//		        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//		        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//		        	//�����´�������ʱ��
		        	
			        
		        }
		        //run�ļ�����֮��ȷ����ǰ��Ʒ��״̬finished��running��error
		        if(lineTxt[1].indexOf("error")!=-1)
		        {
		        	//��״̬����jsp��
		        	request.setAttribute("IonFinLastStatus","error");
		        	//�����running״̬�Ļ������ô�run�ļ��н������ϴ����н���ʱ��
		        	int length=lineTxt[0].length();
		        	IonFinLastEndTime=lineTxt[0].substring(length-21,length);
		        	request.setAttribute("IonFinLastEndTime", IonFinLastEndTime);
//		        	//�����´�������ʱ��
		        	/*********�ļ�����**********/
		        	
		        }
		        File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //�ж��ļ��Ƿ����
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //���ǵ������ʽ
		            BufferedReader bufferedReader = new BufferedReader(read);
		            String tem="NULL";
		            while((tem=bufferedReader.readLine()) != null){
		                if(tem.indexOf("ion_fnl_pcs.sh")!=-1)
		                {
		                	lineTxtNext=tem;
		                	break;
		                }
		            }
		            read.close();
		        }
		        /*********�ļ�����**********/
		        //������crontab���´�����������
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	IonFinNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
					} catch (ParseException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
		        }
		    
		        else
		        {
		        	if(nowMonth==1&&nowDay==31)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		IonFinNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		IonFinNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("IonFinNextBeginTime", DF.format(DF.parse(IonFinNextBeginTime)));
						} catch (ParseException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}
		        	}
		        }
			}
		}
		/*********���յ����״̬���ϴν���ʱ�䡢�´�����ʱ�䴦�����**********/
		request.getRequestDispatcher("/BusinessProcessDisplay.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}