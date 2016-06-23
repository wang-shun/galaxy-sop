<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.galaxyinternet.com/fx" prefix="fx" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<% 
	String path = request.getContextPath(); 
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>项目详情</title>

<link href="<%=path %>/css/axure.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/css/beautify.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet"/>
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->

<!-- bootstrap-table -->
<link rel="stylesheet" href="<%=path %>/bootstrap/bootstrap-table/bootstrap-table.css"  type="text/css">
<!-- 日历插件 -->
<link href="<%=path %>/bootstrap/bootstrap-datepicker/css/bootstrap-datepicker3.css" type="text/css" rel="stylesheet"/>
<!-- 富文本编辑器 -->
<link href="<%=path %>/ueditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">

<jsp:include page="../../common/taglib.jsp" flush="true"></jsp:include>
<script src="<%=path %>/js/sopinfo.js"></script>
</head>


<body>

<jsp:include page="../../common/header.jsp" flush="true"></jsp:include>

<div class="pagebox clearfix">
	<!--左侧导航-->
	<jsp:include page="../../common/menu.jsp" flush="true"></jsp:include>
     
    <!--右中部内容-->
 	<div class="ritmin">
 	
    	<jsp:include page="sopcommon.jsp" flush="true"></jsp:include>


		<div class="new_left">
			<div class="tabtable assessment label_static">
				<!-- tab标签 -->
	            <ul class="tablink tablinks">
	                <li><a href="javascript:;" onclick="showTabs(${pid},0)">基本信息</a></li>
	                <c:choose>
	             	<c:when test="${aclViewProject==true}">
	                <li><a href="javascript:;" onclick="showTabs(${pid},1)">团队成员</a></li>
	                <li><a href="javascript:;" onclick="showTabs(${pid},2)">股权结构</a></li>
	                <li class="on"><a href="javascript:;" onclick="showTabs(${pid},3)">访谈记录</a></li>
	                <li><a href="javascript:;" onclick="showTabs(${pid},4)">会议纪要</a></li>
	                <li><a href="javascript:;" onclick="showTabs(${pid},5)">项目文档</a></li>
	                <li><a href="javascript:;" onclick="showTabs(${pid},6)">操作日志</a></li>
	                </c:when>
	                <c:otherwise>
	                <li class="no"><a href="javascript:;">团队成员</a></li>
	                <li class="no"><a href="javascript:;">股权结构</a></li>
	                <li class="no"><a href="javascript:;">访谈记录</a></li>
	                <li class="no"><a href="javascript:;">会议纪要</a></li>
					<li class="no"><a href="javascript:;">项目文档</a></li>
	                <li class="no"><a href="javascript:;">操作日志</a></li> 
	                </c:otherwise>
	             	</c:choose>
	            </ul>


				<!-- 访谈记录
				<div data-tab="con" >
  -->
					<div class="member interview">
						<c:if test="${aclViewProject==true}">

						<!--按钮-->
						<div class="top clearfix">
							<div class="btnbox_f btnbox_f1 clearfix">
								<a href="#"  id="tjftjl" onclick="toAddProInterview();" class="pubbtn bluebtn ico c4 add_prj add_interview" style="display: none;"></a>
								<!-- <a href="#"  id="qdnbps" class="pubbtn fffbtn lpubbtn option_item_mark" style="display: none;"></a> -->
							</div>
						</div>

						<!-- 接触访谈信息 -->
						<div class="min_document clearfix" id="projectProgress_1_table_custom-toolbar" >
						<div class="bottom searchall clearfix">
							<input type="hidden" id="projectId" name="projectId" value="">   <!-- 项目id -->
						<!--   <dl class="fmdl fmmr fmdll clearfix">
								<dt>访谈日期：</dt>
								<dd>
									<!-- <input class="form-control" type="date" id = "startTime" name="startTime"  /> 
									<input type="text" class="datepicker txt time" readonly id="startTime" name="startTime" style="height: 23px;" /> 
									<span>至</span>
									<input type="text" class="datepicker txt time" readonly id="endTime" name="endTime" style="height: 23px;" />
								</dd>
								<dd>
									<a href="javascript:;" class="search_icon" action="querySearch">查询</a>
								</dd>
							</dl>
							-->
						</div>
						</div>
						<table id="projectProgress_1_table" class="commonsize"
							data-url="<%=path%>/galaxy/project/progress/queryInterview" 
							data-id-field="id" 
							data-toolbar="#projectProgress_1_table_custom-toolbar">
							<thead>
								<tr>
									<th data-field="viewinfo" data-align="left" data-formatter="intervierInfoFormat" data-class="no1">访谈概况</th>
									<th data-field="viewNotes" data-align="left" data-formatter="tc_viewNotesFormat_noinfo" data-class="no2">访谈记录</th>
									<th data-field="oper" data-align="left" data-formatter="viewOperFormat">操作</th>
								</tr>
							</thead>
						</table>
						</c:if>
					</div>

				<!-- </div>
				tab end-->
				
			</div>
		</div>

		<!--右边-->
        <jsp:include page="./includeRight.jsp" flush="true"></jsp:include>
        
    </div>
 
