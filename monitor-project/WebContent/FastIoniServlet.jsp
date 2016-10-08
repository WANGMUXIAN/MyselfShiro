<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
       //页面每隔2秒自动刷新一遍      
       response.setHeader("refresh","2");  
%>  
<html>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>快速产品：电离层</title>
<link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Marvel' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Marvel|Delius+Unicase' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Arvo' rel='stylesheet' type='text/css'>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script>
		function hiddenDiv(div){
			div.style.display=(div.style.display=='none'?'block':'none');
		}
	</script>
</head>
<body>
<div id="wrapper">
	<div id="wrapper2">
		<div id="header" class="container">
			<div id="logo">
				<h1><a > <span></span></a></h1>
			</div>
			<div id="menu">
				<a>GNSS精密分析处理监控系统</a>
			</div>
		</div>
		<!-- end #header -->
		<div id="page">
			<div id="content">
				<div class="post">
					<h2 class="title"><a >快速产品：电离层 </a></h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="list5" items="${list5 }">
							${list5}<br>
						</c:forEach>
				  	</div>
				</div>
				<div style="clear: both;">&nbsp;</div>
			</div>
			<!-- end #content -->
			<div id="sidebar">
				<ul>
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