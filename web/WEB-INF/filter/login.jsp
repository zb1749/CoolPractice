<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>登录页面</title>
	<meta name="website" content="http://www.crazyit.org" />
</head>
<body>
<h2>登录页面</h2>
<%
if(request.getAttribute("tip") != null)
{
	out.println("<font color='red'>" 
		+ request.getAttribute("tip")
		+ "</font>");
}
%>
<form method="post" action="proLogin.jsp">
用户名:<input type="text" name="name"/><br/>
<input type="submit" value="登录"/>
</form>
</body>
</html>