</div>

<jsp:include page="../../common/footer.jsp" flush="true"></jsp:include>


<!-- 分页二css+四js -->
<script src="<%=path %>/js/bootstrap-v3.3.6.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-table/bootstrap-table-xhhl.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<!-- file -->
<script src="<%=path %>/js/plupload.full.min.js" type="text/javascript"></script>
<script src="<%=path %>/js/plupload/zh_CN.js" type="text/javascript"></script>
<!-- time -->
<script src="<%=path %>/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/js/datepicker-init.js"></script>
<!-- 富文本编辑器 -->
<script id="d" type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/umeditor.min.js"></script>
<script id="c" type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/umeditor.config.js"></script>
<script id="b" type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/dialogs/map/map.js"></script>
<script id="e" type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>



<script>

	var proinfo = '${proinfo}';
	//proinfo = JSON.parse(proinfo);
	var proid = '${pid}';
	var pname = '${pname}';
	var interviewSelectRow = null;
	//var admin = "${fx:isCreatedByUser('project',pid) }";

$(function(){
	createMenus(5);
	
	$("#projectId").val(proid);
	
	$('#projectProgress_1_table').bootstrapTable({
		queryParamsType: 'size|page', // undefined
		pageSize:5,
		pageList : [5, 10, 20 ],
		showRefresh : false ,
		sidePagination: 'server',
		method : 'post',
		pagination: true,
		uniqueId: "id", 
		idField : "id",
		clickToSelect: true,
        search: false,
	});
	
	//check to show or not not show qdnbps button
	/* if(projectInfo.projectStatus == 'projectStatus:2' || projectInfo.projectStatus == 'projectStatus:3' || projectInfo.projectStatus == 'meetingResult:3' || admin!="true"){
		//$('#tjftjl').remove();
		$('#tjftjl').removeAttr("onclick");
	} else {
		if(index == 1){
			checkToShowBut();
		}
		$('#tjftjl').show();
		$('#tjftjl').text("添加访谈记录");
	} */
	if(projectInfo.projectStatus == 'projectStatus:2' || projectInfo.projectStatus == 'projectStatus:3' || projectInfo.projectStatus == 'meetingResult:3' || admin!="true"){
		//$('#tjftjl').remove();
		$('#tjftjl').removeAttr("onclick");
	} else {
		$('#tjftjl').show();
		$('#tjftjl').text("添加访谈记录");
	}
});	
	

//检测是否显示启动内部评审按钮
function checkToShowBut(){
	if(viewList && viewList.length>0){
		toshowbut();
	}
}
function toshowbut(){
	//$('#qdnbps').text("启动内部评审");
	$('#qdnbps').on('click', function() {
		startReview();
	})	
	$('#qdnbps').show();
}
function tohidebut(){
	$('#qdnbps').hide();
	$('#qdnbps').text("");
	$('#qdnbps').off();
}	

/**
 * 添加接触访谈纪要弹出层
 */
function toAddProInterview(){
	var _url=Constants.sopEndpointURL + '/galaxy/air';
	$.getHtml({
		url:_url,
		data:"",
		okback:function(){
			initViewUpload();
			//$('.edui-container').show();
		}
	});
	return false;
}



