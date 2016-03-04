<%@ page language="java" pageEncoding="UTF-8"%>
<div class="meetingtc">
	<div class="top clearfix">
    	<div class="searchall clearfix">
            <dl>
            	<dt>项目 :</dt>
                <dd>
                	<select id="projectId" name="projectId" >
                    </select>
               	 <!-- <input type="text" placeholder="请输入关键字查找" class="txt"/> -->
                </dd>
            </dl>
            <!-- <a href="javascript:;" class="searchbtn null">搜索</a> -->
        </div>
        <dl class="fmdl clearfix">
            <dt>会议召开日期：</dt>
            <dd>
                <input type="text" id="meetingDateStr" name = "meetingDateStr"  placeholder="访谈日期" class="txt" />
            </dd>
        </dl>
    </div>
    <div class="min clearfix">
    	<dl class="fmdl fml clearfix">
            <dt>会议类型：</dt>
            <dd class="clearfix">
                <label><input type="radio" name="meetingType" value="meetingType:1"/>内评会</label>
                <label><input type="radio" name="meetingType" value="meetingType:2"/>CEO评审</label>
                <label><input type="radio" name="meetingType" value="meetingType:3"/>立项会</label>
                <label><input type="radio" name="meetingType" value="meetingType:4"/>投决会</label>
            </dd>
        </dl>
        <dl class="fmdl clearfix">
            <dt>会议结论：</dt>
            <dd class="clearfix">
                <label><input type="radio" name="meetingResult" value="meetingResult:1"/>通过</label>
                <label><input type="radio" name="meetingResult" value="meetingResult:2"/>待定</label>
                <label><input type="radio" name="meetingResult" value="meetingResult:3"/>否决</label>
            </dd>
        </dl>
    </div>
    <dl class="fmdl clearfix">
        <dt>会议纪要：</dt>
        <dd>
        
        <div class="edit">
        	<textarea style="width:588px;height:200px;" id="meetingNotes" name = "meetingNotes"  placeholder="访谈纪要"  ></textarea>
         </div>
         
<!--             <div class="edit">
                这里是编辑器
            </div> -->
        </dd>
    </dl>
    <dl class="fmdl clearfix">
        <dt>会议录音：</dt>
        <dd>
            <div class="fmload clearfix">
                <p class="loadname"></p>
                <input type="file" class="load"/>
                <a href="javascript:;" class="pubbtn fffbtn">上传录音</a>
                
                 <input type="hidden" id="meetfileID" name = "fileId" value="" /> 
            </div>
        </dd>
    </dl>
    <div class="btnbox">
    	<a href="javascript:saveMeet()" class="pubbtn bluebtn">保存</a><a href="javascript:;" class="pubbtn fffbtn"data-close="close">取消</a>
    </div>
</div>