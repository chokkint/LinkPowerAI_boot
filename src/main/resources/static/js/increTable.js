


window.UI.IncreTable = function(table,tempTable){
	if(typeof table == "string"){this.table = $("#"+table);}
	else this.table = table;
	if(typeof tempTable == "string"){this.tempTable = $("#"+tempTable);}
	else this.tempTable = tempTable;
	this.init();
};

window.UI.IncreTable.prototype = {
	constructor:window.UI.IncreTable,
	init:function(){
		var increTable = this;
		increTable.table.on("click","tr",function(event){
			$("tr.selectedTr",increTable.table).removeClass("selectedTr");
			$(event.currentTarget).addClass("selectedTr");
		})
	},
	addRow:function(){
		var increTable = this;
		var subtrim = increTable.tempTable.attr("subtrim");
		var trHtml = increTable.tempTable.clone();
		var count = $("tr",increTable.table).length-1;
				
		$(":input,select",trHtml).each(function(j,m){
			var Jm = $(m);
			Jm.attr("name",subtrim+"["+count+"]."+Jm.attr("sub-name"));
			if(Jm.attr("isInherit")=="true"){
				Jm.val($(("[name='"+subtrim+"["+0+"]."+Jm.attr("sub-name")+"']"),increTable.table).val());
			}
		});
		increTable.table.append(trHtml.children());
		increTable.countNumber();
	},
	countNumber:function(){
		var increTable = this;
		$(":text[autonumber='true']",increTable.table).each(function(i,n){
			$(n).val(i+1);
		});
	},
	deleteRow:function(){
		var increTable = this;
		$("tr.selectedTr",increTable.table).remove();
		var subtrim = increTable.tempTable.attr("subtrim");
		$("tr",increTable.table).slice(1).each(function(i,n){
			$(":input,select",n).each(function(j,m){
				$(m).attr("name",subtrim+"["+i+"]."+$(m).attr("sub-name"));
			});
		});
		increTable.countNumber();
	},
	addRowNew:function(){
		var increTable = this;
		var subtrim = increTable.tempTable.attr("subtrim");
		var trHtml = increTable.tempTable.clone();
		var count = $("tr",increTable.table).length-1;
				
		$(":input,select",trHtml).each(function(j,m){
			var Jm = $(m);
			Jm.attr("name",subtrim+"["+count+"]."+Jm.attr("sub-name"));
			if(Jm.attr("isInherit")=="true"){
				Jm.val($(("[name='"+subtrim+"["+0+"]."+Jm.attr("sub-name")+"']"),increTable.table).val());
			}
		});
		increTable.table.append(trHtml.children());
		increTable.countNumberNew();
	},
	countNumberNew:function(){
		var increTable = this;
		var trObj = $(":text[autonumber='true']",increTable.table);
		trObj.last().val(trObj.length);
	},
	deleteRowNew:function(){
		var increTable = this;
		$("tr.selectedTr",increTable.table).remove();
		var subtrim = increTable.tempTable.attr("subtrim");
		$("tr",increTable.table).slice(1).each(function(i,n){
			$(":input,select",n).each(function(j,m){
				$(m).attr("name",subtrim+"["+i+"]."+$(m).attr("sub-name"));
			});
		});
	}
};
