function editOpen(){
	$(".h_edit_btn").click(function(){
		/*var part=$(this).parent().parent(".h_look").parent(".radius").attr("id").split('_');
		var edit_id='h_edit_'+part[1];
		$(this).attr("id",edit_id)*/
	    $(this).parent().parent(".h_look").hide();
	    $(this).parent().parent(".h_look").siblings(".h_edit").show();
	    $(this).parent().parent().parent(".h").css("background","#fbfbfb")
	  })
	/*取消*/
	$('span[data-on="h_cancel"]').click(function(){
	  $(this).parent().parent(".h_edit").hide();
	  $(this).parent().parent(".h_edit").siblings(".h_look").show();
	  $(this).parent().parent().parent(".h").css("background","#fff");
	})
}
/*多选标签*/
$("div").delegate(".check_label","click",function(event){
	  $(this).toggleClass('active');
	  event.stopPropagation();
});
/*文本域字数统计*/
function countChar(textareaName,spanName,maxLimit){
	//var maxLimit=10;
	var textArea=document.getElementById(textareaName);
	var spanCount=document.getElementById(spanName);
	if (textArea.value.length>maxLimit){
		spanCount.innerHTML='0';
		textArea.value = textArea.value.substring(0, maxLimit);
	}else{
		spanCount.innerHTML =maxLimit-textArea.value.length;
	}
}
function tabInfoChange(index){
	$("#tab-content").remove();
	$("#tab-content1").remove();
		switch(index){
	case '0':initBaseInfo(); break;  //标签0:基本信息
	case '1':initProjectInfo(); break;  //标签1:项目
	case '2': initTeamInfo(); break;  //标签2: 团队
	case '3': initOperateInfo();   break;  //标签3:运营数据
	case '4': initCompeteInfo();   break;  //标签4:竞争
	case '5': initPlanInfo();   break;  //标签5:战略及策略
	case '6': initFinanceInfo();   break;  //标签6:财务
	case '7': initJusticeInfo();   break;  //标签7:法务
	case '8': initValuationInfo();   break;  //标签8:融资及估值
	default: return false;
}
}
//基本信息
	function initBaseInfo(){
		$.getTabHtmlInfo({
			url : platformUrl.toBaseInfo
		}); 
	   }
	   //项目
		function initProjectInfo(){
		 $.getTabHtmlInfo({
				url : platformUrl.toProjectInfo 
			}); 
		}
		 //团队
		function initTeamInfo(){
			$.getTabHtmlInfo({
				url : platformUrl.toTeamInfo 
			});
		}
		 //运营数据
		function initOperateInfo(){
			$.getTabHtml({
				url : platformUrl.toOperateInfo 
			});
		}
		//竞争
		function initCompeteInfo(){
			$.getTabHtml({
				url : platformUrl.toCompeteInfo 
			});
		}
		//战略以及策略
		function initPlanInfo(){
			$.getTabHtml({
				url : platformUrl.toPlanInfo 
			});
		}
		//财务
		function initFinanceInfo(){
			$.getTabHtml({
				url : platformUrl.toFinanceInfo 
			});
		}
		//法务
		function initJusticeInfo(){
			$.getTabHtml({
				url : platformUrl.toJusticeInfo 
			});
		}
		//融资及估值
		function initValuationInfo(){
			$.getTabHtml({
				url : platformUrl.toValuationInfo 
			});
		}


	$.fn.showResults = function(readonly){
		var sec = $(this);
		var pid = $(this).data('sectionId');

		if(pid == 1302){
		     sendGetRequest("http://fx.local.galaxyinternet.com/sop/galaxy/team/queryRowsList/1302/1",null,function(data){
		        var result = data.result.status;
                if (result == 'OK')
                {
                    var entityList = data.entityList;
                    var data = entityList[0]

                    buildResults(sec,data,readonly);
                    buildTable(sec,data);
                }
		     })
		}
		sendGetRequest(platformUrl.getTitleResults + pid+'/'+projectInfo.id, null,
				function(data) {
			
			var result = data.result.status;
			if (result == 'OK') 
			{
				var entityList = data.entityList;
				if(entityList && entityList.length >0)
				{
					$.each(entityList,function(){
						var title = this;
						buildResults(sec,title,readonly);
						buildTable(sec,title);
						buildfinxedTable(sec,title,readonly);
					});
				}
			} 
		})
	}

});

