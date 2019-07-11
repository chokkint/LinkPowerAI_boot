/**
 * 系统公用UTILS工具类
 */
window.NewUtils = {
	/**
	 * 功能说明:封装Ajax调用方法,需要注意一下几点:<br>
	 * <br>
	 * 1、第一个参数必须为Ajax的url,类型为String,其余的参数没有固定顺序,程序根据参数类型自动封装<br>
	 * 2、数据参数:data,可以是"param1=###&param2=###"形式,也可以是{param1:####,param2:####}形式<br>
	 * 3、数据返回格式:dataType,类型为String,值只能为"json","text","html","xml"<br>
	 * 4、传输方式:type,类型为String,值只能为"post","get"<br>
	 * 5、执行方式:async,类型为Boolean,值只能为true(异步执行),false(同步执行)<br>
	 * 6、执行成功后的回调函数:onsuccess,类型为function,参数为Ajax返回结果<br>
	 */
	JAjax : function() {
		//默认参数,调用是不传该项目时采用当前默认值
		var result, params = {
			async : false,
			type : "POST",
			data : "",
			dataType : "json"
		};
		// url, data, dataType, type, async, onsuccess;
		if ($.type(arguments[0]) == "object" && arguments[0].url != "") {
			params = $.extend({}, params, arguments[0]);
		}
		
		//封装Ajax参数
		for (var i = 0; i < arguments.length; i++) {
			var param = arguments[i];
			if (i == 0) {
				params.url = param;
			} else if ($.type(param) == "string" && param != "") {
				var lowerParam = param.toString().toLowerCase();
				if (lowerParam == "post" || lowerParam == "get") {
					params.type = param;
				} else if (lowerParam == "text" || lowerParam == "json"
						|| lowerParam == "html" || lowerParam == "xml") {
					params.dataType = param;
				} else {
					params.data = param;
				}
			} else if ($.type(param) == "boolean") {
				params.async = param;
			} else if ($.type(param) == "function") {
				if (!params.onsuccess) {
					params.onsuccess = param;
				} else {
					params.onfailure = param;
				}
			} else if ($.type(param) == "object") {
				params.data = param;
			}
		}

		//调用Ajax请求后台程序
		$.ajax({
			url : params.url,
			cache : false,
			async : params.async,
			type : params.type,
			data : params.data,
			dataType : params.dataType,
			complete : function(XMLHttpRequest, textStatus) {
				// 通过XMLHttpRequest取得响应头sessionstatus
				var sessionstatus = XMLHttpRequest.getResponseHeader("errorCode");
				var url = XMLHttpRequest.getResponseHeader("url");
				
				// 如果超时就处理 ，指定要跳转的页面
				if (sessionstatus == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SESSION_TIMEOUT) {
					tips("登陆已经过期，即将重新登陆!", "warning", 3000, function() {
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
					if (params.onsuccess && $.type(params.onsuccess) == "function")
						params.onsuccess(jsonResult);
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
	getContextPath : function() {
		var pathName = document.location.pathname;
		var index = pathName.substr(1).indexOf("/");
		var result = pathName.substr(0, index + 1);
		return result;
	},
	
	initFormElementsClass : function(dialog) {
		// 统一处理整个系统中的button样式
		$("button[type='button']", dialog).each(function(i, n) {
			$(n).addClass("btn btn-success btn-block btn-flat");
		});
		
		$(":text[name],:password[name],select[name],textarea[name],date[name]", dialog).each(function(i, n) {
			$(n).addClass("form-control");
		});
		
		$(":text[id],:password[id],select[id],textarea[id],date[id]", dialog).each(function(i, n) {
			$(n).addClass("form-control");
		});
		
		// 统一为整个系统的下拉列表增加bootstrap-select样式
		$("select[name],select[id]", dialog).each(function(i, n) {
			$(n).addClass("selectpicker");
			$(n).attr("data-live-search", "true");
			$(n).selectpicker({noneSelectedText:"请选择"});
			$(n).selectpicker("refresh");
		});
		
		// 处理所有日期表单
		$("input[alisaType='date']", dialog).each(function(i, n) {
			$(n).addClass("datepicker");
			$(n).datepicker({
				language: "zh-CN",
	            autoclose: true,//选中之后自动隐藏日期选择框
	            format: "yyyy-mm-dd"//日期格式
			});
		});
	},
	
	/**
	 * 功能说明:获取当前所选行信息数组<br>
	 * <br>
	 * @param datagrid 当前datagrid对象<br>
	 * @returns datagrid选中的记录数据,类型为object<br>
	 */
	getSelectedRows : function(datagrid) {
		var selectedRows = datagrid.datagrid("getSelections");
		return selectedRows;
	},

	/**
	 * 功能说明:重置form表单<br>
	 * <br>
	 * @param formId 需要重置的form表单的id<br>
	 */
	clearForm : function(formId) {
		$("#" + formId)[0].reset();
	},
	
	/**
	 * 功能说明:返回对象arrayObj中的column属性的值组成数组<br>
	 * <br>
	 * @param arrayObj 数据对象<br>
	 * @param column 对象中需要抽取值的属性名称<br>
	 * @returns {Array} 抽取到的值组成的数组<br>
	 */
	ObjectArray2ParamArray : function(arrayObj, column) {
		var result = [];
		if (!!!arrayObj)
			return result;
		for (var i = 0; i < arrayObj.length; i++) {
			var n = arrayObj[i];
			result.push(n[column]);
		}
		return result;
	},
	
	/**
	 * 功能说明:返回对象arrayObj中的多个列columnArray对应的值用{ConstantNew.SPLIT_STR}连接组成数组<br>
	 * <br>
	 * @param arrayObj 数据对象<br>
	 * @param columnArray 对象中需要抽取值的属性名称数组<br>
	 * @returns {Array} 抽取到的值组成的数组<br>
	 */
	ObjectMultipleColumns2ParamArray : function(arrayObj, columnArray) {
		var result = [];
		if (!!!arrayObj)
			return result;
		if ((!!!columnArray) || !(columnArray instanceof Array))
			return result;
		for (var i = 0; i < arrayObj.length; i++) {
			var n = arrayObj[i];
			var str = "";
			for (var j = 0; j < columnArray.length; j++) {
				str += n[columnArray[j]];
				if (j != columnArray.length - 1) {
					str += ConstantNew.SPLIT_STR;
				}
			}
			result.push(str);
		}
		return result;
	},
	
	/**
	 * 功能说明:通过form表单里面的对象值生成url参数用的data,对特殊字符进行转义<br>
	 * <br>
	 * @param form form表单对像<br>
	 * @returns {String} 处理后的url参数信息,格式为A=a&B=b&C=c<br>
	 */
	proccessForm : function(form) {
		var dataparam = "";
		$(":text[name],:password[name],:checkbox[name]:checked,:radio[name]:checked,select[name],:hidden[name],textarea[name]", form).each(function(i, n) {
			if (n.value != "") {
				var newValue = n.value;
				
				// 如果数据中有%，替换成%25防止数据被URL自动切断
				if (newValue && newValue.indexOf("\%") > -1) {
					newValue = newValue.replace(/\%/g, "\%25");
				}
				// 如果数据中有+，替换成%2B防止数据被URL自动切断
				if (newValue && newValue.indexOf("\+") > -1) {
					newValue = newValue.replace(/\+/g, "\%2B");
				}
				// 如果数据中有&，替换成%26防止数据被URL自动切断
				if (newValue && newValue.indexOf("\&") > -1) {
					newValue = newValue.replace(/\&/g, "\%26");
				}
				// 如果数据中有#，替换成%23防止数据被URL自动切断
				if (newValue && newValue.indexOf("\#") > -1) {
					newValue = newValue.replace(/\#/g, "\%23");
				}
				// 如果数据中有=，替换成%3D防止数据被URL自动切断
				if (newValue && newValue.indexOf("\=") > -1) {
					newValue = newValue.replace(/\=/g, "\%3D");
				}
				// 如果数据中有空格，替换成%20防止数据被URL自动切断
				if (newValue && newValue.indexOf(" ") > -1) {
					newValue = newValue.replace(/\s/g, "\%20");
				}
				
				dataparam += (i == 0 ? "" : "&") + n.name + "=" + newValue;
			}
		});
		return dataparam;
	},
	
	/**
	 * 功能说明:通过form表单里面的对象值生成object<br>
	 * <br>
	 * @param form form表单对象<br>
	 * @param hasEmpty 是否将form表单中的空值也生成到对象中(true or false)<br>
	 * @returns dataparam 生成的数据object<br>
	 */
	parseForm : function(form, hasEmpty) {
		hasEmpty = hasEmpty == undefined ? false : hasEmpty;
		var dataparam = {};
		$(":text[name],:password[name],:checkbox[name]:checked,:radio[name]:checked,select[name],:hidden[name],textarea[name]", form).each(function(i, n) {
			if (hasEmpty || n.value != ""){
				dataparam[n.name] = n.value;
			}
		});
		return dataparam;
	},

	/**
	 * 功能说明:通过data对象里面的数据填充form表单里面的对象值,data中的属性值自动填充form表单中相同name值对象的值<br>
	 * <br>
	 * @param Jform form表单对象<br>
	 * @param data 数据对象<br>
	 */
	initFormByData : function(Jform, data) {
		$.each(data, function(i, n) {
			var str = $("[name='" + i + "']", Jform).attr("type");
			if (str != "radio" && str != "checkbox") {
				$("[name='" + i + "']", Jform).val(n);
			} else {
				initRadio(i, Jform, n);
			}
		});
	},
	//为模态框填充form增加以下方法(20181120)
	serializeObject: function(form) {
		var formEL = $(form);
		var o = {};
		var a = formEL.serializeArray();
		$.each(a, function() {
			if(o[this.name]) {
				if(!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	},

	fillFormData: function(form, obj, isStatus) {
		var formEL = $(form);
		$.each(obj, function (index, item) {
			if(formEL.find("[name=" + index + "]").attr("multiple") != undefined){
				item = item == null ? "" : item;
				formEL.find("[name=" + index + "]").selectpicker('val', item.split(','));
			}else{
				formEL.find("[name=" + index + "]").val(item);
			}
            formEL.find("[name=" + index + "]").selectpicker('refresh');
        });
	},
	empty: function(data) {
		if(null == data || "" == data) return true;
		else return false;
	},
	/**
	 * 功能说明:弹出增删改功能对应的信息框，并默认生成[提交]和[关闭]两个按钮<br>
	 * <br>
	 * @param params 传入的参数信息,类型为Object,对象中属性含义如下<br>
	 *               html:需要弹出对话框中包含的HTML内容(类型为字符串),<br>
	 *               title:弹出对话框的标题信息(类型为字符串),<br>
	 *               buttons:弹出对话框中包含的操作按钮以及对应的处理逻辑(类型为Object),<br>
	 *               ajaxUrl:默认[提交]按钮调用ajax对应的url(类型为字符串),<br>
	 *               buttonName1:默认按钮中第一个按钮的名称(类型为字符串),<br>
	 *               buttonName2:默认按钮中第二个按钮的名称(类型为字符串),<br>
	 *               height:弹出对话框的高度(类型为数字),<br>
	 *               width:弹出对话框的宽度(类型为数字),<br>
	 *               initFunction:弹出对话框之后的初始化操作(类型为function),<br>
	 *               beforeSubmit:提交之前需要做的逻辑操作(类型为function),<br>
	 *               submitSuccess:提交成功之后需要做的操作(类型为function),<br>
	 *               submitFailure:提交失败之后需要做的操作(类型为function),<br>
	 *               submitFunction:提交之后根据返回值需要做的操作(类型为function),<br>
	 */
	dialogFunction : function(params) {
		
		var iscommit = false;
		var dialogParam = {
			html : params.html,
			title : params.title,
			isMatte : true
		};
		
		// 判断前台是否自定义了对话框按钮及其操作
		if (params.buttons) {
			var buttonsParam = [];
			$.each(params.buttons, function(i, n) {
				var buttonObj = {};
				buttonObj.name = n.name;
				// 判断该按钮操作完成后是否需要关闭当前对话框
				if (n.isClose && n.isClose == true) {
					buttonObj.clickEvent = function() {
						n.clickEvent(params.dialog);
					}
				} else {
					buttonObj.clickEvent = n.clickEvent;
				}
				buttonsParam.push(buttonObj);
			});
			dialogParam.buttons = buttonsParam;
			if (params.hasCloseButton)
				dialogParam.hasCloseButton = params.hasCloseButton;
		} else {
			// 默认按钮及其操作
			dialogParam.buttons = [
			    {
					name : params.buttonName1 || "提交",
					clickEvent : function(obj) {
						if (!$('.dialog-form-one,.dialog-form-two', params.dialog.Jdialog).valid()){
							return false;
						}
						if (iscommit) return;
						if ($.isFunction(params.beforeSubmit)) {
							if (!params.beforeSubmit(params.dialog.Jdialog)) return false;
						}
						iscommit = true;
						NewUtils.JAjax(params.ajaxUrl, true, NewUtils.proccessForm($("form", obj)), function(result) {

							if (result[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SUCCESS) {
								tips("提交成功!", "success");
								if (typeof params.submitSuccess == "function") {
									if ($.isFunction(params.submitSuccess)){
										params.submitSuccess(params.dialog.Jdialog);
									}
								}
								params.dialog.close();
							}else{
								if ($.isFunction(params.submitFunction)){
									params.submitFunction(result, params.dialog);
								}
							}
							
							iscommit = false;
						},
						function() {
							if ($.isFunction(params.submitFailure)){
								params.submitFailure(null,params.dialog.Jdialog);
								iscommit = false;
							}
						});
						
					}
				},{
					name : params.buttonName2 || "取消",
					clickEvent : function() {
					params.dialog.close();
				}
			}];
		}
		
		if (params.height){
			dialogParam.height = params.height;
		}
		if (params.width){
			dialogParam.width = params.width;
		}
		params.dialog = top.NewUI.Dialog(dialogParam);
		
		$('.dialog-form-one,.dialog-form-two', params.dialog.Jdialog).tooltip().validate();
		if (typeof params.initFunction == "function") {
			params.initFunction(params.dialog.Jdialog);
		}
	},
	
	/**
	 * 功能说明:弹出选取数据对话框<br>
	 * <br>
	 * @param pageUrl 弹出的子页面对话框url<br>
	 * @param titleName 弹出的自i页面对话框标题信息<br>
	 * @param onConfirm 点击[确认]按钮后执行的回调函数<br>
	 * @param dataParams 传入子页面的参数信息<br>
	 * @param moreLines 是否多选(true or false)<br>
	 */
	selectDialog:function(type, onConfirm, dataParams, moreLines, dialogParams){
		moreLines = moreLines==undefined||moreLines==null?false:moreLines;
		var path = {
			"CUSTOMER_LASTED":"/jsp/utils/odsLastedCustomerInfoSelect",
			"CUSTOMER_LAST_MONTH":"/jsp/utils/odsLastMonthCustomerInfoSelect",
			"INVOICE_DETAIL" : "/jsp/utils/invoiceDetailSelect",
			"INVOICE_DETAIL_4_NEGATIVE" : "/jsp/utils/invoiceDetail4NegativeSelect"
		}[type];
		var title = {
			"CUSTOMER_LASTED":"客户信息",
			"CUSTOMER_LAST_MONTH":"客户信息",
			"INVOICE_DETAIL" : "未开票流水信息",
			"INVOICE_DETAIL_4_NEGATIVE" : "与负值交易对冲的原交易信息"
		}[type];
		var defaultHeight = {
				"CUSTOMER_LASTED":550,
				"CUSTOMER_LAST_MONTH":550,
				"INVOICE_DETAIL" : 630,
				"INVOICE_DETAIL_4_NEGATIVE" : 630
			}[type];
		
		if(dataParams!=null&&dataParams!=undefined&&$.type(dataParams)!="string"){
			dataParams = "?"+$.param(dataParams);
		} else{
			dataParams="";
		}
		
		params = {
			src:(getContextPath()+path+dataParams),
			title:("选择"+title),
			hasCloseButton:true,
			isMatte:true,
			buttons:[
			   {name:"确定",clickEvent:function(obj){
				   	if(obj.datagrid){
						var getSelectedw =obj.datagrid.getSelectedRows();
						if(getSelectedw.length==0||(!moreLines&&getSelectedw.length>1)){
							tips("请选择一行数据");
							return;
						}
						if($.isFunction(onConfirm)){
							onConfirm(getSelectedw);
						}
					}else if(obj.zTree){
						var treeData = obj.zTree.getSelectedNodes();
						if(treeData.length==0||(!moreLines&&treeData.length>1)){
							tips("请选择数据");
							return;
						}
						if($.isFunction(onConfirm)){
							onConfirm(treeData);
						}
					}else if(obj.zTreeSelect){
						var treeData = obj.zTreeSelect.getCheckedNodes(true);
						if(treeData.length==0){
							tips("请选择数据");
							return;
						}
						if($.isFunction(onConfirm)){
							onConfirm(treeData);
						}
					}
				   	dialog.close();
			   	}
		   }]	
		};
		
		if(dialogParams!=null&&dialogParams!=undefined&&$.type(dialogParams)!="string"){
			if(dialogParams.width!=null&&dialogParams.width!=undefined){
				params.width = dialogParams.width;
			}
			if(dialogParams.height!=null&&dialogParams.height!=undefined){
				params.height = dialogParams.height;
			}else{
				params.height = defaultHeight;
			}
		}else{
			params.height = defaultHeight;
		}
		
		var dialog = top.NewUI.Dialog(params);
	},
	selectDialog2 : function(pageUrl, titleName, onConfirm, params, moreLines) {
		moreLines = moreLines == undefined || moreLines == null ? false : moreLines;
		if (params != null && params != undefined && $.type(params) != "string"){
			params = "?" + $.param(params);
		} else{
			params = "";
		}
		
		var dialog = top.NewUI.Dialog({
			src : (getContextPath() + pageUrl + params),
			title : ("选择" + titleName),
			height : "480",
			hasCloseButton : true,
			isMatte : true,
			buttons : [ {
				name : "确定",
				clickEvent : function(obj) {
					if (obj.datagrid) {
						var getSelectedw = obj.datagrid.getSelectedRows();
						if (getSelectedw.length == 0 || (!moreLines && getSelectedw.length > 1)) {
							tips("请选择一行数据");
							return;
						}
						if ($.isFunction(onConfirm)) {
							onConfirm(getSelectedw);
						}
					} else if (obj.zTree) {
						var treeData = obj.zTree.getSelectedNodes();
						if (treeData.length == 0 || (!moreLines && treeData.length > 1)) {
							tips("请选择一条数据");
							return;
						}
						if ($.isFunction(onConfirm)) {
							onConfirm(treeData);
						}
					} else if (obj.zTreeSelect) {
						var treeData = obj.zTreeSelect.getCheckedNodes(true);
						if (treeData.length == 0) {
							tips("请选择一条数据");
							return;
						}
						if ($.isFunction(onConfirm)) {
							onConfirm(treeData);
						}
					}
					dialog.close();
				}
			} ]
		});
	},
	
	/**
	 * 转换array对象为object对象
	 * arrayObj:array对象
	 * column:array对象中每条的唯一值字段
	 * num:对象中每条数据添加属性，每条数据在array的序列，字段名无参为“num”，有参数即为设置的string
	*/
	array2Object:function(arrayObj,column,num){
		var result = {};
		if(!!!arrayObj)return result;
		for(var i =0; i < arrayObj.length; i++){
		    var n = arrayObj[i];
		    if(n&&n[column]){
		    	n[num||"num"] = i;
		    	result[n[column]] = n;
		    }
		}
		return result;
	},

	/**
	 * 功能说明:根据传入的数据对象和列名称生成下拉列表<br>
	 * <br>
	 * @param selectObj 需要生成下拉列表的JQuery对象<br>
	 * @param data 数据对象,类型为Object<br>
	 * @param NameCoulmn 下拉列表显示值对应的对象属性名称<br>
	 * @param valueCoulmn 下拉列表实际值对应的对象属性名称<br>
	 * @param hasEmptyOption 是否在信息列表开头生成空值,值为true或者false<br>
	 * @param hasMerge 是否将NameCoulmn和valueCoulmn对应的属性值拼接作为下拉列表显示值<br>
	 * @returns {Boolean}<br>
	 */
	generatorSelect : function(selectObj, data, NameCoulmn, valueCoulmn, hasEmptyOption, hasMerge) {
		
		var hasEmptyOption = hasEmptyOption == undefined ? true : hasEmptyOption;
		var selectObj = $(selectObj);
		if (selectObj.size() != 1) {
			return false;
		}
		
		var generatorHtml = "";
		if (hasEmptyOption) generatorHtml += "<option value=''></option>";
		$.each(data, function(i, n) {
			if (hasMerge == "Merge") {
				generatorHtml += "<option value='" + n[valueCoulmn] + "'>" + n[NameCoulmn] + "_" + n[valueCoulmn] + "</option>";
			} else {
				generatorHtml += "<option value='" + n[valueCoulmn] + "'>" + n[NameCoulmn] + "</option>";
			}
		});
		selectObj.html(generatorHtml);
		selectObj.selectpicker("refresh");
	},
	
	/**
	 * 功能说明:字符串比较<br>
	 * <br>
	 * @param selectObj 需要生成下拉列表的JQuery对象<br>
	 * @param data 数据对象,类型为Object<br>
	 * @param NameCoulmn 下拉列表显示值对应的对象属性名称<br>
	 * @param valueCoulmn 下拉列表实际值对应的对象属性名称<br>
	 * @param hasEmptyOption 是否在信息列表开头生成空值,值为true或者false<br>
	 * @param hasMerge 是否将NameCoulmn和valueCoulmn对应的属性值拼接作为下拉列表显示值<br>
	 * @returns {Boolean}<br>
	 */	
    compare: function (input, str, exact) {
        return input !== undefined && (exact ? input === str : input.match(str));
    },
};

/**
 * 功能说明:重构JQuery的clone方法,修复应用于textarea和select值丢失的bug<br>
 */
(function(original) {
	jQuery.fn.clone = function() {
		var result = original.apply(this, arguments),
		my_textareas = this.find('textarea').add(this.filter('textarea')),
		result_textareas = result.find('textarea').add(result.filter('textarea')),
		my_selects = this.find('select').add(this.filter('select')),
		result_selects = result.find('select').add(result.filter('select'));

		for (var i = 0, l = my_textareas.length; i < l; ++i){
			$(result_textareas[i]).val($(my_textareas[i]).val());
		}
		
		for (var i = 0, l = my_selects.length; i < l; ++i){
			result_selects[i].selectedIndex = my_selects[i].selectedIndex;
		}
			
		return result;
	};
})(jQuery.fn.clone);

/**
 * 功能说明:通过data对象里面的数据填充form表单里面的radio值<br>
 * <br>
 * 例子：data:{name:"test",desc:"test"},表单对象form1<br>
 * 执行后，form1中name为name1的对象值为test,form1中name为desc的对象值为test<br>
 */
function initRadio(rName, rDialog, rValue) {
	$("[name=" + rName + "]", rDialog).removeAttr("checked");
	$("[name=" + rName + "]", rDialog).each(function() {
		if ($(this).val() == rValue) {
			$(this).prop('checked', true);
		}
	});
};

/*
function mergeObject() {
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
}

function arrayHasParam(arrayObj, childrenName) {
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
}

function array2Object(arrayObj, column, childrenName) {
	var result = {};
	if (!!!arrayObj)
		return result;
	for (var i = 0; i < arrayObj.length; i++) {
		var n = arrayObj[i];
		if (childrenName && n[childrenName]) {
			n = mergeObject(n, array2Object(n[childrenName], column,
					childrenName));
		}
		if (n && n[column]) {
			result[n[column]] = n;
		}
	}
	return result;
}

function ObjectTranslateTree(datalist, topId, idName, pidName, childrenName) {
	if (!!!datalist || datalist.length == 0)
		return [];
	var objectForObj = [];
	var listForObj = array2Object(datalist, idName || "id", childrenName);

	if (arrayHasParam(datalist, childrenName)) {
		objectForObj = datalist;
	} else {
		if (topId) {
			objectForObj = initObjectForObj(listForObj, topId, pidName);
		} else {
			var k = 0;
			$.each(listForObj, function(i, n) {
				if (n.pid == undefined || !listForObj[n.pid]) {
					objectForObj[k] = n;
					var result = initObjectForObj(listForObj, i, pidName);
					if (result && result.length > 0)
						objectForObj[k].children = result;
					k++;
				}
			});
		}
	}
	return [ objectForObj, listForObj ];
}

function initObjectForObj(obj, pid, pidName) {
	var objectForObj = [];
	var k = 0;
	$.each(obj, function(i, n) {
		if (n[pidName || "pid"] == pid) {
			objectForObj[k] = n;
			delete obj[i];
			var result = initObjectForObj(obj, i, pidName);
			if (result && result.length > 0)
				objectForObj[k].children = result;
			k++;
		}
	});
	return objectForObj;
}
*/

/**
 * 功能说明:构造字符串的trim方法,去除字符串左边和右边的空格
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * 功能说明:构造字符串的Ltrim方法,去除字符串左边的空格<br>
 */
String.prototype.Ltrim = function() {
	return this.replace(/(^\s*)/g, "");
};

/**
 * 功能说明:构造字符串的Rtrim方法,去除字符串右边的空格<br>
 */
String.prototype.Rtrim = function() {
	return this.replace(/(\s*$)/g, "");
};

/**
 * 功能说明:构造数组的indexOf()方法,返回数组中包含某元素的下标<br>
 * <br>
 * @param {Object} elt:要查询的元素, from:开始查询的起始下标<br>
 * @return 数组中包含某元素的下标<br>
 */
if (!Array.prototype.indexOf) {
	Array.prototype.indexOf = function(elt /* , from */) {
		var len = this.length >>> 0;

		var from = Number(arguments[1]) || 0;
		from = (from < 0) ? Math.ceil(from) : Math.floor(from);
		if (from < 0)
			from += len;

		for (; from < len; from++) {
			if (from in this && this[from] === elt)
				return from;
		}
		return -1;
	};
};