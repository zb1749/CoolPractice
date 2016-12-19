;(function($){

	var methods = {

		init : function(options) {

			return this.each(function(){

				var $this = $(this);
				
				var defaults = {
					divId        : $this.attr('id'),//TODO
					url          : null,
					action       : null,
					type		 : "GET",
					formData     : {},
					currentPageName : "currentPage",
					currentPage  : 0,
					countPerPage : Cookies.get("pageSize") == null?10:Cookies.get("pageSize"),
					totalPage    : null,
					count		 : null,
					refresh		 : true,
					pageInputError : null
				};

				var settings = $.extend({},defaults,options);
				settings.currentPage = parseInt(settings.currentPage);
				settings.totalPage = parseInt(settings.totalPage);
				settings.count = parseInt(settings.count);
				settings.countPerPage = parseInt(settings.countPerPage);
				
				if(settings.totalPage != 0 && settings.currentPage > settings.totalPage) {
					handlers.ajaxTo(settings,handlers.addCCData(settings,settings.totalPage));
				}

				$this.data('pageNav',settings);
				
				$this.addClass("pageNav");
				$this.append("<span class='first-page'></span>");
				$this.append("<span class='prev-page'></span>");
				$this.append('<span class="curr-num">第&nbsp;<input type="text" class="enter-page-num" value="'+settings.currentPage+'">&nbsp;页</span>');
				$this.append('<span class="page-num">共 '+settings.totalPage+' 页</span>');
				$this.append("<span class='next-page'></span>");
				$this.append("<span class='last-page'></span>");
				if(settings.count != null && !$.isNaN(settings.count)) {
					$this.append('<span class="data-count">共 '+settings.count+' 条</span>');
				}
				if(settings.refresh) {
					$this.append('<span class="pageNav-load"></span>');
				}

				// 当前页是最后一页
				if (Number(settings.totalPage) <= Number(settings.currentPage)){
					$(".next-page",$this).hide();
					$(".last-page",$this).hide();
				}
				
				// 当前页是第一页
				if(settings.currentPage == 1) {
					$(".prev-page",$this).hide();
					$(".first-page",$this).hide();
				}
				
				//下一页等跳转按钮
				$("span[class$='page']",$this).click(function(){
					var attrC = $(this).attr("class");
					if(typeof settings.formData === 'object') {
						if(settings.formData instanceof Array) {
							switch(attrC){
								case'first-page':settings.formData.push({name:settings.currentPageName,value:0});break;
								case'prev-page':settings.formData.push({name:settings.currentPageName,value:(settings.currentPage-2)});break;
								case'next-page':settings.formData.push({name:settings.currentPageName,value:settings.currentPage});break;
								case'last-page':settings.formData.push({name:settings.currentPageName,value:(settings.totalPage-1)});break;
								default:alert(attrC);break;
							}
							settings.formData.push({name:"countPerPage",value:settings.countPerPage});
						} else {
							switch(attrC){
								case'first-page':settings.formData[settings.currentPageName] = 0;break;
								case'prev-page':settings.formData[settings.currentPageName] = (settings.currentPage-2);break;
								case'next-page':settings.formData[settings.currentPageName] = settings.currentPage;break;
								case'last-page':settings.formData[settings.currentPageName] = (settings.totalPage-1);break;
								default:alert(attrC);break;
							}
							settings.formData.countPerPage = settings.countPerPage;
						}
					} else if(typeof settings.formData === 'string') {
						switch(attrC){
							case'first-page':settings.formData += '&' + settings.currentPageName + '=0';break;
							case'prev-page':settings.formData += '&' + settings.currentPageName + '=' + (settings.currentPage-2);break;
							case'next-page':settings.formData += '&' + settings.currentPageName + '=' + settings.currentPage;break;
							case'last-page':settings.formData += '&' + settings.currentPageName + '=' + (settings.totalPage-1);break;
							default:alert(attrC);break;
						}
						settings.formData += '&countPerPage=' + settings.countPerPage;
					}
					handlers.ajaxTo(settings);
				});
				
				//回车跳转
				$(".enter-page-num",$this).keyup(function(e){
					if(e.keyCode == 13) {
						var num = $(this).val();
						var msg = handlers.checkNumForInput(settings,num);
						if(msg != ""){
							if($.isFunction(settings.pageInputError)) {
								return settings.pageInputError(msg);
							} else {
								throw new Error(msg);
							} 
						}
						handlers.ajaxTo(settings,handlers.addCCData(settings,num));
					}
				});
				
				//刷新
				if(settings.refresh) {
					$(".pageNav-load",$this).click(function(){
						handlers.ajaxTo(settings,handlers.addCCData(settings,settings.currentPage));
					});
				}
				
			});
		}

	};
	
	var handlers = {
		ajaxTo : function(settings) {
			if(settings.ajaxTo) {
				settings.ajaxTo.call(this);
			} else {
				if(settings.url != null) {
					if(settings.url.charAt(settings.url.length-1) == "&") {
						settings.url = settings.url.substring(0,settings.url.length-1);
					}
					$.ajax({//TODO
						loading		: true,
						type  		: settings.type,
						url   		: settings.url,
						data		: settings.formData,
						success		: function(data) {
							$('#'+settings.divId).html(data);
						}
					});
				} else {
					$.ajax({
						loading		: true,
						type  		: settings.type,
						url   		: settings.action,
						data  		: settings.formData,
						traditional	: true,
						success		: function(data) {
							$('#'+settings.divId).html(data);
						}
					});
				}
			}
		},
		checkNumForInput : function(settings,num){
			var msg = "";			
			if(isNaN(num)) {
				msg = "当前页不是数字";
			} else if(num!=parseInt(num)) {
				msg = "当前页为非整数";
			} else if(parseInt(num) > parseInt(settings.totalPage)) {
				msg = "当前页大于总页数";
			} else if(parseInt(num) <= 0) {
				msg = "当前页不能小于等于0";
			} 
			return msg;
		},
		addCCData : function(settings,num) {
			if(typeof settings.formData === 'object') {
				if(settings.formData instanceof Array) {
					settings.formData.push({name:settings.currentPageName,value:(num-1)});
					settings.formData.push({name:"countPerPage",value:settings.countPerPage});
				} else {
					settings.formData[settings.currentPageName] = num - 1;
					settings.formData.countPerPage = settings.countPerPage;
				}
			} else if(typeof settings.formData === 'string') {
				settings.formData += '&' + settings.currentPageName + '=' + (num - 1) + '&countPerPage=' + settings.countPerPage;
			}
		}
	};

	$.fn.navPage = function(method) {

		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('The method ' + method + ' does not exist in $.navPage');
		}

	};

})(jQuery);