//plupload 上传对象初始化, 绑定保存saveViewFile
function initViewUpload() {
	var viewuploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : $("#select_btn")[0], 
		url : platformUrl.stageChange,
		multipart:true,
		multi_selection:false,
		filters : {
			max_file_size : '25mb',
			mime_types: paramsFilter(1)
		},

		init: {
			//上传按钮点击事件 - 开始上传
			PostInit: function(up) {
				$("#save_interview").click(function(){
					$("#save_interview").addClass("disabled");
					var res = getInterViewCondition('y',proid, "viewDate", "viewTarget", "viewNotes");
					if(res == false || res == "false"){
						up.stop();
						$("#save_interview").removeClass("disabled");
						return;
					}
					res.stage = "projectProgress:1";
					res.pid = proid;
					res.createDate = res.viewDateStr;
					res.content = res.viewNotes;
					res.target = res.viewTarget;
					//var file = $("#fileName").val();
					if(up.files.length > 0){
						up.settings.multipart_params = res;  //viewuploader.multipart_params = { id : "12345" };
						viewuploader.start();
					}else{
						sendPostRequestByJsonObj(platformUrl.stageChange,res,function(data){
							var result = data.result.status;
							if(result == "ERROR"){ //OK, ERROR
								$("#save_interview").removeClass("disabled");
								layer.msg(data.result.message);
								return;
							}else{
								layer.msg("保存成功", {time : 500});
								/* if(index == 1 && $("#qdnbps").css('display')=='none' ){
									toshowbut();
								} */
								toFormatNearNotes();
								var _this = $("#projectProgress_1_table");
								if(_this == null || _this.length == 0 || _this == undefined){
									removePop1();
								}else{
									$("#projectProgress_1_table").bootstrapTable('refresh');
									removePop1();
								}
							}
						});
					}
					return false;
				});
			},
			
			FilesAdded: function(up, files) {
				if(viewuploader.files.length >= 2){
					viewuploader.splice(0, viewuploader.files.length-1)
				}
				plupload.each(files, function(file) {
					$("#file_object").val(file.name);
				});
			},
			
			UploadProgress: function(up, file) { 
			},
			
			FileUploaded: function(up, files, rtn) {  //上传回调
				var response = $.parseJSON(rtn.response);
				var rs = response.result.status;
				if(rs == "ERROR"){ //OK, ERROR
					$("#save_interview").removeClass("disabled");
					$("#file_object").val("");
					viewuploader.splice(0, meetuploader.files.length)
					layer.msg(response.result.message);
					return false;
				}else{
					layer.msg("保存成功", {time : 500});
					/* if(index == 1 && $("#qdnbps").css('display')=='none' ){
						toshowbut();
					} */
					toFormatNearNotes();
					var _this = $("#projectProgress_1_table");
					if(_this == null || _this.length == 0 || _this == undefined){
						removePop1();
					}else{
						$("#projectProgress_1_table").bootstrapTable('refresh');
						removePop1();
					}
				}
			},
			
			BeforeUpload:function(up){
			},
			
			Error: function(up, err) {
				$("#save_interview").removeClass("disabled");
				$("#file_object").val("");
				layer.msg(err.message);
			}
		}
	});

	viewuploader.init();
}

/**
 *  查看  or 编辑  
 */
function viewOperFormat(value,row,index){  
	var info = "<span class=\"see blue\"  onclick=\"notesInfoEdit('"+row.id+"','v')\" >查看</span>";
	var edit = "";
	
	if(userId==row.createdId){
		edit = " <span class=\"edit blue\"  onclick=\"notesInfoEdit('"+row.id+"','e')\" >编辑</span>";
	}
	return info + edit;
}


function notesInfoEdit(selectRowId,type){
	interviewSelectRow = $('#projectProgress_1_table').bootstrapTable('getRowByUniqueId', selectRowId);
	var _url = Constants.sopEndpointURL+"/galaxy/project/progress/interViewLog";
	$.getHtml({
		url:_url,
		data:"",
		okback:function(){
			var um=UM.getEditor('viewNotes');
			um.setContent(interviewSelectRow.viewNotes);
			if(type == 'v'){
				$("#interviewsave").remove();
			}
		}
	});
	return false;
}

function interviewsave(){  
	var um = UM.getEditor('viewNotes');
	var log = um.getContent();
	var id= interviewSelectRow.id;
	if(id != ''){
		sendPostRequestByJsonObj(platformUrl.updateInterview, {"id" : id, "viewNotes" : log}, function(data){
			if (data.result.status=="OK") {
				layer.msg("保存成功");
				//$(".meetingtc").find("[data-close='close']").click();
				removePop1();
				$("#projectProgress_1_table").bootstrapTable('refresh');
			} else {
				layer.msg(data.result.message);
			}
			
		});
	}
}


	

/**
 * 启动内部评审
 */
function startReview(){
	if(proid != '' && proid != null && proid != undefined){
		sendGetRequest(platformUrl.startReview + proid, {}, function(data){
			var result = data.result.status;
			if(result == "OK"){
				layer.msg("启动内部评审成功!");
				window.location.reload();
			}else{
				layer.msg(data.result.message);
			}
		});
	}
}
	
	
</script>
</html>

