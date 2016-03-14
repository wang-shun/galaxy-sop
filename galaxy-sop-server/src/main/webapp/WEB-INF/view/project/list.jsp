<%@ page language="java" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>繁星SOP-添加项目</title>
<link href="<%=path %>/css/axure.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet"/>
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
<!-- jsp文件头和头部 -->
<link href="<%=path %>/ueditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<jsp:include page="../common/taglib.jsp" flush="true"></jsp:include>
<script src="<%=path %>/js/my_ext.js"></script>
<script src="<%=path %>/js/my.js"></script>
<!-- 分页二css+四js -->
<link rel="stylesheet" href="<%=path %>/bootstrap-table/bootstrap-table.css"  type="text/css">
<script src="<%=path %>/js/bootstrap-v3.3.6.js"></script>
<script src="<%=path %>/bootstrap-table/bootstrap-table-xhhl.js"></script>
<script src="<%=path %>/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="<%=path %>/js/init.js"></script>

<!-- 富文本编辑器 -->
<script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/dialogs/map/map.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/umeditor.min.js"></script>
<script type="text/javascript" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>

<body>

<jsp:include page="../common/header.jsp" flush="true"></jsp:include>

<div class="pagebox clearfix">
	<jsp:include page="../common/menu.jsp" flush="true"></jsp:include>
	<!--右中部内容-->
 	<div class="ritmin">
    	<h2>我的项目</h2>
    	<input type="hidden" id="project_id" value=""/>
        <!--页眉-->
        <div class="top clearfix">
        	<!--按钮-->
            <div class="btnbox_f btnbox_f1 clearfix">
                <a href="添加项目.html" class="pubbtn bluebtn ico c4">添加项目</a>
                <a href="编辑项目.html" class="pubbtn bluebtn ico c5">编辑</a>
            </div>
        </div>
        <!-- 搜索条件 -->
		<div class="min_document clearfix">
			<div class="bottom searchall clearfix" id="custom-toolbar">
				<dl class="fmdl fml fmdll clearfix">
	              <dt>项目类别：</dt>
	              <dd>
	                <select name="projectType">
	                  <option value="">全部</option>
	                  <option value="projectType:1">外部投资</option>
	                  <option value="projectType:2">内部创建</option>
	                </select>
	              </dd>
	            </dl>
	            <dl class="fmdl fml fmdll clearfix">
	              <dt>项目进度：</dt>
	              <dd>
	                <select name="projectProgress">
	                  <option value="">全部</option>
	                  <option value="projectProgress:1">接触访谈</option>
	                  <option value="projectProgress:2">内部评审</option>
	                  <option value="projectProgress:3">CEO评审</option>
	                  <option value="projectProgress:4">立项会</option>
	                  <option value="projectProgress:5">投资意向书</option>
	                  <option value="projectProgress:6">尽职调查</option>
	                  <option value="projectProgress:7">投资决策会</option>
	                  <option value="projectProgress:8">投资协议</option>
	                  <option value="projectProgress:9">股权交割</option>
	                  <!-- <option value="projectProgress:10">投后运营</option> -->
	                </select>
	              </dd>
	            </dl>
				<dl class="fmdl fmdll clearfix">
					<dt></dt>
					<dd>
						<input type="text" class="txt" id="search_text" placeholder="请输入姓名或手机号" />
					</dd>
					<dd>
						<button type="submit" class="bluebtn ico cx" name="querySearch">搜索</button>
					</dd>
				</dl>
			</div>
		</div>
		<div class="tab-pane active" id="view">	
			<table id="data-table" data-url="project/spl" data-height="555" 
				data-page-list="[1, 5, 50]" data-toolbar="#custom-toolbar">
				<thead>
				    <tr>
				    	<th data-field="projectCode" data-align="center" class="data-input">项目编码</th>
			        	<th data-field="projectName" data-align="center" class="data-input">项目名称</th>
			        	<th data-field="progress" data-align="center" class="data-input">项目进度</th>
			        	<th data-field="type" data-align="center" class="data-input">项目类型</th>
			        	<th data-field="createDate" data-align="center" class="data-input">创建日期</th>
			        	<th data-align="center" class="col-md-2" data-formatter="editor">操作</th>
 					</tr>	
 				</thead>
			</table>
           </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp" flush="true"></jsp:include></body>
