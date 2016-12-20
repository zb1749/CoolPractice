/**
 * Created by Kevin on 2016/12/20.
 */
/**
 * 列表排序工具
 * 需要在列表字段元素上添加属性 class="eb_orderBy" orderKey="排序字段值" order="ASC（升序）DESC（降序）" th中input标签需加样式"inputCheckbox"
 * @param indexOrderKey 默认排序字段
 * @param indexOrder 默认排序顺序
 * @param url 请求列表地址
 * @param position 刷新目标元素对象，如果不填默认刷新当前tab的div
 */
// 初始化列表字段点击事件，处理排序
$.initOrderBy = function(indexOrderKey, indexOrder, url, position) {
    var tabDiv = $("#" + $(".ui-tabs-selected").children("a").attr("idflag"));

    if(position != undefined) {
        tabDiv = $(position) ;
    }

    $(tabDiv).find("[orderKey='"+ indexOrderKey +"']").addClass("in_orderBy_" + indexOrder);

    $(tabDiv).find(".in_orderBy").click(function(){
        var order = $(this).attr("order");
        var orderKey = $(this).attr("orderKey");

        var ajaxUrl = url + ((url.charAt(url.length-1)=="?")==true ? "": "&") + "indexOrderKey=" + orderKey + "&indexOrder=" + order;
        ajaxForward(ajaxUrl, position);
    });

    if(indexOrder == "DESC") {
        $(tabDiv).find("th[orderKey='" + indexOrderKey+ "']").attr("order", "ASC")
            .removeClass("in_orderBy_DESC sorting_desc")
            .addClass("in_orderBy_ASC sorting_asc").siblings().not(".th-no-sorting").addClass("in_orderBy");
    } else {
        $(tabDiv).find("th[orderKey='" + indexOrderKey + "']").attr("order", "DESC")
            .removeClass("in_orderBy_ASC sorting_asc")
            .addClass("in_orderBy_DESC sorting_desc").siblings().not(".th-no-sorting").addClass("in_orderBy");
    }
};


/**
 * ajax跳转页面方法
 * 该方法根据url跳转，获取响应页面，并且加载到当前展示的tab页
 * @param url
 * @param position 刷新目标元素，不填为当前tab的div
 * @param func 执行成功后的回调方法
 */
function ajaxForward(url, position, func) {
    $.ajax({
        url : url,
        type : "GET",
        async : false,
        loading : true,
        success : function(data){
            if(position == undefined || position == "" || position == null) {
                $("#" + $(".ui-tabs-selected").children("a").attr("idflag")).html(data);
            } else {
                $(position).html(data);
            }
            if(func != undefined){
                func();
            }
        }
    });
}