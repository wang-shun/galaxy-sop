<%@ page language="java" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
	Long projectId = (Long)request.getAttribute("projectId");
// 	System.out.println(projectId);
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>项目详情</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/validate/lib/tip-yellowsimple/tip-yellowsimple.css" />

<link href="<%=path %>/css/axure.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/css/beautify.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/css/style.css" type="text/css" rel="stylesheet"/>
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->

<!-- jsp文件头和头部 -->
<link id="f" href="<%=path %>/ueditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">

<jsp:include page="../../common/taglib.jsp" flush="true"></jsp:include>

<!-- 校验 -->
<script src="<%=path %>/js/bootstrap-v3.3.6.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/messages_zh.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/validate/lib/jquery.poshytip.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/fx.validate.js"></script>
<script type="text/javascript" src="<%=path %>/js/validate/fx.validate-ext.js"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/js/validate/lib/jq.validate.js'></script>

<link rel="stylesheet" type="text/css" href="<%=path %>/js/validate/fx.validate.css" />
</head>


<body>

<jsp:include page="../../common/header.jsp" flush="true"></jsp:include>

<div class="pagebox clearfix">
	<!--左侧导航-->
	<jsp:include page="../../common/menu.jsp" flush="true"></jsp:include>
     
    <!--右中部内容-->
 	<div class="ritmin">
 	
    	<div class="new_tit_a"><a href="#">工作桌面</a>><a href="#">创投项目</a>>Utter绝对潮流</div>
    	
    	<div class="new_tit_b">
        	<span class="new_color size18" id="project_name_title"></span><span class="new_color" id="project_code">ID987786600009</span>
        	<span class="b_span"><a href="#">返回项目列表></a></span>
        </div>
        
        
        <div class="new_left">
        	<div class="tabtable assessment label_static">
          	<!-- tab标签 -->
            <ul class="tablink">
                <li data-tab="nav"><a href="javascript:;">基本信息</a></li>
                <li data-tab="nav"><a href="javascript:;">团队成员</a></li>
                <li data-tab="nav"><a href="javascript:;">股权结构</a></li>
                <li data-tab="nav"><a href="javascript:;" onclick="toInterView()">访谈记录</a></li>
                <li data-tab="nav"><a href="javascript:;" onclick="toMeet()">会议纪要</a></li>
                <li data-tab="nav"><a href="javascript:;">项目文档</a></li>
                <li data-tab="nav" class="no"><a href="javascript:;">操作日志</a></li>
            </ul>

            <!-- 基本信息 -->
			<div data-tab="con">
				<div class="tabtable_con">
					<div class="new_r_compile">
						<span class="new_fctbox"> 
							<a href="javascript:;" class="ico f1" data-on="compile">编辑</a>
						</span>
					</div>
					
					<table width="100%" cellspacing="0" cellpadding="0" class="new_table">
					 <input type="hidden" id="pid" name="id" value="${projectId}"/>
						<tr>
							<td><span class="new_color_gray">项目名称：</span>
								<span class="new_color_black" id="project_name"></span>
							</td>
							<td><span class="new_color_gray">创建时间：</span>
								<span class="new_color_black" id="create_date"></span>
							</td>
						</tr>
						
						<tr>
							<td><span class="new_color_gray" >项目类型：</span><span class="new_color_black" id="projectType"></span></td>
							<td><span class="new_color_gray">最后编辑：</span><span class="new_color_black" id="updateDate"></span></td>
						</tr>
						
						<tr>
							<td><span class="new_color_gray">行业归属：</span><span class="new_color_black">互联网旅游</span></td>
							<td><span class="new_color_gray" >投资经理：</span>
								<span class="new_color_black" id="createUname">刘佳</span><span class="new_color_gray" id="projectCareerline">（O2O及电商）</span></td>
						</tr>
						
						<tr>
							<td><span class="new_color_gray">融资状态：</span><span class="new_color_black">尚未获投</span></td>
							<td><span class="new_color_gray">项目进度：</span><span class="new_color_black">接触访谈</span>
								<span class="new_color_gray">（跟进中）</span><span class="new_bj "></span></td>
						</tr>
					</table>

					<!--融资计划-->
					<div class="new_r_compile new_bottom_color">
						<span class="new_ico_financing"></span> <span class="new_color size16">融资计划</span>
					</div>
					<table width="100%" cellspacing="0" cellpadding="0" class="new_table">
						<tr>
							<td><span class="new_color_gray">融资金额：</span><span class="new_color_black" id="project_contribution"></span></td>
							<td><span class="new_color_gray">项目估值：</span><span class="new_color_black" id="project_valuations"></span></td>
						</tr>
						<tr>
							<td><span class="new_color_gray">出让股份：</span><span class="new_color_black" id="project_share_ratio"></span></td>
						</tr>
					</table>
					
					<!--实际投资-->
					<div class="new_r_compile new_bottom_color">
						<span class="new_ico_practical"></span> <span class="new_color size16">实际投资</span>
					</div>
					<table width="100%" cellspacing="0" cellpadding="0" class="new_table">
						<tr>
							<td><span class="new_color_gray">投资金额：</span><span class="new_color_black">600万人民币</span></td>
							<td><span class="new_color_gray">项目估值：</span><span class="new_color_black">600万人民</span></td>
						</tr>
						<tr>
							<td><span class="new_color_gray">股权占比：</span><span class="new_color_black">90%</span></td>
						</tr>
					</table>
				</div>
				<!--商业计划书-->
				<div class="tabtable_con_on">
					<div class="new_r_compile new_bottom_color">
						<span class="new_ico_book"></span> <span class="new_color size16">商业计划书</span>
					</div>
					<ul class="new_ul_all">
						<li><span id="bpName">《XXXXXXXXXXXXXXXXX》</span></li>
						<li><span class="new_color_gray">状态：</span><span class="new_color_black" id="is_upload"></span></li>
						<li><span class="new_color_gray">更新时间：</span><span class="new_color_black" id="uploadtime"></span></li>
						<li class="new_ul_right"><span class="new_fctbox"> <a href="javascript:;" class="ico f2" data-btn="describe">查看</a>
								<a href="javascript:;" class="ico new1" data-btn="edit" id="uploadOperator">更新</a>
								<a href="javascript:;" class="ico new2" data-btn="describe">查看历史</a>
						</span></li>
					</ul>
				</div>
				<!--项目概述-->
				<div class="tabtable_con_on">
					<div class="new_r_compile new_bottom_color">
						<span class="new_ico_project"></span> <span class="new_color size16">项目概述</span> <span class="bj_ico" id="descript">暂无数据</span><span class="new_fctbox">
							<a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					<div class="new_ul_all">
						<span class="ico_dot ico"></span>
						<p id="describe_editor"></p>
					</div>
				</div>
				<!--公司定位-->
				<div class="tabtable_con_on">
					<div class="new_r_compile ">
						<span class="new_ico_firm"></span> <span class="new_color size16">公司定位</span> <span class="bj_ico" id="location">暂无数据</span>
						<span class="new_fctbox"> <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					<div class="new_ul_all">
						<span class="ico_dot ico"></span>
						<p id="location_editor"></p>
					</div>
				</div>
				<!--用户画像-->
				<div class="tabtable_con_on">
					<div class="new_r_compile ">
						<span class="new_ico_people"></span> <span class="new_color size16">用户画像</span> <span class="bj_ico" id="portrait">暂无数据</span>
						<span class="new_fctbox"> <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					<div class="new_ul_all">
						<span class="ico_dot ico"></span>
						<p id="portrait_editor"></p>
					</div>
				</div>
				<!--产品服务-->
				<div class="tabtable_con_on">
					<div class="new_r_compile ">
						<span class="new_ico_product"></span> <span class="new_color size16">产品服务</span> <span class="bj_ico" id="business_model">暂无数据</span>
						<span class="new_fctbox"> <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					<div class="new_ul_all">
						<span class="ico_dot ico"></span>
						<p id="business_model_editor"></p>
					</div>
				</div>

				<!--运营数据-->
				<div class="tabtable_con_on">
					<div class="new_r_compile ">
						<span class="new_ico_run"></span> <span class="new_color size16">运营数据</span>
						<span class="bj_ico" id="operational_data">暂无数据</span> <span class="new_fctbox">
							<a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					<div class="new_ul_all">
						<span class="ico_dot ico"></span>
						<p id="operational_data_editor"></p>
					</div>
				</div>
				<!--行业分析-->
				<div class="tabtable_con_on">
					<div class="new_r_compile ">
						<span class="new_ico_industry"></span> <span class="new_color size16">行业分析</span> <span class="bj_ico" id="industry_analysis">暂无数据</span>
						<span class="new_fctbox"> <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					<div class="new_ul_all">
						<span class="ico_dot ico"></span>
						<p id="industry_analysis_editor"></p>
					</div>
				</div>
				<!--竞情分析-->
				<div class="tabtable_con_on">
					<div class="new_r_compile ">
						<span class="new_ico_jq"></span> <span class="new_color size16">竞情分析</span>
						<span class="bj_ico" id="analysis">暂无数据</span> <span class="new_fctbox">
							<a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					<div class="new_ul_all">
						<span class="ico_dot ico"></span>
						<p id="analysis_editor"></p>
					</div>
				</div>

				<!--下一轮融资路径-->
				<div class="tabtable_con_on">
					<div class="new_r_compile ">
						<span class="new_ico_nex"></span> <span class="new_color size16">下一轮融资路径</span>
						<span class="bj_ico" id="next_financing_source">暂无数据</span> <span class="new_fctbox">
							<a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					<div class="new_ul_all">
						<span class="ico_dot ico"></span>
						<p id="next_financing_source_editor"></p>
					</div>
				</div>
				
			</div>
					
					
					
					
					
			<!-- 团队成员 -->
			<div data-tab="con">
				<div class="tabtable_con">
					<div class="new_r_compile">
						<span class="new_fctbox"> <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
						</span>
					</div>
					
					团队成员

				</div>
			</div>



			<!-- 股权结构 -->
            <div  data-tab="con" >   
            	<div class="tabtable_con">
                    <jsp:include page="/galaxy/project/tabShares">
			        	<jsp:param value="<%=request.getAttribute(\"projectId\") %>" name="id"/>
			        </jsp:include>
                </div>                 
            </div>
            
            
            
             <!-- 访谈记录 -->
            <div  data-tab="con" >   
            	<div class="tabtable_con">
                    <div class="new_r_compile">
                        <span class="new_fctbox">
                            <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
                          </span>
                    </div>  
                    	访谈记录  
                </div>                 
            </div>
            
            
             <!-- 会议纪要 -->
            <div  data-tab="con" >   
            	<div class="tabtable_con">
                    <div class="new_r_compile">
                        <span class="new_fctbox">
                            <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
                          </span>
                    </div>  
                    	会议纪要  
                </div>                 
            </div>
            
            
             <!-- 项目文档 -->
            <div  data-tab="con" >   
            	<div class="tabtable_con">
                    <div class="new_r_compile">
                        <span class="new_fctbox">
                            <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
                          </span>
                    </div>  
                    	项目文档  
                </div>                 
            </div>
            
            
             <!-- 操作日志 -->
            <div  data-tab="con" >   
            	<div class="tabtable_con">
                    <div class="new_r_compile">
                        <span class="new_fctbox">
                            <a href="javascript:;" class="ico f1" data-btn="edit">编辑</a>
                          </span>
                    </div>  
                  	  操作日志  
                </div>                 
            </div>
            <!--tab end-->
          </div>
        </div>
        
        
        
       <!--右边-->
        <jsp:include page="./includeRight.jsp" flush="true"></jsp:include>
        
    </div>
 
