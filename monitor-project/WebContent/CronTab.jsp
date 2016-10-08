<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>任务计划</title>
<link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Marvel' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Marvel|Delius+Unicase' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Arvo' rel='stylesheet' type='text/css'>
<link href="style1.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/pageView.js"></script>
	<script type="text/javascript">
			var currentpage = 0;
	    	var totalpage = 0;
</script>
</head>
<body>
<div id="wrapper">
	<div id="wrapper2">
		<div id="header" class="container">
			<div id="logo">
				<h1><a href="#"><span></span></a></h1>
			</div>
			<div id="menu">
				<a>GNSS精密分析处理监控系统</a>
			</div>
		</div>
		<div align="center">
		<style type="text/css">

table.hovertable {

	 font-family: verdana,arial,sans-serif;

	 font-size:11px;

	 color:#333333;

	 border-width: 1px;

	 border-color: #999999;

	 border-collapse: collapse;

}

table.hovertable th {

	 background-color:#c3dde0;

	 border-width: 1px;

	 padding: 8px;

	 border-style: solid;

	 border-color: #a9c6c9;

}

table.hovertable tr {

	 background-color:#d4e3e5;

}

table.hovertable td {

	 border-width: 1px;

	 padding: 8px;

	 border-style: solid;

	 border-color: #a9c6c9;

}

</style>




<!-- Table goes in the document BODY -->
<form method="post" action="${pageContext.request.contextPath }/CrontabFormServlet">
	<table width="750" class="hovertable">
<tr>
	 <th width="50">minute</th>
	 <th width="100">hour</th>
	 <th width="50">day</th>
	 <th width="50">month</th>
	 <th width="50">week</th>
	 <th >command</th>
</tr>
<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">
	 
	 <td><input size="5" type="text" name="minute1" id="search-text" value=${crontablist[0].minute } /></td>
	 <td><input size="10" type="text" name="hour1" id="search-text" value=${crontablist[0].hour } /></td>
	 <td><input size="5" type="text" name="day1" id="search-text" value=${crontablist[0].day } /></td>
	 <td><input size="5" type="text" name="month1" id="search-text" value=${crontablist[0].month } /></td>
	 <td><input size="5" type="text" name="week1" id="search-text" value=${crontablist[0].week } /></td>
	 <td ><textarea style="width:400px" name="command1" rows="1" id="search-text" >${crontablist[0].command}</textarea></td>

