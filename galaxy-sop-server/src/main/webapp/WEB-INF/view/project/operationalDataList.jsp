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
<title>繁星</title>
<link href="<%=path %>/css/axure.css" type="text/css" rel="stylesheet"/>
<!-- jsp文件头和头部 -->
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path %>/js/validate/lib/tip-yellowsimple/tip-yellowsimple.css" />
<script type="text/javascript" src="<%=path %>/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/messages_zh.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/lib/jquery.poshytip.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/fx.validate.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/fx.validate-ext.js"></script>
<!-- time -->
<link href="<%=path %>/bootstrap/bootstrap-datepicker/css/bootstrap-datepicker3.css" type="text/css" rel="stylesheet"/>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/js/datepicker-init.js"></script>

<!-- 分页二css+四js -->
<script src="<%=path %>/js/bootstrap-v3.3.6.js"></script>
<link rel="stylesheet" href="<%=path %>/bootstrap/bootstrap-table/bootstrap-table.css"  type="text/css">
<link rel="stylesheet" href="<%=path %>/plugins/daterangepicker/css/font-awesome.min.css"  type="text/css">
<script src="<%=path %>/bootstrap/bootstrap-table/bootstrap-table-xhhl.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="<%=path %>/js/init.js"></script>
</head>

<body>

<jsp:include page="../common/header.jsp" flush="true"></jsp:include>

<div class="pagebox clearfix">
	<jsp:include page="../common/menu.jsp" flush="true"></jsp:include>
	<!--右中部内容-->
 	<div class="ritmin">
    	<!-- <h2>运营数据记录</h2> -->
    	<div class="new_tit_a">
    	  <a href="<%=path %>/galaxy/index">工作桌面</a><a href="#">>创投项目</a><a href="#">>${projectName }</a><a href="#">>运营分析</a>>运营数据记录
    	  <span class="b_span" style="float:right;"> 
            <a href="javascript:void(0);" onclick="backInitTabPostMeeting()" class="blue">返回&gt;</a>
          </span>
    	  
    	  </div>
    	
        <!--页眉-->
        <div class="top clearfix">
          <c:if test="${fx:hasRole(4)}">
            <a href="<%=path %>/galaxy/operationalData/addOperationalDataList/${projectId}"  style="width:130px; margin-top:5px;" id="addOperate" class="pbtn bluebtn h_bluebtn" >添加运营数据</a>
          </c:if>
        </div>	
        <!-- 搜索条件 -->
        <div class="min_document clearfix min_document_da"  id="custom-toolbar-operate">
          <div class="top clearfix search_adjust1 searchall">
            <input type="hidden" name="projectId" value="${projectId}">
          <dl class="fmdl fmdll clearfix"">
             <dt>运营数据统计区间：</dt>
              <dd>
	         <input type="text" class="datepicker-year-text txt time" name="operateDateStart"  /> 
	          </dd>
          </dl>     
         <dl>
            <dd>
             <span>至</span>
	<input type="text" class="datepicker-year-text txt time" name="operateDateEnd"  /> 
            </dd>
         </dl>
          <dl class="fmdl fmmt clearfix">
              <dd class="clearfix">
                <label><input type="checkbox" checked="checked" name="dataTypeMonth" value="0" id="month">月数据</label>
                <label><input type="checkbox" checked="checked" name="dataTypeQuarter" value="1" id="quarter">季数据</label>
              </dd>
              <dd>
                 <button type="submit" class="bluebtn ico cx" action="querySearch">搜索</button>
              </dd>
            </dl>   
            
        </div>
        </div>
       <div class="tab-pane active" id="view">		
       	  <!--  <table id="fileGridOperation">
			</table> --> 
			<table id="fileGridOperation" data-url="<%=path %>/galaxy/operationalData/operationalDataList"  
				data-toolbar="#custom-toolbar" data-show-refresh="true">
				<thead>
				    <tr>
				    	<th data-field="operationIntervalDate"  data-formatter="dataRange" class="data-input">运营数据统计区间</th>
			        	<th data-field="updateDate"  class="data-input">编辑时间</th>
			        	<th data-field="updateUserName"  class="data-input">编辑人</th>
			        	<th  class="col-md-2" data-formatter="editor" data-class="noborder">操作</th>
 					</tr>	
 				</thead>
			</table>
           </div>      
    </div>
</div>
<jsp:include page="../common/footer.jsp" flush="true"></jsp:include>
</body>


<script type="text/javascript">
$('input[name="operateDateStart"], input[name="operateDateEnd"]').val(new Date().format("yyyy"));

