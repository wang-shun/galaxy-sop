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
<script src="<%=path %>/js/sopinfo.js"></script>
<jsp:include page="../../common/taglib.jsp" flush="true"></jsp:include>
</head>


<body>

<jsp:include page="../../common/header.jsp" flush="true"></jsp:include>

<div class="pagebox clearfix">
	<!--左侧导航-->
	<jsp:include page="../../common/menu.jsp" flush="true"></jsp:include>
     
    <!--右中部内容-->
 	<div class="ritmin">
 	
    	<jsp:include page="sopcommon.jsp" flush="true"></jsp:include>
        	<input type="hidden" id="pid" name="id" value="${pid}"/>
       


		<div class="new_left">
			<div class="tabtable assessment label_static">
				<!-- tab标签 -->
	            <ul class="tablink">
	             	<li><a href="javascript:;" onclick="showTabs(${pid},0)">基本信息</a></li>
	             	<c:choose>
	             	<c:when test="${fx:hasRole(1) || fx:hasRole(2) || fx:hasRole(3)|| fx:isCreatedByUser('project',pid) }">
	                <li class="on"><a href="javascript:;" onclick="showTabs(${pid},1)">团队成员</a></li>
	                <li><a href="javascript:;" onclick="showTabs(${pid},2)">股权结构</a></li>
	                <li><a href="javascript:;" onclick="showTabs(${pid},3)">访谈记录</a></li>
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
	            <c:if test="${fx:hasRole(1) || fx:hasRole(2) || fx:hasRole(3)|| fx:isCreatedByUser('project',pid) }">
				<div class="member">
				      <div class="top clearfix">
				          <!--按钮-->
				          <div class="btnbox_f btnbox_f1 clearfix">
				              <a href="javascript:;" class="pubbtn bluebtn ico c4 add_prj add_profile" onclick="addPerson();">添加</a>
				              <a href="javascript:;" class="pubbtn bluebtn edit_profile" onclick="toSureMsg();">完善简历</a>
				          </div>
				      </div>
				              <!--表格内容-->
				               <div class="tab-pane active" id="view">	
				      <table id="tablePerson" width="100%" data-height="555" 
				            	data-method="post" data-page-list="[10,20,30]" data-show-refresh="true" >
					  </table> 
					  </div>
				     
				</div>
	            </c:if>
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
<%-- <script src="<%=path %>/js/jquery-1.10.2.min.js" type="text/javascript"></script> --%>
<script src="<%=path %>/js/axure.js" type="text/javascript"></script>
<script src="<%=path %>/js/axure_ext.js" type="text/javascript"></script>
<script src="<%=path %>/js/person.js"></script>


<script>

	var proinfo = '${proinfo}';
	var proid = '${pid}';
	var pname = '${pname}';
	var interviewSelectRow = null;
	var projectId ='${pid}';
	var flag = '${flag}';

$(function(){
	createMenus(5);
	getTabPerson();
	
	
});	
//添加团队成员
function addPerson(){
	sendGetRequest(platformUrl.getDegreeByParent + "/" + "null",null,addPersonCallBack);
	return false;
}
function addPersonCallBack(data){
	   $.getHtml({
			url:platformUrl.addPersonView,//模版请求地址
			data:"",//传递参数
			okback:function(){
				var _dom =  $("#highestDegree");
			    _dom.empty();
				_dom.append("<option value='all'>请选择</option>");
				$.each(data.entityList,function(){
					if(this.code){
						_dom.append("<option value='"+this.code+"'>"+this.name+"</option>");
					}else{
						_dom.append("<option value='"+this.id+"'>"+this.name+"</option>");
					}
					
				});	
			}//模版反回成功执行	
				
		});     
	}
	function updatePer(id){
		sendGetRequest(platformUrl.getDegreeByParent+"/"+id,null,updatePerCallBack);
		return false;
	}

	function updatePerCallBack(data){
		var _url = platformUrl.updatePerView+data.result.message;
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				
				var _dom =  $("#highestDegree");
			    _dom.empty();
				_dom.append("<option value='all'>请选择</option>");
				$.each(data.entityList,function(){
					if(_dom.data("value")==this.code){
						_dom.append("<option value='"+this.code+"' selected=true >"+this.name+"</option>");
					}else{
						_dom.append("<option value='"+this.code+"'>"+this.name+"</option>");
					}
					
				});	
			
			}//模版反回成功执行	
		});
	}

	//个人简历
	function tiaozhuan(id){
		var url =platformUrl.personHHr;
		if(flag == "true"){
			url =platformUrl.personHr;
		}
		$.getHtml({
			url: url+"?personId="+id, 
			data:"",//传递参数
			okback:function(){
			/* alert("进入完善简历页面"); */
			$(".resumetc .tabtable").tabchange2();
			}//模版反回成功执行	
		}); 
		
	}
	function toSureMsg(){
		sendGetRequest(
				Constants.sopEndpointURL + '/galaxy/soptask/toSureMsg/'+projectId, 
				 null, function(data){
					 layer.msg(data.result.message);
				 });
	}


	function reloadJS(){
		loadJs('<%=request.getContextPath() %>/js/axure.js'); 
	}
</script>
</html>
