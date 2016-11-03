/**
 * Created by Kevin on 2016/11/3.
 */

/**
 * 获取所有选中的checkbox的值
 */
jQuery.fn.findCheckedStr = function() {
    var selectedIds = new Array();
    $(this).each(function(index, element){
        if(element.checked == true && $(element).val() != "on") {
            selectedIds.push(element.value);
        }
    });
    return selectedIds;
};
