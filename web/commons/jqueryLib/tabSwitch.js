/**
 * 用于页面内部的tab页点击切换显示相应div的事件方法
 * @param _this 所点击的tab标签元素
 * @param content_prefix tab标签所对应div的id前缀。注：这里要求所有的前缀必须一样。
 * @param active 所要激活div的id的后缀
 */
function tabSwitch(_this,content_prefix,active) {
    var $tabs = $(_this);
    var $tabParent = $tabs.closest(".tab-panel-small");
    if($tabParent.length == 0) {
        $tabParent = $tabs.closest(".parentTab");
    }
    if( !$tabs.hasClass("disabled-tab")){
        $tabs.parent().siblings().find('a').not(".disabled-tab").removeClass("inside-tab-active");
        $("div[id^='"+content_prefix+"'][class='tab-panel-small']",$tabParent).css("display","none");
        $("div[id^='"+content_prefix+"'][class='dialog-tab-panel']",$tabParent).css("display","none");
        $tabs.addClass("inside-tab-active");
        $("#"+content_prefix+active,$tabParent).css("display","block");
    }
}
