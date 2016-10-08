<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>产品上传</title>
<link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Marvel' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Marvel|Delius+Unicase' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Arvo' rel='stylesheet' type='text/css'>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script>
		function hiddenDiv(div){
			div.style.display=(div.style.display=='none'?'block':'none');
		}
		
		function CLASS_LIANDONG_YAO(array) {
		   //数组，联动的数据源
		  	this.array=array; 
		  	this.indexName='';
		  	this.obj='';
		  	//设置子SELECT
			// 参数：当前onchange的SELECT ID，要设置的SELECT ID
		    this.subSelectChange=function(selectName1,selectName2)
		  	{
		    	var obj1=document.all[selectName1];
		    	var obj2=document.all[selectName2];
		    	var objName=this.toString();
		    	var me=this;
		    	obj1.onchange=function()
		    	{
		    		me.optionChange(this.options[this.selectedIndex].value,obj2.id)
		   		}
		  	}
		  	//设置第一个SELECT
			// 参数：indexName指选中项,selectName指select的ID
		  	this.firstSelectChange=function(indexName,selectName)  
		  	{
		  		this.obj=document.all[selectName];
		  		this.indexName=indexName;
		  		this.optionChange(this.indexName,this.obj.id)
		  	}
		  // indexName指选中项,selectName指select的ID
		  	this.optionChange=function (indexName,selectName)
		  	{
		    	var obj1=document.all[selectName];
		    	var me=this;
		    	obj1.length=0;
		    	obj1.options[0]=new Option("请选择",'');
		    	for(var i=0;i<this.array.length;i++)
		    	{	
		    		if(this.array[i][1]==indexName)
		    		{
		    		//alert(this.array[i][1]+" "+indexName);
		      	obj1.options[obj1.length]=new Option(this.array[i][2],this.array[i][0]);
		    		}
		    	}
		  	}	
		  }

