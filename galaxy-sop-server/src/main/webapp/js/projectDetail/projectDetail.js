
//function init(){
//	
//}

//文档加载结束
//$(document).ready(init());
//所有资源加载结束

$(function(){
	$('.projectDetail').tabLazyChange({
		onchangeSuccess:function(index){
			switch(index){
				case 0: initTabInfo(projectId);  break;  //标签0:基本信息
				case 1: initTabProjectTeam(); break;  //标签1:团队成员
				case 2: initTabEquity(); break;  //标签2: 股权结构
				case 3: initTabInterview();   break;  //标签3:访谈记录
				case 4: initTabMeeting();   break;  //标签4:会议记录
				case 5: initTabDelivery();   break;  //标签5:交割前事项
				case 6: initTabAppropriation();   break;  //标签6:拨款信息
				case 7: initTabPostMeeting();   break;  //标签7:运营分析
				case 8: initTabSopFile(projectId);   break;  //标签8:项目文档
				case 9: initTabOperLog();   break;  //标签9:操作日志
				default: return false;
			}
			
			//右侧刷新	
		}
});
	
})

//基本信息
function initTabInfo(projectId){
	tabInfo.init(projectId);
}

//团队成员
function initTabProjectTeam(){
	$.getTabHtml({
		url : platformUrl.showPersonDetail +'/'+ projectId
	});
}
//股权结构
function initTabEquity(){
	$.getTabHtml({
		url : platformUrl.showShareDetail,
		data:{'id':projectId}
	});
}
//访谈记录
function initTabInterview(){
	
}
//会议纪要
function initTabMeeting(){
	
}
//交割前事项
function initTabDelivery(){
	
}
//拨款信息
function initTabAppropriation(){
	
}
//运营分析
function initTabPostMeeting(){
	$.getTabHtml({
		url : platformUrl.showOperationsAnalysis +'/'+ projectId
	});
}
//项目文档
function initTabSopFile(){
	tabFile.init(projectId);
	
}
//操作日志
function initTabOperLog(){
	var url = Constants.sopEndpointURL+"/galaxy/project/toprolog/" + projectId;
	$.getTabHtml({
		url : url
	});
}




