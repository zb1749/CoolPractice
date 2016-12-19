<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 2016/11/3
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/commonlib.jsp" %>

<html>
<head>
    <title>json array test</title>
</head>
<script type="text/javascript">
    var count = parseInt("20");//从后台action中获取总记录数
    var currentPage = parseInt("0") + 1;
    var countPerPage = parseInt("10");
    var totalPage = parseInt((count + countPerPage - 1) / countPerPage);
    $(function () {
        var url = '<s:url value="/group-manage/index"/>'+'?'
                + 'orgName=${orgName}' + '&'
                + 'categoryName=${categoryName}' + '&'
                + 'orgRemark=${orgRemark}' + '&'
                + 'indexOrderKey=${indexOrderKey}' + '&'
                + 'indexOrder=${indexOrder}' + '&';
        //分页
        $("#group_manage_navPage").navPage({
            url : url,
            count:count,
            currentPage : currentPage,
            totalPage : totalPage
        });
    });
</script>
<body>

周期json数组测试
<div id="thisIndex"></div>
<a class="btn-auto-size" onclick="javascript:add_dialog()">新增</a>
<a class="btn-auto-size" onclick="javascript:modify_dialog()">修改</a>

<br/>
<br/>
<br/>
<!-- 分页控制区 -->
<div id="group_manage_navPage"></div>

</body>
</html>
