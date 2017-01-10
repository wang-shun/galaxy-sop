
function reloadData(searchPartMoney,pid){
	var data = {};
	  data.searchPartMoney=searchPartMoney;
	  data.projectId=pid;
	  sendPostRequestByJsonStr(platformUrl.queryGrantTotalList,JSON.stringify(data),queryBack);

}
function queryBack(data){
	$("#tabApprAllList").children('div').remove(); 
	var result = data.result.status;
	if(result == "ERROR"){ //OK, ERROR
		layer.msg(data.result.message);
		return;
	}else{ 
	    var entityList = data.pageList;
	    //暂无注资计划
	    if(entityList.total==0){
	    	var noContent='<div class="no_con" style="display: block;">没有找到匹配的记录</div>';
	    	$("#tabApprAllList").append(noContent);
	    }
		if(typeof(entityList)!="underfined"&&entityList!=null){
			var content=entityList.content;			
			if(content.length>0){
				for(var i=0;i<content.length;i++){
					var grantTotal=content[i];
					var _this=$("#tabApprAllList");
					var kk=assembleHtml(grantTotal,i);
					$("#tabApprAllList").append(kk);
					var partList=grantTotal.partList;
					if(null!=partList&&partList.length>0){
						for(var k=0;k<partList.length;k++){
							  var grantPart=partList[k];
							  var o=_this;
							  $("#tabApprSingleList_"+i+"").append(assembleSingleTabHtml(grantPart,grantTotal.grantName,i,k));
							}
						}else{
							var noData =
								'<tr>'+
								 '<td colspan="6" class="no_info no_info01" style="text-align:center;"><p class="no_info_icon">没有找到匹配的记录</p></td>'+
								' </tr>'; 			
							 $("#tabApprSingleList_"+i+"").append(noData)
						}
					}
					if(isEditable != 'true')
					{
					 	$("#tabApprAllList .b_agreement_r .rolehide").hide();
					 	$("#tabApprAllList .edit-btn, #tabApprAllList .del-btn").hide();
					}
			}
		}
	}
}
function  assembleHtml(grantTotal,i){
	var name=grantTotal.updatedUname;
	if(typeof(name) == "undefined"){
		name = grantTotal.createUname;
	}
	var subName = name;
	if($(window).width()<1400){  //编辑人针对窄屏显示器截断显示
		if(name.length>5){
			subName=name.substring(0,5);
		}
	}	
	var html=
		'<div class="agreement">'
	     +'<div class="b_agreement clearfix">'
		  +'<div class="b_agreement_l fl">'
		     +'<h3>'+grantTotal.grantName+'</h3>'
	         +'<dl><dt>计划总注资金额（万元）：</dt><dd>'+fixSizeDecimal(grantTotal.grantMoney,4)+'</dd></dl>'
             +'<dl><dt>编辑人：</dt><dd title="'+name+'">'+subName+'</dd></dl>'    
             +'<dl><dt>编辑日期：</dt><dd>'+time_zh(grantTotal.updatedTime)+'</dd></dl>'
          +'</div>'    
         +'<div class="b_agreement_r fr">'
            +'<button class="pbtn bluebtn rolehide" href="/sop/html/actual_aging.html" data_type="add" data-btn="actual_aging" data-id="'+grantTotal.id+'" data-name="添加分期注资计划" data-total-name="'+grantTotal.grantName+'">添加分期注资计划</button>'
            +'<label class="blue" href="/sop/html/actual_all.html" data-btn="actual_all" data-on="info" data-val="'+grantTotal.id+'"data-name="查看总注资计划">查看</label>'
            +'<label class="blue rolehide" href="/sop/html/actual_all.html" data-btn="actual_all" data-on="edit" data-val="'+grantTotal.id+'"data-name="编辑总注资计划">编辑</label>'
            +'<label class="blue rolehide" href="/sop/html/1tips.html" data-btn="tips" onclick="deleteAppr('+grantTotal.id+')" data-name="提示">删除</label>'
         +'</div>'
    +'</div>'                      
  //  <!--表格内容-->
   +'<table width="100%" cellspacing="0" cellpadding="0" class="commonsize delivery">'
     + '<thead>'
          +'<tr>'
             +'<th>分拨</th>'
              +'<th>计划注资时间</th>'
              +'<th>计划注资金额（万元）</th>'
               +'<th>实际注资金额（万元）</th>'
              +'<th>附件数</th>'
              +'<th>操作</th>'
             +'</tr>'
      +'</thead>' 
      +'<tbody id="tabApprSingleList_'+i+'"></tbody>'
   +'</table>'
 +'</div>';
	 return html ;
} 

