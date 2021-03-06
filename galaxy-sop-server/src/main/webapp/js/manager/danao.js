//1.11
//status  是否引用 0 未引用 
function pagePop(codes){  
	var urlCode ="/galaxy/infoDanao/infoJsp/"; 
	var code = codes;
	$.getHtml({ 
		url:Constants.sopEndpointURL + urlCode,//模版请求地址 
		data:"",//传递参数
		okback:function(){  
			 //infoDetail  
			var danaoProjCode= projectInfo.danaoProjCode; 
			$(".DN_list h5").text("参考信息").css("background","none"); 
			if(!danaoProjCode||danaoProjCode==null){
				$(".DN_list p:first").text("系统检测您还未从创投大脑选择项目进行引用").css("color","#FF5124");
			}else{
				$(".DN_list p:first").hide();
			}
			var _url = Constants.sopEndpointURL +"/galaxy/infoDanao/searchProject"; 
			$("#projectName").text($("#project_name_t").text()); 
			var data={ 
		   			"keyword":projectInfo.projectName,
					"orderBy":"setupDT", 
		    		}
			$(".rightLink").hide();
			$(".over_pro").hide();
			$("li[data-content=3]").hide();
			$(".scheduleIcon").css("width","220px");
			//大脑tableList分页    
			initTable(_url,data,1,codes); 
			$("#poptxt").css("padding","0");
			$("#powindow").css("background","#F4F4F4")
			
		}
	})  
} 
function infoDPop(even,status){  
	var urlCode = $(even).attr("urlCode");   
	if(status==1){ 
		//从引入进入  
		var code=$(even).attr("dncode");   
		var projCode=$(even).attr("projcode");   
		var danaoName = $(even).closest("tr").find(".DN_name").text();
		var dataJson={
				"projId":projectInfo.id,
				"projCode":projCode, 
		}  
		sendPostRequestByJsonObj(
		 Constants.sopEndpointURL + "/galaxy/infoDanao/saveConstat", 
		dataJson,
		function(data){ 
			 if(data.result.status=="OK"){ 
				 //移除其他  
				 $("#popbg").remove();
				 $("#powindow").remove(); 
				 projectInfo.danaoProjCode=projCode;
				 $("a[dncode]").attr("onclick","infoDPop(this)"); 
				 $("a[dncode]").attr("urlcode"," /galaxy/infoDanao/infoDJsp/");  
				getpopHTML(code,event,danaoName);
			 }
		 }) 
	}else{ 	
		//从按钮进入    
		$("#popbg").remove();
		$("#powindow").remove(); 
		$("#poptxt").css("padding","0");
		$("#powindow").css("background","#F4F4F4");
		var code=$(even).attr("dncode");   
		getpopHTML(code,even);
		 
	} 
function getpopHTML(code,even,danaoName){
	 $.getHtml({ 
		url:Constants.sopEndpointURL + urlCode,//模版请求地址 
		data:"",//传递参数
		okback:function(){  
			$("#poptxt").css("padding","0");
			$("#powindow").css("background","#F4F4F4") 
			$(".over_pro").hide();
			$("li[data-content=3]").hide();
			$(".scheduleIcon").css("width","220px");
			$(".backLink").attr("href","javascript:;");
			$(".backLink").attr("urlcode","/galaxy/infoDanao/infoJsp/");
			$(".backLink").click(function(){  					
			$("#popbg").remove();
			$("#powindow").remove();
			 pagePop(code)  
			 return false;
			}) 
				$("#projectName").text(danaoName); 
				var danaoProjCode= projectInfo.danaoProjCode; 
				$(".DN_list h5").text("参考信息").css("background","none"); 
				if(!danaoProjCode||danaoProjCode==null){
					$(".DN_list p:first").text("系统检测您还未从创投大脑选择项目进行引用").css("color","#FF5124");
				}else{
					$(".DN_list p:first").hide();
				}
				 //infoDetail     
				var _url = Constants.sopEndpointURL +"galaxy/infoDanao/searchProjectInfo/";
				var projectName=projectInfo.projectName; 
				var projectId=projectInfo.id;  
				var projCode=projectInfo.danaoProjCode;
				var data={
			   			"keyword":projectName,
						"orderBy":"setupDT",
			    		} 
					var jsonObj={
							projId:projectId,
							projCode:projCode,
							danaoInfo:code,
					}    
					buildInfoD(_url,jsonObj,code);  
					if($(".infoBox li:visible").length<=0){  
						 $(".infoBox").hide();
						 $(".fixedbottom").hide(); 
						 $(".emptyInfo").show().css({"margin-bottom":0,"overflow":"hidden"}); 
					 } 
			}
		}) 
}	  
}
function buildInfoD(url,data,code){ 
	sendPostRequestByJsonObj(url, data, function(data){
	 if(data.result.status=="OK"){
		 $("#projectName").text(data.userData.projTitle)
		 //根据code进行渲染  融资历史---history   股权结构-----equity  法人信息----legal   团队成员--team
		 var legal=data.userData.legalInfo;
		 var equityInfo=data.userData.equityInfo;
		 var team=data.userData.teamInfo; 
		 var financeInfo=data.userData.financeInfo; 	
		 buildDNtable($("#companyInfo"),legal,"");
		 buildDNtable($("#teamInfo"),team,"teamInfo");
		 buildDNtable($("#fina_historyInfo"),financeInfo,"financeInfo");
		 buildDNtable($("#equityInfo"),equityInfo,"equityInfo"); 
		 
	 }
	}) 
} 

