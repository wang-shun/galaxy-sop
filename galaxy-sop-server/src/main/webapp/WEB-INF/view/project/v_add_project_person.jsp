<%@ page language="java" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
%>
<div class="addPersontc">
	<div class="title_bj" id="popup_name"></div>
		<form action="" id="add_person" method="post">
		<input type="hidden" value="0" name="tempStatus">
		<input type="hidden" value="${uuid}" name="uuid">
        <div class="addPerson_all" id="person-pool">
            <div class="info clearfix">
                <h3>基本信息</h3>
                <dl class="fmdl fl">
                    <dt><em class="red">*</em>&nbsp;姓名：</dt>
                    <dd><input type="text" class="txt" name="personName" valtype="required" msg="<font color=red>*</font>姓名不能为空"/></dd>
                </dl>
                <dl class="fmdl fl">
                    <dt><em class="red">*</em>&nbsp;性别：</dt>
                    <dd class="clearfix">
                        <label><input type="radio" value="0" name="personSex" checked="checked" />男</label>
                        <label><input type="radio" value="1" name="personSex" />女</label>
                    </dd>
                </dl>
                <dl class="fmdl fl">
                    <dt><em class="red">*</em>&nbsp;当前职务：</dt>
                    <dd><input type="text" class="txt" name="personDuties" valtype="required" msg="<font color=red>*</font>当前职务不能为空"/></dd>
                </dl>
                <dl class="fmdl fl">
                    <dt><em class="red">*</em>&nbsp;出生日期：</dt>
                    <dd>
                        <input name="personBirthdayStr" type="text" class="datetimepickerHour txt time" readonly="" value="" valtype="required" msg="<font color=red>*</font>出生日期不能为空">
                    </dd>
                </dl>
                 <dl class="fmdl fl">
                    <dt>电话号码：</dt>
                    <dd><input type="text" class="txt" placeholder="请输入电话号码" name="personTelephone" valtype="MOBILE" msg="<font color=red>*</font>手机格式不正确"/></dd>
                </dl>
                <dl class="fmdl fl">
                    <dt><em class="red">*</em>&nbsp;是否为联系人：</dt>
                    <dd class="clearfix">
                        <label><input type="radio" value="0" name="isContacts" checked="checked"/>是</label>
                        <label><input type="radio" value="1" name="isContacts"/>否</label>
                    </dd>
                </dl>
                <dl class="fmdl fl block">
                    <dt>备注：</dt>
                    <dd><textarea maxlength="50" name="remark"></textarea></dd>
                </dl>
            </div>
            <div class="qualifications">
            	<input type="hidden" value="0" id="person-learning"/>
                <h3>学历背景</h3>
                <span onclick="addPersonLearning();" class="blue fr add" data-btn="qualifications" data-name="添加学历背景">添加</span>
                <table id="learning-table" style="width:94%;"  cellspacing="0" cellpadding="0" class="basic_table table">
                    <thead>
	                    <tr>
	                        <th>毕业院校</th>
	                        <th>专业</th>
	                        <th>时间</th>
	                        <th>学历</th>
	                        <th>操作</th>
	                    </tr>
                    </thead>
                    <tbody id="learning-tbody">
	                    
                    </tbody>
                </table>
            </div>
            <div class="qualifications">
            	<input type="hidden" value="0" id="person-work"/>
                <h3>工作履历</h3>
                <span class="blue fr add" onclick="addPersonWork();" data-btn="addPersonWork" data-name="添加工作履历">添加</span>
                <table id="work-table" style="width:94%;"  cellspacing="0" cellpadding="0" class="basic_table table">
	                <thead>
	                    <tr>
	                        <th>时间</th>
	                        <th>任职公司名称</th>
	                        <th>职位</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
                    <tbody id="work-tbody">
	                    
                    </tbody>
                </table>
            </div>
        </div>
        </form>
    <div class="button_affrim">
        <a href="javascript:;"  class="register_all_affrim fl" id="save_person" >确定</a>
        <a href="javascript:;"  class="register_all_input fr"  data-close="close">取消</a>
    </div>