</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute2" id="search-text" value=${crontablist[1].minute } /></td>
	 <td><input size="10" type="text" name="hour2" id="search-text" value=${crontablist[1].hour } /></td>
	 <td><input size="5" type="text" name="day2" id="search-text" value=${crontablist[1].day } /></td>
	 <td><input size="5" type="text" name="month2" id="search-text" value=${crontablist[1].month } /></td>
	 <td><input size="5" type="text" name="week2" id="search-text" value=${crontablist[1].week } /></td>
	 <td ><textarea style="width:400px" name="command2" rows="1" id="search-text" >${crontablist[1].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute3" id="search-text" value=${crontablist[2].minute } /></td>
	 <td><input size="10" type="text" name="hour3" id="search-text" value=${crontablist[2].hour } /></td>
	 <td><input size="5" type="text" name="day3" id="search-text" value=${crontablist[2].day } /></td>
	 <td><input size="5" type="text" name="month3" id="search-text" value=${crontablist[2].month } /></td>
	 <td><input size="5" type="text" name="week3" id="search-text" value=${crontablist[2].week } /></td>
	 <td ><textarea style="width:400px" name="command3" rows="1" id="search-text" >${crontablist[2].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute4" id="search-text" value=${crontablist[3].minute } /></td>
	 <td><input size="10" type="text" name="hour4" id="search-text" value=${crontablist[3].hour } /></td>
	 <td><input size="5" type="text" name="day4" id="search-text" value=${crontablist[3].day } /></td>
	 <td><input size="5" type="text" name="month4" id="search-text" value=${crontablist[3].month } /></td>
	 <td><input size="5" type="text" name="week4" id="search-text" value=${crontablist[3].week } /></td>
	 <td ><textarea style="width:400px" name="command4" rows="1" id="search-text" >${crontablist[3].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute5" id="search-text" value=${crontablist[4].minute } /></td>
	 <td><input size="10" type="text" name="hour5" id="search-text" value=${crontablist[4].hour } /></td>
	 <td><input size="5" type="text" name="day5" id="search-text" value=${crontablist[4].day } /></td>
	 <td><input size="5" type="text" name="month5" id="search-text" value=${crontablist[4].month } /></td>
	 <td><input size="5" type="text" name="week5" id="search-text" value=${crontablist[4].week } /></td>
	 <td ><textarea style="width:400px" name="command5" rows="1" id="search-text" >${crontablist[4].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute6" id="search-text" value=${crontablist[5].minute } /></td>
	 <td><input size="10" type="text" name="hour6" id="search-text" value=${crontablist[5].hour } /></td>
	 <td><input size="5" type="text" name="day6" id="search-text" value=${crontablist[5].day } /></td>
	 <td><input size="5" type="text" name="month6" id="search-text" value=${crontablist[5].month } /></td>
	 <td><input size="5" type="text" name="week6" id="search-text" value=${crontablist[5].week } /></td>
	 <td ><textarea style="width:400px" name="command6" rows="1" id="search-text" >${crontablist[5].command}</textarea></td>

</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute7" id="search-text" value=${crontablist[6].minute } /></td>
	 <td><input size="10" type="text" name="hour7" id="search-text" value=${crontablist[6].hour } /></td>
	 <td><input size="5" type="text" name="day7" id="search-text" value=${crontablist[6].day } /></td>
	 <td><input size="5" type="text" name="month7" id="search-text" value=${crontablist[6].month } /></td>
	 <td><input size="5" type="text" name="week7" id="search-text" value=${crontablist[6].week } /></td>
	 <td ><textarea style="width:400px" name="command7" rows="1" id="search-text" >${crontablist[6].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute8" id="search-text" value=${crontablist[7].minute } /></td>
	 <td><input size="10" type="text" name="hour8" id="search-text" value=${crontablist[7].hour } /></td>
	 <td><input size="5" type="text" name="day8" id="search-text" value=${crontablist[7].day } /></td>
	 <td><input size="5" type="text" name="month8" id="search-text" value=${crontablist[7].month } /></td>
	 <td><input size="5" type="text" name="week8" id="search-text" value=${crontablist[7].week } /></td>
	 <td ><textarea style="width:400px" name="command8" rows="1" id="search-text" >${crontablist[7].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute9" id="search-text" value=${crontablist[8].minute } /></td>
	 <td><input size="10" type="text" name="hour9" id="search-text" value=${crontablist[8].hour } /></td>
	 <td><input size="5" type="text" name="day9" id="search-text" value=${crontablist[8].day } /></td>
	 <td><input size="5" type="text" name="month9" id="search-text" value=${crontablist[8].month } /></td>
	 <td><input size="5" type="text" name="week9" id="search-text" value=${crontablist[8].week } /></td>
	 <td ><textarea style="width:400px" name="command9" rows="1" id="search-text" >${crontablist[8].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute10" id="search-text" value=${crontablist[9].minute } /></td>
	 <td><input size="10" type="text" name="hour10" id="search-text" value=${crontablist[9].hour } /></td>
	 <td><input size="5" type="text" name="day10" id="search-text" value=${crontablist[9].day } /></td>
	 <td><input size="5" type="text" name="month10" id="search-text" value=${crontablist[9].month } /></td>
	 <td><input size="5" type="text" name="week10" id="search-text" value=${crontablist[9].week } /></td>
	 <td ><textarea style="width:400px" name="command10" rows="1" id="search-text" >${crontablist[9].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute11" id="search-text" value=${crontablist[10].minute } /></td>
	 <td><input size="10" type="text" name="hour11" id="search-text" value=${crontablist[10].hour } /></td>
	 <td><input size="5" type="text" name="day11" id="search-text" value=${crontablist[10].day } /></td>
	 <td><input size="5" type="text" name="month11" id="search-text" value=${crontablist[10].month } /></td>
	 <td><input size="5" type="text" name="week11" id="search-text" value=${crontablist[10].week } /></td>
	 <td ><textarea style="width:400px" name="command11" rows="1" id="search-text" >${crontablist[10].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute12" id="search-text" value=${crontablist[11].minute } /></td>
	 <td><input size="10" type="text" name="hour12" id="search-text" value=${crontablist[11].hour } /></td>
	 <td><input size="5" type="text" name="day12" id="search-text" value=${crontablist[11].day } /></td>
	 <td><input size="5" type="text" name="month12" id="search-text" value=${crontablist[11].month } /></td>
	 <td><input size="5" type="text" name="week12" id="search-text" value=${crontablist[11].week } /></td>
	 <td ><textarea style="width:400px" name="command12" rows="1" id="search-text" >${crontablist[11].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute13" id="search-text" value=${crontablist[12].minute } /></td>
	 <td><input size="10" type="text" name="hour13" id="search-text" value=${crontablist[12].hour } /></td>
	 <td><input size="5" type="text" name="day13" id="search-text" value=${crontablist[12].day } /></td>
	 <td><input size="5" type="text" name="month13" id="search-text" value=${crontablist[12].month } /></td>
	 <td><input size="5" type="text" name="week13" id="search-text" value=${crontablist[12].week } /></td>
	 <td ><textarea style="width:400px" name="command13" rows="1" id="search-text" >${crontablist[12].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute14" id="search-text" value=${crontablist[13].minute } /></td>
	 <td><input size="10" type="text" name="hour14" id="search-text" value=${crontablist[13].hour } /></td>
	 <td><input size="5" type="text" name="day14" id="search-text" value=${crontablist[13].day } /></td>
	 <td><input size="5" type="text" name="month14" id="search-text" value=${crontablist[13].month } /></td>
	 <td><input size="5" type="text" name="week14" id="search-text" value=${crontablist[13].week } /></td>
	 <td ><textarea style="width:400px" name="command14" rows="1" id="search-text" >${crontablist[13].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute15" id="search-text" value=${crontablist[14].minute } /></td>
	 <td><input size="10" type="text" name="hour15" id="search-text" value=${crontablist[14].hour } /></td>
	 <td><input size="5" type="text" name="day15" id="search-text" value=${crontablist[14].day } /></td>
	 <td><input size="5" type="text" name="month15" id="search-text" value=${crontablist[14].month } /></td>
	 <td><input size="5" type="text" name="week15" id="search-text" value=${crontablist[14].week } /></td>
	 <td ><textarea style="width:400px" name="command15" rows="1" id="search-text" >${crontablist[14].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute16" id="search-text" value=${crontablist[15].minute } /></td>
	 <td><input size="10" type="text" name="hour16" id="search-text" value=${crontablist[15].hour } /></td>
	 <td><input size="5" type="text" name="day16" id="search-text" value=${crontablist[15].day } /></td>
	 <td><input size="5" type="text" name="month16" id="search-text" value=${crontablist[15].month } /></td>
	 <td><input size="5" type="text" name="week16" id="search-text" value=${crontablist[15].week } /></td>
	 <td ><textarea style="width:400px" name="command16" rows="1" id="search-text" >${crontablist[15].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute17" id="search-text" value=${crontablist[16].minute } /></td>
	 <td><input size="10" type="text" name="hour17" id="search-text" value=${crontablist[16].hour } /></td>
	 <td><input size="5" type="text" name="day17" id="search-text" value=${crontablist[16].day } /></td>
	 <td><input size="5" type="text" name="month17" id="search-text" value=${crontablist[16].month } /></td>
	 <td><input size="5" type="text" name="week17" id="search-text" value=${crontablist[16].week } /></td>
	 <td ><textarea style="width:400px" name="command17" rows="1" id="search-text" >${crontablist[16].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute18" id="search-text" value=${crontablist[17].minute } /></td>
	 <td><input size="10" type="text" name="hour18" id="search-text" value=${crontablist[17].hour } /></td>
	 <td><input size="5" type="text" name="day18" id="search-text" value=${crontablist[17].day } /></td>
	 <td><input size="5" type="text" name="month18" id="search-text" value=${crontablist[17].month } /></td>
	 <td><input size="5" type="text" name="week18" id="search-text" value=${crontablist[17].week } /></td>
	 <td ><textarea style="width:400px" name="command18" rows="1" id="search-text" >${crontablist[17].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute19" id="search-text" value=${crontablist[18].minute } /></td>
	 <td><input size="10" type="text" name="hour19" id="search-text" value=${crontablist[18].hour } /></td>
	 <td><input size="5" type="text" name="day19" id="search-text" value=${crontablist[18].day } /></td>
	 <td><input size="5" type="text" name="month19" id="search-text" value=${crontablist[18].month } /></td>
	 <td><input size="5" type="text" name="week19" id="search-text" value=${crontablist[18].week } /></td>
	 <td ><textarea style="width:400px" name="command19" rows="1" id="search-text" >${crontablist[18].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute20" id="search-text" value=${crontablist[19].minute } /></td>
	 <td><input size="10" type="text" name="hour20" id="search-text" value=${crontablist[19].hour } /></td>
	 <td><input size="5" type="text" name="day20" id="search-text" value=${crontablist[19].day } /></td>
	 <td><input size="5" type="text" name="month20" id="search-text" value=${crontablist[19].month } /></td>
	 <td><input size="5" type="text" name="week20" id="search-text" value=${crontablist[19].week } /></td>
	 <td ><textarea style="width:400px" name="command20" rows="1" id="search-text" >${crontablist[19].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute21" id="search-text" value=${crontablist[20].minute } /></td>
	 <td><input size="10" type="text" name="hour21" id="search-text" value=${crontablist[20].hour } /></td>
	 <td><input size="5" type="text" name="day21" id="search-text" value=${crontablist[20].day } /></td>
	 <td><input size="5" type="text" name="month21" id="search-text" value=${crontablist[20].month } /></td>
	 <td><input size="5" type="text" name="week21" id="search-text" value=${crontablist[20].week } /></td>
	 <td ><textarea style="width:400px" name="command21" rows="1" id="search-text" >${crontablist[20].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute22" id="search-text" value=${crontablist[21].minute } /></td>
	 <td><input size="10" type="text" name="hour22" id="search-text" value=${crontablist[21].hour } /></td>
	 <td><input size="5" type="text" name="day22" id="search-text" value=${crontablist[21].day } /></td>
	 <td><input size="5" type="text" name="month22" id="search-text" value=${crontablist[21].month } /></td>
	 <td><input size="5" type="text" name="week22" id="search-text" value=${crontablist[21].week } /></td>
	 <td ><textarea style="width:400px" name="command22" rows="1" id="search-text" >${crontablist[21].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute23" id="search-text" value=${crontablist[22].minute } /></td>
	 <td><input size="10" type="text" name="hour23" id="search-text" value=${crontablist[22].hour } /></td>
	 <td><input size="5" type="text" name="day23" id="search-text" value=${crontablist[22].day } /></td>
	 <td><input size="5" type="text" name="month23" id="search-text" value=${crontablist[22].month } /></td>
	 <td><input size="5" type="text" name="week23" id="search-text" value=${crontablist[22].week } /></td>
	 <td ><textarea style="width:400px" name="command23" rows="1" id="search-text" >${crontablist[22].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute24" id="search-text" value=${crontablist[23].minute } /></td>
	 <td><input size="10" type="text" name="hour24" id="search-text" value=${crontablist[23].hour } /></td>
	 <td><input size="5" type="text" name="day24" id="search-text" value=${crontablist[23].day } /></td>
	 <td><input size="5" type="text" name="month24" id="search-text" value=${crontablist[23].month } /></td>
	 <td><input size="5" type="text" name="week24" id="search-text" value=${crontablist[23].week } /></td>
	 <td ><textarea style="width:400px" name="command24" rows="1" id="search-text" >${crontablist[23].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute25" id="search-text" value=${crontablist[24].minute } /></td>
	 <td><input size="10" type="text" name="hour25" id="search-text" value=${crontablist[24].hour } /></td>
	 <td><input size="5" type="text" name="day25" id="search-text" value=${crontablist[24].day } /></td>
	 <td><input size="5" type="text" name="month25" id="search-text" value=${crontablist[24].month } /></td>
	 <td><input size="5" type="text" name="week25" id="search-text" value=${crontablist[24].week } /></td>
	 <td ><textarea style="width:400px" name="command25" rows="1" id="search-text" >${crontablist[24].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute26" id="search-text" value=${crontablist[25].minute } /></td>
	 <td><input size="10" type="text" name="hour26" id="search-text" value=${crontablist[25].hour } /></td>
	 <td><input size="5" type="text" name="day26" id="search-text" value=${crontablist[25].day } /></td>
	 <td><input size="5" type="text" name="month26" id="search-text" value=${crontablist[25].month } /></td>
	 <td><input size="5" type="text" name="week26" id="search-text" value=${crontablist[25].week } /></td>
	 <td ><textarea style="width:400px" name="command26" rows="1" id="search-text" >${crontablist[25].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute27" id="search-text" value=${crontablist[26].minute } /></td>
	 <td><input size="10" type="text" name="hour27" id="search-text" value=${crontablist[26].hour } /></td>
	 <td><input size="5" type="text" name="day27" id="search-text" value=${crontablist[26].day } /></td>
	 <td><input size="5" type="text" name="month27" id="search-text" value=${crontablist[26].month } /></td>
	 <td><input size="5" type="text" name="week27" id="search-text" value=${crontablist[26].week } /></td>
	 <td ><textarea style="width:400px" name="command27" rows="1" id="search-text" >${crontablist[26].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute28" id="search-text" value=${crontablist[27].minute } /></td>
	 <td><input size="10" type="text" name="hour28" id="search-text" value=${crontablist[27].hour } /></td>
	 <td><input size="5" type="text" name="day28" id="search-text" value=${crontablist[27].day } /></td>
	 <td><input size="5" type="text" name="month28" id="search-text" value=${crontablist[27].month } /></td>
	 <td><input size="5" type="text" name="week28" id="search-text" value=${crontablist[27].week } /></td>
	 <td ><textarea style="width:400px" name="command28" rows="1" id="search-text" >${crontablist[27].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute29" id="search-text" value=${crontablist[28].minute } /></td>
	 <td><input size="10" type="text" name="hour29" id="search-text" value=${crontablist[28].hour } /></td>
	 <td><input size="5" type="text" name="day29" id="search-text" value=${crontablist[28].day } /></td>
	 <td><input size="5" type="text" name="month29" id="search-text" value=${crontablist[28].month } /></td>
	 <td><input size="5" type="text" name="week29" id="search-text" value=${crontablist[28].week } /></td>
	 <td ><textarea style="width:400px" name="command29" rows="1" id="search-text" >${crontablist[28].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute30" id="search-text" value=${crontablist[29].minute } /></td>
	 <td><input size="10" type="text" name="hour30" id="search-text" value=${crontablist[29].hour } /></td>
	 <td><input size="5" type="text" name="day30" id="search-text" value=${crontablist[29].day } /></td>
	 <td><input size="5" type="text" name="month30" id="search-text" value=${crontablist[29].month } /></td>
	 <td><input size="5" type="text" name="week30" id="search-text" value=${crontablist[29].week } /></td>
	 <td ><textarea style="width:400px" name="command30" rows="1" id="search-text" >${crontablist[29].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute31" id="search-text" value=${crontablist[30].minute } /></td>
	 <td><input size="10" type="text" name="hour31" id="search-text" value=${crontablist[30].hour } /></td>
	 <td><input size="5" type="text" name="day31" id="search-text" value=${crontablist[30].day } /></td>
	 <td><input size="5" type="text" name="month31" id="search-text" value=${crontablist[30].month } /></td>
	 <td><input size="5" type="text" name="week31" id="search-text" value=${crontablist[30].week } /></td>
	 <td ><textarea style="width:400px" name="command31" rows="1" id="search-text" >${crontablist[30].command}</textarea></td>
</tr>

<tr onMouseOver="this.style.backgroundColor='#ffff66';" onMouseOut="this.style.backgroundColor='#d4e3e5';">

	 <td><input size="5" type="text" name="minute32" id="search-text" value=${crontablist[31].minute } /></td>
	 <td><input size="10" type="text" name="hour32" id="search-text" value=${crontablist[31].hour } /></td>
	 <td><input size="5" type="text" name="day32" id="search-text" value=${crontablist[31].day } /></td>
	 <td><input size="5" type="text" name="month32" id="search-text" value=${crontablist[31].month } /></td>
	 <td><input size="5" type="text" name="week32" id="search-text" value=${crontablist[31].week } /></td>
	 <td ><textarea style="width:400px" name="command32" rows="1" id="search-text" >${crontablist[31].command}</textarea></td>
</tr>

</table>
    <input type="submit" id="search-submit" value="保存" />
    <div id="mainlist_page"></div>
    <input type="hidden" id="currentpage" /> 
    <input type="hidden" id="totalpage" />							  
</form>
</div >		
		
		<div id="footer">
			<p></p>
		</div>
	</div>
</div>
	<script type="text/javascript">
		$(function(){
			 document.getElementById("currentpage").value='<%=request.getAttribute("currentpage")%>';
	         document.getElementById("totalpage").value='<%=request.getAttribute("totalpage")%>';
	      //   alert(parseInt($("#currentpage").val()));
		})
	</script>
<!-- end #footer -->
</body>
</html>