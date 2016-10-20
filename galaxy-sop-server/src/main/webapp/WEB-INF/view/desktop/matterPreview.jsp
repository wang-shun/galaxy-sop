<%@ page language="java" pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath();
%>

<!-- 事项预览 -->
<dl>
	<dt>
		<h3 class="ico t7">事项预览</h3>
	</dt>
	<dd>
		<div class="l_previewTop clearfix">
			<div style="margin-left: 25%; display: none">
				<span vertical-align:middle>今日会议总数<a id="meeting_number_today" href="javascript:;">0</a>个
				</span>
			</div>
			<span class='bj_pagebox'>今日：立项会 <a id="lxh_number_today" href="javascript:;">0</a>个
			</span> <span class='bj_pagebox bj_pagebox_left'>投决会 <a id="tjh_number_today" href="javascript:;">0</a>个
			</span> <span class='bj_pagebox bj_pagebox_left'>评审会 <a id="psh_number_today" href="javascript:;">0</a>个
			</span>
		</div>
		<div class="l_previewBottom clearfix">
		<div class="l_previewBottom_l l_previewBottom_b fl">
			<a href="javascript:;" id=""></a>
				<span><p>排期等待</p></span>
			</div>
			<div class="l_previewBottom_l l_previewBottom_b fl">
			<a href="javascript:;" id="lxh_eduling_wait"></a>
				<span><p>立项会</p></span>
			</div>
			<div class="l_previewBottom_r l_previewBottom_b fl">
			<a href="javascript:;" id="tjh_eduling_wait"></a>
				<span><p>投决会</p></span>
			</div>
			<div class="l_previewBottom_r l_previewBottom_b fl">
			<a href="javascript:;" id="psh_eduling_wait"></a>
				<span><p>评审会</p></span>
			</div>
		</div>
	</dd>
</dl>

<script src="<%=path %>/js/charts/indexMatterPreview.js"></script>
<script>
matterPreviewUtils.init();
</script>
