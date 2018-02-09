<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page import="com.opensymphony.xwork2.*" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    var currentPage = parseInt("${currentPage}") + 1;     //当前页  从后台action中获取
    var countPerPage = parseInt("${countPerPage}");    //每页数据量
    var totalCount = parseInt("${totalCount}");        //总数据量
    var totalPage = parseInt((totalCount + countPerPage - 1) / countPerPage);  //总页数
    $(function () {
        $("#contentTypes").find("option[value='${contentTypes}']").attr("selected", "selected");
        $("#recommendedTypes").find("option[value='${recommendedTypes}']").attr("selected", "selected");
        $("#cycleFlag").find("option[value='${cycleFlag}']").attr("selected", "selected");

        if ($('#recommendedTypes').val() != 2) {
            $("#cycleFlag").val('');
            $("#cycleFlag").hide();
            $("#cycleFlagTd").hide();
        }

        $('#recommendedTypes').change(function () {
            var self = $(this);
            if (self.val() == 2) {
                $("#cycleFlag").show();
                $("#cycleFlagTd").show();
            } else {
                $("#cycleFlag").val('');
                $("#cycleFlag").hide();
                $("#cycleFlagTd").hide();
            }
        });


        var url = "<s:url value='/client-info-and-market-push-mng/index'/>" + '?';
        searchCondition = $("#search_recommended_marketing_push").searchCondToUrlParam();
        if (searchCondition != "") {
            url = url + $("#search_recommended_marketing_push").searchCondToUrlParam();
            +'&';
        }
        url = url + "&t=" + Math.random() + "&";
        //分页请求
        $("#clientMessageNavePage").navPage({
            divId: "clientMessageMarketingPushIndex",
            url: url + "indexOrderKey=${indexOrderKey}&indexOrder=${indexOrder}&",
            currentPage: currentPage,
            count: totalCount,
            totalPage: totalPage,
        });
        //初始化排序
        $.initOrderBy("${indexOrderKey}", "${indexOrder}", "<s:url value='/client-info-and-market-push-mng/index'/>"
            + '?' + $("#search_recommended_marketing_push").searchCondToUrlParam(),
            $("#clientMessageMarketingPushIndex"));
        //时间插件初始化
        $("#search_recommended_marketing_push input[name=createstarttime]").ligerDateEditor({showTime: true});
        $("#search_recommended_marketing_push input[name=createendtime]").ligerDateEditor({showTime: true});
        $(".l-box-dateeditor-time").css("display", "block");
        $(".l-text-wrapper").css("float", "left");
        $("#province_monthly_dialog .l-text").width("228px"),

            //初始化列表table添加可选择列的内容样式
            entityTableAddSelectColumn("clientMsgPush_all_innerTable"); //参数为entityTable的id
        //初始化列表可选列
        initSelectColumn("clientMsgPush_all_innerTable"); //参数为entityTable的id
        $("#import_form").validationEngine();
        //新增、修改弹窗
        $("#add_new_recommond_clientMessage").dialog({
            appendTo: "#" + $(".ui-tabs-selected").children("a").attr("idflag"),
            width: 760,
            autoOpen: false,
            resizable: false,
            modal: true
        });
        //组管理弹窗
        $("#group_menager_dialog").dialog({
            appendTo: "#" + $(".ui-tabs-selected").children("a").attr("idflag"),
            width: 760,
            autoOpen: false,
            resizable: false,
            modal: true
        });
        //初始化导入div
        $("#batch_import_dialog").dialog({
            appendTo: "#" + $(".ui-tabs-selected").children("a").attr("idflag"),
            width: 600,
            autoOpen: false,
            resizable: false,
            modal: true,
            buttons: {
                '确定': function () {
                    if ($("#import_form").validationEngine("validate")) {
                        $('#batch_import_dialog').dialog("close");
                        $("#import_form").ebAjaxFileForm({
                            //url:"<s:url value='/client-info-and-market-push-mng/batchImport'/>.json",
                            dataType: "json",
                            //type:"post",
                            loading: true,
                            success: function (data) {

                                Box.message({
                                    content: data.message,
                                    type: "warning",
                                    ok: function () {
                                        $("#batch_import_file").val("");
                                        select_clientMessage();
                                    }
                                });
                            }
                        });
                    }
                },
                '取消': function () {
                    $(this).dialog("close");
                }
            }
        });
        $("#batch_import_push_detail").dialog({
            width: 1000,
            appendTo: "#" + $(".ui-tabs-selected").children("a").attr("idflag"),
            autoOpen: false,
            resizable: false,
            modal: true,
            buttons: {
                "取消": function () {
                    $(this).dialog("close");
                }
            }
        });
        ebJspCommonInit({initDivId: $(".ui-tabs-selected").children("a").attr("idflag")});
    });

    //新增弹窗
    function add_newsCMMP_dialog_open() {
        var url = "<s:url value='/client-info-and-market-push-mng/addRecommended'/>";
        ajaxForward(url, "#add_new_recommond_clientMessage", function () {
            $("#add_new_recommond_clientMessage").dialog("open");
        });

    }

    //组管理弹窗
    function group_menager_dialog_open() {
        var auditStatus = '';
        $("#clientMsgPush_all_innerTable tr").each(function () {
            var chk = $(this).find("td").eq(0).find("input");
            if (chk.attr("checked") == "checked") {
                auditStatus = $(this).find("input[name=auditStatus]").val();
            }
        });
        if (auditStatus == '审核中') {
            Box.alert("审核中，不能进行组管理！");
            return;
        }

        $.get("<s:url value='/client-message-group-management/index'/>",
            function (data) {
                $("#group_menager_dialog").html(data);
                $("#group_menager_dialog").dialog("open");
                resizeDialogTableThWidth("GroupManagement-index-table");
            });
    }

    //批量导入
    function batch_import_dialog_open() {
        $("#batch_import_dialog").dialog('open');
    }

    function batch_import_error_log() {
        $.ebGet("<s:url value='/client-info-and-market-push-mng/getImportBatchList'/>", function (data) {
            $("#batch_import_push_detail").html(data);
            $("#batch_import_push_detail").dialog("open");

            resizeDialogTableThWidth("batch_import_push_detail_table");
        });

    }

    //条件查询实现
    function select_clientMessage() {
        var createstarttime = $("#search_recommended_marketing_push input[name=createstarttime]").val();
        var createendtime = $("#search_recommended_marketing_push input[name=createendtime]").val();
        if (createstarttime != '' && createendtime != '' && (createstarttime > createendtime)) {
            Box.alert("创建时间：开始时间不能在结束时间之后！");
            return;
        }

        var form = $("#search_recommended_marketing_push");
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

    //用于保存后的页面刷新
    function save_refresh_page() {
        var form = $("#search_recommended_marketing_push");
        var pageType = '${param.pageType}';
        $.ebAjax({
            loading: true,
            type: form.attr('method').toUpperCase(),
            url: form.attr('action'),
            data: {"pageType": pageType},
            traditional: true,
            async: true,
            success: function (data) {
                $("#clientMsgPush_" + pageType + "_table").html(data);
            }
        });
    }

    //修改实现
    function changeCMMP_dialog_open() {
        var selectedIds = $("#clientMsgPush_all_innerTable").find(".eb_selectAll").findCheckedStr();
        if (selectedIds.length != 1) {
            Box.alert("请选择一条数据！");
            return;
        }
        var status = '';
        var auditStatus = '';
        $("#clientMsgPush_all_innerTable tr").each(function () {
            var chk = $(this).find("td").eq(0).find("input");
            if (chk.attr("checked") == "checked") {
                status = $(this).find("input[name=status]").val();
                auditStatus = $(this).find("input[name=auditStatus]").val();
            }
        });
        if (status == '启用') {
            Box.alert("启用状态，不能修改！");
            return;
        }
        if (auditStatus == '审核中') {
            Box.alert("审核中，不能修改！");
            return;
        }
        var url = "<s:url value='/client-info-and-market-push-mng/editInforIndex'/>?recommendId=" + selectedIds;
        ajaxForward(url, "#add_new_recommond_clientMessage", function () {
            $("#add_new_recommond_clientMessage").dialog("open");
        });
    }

    //查看实现
    function add_new_recommond_clientMessage_open() {
        var selectedIds = $("#clientMsgPush_all_innerTable").find(".eb_selectAll").findCheckedStr();
        if (selectedIds.length != 1) {
            Box.alert("请选择一条数据！");
            return;
        }
        var url = "<s:url value='/client-info-and-market-push-mng/checkInformation'/>?recommendId=" + selectedIds;
        ajaxForward(url, "#add_new_recommond_clientMessage");
        $("#add_new_recommond_clientMessage").dialog("open");
    }

    //删除实现
    function deleteCMMP() {
        var selectedIds = $("#clientMsgPush_all_innerTable").find(".eb_selectAll").findCheckedStr();
        if (selectedIds.length != 1) {
            Box.alert("请选择一条数据！");
            return;
        }
        var status = '';
        var auditStatus = '';
        $("#clientMsgPush_all_innerTable tr").each(function () {
            var chk = $(this).find("td").eq(0).find("input");
            if (chk.attr("checked") == "checked") {
                status = $(this).find("input[name=status]").val();
                auditStatus = $(this).find("input[name=auditStatus]").val();
            }
        });
        if (status == '启用') {
            Box.alert("启用状态，不能删除！");
            return;
        }
        if (auditStatus == '审核中') {
            Box.alert("审核中，不能删除！");
            return;
        }
        Box.confirm(
            "是否删除?",
            function () {
                var url = "<s:url value='/client-info-and-market-push-mng/deleteRec.json'/>";
                $.ebAjax({
                    url: url,
                    type: "GET",
                    data: "recommendId=" + selectedIds,
                    dataType: "json",
                    success: function (data) {
                        if (data.info.status == 1) {
                            Box.message({
                                content: "删除成功",
                                type: "warning",
                                ok: function () {
                                    ajaxForward("<s:url value='/client-info-and-market-push-mng/index'/>", "#clientMsgPush_all_table");
                                }
                            });
                        } else
                            Box.alert("删除失败！请重试");
                    }
                });
            }, function () {
                return;
            });
    }

    //启用实现
    function enable_CMMP_change_state() {
        var selectedIds = $("#clientMsgPush_all_innerTable").find(".eb_selectAll").findCheckedStr();
        var url = "<s:url value='/client-info-and-market-push-mng/startRecommend.json'/>";
        var status = '';
        var auditStatus = '';
        //debugger;
        $("#clientMsgPush_all_innerTable tr").each(function () {
            var chk = $(this).find("td").eq(0).find("input");
            if (chk.attr("checked") == "checked") {
                status = $(this).find("input[name=status]").val();
                auditStatus = $(this).find("input[name=auditStatus]").val();
            }
        });
        if (status != '草稿' && status != '停用') {
            Box.alert("只有草稿与停用状态才可启用！");
            return;
        }
        if (auditStatus != '审核通过' && auditStatus != '') {
            Box.alert("审核通过才能启用！");
            return;
        }
        Box.confirm(
            "是否启用?",
            function () {
                $.ebAjax({
                    url: url,
                    type: "GET",
                    data: "recommendId=" + selectedIds,
                    dataType: "json",
                    success: function (data) {
                        if (data.info.status == '1') {
                            Box.alert("启用成功!");
                            ajaxForward("<s:url value='/client-info-and-market-push-mng/index'/>", "#clientMsgPush_all_table");
                        }
                    }
                });
            }, function () {
                return;
            }
        );
    }

    //停用实现
    function disable_CMMP_change_state() {
        var selectedIds = $("#clientMsgPush_all_innerTable").find(".eb_selectAll").findCheckedStr();
        var url = "<s:url value='/client-info-and-market-push-mng/stopRecommend.json'/>";
        var status = '';
        var auditStatus = '';
        //debugger;
        $("#clientMsgPush_all_innerTable tr").each(function () {
            var chk = $(this).find("td").eq(0).find("input");
            if (chk.attr("checked") == "checked") {
                status = $(this).find("input[name=status]").val();
                auditStatus = $(this).find("input[name=auditStatus]").val();
            }
        });
        if (status != '启用') {
            Box.alert("只有启用状态才可停用！");
            return;
        }
        if (auditStatus == '审核中') {
            Box.alert("审核中，不能停用！");
            return;
        }
        Box.confirm(
            "是否停用?",
            function () {
                $.ebAjax({
                    url: url,
                    type: "GET",
                    data: "recommendId=" + selectedIds,
                    dataType: "json",
                    success: function (data) {
                        if (data.info.status == '1') {
                            Box.alert("停用成功!");
                            ajaxForward("<s:url value='/client-info-and-market-push-mng/index'/>", "#clientMsgPush_all_table");
                        }
                    }
                });
            }, function () {
                return;
            }
        );
    }

    //下载模板
    function downloadTemplet() {
        var url = "<s:url value='/client-info-and-market-push-mng/downloadTemplet'/>";
        $.fileDownload(url);
    }

    //提交审核
    function clientMsg_submit_audit() {
        var selectedIds = $("#clientMsgPush_all_innerTable").find(".eb_selectAll").findCheckedStr();
        if (selectedIds.length != 1) {
            Box.alert("请选择一条数据！");
            return;
        }

        var status = '';
        var auditStatus = '';
        $("#clientMsgPush_all_innerTable tr").each(function () {
            var chk = $(this).find("td").eq(0).find("input");
            if (chk.attr("checked") == "checked") {
                status = $(this).find("input[name=status]").val();
                auditStatus = $(this).find("input[name=auditStatus]").val();
            }
        });
        if (status != '草稿' || auditStatus != '待提交') {
            Box.alert("只有草稿待提交状态才可以提交审核！");
            return;
        }

        var pageType = "all";
        $.ajax({
            type: "GET",
            url: "<s:url value='/client-info-and-market-push-mng'/>/submitAudit.json",
            data: {"recommendId": selectedIds[0], "pageType": pageType},
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
                $("#clientMsgPush_index .insideTab").eq("0").click();
            }
        });

    }

