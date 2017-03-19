<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 增加Cookie </title>
	<meta name="website" content="http://www.sun.com" />
</head>
<body>
<%
//http://localhost:8080/cookie/addCookie?name=wma
//Cookie对象在servlet jar包中定义
// 获取请求参数
String name = request.getParameter("name");
// 以获取到的请求参数为值，创建一个Cookie对象
Cookie c = new Cookie("username" , name);
// 设置Cookie对象的生存期限
c.setMaxAge(24 * 3600);//为什么设置了生存周期，然而浏览器上的cookie过期时间还是会话呢？
// 向客户端增加Cookie对象
response.addCookie(c);
%>
</body>
</html>