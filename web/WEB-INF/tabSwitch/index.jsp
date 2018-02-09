<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript">
    $(function () {
        //添加点击效果
        $("#clientMsgPush_index .insideTab").first().click();

        //异步加载所有tab页
        clientMsgPush_pageLoad("ALLT");
        ebJspCommonInit({initDivId: $(".ui-tabs-selected").children("a").attr("idflag")});
    });

    //加载页面数据列表  which : 区别按钮类型
    function clientMsgPush_pageLoad(which) {
        var dataArray = null;

        //草稿
        if (which == 'ALLT' || which == 'all') {

            dataArray = new Array();
            dataArray.push({name: "pageType", value: "all"});
            $.ebPost("<s:url value='/client-info-and-market-push-mng/innerTable'/>", dataArray, function (data) {
                $("#clientMsgPush_all_table").html(data);
                resizeDialogTableThWidth("clientMsgPush_all_innerTable");
            });

            ebJspCommonInit({initDivId: $(".ui-tabs-selected").children("a").attr("idflag")});
        }

        //正式
        if (which == 'ALLT' || which == 'audit') {
            dataArray = new Array();
            dataArray.push({name: "pageType", value: "audit"});
            $.ebPost("<s:url value='/client-info-and-market-push-mng/innerTable'/>", dataArray, function (data) {
                $("#clientMsgPush_audit_table").html(data);
                resizeDialogTableThWidth("clientMsgPush_audit_innerTable");
            });

        }
    }
</script>

<div id="clientMsgPush_index" class="tab-panel-small">
    <!-- 草稿-->
    <div id="clientMsgPush_all" class="tab-panel-small">
        <s:include value="allTab.jsp">
            <s:param name="pageType">all</s:param>
        </s:include>
        <div id="clientMsgPush_all_table">
            <!-- 待载入 innerTable table -->
        </div>
    </div>
    <!-- 正式 -->
    <div id="clientMsgPush_audit" class="tab-panel-small">
        <s:include value="auditTab.jsp">
            <s:param name="pageType">audit</s:param>
        </s:include>
        <div id="clientMsgPush_audit_table">
            <!-- 待载入 innerTable table -->
        </div>
    </div>
    <div class="inside-tab-top">
        <ul class="insideTabs">
            <li><a class="insideTab" onclick="javascript:tabSwitch(this,'clientMsgPush_','all')">全部</a></li>
            <li><a class="insideTab" onclick="javascript:tabSwitch(this,'clientMsgPush_','audit')">审核</a></li>
        </ul>
    </div>
</div>