function buildResults(sec,title,readonly)
{
	//普通字段
	if(title.resultList)
	{
		if(title.type == 1)
		{
			if(readonly == true)
			{
				$(".field[data-title-id='"+title.id+"']").text(title.resultList[0].contentDescribe1);
			}
			else
			{
				$("input[data-title-id='"+title.id+"']").val(title.resultList[0].contentDescribe1);
			}
		}
		if(title.type == 2)
		{
			if(readonly == true)
			{
				$(".field[data-title-id='"+title.id+"']").text(title.resultList[0].valueName);
			}
			else
			{
				$("input[data-title-id='"+title.id+"'][value='"+title.resultList[0].contentChoose+"']").attr('checked','true');
			}
		}
		if(title.type == 3){
			if(readonly == true)
			{
				$.each(title.resultList,function(i,n){
					$("dd[data-title-id='"+ n.contentChoose +"']").text(n.valueName);
				});
			}else{
				$.each(title.resultList,function(i,n){
					$("li[data-id='"+ n.contentChoose +"']").addClass('active');
				});
			}
			
		}
		else(title.type == 8)
		{
			if(readonly == true)
			{
				$(".field[data-title-id='"+title.id+"']").text(title.resultList[0].contentDescribe1);
			}
			else
			{
				$("textarea[data-title-id='"+title.id+"']").val(title.resultList[0].contentDescribe1);
			}
		}
	}
}
function buildTable(sec,title)
{
	//列表Header
	if(title.tableHeader)
	{
		var header = title.tableHeader;
		var tables = $("table[data-title-id='"+header.titleId+"']");
		$.each(tables,function(){
			var table = $(this);
			table.attr('data-code',header.code);
			table.empty();
			var tr="<thead><tr>";
			for(var key in header)
			{
				if(key.indexOf('field')>-1)
				{
					tr +='<th data-field-name="'+key+'">'+header[key]+'</th>';
				}
			}
			var editable = table.hasClass('editable');
			if(editable == true)
			{
				tr +='<th data-field-name="opt">操作</th>';
			}
			tr+="</tr></thead>";
			table.append(tr);
		});
	}
	//列表Row
	if(title.dataList)
	{
		$.each(title.dataList,function(){
			var row = this;
			alert(row.titleId)
			var tables = $("table[data-title-id='"+row.titleId+"']");
			$.each(tables,function(){
				var table = $(this);
				var tr = buildRow(row,table.hasClass('editable'));
				table.append(tr);
				console.log(table.html())
			});
		});
	}
}
function buildRow(row,showOpts)
{
	var tr=$("<tr data-row-id='"+row.id+"'></tr>");
	for(var key in row)
	{
		//设置data
		tr.data(key,row[key]);
		if(key.indexOf('field')>-1)
		{
			tr.append('<td data-field-name="'+key+'">'+row[key]+'</td>');
		}
	}
	if(showOpts == true)
	{
		var td = $('<td data-field-name="opt"></td>');
		td.append('<span class="blue" data-btn="btn" onclick="editRow(this)">编辑</span>');
		td.append('<span class="blue" data-btn="btn" onclick="delRow(this)">删除</span>');
		tr.append(td);
	}
	return tr;

}
function buildfinxedTable(sec,title,readonly){
	if(title.fixedTableList){
	  if(readonly == true)
		{
		  $.each(title.fixedTableList,function(i,n){
				$("td[data-format='"+n.rowNo+"_"+n.colNo+"']").text(n.valueName);
			});
		}else
		{
			$.each(title.fixedTableList,function(i,n){
				$("td[data-flag='"+n.colNo+"']").find("input[data-row='row"+n.rowNo+"'][value="+n.content+"]").attr('checked','true');
				
			});
		}
	}
}
function setDate(pid, readonly) {
	sendGetRequest(platformUrl.getTitleResults + pid + '/' + projectInfo.id,
			null, function(data) {
				var result = data.result.status;
				if (result == 'OK') {
					var entityList = data.entityList;
					if (entityList && entityList.length > 0) {
						$.each(entityList, function() {
							var title = this;
							buildResults(null, title, readonly);
							buildfinxedTable(null, title, readonly);
						});
					}
				}
			})

}