/*
 * 检测对象是否是空对象(不包含任何可读属性)。
 * 方法既检测对象本身的属性，也检测从原型继承的属性(因此没有使hasOwnProperty)。
 */
function isEmpty(obj)
{
    for (var name in obj) 
    {
        return false;
    }
    return true;
};
//显示数据 
function buildDNinfo(_url,jsonObj){

	sendPostRequestByJsonObj(_url, jsonObj, function(data){   
		if(isEmpty(data.userData)){
			$(".tableBox.infoBox ").hide();
			$(".fixedbottom").hide();
			$(".emptyInfo").show().css({"margin-bottom":0,"overflow":"hidden"});
			return false;
		}
		 var legal=data.userData.legalInfo;
		 var equityInfo=data.userData.equityInfo;
		 var team=data.userData.teamInfo; 
		 var financeInfo=data.userData.financeInfo; 		 
		 buildDNtable($("#companyInfo"),legal,"");
		 buildDNtable($("#teamInfo"),team,"teamInfo");
		 buildDNtable($("#fina_historyInfo"),financeInfo,"financeInfo");
		 buildDNtable($("#equityInfo"),equityInfo,"equityInfo");
	 
	}) 
}
function buildDNtable(dom ,data,code){   
	if(!data){
		dom.hide();
		return false;
	};
	var str="";
	if(code=="equityInfo"){
		for(i=0;i<data.length;i++){
			 var that = data[i]
			 str+='<tr >'
					+'<td>'
					+'<input type="checkbox" onclick="checkSelf(this)" /><label></label>'
				+'</td>'
				+'<td name="field1" dnVal='+that.shareholder+'>'+filter(that.shareholder)+'</td>'
				+'<td name="field3" dnVal='+that.name+'>'+filter(that.shareholder11)+'</td>'
				+'<td name="field4" dnVal='+that.shareholderTypeId+'>'+filter(that.shareholderType)+'</td>'
				+'<td name="field2" dnVal='+that.equityRate+'>'+filter(that.equityRate)+'</td>'
				+'<td name="field5" dnVal='+that.name+'>'+filter(that.shareholder11)+'</td> '
				+'</tr>'
		 }
		dom.find("tbody").html(str);
	}else if(code=="teamInfo"){
		for(i=0;i<data.length;i++){
			 var that = data[i]
			 str+='<tr >'
					+'<td>'
					+'<input type="checkbox" onclick="checkSelf(this)" /><label></label>'
				+'</td>'
				+'<td name="field1" dnVal='+that.name+'>'+filter(that.name)+'</td>'
				+'<td name="field2" dnVal='+that.jobId+'>'+filter(that.job)+'</td>' 
				+'</tr>'
		 }
		dom.find("tbody").html(str);
		
	}else if(code=="financeInfo"){	 
		for(i=0;i<data.length;i++){
			 var that = data[i]
			 str+='<tr >'
				+'<td>'
				+'<input type="checkbox" onclick="checkSelf(this)" /><label></label>'
			+'</td>'
			+'<td name="field7" dnVal='+that.roundId+'>'+filter(that.round)+'</td>'
			+'<td name="field1" dnVal='+that.investDate+'>'+filter(that.investDate)+'</td>'
			+'<td name="field3" dnVal='+that.num+'>'+filter(that.num)+'</td>'
			+'<td name="field6" dnVal = '+that.unitId+'>'+filter(that.unit)+'</td>'
			+'<td name="field4" dnVal='+that.stock+'>'+filter(that.stock)+'</td> '
			+'<td name="field2" dnVal='+that.empty+'>'+filter(that.empty)+'</td> '
			+'</tr>'
		 }
		dom.find("tbody").html(str);
	}else{
		 $("#DN_projectCompany").text(data.company);
		 $("#DN_formationDate").text(data.foundDate);
		 $("#DN_companyLegal").text(data.legalPerson); 
	}
	if(dom.find("tbody tr").length<=0){
		dom.hide();
	} 
} 
//保存
function saveDN(even){  
	
	//判断列表超过10条数与否之后  才进行保存
	 var dataDN={};
	 dataDN.projectId=projectInfo.id;
	 var tables = $(even).closest(".fixedbottom").prev().find(".infoConList table:visible"); 
	 $.each(tables,function(){
		 var thatTable = $(this); 
		 var code =thatTable.attr("code");
		 var titleid = thatTable.attr("titleid"); 
		 if(code=="finance-history"){ 
			 sendGetRequest(platformUrl.getTitleResults+"1902/"+projectInfo.id,null,
				function(data) {
				 var arr = data.entityList; 
				 var array = arr.filter(function(val){return val.danaoInfo}) ;
				 var length = array[0].dataList.length; 
				saveDNsame(thatTable,dataDN,length,"融资历史")
			 }) 
			 //融资历史的保存
		 } else if(code=="equity-structure"){
			 //股权的保存
			 sendGetRequest(platformUrl.getTitleResults+"1902/"+projectInfo.id,null,
				function(data) {
				 var arr = data.entityList; 
				 var array = arr.filter(function(val){return val.danaoInfo}) ;
				 var length = array[1].dataList.length;  
				 saveDNsame(thatTable,dataDN,length,"股权结构")
			 }) 
			 
		 } else if(code=="company-info"){ 
			 var resultIdList;
			 //法人信息的保存
			 sendGetRequest(platformUrl.getRelateTitleResults +"2/5813/"+projectInfo.id, null,
				function(data) {
					 resultIdList =data.entityList; 
			 }) 
			 var infoListDN=[];
			 var checkTr=thatTable.find("tbody input[type=checkbox]:checked").closest("tr");
			 $.each(checkTr,function(){ 
				 var that =$(this).find("td").last(),
				 resultIdData=resultIdList.filter(function(val){ return val.id==that.attr("titleId")})[0].resultList,
				  resultIdres=null;
				 if(resultIdData){
					 resultIdres=resultIdData[0].id
				 }  
				 info={};
				 info.remark1=that.text();
				 info.tochange=true;
				 info.titleId=that.attr("titleId");
				 //需要带进来resultId
				 info.resultId=resultIdres; 
				 info.type=1;
				 infoListDN.push(info);
			 })   
			 dataDN.infoModeList=infoListDN;  
			  sendPostRequestByJsonObj(
					    platformUrl.saveOrUpdateInfo,
					    dataDN,
			    function(data) { 
					 layer.msg("保存成功")	 
					 $("#popbg").remove();
					 $("#powindow").remove();
					 $("body").css("overflow-y","auto");
			}) 
		 }else if(code=="team-members"){
			 //团队的保存
			 var infoListDN=[];
			 var checkTr=thatTable.find("tbody input[type=checkbox]:checked").closest("tr");
			 dataDN.titleId=thatTable.attr("titleid");	
			 sendGetRequest(platformUrl.queryMemberList+"1302/"+projectInfo.id,null,
				function(data) { 
				 var dttaLength = data.entityList[0].dataList.length;
				 var lengthTr = checkTr.length; 
				 var lastL=10-dttaLength;
				 if(lastL<0){lastL=0}
				 if(dttaLength+lengthTr >10){
					 layer.msg("已超过列表上线，剩余"+lastL+"条可选择" )
					 return false;
				 }
			 })  
			 $.each(checkTr,function(){ 
				 var info={}; 
				 info.code=tables.attr("code");
				 info.projectId=projectInfo.id;
				 info.subCode=thatTable.attr("code");
				 info.titleId=thatTable.attr("titleid");	 
				 var fileds=$(this).find("td[dnval]");
				 $.each(fileds,function(){ 
					 var key =$(this).attr("name");
					 var val = $(this).attr("dnval"); 
					 info[key] = val;
				 }) 
				 infoListDN.push(info);
				 
			 }) 
			 //团队的接口要变
			 dataDN.dataList=infoListDN;  
			  sendPostRequestByJsonObj(
					    platformUrl.saveTeamMember,
					    dataDN,
			    function(data) { 
					layer.msg("保存成功")	 
					$("#popbg").remove();
					$("#powindow").remove();
					$("body").css("overflow-y","auto");
			}) 
		 }
	 })
//	 全部成功之后进入倒计时页面且只针对页面方式的保存 
	 //加个对于页面的判断
//	 $(".jumpBox").show()
//	 timeOut(3,$("#time"));
} 
function saveDNsame(thatTable,dataDN,length,tabName) {
		 var infoListDN=[];
		 var checkTr=thatTable.find("tbody input[type=checkbox]:checked").closest("tr");
		 var lastL = 10-length;
		 if(length+checkTr.length >10){ 
			 layer.msg(tabName+"已超过列表上线，剩余"+lastL+"条可选择" )
			 return false;
		 }
		 $.each(checkTr,function(){ 
			 var that =$(this),
			 info={};
			 info.subCode= thatTable.attr("code"); 
			 info.titleId=thatTable.attr("titleId");
		 	var fileds=that.find("td[dnval]")
			 $.each(fileds,function(){ 
				 var key =$(this).attr("name");
				 var val = $(this).attr("dnval");  
				 if(val==undefined||val=="undefined"){val=""}
				 info[key] = val;
			 })		
			 infoListDN.push(info);
		 })   
		 dataDN.infoTableModelList=infoListDN;
		 sendPostRequestByJsonObj(
				 platformUrl.saveOrUpdateInfo,
				    dataDN,
		    function(data) { 
				 layer.msg("保存成功")	 
				 $("#popbg").remove();
				 $("#powindow").remove();
				 $("body").css("overflow-y","auto");   
				 refreshSection("NO9_1")
		})
 }   
//倒计时
function timeOut(num,dom) { 
	if(dom.length<=0){
		return;
	}
    var i = num; 
    setInterval(function(){  
    		if(i == 0) {forwardWithHeader(Constants.sopEndpointURL + "/galaxy/project/detail/"+projectId+ "?backurl=list");
			}
    		dom.text(i--);
 
    },1000); 
}; 