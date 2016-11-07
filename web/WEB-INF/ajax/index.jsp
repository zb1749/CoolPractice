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
    $(function () {
        //新增窗口
        $("#infoAddDialog").dialog({
            width: 700,
            //appendTo: "#thisIndex",
            autoOpen: false,
            resizable: false,
            modal: true,
            buttons: {
                '确定': function () {
                    //debugger;
                    add_cycle_submit();
                    $(this).dialog("close");
                }
            }
        });

        $("#infoModifyDialog").dialog({
            width: 700,
            autoOpen: false,
            resizable: false,
            closeOnEscape: false,//按esc退出默认的true
            draggable: true, //拖拽默认是true
            show:"slide",//打开窗口的效果
            hide: "toggle",//关闭窗口的效果
            modal: true, //遮罩效果默认是false不遮住
            buttons: {
                '确定': function () {
                    $(this).dialog("close");
                }
            }
        });
    });


    /* 点击新增按钮  */
    function add_dialog() {
        //$("#infoUpdateDialog").empty();
        $.get("<s:url value='/ajax/jsonArr_addDialog.action'/>", {},
                function (data) {
                    //debugger;
                    $("#infoAddDialog").html(data);
                    $("#infoAddDialog").dialog("open");
                }
        );
    }

    function modify_dialog() {
        $("#infoModifyDialog").dialog("open");
    }

    function add_cycle_submit() {
        //debugger;
        var message = "[";
        $.each($("#div_cycleMonth").find("input[name=cycleCheckBox]"), function () {
            var id = $(this).val();
            message = message + "{cycleNum:'" + $("#" + id).find("input[name=cycleNum]").val() + "',fee:'" + $("#" + id).find("input[name=fee]").val() + "',supportPayTypes:'" + $("#" + id).find("input[name=supportPayTypes]").val()
                    + "',clientPrompt:'" + $("#" + id).find("input[name=clientPrompt]").val() + "',autoRenewalFlag:'" + ($("#" + id).find("input[name=autoRenewalFlag]").prop("checked")?"1":"0")
                    + "',appStoreProductId:'" + $("#" + id).find("input[name=appStoreProductId]").val() + "',appStoreApplicationId:'" + $("#" + id).find("input[name=appStoreApplicationId]").val() + "'},";
        });
        if(message.length>1){
            message = message.substring(0, message.length - 1) + "]";
        }else{
            message = message + "]";
        }
        //debugger;
        //提交
        $.ajax({
            url: "<s:url value='/ajax/jsonArrTransArr.action'/>?ajaxArrJson=" + message + "&productInfoHeadPojo.appStorePay=4",
            type: "GET",
            loading: false,
            data: $("#productApply_apply").serialize(),
            dataType: "json",
            success : function(reData){
//                alert("returned");
                var jsonRel = eval(reData);
                alert(jsonRel.toString());
                Box.message({
                    content:"json array trans success!",
                    type:"warning"
                });
            }
        });
    }
</script>
<body>

周期json数组测试
<div id="thisIndex"></div>
<a class="btn-auto-size" onclick="javascript:add_dialog()">新增</a>
<a class="btn-auto-size" onclick="javascript:modify_dialog()">修改</a>
<!-- 新增-->
<div id="infoAddDialog"></div>
<div id="infoModifyDialog" title="Basic dialog">
    <p>这是一个动画显示的对话框，用于显示信息。对话框窗口可以移动，调整尺寸，默认可通过 'x' 图标关闭。</p>
</div>
</body>
</html>
