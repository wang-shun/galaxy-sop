<%@ page language="java" pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath(); 
%>

<!-- 校验 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/validate/lib/tip-yellowsimple/tip-yellowsimple.css" />

<script type="text/javascript" src="<%=request.getContextPath() %>/js/validate/lib/jquery.poshytip.js"></script>
<script type='text/javascript' src='<%=request.getContextPath() %>/js/validate/lib/jq.validate.js'></script>
<script src="<%=path %>/js/utils.js"></script>
<link rel="stylesheet" href="<%=path %>/css/showLoading.css"  type="text/css">
<div class="addmentc">
		<div class="title_bj" id="popup_name">编辑会议纪要</div>
		<input type="hidden" id="remainMoneyPart" value=""/>
		<input type="hidden" id="totalMoneyPart" value=""/>
	    <div class="form clearfix grantPartTc"  id="actual_aging_container">
	        <div class="appr_aging">
	           <form id="actual_aging_form">
		           
	                <dl class="fmdl fl_l  clearfix">
	                <dt>分拨名称 ：</dt>
	                <dd>
	                	<div id="grantName">
	                    </div>
	                </dd>
	                </dl> 
	                <dl class="fmdl fl_l  clearfix">
		                <dt>计划注资时间 ：</dt>
		                <dd>
		                	<div id="grantDetail">
		                    </div> 
		                </dd>
		            </dl>
	                 <dl class="fmdl fl_l  clearfix">
		                <dt>计划注资金额 ：</dt>
		                <dd>
		                	
		                	<div class='moeny_all'>
		                	    <span id="grantMoney"></span>
		                    	<span id="editMoney" class="bj_hui"></span><span>万元</span>
		                    	
		                    </div> 
	                        <div class="gray">剩余金额<span id="formatRemainMoney"></span>万元</div> 
		                </dd>
		            </dl>
		             <dl class="fmdl fl_l  clearfix">
		                 <dt>付款条件：</dt>
		                <dd style='width:70%;'>
		                	<div id="payCondition">
		                    </div> 
		                </dd>
		            </dl>
                 </form>
		            <dl class="fmdl fl_l" id="show_up_file">
		                 <table style="width:500px;margin: auto;" id="filelist"  cellspacing="0" cellpadding="0">
		                 <thead>
		                    <tr>
		                      <th style="width:265px;">文件名称</th>
		                      <th style="width:105px" align="center">文件大小</th>
		                    </tr>
		                 </thead>
		                 </table> 
		            </dl>
         
	            
	             
	        </div>
	    </div>
	</div>
