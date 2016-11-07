<%--
  Created by IntelliJ IDEA.
  User: Kevin
  Date: 2016/9/9
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/commons/commonlib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kevin`s Practice</title>
</head>
<script type="text/javascript">
    //生成产品周期配置sequence id
    var serial_maker = function () {
        var seq = 0;
        var count = 0;
        return {
            set_seq: function (s) {
                seq = s;
            },
            gen_seq: function () {
                var result = seq;
                seq += 1;
                count++;
                return result;
            },
            reduce: function () {
                count--;
            },
            getTotal: function () {
                return count;
            }
        };
    };
    var cycleSeq = serial_maker();
    cycleSeq.set_seq(0);

    //监听周期数变化
    function bind_num_change(id) {
        $("#div_cycleMonth").find("#" + id).find(".cycle-num-check").change(function () {
            var cycleArr = $("#div_cycleMonth").data("cycle-num");
            var oldCycleNum = $("#div_cycleMonth").data(id);
            if ($.inArray(this.value, cycleArr) >= 0) {
                if (this.value == 1) {
                    var count1 = 0;
                    $.each(cycleArr, function (n, value) {
                        if (value == 1) {
                            count1++;
                        }
                    });
                    if (count1 > 1) {
                        Box.message({
                            content: "周期数量为1的最多有2个!",
                            type: "warning"
                        });
                        this.value = '';
                        pass = false;
                    } else {
                        if (!isNaN(oldCycleNum)) {
                            cycleArr.splice(cycleArr.indexOf(oldCycleNum), 1);
                        }
                        $("#div_cycleMonth").data(id, this.value);
                        cycleArr.push(this.value);
                        $("#div_cycleMonth").find("#" + id).find("input[type=radio]").prop("disabled", false);
                        $("#div_cycleMonth").data("cycle-num", cycleArr);
                    }
                } else {
                    Box.message({
                        content: "周期数量>1的不能重复!",
                        type: "warning"
                    });
                    this.value = '';
                    pass = false;
                }
            } else {
                if (this.value == 1) {
                    $("#div_cycleMonth").find("#" + id).find("input[type=radio]").prop("disabled", false);
                } else {
                    $("#div_cycleMonth").find("#" + id).find("input[type=radio]").prop("disabled", true);
                }
                if (!isNaN(oldCycleNum)) {
                    cycleArr.splice(cycleArr.indexOf(oldCycleNum), 1);
                }
                $("#div_cycleMonth").data(id, this.value);
                cycleArr.push(this.value);
                $("#div_cycleMonth").data("cycle-num", cycleArr);
            }
        });
    }
    $(function () {
        //alert("123");
        //初始化周期数数组存储
        $("#div_cycleMonth").data("cycle-num", new Array());
        //初始化周期产品配置信息列表
        var productCycleArrJson = ${productCycleArrJson};
        if (productCycleArrJson != null && productCycleArrJson != '') {
            var cycleJsonData = eval(productCycleArrJson);
            var cycleArr = $("#div_cycleMonth").data("cycle-num");
            for (var i = 0; i < cycleJsonData.length; i++) {
                var seq = cycleSeq.gen_seq();
                $("#div_cycleMonth").append($("<div id='" + "cycle" + seq + "'>" + $("#product_cycle_config_update").html() + "</div>"));
                $("#cycle" + seq).find("input[name=cycleCheckBox]").val("cycle" + seq);
                $("#cycle" + seq).find("input[name=cycleNum]").val(cycleJsonData[i].cycleNum);
                $("#cycle" + seq).find("input[name=fee]").val(cycleJsonData[i].fee);
                $("#cycle" + seq).find("input[name=autoRenewalFlag]").val(cycleJsonData[i].autoRenewalFlag);
                if (cycleJsonData[i].autoRenewalFlag == 1) {
                    $("#cycle" + seq).find("input[name=autoRenewalFlag]").prop("checked", true);
                }
                $("#cycle" + seq).find("input[name=clientPrompt]").val(cycleJsonData[i].clientPrompt);
                $("#cycle" + seq).find("input[name=appStoreProductId]").val(cycleJsonData[i].appStoreProductId);
                $("#cycle" + seq).find("input[name=appStoreApplicationId]").val(cycleJsonData[i].appStoreApplicationId);
                cycleArr.push(cycleJsonData[i].cycleNum);
                $("#div_cycleMonth").data("cycle" + seq, cycleJsonData[i].cycleNum);
                if (cycleJsonData[i].cycleNum > 1) {
                    $("#cycle" + seq).find("input[type=radio]").prop("disabled", true);
                }
                bind_num_change("cycle" + seq);
            }
            $("#div_cycleMonth").data("cycle-num", cycleArr);
        }
        $("#div_cycleMonth").data("oldProductCycleArrJson", productCycleArrJson);
    });

    function create_cycle_update() {
        if (cycleSeq.getTotal() >= 20) {
            Box.message({
                content: "只能一次创建20条记录!",
                type: "warning"
            });
        } else {
            var seq = cycleSeq.gen_seq();
            $("#div_cycleMonth").append($("<div id='" + "cycle" + seq + "'>" + $("#product_cycle_config_update").html() + "</div>"));
            $("#cycle" + seq).find("input[name=cycleCheckBox]").val("cycle" + seq);
            bind_num_change("cycle" + seq);
        }
    }
    function delete_cycle_update() {
        var selectedIds = $("#div_cycleMonth input[name=cycleCheckBox]").findCheckedStr();
        $.each(selectedIds, function (i, value) {
            //删除已存周期数
            var cycleArr = $("#div_cycleMonth").data("cycle-num");
            var currNum = $("#" + value).find("input[name='productCycleList.cycleNum']").val();
            cycleArr.splice(cycleArr.indexOf(currNum), 1);
            $("#div_cycleMonth").data("cycle-num", cycleArr);
            //删除选中节点
            $("#" + value).remove();
            cycleSeq.reduce();
        });
    }

</script>
<body>
modify 123
</body>
</html>

<div id="product_cycle_config_update" style="display:none">
    <table class="table-edit" style="margin:0 auto">
        <tr>
            <td>
                <label>创建</label><input type="text"
                                        class="validate[required,custom[integer],min[1],maxSize[3]] ipt-e-short cycle-num-check"
                                        name="cycleNum"/><label>个周期</label>
            </td>
            <td>
                <input type="checkbox" name="cycleCheckBox" style="float:right;"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <label>产品价格</label><input type="text" class="validate[required,custom[integer],min[0],maxSize[6]] ipt-e"
                                          name="fee"/><label>元</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="radio" class="table-radio" name="autoRenewalFlag"
                       onclick="if(this.c==0){this.c=1;this.checked=true;this.value=1;}else{this.c=0;this.checked=false;this.value=0;};"
                       c="0" value="0"/><label>用户可选自动续订</label>
            </td>
            <td>
                <label>优惠提示语</label>
                <input type="text" class="validate[maxSize[100]] ipt-e" name="clientPrompt"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="checkbox" class="edit-checkbox" name="supportPayTypes" value="4" checked="checked"
                       disabled="disabled"/>appstore支付
            </td>
        </tr>
        <tr>
            <th>appstore产品ID</th>
            <td>
                <input type="text" class="validate[required,maxSize[100]] ipt-e"
                       name="appStoreProductId"/>
                <span class="required">*</span>
            </td>
        </tr>
        <tr>
            <th>appstore应用ID</th>
            <td>
                <input type="text" class="validate[required,maxSize[100]] ipt-e"
                       name="appStoreApplicationId"/>
                <span class="required">*</span>
            </td>
        </tr>
    </table>
</div>

