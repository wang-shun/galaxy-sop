<%@ page language="java" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>星河投</title>
<!-- 校验样式 -->
<!-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/validate/reset.css" /> -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/validate/lib/tip-yellowsimple/tip-yellowsimple.css" />
<link href="<%=path %>/css/axure.css" type="text/css" rel="stylesheet"/>
<!--[if lt IE 9]><link href="css/lfie8.css" type="text/css" rel="stylesheet"/><![endif]-->
<!-- jsp文件头和头部 -->
<jsp:include page="../common/taglib.jsp" flush="true"></jsp:include>
<!-- 日历插件 -->
<link href="<%=path %>/bootstrap/bootstrap-datepicker/css/bootstrap-datepicker3.css" type="text/css" rel="stylesheet"/>
<link href="<%=path %>/bootstrap/css/bootstrap-select.css" type="text/css" rel="stylesheet"/>
<%-- <link href="<%=path %>/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/> --%>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/js/datepicker-init.js"></script>
<script src="<%=path %>/bootstrap/js/bootstrap-select.js"></script> 
<style>
	body{
		background-color:#E9EBF2;
	}
	.after{
		position:relative;
	}
	.after::after{content: "万元";
    font-family: "Microsoft YaHei";
    font-size: 12px;
    color: #666;
    position: absolute;
    right: 15px;
    top: 1px;
	}
	.after2::after{content: "%";} 
</style>
</head>

<body >

<jsp:include page="../common/header.jsp" flush="true"></jsp:include>
		<div class="new_tit_b add-project-title">
            <span>添加项目</span>
		</div>
