<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.galaxyinternet.com/fx" prefix="fx" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	String path = request.getContextPath(); 
    String flag=(String)request.getAttribute("flagUrl");
    String title="";
    if(flag.equals("healthHighNum")){
    	title="高于预期";
    }else if(flag.equals("healthGoodNum")){
    	title="健康";
    }else if(flag.equals("healthWarnNum")){
    	title="健康预警";
    }else{
    	title="项目健康度详情";
    }
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>星河投</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/validate/lib/tip-yellowsimple/tip-yellowsimple.css" />

<link href="<%=path %>/css/axure.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/css/beautify.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet"/>
<link rel="stylesheet" href="<%=path %>/bootstrap/bootstrap-table/bootstrap-table.css"  type="text/css">
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
<!-- jsp文件头和头部 -->
<jsp:include page="../common/taglib.jsp" flush="true"></jsp:include>

<!-- 校验 -->
<script src="<%=path %>/js/bootstrap-v3.3.6.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path %>/js/validate/fx.validate.css" />

</head>

<body>

<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
<div class="pagebox clearfix">
	<jsp:include page="../common/menu.jsp" flush="true"></jsp:include>
	<!--右中部内容-->
 	<div class="ritmin prj_all" >
    	<div class="new_tit_a"><a href="#" onclick="backIndex()">工作桌面</a><img alt="" src="<%=path %>/img/arrow-brumd.png" class="arrow"/>项目健康度</div>
    		
    	<div class="new_tit_b">
        	<span class="size18" id="project_name_t"><%=title %></span>
        	<span class="b_span"> 
	        	<a href="#" onclick="backIndex();">返回></a>
			</span>
        </div>
    	 	<div class="min_document clearfix" id="health-custom-toolbar" style="display:none;" >
				<div class="bottom searchall clearfix">
					<input type="hidden" name="flagUrl" value="${ flagUrl}"> 
				</div>
			</div>
     	<div class="tab-pane active ctlist" id="view">	
			<table id="chart_health_table" data-url="<%=path %>/galaxy/health/getHealthChartGrid" 
				data-page-list="[10, 20, 30]" data-toolbar="#health-custom-toolbar" data-show-refresh="true">
				<thead>
				    <tr>
			        	<th data-field="projectName"  data-formatter="projectNameInfo" class="data-input" data-width="16%">项目名称</th>
			        	<th data-field="projectCareerline"  class="data-input" data-width="9%">投资事业线</th>
			        	<th data-field="healthStateDesc"  data-formatter="healthStateFormat"    data-width="6%">健康状态<span></span></th>
			        	<th data-field="rematk"   data-formatter="rematkFormat" class="data-input" data-width="17%">风险点</th>
			        	<th data-field="userName"   class="data-input" data-width="8%">分析人<span></span></th>
			        	<th class="noborder" data-field="createdTime" data-formatter="longTime_Format"  data-width="5%">分析日期</th>
			        	</tr>
 				</thead>
			</table>
           </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp" flush="true"></jsp:include></body>
<script src="<%=request.getContextPath() %>/js/axure.js" type="text/javascript"></script>

<!-- 分页二css+四js -->
<link rel="stylesheet" href="<%=path %>/bootstrap/bootstrap-table/bootstrap-table.css"  type="text/css">

<script src="<%=path %>/bootstrap/bootstrap-table/bootstrap-table-xhhl.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>


<script type="text/javascript">
    $(function(){
    	createMenus(160);
    	init_bootstrapTable('chart_health_table',10);
    });
	 function rematkFormat(value,row,index){
			var str=row.rematk;
			var options="";
			if(str.length>10){
				subStr = str.substring(0,10);
				var options = "<label  title='"+str+"'>"+subStr+"</label>";
			}else{
				var options = "<label title='"+str+"'>"+str+"</label>";
			}
			return options;
		
	 }
	 
	 function healthStateFormat(value,row,index){
		 var healthState=row.healthState;
		 var healthStates={
				 "0":"初始",
				 "1":"高于预期",
				 "2":"正常",
				 "3":"健康预警",
				 "4":"清算"
		 }
		 return  healthStates[healthState];
	 }
	 
	 function projectNameInfo(value,row,index){
		 
		 var id=row.projectId;
			var str=row.projectName;
			if(str.length>10){
				subStr = str.substring(0,10);
				var options = "<a href='#' class='blue' data-btn='myproject' onclick='proInfo(" + id + ")' title='"+str+"'>"+subStr+"</a>";
				return options;
			}
			else{
				var options = "<a href='#' class='blue' data-btn='myproject' onclick='proInfo(" + id + ")' title='"+str+"'>"+str+"</a>";
				return options;
			}
	 }
	 function proInfo(id){
			//项目详情页返回地址
		//	setCookie("href_flag", "healthy",24,'/');
		//	var formdata = {
		//			_paramKey : 'projectList',
		///			_url : Constants.sopEndpointURL + "/galaxy/project/detail/" + id,
		//			_path : "/",
		//			_param : {
						
		//			}
	//		}
			var href_url=window.location;
			setCookie("href_url", href_url,24,'/');
		//	cookieOperator.forwardPushCookie(formdata);
			forwardWithHeader(Constants.sopEndpointURL + "/galaxy/project/detail/" + id);
		}
	 function back(){
		 window.history.go(-1);
		}
	   function backIndex(){
	    	forwardWithHeader("<%= path%>/galaxy/index");
	    }
	 
</script>
</html>