<script src="<%=path %>/js/plupload.full.min.js" type="text/javascript"></script>
<script src="<%=path %>/js/pprogress.js" type="text/javascript"></script>
<script src="<%=path %>/js/teamSheet.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/js/axure.js" type="text/javascript"></script>
<script type="text/javascript">
	createMenus(5);
	/**
	 * 分页数据生成操作内容
	 */
	function editor(value, row, index){
		var id=row.id;
		var options = "<a href='#' data-btn='myproject' onclick='info(" + id + ")'>查看</a>";
		options += "<a href='<%=path%>/galaxy/upp/"+id+"'>修改</a>";
		return options;
	}
	/**
	 * 查看项目阶段详情的弹出层
	 */
	function info(id){
		var _url='<%=path%>/galaxy/ips';
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				$(".myprojecttc .tabtable").tabchange();
				$('.searchbox').toggleshow();
				leicj();
				/**
				 * 加载项目详情数据
				 */
				sendGetRequest(platformUrl.detailProject + id, {}, function(data){
					$("#project_name").text(data.entity.projectName);
					$("input[name='projectId']").val(data.entity.id);
					$("#project_id").val(id);
					var progress = data.entity.projectProgress;
					progress = progress.replace(":","_");
					var index = progress.substr(progress.length-1,1);
					for(var i = 1; i<10; i++){
						if(i > index){
							//当前阶段之后的tab变为不可用
							$("#projectProgress_" + i).addClass("disabled");
						}
						if(i == 1){
							tiggerTable($("#" + progress + "_table"),3);
						}
						if(i == 5){
							tzyxs(0);
						}
						if(i == 8){
							tzxy(data.entity.projectType);
						}
						if(i == 9){
							projectProgress9(id);
						}
						if(i == 6){
							jzdc();
						}
						
						//为Tab添加点击事件，用于重新刷新
						$("#projectProgress_" + i).on("click",function(){
							var id = $(this).attr("id");
							var indexNum = id.substr(id.length-1,1);
							var pp = data.entity.projectProgress;
							var pNum = pp.substr(pp.length-1,1);
							if(indexNum == '1'){
								if(parseInt(indexNum) < parseInt(pNum)){
									$("#qdnbps").remove();
								}
							    $("#projectProgress_1_con").css("display","block");
								tiggerTable($("#projectProgress_1_table"),3);
							}else if(indexNum == '2'){
							    $("#projectProgress_2_con").css("display","block");
								tiggerTable($("#projectProgress_2_table"),3);
							}else if(indexNum == '3'){
								if(parseInt(indexNum) < parseInt(pNum)){
									$("#lxhpq").remove();
								}
								$("#projectProgress_3_con").css("display","block");
								tiggerTable($("#projectProgress_3_table"),3);
							} else if(indexNum == '4'){
							    $("#projectProgress_4_con").css("display","block");
							    tiggerTable($("#projectProgress_4_table"),3);
							} else if(indexNum == '5'){
								$("#projectProgress_7_con").css("display","none");
								$("#projectProgress_5").addClass("on");
								$("#projectProgress_5_con").css("display","block");
								if(parseInt(indexNum) < parseInt(pNum)){
									tzyxs(1);
								}else{
									tzyxs(0);
								}
							}else if(indexNum == '6'){
								$("#projectProgress_5_con").css("display","none");
								 $("#projectProgress_6_con").css("display","block");
								 tiggerTable($("#projectProgress_6_table"),3);
								 if(parseInt(indexNum) < parseInt(pNum)){
									 $("#jzdc_options").remove();
								 }
							}else if(indexNum == '7'){
								$("#projectProgress_6_con").css("display","none");
								$("#projectProgress_7_con").css("display","block");
								 tiggerTable($("#projectProgress_7_table"),3);
							}else if(indexNum == '8'){
								$("#projectProgress_7_con").css("display","none");
								$("#projectProgress_8_con").css("display","block");
								 if(parseInt(indexNum) < parseInt(pNum)){
									 $("#tzxy_options").remove();
								 }
							}else if(indexNum == '9'){
								$("#projectProgress_8_con").css("display","none");
								$("#projectProgress_9").addClass("on");
								$("#projectProgress_9_con").css("display","block");
								projectProgress9($("#project_id").val());
							}
						});
					}
					$("#" + progress).addClass("on");
					$("#" + progress + "_con").css("display","block");
				},null);
			}
		});
		return false;
	}
	/**
	 * 上传接触访谈纪要弹出层
	 */
	function air(){
		var _url='<%=path%>/galaxy/air';
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				$(".meetingtc").tabchange();
				$('.searchbox').toggleshow();
				leicj();
				//初始化文件上传
				toinitUpload(platformUrl.stageChange, "select_btn","file_object","save_interview",
						function getSaveCondition(){
							var	condition = {};
							var pid = $("#project_id").val();
							var viewDateStr = $("#viewDate").val();
							var viewTarget = $.trim($("#viewTarget").val());
							var um = UM.getEditor('viewNotes');
							var viewNotes = $.trim(um.getContent());
							var fileId = $("#viewfileID").val();
							if(pid == null || pid == ""){
								return;
							}
							if(viewDateStr == null ||  viewDateStr == ""){
								alert("日期不能为空");
								return false;
							}
							if(viewTarget == null ||  viewTarget == ""){
								alert("对象不能为空");
								return false;
							}
							condition.pid = pid;
							condition.stage = "projectProgress:1";
							condition.createDate = viewDateStr;
							condition.target = viewTarget;
							condition.content = viewNotes;
							condition.fileId = fileId;
							/*var	condition = {
								"pid" : pid,
								"stage" : "projectProgress:1",
								"createDate" : viewDate,
								"target" : viewTarget,
								"content" : viewNotes,
								"fileId" : fileId
							};*/
							return condition;
						});
			}
		});
		return false;
	}
	
	/**
	 * 启动内部评审
	 */
	function startReview(){
		var pid = $("#project_id").val();
		if(pid != '' && pid != null && pid != undefined){
			sendGetRequest(platformUrl.startReview + pid, {}, function(data){
			});
		}
	}
	
	/**
	 * 上传会议记录
	 */
	 function addMettingRecord(num,meetingType){
		var _url='<%=path %>/galaxy/mr';
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				$(".meetingtc").tabchange();
				$('.searchbox').toggleshow();
				leicj(meetingType);
				toinitUpload(platformUrl.stageChange, "meeting_select_btn","meeting_file_object","save_meeting",
						function getSaveCondition(){
							var	condition = {};
							var pid = $("#project_id").val();
							var meetingDateStr = $.trim($("#meeting_date").val());
							var meetingResult = $.trim($("input:radio[name='meetingResult']:checked").val());
							var um = UM.getEditor("meeting_notes");
							var meetingNotes = $.trim(um.getContent());
							if(pid == null || pid == ""){
								alert("项目不能为空");
								return;
							}
							if(meetingDateStr == null ||  meetingDateStr == ""){
								alert("日期不能为空");
								return;
							}
							if(meetingType == null ||  meetingType == ""){
								alert("会议类型不能为空");
								return;
							}
							if(meetingResult == null ||  meetingResult == ""){
								alert("结果不能为空");
								return;
							}
							condition.pid = pid;
							condition.stage = "projectProgress:"+num;
							condition.createDate = meetingDateStr;
							condition.meetingType = meetingType;
							condition.result = meetingResult;
							condition.content = meetingNotes;
							return condition;
						});
			}
		});
		return false;
	}
	
	 /**
	  * 申请立项会操作
	  */
	function toEstablishStage(){
		var pid = $("#project_id").val();
		if(pid != '' && pid != null && pid != undefined){
			sendGetRequest(platformUrl.toEstablishStage + pid, {}, function(data){
			});
		}
	}
	
	 /**
	  * 动态生成投资意向书阶段HTML
	  */
	function tzyxs(flag){
		 var pid = $("#project_id").val();
		 if(pid != '' && pid != null){
			 /**
			  *  生成尽职调查报告列表
			  */
			 sendGetRequest(
					 sopContentUrl + '/galaxy/project/progress/proFileInfo/'+pid+'/5',
					 null, function(data){
						 var json = eval(data);
						 var dataList=json.entityList;
							for(var p in dataList){
								var handlefile="";
						        if (dataList[p].fileStatusDesc == "缺失") { 
						        	handlefile ='<td><a href="javascript:; " class="pubbtn fffbtn llpubbtn" onclick="addFile(5,0);">上传投资意向书</a></td>';
								}else{
									handlefile = '<td><a href="javascript:; " class="pubbtn fffbtn llpubbtn">更新投资意向书</a><a  href="javascript:; " class="pubbtn fffbtn lpubbtn" onclick="addFile(5,1);">上传签署证明</a></td>';
								}
						        var htmlhead = '<div id="tzyxs_options" class="btnbox_f btnbox_f1 btnbox_m clearfix">'+
						        '<a href="javascript:;" class="pubbtn fffbtn llpubbtn">下载投资意向书模板</a>'+
						        handlefile+'</div>'+
							        '<div class="process clearfix">'+
							        '<h2>投资意向书盖章流程</h2>'+
							        '<img src="img/process.png" alt="">'+
							        '</div>';
							        
								 var htmlstart=htmlhead+'<table width=\"100%" cellspacing="0" cellpadding="0" >'+
									             '<thead>'+
									                '<tr>'+
									                 '<th>业务分类</th>'+
									                 '<th>创建日期</th>'+
									                 '<th>存储类型</th>'+
									                 '<th>更新日期</th>'+
									                 '<th>档案状态</th>'+
									                 '<th>查看附件</th>'+
									                 '</tr>'+
									            '</thead>'+                                                                                                                                   
									             '<tbody>';
										var typehtml = "";
										if (typeof(dataList[p].fType) == "undefined") { 
											typehtml ='<td>未知</td>';
										}else{
											typehtml = '<td>'+dataList[p].fType+'</td>';
										}
										
										var endhtml ="";
										if (dataList[p].fileStatusDesc == "缺失") { 
											endhtml ='<td>缺失</td>';
										}else{
											endhtml = '<td><a href="javascript:;" onclick="handleDownload('+dataList[p].id+');" class="blue">附件</a></td>';
										}
										
										htmlstart +='<tr>'+
										'<td>'+dataList[p].fWorktype+'</td>'+
										'<td>'+dataList[p].createDate+'</td>'+
										typehtml+
										'<td></td>'+'<td>'+dataList[p].fileStatusDesc+'</td>'+
										endhtml+
										'</tr>';   
										
							}
							var htmlend= '</tbody></table>';
							$("#projectProgress_5_con").html(htmlstart+htmlend);
							if(flag == 1){
					        	$("#tzyxs_options").remove();
					        }
					 });
		 }
	 }
		 
	/**
	 * 上传文档
	 */
	 function addFile(num,i){
		var _url='<%=path %>/galaxy/tzyx';
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				$(".meetingtc").tabchange();
				$('.searchbox').toggleshow();
				leicj();
				if(i == 1){
					$("#voucherType").attr("checked","checked");
				}
				toinitUpload(platformUrl.stageChange, "select_file_btn","file_obj","save_file_btn",
						function getSaveCondition(){
					var	condition = {};
					var pid = $("#project_id").val();
					if(pid == null || pid == ""){
						alert("项目不能为空");
						return;
					}
					var type = $("input[name='fileSource']").val();
					if(type == null || type == ""){
						alert("档案来源不能为空");
						return;
					}
					var fileType = $("#fileType").val();
					if(fileType == null || fileType == ""){
						alert("存储类型不能为空");
						return;
					}
					var fileWorktype = $("#fileWorkType").val();
					if(fileWorktype == null || fileWorktype == ""){
						alert("业务分类不能为空");
						return;
					}
					var voucherType = $("input[id='voucherType']:checked").val();
					condition.pid = pid;
					condition.stage = "projectProgress:"+num;
					condition.type = type;
					condition.fileType = fileType;
					condition.fileWorktype = fileWorktype;
					condition.voucherType = voucherType;
					return condition;
				});
			}
		});
		return false;
	}
	 
	 /**
	  * 尽职调查
	  */
	 function jzdc(){
		 var pid = $("#project_id").val();
		 if(pid != '' && pid != null){
			 /**
			  *  生成尽职调查报告列表
			  */
			 sendGetRequest(
					 sopContentUrl + '/galaxy/project/progress/proFileInfo/'+pid+'/6', 
					 null, function(data){
				 var html = "";
				 $.each(data.entityList, function(i,o){
					 html += "<tr>";
					 if(o.fileWorktype == 'fileWorktype:1'){
						 html += "<td>业务尽职调查报告";
						 html += "</td><td>" + o.createDate + "</td>";
						 html += "<td>投资&杨一</td><td>文档</td>";
					 }else if(o.fileWorktype == 'fileWorktype:2'){
						 html += "<td>人事尽职调查报告";
						 html += "</td><td>" + o.createDate + "</td>";
						 html += "<td>人事部</td><td>文档</td>";
					 }else if(o.fileWorktype == 'fileWorktype:3'){
						 html += "<td>法务尽职调查报告";
						 html += "</td><td>" + o.createDate + "</td>";
						 html += "<td>法务部</td><td>文档</td>";
					 }else if(o.fileWorktype == 'fileWorktype:4'){
						 html += "<td>财务尽职调查报告";
						 html += "</td><td>" + o.createDate + "</td>";
						 html += "<td>财务部</td><td>文档</td>";
					 }
					 if(o.fileStatus == 'fileStatus:1'){
						 html += "<td>缺失</td>";
						 if(o.fileWorktype != 'fileWorktype:1'){
							 html += "<td><a href='javascript:void(0);'>催办</a></td>";
						 }else{
							 html += "<td></td>";
						 }
						 html += "<td>无</td>";
					 }else if(o.fileStatus == 'fileStatus:2'){
						 html += "<td>已上传</td>";
						 html += "<td></td>";
						 html += "<td><a href='javascript:void(0);'>" + o.fileName + "</a></td>";
					 }else if(o.fileStatus == 'fileStatus:3'){
						 html += "<td>已签署</td>";
						 html += "<td></td>";
						 html += "<td><a href='javascript:void(0);'>" + o.fileName + "</a></td>";
					 }
					 html += "</tr>";
			   	 });
				 $("#fileList").append(html);
			 });
		 }
	}
	 /**
	 * 点击上传业务尽调报告按钮
	 */
	function uploadYwjd(){
		var _url='<%=path %>/galaxy/jzdc';
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				$(".meetingtc").tabchange();
				$('.searchbox').toggleshow();
				leicj();
				toinitUpload(platformUrl.stageChange, "select_file_btn","file_obj","save_file_btn",
						function getSaveCondition(){
					var	condition = {};
					var pid = $("#project_id").val();
					if(pid == null || pid == ""){
						alert("项目不能为空");
						return;
					}
					var type = $("input[name='fileSource']").val();
					if(type == null || type == ""){
						alert("档案来源不能为空");
						return;
					}
					var fileType = $("#fileType").val();
					if(fileType == null || fileType == ""){
						alert("存储类型不能为空");
						return;
					}
					var fileWorktype = $("#fileWorkType").val();
					if(fileWorktype == null || fileWorktype == ""){
						alert("业务分类不能为空");
						return;
					}
					condition.pid = pid;
					condition.stage = "projectProgress:6";
					condition.type = type;
					condition.fileType = fileType;
					condition.fileWorktype = fileWorktype;
					return condition;
				});
			}
		});
		return false;
	}
	/**
	 * 点击申请投决会按钮
	 */
	function inTjh(){
		var pid = $("#project_id").val();
		if(pid != '' && pid != null && pid != undefined){
			sendGetRequest(
					platformUrl.inTjh + pid,
					null,
					function(data){
						
					});
		}
	}
	/**
	 * 动态生成投资协议的HTML
	 */
	function tzxy(projectType){
		var pid = $("#project_id").val();
		if(pid != '' && pid != null){
			sendPostRequestByJsonObj(
					platformUrl.searchSopFileListWithoutPage,
					{"projectId" : pid},
					function(data){
						var _table = $("#teamSeheetDataGrid");
						var _tbody = _table.find("tbody");
						_tbody.empty();
						$.each(data.entityList,function(){
							var $tr = $('<tr></tr>');
							$tr.append('<td>'+this.fWorktype+'</td>') ;
							if(this.fileType){
								$tr.append('<td>'+this.fType+'</td>');
								$tr.append('<td>'+this.updatedDate+'</td>') ;
							}else{
								$tr.append('<td>未知</td>');
								$tr.append('<td></td>') ;
							}	
							$tr.append('<td>'+this.fileStatusDesc+'</td>') ;
							if(this.fileKey == null){	
								$tr.append('<td><a href="javascript:tzxyAlert(8,0);" class="blue">上传</a></td>');
							}else{
								$tr.append('<td><a href="javascript:; " class="blue">查看</a></td>'); 	
							}
							$tr.append('<td><a href="javascript:tzxyAlert(8,1);" class="blue">上传</a></td>') ;
							_tbody.append($tr);
						});
					}
			);	
			if(projectType == 'projectType:2'){
				$("#stock_transfer_model").remove();
			}
		}
	}
	 
	/**
	 * 投资协议弹出层
	 */
	 function tzxyAlert(num,i){
		var _url='<%=path %>/galaxy/tzxy';
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				$(".meetingtc").tabchange();
				$('.searchbox').toggleshow();
				leicj();
				if(i == 1){
					$("#voucherType").attr("checked","checked");
				}
				toinitUpload(platformUrl.stageChange, "select_file_btn","file_obj","save_file_btn",
						function getSaveCondition(){
					var	condition = {};
					var pid = $("#project_id").val();
					if(pid == null || pid == ""){
						alert("项目不能为空");
						return;
					}
					var type = $("input[name='fileSource']").val();
					if(type == null || type == ""){
						alert("档案来源不能为空");
						return;
					}
					var fileType = $("#fileType").val();
					if(fileType == null || fileType == ""){
						alert("存储类型不能为空");
						return;
					}
					var fileWorktype = $("#fileWorkType").val();
					if(fileWorktype == null || fileWorktype == ""){
						alert("业务分类不能为空");
						return;
					}
					var voucherType = $("input[id='voucherType']:checked").val();
					var hasStockTransfer = $("input[id='stock_transfer']:checked").val();
					condition.pid = pid;
					condition.stage = "projectProgress:"+num;
					condition.type = type;
					condition.fileType = fileType;
					condition.fileWorktype = fileWorktype;
					condition.voucherType = voucherType;
					condition.hasStockTransfer=hasStockTransfer;
					return condition;
				});
			}
		});
		return false;
	}
</script>
</html>