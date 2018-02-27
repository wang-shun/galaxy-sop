 <%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>星河投</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/validate/lib/tip-yellowsimple/tip-yellowsimple.css" />
	<link href="<%=path%>/css/axure.css" type="text/css" rel="stylesheet" />
	<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
	<jsp:include page="../common/taglib.jsp"></jsp:include>
	<script src="<%=request.getContextPath()%>/bootstrap/bootstrap-table/bootstrap-table-xhhl.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
</head>

<body> 
	<div class="pagebox clearfix">
		<jsp:include page="../common/menu.jsp" flush="true"></jsp:include>
		<div class="ritmin">
			<h2>系统通知</h2>	
			<div class="tableSearch">
				<div class="searchTerm">
					<label>发送时间：</label>
					<input />
					<span>至</span>					
					<input /> 
					<label>状态：</label>
				</div>
				<table   class='assingTable table-hover' id="noticeTable">
					<thead>
					    <tr> 
				        	<th data-field="projectName"  data-width="14%" data-align="left">通知内容</th>
				        	<th data-field="projectName"  data-width="14%" data-align="left">创建人</th>
				        	<th data-field="projectName"  data-width="14%" data-align="left">创建时间</th>
				        	<th data-field="projectName"  data-width="20%" data-align="left">推送通知时间</th>
				        	<th data-field="projectName"  data-width="10%" data-align="left">状态</th>
				        	<th data-field="projectName"  data-width="14%" data-align="left">发送平台</th>
				        	<th data-field="projectName"  data-width="14%" data-align="left">操作</th>
	 					</tr>	
	 				</thead>
	 				<tbody>
	 				
	 				</tbody>
				</table> 
			
			
			
			</div>	
		</div>
	</div>
</body>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/footer.jsp"></jsp:include></body>
<jsp:include page="../common/uploadwin.jsp"></jsp:include>

</html>
<script type="text/javascript" charset="utf-8" src="<%=path %>/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/messages_zh.min.js"></script>
<script>
	createMenus(5);
</script>
