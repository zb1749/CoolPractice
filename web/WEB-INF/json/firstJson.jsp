<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/commons/commonlib.jsp" %>
<head>
    <title>使用JSON插件</title>
    <script type="text/javascript">
        function gotClick() {
            $("#show").hide();
            // 指定向JSONExample发送请求，将id为form1的表单所包含的表单控件转换为请求参数
            $.post("JSONExample",
                    $("#form1").serializeArray(),
                    // 指定回调函数
                    function (data, statusText) {
                        $("#show").height(80)
                                .width(240)
                                .css("border", "1px solid black")
                                .css("border-radius", "15px")
                                .css("background-color", "#efef99")
                                .css("color", "#ff0000")
                                .css("padding", "20px")
                                .empty();
                        // 遍历JavaScript对象的各属性
                        for (var propName in data) {
                            $("#show").append(propName + "-->"
                                    + data[propName] + "<br/>");
                        }
                        $("#show").show(600);
                    },
                    // 指定服务器响应为JSON数据
                    "json");
        }
    </script>
</head>
<body>
<s:form id="form1">
    <s:textfield name="field1" label="Field 1"/>
    <s:textfield name="field2" label="Field 2"/>
    <s:textfield name="field3" label="Field 3"/>
    <tr>
        <td colspan="2">
            <input type="button" value="提交" onclick="gotClick();"/>
        </td>
    </tr>
</s:form>
<div id="show">
</div>
</body>
</html>