<div class="pagebox clearfix">
	<jsp:include page="../common/menu.jsp" flush="true"></jsp:include>
	<!--右中部内容-->
 	<div class="ritmin add-project-ritmin">
		<%-- <div class="new_tit_a"><a href="#">工作桌面</a><img alt="" src="<%=path %>/img/arrow-brumd.png" class="arrow"/><a href="#">创投项目</a><img alt="" src="<%=path %>/img/arrow-brumd.png" class="arrow"/>添加项目</div> --%>
        <div class="new_left add-poject-right">
        	<div class="tabtable_con_on add-project-tabtable">
                    <!--基本信息-->
                 <form action="" id="add_form" method="post" autocomplete="off">
                  <div class='addpro-basic-content'>
                    <div class="addpro-new-title ">
                        <span class="new_color  add-pro-basicmessage">基本信息</span>
                    </div>  
                    <ul class="basic_ul addpro-basi-ul clearfix">
                    	<li>
                        	<span class="basic_span addpro-basic-span"><em class="red">*</em><span class='letter-space'>项目类型：</span></span>
                            <span class="m_r30 inpu-self inpu-self-checked"><input class='inpu-radio' name="projectType" type="radio" value="projectType:1" id="radio_w" checked='checked'><label for="radio_w">投资</label></span>
                            <span class="m_r30 inpu-self"><input class='inpu-radio' name="projectType" type="radio" value="projectType:2" id="radio_n"><label for="radio_n">创建</label></span>
                            <span class="basic_span addpro-basic-span addpro-left"><em class="red">*</em><span class='letter-space'>创建时间：</span></span>
                            <span class="m_r30"><input style='display: inline-block;vertical-align: middle;' type="text" class='datepicker-text addpro-input' name="createDate" id="createDate" readonly value="" valType="required" msg="<font color=red>*</font>创建时间不能为空"/><img style='display: inline-block;vertical-align: middle;' title="创建时间指的是投资业务人员首次接触该项目的时间，并非项目成立时间 " src="/sop/img/sop_progress/remind__icon.png" class="alertImg"></span>
                        </li>
                        <li class='projectSourceli clearfix'>
                            <span class="basic_span addpro-basic-span"><em class="red">*</em><span class='letter-space'>项目名称：</span></span>
                            <span class="m_r30"><input type="text" class='addpro-input' maxlength="24" id="projectName" name="projectName" <%-- data-msg-required="<font color=red>*</font>项目名称不能为空" --%>/><label class='project-name'></label></span>
                       		<span class="basic_span addpro-basic-span addpro-marin-lt"><em class="red">*</em><span class='letter-space rzlc_span'>本轮融资轮次：</span></span>
                            <span class="m_r30 selectcheck select">
								<select name="financeStatus" class=' '   data-title-id="1108" data-type="14">
									<!-- <option value="">请选择</option> -->
			                    </select>
							</span>
                        </li>
                        <li class="projectSourceli clearfix">
                        	
                            <span class="basic_span addpro-basic-span "><em class="red">*</em><span class='letter-space'>项目来源：</span></span>
                            <span class="m_r30  selectcheck select" >
	                            <select name="proSource"  data-title-id="1120" data-type="14" >
				                    	<option value="">请选择</option>
				                </select> 
                       		</span>                       		
                       		<span class="basic_span addpro-basic-span addpro-marin-lt"><em class="red">*</em><span class='letter-space'>行业归属：</span></span>
                            <span class="m_r30 selectcheck select">
                            	<select name="industryOwn"   >
			                    	<option value="">请选择</option>
			                    </select>
                            </span>
                        </li>
                        <li class="projectSourceli clearfix">
                        <div class="projectSource projectSource5">
                        		 <span class="basic_span addpro-basic-span"><span class='letter-space'>合投机构名称 ：</span></span>
                        		 <span class="m_r30">
									<input type="text" class="addpro-input" data-title-id="1121" data-type="1"   maxlength="50"  placeholder="请输入合投机构名称" id="proS6" name="proS5"/>
								 </span>
                        	</div>
                        	<div class="projectSource projectSource6">
                        		 <span class="basic_span addpro-basic-span"><em class="red">*</em><span class='letter-space'>FA名称 ：</span></span>
                        		 <span class="m_r30">
									<input type="text" class="addpro-input" data-title-id="1122" data-type="1"   maxlength="20"  placeholder="请输入FA名称（必填）" id="proS6" name="proS6"/>
								 </span>
                        	</div>
                        	<div class="projectSource projectSource7">
                        		 <span class="basic_span addpro-basic-span"><span class='letter-space'>孵化器名称 ：</span></span>
                        		 <span class="m_r30">
									<input type="text" class="addpro-input" data-title-id="1123" data-type="1" maxlength="50" placeholder="请输入孵化器名称" id="proS7" name="proS7"/>
								 </span>
                        	</div>
                        	<div class="projectSource projectSource8">
                        		 <span class="basic_span addpro-basic-span"><span class='letter-space'>机构及路演名称：</span></span>
                        		 <span class="m_r30">
									<input type="text" class="addpro-input" data-title-id="1124" data-type="1" maxlength="50" placeholder="请输入机构及路演名称" id="proS8" name="proS8"/>
								 </span>
                        	</div>
                        	<div class="projectSource projectSource9">
                        		 <span class="basic_span addpro-basic-span"><span class='letter-space'>创投机构名称 ：</span></span>
                        		 <span class="m_r30">
									<input type="text" class="addpro-input" data-title-id="1125" data-type="1" maxlength="50" placeholder="请输入创投机构名称" id="proS9" name="proS9"/>
								 </span>
                        	</div>
                        	<div class="projectSource projectSource10">
                        		 <span class="basic_span addpro-basic-span"><span class='letter-space'>媒体名称 ：</span></span>
                        		 <span class="m_r30">
									<input type="text" class="addpro-input" data-title-id="1126" data-type="1" maxlength="50" placeholder="请输入媒体名称" id="proS10" name="proS10"/>
								 </span>
                        	</div>
                        	<div class="projectSource projectSource11">
                        		 <span class="basic_span addpro-basic-span"><em class="red">*</em><span class='letter-space'>项目承揽人 ：</span></span>
                        		 <span class="m_r30 selectcheck" >
									<select id="selectRadio" name="projectContractor" class="selectpicker" multiple data-live-search="true" data-type="23" data-title-id="1118">
									    
									  </select>
									  <input type="text" class="addpro-input" id="pickeother" maxlength="12" placeholder='请输入非投资线项目承揽人名称(必填)'/>
								</span>
                        	</div>
                        </li>
                        <li class="projectSourceli clearfix">
                        	<span class="basic_span addpro-basic-span"><span class='letter-space'>公司名称：</span></span></span>
                            <span class="m_r30"><input type="text" class='addpro-input' maxlength="50" data-title-id="1814" data-type="1" /> </span>
                        </li>
                        <li class="projectSourceli clearfix">
                        	<span class="basic_span addpro-basic-span"><span class='letter-space'>项目简介：</span></span></span>
                            <span class=""><textarea data-title-id="1203" data-type="8" type="text" class='textarea_h add_textarea' maxlength="2000" ></textarea> </span>
                        </li>
                          
                    </ul>  
                </div>
                    <!--融资计划-->
                <div class='addpro-finacing-plan'>
                    <div class="addpro-new-title ">
                        <span class="new_color  add-pro-basicmessage">融资计划</span>
                    </div> 
                    <ul class="basic_ul addpro-finacing-ul">
                        <li>
                            <span class="basic_span letter-space add-finace-lf">融资金额：</span>
                            <span class="m_r15 after after1">
                            	<input type="text" placeholder='融资金额' class='new_nputr_number addpro-input' id="formatContribution" data-title-id="1916" data-type="19" name="formatContribution procontribution" data-rule-procontribution="true"  data-msg-procontribution="<font color=red>*</font>支持9位长度的支持6位小数"/>
                            </span>
                            <!-- <span class="m_r30">万元</span> -->
                            
                        </li>
                        <li>
	                        <span class="basic_span letter-space add-finace-lf">出让股份：</span>
                            <span class="m_r15 after after2">
                            	<input type="text" placeholder='出让股份' class='new_nputr_number addpro-input ' id="formatShareRatio" data-title-id="1917" data-type="19" name="formatShareRatio proshare"  data-rule-proshare="true" data-msg-proshare="<font color=red>*</font>0到100之间的5位小数"/>
                            </span>
                            <!-- <span class="m_r30">% </span> -->
	                    </li>
                        <li>
                        	<span class="basic_span letter-space add-finace-lf">项目估值：</span>
                            <span class="m_r15 after after3">
                            	<input type="text" placeholder='项目估值' class='new_nputr_number addpro-input' id="formatValuations" data-title-id="1943" data-type="19" name="formatValuations provaluations"  data-rule-provaluations="true" data-msg-provaluations="<font color=red>*</font>支持13位长度的6位小数"/>
                            </span>
                            <!-- <span class="m_r30">万元</span> -->
                        </li>
                    </ul>
               </div>
               <!-- 团队成员 -->
                <div class='addpro-finacing-plan ADDcurrendTable'>
                 	<div class="addpro-new-title ">
                        <span class="new_color  add-pro-basicmessage">团队成员</span>
                    </div> 
                    <table style="width:97%;" id="team-table" cellspacing="0" cellpadding="0" class="team-table">
	                 <thead>
		                	<tr>
		                		<th width='15%'>姓名</th>
		                		<th width='15%'>性别</th>
		                		<th width='15%'>最高学历</th>
		                		<th width='15%'>联系电话</th>
		                		<th width='20%'>微信号</th>
		                		<th width='20%'>职位</th>
		                	</tr>	
		                </thead>	
		                <tbody>
		                	<tr>
		                		<td>
								<input placeholder="姓名" name="field1" type="text" class="" maxlength="50" required />
								</td> 
		                		<td class="selectcheck select">
									<select name="field3">
										<option value='1343'>男</option>
										<option value='1344'>女</option>
									</select>
								</td>
								<td class="selectcheck select">
									<select name="field5" class="txt_select txt" id="field5">
	               
                   					</select>
								</td>
								<td>
									 <input  type="text" class="txt " name="field4" class="" placeholder="手机号"  data-rule-phone="true"  data-msg-phone="<font color=red>*</font>格式不对" />
								</td>
								<td>
									  <input  type="text" class="txt " name="field6" class="fn-tinput" placeholder="微信号"  maxlength="20" data-rule-wechat="true"  data-msg-phone="<font color=red>*</font>允许输入字母、数字、下划线和减号" />								
								</td>
								<td class="selectcheck select">
									<select name="field2" class="txt_select txt" id="field2">
									</select>
								</td>
		                	</tr>	
		                </tbody>
	                </table>
                </div>
                    <!--实际投资-->
                <div class='addpro-business-plan'>
	                <div class="addpro-new-title ">
                        <span class="new_color  add-pro-basicmessage">商业计划书<em>(文件上传大小不超过25MB)</em></span>
                    </div> 
	                <!-- 商业计划书表格-->
	                <table style="width:97%;" id="plan_business_table" cellspacing="0" cellpadding="0" class="business-plan-table">
		               
	                </table>
	                <div class="compile_on_center">
                       <div class="compile_on_left addpro-compile">
                           <span class="pubbtn adddpro-save" onclick="add();">保存</span>
                           <span class="pubbtn addpro-cacel" data-name='industry' data-on="close">取消</span>
                       </div>  
                   </div>
                </div>
                 </form>
                    <!-- 商业计划书隐藏页面 -->
					<div id="uploadPanel"  style="display: none;">
						<div class="title_bj">上传更新</div>
						<div class="meetingtc margin_45">
						<dl class="fmdl clearfix">
					    	<dt>档案来源：</dt>
					        <dd class="clearfix">
					        	<label><input name="win_fileSource" type="radio" value = "1" checked="checked"/>内部</label>
					            <label><input name="win_fileSource" type="radio" value = "2"/>外部</label>
					        </dd>
					    </dl>
					    <dl class="fmdl clearfix">
					    	<dt>存储类型：</dt>
					        <dd>
					        	<select id="win_fileType">
					            	<option>sadasd</option>
					            </select>
					        </dd>
					    </dl>
					    <dl class="fmdl clearfix">
					    	<dt>业务分类：</dt>
					        <dd>
					<!--          	<input type="text" id="fileWorkType"  class="txt"/> -->
					<!--          	<input type="hidden" id="fileWorkTypeId"/> -->
					         	
					         	<select id="win_fileWorkType">
					            	<option>sadasd</option>
					            </select>
					         	
					        </dd>
					        <dd id="win_isProve_div">
					        	<label><input type="checkbox" value="1" id="win_isProve"/>签署凭证</label>
					        </dd>
					    </dl>
					    <dl class="fmdl clearfix">
					    	<dt>所属项目：</dt>
					        <dd>
					            <input type="hidden" id="win_sopFileId" data-tid=""  class="txt disabled"/>
					        	<input type="text" id="win_sopProjectId" data-tid=""  class="txt disabled"/>
					        </dd>
					       <dd><a class="searchbtn null" id="win_searchProjectBtn" href="javascript:;">搜索</a></dd>
					   
					    </dl>
					    
					     <dl class="fmdl clearfix">
					    	<dt>文档上传：</dt>
					        <dd>
					        	<input type="text" class="txt" id="win_fileTxt" readonly="readonly"/>
					        </dd>
					        <dd> <a href="javascript:;" class="pubbtn fffbtn" id="win_selectBtn">选择档案</a></dd>
					    </dl>  
					    <TEXTAREA ID="win_FILELIST"></TEXTAREA>
					
					    <a href="javascript:;" class="pubbtn bluebtn" id="win_uploadBtn" style="margin-left:80px;">上传保存</a>
					<%--     <input type="hidden" id="pathInput" value="<%=path%>"> --%>
						</div>
					</div>
                    
          </div>
        </div>
       <!--右边-->
        <div class="basic_right">
        <!-- 	<div class="tabtable_con_on">
            	<div class="new_bottom_color">
                    <span class="new_ico_hint"></span>
                    <span class="new_color size16">温馨提示</span>
                </div>
                <p class="basic_p">标记 <em class="red">*</em> 的内容需要进行填写，填写后方能进入内部评审阶段。（包括基本信息中的商业计划书、融资计划、项目描述、公司定位、用户画像、产品服务、行业分析、竞争分析；访谈记录；团队成员中的基本信息）</p>
            </div> -->
        </div>
        <!--右边 end--> 
    </div>
     
