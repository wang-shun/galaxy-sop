<%@ page language="java" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
%>

<div class="meetingtc">
	<div class="top clearfix">
    	<div class="searchall clearfix">
            <dl>
            	<dt>项目 :</dt> 
                <dd>
                	<select id="projectId" name="projectId"   
                	valType="required" msg="<font color=red>*</font>项目不能为空" >
                    </select>
                	<!-- <input type="text" id="proName" name = "proName"  placeholder="请输入关键字查找" class="txt"/>
                	<input type="hidden" id="projectId" name = "projectId" value="" /> -->
                </dd>
            </dl>
        </div>
        <dl class="fmdl clearfix">   <!-- valType="required" msg="<font color=red>*</font>不能为空" -->
            <dt>访谈日期：</dt>
            <dd>
            	<input type="text" class="datepicker txt time" readonly  id="viewDate" name ="viewDate" style="height:23px;"  
            	valType="required" msg="<font color=red>*</font>访谈日期不能为空"  />
            </dd>
        </dl>
    </div>
    
    <div class="min clearfix">
    	<dl class="fmdl fml clearfix">
            <dt>访谈对象：</dt>
            <dd>
           		<input type="text" id="viewTarget" name = "viewTarget"  placeholder="访谈对象" class="txt" maxLength="50"  
           		valType="required" msg="<font color=red>*</font>访谈对象不能为空"  />
            </dd>
        </dl>
    </div>
    
<!-- 赋值     var um = UM.getEditor('describe_editor');
		um.setContent(data.entity.projectDescribe); 
		
	取值 	var um = UM.getEditor('viewNotes');
		var projectDescribe = um.getContent();
		-->
		
	    <dl class="fmdl clearfix">
	        <dt>访谈纪要:</dt>
	        <dd>
	        	  <div type="text/plain" id="viewNotes" style="width:100%;max-width:99%; height:150px;max-height:150px;overflow:auto;">
	        	  </div>
			</dd>
        </dl>
            
    
    <dl class="fmdl clearfix">
        <dt>访谈录音：</dt>
        
            <div class="fmload clearfix">
            <dd>
	        	<input type="text" name="fileName" id="fileName" class="txt" readonly="readonly" />
	        </dd>
	        <dd>
	        	<a href="javascript:;" class="pubbtn fffbtn" id="file-select-btn" >上传录音</a>
    		</dd>
            </div>
        
    </dl>
    
    <div class="btnbox"> <!-- saveInterView() -->
    	<a href="javascript:;" id="saveInterView" class="pubbtn bluebtn">保存</a><a href="javascript:;" class="pubbtn fffbtn" data-close="close">取消</a>
    </div>
</div>

<!-- 富文本编辑器 -->
<script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path %>/ueditor/umeditor.min.js"></script>
<script type="text/javascript" src="<%=path %>/ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- time -->
<script src="<%=path %>/bootstrap/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="<%=path %>/bootstrap/bootstrap-datepicker/js/datepicker-init.js"></script>

<%-- <jsp:include page="../common/validateJs.jsp" flush="true"></jsp:include> --%>
 <script type="text/javascript">
		UM.getEditor('viewNotes');
</script>
    
