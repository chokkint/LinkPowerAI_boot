var myChart;
var domGraphic = document.getElementById('graphic');
var domMain = document.getElementById('main');
var needRefresh = false;

var enVersion = location.hash.indexOf('-en') != -1;
var hash = location.hash.replace('-en','');
hash = hash.replace('#','') || (needMap() ? 'default' : 'macarons');
hash += enVersion ? '-en' : '';

var curTheme;
function requireCallback (ec, defaultTheme) {
    curTheme = themeSelector ? defaultTheme : {};
    echarts = ec;
    refresh();
    window.onresize = myChart.resize;
}

var themeSelector = $('#theme-select');
if (themeSelector) {
    themeSelector.html(
        '<option selected="true" name="macarons">macarons</option>'
        + '<option name="infographic">infographic</option>'
        + '<option name="shine">shine</option>'
        + '<option name="dark">dark</option>'
        + '<option name="blue">blue</option>'
        + '<option name="green">green</option>'
        + '<option name="red">red</option>'
        + '<option name="gray">gray</option>'
        + '<option name="helianthus">helianthus</option>'
        + '<option name="roma">roma</option>'
        + '<option name="mint">mint</option>'
        + '<option name="macarons2">macarons2</option>'
        + '<option name="sakura">sakura</option>'
        + '<option name="default">default</option>'
    );
    $(themeSelector).on('change', function(){
        selectChange($(this).val());
    });
    function selectChange(value){
        var theme = value;
        myChart.showLoading();
        $(themeSelector).val(theme);
        if (theme != 'default') {
            window.location.hash = value + (enVersion ? '-en' : '');
            require([NewUtils.getContextPath() + '/plug_in/echarts/theme/' + theme], function(tarTheme){
                curTheme = tarTheme;
                setTimeout(refreshTheme, 500);
            })
        } else {
            window.location.hash = enVersion ? '-en' : '';
            curTheme = {};
            setTimeout(refreshTheme, 500);
        }
    }
    function refreshTheme(){
        myChart.hideLoading();
        myChart.setTheme(curTheme);
    }
    if ($(themeSelector).val(hash.replace('-en', '')).val() != hash.replace('-en', '')) {
        $(themeSelector).val('macarons');
        hash = 'macarons' + enVersion ? '-en' : '';
        window.location.hash = hash;
    }
}

function focusGraphic() {
    domGraphic.className = 'col-md-12 ani';
    if (needRefresh) {
        myChart.showLoading();
        setTimeout(refresh, 1000);
    }
}

function refresh(isBtnRefresh){
    if (isBtnRefresh) {
        needRefresh = true;
        focusGraphic();
        return;
    }
    needRefresh = false;
    if (myChart && myChart.dispose) {
        myChart.dispose();
    }
    myChart = echarts.init(domMain, curTheme);
    window.onresize = myChart.resize;
    
    // 判断业务类别或者统计指标必须至少选择一个值
    var businessCode = document.getElementById("businessCodeSearch").value;
    var indexCode = document.getElementById("indexCodeSearch").value;
    if((businessCode== null || businessCode == "")&& (indexCode== null || indexCode == "")){
		return;
	}
    
    // 判断其他必要条件是否为空
    var startDate = document.getElementById("startDateSearch").value;
    var endDate = document.getElementById("endDateSearch").value;
    var branch = document.getElementById("branchSearch").value;
    var language = document.getElementById("languageSearch").value;
    var chartType = document.getElementById("chartTypeSearch").value;
    var dataFreq = document.getElementById("dataFreqSearch").value;
    
    if(startDate== null || startDate == ""
			|| endDate== null || endDate == ""
			|| language== null || language == ""
			|| chartType== null || chartType == ""
			|| dataFreq== null || dataFreq == ""){
    	return;
    }
    
	var param={
		startDate : startDate,
		endDate : endDate,
		branch : branch,
		businessCode : businessCode,
		indexCode : indexCode,
		language : language,
		chartType : chartType,
		dataFreq : dataFreq
	};
    
	NewUtils.JAjax(NewUtils.getContextPath()+"/getChartOptionData", param, function(result){
		if (result[ConstantNew.ERROR_CODE_KEY] == ConstantNew.ERROR_CODE_OBJECT.ERROR_CODE_SUCCESS) {
			var dataset = result.DATASET;
		    myChart.setOption(dataset, true);
		}else{
			sysAlert(result[ConstantNew.ERROR_MESSAGE_KEY]);
		}
	});
}

function needMap() {
    var href = location.href;
    return href.indexOf('map') != -1
           || href.indexOf('mix3') != -1
           || href.indexOf('mix5') != -1
           || href.indexOf('dataRange') != -1;

}

var echarts;
// for echarts online home page
require.config({
    paths: {
        echarts: NewUtils.getContextPath() + '/plug_in/echarts/www/js'
    }
});
launchExample();


var isExampleLaunched;
function launchExample() {
    if (isExampleLaunched) {
        return;
    }

    // 按需加载
    isExampleLaunched = 1;
    require(
        [
            'echarts',
            NewUtils.getContextPath() + '/plug_in/echarts/theme/' + hash.replace('-en', ''),
            'echarts/chart/line',
            'echarts/chart/bar',
            'echarts/chart/scatter',
            'echarts/chart/k',
            'echarts/chart/pie',
            'echarts/chart/radar',
            'echarts/chart/force',
            'echarts/chart/chord',
            'echarts/chart/gauge',
            'echarts/chart/funnel',
            'echarts/chart/eventRiver',
            'echarts/chart/venn',
            'echarts/chart/treemap',
            'echarts/chart/tree',
            'echarts/chart/wordCloud',
            'echarts/chart/heatmap',
            needMap() ? 'echarts/chart/map' : 'echarts'
        ],
        requireCallback
    );
}

