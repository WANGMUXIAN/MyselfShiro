<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
</head>
<body>
	<shiro:hasAnyRoles name="wmx">
       			您无admin权限
	</shiro:hasAnyRoles>
	<shiro:hasAnyRoles name="admin">
       			您无wmx权限
	</shiro:hasAnyRoles>
</body>
</html>