</div>
<jsp:include page="../common/footer.jsp" flush="true"></jsp:include></body>
<jsp:include page="../common/uploadwin.jsp" flush="true"></jsp:include>
<script src="<%=path %>/js/plupload.full.min.js" type="text/javascript"></script>
<script src="<%=path %>/js/plupload/zh_CN.js" type="text/javascript"></script>
<script src="<%=path%>/js/bootstrap-v3.3.6.js"></script>
<script src="<%=path%>/bootstrap/bootstrap-table/bootstrap-table-xhhl.js"></script>
<script src="<%=path%>/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/js/teamSheetNew2.js'></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/js/addPlanbusiness2.js'></script>
<!-- 校验 -->
<script type='text/javascript' src='<%=path%>/js/validate/jquery.validate.min.js'></script>
<script type='text/javascript' src='<%=path%>/js/projectDetail/tabInfoValidate.js'></script>
<script>
$(function(){
	$("#createDate").val(new Date().format("yyyy-MM-dd"));
	createMenus(5);  
	/**
	 * 本轮融资轮次下拉数据
	 * 项目来源下拉数据
	 * @version 2018-4-11
	 *开始
	 */
	sendGetRequest(platformUrl.queryAllTitleValues+'FNO1?reportType=4', null,CallBackB);
	function CallBackB(data){ 
	    var _dom=$("select[name='financeStatus']");
	        _dom.html("");
	        _dom.append('<option value="">请选择</option>');
	    var childNum = _dom.find("option").length;
	    var entity=data.entity.childList[0];
	    if(!childNum || childNum !=0 ){
	    	$.each(entity.valueList,function(){
	    		_dom.append("<option value='"+this.id+"' data-title-id='"+this.titleId+"'>"+this.name+"</option>");
			});
	    	_dom.selectpicker();
	    }
	    //项目来源下拉数据
	    CallBackD(data)
	}
	function CallBackD(data){ 
	    var _dom=$("select[name='proSource']");
	        _dom.html("");
	        _dom.append('<option value="">请选择</option>');
	    var childNum = _dom.find("option").length;  
	    var entity=data.entity.childList.filter(function(val){return val.titleId=="1120"})[0];  
	    if(!childNum || childNum !=0 ){
	    	$.each(entity.valueList,function(){
	    		_dom.append("<option value='"+this.id+"' code='"+this.code+"'  data-title-id='"+this.titleId+"'>"+this.name+"</option>");
			}); 
	    	_dom.selectpicker()
	    }
	} 
})
//结束

