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

public class BusinessProcessDisplayServletBAK extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessProcessDisplayServletBAK() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取session对象
		HttpSession session = request.getSession();
		//获取当前时间
		SimpleDateFormat DF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now=DF.format(new Date(System.currentTimeMillis()));
		int nowYear=Integer.parseInt(now.substring(0,4));
		int nowMonth=Integer.parseInt(now.substring(5,7));
		int nowDay=Integer.parseInt(now.substring(8,10));
		int nowHour=Integer.parseInt(now.substring(11,13));
		int nowMinute=Integer.parseInt(now.substring(14,16));
		//定义上次结束时间和下次启动时间的字符串
		//超快
		String PodUltLastEndTime="";
		String PodUltNextBeginTime="";
		String ClkUltLastEndTime="";
		String ClkUltNextBeginTime="";
		//快速
		String PodRpdLastEndTime="";
		String PodRpdNextBeginTime="";
		String ClkRpdLastEndTime="";
		String ClkRpdNextBeginTime="";
		String IonRpdLastEndTime="";
		String IonRpdNextBeginTime="";
		//最终
		
		//先从配置文件读出各个业务进程crontab和run文件的路径，在下面程序中统一调用
		String[] S=null;
		try {
			S=ParseXML.ReadXML();
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		/****超快轨道、erp、对流层   69
	 	 ****超快钟差  710
		 ****快速轨道、erp、对流层  834  
		 ****快速钟差   1393
		 ****快速电离层  
		 ****最终轨道、erp、电离层
		 ****最终钟差
		 ****最终电离层
		 ****/
		/*********先处理超快轨道、erp、对流层状态、上次结束时间、下次启动时间**********/
		//先读run文件,并且将run文件中的前两行放在lineTxt字符串组中
		if(true){
			File file=new File(S[3]);
			String lineTxt[]=new String[2];
			/*********文件操作**********/
	        if(file.isFile() && file.exists()){         //判断文件是否存在
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file));    //考虑到编码格式
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
	        /*********文件操作**********/
	        
	        //run文件读完之后，确定当前产品的状态finished、running、error
	        if(lineTxt[1].indexOf("finished")!=-1)
	        {
	        	//将状态传到jsp中
	        	request.setAttribute("PodUltLastStatus","finished");
	        	//如果是finished状态的话，就要从run文件的第一行解析出上次结束的时间
	        	int length=lineTxt[0].length();
	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//	        	//计算下次启动的时间
	        	/*********文件操作**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //考虑到编码格式
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
		        }//sunbaoqi@ntsc.ac.cn
		        /*********文件操作**********/
		        //解析出crontab中下次启动的数据
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
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
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
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        //run文件读完之后，确定当前产品的状态finished、running、error
	        if(lineTxt[1].indexOf("running")!=-1)
	        {
	        	//将状态传到jsp中
	        	request.setAttribute("PodUltLastStatus","running");
	        	//如果是running状态的话，不用从run文件中解析出上次运行结束时间
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//	        	//计算下次启动的时间
	        	/*********文件操作**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //考虑到编码格式
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
		        /*********文件操作**********/
		        //解析出crontab中下次启动的数据
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
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
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
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        //run文件读完之后，确定当前产品的状态finished、running、error
	        if(lineTxt[1].indexOf("error")!=-1)
	        {
	        	//将状态传到jsp中
	        	request.setAttribute("PodUltLastStatus","error");
	        	//如果是running状态的话，不用从run文件中解析出上次运行结束时间
	        	int length=lineTxt[0].length();
	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
//	        	//计算下次启动的时间
	        	/*********文件操作**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //考虑到编码格式
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
		        /*********文件操作**********/
		        //解析出crontab中下次启动的数据
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
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
		        }
		        else if((nowHour>ThirdHour||(nowHour==ThirdHour&&nowMinute>=minute))&&(nowHour<FourthHour||(nowHour==FourthHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FourthHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
		        }
		        else if((nowHour>FourthHour||(nowHour==FourthHour&&nowMinute>=minute))&&(nowHour<FirstHour||(nowHour==FirstHour&&nowMinute<minute)))
		        {
		        	PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+FirstHour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
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
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodUltNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodUltNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+SecondHour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodUltNextBeginTime", DF.format(DF.parse(PodUltNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        }
	        }
		}
		/*********超快轨道、erp、对流层状态、上次结束时间、下次启动时间处理完毕**********/
		
		
		/*********处理超快钟差状态、上次结束时间、下次启动时间**********/
		//先读run文件,并且将run文件中的前两行放在lineTxt字符串组中
//		if(true){
//			File file=new File(S[4]);
//			String lineTxt[]=new String[2];
//			/*********文件操作**********/
//	        if(file.isFile() && file.exists()){         //判断文件是否存在
//	            InputStreamReader read = new InputStreamReader(
//	            new FileInputStream(file));    //考虑到编码格式
//	            BufferedReader bufferedReader = new BufferedReader(read);
//	            String tem="NULL";
//	            int i=0;
//	            while((tem=bufferedReader.readLine()) != null&&i<=1){
//	                if(!tem.equals(""))
//	                {
//	                	lineTxt[i]=tem;
//	                	i++;
//	                }
//	            }
//	            read.close();
//	        }
//	        else
//	        {
//	        	System.out.println("No File!");
//	        }
//	        /*********文件操作**********/
//	        
//	        //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("finished")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("ClkUltLastStatus","finished");
//	        	//如果是finished状态的话，就要从run文件的第一行解析出上次结束的时间
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("ClkUltLastEndTime", ClkUltLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("clk_ult_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//	        }
//	        
//	      //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("running")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("ClkUltLastStatus","running");
//	        	//如果是running状态的话，不用从run文件中解析出上次运行结束时间
////	        	int length=lineTxt[0].length();
////	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
////	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("clk_ult_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//		        
//	        }
//	        //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("error")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("ClkUltLastStatus","error");
//	        	//如果是running状态的话，不用从run文件中解析出上次运行结束时间
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("ClkUltLastEndTime", ClkUltLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("clk_ult_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//	        }
//		}
		/*********超快钟差状态、上次结束时间、下次启动时间处理完毕**********/
		
		
		
		/*********处理快速轨道、erp、对流层状态、上次结束时间、下次启动时间**********/
		//先读run文件,并且将run文件中的前两行放在lineTxt字符串组中
		if(true){
			File file=new File(S[5]);
			String lineTxt[]=new String[2];
			/*********文件操作**********/
	        if(file.isFile() && file.exists()){         //判断文件是否存在
	            InputStreamReader read = new InputStreamReader(
	            new FileInputStream(file));    //考虑到编码格式
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
	        /*********文件操作**********/
	        
	        //run文件读完之后，确定当前产品的状态finished、running、error
	        if(lineTxt[1].indexOf("finished")!=-1)
	        {
	        	//将状态传到jsp中
	        	request.setAttribute("PodRpdLastStatus","finished");
	        	//如果是finished状态的话，就要从run文件的第一行解析出上次结束的时间
	        	int length=lineTxt[0].length();
	        	PodRpdLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("PodRpdLastEndTime", PodRpdLastEndTime);
//	        	//计算下次启动的时间
	        	/*********文件操作**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //考虑到编码格式
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
		        /*********文件操作**********/
		        //解析出crontab中下次启动的数据
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
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
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        //run文件读完之后，确定当前产品的状态finished、running、error
	        if(lineTxt[1].indexOf("running")!=-1)
	        {
	        	//将状态传到jsp中
	        	request.setAttribute("PodRpdLastStatus","running");
//	        	//如果是finished状态的话，就要从run文件的第一行解析出上次结束的时间
//	        	int length=lineTxt[0].length();
//	        	PodRpdLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("PodRpdLastEndTime", PodRpdLastEndTime);
//	        	//计算下次启动的时间
	        	/*********文件操作**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //考虑到编码格式
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
		        /*********文件操作**********/
		        //解析出crontab中下次启动的数据
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
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
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        }
	        }
	        //run文件读完之后，确定当前产品的状态finished、running、error
	        if(lineTxt[1].indexOf("error")!=-1)
	        {
	        	//将状态传到jsp中
	        	request.setAttribute("PodRpdLastStatus","error");
//	        	//如果是finished状态的话，就要从run文件的第一行解析出上次结束的时间
	        	int length=lineTxt[0].length();
	        	PodRpdLastEndTime=lineTxt[0].substring(length-21,length);
	        	request.setAttribute("PodRpdLastEndTime", PodRpdLastEndTime);
//	        	//计算下次启动的时间
	        	/*********文件操作**********/
	        	File fileNext=new File(S[12]);
				String lineTxtNext="";
		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
		            InputStreamReader read = new InputStreamReader(
		            new FileInputStream(fileNext));    //考虑到编码格式
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
		        /*********文件操作**********/
		        //解析出crontab中下次启动的数据
		        int minute=Integer.parseInt(lineTxtNext.substring(0,2));
		        int Hour=Integer.parseInt(lineTxtNext.substring(3,5));
		        if((nowHour<Hour||(nowHour==Hour&&nowMinute<minute)))
		        {
		        	PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+nowDay+" "+Hour+":"+minute+":"+"00";
		        	try {
						request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
					} catch (ParseException e) {
						// TODO 自动生成的 catch 块
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
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if((nowMonth==2&&nowDay==28)||(nowMonth==2&&nowDay==29))
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==3&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==4&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==5&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==6&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==7&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==8&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==9&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==10&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==11&&nowDay==30)
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+(nowMonth+1)+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else if(nowMonth==12&&nowDay==31)
		        	{
		        		PodRpdNextBeginTime=(nowYear+1)+"-"+"01"+"-"+"01"+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        	else
		        	{
		        		PodRpdNextBeginTime=nowYear+"-"+nowMonth+"-"+(nowDay+1)+" "+Hour+":"+minute+":"+"00";
			        	try {
							request.setAttribute("PodRpdNextBeginTime", DF.format(DF.parse(PodRpdNextBeginTime)));
						} catch (ParseException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
		        	}
		        }
	        }
		}
		/*********快速轨道、erp、对流层状态、上次结束时间、下次启动时间处理完毕**********/
		
		
//		/*********处理快速钟差状态、上次结束时间、下次启动时间**********/
//		//先读run文件,并且将run文件中的前两行放在lineTxt字符串组中
//		if(true){
//			File file=new File(S[6]);
//			String lineTxt[]=new String[2];
//			/*********文件操作**********/
//	        if(file.isFile() && file.exists()){         //判断文件是否存在
//	            InputStreamReader read = new InputStreamReader(
//	            new FileInputStream(file));    //考虑到编码格式
//	            BufferedReader bufferedReader = new BufferedReader(read);
//	            String tem="NULL";
//	            int i=0;
//	            while((tem=bufferedReader.readLine()) != null&&i<=1){
//	                if(!tem.equals(""))
//	                {
//	                	lineTxt[i]=tem;
//	                	i++;
//	                }
//	            }
//	            read.close();
//	        }
//	        else
//	        {
//	        	System.out.println("No File!");
//	        }
//	        /*********文件操作**********/
//	        
//	        //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("finished")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("ClkRpdLastStatus","finished");
//	        	//如果是finished状态的话，就要从run文件的第一行解析出上次结束的时间
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("ClkRpdLastEndTime", ClkRpdLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("clk_rpd_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//	        }
//	        
//	      //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("running")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("ClkRpdLastStatus","running");
//	        	//如果是running状态的话，不用从run文件中解析出上次运行结束时间
////	        	int length=lineTxt[0].length();
////	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
////	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("clk_rpd_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//		        
//	        }
//	        //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("error")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("ClkRpdLastStatus","error");
//	        	//如果是running状态的话，不用从run文件中解析出上次运行结束时间
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("ClkRpdLastEndTime", ClkRpdLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("clk_rpd_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//	        }
//		}
//		/*********快速钟差状态、上次结束时间、下次启动时间处理完毕**********/
//		
//		/*********处理快速电离层状态、上次结束时间、下次启动时间**********/
//		//先读run文件,并且将run文件中的前两行放在lineTxt字符串组中
//		if(true){
//			File file=new File(S[7]);
//			String lineTxt[]=new String[2];
//			/*********文件操作**********/
//	        if(file.isFile() && file.exists()){         //判断文件是否存在
//	            InputStreamReader read = new InputStreamReader(
//	            new FileInputStream(file));    //考虑到编码格式
//	            BufferedReader bufferedReader = new BufferedReader(read);
//	            String tem="NULL";
//	            int i=0;
//	            while((tem=bufferedReader.readLine()) != null&&i<=1){
//	                if(!tem.equals(""))
//	                {
//	                	lineTxt[i]=tem;
//	                	i++;
//	                }
//	            }
//	            read.close();
//	        }
//	        else
//	        {
//	        	System.out.println("No File!");
//	        }
//	        /*********文件操作**********/
//	        
//	        //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("finished")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("IonRpdLastStatus","finished");
//	        	//如果是finished状态的话，就要从run文件的第一行解析出上次结束的时间
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("IonRpdLastEndTime", IonRpdLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("ion_rpd_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//	        }
//	        
//	      //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("running")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("IonRpdLastStatus","running");
//	        	//如果是running状态的话，不用从run文件中解析出上次运行结束时间
////	        	int length=lineTxt[0].length();
////	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
////	        	request.setAttribute("PodUltLastEndTime", PodUltLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("ion_rpd_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//		        
//	        }
//	        //run文件读完之后，确定当前产品的状态finished、running、error
//	        if(lineTxt[1].indexOf("error")!=-1)
//	        {
//	        	//将状态传到jsp中
//	        	request.setAttribute("IonRpdLastStatus","error");
//	        	//如果是running状态的话，不用从run文件中解析出上次运行结束时间
//	        	int length=lineTxt[0].length();
//	        	PodUltLastEndTime=lineTxt[0].substring(length-21,length);
//	        	request.setAttribute("IonRpdLastEndTime", IonRpdLastEndTime);
////	        	//计算下次启动的时间
//	        	/*********文件操作**********/
//	        	File fileNext=new File(S[12]);
//				String lineTxtNext="";
//		        if(fileNext.isFile() && fileNext.exists()){         //判断文件是否存在
//		            InputStreamReader read = new InputStreamReader(
//		            new FileInputStream(fileNext));    //考虑到编码格式
//		            BufferedReader bufferedReader = new BufferedReader(read);
//		            String tem="NULL";
//		            while((tem=bufferedReader.readLine()) != null){
//		                if(tem.indexOf("ion_rpd_pcs.sh")!=-1)
//		                {
//		                	lineTxtNext=tem;
//		                	break;
//		                }
//		            }
//		            read.close();
//		        }
//		        /*********文件操作**********/
//		        //解析出crontab中下次启动的数据
//	        }
//		}
//		/*********快速电离层状态、上次结束时间、下次启动时间处理完毕**********/
//		
//		/*********处理最终轨道、erp、对流层状态、上次结束时间、下次启动时间**********/
//		//先读run文件,并且将run文件中的前两行放在lineTxt字符串组中
//		if(true){
//				}
//		/*********最终轨道状态、上次结束时间、下次启动时间处理完毕**********/
//		
//		/*********处理最终钟差状态、上次结束时间、下次启动时间**********/
//		//先读run文件,并且将run文件中的前两行放在lineTxt字符串组中
//		if(true){
//				}
//		/*********最终钟差状态、上次结束时间、下次启动时间处理完毕**********/
//		
//		/*********处理最终电离层状态、上次结束时间、下次启动时间**********/
//		//先读run文件,并且将run文件中的前两行放在lineTxt字符串组中
//		if(true){
//				}
//		/*********最终电离层状态、上次结束时间、下次启动时间处理完毕**********/
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