</script>

<div id="clientMessageMarketingPushIndex">
    <div class="search">
        <form action='<s:url value='/client-info-and-market-push-mng/innerTable'/>'
              id="search_recommended_marketing_push" method="post">
            <table>
                <tr>
                    <th>推荐标题</th>
                    <td><input id="recommendtitles" name="recommendtitles" value="${recommendtitles}"
                               class="search-input eb_searchCond"/></td>
                    <th>推荐类型</th>
                    <td>
                        <select id="recommendedTypes" name="recommendedTypes" class="search-select eb_searchCond">
                            <option value="0"> ---不限---</option>
                            <option value="1">书架营销位</option>
                            <option value="2">消息推送</option>
                            <option value="3">预置图书推送</option>
                        </select>
                    </td>
                    <th>内容类型</th>
                    <td>
                        <select id="contentTypes" name="contentTypes" class="search-select eb_searchCond">
                            <option value="0">---不限---</option>
                            <option value="1">图书</option>
                            <option value="2">活动</option>
                            <option value="3">书架</option>
                        </select>
                    </td>
                    <th></th>
                    <td>
                        <div class="table-search-and-reset">
                            <a class="btn-auto-size" onclick="javascript:select_clientMessage()">查询</a>
                            <a class="btn-auto-size"
                               onclick="javascript:cmu_formReset('search_recommended_marketing_push')">重置</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>创建时间</th>
                    <td colspan="3">
                        <input type="text" value="${createstarttime}" class="validate[required] search-input"
                               readonly="readonly" name="createstarttime"/>
                        <span class="between-time">~</span>
                        <input type="text" value="${createendtime}" class="validate[required] search-input"
                               readonly="readonly" name="createendtime"/>
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
                    <a class="btn-auto-size " href="javascript:add_newsCMMP_dialog_open();">新增</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:deleteCMMP();">删除</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:changeCMMP_dialog_open();">修改</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:enable_CMMP_change_state();">启用</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:disable_CMMP_change_state();">停用</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:add_new_recommond_clientMessage_open();">查看</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:group_menager_dialog_open();">组管理</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:batch_import_dialog_open();">批量导入</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:batch_import_error_log();">批量错误日志</a>
                </td>
                <td>
                    <a class="btn-auto-size " href="javascript:clientMsg_submit_audit();">提交审核</a>
                </td>
            </tr>
        </table>
    </div>


    <!-- 组管理弹窗 -->
    <div id="group_menager_dialog"></div>
    <!-- 新增弹窗 -->
    <div id="add_new_recommond_clientMessage"></div>
    <!-- 批量导入错误信息 -->
    <div id="batch_import_push_detail"></div>
    <!-- 批量导入 -->
    <div id="batch_import_dialog">
        <div class="dialog-header">批量导入</div>
        <form id="import_form" enctype="multipart/form-data"
              action='<s:url value='/client-info-and-market-push-mng/batchImport.json'/>' method="post">
            <table class="table-edit">
                <tr>
                    <td><span>请导入合适格式的excel表格</span></td>
                    <td><a href="javascript:downloadTemplet();" style="color:#2CADF1;">下载模板</a></td>
                </tr>
                <tr>
                    <td>
                        <input name="myfile" id="batch_import_file" type="file"
                               class="validate[required] dialog-upload ipt-e" style="float:left;"/>
                        <span class="required">*</span>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</div>