var transferingIds = "${fx:getTransferingPids()}".split(",");
var isflag= "${fx:hasRole(4)}";
createMenus(14);
var projectId = '${projectId}';

  
$(function(){
	 $("#quarter").click(function(){
	      if($(this).attr("checked") == "checked"){
	    	  $(this).removeAttr('checked');
	      }else{
	    	  $(this).attr("checked","checked");
	      }
	    })
	    $("#month").click(function(){
	    	 if($(this).attr("checked") == "checked"){
		    	  $(this).removeAttr('checked');
		      }else{
		    	  $(this).attr("checked","checked");
		      }
	    })
	
    $('input[name="operateDateStart"], input[name="operateDateEnd"]').val('');
	 var initParams;
	 $("#fileGridOperation").bootstrapTable({
			showRefresh : false ,
			sidePagination: 'server',
			method : 'post',
			pagination: true,
			queryParamsType: 'size|page',
		    pageNumber:1,            //初始化加载第一页，默认第一页
		    pageSize: 10,            //每页的记录行数（*）
		    pageList: [10, 20, 30],    //可供选择的每页的行数（*）
	        search: false,
	        //返回附带参数功能代码
	        queryParams:function(params){
	        	//if(getCookieValue("operationList")!=''){
	    		var ll = getCookieValue("operationList");
		    	if(getCookieValue("operationList")){	
		    		initParams = cookieOperator.pullCookie({_paramKey : 'operationList',_path : "/"});
		    		deleteCookie("operationList","/");
		    	}else{
		        	initParams=undefined;
		        }
		    	if(typeof(initParams) !== 'undefined'){
		        	$("input[name='operateDateStart']").val(initParams.operateDateStart);
		        	$("input[name='operateDateEnd']").val(initParams.operateDateEnd);
		        
		        	if(initParams.dataTypeMonth != '' && typeof(initParams.dataTypeMonth) !== 'undefined'){
		        		$("input[name='dataTypeMonth']").attr("checked","true");
		        	}else{
		        		$("input[name='dataTypeMonth']").removeAttr("checked");
		        	}
		        	
		        	if(initParams.dataTypeQuarter != '' && typeof(initParams.dataTypeQuarter) !== 'undefined'){
		        		$("input[name='dataTypeQuarter']").attr("checked","true");
		        	}else{
		        		$("input[name='dataTypeQuarter']").removeAttr("checked");
		        	}
		        	params.pageNum = initParams.pageNum - 1;
		        	params.pageSize = initParams.pageSize;
		    	}
		    	console.log(json_2_1(params,getToobarQueryOperationParams('custom-toolbar-operate')));
		    	return json_2_1(params,getToobarQueryOperationParams('custom-toolbar-operate'));
			},
	        onLoadSuccess: function(data){
	        	
	        	if(typeof(initParams) !== 'undefined' && initParams.pageNum != ''){
		    		if(initParams.pageNum==1){
		    			return;
		    		}else{
		    			$('.pagination li').removeClass('active');
		    			if($('.pagination .page-number').length< initParams.pageNum){
		    				for(var i=$('.pagination .page-number').length; i>0; i--){
		    					$('.pagination .page-number').eq(i).html('<a href="javascript:void(0)">'+i+'</a>');
		    				}
		    			}

		    			$('.pagination li').each(function(){
		    	    		if($(this).text()==initParams.pageNum){
		    	    			$(this).click();
		    	    		}
		    			})
		    		}
		    	}
		        initPageSize=10;
			
			}
		});
	 if('${fx:isTransfering(projectId)}' == 'true')
	 {
	 	$("#addOperate").hide();
	 	
	 	
	 }
  })
  
//根据toobar id 获取表单参数
function getToobarQueryOperationParams(ToolbarId){
	var toolbar = $("#"+ToolbarId);
	var query = {};
	toolbar.find("input[name][type!='checkbox']").each(function(){
		var input = $(this);
		var name = input.attr("name");
		var val = input.val();
		if(val!=''){
			query[name]=val;
		}
	});
	toolbar.find("input[type='radio']").each(function(){
		var input = $(this);
		var name = input.attr("name");
		if(input.attr("checked")=="checked"||input.prop("checked")==true){
			var val = input.val();
    		if(val!=''){
    			query[name]=val;
    		}
		}
	});
	toolbar.find("input[type='checkbox']").each(function(){
		var input = $(this);
		var name = input.attr("name");
		if(input.attr("checked")=="checked"||input.prop("checked")==true){
			var val = input.val();
    		if(val!=''){
    			query[name]=val;
    		}
		}
	});
	toolbar.find("select[name]").each(function(){
		var select = $(this);
		var name = select.attr("name");
		var val = select.val();
		if(val!=''){
			query[name]=val;
		}
	});
	return query;
}


  //查看 or 编辑 会议纪要