</div>
<div id="learn-tip" class="tip-yellowsimple" style="display:none;visibility: inherit; left: 75px; top: 280px; opacity: 1; width: 89px;">
	<div class="tip-inner tip-bg-image">
		<font color="red">*</font>学历背景不能为空
	</div>
	<div class="tip-arrow tip-arrow-left" style="visibility: inherit;"></div>
</div>
<div id="work-tip" class="tip-yellowsimple" style="display:none;visibility: inherit; left: 75px; top: 300px; opacity: 1; width: 89px;">
	<div class="tip-inner tip-bg-image">
		<font color="red">*</font>工作履历不能为空
	</div>
	<div class="tip-arrow tip-arrow-left" style="visibility: inherit;"></div>
</div>
<jsp:include page="../common/validateJs.jsp" flush="true"></jsp:include>
<script>
$('input[name="personBirthdayStr"]').datepicker({
    format: 'yyyy-mm-dd',
    language: "zh-CN",
    autoclose: true,
    todayHighlight: false,
    defaultDate : Date,
    today: "Today",
    todayBtn:'linked',
    leftArrow: '<i class="fa fa-long-arrow-left"></i>',
    rightArrow: '<i class="fa fa-long-arrow-right"></i>',
    forceParse:false,
    currentText: 'Now'
});
$(function(){
	$('input[name="personBirthdayStr"]').val(new Date().format("yyyy-MM-dd"));
	generatePersonEmptyInnerHtml();
	generateLearningEmptyInnerHtml();
	generateWorkEmptyInnerHtml();
	var uuid = $('input[name="uuid"]').val();
	initDialogValstr("person-pool");
	$("#save_person").click(function(){
		$("body").css("overflow","auto");
		$("#learn-tip").css("display","none");
		$("#work-tip").css("display","none");
		if(beforeSubmitById("person-pool")){
			var learns = $("#person-learning").val();
			var works = $("#person-work").val();
			if(learns <= 0){
				$("#learn-tip").css("display","block");
				return;
			}
			if(works <= 0){
				$("#work-tip").css("display","block");
				return;
			}
			
			$('input[name="tempStatus"]').val(1);
			sendPostRequestByJsonStr(Constants.sopEndpointURL + "/galaxy/project/savePerson/"+uuid+"/"+pid, 
					$("#add_person").serializeObject(), 
					function(data){
				$.popupTwoClose();
				if(data.result.status == 'OK'
					&& typeof(data.entityList) != 'undefined' 
					&& data.entityList.length > 0){
					$.popupOneClose();
					generatePersonInnerHtml(data.entityList);
					$("#person").val(data.entityList.length);
					nextBtn();
				}else{
					generatePersonEmptyInnerHtml();
				}
			});
		}
	});
	//调整谈弹窗提示框
	$("#person-pool").scroll(function(){
		var offsetTop=$(this).offset().top;
		var top=$(this).scrollTop();
		console.log(top);
		$(".tip-yellowsimple").css("margin-top",-top);
		var marginTop=$(".tip-yellowsimple").css("margin-top");
		for(var i=0;i<$(".tip-yellowsimple").length;i++){
			var txt=$(".tip-yellowsimple").eq(i).text();
			if(top>130 && txt=="*手机格式不正确"){
				$(".tip-yellowsimple").eq(i).css("opacity","0");
			}else if(top>80 && txt=="*当前职务不能为空"){
				$(".tip-yellowsimple").eq(i).css("opacity","0");
			}else if(top>50 && txt=="*姓名不能为空"){
				$(".tip-yellowsimple").eq(i).css("opacity","0");
			}else{
				$(".tip-yellowsimple").eq(i).css("opacity","1");
			}
		}
	})
});
</script>