</script>
</head>
<body>
<div id="wrapper">
	<div id="wrapper2">
		<div id="header" class="container">
			<div id="logo">
				<h1><a href="#"> <span></span></a></h1>
			</div>
			<div id="menu">
				<a>GNSS精密分析处理监控系统</a>
			</div>
		</div>
		<!-- end #header -->
		<div id="page">
			<div id="content">
				<div class="post">
					<!--<h2 class="title">&nbsp;</h2>
					<h2 class="title">&nbsp;</h2>
					<h2 class="title">&nbsp;</h2>
					<h2><font color="#0000CC" >GNSS精密分析处理监控系统</font></h2>
					<p class="meta"><span class="posted"><font size="+2">中国科学院国家授时中心</font></span></p>
					<div style="clear: both;"></div>
					<div class="entry">
				   	</div>-->
				   	<form method="post" action="${pageContext.request.contextPath }/ProductUploadServlet">
						 From:<input type="text" name="TimeFrom" value="yyyy-MM-dd HH:mm:ss" onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}" style="color:#737e73;"/><br>
						 <p/>
						 &nbsp&nbsp&nbsp&nbspTo:<input type="text" name="TimeTo" value="yyyy-MM-dd HH:mm:ss" onfocus="if(this.value==defaultValue) {this.value='';}" onblur="if(!value) {value=defaultValue; this.type='text';}" style="color:#737e73;"/><br>
						 请选择产品类别：<SELECT ID="s1" NAME="s1"  ><OPTION selected></OPTION></SELECT>
  						 <SELECT ID="s2" NAME="s2"  ><OPTION selected></OPTION></SELECT>
  						 <script language="javascript">
							//例子1-------------------------------------------------------------
							//数据源
							var array=new Array();
						    //array[0]=new Array("超快速产品","根目录","超快速产品"); //数据格式 ID，父级ID，名称
						    array[0]=new Array("NTU","根目录","NTU");
						    array[1]=new Array("NTR","根目录","NTR");
						    array[8]=new Array("NTS","根目录","NTS");
						    array[2]=new Array("sp3","NTU","sp3");
						    //array[3]=new Array("地球自转参数文件(ERP)","超快速产品","地球自转参数文件(ERP)");
						    array[3]=new Array("erp","NTU","erp");
						    array[4]=new Array("tro","NTU","tro");
						    array[5]=new Array("sp3","NTR","sp3");	
						    array[6]=new Array("clk","NTR","clk");
						    array[7]=new Array("erp","NTR","erp");
						    array[9]=new Array("ion","NTR","ion");
						    array[10]=new Array("sp3","NTS","sp3");
							array[11]=new Array("clk","NTS","clk");
							array[12]=new Array("snx","NTS","snx");
							array[13]=new Array("erp","NTS","erp");
							array[14]=new Array("tro","NTS","tro");
							array[15]=new Array("ion","NTS","ion");
							array[16]=new Array("dcb","NTS","dcb");
							array[17]=new Array("sum","NTS","sum");
							//这是调用代码
							var liandong=new CLASS_LIANDONG_YAO(array) //设置数据源
							liandong.firstSelectChange("根目录","s1"); //设置第一个选择框
							liandong.subSelectChange("s1","s2"); //设置子级选择框
							</script>
  						 <input type="submit" id="search-submit" value="GO" />
					</form>
					
					<!-- <img src="D:/hyd.jpg"  title="黄一丹"  style="float:left;z-index:5" >
					<br style="clear:left">
					<img src="images/hyd.jpg"  title="王目现"  width="100" height="100" >-->
					<br style="clear:left">
					<!--  <table width=”450″ border=”1″ cellspacing=”0″ cellpadding=”2″ bordercolor=”#009900″>
						<tr>
						<td>姓名</td>
						<td>性别</td>
						<td>年龄</td>>
						<tr>
						<td>你好</td>
						<td colspan=”2″>你好</td>
						</tr>
						<tr>
						<td rowspan=”2″>你好</td>
						<td>你好</td>
						<td>你好</td>
						</tr>
						<tr>
						<td>你好</td>
						<td>你好</td>
						</tr>
					</table>-->
					<!--table border="1"cellpadding="0"cellspacing="0">
						<tr rowspan="3" >
    						<td colspan="3">& n b s p ;</td>
						</tr>
						<tr>
							<td>姓名</td>
							<td>性别</td>
							<td>年龄</td>
						<tr/>
						<tr>
    						<td>1</td>
    						<td>a</td>
						</tr>
						<tr>
    						<td>2</td>
    						<td>d</td>
						</tr>
					</table>-->
					
				</div>
			</div>
			<!-- end #content -->
			<div id="sidebar">
				<ul>
					<li>
						<div style="clear: both;">&nbsp;</div>
					</li>
					<li>
						<h2>&nbsp;</h2>
					</li>
			<div class="style21">
		<a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('1'))"><h1>数据管理</h1></a>			
			<div class="style211" id="1">
			<ul>
				<li><a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('style214'))"><h2>数据主动下载</h2></a></li>
				<div class="style212" id="style214">				
					<ul>
						<li><a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('style215'))"> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp天文件</a>
							<div class="style213" id="style215">
								<ul>
									<li><a href="${pageContext.request.contextPath }/DailydServlet" target="_blank">d（观测文件）</a></li>
									<li><a href="${pageContext.request.contextPath }/DailyrServlet" target="_blank">r(BDS广播星历)</a></li>
									<li><a href="${pageContext.request.contextPath }/DailynServlet" target="_blank">n(GPS广播星历)</a></li>
									<li><a href="${pageContext.request.contextPath }/DailygServlet" target="_blank">g(GLONASS广播星历)</a></li>
									<li><a href="${pageContext.request.contextPath }/DailypServlet" target="_blank">p(Multi-GNSS广播星历)</a></li>
									<li><a href="${pageContext.request.contextPath }/DailylServlet" target="_blank">l(伽利略)</a></li>
								</ul>
							</div>	
						</li>
						<li><a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('style216'))">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp小时文件</a>
							<div class="style213" id="style216">
								<ul>
									<li><a href="${pageContext.request.contextPath }/HourlydServlet" target="_blank">d（观测文件）</a></li>
									<li><a href="${pageContext.request.contextPath }/HourlyrServlet" target="_blank">r(BDS广播星历)</a></li>
									<li><a href="${pageContext.request.contextPath }/HourlynServlet" target="_blank">n(GPS广播星历)</a></li>
									<li><a href="${pageContext.request.contextPath }/HourlygServlet" target="_blank">g(GLONASS广播星历)</a></li>
									<li><a href="${pageContext.request.contextPath }/HourlypServlet" target="_blank">p(Multi-GNSS广播星历)</a></li>
									<li><a href="${pageContext.request.contextPath }/HourlylServlet" target="_blank">l(伽利略)</a></li>
								</ul>
							</div>	
						</li>
					</ul>
				</div>																				
			</ul>				
			</div>				
		</div>
			<div class="style21">
				<a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('2'))"><h1>运行管理</h1></a>
				<div class="style211" id="2">
				<ul>
					<li><a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('style217'))"><h2>产品生成</h2></a>
						<div class="style212" id="style217">				
					<ul>
						<li><a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('style218'))">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp超快产品</a>
							<div class="style213" id="style218">
								<ul>
									<li><a href="${pageContext.request.contextPath }/SFastOETServlet" target="_blank">轨道、EPR、对流层</a></li>
									<li><a href="${pageContext.request.contextPath }/SFastClockServlet" target="_blank">钟差</a></li>
								</ul>
							</div>	
						</li>
						<li><a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('style219'))">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp快速产品</a>
							<div class="style213" id="style219">
								<ul>
									<li><a href="${pageContext.request.contextPath }/FastOETServlet" target="_blank">轨道、EPR、对流层</a></li>
									<li><a href="${pageContext.request.contextPath }/FastClockServlet" target="_blank">钟差</a></li>
									<li><a href="${pageContext.request.contextPath }/FastIoniServlet" target="_blank">电离层</a></li>
								</ul>
							</div>	
						</li>
						<li><a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('style220'))">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp最终产品</a>
							<div class="style213" id="style220">
								<ul>
									<li><a href="${pageContext.request.contextPath }/FinalOETServlet" target="_blank">轨道、EPR、对流层</a></li>
									<li><a href="${pageContext.request.contextPath }/FinalClockServlet" target="_blank">钟差</a></li>
									<li><a href="${pageContext.request.contextPath }/FinalIoniServlet" target="_blank">电离层</a></li>
								</ul>
							</div>	
						</li>
					</ul>
				</div>							
			</li>
			<li><a href="${pageContext.request.contextPath }/BusinessProcessDisplayServlet" target="_blank"><h2>业务进程展示</h2></a>
			</li>
			<li><a href="${pageContext.request.contextPath }/CrontabServlet" target="_blank"><h2>软件任务计划</h2></a>
			</li>										
		</ul>								
		</div>
		</div>
			<div class="style21">
				<a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('3'))"><h1>产品管理</h1></a>				
				<div class="style211" id="3">
					<ul>
						<li><a href="javascript:void(0)" onClick="hiddenDiv(document.getElementById('style221'))"><h2>产品上传</h2></a>
							<div class="style212" id="style221">				
								<ul>
									<li><a href="${pageContext.request.contextPath }/SendSuFastServlet" target="_blank">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp超快产品</a>							
									</li>
									<li><a href="${pageContext.request.contextPath }/SendFastServlet" target="_blank">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp快速产品</a>							
									</li>
									<li><a href="${pageContext.request.contextPath }/SendFinalServlet" target="_blank">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp最终产品</a>
									</li>
									<li><a href="${pageContext.request.contextPath }/SendFailServlet" target="_blank">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp上传失败</a>
									</li>
								</ul>
							</div>
						</li>
						<li><a href="ProductUpload.jsp" target="_blank"><h2>产品手动上传</h2></a>
						</li>						
					</ul>
				</div>
			</div>		
		</ul>
	</div>
	<!-- end #sidebar -->
	<div style="clear: both;">&nbsp;</div>
	</div>
	<!-- end #page -->
		<div id="footer">
			<p>version 1.0</p>
		</div>
	</div>
</div>
<!-- end #footer -->
</body>
</html>