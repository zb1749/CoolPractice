<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<script type="text/javascript">
    var count = parseInt("${count}");//从后台action中获取总记录数
    var currentPage = parseInt("${currentPage}") + 1;
    var countPerPage = parseInt("${countPerPage}");
    var totalPage = parseInt((count + countPerPage - 1) / countPerPage);

    $(function () {

        ebJspCommonInit({initDivId: $(".ui-tabs-selected").children("a").attr("idflag")});

        //时间插件初始化
        $("#audit_search_recommended_marketing_push_form input[name=createstarttime]").ligerDateEditor({showTime: true});
        $("#audit_search_recommended_marketing_push_form input[name=createendtime]").ligerDateEditor({showTime: true});
        $(".l-box-dateeditor-time").css("display", "block");
        $(".l-text-wrapper").css("float", "left");
    });

    //查询
    function audit_search_client_msg() {
        var createstarttime = $("#audit_search_recommended_marketing_push_form input[name=createstarttime]").val();
        var createendtime = $("#audit_search_recommended_marketing_push_form input[name=createendtime]").val();
        if (createstarttime != '' && createendtime != '' && (createstarttime > createendtime)) {
            Box.alert("创建时间：开始时间不能在结束时间之后！");
            return;
        }

        var form = $("#audit_search_recommended_marketing_push_form");
        var pageType = '${param.pageType}';
        $.ebAjax({
            loading: true,
            type: form.attr('method').toUpperCase(),
            url: form.attr('action'),
            data: form.serialize() + "&pageType=" + pageType,
            traditional: true,
            async: true,
            success: function (data) {
                $("#clientMsgPush_" + pageType + "_table").html(data);
            }
        });
    }

    //查看
    function audit_view_recommond_client_message() {
        var selectedIds = $("#clientMsgPush_audit_innerTable").find(".eb_selectAll").findCheckedStr();
        if (selectedIds.length != 1) {
            Box.alert("请选择一条数据！");
            return;
        }

        var url = "<s:url value='/client-info-and-market-push-mng/checkInformation'/>?recommendId=" + selectedIds;
        $.ajax({
            url: url,
            type: "GET",
            async: false,
            loading: true,
            success: function (data) {
                $("#add_new_recommond_clientMessage").html(data);
                $("#add_new_recommond_clientMessage").dialog("open");
            }
        });
    }

    //审核
    function clientMsg_do_audit(auditStatus) {
        var selectedIds = $("#clientMsgPush_audit_innerTable").find(".eb_selectAll").findCheckedStr();
        if (selectedIds.length != 1) {
            Box.alert("请选择一条数据！");
            return;
        }
        var pageType = "audit";
        $.ajax({
            type: "GET",
            url: "<s:url value='/client-info-and-market-push-mng'/>/doAudit.json",
            data: {"recommendId": selectedIds[0], "pageType": pageType, "auditStatus": auditStatus},
            dataType: "json",
            success: function (data) {
                if (data.success == 0) {
                    Box.message({
                        content: data.message,
                        type: "warning"
                    });
                    return;
                } else {
                    Box.message({
                        content: data.message,
                        type: "success"
                    });
                    return;
                }
            },
            complete: function () {
                clientMsgPush_pageLoad("ALLT");
                $("#clientMsgPush_index .insideTab").eq("1").click();
            }
        });

    }
</script>

<!-- 搜索条件区 -->
<div class="search">
    <form action='<s:url value='/client-info-and-market-push-mng/innerTable'/>'
          id="audit_search_recommended_marketing_push_form" method="post">
        <table>
            <tr>
                <th>推荐标题</th>
                <td><input name="recommendtitles" value="${recommendtitles}" class="search-input eb_searchCond"/></td>
                <th>推荐类型</th>
                <td>
                    <select name="recommendedTypes" class="search-select eb_searchCond">
                        <option value="0"> ---不限---</option>
                        <option value="1">书架营销位</option>
                        <option value="2">消息推送</option>
                        <option value="3">预置图书推送</option>
                    </select>
                </td>
                <th>内容类型</th>
                <td>
                    <select name="contentTypes" class="search-select eb_searchCond">
                        <option value="0">---不限---</option>
                        <option value="1">图书</option>
                        <option value="2">活动</option>
                        <option value="3">书架</option>
                    </select>
                </td>
                <th></th>
                <td>
                    <div class="table-search-and-reset">
                        <a class="btn-auto-size" onclick="javascript:audit_search_client_msg()">查询</a>
                        <a class="btn-auto-size"
                           onclick="javascript:cmu_formReset('audit_search_recommended_marketing_push_form')">重置</a>
                    </div>
                </td>
            </tr>
            <tr>
                <th>创建时间</th>
                <td colspan="3">
                    <input type="text" name="createstarttime" value="${createstarttime}"
                           class="validate[required] search-input" readonly="readonly"/>
                    <span class="between-time">~</span>
                    <input type="text" name="createendtime" value="${createendtime}"
                           class="validate[required] search-input" readonly="readonly"/>
                </td>
                <th id="cycleFlagTd">是否循环</th>
                <td>
                    <select id="cycleFlag" name="cycleFlag" class="search-select eb_searchCond">
                        <option value="">---不限---</option>
                        <option value="0">否</option>
                        <option value="1">每天</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<div class="button-left button-container button-out">
    <table>
        <tr>
            <td>
                <a class="btn-auto-size" onclick="javascript:audit_view_recommond_client_message()">查看</a>
            </td>
            <td>
                <a class="btn-auto-size" onclick="javascript:clientMsg_do_audit('2')">通过</a>
            </td>
            <td>
                <a class="btn-auto-size" onclick="javascript:clientMsg_do_audit('3')">驳回</a>
            </td>
        </tr>
    </table>
</div>