function  assembleSingleTabHtml(grantPart,grantName,i,k){
	 var value='<tr>'	 
		   +'<td><a class="blue" href="javascript:void(0)" title="点击进入详情可查看实际注资信息" data-part-id='+grantPart.id+' data-btn="actual" data-flag="part_'+i+'_'+k+'" data-name="实际注资信息列表">'+grantPart.grantName+'</a></td>'
		   +'<td>'+grantPart.grantDetail+'</td>'
		   +'<td>'+fixSizeDecimal(grantPart.grantMoney,4)+'</td>'
		   +'<td id="part_'+i+'_'+k+'">'+fixSizeDecimal(grantPart.actualMoney,4)+'</td>'
		   +'<td>'+grantPart.fileNum+'</td>'                                 
		   +'<td><label class="blue show-btn" href="/sop/html/actual_aging.html" data_type="info" data-btn="actual_aging" data-part-id="'+grantPart.id+'" data-id="'+grantPart.totalGrantId+'" data-name="查看分期注资计划" data-total-name="'+grantName+'">查看</label><label class="blue edit-btn" href="/sop/html/actual_aging.html" data_type="edit" data-btn="actual_aging" data-part-id="'+grantPart.id+'" data-id="'+grantPart.totalGrantId+'" data-name="编辑分期注资计划" data-total-name="'+grantName+'">编辑</label><label class="blue del-btn" href="javascript:void(0);" onclick="to_del_grantPart('+grantPart.id+')" data-btn="tips" data-name="提示">删除</label>';
		   if(grantPart.fileNum != 0){
			   value +='<label class="blue noMargin" onclick="to_download_grantPart('+grantPart.id+')">下载附件</label></td>';
		   }
		   value += '</tr>';
	  return value;
}
function deleteAppr(id){
	var _url = platformUrl.deleteAlert;
	$.getHtml({
		url:_url,//模版请求地址
		data:"",//传递参数
		okback:function(){
			$("#popup_name").html("提示");
			$("[data-btn='appr_delete']").click(function(){
				del_appr(id);
				//启用滚动条
				 $(document.body).css({
				   "overflow-x":"auto",
				   "overflow-y":"auto"
				 });
			})
			
		}//模版反回成功执行	
	});
 		
 	}
function del_appr(id){
	sendPostRequest(platformUrl.deleteGrantTotal+"/"+id,function(data){
			if (data.result.status=="OK") {
				layer.msg("删除成功");
				$("#powindow").remove();
				$("#popbg").remove();
		        var url = Constants.sopEndpointURL + "/galaxy/project/toAppropriation/null/"+pId;
				$.getTabHtml({
					url : url
				});
				reference(pId);
			}else{
				$("#powindow").remove();
				$("#popbg").remove();
			    layer.msg(data.result.message);
		}
	});
}

function flushData(partId){
	sendGetRequest(platformUrl.getActualPartMoney + "/" + partId,null,function(data){
		var money = data.userData.moneyAvtual;
		var moneyTotal = data.userData.moneyAvtualAll;
		var val=$("#partFlag").val();
		 $("#"+val).text(fixSizeDecimal(money,4));
		 var total=$(".money_total").text();
		  var oldTotal=$("#planMoney").val();
		 setData(oldTotal,moneyTotal);
		 if(typeof(moneyTotal)=="underfined"||null==moneyTotal||moneyTotal==0){
			 moneyTotal=0;
		 }else{
				 //var format=fixSizeTwo(moneyTotal/10000);
				 if(moneyTotal==0.0000){
					 moneyTotal=0;
				 }
				 /*else{
					 moneyTotal=format ;
				 }*/
			 }
		 $(".money_complete").text(moneyTotal);
	});
	
}