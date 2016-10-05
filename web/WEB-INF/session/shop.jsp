<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<!DOCtype html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 选择物品购买 </title>
	<meta name="website" content="http://www.sun.com" />
</head>
<body>
<form method="post" action="processBuy">
	书籍：<input type="checkbox" name="item" value="book"/><br/>
	电脑：<input type="checkbox" name="item" value="computer"/><br/>
	汽车：<input type="checkbox" name="item" value="car"/><br/>
	<input type="submit" value="购买"/>
</form>
</body>
</html>