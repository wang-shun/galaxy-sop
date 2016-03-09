<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath(); 
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>繁星</title>
<link href="<%=path %>/css/axure.css" type="text/css" rel="stylesheet"/>
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<script src="<%=path %>/js/plupload.full.min.js" type="text/javascript"></script>
</head>

<body>

<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
<div class="pagebox clearfix">
	<jsp:include page="../common/menu.jsp" flush="true"></jsp:include>
	<!--右中部内容-->
 	<div class="ritmin">
    <!-- 面包屑 -->
    <ul class="breadcrumb">
      <li><a href="javascript:;" class="bcfirst">待办任务</a></li>
      <li class="bottom_align"><span>&gt;</span><a href="javascript:;" class="active">上传文档</a></li>
    </ul>
      <div class="clearfix"></div>
        <!--项目基本信息内容-->
        <div class="projectmsg clearfix">
          <h2></h2>
          <!-- 上半部分 -->
          <div class="top" id="project-summary">
            <table width="100%" cellspacing="5" cellpadding="0" >
             <tbody>
                  <tr>
                      <td>
                        <dl>
                          <dt>项目编码：</dt>
                          <dd id="projectCode"></dd>
                        </dl>
                      </td>
                      <td>
                        <dl>
                          <dt>来源：</dt>
                          <dd id="type"></dd>
                        </dl>
                      </td>
                  </tr>
                  <tr>
                      <td>
                        <dl>
                          <dt>项目名称：</dt>
                          <dd id="projectName"></dd>
                        </dl>
                      </td>
                      <td>
                        <dl>
                          <dt>创建时间：</dt>
                          <dd id="createDate"></dd>
                        </dl>
                      </td>
                  </tr>
                  <tr>
                      <td>
                        <dl>
                          <dt>投资事业线：</dt>
                          <dd id="projectCareerline"></dd>
                        </dl>
                      </td>
                      <td>
                        <dl>
                          <dt>合伙人：</dt>
                          <dd></dd>
                        </dl>
                      </td>
                  </tr>
                  <tr>
                      <td>
                        <dl>
                          <dt>投资经理：</dt>
                          <dd id="createUname"></dd>
                        </dl>
                      </td>
                  </tr>
                  <tr>
                    <td colspan="2">
                      <dl>
                        <dt>项目概述：</dt>
                        <dd class="tarea" id="projectDescribe"></dd>
                      </dl>
                    </td>
                  </tr>
                </tbody>
              </table>
            <a href="javascript:;"  class="pjt_more">项目详细信息&gt;</a>
          </div>
          <!-- 下半部分 -->
          <!-- 人事尽职调查报告  hrjzdc;-->
          <jsp:include page="/galaxy/taskprocess/showFileList"></jsp:include>
        </div>

    </div>
 
</div>
<jsp:include page="../common/footer.jsp" flush="true"></jsp:include></body>
<script type="text/javascript">
$(function(){
	createMenus(2);
	getProjectInfo();
});

function getProjectInfo()
{
	var url = platformUrl.detailProject+"/${projectId}";
	sendGetRequest(
		url,
		null,
		function(data){
			if(data.result.status == "Error")
			{
				alert(data.result.message );
				return;
			}
			var project = data.entity;
			$("#project-summary dd")
			.each(function(){
				var self = $(this);
				if(self.attr('id') != 'undefined')
				{
					var id = self.attr('id');
					self.text(project[id]);
				}
			});
			$(".projectmsg h2").text(project.projectName);
		}
	);
}

</script>
</body>
</html>
	