<%@ page language="java" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
%>
<div class="addmentc">
  <form action="" id="up_stock_form" method="post" type="validate">
  <input type="hidden" value="" name="projectId" id="projectId">
  <input type="hidden" name="id" value="${share.id }">
  <div class="form clearfix">
    <div class="left">
      <dl class="fmdl fml">
        <dt>所有权人：</dt>
        <dd><input type="text" name="sharesOwner" value="${share.sharesOwner }" class="txt" valType="required" msg="<font color=red>*</font>所有权不能为空"/></dd>
      </dl>
      <dl class="fmdl">
        <dt>占比：</dt>
        <dd><input type="text" name="sharesRatio" value="${share.sharesRatio }" class="txt" valType="required" msg="<font color=red>*</font>占比不能为空"/></dd>
      </dl> 
    </div>
    <div class="right">
      <dl class="fmdl">
        <dt>类型：</dt>
        <dd><input type="text" name="sharesType" value="${share.sharesType }" class="txt" valType="required" msg="<font color=red>*</font>类型不能为空"/></dd>
      </dl>  
      <dl class="fmdl">
        <dt>获取方式：</dt>
        <dd><input type="text" name="gainMode" value="${share.gainMode }" class="txt" valType="required" msg="<font color=red>*</font>获取方式不能为空"/></dd>
      </dl>
    </div>
  </div>
  <div class="form_textarea">
    <dl class="fmdl">
      <dt>备注：</dt>
      <dd><textarea name="remark" >${share.remark }</textarea></dd>
    </dl>
  </div>
      <a href="javascript:updateStock();" class="pubbtn bluebtn">保存</a>
  </form>
</div>