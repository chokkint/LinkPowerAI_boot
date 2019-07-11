function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

window.CommonUtils = {
	/**
	 * 功能：判断当前业务，当前分行，当前数据时间的数据是否已经被确认或者超出补录延迟时间
	 * @param {String} bizCode 业务代码
	 * @param {String} branch  分行
	 * @param {Date} dtDate    数据时间
	 * @param {Boolean} confirmOnly  ture：只判断数据是否已经被确认过 false或者不传该参数：先判断数据是否已经被确认，再判断是否超出补录延迟时间
	 * @return {boolean} true：已经被确认或者超过延迟时间，不可操作， false：没有被确认并且没有超过延迟时间，可操作
	 */
	isConfirmedOrDelayed:function(bizCode, branch, dtDate, confirmOnly){
		var param = {};
		var result = false;
		param.bizCode = bizCode;
		param.branch = branch;
		param.dtDate = dtDate;
		NewUtils.JAjax(getContextPath()+"/isAbleToRecord",param,"json",function(prepareResult){
			if(prepareResult.errorCode &&  prepareResult.errorCode == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_ERROR){
				result = true;
				sysAlert(prepareResult.errorMessage);
			}else{
				NewUtilsJAjax(getContextPath()+"/isConfirmed",param,function(confirmedResult){
					if(confirmedResult == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SUCCESS){
						result = true;
						sysAlert("该数据日期下当前操作分行当前业务的数据已经被确认,不能操作!");
					}else{
						if(confirmOnly==undefined || confirmOnly==false){
							NewUtilsJAjax(getContextPath()+"/isOutOfDelayDays",param,function(delayedResult){
								if(delayedResult == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SUCCESS){
									result = true;
									sysAlert("当前时间已经超出补录延迟时间,不能进行补录!");
								}
							});
						}
					}
				});
			}
		});
		return result;
	},
	
	/**
	 * 功能：检查所选择的记录状态是否可以编辑
	 * @param {Array} getSelectedw
	 * @return {boolean} true:可编辑， false:不可编辑
	 */
	checkRecordStatus:function(getSelectedw){
		var result = true;
		var errMsg = "";
		for(var i = 0; i < getSelectedw.length; i++){
			if (getSelectedw[i].recStatus == 2 || getSelectedw[i].recStatus == 3) {
				errMsg = "所选记录中包含状态为["+ConstantNew.REC_STATUS_OBJECT[getSelectedw[i].recStatus]+"]的记录,不能执行当前操作!";
				result = false;
				sysAlert(errMsg);
				break;
			}
		}
		return result;
	},
	
	/**
	 * 功能：获取上季度的季末日期
	 * @return {String} 季末日期，格式为YYYY-MM-DD
	 */
	getEndDayOfLastSeason:function (strDate){
		var date = new Date();
		if(strDate){
			if(strDate.length == 10){
				var strDate2 = strDate.replace(/-/g,"/");
				date = new Date(Date.parse(strDate2));
			}else{
				sysAlert("参数格式错误，应该为：YYYY-MM-DD");
				return "error";
			}
		}
		var nowYear = date.getFullYear();
		var nowMonth =date.getMonth()+1;
		
		if(nowMonth >= 1 && nowMonth <= 3){
			return ''+(nowYear-1)+'-12-31';
		}else if(nowMonth >= 4 && nowMonth <= 6){
			return ''+nowYear+'-3-31';
		}else if(nowMonth >= 7 && nowMonth <= 9){
			return ''+nowYear+'-6-30';
		}else if(nowMonth >= 10 && nowMonth <= 12){
			return ''+nowYear+'-9-30';
		}
	},

	/**
	 * 功能：获取指定时间的月末日期，如果没有参数，则返回当前月份的月末日期
	 * @param {String} strDate 格式：YYYY-MM-DD
	 * @return {String} 月末日期，格式：YYYY-MM-DD
	 */
	getEndDayOfMonth:function (strDate){
		var date = new Date();
		if(strDate){
			if(strDate.length == 10){
				var strDate2 = strDate.replace(/-/g,"/");
				date = new Date(Date.parse(strDate2));
			}else{
				sysAlert("参数格式错误，应该为：YYYY-MM-DD");
				return "error";
			}
		}
		date.setDate(1);  
        date.setMonth(date.getMonth()+1);  
        cdt = new Date(date.getTime()-1000*60*60*24);
        
        var nowMonth =cdt.getMonth()+1;
		var nowdate = cdt.getDate();
		nowMonth = (nowMonth<10)?("0"+nowMonth):nowMonth;
		nowdate = (nowdate<10)?("0"+nowdate):nowdate;
		return ""+cdt.getFullYear()+"-"+nowMonth+ "-" + nowdate;
	},
	/**
	 * 功能：获取指定时间的上个月末日期，如果没有参数，则返回当前系统日期上个月份的月末日期
	 * @param {String} strDate 格式：YYYY-MM-DD
	 * @return {String} 月末日期，格式：YYYY-MM-DD
	 */
	getEndDayOfLastMonth:function (strDate){
		var date = new Date();
		if(strDate){
			if(strDate.length == 10){
				var strDate2 = strDate.replace(/-/g,"/");
				date = new Date(Date.parse(strDate2));
			}else{
				sysAlert("参数格式错误，应该为：YYYY-MM-DD");
				return "error";
			}
		}
		date.setDate(1);  
        cdt = new Date(date.getTime()-1000*60*60*24);
        
        var nowMonth =cdt.getMonth()+1;
		var nowdate = cdt.getDate();
		nowMonth = (nowMonth<10)?("0"+nowMonth):nowMonth;
		nowdate = (nowdate<10)?("0"+nowdate):nowdate;
		return ""+cdt.getFullYear()+"-"+nowMonth+ "-" + nowdate;
	},
	
	/**
	 * 功能:比较两个日期的大小
	 * @param {String} strDate1
	 * @param {String} strDate2
	 * @return {int} -1:小于, 0:等于, 1:大于
	 */
	compareDate:function(strDate1, strDate2){
		
		strDate1 = strDate1.replace(/-/g,"/");
		strDate2 = strDate2.replace(/-/g,"/");
		sDate = new Date(Date.parse(strDate1));
		eDate = new Date(Date.parse(strDate2));
		if(sDate.getTime() > eDate.getTime()){
			return 1;
		}else if(sDate.getTime() == eDate.getTime()){
			return 0;
		}else{
			return -1;
		}
	},
	
	
	/**
	 * 功能:格式化日期为'YYYY-MM-DD'格式
	 * @param {Date} date
	 * @return {String} strDate
	 */
	formatDate:function(date){
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		
		var strDate = year 
					+"-"+((month>9)?month:("0"+month))
					+"-"+((day>9)?day:("0"+day));
		return strDate;
	},

	
	/**
	 * 功能:弹出货币选择子界面，并将点击的form表单的值写成选择的货币
	 * @param {String} clickObj
	 * @return {TypeName} 
	 */
	showCCYPage: function(clickObj){
		var params = {
	 		src:getContextPath()+"/jsp/utils/currency",
	 		isMatte:true,
	 		title:"货币选择",
	 		width:400,
	 		height:460,
	 		buttons:[{
					name:"确认",
				clickEvent:function(obj){
	 				var selectedTr = obj.datagrid.getSelectedRows();
	 				if(selectedTr.length!=1){
	 					tips("请选择一行数据");
	 					return;
	 				}else{
	 					$(clickObj).val(selectedTr[0].ccy);
						dialog.close();
	 				}
			}
			}],
			hasCloseButton:true
		};
		var dialog = top.NewUI.Dialog(params);
	},

	
	/**
	 * 功能:为传入的JQuery对象绑定月末日期校验
	 * @param {JQueryObject} object
	 * @return {TypeName} 
	 */
	bindLastDayOfMonthAndDateFormatEvent: function(object, showTips){
		showTips = showTips == undefined ? true : showTips;
		$(object).on("change",function(){
			if($(this).val() == ""){
				return;
			}else{
				var a = /^(\d{4})-(\d{2})-(\d{2})$/;
				if (!a.test($(this).val())) {
					if(showTips){
						tips("业务日期格式不正确!");
					}
					$(this).val("");
					return;
				}
				var currentEndDay = CommonUtils.getEndDayOfMonth($(this).val());
				if($(this).val() != currentEndDay){
					if(showTips){
						tips("业务日期必须月末自然日!");
					}
					$(this).val(currentEndDay);
					return;
				}
			}
		});
	},
	
	/**
	 * 功能：审核通过
	 * @param {Object} datagrid Datagrid对象
	 * @param {Object} bizCode 当前补录业务编码
	 * @param {Object} dialog Dialog对象
	 * @return {TypeName} 
	 */
	passRecord:function(datagrid, recType, dialog){
	   var getSelectedw = datagrid.getSelectedRows();
	   if(getSelectedw.length==0){
			tips("请选择一行数据");
			return;
		}
	   	var status = NewUtils.ObjectArray2ParamArray(getSelectedw,"recStatus");
	   	for(var i=0;i<status.length;i++){
	   		if(status[i]!="2"){
				sysAlert("选中的数据包含非[待审核]数据,不能审核通过");
				return;
			}
	   	}
		var iscommit = false;
		sysConfirm("确定审核通过这"+getSelectedw.length+"行数据？",function(){
			var recIds = NewUtils.ObjectArray2ParamArray(getSelectedw,"id");	
			NewUtils.JAjax(getContextPath()+"/passedRecord",$.param({recIds:recIds, recType:recType}), function(result){
				if (result[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SUCCESS) {
					tips(result[ConstantNew.ERROR_MESSAGE_KEY], "success");
					datagrid.reloadDatagrid();
					if(dialog){
						dialog.close();
					}
					iscommit = false;
				}else{
					sysAlert(result[ConstantNew.ERROR_MESSAGE_KEY]);
					iscommit = false;
				}
			});
		});
	},
	/**
	 * 功能：审核退回
	 * @param {Object} datagrid Datagrid对象
	 * @param {String} bizCode 当前补录业务编码
	 * @param {Boolean} isAllowReviewedDataBack 是否允许对已审核数据进行退回操作
	 * @param {Object} dialog Dialog对象
	 * @return {TypeName} 
	 */
	backRecord:function(datagrid, recType, isAllowReviewedDataBack, dialog){
	   var getSelectedw = datagrid.getSelectedRows();
	   if(getSelectedw.length==0){
			tips("请至少选择一行数据");
			return;
		}
	   
	   isAllowReviewedDataBack = isAllowReviewedDataBack == undefined ? false : isAllowReviewedDataBack;
	   
	   var status = NewUtils.ObjectArray2ParamArray(getSelectedw,"recStatus");
	   	for(var i=0;i<status.length;i++){
	   		if(isAllowReviewedDataBack){
		   		if(status[i]!="2" && status[i]!="3"){
					sysAlert("选中的数据不是[待审核]或者[已审核]数据,不能审核退回!");
					return;
				}
	   		}else{
		   		if(status[i]!="2"){
					sysAlert("选中的数据不是[待审核]数据,不能审核退回!");
					return;
				}
	   		}
	   	}
	   	
		var iscommit = false;
		sysConfirm("确定审核退回这"+getSelectedw.length+"行数据？",function(){
			var recIds = NewUtils.ObjectArray2ParamArray(getSelectedw,"id");
			NewUtils.JAjax(getContextPath()+"/backRecord",$.param({recIds:recIds, recType:recType}), function(result){
				if (result[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SUCCESS) {
					tips(result[ConstantNew.ERROR_MESSAGE_KEY], "success");
					datagrid.reloadDatagrid();
					if(dialog){
						dialog.close();
					}
					iscommit = false;
				}else{
					sysAlert(result[ConstantNew.ERROR_MESSAGE_KEY]);
					iscommit = false;
				}
			});
		});
	},
	
	/**
	 * 功能：格式化数字为千分位，小数点后保留n位
	 * @param {Float} num 
	 * @param {Int} n 小数点后保留位数
	 * @return {String} 
	 */
	formatNumber:function(num, n) {
		if(num == null || isNaN(num)){
			num = 0;
		}
	    return (num.toFixed(n) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
	}
};
