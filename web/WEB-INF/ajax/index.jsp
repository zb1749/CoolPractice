<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 2016/11/3
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/commons/commonlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json array test</title>
</head>
<script type="text/javascript">
    $(function () {
        //新增窗口
        $("#productInfoAddDialog").dialog({
            width: 700,
            appendTo: "#thisIndex",
            autoOpen: false,
            resizable: false,
            modal: true,
            buttons: {
                '确定': function () {

                }
            }
        });
    });


    /* 点击产品新增按钮  */
    function area_product_add_dialog(){
        $("#productInfoUpdateDialog").empty();
        $("#productInfoCheckDialog").empty();
        $.get("<s:url value='/ajax/jsonArr_addDialog.action'/>",{},
                function(data){
                    $("#productInfoAddDialog").html(data);
                    $("#productInfoAddDialog").dialog("open");
                }
        );

    }
</script>
<body>

周期json数组测试
<div id="thisIndex"></div>
<a class="btn-auto-size" onclick="javascript:area_product_add_dialog()">新增</a>
</body>
</html>
<!-- 新增-->
<div id="productInfoAddDialog"></div>