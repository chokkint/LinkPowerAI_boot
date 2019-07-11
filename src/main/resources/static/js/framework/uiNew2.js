/**
 * 系统公用UI工具类
 */
function paramsMatter(value, row, index) {
	if (value == "null" || value == null) {
		value = "";
	}
	var values = value;
	if (values != null && typeof(values) == "String") {
		values = values.replace("/\s+\g", "&nbsp;");
	}
	return "<span title=\"" + values + "\">" + value + "</span>";
}

$(function() {
	window.NewUI2 = function() {

		var UI = {}, TUI2 = {};

		TUI2.UDatagrid = function(obj, params) {
			this.JDatagrid, this.Tablecolumn, this.headerRow, this.datagridPage, this.datagridData;
			this.obj = obj;
			this.defaultParam = {
				// method: 'post',					// 请求方式（*）
				striped : true,						// 是否显示行间隔色
				cache : false,						// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
				// pagination: true,				// 是否显示分页（*）
				sortable : true,					// 是否启用排序
				sortOrder : "asc",					// 排序方式
				// queryParams: initQueryParam,		// 传递参数（*）
				sidePagination : "server",			// 分页方式：client客户端分页，server服务端分页（*）
				// pageNumber: 1,					// 初始化加载第一页，默认第一页
				// pageSize: 10,					// 每页的记录行数（*）
				// pageList: [5,10, 25, 50, 100],	// 可供选择的每页的行数（*）
				// search:true,						// 是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
				// strictSearch: true,				// 是否精确查询
				showColumns : true,					// 是否显示所有的列
				showRefresh : true,					// 是否显示刷新按钮
				minimumCountColumns : 2,			// 最少允许的列数
				clickToSelect : true,				// 是否启用点击选中行
//				height : 520,						// 行高
				// uniqueId: "id",					// 每一行的唯一标识，一般为主键列
				showToggle : true,					// 是否显示详细视图和列表视图的切换按钮
				cardView : false,					// 是否显示详细视图
				detailView : false,					// 是否显示父子表
				showExport : true,					// 是否显示导出
				exportDataType : "selected",		// basic', 'all', 'selected'.
				exportTypes : [ "excel", "xlsx", "csv", "txt" ],
				multiple : true,
				usepager : true,
				async : false,
				rp : 10,
				nowrap : true,
				contentType : "application/x-www-form-urlencoded",
				type : "post",
				dataType : "json",
				loadMsg : "数据加载中...",
				nomsg : "没有数据",
				errormsg : "Connection Error"
			};
			this.params = $.extend(this.defaultParam, params || {});
			this.datagridAjaxParam = params.datagridAjaxParam || {};
			this.initDatagrid();
		};

		TUI2.UDatagrid.prototype = {
			constructor : TUI2.UDatagrid,
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
					return false;
				}

				// 封装列信息
				var columes = [];
				var innerCol = [];
				
				// 默认第一列添加checkbox列
				if(datagrid.params.colModel.length > 0){
					var colObj = {};
					colObj.checkbox = true;
					innerCol.push(colObj)
				}

				$.each(datagrid.params.colModel, function(i, n) {
					var colObj = {};
					if (n.name != undefined) {
						colObj.field = n.name;
					}
					if (n.displayName != undefined) {
						colObj.title = n.displayName;
					}
					if (n.width != undefined) {
						colObj.width = n.width;
					}
					if (n.align != undefined) {
						colObj.align = n.align;
					}
					if (n.sortable != undefined) {
						colObj.sortable = n.sortable;
					}
					if (n.order != undefined) {
						colObj.order = n.order;
					}
					if (n.checkbox != undefined) {
						colObj.checkbox = n.checkbox;
					}
					if (n.visible != undefined) {
						colObj.visible = n.visible;
					}

					// 设置每个单元格数据格式以及单元格鼠标停留事件
					if (n.checkbox == undefined) {
						colObj.class = "tableColStyle";
						colObj.formatter = "paramsMatter";
					}

					innerCol.push(colObj)
				});
				
				columes.push(innerCol);
				params.columns = columes;

				// 封装是否使用分页
				if (params.usepager) {
					params.pagination = true;
					if (params.pageSize == undefined) {
						params.pageSize = 10;
					}

					if (params.pageList == undefined) {
						params.pageList = [10, 30, 50];
					}
				}

				// 封装是否支持多选
				if (params.multiple) {
					params.singleSelect = false;
				} else {
					params.singleSelect = true;
				}

				// 封装ajax请求方式
				if (params.type) {
					params.method = params.type;
				}

				// 封装查询条件
				params.queryParams = function(pageParams) {
					var temp = params.initQueryParam || {};
					temp.rows = "" + (pageParams.limit);
					temp.page = "" + ((pageParams.offset / pageParams.limit) + 1);
					temp.pageSize = "" + (pageParams.limit);
					temp.pageNo = "" + ((pageParams.offset / pageParams.limit) + 1);
					return $.extend(temp, pageParams || {});
				};

				// 封装数据处理操作
				if (params.onSuccessEvent) {
					params.onLoadSuccess = function(jsonResult) {
						if (!jsonResult) {
							return;
						}
						if ((jsonResult[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SESSION_TIMEOUT)) {
							tips("登陆已经过期，即将重新登陆", "warning", 3000, function() {
								 top.location = getContextPath() + "/jsp/login";
								// window.location.reload();
							});
							jsonResult.rows = [];
							return jsonResult;
						} else if ((jsonResult.errorCode == 1)) {
							sysAlert(jsonResult.errorMessage);
							jsonResult.rows = [];
							return jsonResult;
						}
						if (jsonResult) {
							if (params.onSuccessEvent && typeof params.onSuccessEvent == "function") {
								params.onSuccessEvent(jsonResult);
								datagrid.$table.bootstrapTable("load", jsonResult);
							}
						} else {
							tips("no result");
						}

						return jsonResult;
					};
				}

				datagrid.params = params;

				// SessionAlive Check
				var result = datagrid.checkSessionTimeOut(datagrid.params);
				datagrid.generateGrid(datagrid.params);

			},
			generateGrid : function(datagridAjaxParam) {
				var datagrid = this;
				datagrid.$table = datagrid.obj.bootstrapTable(datagridAjaxParam);
			},
			reloadDatagrid : function(data) {
				var datagrid = this;
				// SessionAlive Check
				var result = datagrid.checkSessionTimeOut(datagrid.params);
				var refreshParam = {};
				if (data != undefined && data != null) {
					if (typeof data != "string" || data.indexOf("/") == -1) {
						datagrid.params.queryParams = function(pageParams) {
							var temp = data || {};
							temp.rows = "" + (pageParams.limit);
							temp.page = "" + ((pageParams.offset / pageParams.limit) + 1);
							temp.pageSize = "" + (pageParams.limit);
							temp.pageNo = "" + ((pageParams.offset / pageParams.limit) + 1);
							return $.extend(temp, pageParams || {});
						};
						datagrid.params.initQueryParam = $.extend(datagrid.params.initQueryParam, data || {});
						refreshParam.query = data;
					} else {
						datagrid.params.url = data;
						refreshParam.url = data;
					}
				}
				datagrid.$table.bootstrapTable("refresh");
				datagrid.$table.bootstrapTable("selectPage", 1);
			},
			getSelectedRows : function() {
				var datagrid = this;
				var selectedRows = datagrid.$table.bootstrapTable("getSelections");
				return selectedRows;
			},
			checkSessionTimeOut : function(params) {
				var result;
				$.ajax({
					url : getContextPath() + "/isSessionAlive",
					cache : false,
					async : params.async,
					type : params.type,
					data : params.data,
					dataType : params.dataType,
					complete : function(XMLHttpRequest, textStatus) {
						var sessionstatus = XMLHttpRequest.getResponseHeader("errorCode"); // 通过XMLHttpRequest取得响应头,sessionstatus，
						var url = XMLHttpRequest.getResponseHeader("url");
						if (sessionstatus == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SESSION_TIMEOUT) {
							tips("登陆已经过期，即将重新登陆", "warning", 3000, function() {
								top.location = getContextPath() + "/jsp/login";
							});
						}
					}
				});
				return result;
			},
			selectAllRows : function() {
				var datagrid = this;
				datagrid.$table.bootstrapTable("checkAll");
			},
			unSelectAllRows : function() {
				var datagrid = this;
				datagrid.$table.bootstrapTable("uncheckAll");
			}
		};

		UI.NewDatagrid = function(obj, param) {
			return new TUI2.UDatagrid(obj, param)
		};

		jQuery.fn.extend({
			NewDatagrid : function(param) {
				var returnObject;
				if (this.size() == 1) {
					returnObject = new TUI2.UDatagrid(this, param);
				} else {
					returnObject = [];
					this.each(function(i, n) {
						returnObject[i] = new TUI2.UDatagrid(n, param);
					})
				}
				return returnObject;
			}
		});
		return UI;
	}();
});
