(function (A) {
    A.fn.ligerDateEditor = function (B) {
        B = B || {};
        B = A.extend({
            dayMessage: ["日", "一", "二", "三", "四", "五", "六"],
            monthMessage: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            todayMessage: "今天",
            closeMessage: "关闭",
            format: "yyyy-MM-dd hh:mm",
            showTime: false,
            onChangeDate: false
        }, B);
        if (!B.showTime && B.format.indexOf(" hh:mm") > -1) {
            B.format = B.format.replace(" hh:mm", "")
        }
        return this.each(function () {
            if (this.manager) {
                return
            }
            if (this.tagName.toLowerCase() != "input" || this.type != "text") {
                return
            }
            var E = {
                bulidContent: function () {
                    var H = new Date(E.currentDate.year, E.currentDate.month - 1, 1).getDay();
                    var F = E.currentDate.month;
                    var G = E.currentDate.year;
                    if (++F == 13) {
                        F = 1;
                        G++
                    }
                    var I = new Date(G, F - 1, 0).getDate();
                    var J = new Date(E.currentDate.year, E.currentDate.month - 1, 0).getDate();
                    E.buttons.btnMonth.html(B.monthMessage[E.currentDate.month - 1]);
                    E.buttons.btnYear.html(E.currentDate.year);
                    E.toolbar.time.hour.html(E.currentDate.hour);
                    E.toolbar.time.minute.html(E.currentDate.minute);
                    if (E.toolbar.time.hour.html().length == 1) {
                        E.toolbar.time.hour.html("0" + E.toolbar.time.hour.html())
                    }
                    if (E.toolbar.time.minute.html().length == 1) {
                        E.toolbar.time.minute.html("0" + E.toolbar.time.minute.html())
                    }
                    A("td", this.body.tbody).each(function () {
                        this.className = ""
                    });
                    A("tr", this.body.tbody).each(function (L, K) {
                        A("td", K).each(function (P, N) {
                            var M = L * 7 + (P - H);
                            var O = M + 1;
                            if (E.selectedDate && E.currentDate.year == E.selectedDate.year && E.currentDate.month == E.selectedDate.month && M + 1 == E.selectedDate.date) {
                                if (P == 0 || P == 6) {
                                    A(N).addClass("l-box-dateeditor-holiday")
                                }
                                A(N).addClass("l-box-dateeditor-selected");
                                A(N).siblings().removeClass("l-box-dateeditor-selected")
                            } else {
                                if (E.currentDate.year == E.now.year && E.currentDate.month == E.now.month && M + 1 == E.now.date) {
                                    if (P == 0 || P == 6) {
                                        A(N).addClass("l-box-dateeditor-holiday")
                                    }
                                    A(N).addClass("l-box-dateeditor-today")
                                } else {
                                    if (M < 0) {
                                        O = J + O;
                                        A(N).addClass("l-box-dateeditor-out").removeClass("l-box-dateeditor-selected")
                                    } else {
                                        if (M > I - 1) {
                                            O = O - I;
                                            A(N).addClass("l-box-dateeditor-out").removeClass("l-box-dateeditor-selected")
                                        } else {
                                            if (P == 0 || P == 6) {
                                                A(N).addClass("l-box-dateeditor-holiday").removeClass("l-box-dateeditor-selected")
                                            } else {
                                                N.className = ""
                                            }
                                        }
                                    }
                                }
                            }
                            A(N).html(O)
                        })
                    })
                }, showDate: function () {
                    if (!this.selectedDate) {
                        return
                    }
                    var F = E.selectedDate.year + "/" + E.selectedDate.month + "/" + E.selectedDate.date;
                    this.currentDate.hour = parseInt(E.toolbar.time.hour.html());
                    this.currentDate.minute = parseInt(E.toolbar.time.minute.html());
                    if (B.showTime) {
                        F += " " + this.currentDate.hour + ":" + this.currentDate.minute
                    }
                    this.inputText.val(F);
                    this.inputText.trigger("change")
                }, isDateTime: function (G) {
                    var F = G.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
                    if (F == null) {
                        return false
                    }
                    var H = new Date(F[1], F[3] - 1, F[4]);
                    if (H == "NaN") {
                        return false
                    }
                    return (H.getFullYear() == F[1] && (H.getMonth() + 1) == F[3] && H.getDate() == F[4])
                }, isLongDateTime: function (H) {
                    var G = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/;
                    var F = H.match(G);
                    if (F == null) {
                        return false
                    }
                    var I = new Date(F[1], F[3] - 1, F[4], F[5], F[6]);
                    if (I == "NaN") {
                        return false
                    }
                    return (I.getFullYear() == F[1] && (I.getMonth() + 1) == F[3] && I.getDate() == F[4] && I.getHours() == F[5] && I.getMinutes() == F[6])
                }, getFormatDate: function (H) {
                    if (H == "NaN") {
                        return null
                    }
                    var G = B.format;
                    var I = {
                        "M+": H.getMonth() + 1,
                        "d+": H.getDate(),
                        "h+": H.getHours(),
                        "m+": H.getMinutes(),
                        "s+": H.getSeconds(),
                        "q+": Math.floor((H.getMonth() + 3) / 3),
                        "S": H.getMilliseconds()
                    };
                    if (/(y+)/.test(G)) {
                        G = G.replace(RegExp.$1, (H.getFullYear() + "").substr(4 - RegExp.$1.length))
                    }
                    for (var F in I) {
                        if (new RegExp("(" + F + ")").test(G)) {
                            G = G.replace(RegExp.$1, RegExp.$1.length == 1 ? I[F] : ("00" + I[F]).substr(("" + I[F]).length))
                        }
                    }
                    return G
                }, onTextChange: function () {
                    var G = E.inputText.val();
                    if (!B.showTime && !E.isDateTime(G)) {
                        if (!E.usedDate) {
                            E.inputText.val("")
                        } else {
                            E.inputText.val(E.getFormatDate(E.usedDate))
                        }
                    } else {
                        if (B.showTime && !E.isLongDateTime(G)) {
                            if (!E.usedDate) {
                                E.inputText.val("")
                            } else {
                                E.inputText.val(E.getFormatDate(E.usedDate))
                            }
                        } else {
                            while (G.indexOf("-") > -1) {
                                G = G.replace("-", "/")
                            }
                            var F = E.getFormatDate(new Date(G));
                            if (F == null) {
                                if (!E.usedDate) {
                                    E.inputText.val("")
                                } else {
                                    E.inputText.val(E.getFormatDate(E.usedDate))
                                }
                            }
                            E.usedDate = new Date(G);
                            E.selectedDate = {
                                year: E.usedDate.getFullYear(),
                                month: E.usedDate.getMonth() + 1,
                                day: E.usedDate.getDay(),
                                date: E.usedDate.getDate(),
                                hour: E.usedDate.getHours(),
                                minute: E.usedDate.getMinutes()
                            };
                            E.currentDate = {
                                year: E.usedDate.getFullYear(),
                                month: E.usedDate.getMonth() + 1,
                                day: E.usedDate.getDay(),
                                date: E.usedDate.getDate(),
                                hour: E.usedDate.getHours(),
                                minute: E.usedDate.getMinutes()
                            };
                            E.inputText.val(F);
                            if (B.onChangeDate) {
                                B.onChangeDate(F)
                            }
                            if (A(E.dateeditor).is(":visible")) {
                                E.bulidContent()
                            }
                        }
                    }
                }
            };
            this.manager = E;
            E.inputText = A(this);
            if (!E.inputText.hasClass("l-text-field")) {
                E.inputText.addClass("l-text-field")
            }
            E.link = A('<div class="l-trigger"><div class="l-trigger-icon"></div></div>');
            E.text = E.inputText.wrap('<div class="l-text"></div>').parent();
            E.text.append(E.link);
            if (B.width) {
                E.text.css({width: B.width});
                E.inputText.css({width: B.width - 20})
            }
            var C = "";
            C += "<div class='l-box-dateeditor' style='display:none'>";
            C += "    <div class='l-box-dateeditor-header'>";
            C += "        <div class='l-box-dateeditor-header-btn l-box-dateeditor-header-prevyear'><span></span></div>";
            C += "        <div class='l-box-dateeditor-header-btn l-box-dateeditor-header-prevmonth'><span></span></div>";
            C += "        <div class='l-box-dateeditor-header-text'><a class='l-box-dateeditor-header-month'></a> , <a  class='l-box-dateeditor-header-year'></a></div>";
            C += "        <div class='l-box-dateeditor-header-btn l-box-dateeditor-header-nextmonth'><span></span></div>";
            C += "        <div class='l-box-dateeditor-header-btn l-box-dateeditor-header-nextyear'><span></span></div>";
            C += "    </div>";
            C += "    <div class='l-box-dateeditor-body'>";
            C += "        <table cellpadding='0' cellspacing='0' border='0' class='l-box-dateeditor-calendar'>";
            C += "            <thead>";
            C += "                <tr><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td></tr>";
            C += "            </thead>";
            C += "            <tbody>";
            C += "                <tr><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td></tr><tr><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td></tr><tr><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td></tr><tr><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td></tr><tr><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td></tr><tr><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td><td align='center'></td></tr>";
            C += "            </tbody>";
            C += "        </table>";
            C += "        <ul class='l-box-dateeditor-monthselector'><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li></ul>";
            C += "        <ul class='l-box-dateeditor-yearselector'><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li></ul>";
            C += "        <ul class='l-box-dateeditor-hourselector'><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li></ul>";
            C += "        <ul class='l-box-dateeditor-minuteselector'><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li></ul>";
            C += "    </div>";
            C += "    <div class='l-box-dateeditor-toolbar'>";
            C += "        <div class='l-box-dateeditor-time'></div>";
            C += "        <div class='l-button l-button-today'></div>";
            C += "        <div class='l-button l-button-close'></div>";
            C += "        <div class='l-clear'></div>";
            C += "    </div>";
            C += "</div>";
            E.dateeditor = A(C);
            E.text.after(E.dateeditor);
            E.header = A(".l-box-dateeditor-header", E.dateeditor);
            E.body = A(".l-box-dateeditor-body", E.dateeditor);
            E.toolbar = A(".l-box-dateeditor-toolbar", E.dateeditor);
            E.body.thead = A("thead", E.body);
            E.body.tbody = A("tbody", E.body);
            E.body.monthselector = A(".l-box-dateeditor-monthselector", E.body);
            E.body.yearselector = A(".l-box-dateeditor-yearselector", E.body);
            E.body.hourselector = A(".l-box-dateeditor-hourselector", E.body);
            E.body.minuteselector = A(".l-box-dateeditor-minuteselector", E.body);
            E.toolbar.time = A(".l-box-dateeditor-time", E.toolbar);
            E.toolbar.time.hour = A("<a></a>");
            E.toolbar.time.minute = A("<a></a>");
            E.buttons = {
                btnPrevYear: A(".l-box-dateeditor-header-prevyear", E.header),
                btnNextYear: A(".l-box-dateeditor-header-nextyear", E.header),
                btnPrevMonth: A(".l-box-dateeditor-header-prevmonth", E.header),
                btnNextMonth: A(".l-box-dateeditor-header-nextmonth", E.header),
                btnYear: A(".l-box-dateeditor-header-year", E.header),
                btnMonth: A(".l-box-dateeditor-header-month", E.header),
                btnToday: A(".l-button-today", E.toolbar),
                btnClose: A(".l-button-close", E.toolbar)
            };
            var D = new Date();
            E.now = {
                year: D.getFullYear(),
                month: D.getMonth() + 1,
                day: D.getDay(),
                date: D.getDate(),
                hour: D.getHours(),
                minute: D.getMinutes()
            };
            E.currentDate = {
                year: D.getFullYear(),
                month: D.getMonth() + 1,
                day: D.getDay(),
                date: D.getDate(),
                hour: D.getHours(),
                minute: D.getMinutes()
            };
            E.selectedDate = null;
            E.usedDate = null;
            A("td", E.body.thead).each(function (G, F) {
                A(F).html(B.dayMessage[G])
            });
            A("li", E.body.monthselector).each(function (G, F) {
                A(F).html(B.monthMessage[G])
            });
            E.buttons.btnToday.html(B.todayMessage);
            E.buttons.btnClose.html(B.closeMessage);
            if (B.showTime) {
                E.toolbar.time.show();
                E.toolbar.time.append(E.toolbar.time.hour).append(":").append(E.toolbar.time.minute);
                A("li", E.body.hourselector).each(function (G, H) {
                    var F = G;
                    if (G < 10) {
                        F = "0" + G.toString()
                    }
                    A(this).html(F)
                });
                A("li", E.body.minuteselector).each(function (G, H) {
                    var F = G;
                    if (G < 10) {
                        F = "0" + G.toString()
                    }
                    A(this).html(F)
                })
            }
            E.bulidContent();
            E.onTextChange();
            E.link.hover(function () {
                this.className = "l-trigger-hover"
            }, function () {
                this.className = "l-trigger"
            }).mousedown(function () {
                this.className = "l-trigger-pressed"
            }).mouseup(function () {
                this.className = "l-trigger-hover"
            }).click(function () {
                E.bulidContent();
                E.dateeditor.slideToggle("fast")
            });
            E.buttons.btnClose.click(function () {
                E.dateeditor.slideToggle("fast")
            });
            A("td", E.body.tbody).hover(function () {
                if (A(this).hasClass("l-box-dateeditor-today")) {
                    return
                }
                A(this).addClass("l-box-dateeditor-over")
            }, function () {
                A(this).removeClass("l-box-dateeditor-over")
            }).click(function () {
                A(".l-box-dateeditor-selected", E.body.tbody).removeClass("l-box-dateeditor-selected");
                if (!A(this).hasClass("l-box-dateeditor-today")) {
                    A(this).addClass("l-box-dateeditor-selected")
                }
                E.currentDate.date = parseInt(A(this).html());
                E.currentDate.day = new Date(E.currentDate.year, E.currentDate.month - 1, 1).getDay();
                if (A(this).hasClass("l-box-dateeditor-out")) {
                    if (A("tr", E.body.tbody).index(A(this).parent()) == 0) {
                        if (--E.currentDate.month == 0) {
                            E.currentDate.month = 12;
                            E.currentDate.year--
                        }
                    } else {
                        if (++E.currentDate.month == 13) {
                            E.currentDate.month = 1;
                            E.currentDate.year++
                        }
                    }
                }
                E.selectedDate = {year: E.currentDate.year, month: E.currentDate.month, date: E.currentDate.date};
                E.showDate();
                E.dateeditor.slideToggle()
            });
            A(".l-box-dateeditor-header-btn", E.header).hover(function () {
                A(this).addClass("l-box-dateeditor-header-btn-over")
            }, function () {
                A(this).removeClass("l-box-dateeditor-header-btn-over")
            });
            E.buttons.btnYear.click(function () {
                if (!E.body.yearselector.is(":visible")) {
                    A("li", E.body.yearselector).each(function (F, G) {
                        var H = E.currentDate.year + (F - 4);
                        if (H == E.currentDate.year) {
                            A(this).addClass("l-selected")
                        } else {
                            A(this).removeClass("l-selected")
                        }
                        A(this).html(H)
                    })
                }
                E.body.yearselector.slideToggle()
            });
            E.body.yearselector.hover(function () {
            }, function () {
                A(this).slideUp()
            });
            A("li", E.body.yearselector).click(function () {
                E.currentDate.year = parseInt(A(this).html());
                E.body.yearselector.slideToggle();
                E.bulidContent()
            });
            E.buttons.btnMonth.click(function () {
                A("li", E.body.monthselector).each(function (F, G) {
                    if (E.currentDate.month == F + 1) {
                        A(this).addClass("l-selected")
                    } else {
                        A(this).removeClass("l-selected")
                    }
                });
                E.body.monthselector.slideToggle()
            });
            E.body.monthselector.hover(function () {
            }, function () {
                A(this).slideUp("fast")
            });
            A("li", E.body.monthselector).click(function () {
                var F = A("li", E.body.monthselector).index(this);
                E.currentDate.month = F + 1;
                E.body.monthselector.slideToggle();
                E.bulidContent()
            });
            E.toolbar.time.hour.click(function () {
                A("li", E.body.hourselector).each(function (F, G) {
                    if (E.currentDate.hour == F) {
                        A(this).addClass("l-selected")
                    } else {
                        A(this).removeClass("l-selected")
                    }
                });
                E.body.hourselector.slideToggle()
            });
            E.body.hourselector.hover(function () {
            }, function () {
                A(this).slideUp("fast")
            });
            A("li", E.body.hourselector).click(function () {
                var F = A("li", E.body.hourselector).index(this);
                E.currentDate.hour = F;
                E.body.hourselector.slideToggle();
                E.bulidContent()
            });
            E.toolbar.time.minute.click(function () {
                A("li", E.body.minuteselector).each(function (F, G) {
                    if (E.currentDate.minute == F) {
                        A(this).addClass("l-selected")
                    } else {
                        A(this).removeClass("l-selected")
                    }
                });
                E.body.minuteselector.slideToggle("fast", function () {
                    var F = A("li", this).index(A("li.l-selected", this));
                    if (F > 29) {
                        var G = (A("li.l-selected", this).offset().top - A(this).offset().top);
                        A(this).animate({scrollTop: G})
                    }
                })
            });
            E.body.minuteselector.hover(function () {
            }, function () {
                A(this).slideUp("fast")
            });
            A("li", E.body.minuteselector).click(function () {
                var F = A("li", E.body.minuteselector).index(this);
                E.currentDate.minute = F;
                E.body.minuteselector.slideToggle("fast");
                E.bulidContent()
            });
            E.buttons.btnPrevMonth.click(function () {
                if (--E.currentDate.month == 0) {
                    E.currentDate.month = 12;
                    E.currentDate.year--
                }
                E.bulidContent()
            });
            E.buttons.btnNextMonth.click(function () {
                if (++E.currentDate.month == 13) {
                    E.currentDate.month = 1;
                    E.currentDate.year++
                }
                E.bulidContent()
            });
            E.buttons.btnPrevYear.click(function () {
                E.currentDate.year--;
                E.bulidContent()
            });
            E.buttons.btnNextYear.click(function () {
                E.currentDate.year++;
                E.bulidContent()
            });
            E.buttons.btnToday.click(function () {
                E.currentDate = {year: E.now.year, month: E.now.month, day: E.now.day, date: E.now.date};
                E.selectedDate = {year: E.now.year, month: E.now.month, day: E.now.day, date: E.now.date};
                E.showDate();
                E.dateeditor.slideToggle("fast")
            });
            E.inputText.change(function () {
                E.onTextChange()
            })
        })
    }
})(jQuery);