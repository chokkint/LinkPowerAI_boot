/**
 * 系统公用UI工具类
 */
$(function() {

	NewUtils.initFormElementsClass($("form"));
	NewUI.pageInit();
	
	if (window != top) {
		$(document.body).on("click", function() {
			top.document.body.click();
		});
		$(document).on("click", "a[ui-tabRef],ul[ui-tabRef] a[href]", function() {
			top.mainTab[$(this).attr("ui-tabRef")].addTab($(this));
			return false;
		});
	}
});

window.NewUI = function() {
	var UI = {}, TUI = {};

	UI.pageInit = function() {
		$("[ui-ref='Tab']").each(function(i, n) {
			window.mainTab = window.mainTab || [];
			window.mainTab[n.id] = UI.NewTab(n);
		});
	};


	TUI.Utils = {
		getContextPath : function() {
			var pathName = document.location.pathname;
			var index = pathName.substr(1).indexOf("/");
			var result = pathName.substr(0, index + 1);
			return result;
		},
		
		JAjax : function() {
			var result, url, data, dataType, type, async, onsuccess;
			for (var i = 0; i < arguments.length; i++) {
				var param = arguments[i];
				if (i == 0) {
					url = param;
				} else if ($.type(param) == "string" && param != "") {
					var lowerParam = param.toString().toLowerCase();
					if (lowerParam == "post" || lowerParam == "get") {
						type = param;
					} else if (lowerParam == "text" || lowerParam == "json"
							|| lowerParam == "html" || lowerParam == "xml") {
						dataType = param;
					} else {
						data = param;
					}
				} else if ($.type(param) == "boolean") {
					async = param;
				} else if ($.type(param) == "function") {
					onsuccess = param;
				} else if (i == 1) {
					data = param;
				}
			}
			$.ajax({
				url : url,
				cache : false,
				async : async || false,
				type : type || "POST",
				data : data || "",
				dataType : dataType || "json",
				complete : function(XMLHttpRequest, textStatus) {
					var sessionstatus = XMLHttpRequest.getResponseHeader("errorCode"); // 通过XMLHttpRequest取得响应头,sessionstatus，
					var url = XMLHttpRequest.getResponseHeader("url");
					if (sessionstatus == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SESSION_TIMEOUT) {
						tips("登陆已经过期，即将重新登陆", "warning", 3000, function() {
							top.location = getContextPath() + "/jsp/login";
						});
					}
				},
				success : function(jsonResult) {
					if (!jsonResult) {
						return;
					}
					if ((jsonResult[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SESSION_TIMEOUT)) {
						tips("登陆已经过期，即将重新登陆", "warning", 3000, function() {
							top.location = getContextPath() + "/jsp/login";
						});
						return false;
					} else if ((jsonResult[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_NO_ACCESS)) {
						sysAlert("您没有权限访问此链接");
						return false;
					} else if ((jsonResult[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_UNKNOWN_EXCEPTIONS)) {
						sysAlert(jsonResult[ConstantNew.ERROR_MESSAGE_KEY], 500, false);
						// sysAlert("数据库写入失败，请联系管理员查看服务器日志!");
						return false;
					}
					if (jsonResult) {
						result = jsonResult;
						if (onsuccess && $.type(onsuccess) == "function")
							onsuccess(jsonResult);
					} else {
						tips("no result");
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					if (XMLHttpRequest.status == 400
							|| XMLHttpRequest.status == 500
							|| XMLHttpRequest.status == 404) {
						result = {error : "Connection Error"};
					} else {
						result = {error : "System Error"};
					}
				}
			});
			return result;
		},
		
		mergeObject : function() {
			var result = {};
			var isInit = false;
			for (var i = 0; i < arguments.length; i++) {
				var n = arguments[i];
				if (n) {
					if (!isInit) {
						result = n;
						isInit = true;
						continue;
					}
					$.each(n, function(j, m) {
						result[j] = m;
					});
				}
			}
			return result;
		},
		
		arrayHasParam : function(arrayObj, childrenName) {
			var hasChildren = false;
			if (!!!arrayObj)
				return result;
			for (var i = 0; i < arrayObj.length; i++) {
				var n = arrayObj[i];
				if (n && childrenName && n[childrenName]) {
					if (!hasChildren)
						hasChildren = true;
				}
			}
			return hasChildren;
		},
		
		array2Object : function(arrayObj, column, childrenName) {
			var result = {};
			if (!!!arrayObj)
				return result;
			for (var i = 0; i < arrayObj.length; i++) {
				var n = arrayObj[i];
				if (childrenName && n[childrenName]) {
					n = TUI.Utils.mergeObject(n, arguments.callee(n[childrenName], column, childrenName));
				}
				if (n && n[column]) {
					result[n[column]] = n;
				}
			}
			return result;
		},
		
		ObjectTranslateTree : function(datalist, topId, idName, pidName, childrenName) {
			if (!!!datalist || datalist.length == 0)
				return [];
			var objectForObj = [];
			var listForObj = TUI.Utils.array2Object(datalist, idName || "id", childrenName);

			if (TUI.Utils.arrayHasParam(datalist, childrenName)) {
				objectForObj = datalist;
			} else {
				if (topId) {
					objectForObj = TUI.Utils.initObjectForObj(listForObj, topId, pidName);
				} else {
					var k = 0;
					$.each(listForObj, function(i, n) {
						if (n[pidName] == undefined || !listForObj[n[pidName]]) {
							objectForObj[k] = n;
							var result = TUI.Utils.initObjectForObj(listForObj, i, pidName);
							if (result && result.length > 0)
								objectForObj[k].children = result;
							k++;
						}
					});
				}
			}
			return [ objectForObj, listForObj ];
		},
		
		initObjectForObj : function(obj, pid, pidName) {
			var objectForObj = [];
			var k = 0;
			var backFunction = arguments.callee;
			$.each(obj, function(i, n) {
				if (n[pidName || "pid"] == pid) {
					objectForObj[k] = n;
					// delete obj[i];
					var result = backFunction(obj, i, pidName);
					if (result && result.length > 0)
						objectForObj[k].children = result;
					k++;
				}
			});
			return objectForObj;
		}
	}

	TUI.UDatagrid = function(obj, params) {
		this.JDatagrid, this.Tablecolumn, this.headerRow, this.datagridPage, this.datagridData;
		this.obj = obj;
		this.defaultParam = {
			multiple : false,
			usepager : true,
			async : false,
			rp : 10,
			nowrap : true,
			type:"post",
			dataType:"json",
			loadMsg : "数据加载中...",
			nomsg : "没有数据",
			errormsg : 'Connection Error'
		};
		this.params = $.extend(this.defaultParam, params || {});
		this.datagridAjaxParam = params.datagridAjaxParam || {};
		this.initDatagrid();
	};

	TUI.UDatagrid.prototype = {
		constructor : TUI.UDatagrid,
		initDatagrid : function() {
			var datagrid = this;
			var params = datagrid.params;
			var obj = datagrid.obj;
			var datagridDiv, datagridTable, datagridHead, datagridPage;

			if (typeof obj == "string") {
				obj = $("#" + obj);
			}
			datagrid.obj = obj;

			if ($(obj).size() == 0 || ($(obj).get(0).tagName != "TABLE" && $(obj).get(0).tagName != "DIV")) {
				// console.log("datagrid初始化失败：未找到table对象或者div对象");
				return false;
			}

			//封装列信息
			var columes = [];
			var innerCol = []
			
			$.each(datagrid.params.colModel, function(i, n) {
				var colObj = {};
				colObj.field = n.name;
				colObj.title = n.displayName;
				if(n.width != undefined){
					colObj.width = n.width;
				}
				if(n.align != undefined){
					colObj.align = n.align;
				}
				
				if(n.formatter != undefined){
					colObj.formatter = n.formatter;
				}
				
				if(n.checkbox != undefined){
					colObj.checkbox = n.checkbox;
				}
				/*if(n.content&&n.param){
					n.newContent = n.content;
					$.each(n.param,function(k,o){
						var patt = new RegExp(k,"g");
						n.newContent = n.newContent.replace(patt,n[o]);
					})
					colObj.formatter = function(value,row,index){
						return n.newContent;
					}
				}*/
				
				innerCol.push(colObj)
			});
			columes.push(innerCol);
			params.columns = columes;
			
			//封装是否使用分页
			if(params.usepager){
				params.pagination = true;
				if(params.pageSize == undefined){
					params.pageSize = 10;
				}

				if(params.pageList == undefined){
					params.pageList = [10,30,50];
				}
			}

			//封装是否支持多选
			if(params.multiple){
				params.singleSelect = false;
			}else{
				params.singleSelect = true;
			}

			//封装初始化查询条件
			if(params.initQueryParam){
				params.queryParams = params.initQueryParam;
			}

			//封装数据处理操作
			if(params.onSuccessEvent){
				params.loadFilter = function(jsonResult){
					if(!jsonResult){return;}
					if ((jsonResult[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SESSION_TIMEOUT)) {
						tips("登陆已经过期，即将重新登陆", "warning",3000,function(){
							//top.location = getContextPath() + "/jsp/login";
							//window.location.reload();
						});
						jsonResult.rows = [];
						return jsonResult;
					}else if((jsonResult.errorCode == 1)){
						sysAlert(jsonResult.errorMessage);
						jsonResult.rows = [];
						return jsonResult;
					}
					if(jsonResult){
						result = jsonResult;
						if(params.onSuccessEvent&&typeof params.onSuccessEvent == "function")params.onSuccessEvent(jsonResult);
					} else tips("no result");

					return jsonResult;
				};
			}

			datagrid.params = params;
			
			// SessionAlive Check
			var result = datagrid.checkSessionTimeOut(datagrid.params);
			
			datagrid.generateGrid(datagrid.params);

		},
		reloadDatagrid : function(data) {
			var datagrid = this;
			// SessionAlive Check
			var result = datagrid.checkSessionTimeOut(datagrid.params);
			
			if (data != undefined && data != null) {
				if (typeof data != "string" || data.indexOf("/") == -1) {
					// console.log(data);
					datagrid.obj.datagrid('options').queryParams = data;
					// console.log(datagrid.params.queryParams);
				} else {
					datagrid.params.url = data;
				}
			}
			datagrid.obj.datagrid('reload');
			datagrid.obj.datagrid('unselectAll');
		},
		reloadDatagridForQuery : function(data) {
			var datagrid = this;
			// SessionAlive Check
			var result = datagrid.checkSessionTimeOut(datagrid.params);
			
			if (data != undefined && data != null) {
				if (typeof data != "string" || data.indexOf("/") == -1) {
					// console.log(data);
					datagrid.obj.datagrid('options').queryParams = data;
					// console.log(datagrid.params.queryParams);
				} else {
					datagrid.params.url = data;
				}
			}
			datagrid.obj.datagrid('load');
			datagrid.obj.datagrid('unselectAll');
		},
		getSelectedRows : function() {
			var datagrid = this;
			var selectedRows = datagrid.obj.datagrid("getSelections");
			return selectedRows;
		},
		checkSessionTimeOut:function(params){
			var result;
			$.ajax({
				url: getContextPath() + "/isSessionAlive",
				cache:false,
				async:params.async,
				type:params.type,
				data:params.data,
				dataType:params.dataType,		
		        complete:function(XMLHttpRequest,textStatus){ 
		            var sessionstatus=XMLHttpRequest.getResponseHeader("errorCode"); //通过XMLHttpRequest取得响应头,sessionstatus，
					var url = XMLHttpRequest.getResponseHeader("url");
					if (sessionstatus == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SESSION_TIMEOUT) {
						tips("登陆已经过期，即将重新登陆", "warning", 3000, function() {
							top.location = getContextPath() + "/jsp/login";
						});
					} 
				},
//				error:function(XMLHttpRequest, textStatus, errorThrown){
//					if(XMLHttpRequest.status==400||XMLHttpRequest.status==500||XMLHttpRequest.status==404){
//						result = {error:"Connection Error"};
//					}else{
//						result = {error:"System Error"};
//					}
//				}
			});
			return result;
		},		
		generateGrid : function(datagridAjaxParam) {
			var datagrid = this;
			datagrid.obj.datagrid(datagridAjaxParam);

			if(datagridAjaxParam.pagination){
				// 设置分页控件
				var p = datagrid.obj.datagrid('getPager');
				// console.log(p);
				var newParams = {
					pageSize : datagridAjaxParam.pageSize,// 每页显示的记录条数，默认为10
					pageList : datagridAjaxParam.pageList,// 可以设置每页记录条数的列表
					beforePageText : '第',// 页数文本框前显示的汉字
					afterPageText : '页    共 {pages} 页',
					displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'};
				
				$(p).pagination(newParams);
			}
		},	
		selectAllRows : function() {
			var datagrid = this;
			datagrid.obj.datagrid("selectAll");
		},
		unSelectAllRows : function() {
			var datagrid = this;
			datagrid.obj.datagrid("clearSelections");
		}
	};

	TUI.UDialog = function(params) {
		this.Jdialog;
		this.dialogParams = params;
		this.defaultParam = {
			type : "text",
			draggable : true,
			isMatte : false
		};
		this.createDialog();
	};

	TUI.UDialog.prototype = {
		constructor : TUI.UDialog,
		createDialog : function() {
			/* 定义参数 */
			var params = this.dialogParams;
			var dialog = this;
			var defaultParam = this.defaultParam;
			if (!!!params.type) {
				params.type = params.html ? "html" : (params.src ? "iframe" : defaultParam.type);
			}

			if (params.type == "text") {
				defaultParam.width = 300;
				defaultParam.height = 350;
			}else if (params.type == "html") {
				defaultParam.width = 500;
				defaultParam.height = 400;
			} else if (params.type == "iframe") {
				defaultParam.width = 1000;
				defaultParam.height = 700;
			}

			params = $.extend({}, defaultParam, params);
			
			/* 定义html */
			var Jdialog = $("<div class='box box-none dialog'>"
					+ (params.isMatte ? "<div class='Matte'></div>" : "")
					+ "<div class='dialogBox'>"
					+ "<div class='box-header with-border'><h3 class='box-title'></h3><div class='box-tools pull-right'><button type='button' class='btn btn-box-tool' data-widget='remove'><i class='fa fa-remove'></i></button></div></div>"
					+ "<div class='box-body'></div>"
					+ " <div class='box-footer'></div>"
					+ "</div></div>");
			// if(params.isMatte){$(document.body).css("overflow","hidden");}
			var JdialogList = {
				dialogFrame : $(Jdialog),
				dialogHeader : $(".box-header", Jdialog),
				title : $(".box-title", Jdialog),
				dialogBox : $(".dialogBox", Jdialog),
				closeButton : $(".box-tools", Jdialog),
				operateButton : $(".box-footer", Jdialog),
				content : $(".box-body", Jdialog)
			};

			/* 定义按钮 */
			if (params.buttons || params.hasCloseButton) {
				var buttonContent = $("<div class='row'></div>");

				var buttonCount = 0;
				if(params.hasCloseButton){
					buttonCount++;
				}
				if(params.buttons){
					buttonCount += params.buttons.length;
				}
				
				var buttonLength = parseInt(12/buttonCount);
				
				if (params.hasCloseButton) {
					var closeButton = $("<div class='col-xs-" + buttonLength + "'><button type='button' class='btn btn-success pull-left' data-widget='remove' style='width:100%;'>取消</button></div>").bind("click", function() {
						dialog.closeDialog();
					});
					buttonContent.append(closeButton);
				}

				if (params.buttons) {
					$.each(params.buttons, function(i, n) {
						var newButton = $("<div class='col-xs-" + buttonLength + "' style='float: right;'><button type='button' class='btn btn-success' style='width:100%;'>" + n.name + "</button></div>").bind("click", function() {
							n.clickEvent(params.type == "iframe" ? ($("iframe", JdialogList.content).get(0).contentWindow) : JdialogList.content);
						});
						buttonContent.append(newButton);
					});
				}

				JdialogList.operateButton.append(buttonContent);
//				JdialogList.buttonContent = buttonContent;
			}

			dialog.Jdialog = Jdialog;
			JdialogList.title.text(params.title || "");
			if (params.noRightTopCloseButton) {
				JdialogList.closeButton.remove();
			} else {
				JdialogList.closeButton.bind("click", function() {
					dialog.closeDialog();
				});
			}

			/* 定义内容 */
			switch (params.type) {
				case "html": {
					JdialogList.content.append(params.html);
					break;
				}
				case "iframe": {
					var addIframe = $("<iframe frameBorder='0'/>");
					TUI.loadAcx.show(JdialogList.content);
					JdialogList.content.addClass("iframeContent").append(addIframe);
	
					if (params.src) {
						$("document", addIframe).ready(function() {
							TUI.loadAcx.hide(JdialogList.content);
						});
						addIframe.attr("src", params.src);
					} else {
						TUI.loadAcx.hide($(".dialogContent", JdialogList.content));
					}
					break;
				}
				case "text": {
					JdialogList.content.append("<p>" + (params.text || "") + "</p>");
					break;
				}
			};
			
			var isMouseDown = false, pageX = 0, pageY = 0;
			JdialogList.dialogHeader.addClass("draggable");

			JdialogList.dialogHeader.on("mousedown", function(event) {
				isMouseDown = true;
				pageX = event.pageX, pageY = event.pageY;
			});
			
			var currentBody = $(top.document);
			if($("iframe").contents().find("body").length == 1){
				currentBody = $("iframe").contents();
			}

			$(currentBody).find("body").on("mousemove mouseup", function(event) {
				if (isMouseDown) {
					var position = JdialogList.dialogFrame.position();
					JdialogList.dialogFrame.css({
						left:(position.left+event.pageX-pageX),
						top:(position.top+event.pageY-pageY)
					});
					if (event.type == "mousemove") {
						pageX = event.pageX;
						pageY = event.pageY;
					} else {
						isMouseDown = false;
						pageX = 0;
						pageY = 0;
					}
				}
			});

			/* 定义定时消失 */
			if (params.showTime) {
				window.setTimeout(function() {dialog.closeDialog();}, params.showTime);
			}

			/* 定义样式 */
			if (params.css) {
				if (params.css.zindex){
					JdialogList.dialogFrame.css("z-index", params.css.zindex);
				}
			}else{
				JdialogList.dialogFrame.css("z-index", 2000);
			}

			$(currentBody).find("body").append(Jdialog);

			/* 定义位置 */
			if ((params.type == "text" || params.type == "html") && params.height == undefined) {
				params.height = JdialogList.content.height() + 30;
			}

			JdialogList.dialogFrame.width(params.width+20);
			if (params.height){
				JdialogList.content.css("height", params.height+30);
			}
			JdialogList.content.css("width", params.width);
			if(params.backgroundCss)JdialogList.dialogBox.css("background",params.backgroundCss);

			var calculateLeft = ($(currentBody).width() - (params.width)) / 2;
			var calculateTop = ($(currentBody).height() - (JdialogList.dialogBox.height())) / 2;
			if (calculateLeft < 0) {
				params.width = $(currentBody).height();
			}
			JdialogList.dialogFrame.css("left", calculateLeft >0 ? calculateLeft : 0);
			JdialogList.dialogFrame.css("top", calculateTop > 10 ? calculateTop-10 : 50);

			if (params.type == "iframe") {
				$("iframe", JdialogList.content).css("height", params.height);
			}

			dialog.JdialogList = JdialogList;
			top.dialogList.push(this);
		},
		closeDialog : function(speed) {
			var dialog = this;
			if (dialog.dialogParams.isMatte) {
				$(top.document.body).css("overflow", "auto");
			}
			if (typeof dialog.dialogParams.onCloseFunction == "function") {
				dialog.dialogParams.onCloseFunction();
			}
			top.dialogList.pop();

			speed = speed == undefined ? "slow" : speed;
			dialog.JdialogList.dialogFrame.slideUp(speed, function() {
				dialog.Jdialog.remove();
				dialog = {};
			});
		},
		close : function(speed) {
			this.closeDialog(speed);
		}
	};
	
	TUI.UTab = function(tabsDiv) {
		if (typeof tabsDiv == "string") {
			this.tabsDiv = $("#" + tabsDiv);
		} else
			this.tabsDiv = $(tabsDiv);
		if (this.tabsDiv.size() == 0)
			return false;
		this.init();
	};

	TUI.UTab.prototype = {
		constructor : TUI.UTab,
		init : function() {
			var tabsDiv = this.tabsDiv;
			var tab = this;
			var tabsDivId = tabsDiv.attr("id");
			tabsDiv.addClass("tabsDiv");
			tabsDiv.append('<div class="tabMenu" style="display:none"><div class="tabMenuList"><div class="tabMenuListInnerDiv"></div></div></div><div class="tabContent"></div>');

			$(".leftShift", tabsDiv).click(function() {
				$(".tabMenuList", tabsDiv).animate({scrollLeft : ($(".tabMenuList", tabsDiv).scrollLeft() - 200)}, "slow");
			});
			$(".rightShift", tabsDiv).click(function() {
				$(".tabMenuList", tabsDiv).animate({scrollLeft : ($(".tabMenuList", tabsDiv).scrollLeft() + 200)}, "slow");
			});

			var focusTabId = "";
			var tabcontextmenu = $("<div class='tabcontextmenu'><a href='javascript:;'>刷新</a><a href='javascript:;'>关闭当前页面</a><a href='javascript:;'>关闭其他所有页面</a><a href='javascript:;'>关闭所有页面</a></div>");
			$("a:eq(0)", tabcontextmenu).bind("click", function() {
				tab.reloadTab(focusTabId);
			});
			$("a:eq(1)", tabcontextmenu).bind("click", function() {
				tab.removeTab(focusTabId);
			});
			$("a:eq(2)", tabcontextmenu).bind("click", function() {
				tab.removeOtherTabs(focusTabId);
			});
			$("a:eq(3)", tabcontextmenu).bind("click", function() {
				tab.removeAllTabs();
			});
			tabsDiv.append(tabcontextmenu);
			$(document).bind("click", function() {
				if (tabcontextmenu)
					tabcontextmenu.hide();
			});
			$(".tabMenu", tabsDiv).bind("contextmenu", function(event) {
				var eventtarget = event.target;
				if (eventtarget.tagName.toString().toLowerCase() == "span") {
					focusTabId = $(eventtarget).attr("tabId");
					var bodyWidth = $("body").width();
					var menuWidth = tabcontextmenu.width();
					if (event.pageX + menuWidth > bodyWidth) {
						tabcontextmenu.css({left : bodyWidth - menuWidth, top : event.pageY});
					} else {
						tabcontextmenu.css({left : event.pageX, top : event.pageY});
					}
					tabcontextmenu.show();
					event.preventDefault();
				}
			});

			tab.resizeTab();
			$(window).resize(function() {
				tab.resizeTab();
			});
			$("[ui-tabRef='" + tabsDivId + "'][tabInit='true'],[ui-tabRef='" + tabsDivId + "'] [tabInit='true']").each(function(i, n) {
				tab.addTab($(n));
				return false;
			});
			$(document).on("click", "a[ui-tabRef='" + tabsDivId + "'],ul[ui-tabRef='" + tabsDivId + "'] a[href]", function() {
				tab.addTab($(this));
				return false;
			});

		},
		reInitLinks : function() {
			var tab = this;
			var tabsDivId = this.tabsDiv.attr("id");
			$("[ui-tabRef='" + tabsDivId + "'][tabInit='true'],[ui-tabRef='" + tabsDivId + "'] [tabInit='true']").each(function(i, n) {
				tab.addTab($(n));
				return false;
			});
		},
		resizeTab : function() {
			var tabsDiv = this.tabsDiv;
			tabsDiv.height(Math.min($(window).height() - 130, $(document).height() - 131));
			$(".tabContent", tabsDiv).height(tabsDiv.height());
		},
		addTab : function(eventObj, url) {
			var tabsDiv = this.tabsDiv;
			var tab = this;
			tab.removeAllTabs();
			var tabId = eventObj.attr("tabId");
			if (tabId && $(".tabMenu [tabId='" + tabId + "']", tabsDiv).size() > 0) {
				if (!$(".tabMenu [tabId='" + tabId + "']", tabsDiv).is(".focusing")) {
					tab.focusTab(tabId);
				}
			} else {
				tabId = "tab" + new Date().getTime();
				eventObj.attr("tabId", tabId);
				$(".tabMenuListInnerDiv", tabsDiv).append("<span tabId='" + tabId + "'>" + (eventObj.attr("tabName") || eventObj.text()) + "<a class='removeTab'>×</a></span>");
				var addIframe = $("<iframe tabId='" + tabId + "'  frameBorder='0'/>");

				TUI.loadAcx.show($(".tabContent", tabsDiv));
				$(".tabContent", tabsDiv).append(addIframe);
				tab.focusTab(tabId);
				url = url == undefined ? eventObj.attr("href") : url;
				if (url) {
					$("document", addIframe).ready(function() {
						TUI.loadAcx.hide($(".tabContent", tabsDiv));
					});
					addIframe.attr("src", url);
				} else {
					TUI.loadAcx.hide($(".tabContent", tabsDiv));
				}

				$(".tabMenu [tabId='" + tabId + "']", tabsDiv).click(function() {
					tab.focusTab(tabId);
				});
				$(".tabMenu [tabId='" + tabId + "'] .removeTab", tabsDiv).click(function() {
					tab.removeTab(tabId);
				});
			}
		},
		focusTab : function(tabId) {
			var tabsDiv = this.tabsDiv;
			var Tabfocused = $(".tabMenu [tabId='" + tabId + "']", tabsDiv);
			$(".focusing", tabsDiv).removeClass("focusing");
			$("[tabId='" + tabId + "']", tabsDiv).addClass("focusing");
			var offSetRight = Tabfocused.offset().left - tabsDiv.offset().left
					- $(".tabMenu", tabsDiv).width() + Tabfocused.width()
					+ $(".tabMenuList", tabsDiv).scrollLeft() + 40;
			var offSetLeft = Tabfocused.offset().left - tabsDiv.offset().left;
			if (offSetRight > 0) {
				$(".tabMenuList", tabsDiv).animate({scrollLeft : offSetRight}, "slow");
			} else if (offSetLeft < 0) {
				$(".tabMenuList", tabsDiv).animate({scrollLeft : $(".tabMenuList", tabsDiv).scrollLeft() + offSetLeft}, "slow");
			}
		},
		getFocusingTab : function(tabId) {
			var tabsDiv = this.tabsDiv;
			return $("iframe[tabId].focusing", tabsDiv).get(0);
		},
		removeTab : function(tabId) {
			var tabsDiv = this.tabsDiv;
			var tab = this;
			var Tabremoved = $("[tabId='" + tabId + "']", tabsDiv);
			var TabremovedMenu = $(".tabMenu [tabId='" + tabId + "']", tabsDiv);
			if (TabremovedMenu.is(".focusing")) {
				if (TabremovedMenu.prev().size() > 0) {
					tab.focusTab(TabremovedMenu.prev().attr("tabId"));
				} else if (TabremovedMenu.next().size() > 0) {
					tab.focusTab(TabremovedMenu.next().attr("tabId"));
				}
			}
			Tabremoved.remove();
		},
		removeAllTabs : function() {
			var tabsDiv = this.tabsDiv;
			var tab = this;
			var Tabremoved = $("[tabId]", tabsDiv);
			Tabremoved.remove();
		},
		removeOtherTabs : function(tabId) {
			var tabsDiv = this.tabsDiv;
			var tab = this;
			var Tabremoved = $("iframe[tabId!='" + tabId + "']", tabsDiv);
			var TabremovedMenu = $(".tabMenu span[tabId!='" + tabId + "']", tabsDiv);
			TabremovedMenu.remove();
			Tabremoved.remove();
			tab.focusTab(tabId);
		},
		reloadTab : function(tabId) {
			var tabsDiv = this.tabsDiv;
			var Tabiframe = $("iframe[tabId='" + tabId + "']", tabsDiv);
			Tabiframe.get(0).contentWindow.location.reload();
		}
	}

	TUI.UTree = function(obj, params) {
		if (typeof obj == "string") {
			this.Jtree = $("#" + obj);
		} else{
			this.Jtree = $(obj);
		}
		if (this.Jtree.size() == 0){
			return false;
		}
		this.defaultParams = {
			name : "name",
			idName : "id",
			pidName : "pid",
			childrenName : "children"
		};
		this.params = $.extend(this.defaultParams, params || {});
		this.objectForObj = {};
		this.listForObj = {};
		this.init();
	}

	TUI.UTree.prototype = {
		constructor : TUI.UTree,
		init : function() {
			var tree = this;
			if (tree.params.contentSrc) {
				TUI.Utils.JAjax(tree.params.contentSrc, (tree.params.initSeparate ? ((tree.params.data ? "&" : "") + "pid=" + tree.params.topId) : tree.params.data), "json", "GET", true, function(msg) {
					tree.params.content = msg;
					if (tree.params.initSeparate) {
						tree.Jtree.append(tree.initJtreeAjax(tree.params.content, 0, tree.params.topId));
					} else {
						tree.initJtree();
					}
					tree.initTreeChange(tree.Jtree);
				});
			} else if (tree.params && tree.params.content) {
				tree.initJtree();
				tree.initTreeChange(tree.Jtree);
			}
			tree.initReadyEvent();
		},
		initJtree : function() {
			var tree = this;
			var treeHtml = "";
			var result = TUI.Utils.ObjectTranslateTree(tree.params.content, tree.params.topId, tree.params.idName, tree.params.pidName, tree.params.childrenName);
			tree.objectForObj = result[0];
			tree.listForObj = result[1];
			treeHtml = tree.initJtreeHtml(tree.objectForObj, 0, false);
			tree.Jtree.append(treeHtml);
			tree.initEvent();
		},
		initJtreeAjax : function(content, level, pid) {
			var tree = this;
			var treeHtml = "";
			treeHtml = tree.initJtreeHtml(content, level, true, pid);
			return treeHtml;
		},
		initJtreeHtml : function(treeTree, level, hasLine, pid) {
			level = level + 1;
			var tree = this;
			var treeHtml = "";
			if (!!!treeTree) return false;
			//treeHtml += "<ul class='sidebar-menu'><li class='header'>MAIN NAVIGATION</li>";
			$.each(treeTree, function(i, n) {
				//Parent Node
				if (n[tree.params.childrenName]==undefined) {
					if (level==1){
						treeHtml +="<li class='treeview' ui-tree-id='"+n[tree.params.idName]+"'>";
						treeHtml += "<a ";
						if (n.prop) {
							$.each(n.prop, function(j, m) {
								treeHtml += j + "=" + "'" + m + "' ";
							})
						}
						treeHtml += "> <i class='fa fa-dashboard'></i>"
							+ "<span>"+n[tree.params.name]+"</span>"
				            + "</a>";						
					}else{
						treeHtml +="<li ui-tree-id='"+n[tree.params.idName]+"'>";
						treeHtml += "<a ";
						if (n.prop) {
							$.each(n.prop, function(j, m) {
								treeHtml += j + "=" + "'" + m + "' ";
							})
						}
						treeHtml += "> <i class='fa fa-circle-o'></i>"
							+ "<span>"+n[tree.params.name]+"</span>"
				            + "</a>";
					}
					

				}else{
					treeHtml +="<li class='treeview'>"
						+ "<a>";
					if (level==1){
						treeHtml +="<i class='fa fa-dashboard'></i>";
						treeHtml += "<span>"+n[tree.params.name]+"</span>"
						+ "<span class='pull-right-container'>"
			            + "<i class='fa fa-angle-left pull-right'></i>"
			            + "</span>"
			            + "</a>";						
					}else{
						treeHtml +="<i class='fa fa-circle-o'></i>";
						treeHtml += "<span>"+n[tree.params.name]+"</span>"
						+ "<span class='pull-right-container'>"
			            + "<i class='fa fa-angle-left pull-right'></i>"
			            + "</span>"
			            + "</a>";
					}
						

					//child node
					treeHtml +="<ul class='treeview-menu'>";
					treeHtml += tree.initJtreeHtml(n[tree.params.childrenName], level, (level > 0 && i < treeTree.length - 1));
					treeHtml += "</ul>";					
				}

				treeHtml += "</li>";
			});
			//treeHtml += "</ul>";
			return treeHtml;
		},
		initEvent : function() {
			var tree = this;
			$.each(tree.listForObj, function(i, n) {
				if (n.onclick || n.ondblclick || n.onload) {
					var selA = $("li[ui-tree-id='" + n[tree.params.idName] + "']>a", tree.Jtree);
					if (n.onload) {
						n.onload(selA.get(0));
					}
					if (n.onclick) {
						selA.on("click", n.onclick);
					}
					if (n.ondblclick) {
						selA.on("dblclick", n.onclick);
					}
				}
			})
		},
		initReadyEvent : function() {
			var tree = this;
			if (tree.params.onReady && typeof tree.params.onReady == "function") {
				tree.params.onReady();
			}
			$.each(tree.listForObj, function(i, n) {
				if (typeof n.onReady == "function") {
					var selA = $("li[ui-tree-id='" + n[tree.params.idName] + "']>a", tree.Jtree);
					n.onReady(selA.get(0));
				}
			})
		},
		initTreeChange : function(Jtree) {
			var tree = this;
			var isMenu = tree.params.treeType && tree.params.treeType == "menu";
			$((isMenu ? "li>a" : "li>span.switch"), Jtree).click(function() {
				var currentLink = $(this).parent();
				var parentTree = currentLink.parent();
				if (!!!currentLink.is(".opened")) {
					if (tree.params.openSelf) {
						$("ul.opened,li.opened", parentTree).removeClass("opened");
					}
					currentLink.addClass("opened");
					if (tree.params.contentSrc && tree.params.initSeparate && !currentLink.prop("childInit")) {
						TUI.Utils.JAjax(tree.params.contentSrc, ((tree.params.data ? "&" : "") + "pid=" + currentLink.attr("ui-tree-id")), "json", "GET", true, function(msg) {
							var newContent = msg;
							if (typeof tree.params.onDataLoad == "function") {
								newContent = tree.params.onDataLoad(newContent);
							}
							var result = tree.initJtreeAjax(newContent, 1, currentLink.attr("ui-tree-id"));
							if (result){
								currentLink.append(result);
							}
							tree.initTreeChange(currentLink);
							currentLink.prop("childInit", true);
							var currentChildTree = $(currentLink).children("ul");
							if (currentChildTree.size() > 0) {
								currentChildTree.addClass("opened");
							}
						});
					} else {
						var currentChildTree = $(currentLink).children("ul");
						if (currentChildTree.size() > 0) {
							currentChildTree.addClass("opened");
						}
					}
				} else {
					var currentChildTree = $(currentLink).children("ul");
					if (currentChildTree.size() > 0 && tree.params.openSelf) {
						currentChildTree.find(".opened").andSelf().removeClass("opened");
						$(".opened", currentChildTree).removeClass("opened");
						currentLink.removeClass("opened");
					} else if (currentChildTree.size() > 0) {
						currentChildTree.removeClass("opened");
						currentLink.removeClass("opened");
					}
				}
			});

			if (!isMenu && tree.params.checkbox) {
				$("li>span.chk", Jtree).click(function() {
					var currentCheckbox = $(this);
					var checkedStatus = currentCheckbox.prop("checked");
					var currentLi = currentCheckbox.parent();
					if (checkedStatus) {
						currentCheckbox.removeClass("checked").prop("checked", false);
						if (!tree.params.checkboxNoLink) {
							tree.setParentUnCheck(currentLi);
							$("li>span.chk", currentLi).removeClass("checked").prop("checked", false);
						}
					} else {
						currentCheckbox.addClass("checked").prop("checked", true);
						if (!tree.params.checkboxNoLink) {
							currentLi.parentsUntil('.level_1').filter("li").children("span.chk").addClass("checked").prop("checked", true);
							$("li>span.chk", currentLi).addClass("checked").prop("checked", true);
						}
					}
				});
			}

			$("li>a", tree.Jtree).click(function() {
				$("a.curSelectedNode", tree.Jtree).removeClass("curSelectedNode");
				$(this).addClass("curSelectedNode");
			});
		},
		setParentUnCheck : function(currentLi) {
			if (currentLi.parent().is("ul.level_1")) {
				return false;
			} else {
				var parentLi = currentLi.parent().parent();
				if (parentLi.children("ul").children("li").children("span.chk.checked").length == 0) {
					parentLi.children("span.chk.checked").removeClass("checked").prop("checked", false);
					arguments.callee(parentLi);
				}
			}
		},
		getSelectedTreeList : function() {
			var tree = this;
			var selectedTreeList = [];
			$("span.chk.checked", tree.Jtree).each(function(i, n) {
				var selectedLi = $(n).parent();
				selectedTreeList.push({
					id : selectedLi.attr("ui-tree-id"),
					name : $(selectedLi).children("a").children(".treeText").text()
				});
			});
			return selectedTreeList;
		},
		getCurSelected : function() {
			var tree = this;
			var curSelectedNode = $(".curSelectedNode", tree.Jtree);
			return {
				id : curSelectedNode.parent().attr("ui-tree-id"),
				name : $(".treeText", curSelectedNode).text()
			};
		}
	}

	TUI.loadAcx = {
		show : function(loadContainer) {
			var JloadContainer = $(loadContainer);
			var loadingPage = $("<div class='loadAcx'><img src='" + (TUI.Utils.getContextPath() + "/images/ajax-loader.gif") + "'/></div>");
			loadingPage.height(JloadContainer.height());
			loadingPage.width(JloadContainer.width());
			loadingPage.css("top", JloadContainer.position().top);
			loadingPage.css("left", JloadContainer.position().left);
			loadContainer.append(loadingPage);
			loadContainer.data("loadingData", {"overflow" : (loadContainer.css("overflow"))});
			loadContainer.css("overflow", "hidden");
		},
		hide : function(loadContainer) {
			var JloadContainer = $(loadContainer);
			if (JloadContainer.data("loadingData")) {
				loadContainer.css("overflow", JloadContainer.data("loadingData").overflow);
				$(".loadAcx", JloadContainer).remove();
				JloadContainer.removeData("loadingData");
			}
		}
	};

	TUI.Page = function(pageSize) {
		this.pageSize = pageSize || 10;
		this.pageNo = 1;
		this.pageCount = 0;
		this.pageNum = 0;
		this.Jpage;
	};

	TUI.Page.prototype = {
		constructor : TUI.Page,
		generateLink : function(pageSet) {
			pageSet = pageSet ? pageSet : {};
			var page = this;

			var pageNo = page.pageNo = pageSet.pageNo || 1;
			var pageCount = page.pageCount = pageSet.pageCount;
			page.pageSize = pageSet.pageSize;
			// pageSet.pageCount||0;
			var pageNum = page.pageNum = page.getPageNum();

			var pageHtml = '<div class="page floatRight">';
			if (pageCount > 0) {
				pageHtml += "<span class='pageCount'>总共" + pageCount + "条数据</span>&nbsp;&nbsp;&nbsp;";
				var beginshow = pageNum < 8 ? 2 : (pageNum - pageNo < 3) ? (pageNum - 4) : (pageNo > 4 ? (pageNo - 2) : 2);
				var endshow = pageNum < 8 ? (pageNum - 1) : pageNo < 4 ? 5 : (pageNum - pageNo < 3 ? (pageNum - 1) : (pageNo + 2));

				pageHtml += "<a href='javascript:;' pageRel='1' "
						+ (1 == pageNo ? "class='selected'" : "") + ">1</a>"
						+ (beginshow > 2 ? "..." : "");
				if (pageNum > 2) {
					var i = beginshow;
					while (i < endshow + 1) {
						pageHtml += "<a href='javascript:;' pageRel='" + i + "' "
								+ (i == pageNo ? "class='selected'" : "") + ">"
								+ i + "</a>";
						i++;
					}
				}
				pageHtml += (pageNum - endshow > 1 ? "..." : "")
						+ (pageNum > 1 ? ("<a href='javascript:;' pageRel='" + pageNum + "' "
								+ (pageNum == pageNo ? "class='selected'" : "")
								+ ">" + pageNum + "</a>") : "")
						+ "<input/><button type='button'>跳转</button></div>";
			}
			page.Jpage = $(pageHtml);
		},
		getPageNum : function() {
			return (Math.floor(((this.pageCount || 1) - 1) / this.pageSize) + 1);
		},
		clearPage : function() {
			if (this.Jpage)
				this.Jpage.remove();
		},
		getParams : function() {
			return {
				"pageNo" : this.pageNo,
				"pageSize" : this.pageSize
			};
		},
		resetPage : function() {
			this.pageNo = 1;
			this.pageNum = this.pageCount = 0;
		}
	}

	TUI.UMtab = function(content) {
		if (typeof content == "string") {
			content = $("#" + content);
		}
		this.Jcontent = $(content);
		if (this.Jcontent.size() == 0)
			return false;
		this.JContainer, this.Jhead;
		this.init();
	};

	TUI.UMtab.prototype = {
		constructor : TUI.UMtab,
		init : function() {
			var Mtab = this;
			var Jcontent = Mtab.Jcontent;
			var Jhead = $('<div class="MtabMenu"></div>');
			var headTabHtml = "";
			Jcontent.children("div").each(function(i, n) {
				var linkId = "ui-Mtab" + i;
				headTabHtml += "<span ui-Mtab-linkTab='" + linkId + "'>" + $(n).attr("ui-Mtab-name") + "</span>";
				$(n).attr("ui-Mtab-id", linkId);
			});

			Jhead.append(headTabHtml);

			$("span[ui-Mtab-linkTab]", Jhead).on("click", function() {
				Mtab.focusTab($(this).attr("ui-Mtab-linkTab"));
			});

			Jcontent.wrap('<div class="MtabDiv"></div>');
			var JContainer = Jcontent.parent();
			Jcontent.addClass("MtabContent");
			JContainer.prepend(Jhead);
			Mtab.JContainer = JContainer;
			Mtab.Jhead = Jhead;
			Mtab.focusTab($("span:eq(0)", Jhead).attr("ui-Mtab-linkTab"));
		},
		focusTab : function(linkId) {
			var Mtab = this;
			$(".focusing", Mtab.JContainer).removeClass("focusing");
			$("[ui-Mtab-linkTab = " + linkId + "]", Mtab.Jhead).addClass("focusing");
			$("[ui-Mtab-id = " + linkId + "]", Mtab.Jcontent).addClass("focusing");
		}
	}
	UI.Dialog = function(param) {
		return new TUI.UDialog(param)
	};
	UI.NewTab = function(obj) {
		return new TUI.UTab(obj)
	};
	UI.NewTree = function(obj, param) {
		return new TUI.UTree(obj, param)
	};
	UI.NewshowLoad = function(obj) {
		TUI.loadAcx.show(obj);
	};
	UI.NewhideLoad = function(obj) {
		TUI.loadAcx.hide(obj);
	};
	UI.NewTree = function(obj, param) {
		return new TUI.UTree(obj, param)
	};
	UI.NewDatagrid = function(obj, param) {
		return new TUI.UDatagrid(obj, param)
	};
	UI.NewMtab = function(obj, param) {
		return new TUI.UMtab(obj, param)
	};
	UI.NewDialog = function(param) {
		return new TUI.UDialog(param)
	};

	jQuery.fn.extend({
		NewTab : function() {
			var returnObject;
			if (this.size() == 1) {
				returnObject = new TUI.UTab(this);
			} else {
				returnObject = [];
				this.each(function(i, n) {
					returnObject[i] = new UTab(n);
				})
			}
			return returnObject;
		},
		
		NewTree : function(param) {
			var returnObject;
			if (this.size() == 1) {
				returnObject = new TUI.UTree(this, param);
			} else {
				returnObject = [];
				this.each(function(i, n) {
					returnObject[i] = new TUI.UTree(n, param);
				})
			}
			return returnObject;
		},
		
		NewshowLoad : function() {
			TUI.loadAcx.show(this);
		},
		
		NewhideLoad : function() {
			TUI.loadAcx.hide(this);
		},
		
		NewDatagrid : function(param) {
			var returnObject;
			if (this.size() == 1) {
				returnObject = new TUI.UDatagrid(this, param);
			} else {
				returnObject = [];
				this.each(function(i, n) {
					returnObject[i] = new TUI.UDatagrid(n, param);
				})
			}
			return returnObject;
		},
		
		NewMtab : function(param) {
			var returnObject;
			if (this.size() == 1) {
				returnObject = new TUI.UMtab(this, param);
			} else {
				returnObject = [];
				this.each(function(i, n) {
					returnObject[i] = new TUI.UMtab(n, param);
				})
			}
			return returnObject;
		},
		
		NewDialog : function(param) {
			return new TUI.UDialog(param);
		}
	});

	return UI;
}();

/**
 * 功能说明:封装警告信息对话框
 * 
 * @param content 警告信息内容
 * @param width 对话框宽度,默认宽度为300
 * @param hasType 是否有类型, 默认不传值或者true表示是提示框, false表示不是提示框
 * @param title 对话框标题,默认为"Alert"
 */
window.sysAlert = function(content, width, hasType, title) {
	if (width == undefined) width = 500;
	if (hasType == undefined)hasType = true;
	
	var options = {
		allowEscapeKey:true,
		allowOutsideClick:true,
		showConfirmButton:true,
		width:width
	};
	if (title != undefined){
		options.title = title;
	}
	if (hasType){
		options.type = "warning";
		options.showCloseButton = true;
	}else{
		content = "<div align='left'>" + content + "</div>";
		options.confirmButtonColor = '#00a65a';
	}

	options.html = content;
	
	swal(options);
};

/**
 * 功能说明:封装提示信息对话框
 * 
 * @param content 提示信息内容
 * @param type 提示框类型(info/success/warning/error)
 * @param showTime 对话框显示时间,默认1000ms
 * @param onCloseFunction 对话框关闭时的回调函数
 */
window.tips = function(content, type, showTime, onCloseFunction) {
//	if (type == undefined) type = "warning";
	type = "warning";
	toastr.options = {
		positionClass:"toast-top-center",
		closeButton:false,
		debug:false,
		newestOnTop:true,
		processBar:true,
		onclick:onCloseFunction,
		showDuration:500,
		hideDuration:500,
		timeOut:showTime || 2000,
		extendedTimeOut:1000,
		showWasing:"swing",
		hideWasing:"linear",
		showMethod:"fadeIn",
		hideMethod:"fadeOut"
	};
	toastr[type](content);
};

/**
 * 功能说明:封装确认操作信息对话框
 * 
 * @param content 提示信息内容
 * @param successEvent 点击[确认]按钮执行的function
 * @param failureEvent 点击[取消]按钮执行的function
 */
window.sysConfirm = function(content, successEvent, failureEvent) {
	var options = {
		html: content,
		type: 'question',
		showCancelButton: true,
		confirmButtonText:"确定",
		cancelButtonText: "取消",
		confirmButtonClass: 'btn btn-success',
		cancelButtonClass: 'btn btn-danger',
		confirmButtonColor: '#d33',
		showCloseButton:true,
		allowEscapeKey:false,
		allowOutsideClick:false
      };
	if(successEvent){
		swal(options).then(successEvent);
	}else{
		swal(options);
	}
};


/**
 * 功能说明:封装警告信息对话框
 * 
 * @param content 警告信息内容
 * @param width 对话框宽度,默认宽度为250
 * @param height 对话框高度,默认高度为50
 * @param title 对话框标题,默认为"Alert"
 */
window.sysAlertDialog = function(content, width, height, title) {
	if (width == undefined) width = 250;
	if (height == undefined) height = 50;
	if (title == undefined) title = "Alert";
	NewUI.NewDialog({
		text : content,
		title : title,
		width : width,
		height : height,
		css : {
			"zindex" : 2018
		}
	});
};

/**
 * 功能说明:封装提示信息对话框
 * 
 * @param content 提示信息内容
 * @param showTime 对话框显示时间,默认1000ms
 * @param onCloseFunction 对话框关闭时的回调函数
 */
window.tipsDialog = function(content, showTime, onCloseFunction) {
		NewUI.NewDialog({
		text : content,
		title : "Tips",
		width : 250,	
		height : 70,
		showTime : showTime || 1000,
		onCloseFunction : onCloseFunction,
		css : {
			"zindex" : 2018
		}
	});
};

/**
 * 功能说明:封装确认操作信息对话框
 * 
 * @param content 提示信息内容
 * @param successEvent 点击[确认]按钮执行的function
 * @param failureEvent 点击[取消]按钮执行的function
 */
window.sysConfirmDialog = function(content, successEvent, failureEvent) {
	var sysConfirmDialog = NewUI.NewDialog({
		text : content, title : "Confirm", width : 350, height : 80,
		buttons : [ {
			name : "确定",
			clickEvent : function() {
				if (successEvent) successEvent();
				sysConfirmDialog.closeDialog();
			}
		}, {
			name : "取消",
			clickEvent : function() {
				if (failureEvent) failureEvent();
				sysConfirmDialog.closeDialog();
			}
		} ]
	});
};

/**
 * 功能说明:存放当前所有打开的对话框对象
 */
window.dialogList = [];

/**
 * 功能说明:存放最后打开的对话框对象
 */
window.selfDialog = function() {
	if (top.dialogList.length == 0)
		return null;
	return top.dialogList[dialogList.length - 1];
}

/**
 * 功能说明:存放当前打开的主页面对象
 */
window.parentWindow = function() {
	return top.mainTab["mainTabs"].getFocusingTab().contentWindow;
}

/**
 * 功能说明:存放当前主页面的datagrid对象
 */
window.mainDatagrid = null;

/**
 * 功能说明:关闭所有弹出的子对话框,并且刷新主页面的datagrid对象
 */
window.closeDialogAndReloadMainDatagrid = function() {
	if (top.dialogList.length >= 0){
		$.each(top.dialogList, function(i, n){
			n.close();
		});
	}
	
	if(top.mainDatagrid != null){
		top.mainDatagrid.reloadDatagrid();
	}
}
