function saveBaseInfo(dom,val1,val2,val3){
	var infoModeList = new Array();
	var fields = $("#"+dom).find("input[data-title-id],select[data-title-id]");
	var data = {
			projectId : projectInfo.id
		};
	$.each(fields,function(){
		var field = $(this);
		var type = field.data('type');
		var _tochange =field.attr("tochange");
		var sele = field.get(0).tagName;
		var _resultId = field.attr("data-result-id");
		/*if(sele=="SELECT"){
			var _resultId = field.attr("resultId");
		}else{
			var _resultId = field.attr("resultId");
		}*/
		if(_tochange==undefined){
			_tochange=false;
		}
		if(_resultId==undefined){
			_resultId=null;
		}
		if(_tochange==undefined){
			_tochange=false;
		}
		var infoMode = {
			titleId	: field.data('titleId'),
			tochange:_tochange,
			resultId:_resultId,
			type : type
		};
		if(field.data('titleId')=="1118"&&type=="23"){ 
				var judgment = $("input[name=projectSource]").attr("m-val");
				alert(judgment);
				if(judgment!='2257'&&judgment!='2262'){ 
					var judgName = $(".man_info .name").text();
					var val = $("select[data-title-id=1118]").find("option:contains("+judgName+")").attr("value");
					 if(val!=undefined){
						 var infoMode = {
								titleId	: field.data('titleId'),
								tochange:true,
								resultId:"",
								type : type,
								value:val
							};						 
					 }else{
						 val = $("select[data-title-id=1118]").find("option").last().attr("value");
						 var infoMode = {
								titleId	: field.data('titleId'),
								tochange:true,
								resultId:resultId13,
								type : type,
								value:val,
								remark1:judgName
								
							};
					 }
					 infoModeList.push(infoMode); 
					 data.infoModeList = infoModeList;
					 return;
				}else if(judgment=='2257'){
					data.deletedResultTids=['1118'];
					return;
				}else{ 
					debugger;
					var values =[] ; 
					var doms = $(".selectcheck li.selected span");
					$.each(doms,function(){ 
						values.push($(this).attr('data-value'))
					})  
					var remark = $('.selectcheck .addpro-input').val();
					var other = $('.selectcheck .addpro-input').attr("ovalue");  
					for(i=0;i<values.length;i++){ 
						var resultId13=$("select#selectRadio").find("option[value="+values[i]+"]").attr("data-result-id")
						if(resultId13==undefined){
							resultId13=null
						}
						var infoMode = {
							titleId	: field.data('titleId'),
							tochange:true,
							resultId:resultId13,
							type : type
						};
						var that = values[i]; 
						infoMode.value=that;  
						if(other==that&&remark!=''&&remark!=null){  
							infoMode.remark1=remark;
						}
						infoModeList.push(infoMode); 
					}  
					data.infoModeList = infoModeList;
					return;
				}

			}else if(field.hasClass("inputSouce")&&type==1){  
				var infoMode = {
						titleId	: field.data('titleId'),
						tochange:true,
						resultId:_resultId,
						type : type
					};	 
				infoMode.remark1=field.val();
			}else if(type==14 )
		{
			infoMode.value = field.attr('m-val');
		}else if(type==19 ){
			infoMode.remark1 = field.val();
		}	
		if (infoMode != null&&type!="13") {
	        infoModeList.push(infoMode);
	    } 
		data.infoModeList = infoModeList;
	}); 
	sendPostRequestByJsonObjNoCache(
			platformUrl.saveOrUpdateInfo , 
			data,
			true,
			function(data) {
				var result = data.result.status;
				if (result == 'OK') {
					layer.msg('保存成功');
					if(dom=="basicForm"&&val1=="finance"){	
						updateReportMoney(); 
					}
//					弹窗关闭
					var close="basic"
					$('.'+close+'_current').hide();//basic_current
					$('.'+close+'_on').hide();
					$('.'+close+'_center').show();
					$('.bj_hui_on').hide();
					$('.tip-yellowsimple').hide();
					$("body").css('overflow-y','auto');
					
					
					if(dom=='company-info-form'){
						$("#projectCompany").text(val1);
						$('#companyLegal').text(val3);
						$('#formationDate').text(val2);
					}
				} else {
					layer.msg("操作失败!");
				}
		});
}