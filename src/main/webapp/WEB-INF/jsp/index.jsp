<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page language="java" import="java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<% String path = request.getContextPath();%>

<!DOCTYPE html>
<html>
<head>
<title>LinkPowerAI</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link rel="stylesheet" href="/css/home.css" />
<link rel="stylesheet" href="/css/commonNew.css" />
<script src="/js/commonNew.js"></script>

<script type="text/javascript">
$(function() {
	
	//************************************************页面控件初始化*********************************************/
	
	$("#isUrlSelf").on("change", function(){
		if($(this).is(':checked')  == true){
			$("#urlSelf").removeAttr("disabled");
			$("#url").attr("disabled", "disabled");
			$("#url").addClass("disabled");
			$("#url").val("");
			$("#param").val("");
		}else{
			$("#url").removeAttr("disabled");
			$("#url").removeClass("disabled");
			$("#urlSelf").attr("disabled", "disabled");
		}
	});
	
	$("#url").on("change", function(){
		if($(this).val()  == "generateScoreResultLabelData"){
			$("#param").val("{\"saledId\":\"\", \"custId\":\"\"}");
		}else if($(this).val()  == ""){
			$("#param").val("");
		}else{
			$("#param").val("{\"saledId\":\"\", \"custId\":\"\", \"dataDate\":\"\"}");
		}
	});
	
	
	$("#sendAjax").on("click", function(){
		var url = "";
		if($("#isUrlSelf").is(':checked')  == true){
			url = $("#urlSelf").val();
		}else{
			url = $("#url").val();
		}
		
		if(url == ""){
			sysAlert("请输入URL!");
			return;
		}
		
		$("#showResult").val("请求发送中,请等待后台处理结果...");

		var param = {};
		var data = $("#param").val();
		console.log(param);
		if(data == ""){
			param = {};
		}else{
			param = JSON.parse(data);
		}
		console.log(param);
		
		NewUtils.JAjax("<%=path%>/LpScoreIndexMidController/"+url, param, true, "json", function(result) {
 			if (result.ERROR_CODE != "SUCCESS") {
 				sysAlert(result.ERROR_MESSAGE);
 				console.log(result.DATASET);
 			} else {
 				var resultHtml = "";
 				if(result.DATASET == ""){
 					resultHtml = "没有数据";
 				}else{
 					resultHtml = JSON.stringify(result);
 				}
				
 				$("#showResult").val(resultHtml);
 			}
 		});
	});
});
</script>
<style type="text/css">
	#url, #urlSelf, #param {
		width:30%;
		height:30px;
		margin-left: 15px;
	}
	#isUrlSelf {
		width: 25px;
	    height: 25px;
	}
	button {
		margin:5px 0px 30px 0px;
		width: 40%;
		background-color: #47BAFE;
		color: white;
		height: 35px;
	}
	.disabled {
		background-color: rgb(235, 235, 228);
	}

</style>
</head>

<body>
	<div>
		<center>
			<label style="color:blue; margin:80px 0px 5px 0px; width:170px; text-align:right;">系统内置模型接口:<p style="color:grey; font-size:10px;">(默认getAllUserOrgForFilter)</p></label>
			
			<select id="url">
				<option value="">=========请选择模型接口=========</option>
				<option value="perHandlerSourceData">Step1:源数据预处理(日期参数YYYYMMDD不传则处理所有未参与过模型计算的数据)</option>
				<option value="generateLpDsModelData">Step2-1:生成模型中间表数据(日期参数YYYYMMDD不传则处理系统当日)</option>
				<option value="generateIndexScoreData">Step2-2:生成灵豹分各指标得分(日期参数YYYYMMDD不传则处理系统当日)</option>
				<option value="generateScoreResultLabelData">Step2-3:生成灵豹分和灵豹标签</option>
				<option value="generateLpActSuggestData">Step3:生成行动推荐数据(日期参数YYYYMMDD不传则处理系统当日)</option>
			</select>
			<br>
			<input id="isUrlSelf" type="checkbox">
			<label style="color:blue; width:140px; text-align:right;">自定义测试URL : </label>
			<input id="urlSelf" type="text" disabled="disabled" placeholder="仅输入Controller接口名称" />
			<br>
			<label style="color:blue; margin:5px 0px 5px 0px; width:170px; text-align:right;">请输入测试参数:<p style="color:grey; font-size:10px;">((格式为{"key":"value"})</p></label>
			<input id="param" type="text" placeholder="请输入JSON格式参数)" />
			<br>
			<button id="sendAjax" type="button">发送请求</button>
			<br>
			<h1 style="margin:20px 0px;"><font color='red'>请求结果为:</font></H1>
			<textarea id="showResult" style="width:80%; height:400px; padding:50px 20px;"></textarea>
		</center>
	</div>
</body>
</html>