function editor(value,row,index){
	var info = "<span id=\"infoOperate\" class=\"edit blue\"  onclick=\"operateOperationalData('"+row.id+"','info')\" >查看</span>";
	var edit = "";
	var deletes = "";
	if('${fx:isTransfering(projectId)}' == 'false'){
		if(isflag == "true"){
			edit = " <span  id=\"editOperate\" class=\"edit blue\"  onclick=\"operateOperationalData('"+row.id+"','edit')\" >编辑</span>";
			deletes = " <span  id=\"delOperate\" class=\"edit blue\"  onclick=\"deleteOperationalData('"+row.id+"','e')\" >删除</span>";
		}
	}
	return info + edit + deletes;
}
function dataRange(value,row,index){
	return value+"年"+row.dataTypeValue;
}

function operateOperationalData(id,i){
	var _url='<%=path %>/galaxy/operationalData/editOperationalDataList/'+id;
	if(i == "info"){
		_url='<%=path %>/galaxy/operationalData/infoOperationalDataList/'+id;
	}
	
	var _url='<%=path %>/galaxy/operationalData/editOperationalDataList/'+id;
	if(i == "info"){
		_url='<%=path %>/galaxy/operationalData/infoOperationalDataList/'+id;
	}
	//详情页返回地址
	var options = $("#fileGridOperation").bootstrapTable('getOptions');
	var tempPageSize = options.pageSize ? options.pageSize : 10;
	var tempPageNum = options.pageNumber ? options.pageNumber : 1;
	var dataTypeMonth = $("input[name='dataTypeMonth']:checked").val();
	var dataTypeQuarter = $("input[name='dataTypeQuarter']:checked").val();
	var operateDateStart = $("input[name='operateDateStart']").val();
	var operateDateEnd = $("input[name='operateDateEnd']").val();
	var projectId =  $("input[name='projectId']").val();
	
	var formdata = {
			_paramKey : 'operationList',
			_url : _url,
			_path : "/",
			_param : {
				pageNum : tempPageNum,
        		pageSize : tempPageSize,
        		dataTypeMonth : dataTypeMonth,
        		dataTypeQuarter : dataTypeQuarter,
        		operateDateStart : operateDateStart,
        		operateDateEnd : operateDateEnd,
        		projectId :projectId
			}
	}
	cookieOperator.forwardPushCookie(formdata);
}
function deleteOperationalData(selectRowId){
	layer.confirm('是否删除运营数据?',
		{
		  btn: ['确定', '取消'] ,
		  title :'提示',
		}, 
		
		function(index, layero){
			del_operate(selectRowId);
		}, 
		function(index){
		}
	);
}

function del_operate(id){  
	var _url =  '<%=path %>/galaxy/operationalData/delOperationalData/'+id;
	sendPostRequestByJsonObj(_url, {}, function(data){
		if (data.result.status=="OK") {
			layer.msg("删除成功");
 	        var le = $("#fileGridOperation tbody tr").length;
 	        if(le <= 1){
 	        	var options = $("#operationList").bootstrapTable('getOptions');
 				var tempPageSize = options.pageSize ? options.pageSize : 10;
 				var tempPageNum = options.pageNumber ? options.pageNumber : 1;
 	 	        options.pageNum = tempPageNum - 1;
 	 	        var params = {};
 	 	        params.pageSize = tempPageSize;
 	        	params.pageNum = tempPageNum - 1;
 	        	$("#fileGridOperation").bootstrapTable('refresh', {query: json_2_1(params,getToobarQueryOperationParams('custom-toolbar-operate'))});
 	        }else{
 	        	$("#fileGridOperation").bootstrapTable('refresh');
 	        } 
		  
		} else {
			layer.msg(data.result.message);
		}
	});
}

$("button[action='querySearch']").click(function(){
	var start = $('input[name="operateDateStart"]').val();
	var end = $('input[name="operateDateEnd"]').val();
	if(start > end){
		layer.msg("开始时间不能大于结束时间!");
		return;
	}
	$("#fileGridOperation").bootstrapTable('refresh');
});

//运营分析
function backInitTabPostMeeting(){
	 var url=Constants.sopEndpointURL + "/galaxy/project/detail/" + projectId;
	 forwardWithHeader(url);
}
</script>



</html>

