<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/WriteConfigFileServlet" method="post">
		<textarea style="width: 600px" name="config" rows="20" id="search-text"><c:forEach var="configList" items="${configList }">${configList}</c:forEach></textarea>			
		<input type="submit" id="search-submit" value="保存" />
	</form>	
</body>
</html>