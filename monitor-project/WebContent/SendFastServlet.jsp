<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%  
       //页面每隔2秒自动刷新一遍      
       response.setHeader("refresh","600");  
%>
<html>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>上传：快速产品</title>
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
					<h1 >
						<a><font color="red">(10.12.5.6-->综合服务中心)快速产品上传</font></a></br>					
					</h1>
					<p class="meta"><span class="posted"><font size="+2">(${weeks }周&nbsp${weeknum }天&nbsp${hour })</font> </span></p>		
					<div style="clear: both;">&nbsp;</div>
				</div>
				<div class="post">
					<h2 class="title">
						<a >快速：卫星轨道文件 </a>					
					</h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="finallylistfasp3" items="${finallylistfasp3 }">
							${finallylistfasp3}<br>
						</c:forEach>
				  	</div>
				</div>
				<div class="post">
					<h2 class="title">
						<a >快速：钟差文件</a>
					</h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="finallylistfaclk" items="${finallylistfaclk }">
							${finallylistfaclk}<br>
						</c:forEach>
				  	</div>
				</div>
				
				<div class="post">
					<h2 class="title">
						<a >快速：地球自转参数文件</a>
					</h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="finallylistfaerp" items="${finallylistfaerp }">
							${finallylistfaerp}<br>
						</c:forEach>
				  	</div>
				</div>
				
				<div class="post">
					<h2 class="title">
						<a >快速：电离层参数文件</a>
					</h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="finallylistfaion" items="${finallylistfaion }">
							${finallylistfaion}<br>
						</c:forEach>
				  	</div>
				</div>
				<br>
				<br>
				<br>
				
				<div class="post">
					<h1 >
						<a><font color="Red">(192.168.7.10-->10.12.5.6)快速产品上传</font></a></br>					
					</h1>
					
					<div style="clear: both;">&nbsp;</div>
				</div>
				<div class="post">
					<h2 class="title">
						<a >快速：卫星轨道文件 </a>					
					</h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="listfasp3" items="${listfasp3 }">
							${listfasp3}<br>
						</c:forEach>
				  	</div>
				</div>
				<div class="post">
					<h2 class="title">
						<a >快速：钟差文件</a>
					</h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="listfaclk" items="${listfaclk }">
							${listfaclk}<br>
						</c:forEach>
				  	</div>
				</div>
				
				<div class="post">
					<h2 class="title">
						<a >快速：地球自转参数文件</a>
					</h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="listfaerp" items="${listfaerp }">
							${listfaerp}<br>
						</c:forEach>
				  	</div>
				</div>
				
				<div class="post">
					<h2 class="title">
						<a >快速：电离层参数文件</a>
					</h2>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<c:forEach var="listfaion" items="${listfaion }">
							${listfaion}<br>
						</c:forEach>
				  	</div>
				</div>
				<div style="clear: both;">&nbsp;</div>
			</div>
			<!-- end #content -->
			<div id="sidebar">
				<ul>
					<li>
						<div id="search" >
							<form method="post" action="${pageContext.request.contextPath }/SendFastFormServlet">
							  <div>
									年：<input type="text" name="year" id="search-text" value="" /></br>
									月：<input type="text" name="month" id="search-text" value="" /></br>
									日：<input type="text" name="date" id="search-text" value="" />
									   <input type="submit" id="search-submit" value="GO" />
									   <span class="message">${message}</span>
							  </div>
						  </form>
						</div>
						<div style="clear: both;">&nbsp;</div>
					</li>
					<li>
						<h2>&nbsp;</h2>
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