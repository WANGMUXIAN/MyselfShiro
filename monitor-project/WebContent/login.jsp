<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	</head>
	<body>
		<div>
			<div class="content">
				<div class="title hide">管理登录</div>
				<form name="login" action="login" method="post">
					<fieldset>
						<div class="input">
							<input class="input_all name" name="username" id="username" placeholder="用户名" type="text" onFocus="this.className='input_all name_now';" onBlur="this.className='input_all name'" maxLength="24" />
						</div>
						<div class="input">
							<input class="input_all password" name="password" id="password" type="password" placeholder="密码" onFocus="this.className='input_all password_now';" onBlur="this.className='input_all password'" maxLength="24" />
						</div>
						<div class="checkbox">
							<!-- <input type="checkbox" name="remember" id="remember" />
							<label for="remember">
								<span>记住密码</span>
							</label> -->
							<span style="margin-left: 40px;color: #FF0000">${error}</span>
						</div>
						
						<div class="enter">
							<input class="button hide" name="submit" type="submit" value="登录" />
						</div>
					
					</fieldset>
				</form>
	
			</div>
		</div>
	</body>
</html>