/**
 * 项目名字重复
 * @version 2018-4-11
 *开始
 */
 //这儿会导致验证有点问题
  $('#projectName').blur(function(){
	var projectName=$("#projectName").val().trim();
	if(projectName==""||projectName=="undefined"){
		return false
	}else{
		var data2 = {
				'projectName' : projectName
		}
		sendPostRequestByJsonObj(platformUrl.checkProjectName,data2,function(data){ 
				if(data.result.status=="ERROR"){
			       objDatad =data.userData;
                    if(data.result.errorCode == "name-repeat"){  
            			$("select").attr("disabled",true)
                        layer.alert("您输入的项目与【"+objDatad.projectName+"】项目重复，不能保存。<br/>项目承做人："+objDatad.teamPerson +" | "+ objDatad.departmentName);
                        $('.project-name').css('display','block');
                    } 
				}else if(data.result.status ==='OK'){
					$('.project-name').css('display','none');
				}
		})
	}  
})  
//结束

/**
 * 项目来源和承揽人等联动
 * @version 2018-4-11
 *开始
 */ 
$("select[name='proSource']").change(function(){
	$(".projectSource").hide();
	var selCode=$(this).find("option:checked").attr("code");
	$("."+selCode).show(); 
	$("#selectRadio option").attr("selected",false);
	$("button.selectpicker").attr("title",'请选择');
	$("button.selectpicker span").text("请选择");
	$("ul.selectpicker li").removeClass("selected");
	$(".projectSource input").val("")
	$(".trSouce input").val("");
	$(".trSouceOther").hide().val("")
	$("span.error").hide();
	$(".selectcheck input.addpro-input").hide();
	 $('#selectRadio').selectpicker({
  			 dropupAuto:false
             });
}) 
$("#selectRadio").change(function(){
        $(".add-project-tabtable #selectRadio-error").hide();
		var otherValue = $(this).find("option").last().val();
		var value = $(this).val();
		if(value==null){
			$(".selectcheck .addpro-input").hide().val("").removeAttr("name");
			return;
		}
		var filt = value.filter(function(val){return val==otherValue});
		if(filt.length>0){
			$(".selectcheck .addpro-input").show().attr("name",'pickeother');
			$(".selectcheck .addpro-input").attr("ovalue",filt[0])
		}else{
			$(".selectcheck .addpro-input").hide().val("").removeAttr("name");
		}
	})
