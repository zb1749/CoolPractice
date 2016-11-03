/**
 * Created by Kevin on 2016/11/3.
 */

;var Box = (function ($) {
    new Image().src = "/commons/images/error/success.png";
    new Image().src = "/commons/images/error/warn.png";
    new Image().src = "/commons/images/error/question.png";

    var imgs = {
        success: "/commons/images/error/success.png",
        warning: "/commons/images/error/warn.png",
        confirm: "/commons/images/error/question.png"
    };

    function _init(mInfo, mType, MSuccess, MCancel) {
        var $div = null;

        if (typeof mInfo != "string") {
            $.error("错误的提示信息");
        }

        if (mType == "success" || mType == "warning") {
            $div = $('<div class="dialog-cmu"> <div class="dialog-content"> <div class="dialog-center"> <div class="dialog-warn"> <table> <tbody> <tr> <td><img src="" /></td> <td class="boxInfo"></td> </tr> </tbody> </table> </div> </div> </div> <div class="dialog-determine-panel"> <a class="button-determine"></a> </div> </div>');
        } else if (mType == "confirm") {
            $div = $('<div class="dialog-cmu"> <div class="dialog-content"> <div class="dialog-center"> <div class="dialog-warn"> <table> <tbody> <tr> <td><img src="" /></td> <td class="boxInfo"></td> </tr> </tbody> </table> </div> </div> </div> <div class="dialog-button"> <a class="button-yes"></a> <a class="button-no"></a> </div> </div>');
        } else {
            $.error("不支持的type类型[" + mType + "]");
        }

        $(".dialog-warn>table .boxInfo", $div).html(mInfo);
        $(".dialog-warn>table img", $div).attr("src", imgs[mType]);

        var btnOK = null;
        var btnCancel = null;
        if (mType == "confirm") {
            btnOK = "button-yes";
            btnCancel = "button-no";
        } else {
            btnOK = "button-determine";
        }

        if (MSuccess && typeof MSuccess == "function") {
            $("." + btnOK, $div).click(function (e) {
                $div.dialog("close");
                MSuccess.call();
                e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = true;
            });
        } else {
            $("." + btnOK, $div).click(function (e) {
                $div.dialog("close");
                e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = true;
            });
        }

        if (MCancel && typeof MCancel == "function" && mType == "confirm") {
            $("." + btnCancel, $div).click(function (e) {
                $div.dialog("close");
                MCancel.call();
                e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = true;
            });
        } else {
            $("." + btnCancel, $div).click(function (e) {
                $div.dialog("close");
                e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble = true;
            });
        }

        return $div;
    }

    var Box = {}

    Box.message = function (cfg) {
        try {
            var $div = _init(cfg.content, cfg.type, cfg.ok, cfg.cancel);
            $div.dialog({
                appendTo: cfg.appendTo || "#" + $(".ui-tabs-selected").children("a").attr("idflag"),
                minHeight: 150,
                width: 278,
                resizable: false,
                modal: true,
                close: function () {
                    if (cfg.close) {
                        cfg.close();
                    }
                    $(this).remove();
                }
            });
            $div.parent().css("outline", "none");
            return $div;
        } catch (e) {
            console.error("来自messageBox的错误：" + e);
            return null;
        }
    }

    Box.alert = function () {
        var message = arguments[0];
        var type = arguments[1];
        var callback = arguments[2] || arguments[1];

        if (typeof type != "string") {
            type = undefined;
        }

        try {
            if (type != "confirm") {
                return Box.message({
                    content: message,
                    type: type || "warning",
                    ok: callback
                });
            } else {
                $.error("请使用Box.confirm");
            }
        } catch (e) {
            console.error("来自messageBox的错误：" + e);
            return null;
        }
    }

    Box.confirm = function (message, ok, cancel) {
        if (ok && !$.isFunction(ok)) {
            $.error("来自messageBox的错误：无效的回调函数");
            return;
        }
        if (cancel && !$.isFunction(cancel)) {
            $.error("来自messageBox的错误：无效的回调函数");
            return;
        }
        return Box.message({
            content: message,
            type: "confirm",
            ok: ok,
            cancel: cancel
        });
    }

    return Box;
})(jQuery);
