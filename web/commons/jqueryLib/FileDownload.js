/**
 * Created by Kevin on 2016/12/5.
 */
$.fileDownload = function(url) {
    $(".ebDownloadIframe").remove();
    var $frame = $("<iframe></iframe>");
    $frame.attr("src",url).css({
        "display":"none"
    }).addClass("ebDownloadIframe");
    $frame.appendTo("body");
};