//结束
/**
 * 获取项目承揽人下拉项
 * @version 2016-06-21
 */
 sendGetRequest(platformUrl.searchCLR, null,CallBackE);
 function CallBackE(data){ 
 	var data_list = data.entityList; 
 	var res="";
 	$.each(data_list,function(){
 		if(this.departmentName!=null){
 			res+="<option value='"+this.id+"' data-type='23' data-title-id='1118'>"+this.realName+'&nbsp;&nbsp;|&nbsp;&nbsp;'+this.departmentName+"</option>"
 		}else{
 			res+="<option value='"+this.realName+"' data-type='23' data-title-id='1118'>"+this.realName+"</option>"
 		}
 		
 	})
 	$("#selectRadio").html(res) 
} 
//结束
/**
* 查询事业线  行业归属下拉
* @version 2016-06-21
*/
createDictionaryOptions(platformUrl.searchDictionaryChildrenItems+"industryOwn","industryOwn");
$("select[name='industryOwn']").selectpicker() 
//结束

//估值计算 
$("#formatShareRatio").blur(function(){ 
	var projectShareRatio = $("#formatShareRatio").val();
	var projectContribution = $("#formatContribution").val();
	var valuations = finalValue(projectContribution,projectShareRatio);
	if(valuations != null){
		$("#formatValuations").val(valuations).attr("guzhi",valuations);
	}else{
		$("#formatValuations").removeAttr("guzhi");
	}
});
$("#formatContribution").blur(function(){ 
	var projectShareRatio = $("#formatShareRatio").val();
	var projectContribution = $("#formatContribution").val();
	var valuations = finalValue(projectContribution,projectShareRatio);
	if(valuations != null){
		$("#formatValuations").val(valuations).attr("guzhi",valuations);
	}else{
		$("#formatValuations").removeAttr("guzhi");
	}
});
//结束
//团队下拉 
var map_pos = selectCache("team-members","field5") 
/**
团队select 字典缓存
*/
var map_pos = selectCache("team-members","field5");
var map_field2 = selectCache("team-members","field2");

