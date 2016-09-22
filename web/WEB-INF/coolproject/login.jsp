<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 2016/9/22
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/commons/commonlib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Kevin`s Home !</title>
</head>
<script type="text/javascript">
    var arr = [1,1,2,3,4,5];
    document.writeln("arr init: "+arr.toString());
    document.writeln("</br>");
    document.writeln("2 index in arr: "+$.inArray(2,arr));
    document.writeln("</br>");
    arr.splice($.inArray(2,arr),1);
    document.writeln("arr del result: "+arr.toString());
    document.writeln("</br>");
    document.writeln("4 index in arr: "+arr.indexOf(4));
</script>
<body>

</body>
</html>
