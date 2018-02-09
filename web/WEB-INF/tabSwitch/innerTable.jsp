<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
    var totalCount = parseInt("${totalCount}");
    var currentPage = parseInt("${currentPage}") + 1;
    var countPerPage = parseInt("${countPerPage}");
    var totalPage = parseInt((totalCount + countPerPage - 1) / countPerPage);

    $(function () {
        var url = '<s:url value="/client-info-and-market-push-mng/innerTable"/>';

        var orderArray = new Array();
        orderArray.push({name: "indexOrderKey", value: "${indexOrderKey}"});
        orderArray.push({name: "indexOrder", value: "${indexOrder}"});

        $("#clientMsgPush_${pageType}_navPage").navPage({
            divId: 'clientMsgPush_${param.pageType}_table',
            type: 'POST',
            action: url,
            count: totalCount,
            formData: initSearchData().concat(orderArray),
            currentPage: currentPage,
            totalPage: totalPage
        });

        //点击列头排序
        $.initOrderByPost({
            position: '#clientMsgPush_${param.pageType}_table',
            action: "<s:url value='/client-info-and-market-push-mng/innerTable'/>",
            data: initSearchData(),
            indexOrderKey: "${indexOrderKey}",
            indexOrder: "${indexOrder}"
        });

        //初始化列表table添加可选择列的内容样式
        entityTableAddSelectColumn("clientMsgPush_${param.pageType}_innerTable"); //参数为entityTable的id
        //初始化列表可选列
        initSelectColumn("clientMsgPush_${param.pageType}_innerTable"); //参数为entityTable的id

        ebJspCommonInit({initDivId: $(".ui-tabs-selected").children("a").attr("idflag")});

        //设置table高度
        $("#clientMsgPush_all_innerTable").parent(".wrapper_entityTable").height(document.body.offsetHeight - 405);
        $("#clientMsgPush_audit_innerTable").parent(".wrapper_entityTable").height(document.body.offsetHeight - 405);
    });

    function initSearchData() {
        var dataArray = new Array();
        var formArray = new Array();
        dataArray.push({name: "pageType", value: "${pageType}"});

        var formId = "clientMsgPush_${param.pageType}_searchForm";
        formArray = $("#" + formId).serializeArray();

        return dataArray.concat(formArray);
    }

</script>

<!-- 显示表单 -->
<div class="main-unit">
    <table id="clientMsgPush_${param.pageType}_innerTable"
           class="table-complex table-imgbtn">
        <thead>
        <tr>
            <!-- tab1 thead 全部 -->
            <c:if test="${param.pageType =='all'}">
                <th class="th-no-sorting th-checkbox"><input type="checkbox" id="clientMsgPush_${param.pageType}_select"
                                                             class="eb_headSelectAll"></th>
                <th class="eb_orderBy" orderKey="recommendId" order="ASC">推荐Id</th>
				<th class="eb_orderBy" orderKey="title" order="ASC">推荐标题</th>
				<th class="eb_orderBy" orderKey="recommendType" order="ASC">推荐类型</th>
				<th class="eb_orderBy" orderKey="recContent" order="ASC">推荐语</th>
				<th class="eb_orderBy" orderKey="address" order="ASC">推荐地址</th>
				<th class="eb_orderBy" orderKey="contentType" order="ASC">内容类型</th>
				<th class="eb_orderBy" orderKey="createTime" order="ASC">创建时间</th>
				<th class="eb_orderBy" orderKey="status" order="ASC">状态</th>
				<th class="eb_orderBy" orderKey="auditStatus" order="ASC">审核状态</th>
				<th class="eb_orderBy" orderKey="cycleFlag" order="ASC">是否循环</th>
            </c:if>

            <!-- tab2 thead 审核 -->
            <c:if test="${param.pageType =='audit'}">
                <th class="th-no-sorting th-checkbox"><input type="checkbox" id="clientMsgPush_${param.pageType}_select"
                                                             class="eb_headSelectAll"></th>
                <th class="eb_orderBy" orderKey="recommendId" order="ASC">推荐Id</th>
				<th class="eb_orderBy" orderKey="title" order="ASC">推荐标题</th>
				<th class="eb_orderBy" orderKey="recommendType" order="ASC">推荐类型</th>
				<th class="eb_orderBy" orderKey="recContent" order="ASC">推荐语</th>
				<th class="eb_orderBy" orderKey="address" order="ASC">推荐地址</th>
				<th class="eb_orderBy" orderKey="contentType" order="ASC">内容类型</th>
				<th class="eb_orderBy" orderKey="createTime" order="ASC">创建时间</th>
				<th class="eb_orderBy" orderKey="status" order="ASC">状态</th>
				<th class="eb_orderBy" orderKey="auditStatus" order="ASC">审核状态</th>
				<th class="eb_orderBy" orderKey="cycleFlag" order="ASC">是否循环</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <!-- tab1 tbody 全部 -->
        <c:if test="${param.pageType =='all'}">
            <s:iterator value="information" status="st">
            <s:if test="#st.odd">
            <tr class='odd'>
            </s:if>
            <s:else>
            <tr class='even'>
            </s:else>
                <td>
					<input type="checkbox" class="eb_selectAll" value='<s:property value="recommendId"/>'/>
					<input type="hidden" name="status" value='<s:property value="status" />'/>
					<input type="hidden" name="auditStatus" value='<s:property value="auditStatus" />'/>
				</td>
				<td><s:property value="recommendId" /></td>
				<td><s:property value="title" /></td>
				<td><s:property value="recommendType" /></td>
				<td><s:property value="recContent" /></td>
				<td><s:property value="address" /></td>
				<td><s:property value="contentType" /></td>
				<td><s:property value="createTime" /></td>
				<td><s:property value="status" /></td>
				<td><s:property value="auditStatus" /></td>
				<td>
					<s:if test="cycleFlag == 1">每天</s:if>
					<s:if test="cycleFlag == 0">否</s:if>
				</td>
            </s:iterator>
        </c:if>
            <!-- tab2 tbody 审核-->
        <c:if test="${param.pageType =='audit'}">
            <s:iterator value="auditInformation" status="st">
            <s:if test="#st.odd">
            <tr class='odd'>
            </s:if>
            <s:else>
            <tr class='even'>
            </s:else>
                <td>
					<input type="checkbox" class="eb_selectAll" value='<s:property value="recommendId"/>'/>
				</td>
				<td><s:property value="recommendId" /></td>
				<td><s:property value="title" /></td>
				<td><s:property value="recommendType" /></td>
				<td><s:property value="recContent" /></td>
				<td><s:property value="address" /></td>
				<td><s:property value="contentType" /></td>
				<td><s:property value="createTime" /></td>
				<td><s:property value="status" /></td>
				<td><s:property value="auditStatus" /></td>
				<td>
					<s:if test="cycleFlag == 1">每天</s:if>
					<s:if test="cycleFlag == 0">否</s:if>
				</td>
            </s:iterator>
        </c:if>
        </tbody>
    </table>
</div>
<!-- 分页实现 -->
<div id="clientMsgPush_${pageType}_navPage"></div>