var xlOP="";
var xlOP2="";
$.each(map_field2,function(e,index){ 
	xlOP2 += "<option value="+e+">"+index+"</option>"
})
$.each(map_pos,function(e,index){ 
	xlOP += "<option value="+e+">"+index+"</option>"
})
$("#team-table select[name=field5]").html(xlOP).selectpicker();
$("#team-table select[name=field2]").html(xlOP2).selectpicker(); 
$("#team-table select[name=field3]").selectpicker(); 
function selectCache(subCode,filed){
    var map = {};
	sendGetRequest(platformUrl.getDirectory+"1303"+'/'+subCode+"/"+filed,null,
		function(data) {
			var result = data.result.status;
			if (result == 'OK')
			{
				var dataMap = data.userData;
			    var list=dataMap[filed];
			    var name=""
				$.each(list, function(i, value){
				     map[value.id]=value.name;
				});
			}
		})
		return map;
}





/**
* 查询事业线  行业归属下拉
* @version 2018-04-11
*/ 
//验证不忽略隐藏的select（使用了插件）
$.validator.setDefaults({ignore: ".projectSource :hidden"});
function add(){     
	//保存前的验证
	//1.项目名称是否重复  
	 if(!$('.project-name').is(":hidden")&&$("#projectName").val().trim()!=''){
		  layer.alert("您输入的项目与【"+objDatad.projectName+"】项目重复，不能保存。<br/>项目承做人："+objDatad.teamPerson +" | "+ objDatad.departmentName);
		return false;
	}
	//2.项目承揽人
    $("#selectRadio[name=projectContractor]").css("display","inline-block");
	//3.表单验证  
    if(!$('#add_form').validate().form()){//验证不通过时候执行
		$(".adddpro-save").submit();
		return false;	
	}
	//开始新建项目
    var data1= JSON.stringify(getUpdateData());//转换成字符串 
	if(formData != data1){ 
		//获取TOKEN 用于验证表单提交
		sendPostRequest(platformUrl.getToken,function(data){
			TOKEN=data.TOKEN;
			return TOKEN;
		});
	}   
	sendPostRequestBySignJsonStr(platformUrl.addProject,data1, function(data){
		console.log(data);
		if(!data){
			layer.msg("提交表单过于频繁!");
		}else if(data.result.status=="ERROR"){
			if(data.result.errorCode == "csds"){
				layer.msg("必要的参数丢失!");
			}else if(data.result.errorCode == "myqx"){
				layer.msg("没有权限添加项目!");
			} 
			formData = JSON.stringify(getUpdateData());
		}else{
			debugger;
			//saveBaseInfo("add_form",data.id,data.id);
			
		}
		
	},TOKEN);
	
	
}
function getUpdateData(){  //获取保存数据
	var projectType=$('input:radio[name="projectType"]:checked').val();
	var projectName=$("#projectName").val().trim();
	var createDate=$("#createDate").val().trim();
	var industryOwn=$('select[name="industryOwn"] option:selected').attr("value");	
	var formatData={
  				   "projectType":projectType,
			       "projectName":projectName,
			       "createDate":createDate,
			       "industryOwn":industryOwn
	};
	return formatData;
}
</script>
</html>