</div>

<!--隐藏-->
<div class="bj_hui_on none"></div>
<div class="compile_on none">
	<div class="compile_on_center">
        <div class="compile_on_right">
            <span class="compile_on_right_b">保存</span>
            <span class="compile_on_right_q" data-ｏｎ="close">取消</span>
        </div>  
        <table width="100%" cellspacing="0" cellpadding="0" class="new_table">
            <tr>
                <td><span class="new_color_gray">项目名称：</span><span><input class="new_nputr" value="Utter绝对潮流"></input></span></td>
                <td><span class="new_color_gray">创建时间：</span><span class="new_color_black">2016-01-25</span></td>
            </tr>
            <tr>
                <td><span class="new_color_gray">项目类型：</span><span class="new_color_black">外部投资</span></td>
                <td><span class="new_color_gray">最后编辑：</span><span class="new_color_black">2016-01-25</span></td>
            </tr>
            <tr>
                <td><span class="new_color_gray">行业归属：</span><span><select class="new_nputr"><option>ddddd</option></select></span></td>
                <td><span class="new_color_gray">投资经理：</span><span class="new_color_black">刘佳</span><span class="new_color_gray">（O2O及电商）</span></td>
            </tr>
            <tr>
                <td><span class="new_color_gray">融资状态：</span><span><input class="new_nputr" value="Utter绝对潮流"></input></span></td>
                <td><span class="new_color_gray">项目进度：</span><span class="new_color_black">接触访谈</span><span class="new_color_gray">（跟进中）</span><span class="new_bj "></span></td>
            </tr>
        </table>  
        
        <!--融资计划-->
        <div class="new_r_compile new_bottom_color">
            <span class="new_ico_financing"></span>
            <span class="new_color size16">融资计划</span>
        </div>  
        <table width="100%" cellspacing="0" cellpadding="0" class="new_table">
            <tr>
                <td><span class="new_color_gray">融资金额：</span><span class="new_color_black"><input class="new_nputr_number" />　万人民币</span></td>
                <td><span class="new_color_gray">项目估值：</span><span class="new_color_black"><input class="new_nputr_number" />　万人民</span></td>
            </tr>
            <tr>
                <td><span class="new_color_gray">出让股份：</span><span class="new_color_black"><input class="new_nputr_number" />　90%</span></td>
            </tr>
        </table>
        <!--实际投资-->
        <div class="new_r_compile new_bottom_color">
            <span class="new_ico_practical"></span>
            <span class="new_color size16">实际投资</span>
        </div>  
        <table width="100%" cellspacing="0" cellpadding="0" class="new_table">
            <tr>
                <td><span class="new_color_gray">投资金额：</span><span class="new_color_black"><input class="new_nputr_number" />　万人民币</span></td>
                <td><span class="new_color_gray">项目估值：</span><span class="new_color_black"><input class="new_nputr_number" />　万人民</span></td>
            </tr>
            <tr>
                <td><span class="new_color_gray">股权占比：</span><span class="new_color_black"><input class="new_nputr_number" />　%</span></td>
            </tr>
        </table>
    </div>
</div>
<jsp:include page="../../common/footer.jsp" flush="true"></jsp:include>
<script src="<%=path %>/js/projectDetail.js"></script>
<script src="<%=path %>/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="<%=path %>/js/axure.js" type="text/javascript"></script>
<script src="<%=path %>/js/axure_ext.js" type="text/javascript"></script>
<script>

var projectId = <%=projectId%>;

$(function(){
	createMenus(5);
	
	
	$('[data-on="compile"]').on('click',function(){
		$('.bj_hui_on').show();
		$('.compile_on').show();
	})
	$('[data-on="close"]').on('click',function(){
		$('.bj_hui_on').hide();
		$('.compile_on').hide();
	})
})


	
function toInterView(){
	window.location.href= Constants.sopEndpointURL + "/galaxy/project/proview/" + projectId;
}
function toMeet(){
	window.location.href= Constants.sopEndpointURL + "/galaxy/project/promeet/" + projectId;
}



</script>
</html>
