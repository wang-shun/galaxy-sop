package com.galaxyinternet.model.operationLog;


/**
 * @Description: sop流程中操作日志的规范类
 * @author keifer
 * @date 2016年3月16日
 */
public enum OperationLogType {
	
    interview_add("/galaxy/progress/p1/add/"+UrlNumber.one, OperType.ADD.getOperationType(), "接触访谈",SopStage.TOUCH_INTERVIEW.getStageName()), 
	
    interview_update("/galaxy/progress/p1/add/"+UrlNumber.two, OperType.UPDATE.getOperationType(), "接触访谈",SopStage.TOUCH_INTERVIEW.getStageName()), 
	
	
	/* 2017.7 v1.5   会后商务谈判      -----------------------------------*/
	
	//meetingType:5   会后商务谈判
    meeting_1_add("/galaxy/progress/p2/add/"+UrlNumber.one, OperType.ADD.getOperationType(), "内部评审会议记录",SopStage.INNER_REVIEW_SCHEDULE.getStageName()), 
	
	meeting_1_update("/galaxy/progress/p2/add/"+UrlNumber.two, OperType.UPDATE.getOperationType(), "内部评审会议记录",SopStage.INNER_REVIEW_SCHEDULE.getStageName()), 
	
    meeting_2_add("/galaxy/progress/p2/add/"+UrlNumber.three, OperType.ADD.getOperationType(), "CEO评审会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	
	meeting_2_update("/galaxy/progress/p2/add/"+UrlNumber.four, OperType.UPDATE.getOperationType(), "CEO评审会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	
    meeting_3_add("/galaxy/progress/p2/add/"+UrlNumber.five, OperType.ADD.getOperationType(), "立项会会议记录",SopStage.PROJECT_SCHEDULE.getStageName()), 
	
	meeting_3_update("/galaxy/progress/p2/add/"+UrlNumber.six, OperType.UPDATE.getOperationType(), "立项会会议记录",SopStage.PROJECT_SCHEDULE.getStageName()), 
	
    meeting_4_add("/galaxy/progress/p2/add/"+UrlNumber.seven, OperType.ADD.getOperationType(), "会后商务谈判会议记录",SopStage.SW_TP.getStageName()), 
	
	meeting_4_update("/galaxy/progress/p2/add/"+UrlNumber.eight, OperType.UPDATE.getOperationType(), "会后商务谈判会议记录",SopStage.SW_TP.getStageName()), 
	
	meeting_5_add("/galaxy/progress/p2/add/"+UrlNumber.nine, OperType.ADD.getOperationType(), "投决会会议记录",SopStage.VOTE_DECISION_MEETING.getStageName()), 
	
	meeting_5_update("/galaxy/progress/p2/add/"+UrlNumber.ten, OperType.UPDATE.getOperationType(), "投决会会议记录",SopStage.VOTE_DECISION_MEETING.getStageName()), 
	
	//会议列表页面操作
	menu_meeting_1_add("/galaxy/meeting/save/"+UrlNumber.one, OperType.ADD.getOperationType(), "内部评审会议记录",SopStage.INNER_REVIEW_SCHEDULE.getStageName()), 
	menu_meeting_1_update("/galaxy/meeting/save/"+UrlNumber.two, OperType.UPDATE.getOperationType(), "内部评审会议记录",SopStage.INNER_REVIEW_SCHEDULE.getStageName()), 
		
	menu_meeting_2_add("/galaxy/meeting/save/"+UrlNumber.three, OperType.ADD.getOperationType(), "CEO评审会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	menu_meeting_2_update("/galaxy/meeting/save/"+UrlNumber.four, OperType.UPDATE.getOperationType(), "CEO评审会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
		
	menu_meeting_3_add("/galaxy/meeting/save/"+UrlNumber.five, OperType.ADD.getOperationType(), "立项会会议记录",SopStage.PROJECT_SCHEDULE.getStageName()), 
	menu_meeting_3_update("/galaxy/meeting/save/"+UrlNumber.six, OperType.UPDATE.getOperationType(), "立项会会议记录",SopStage.PROJECT_SCHEDULE.getStageName()), 
		
	menu_meeting_4_add("/galaxy/meeting/save/"+UrlNumber.seven, OperType.ADD.getOperationType(), "会后商务谈判会议记录",SopStage.SW_TP.getStageName()), 
	menu_meeting_4_update("/galaxy/meeting/save/"+UrlNumber.eight, OperType.UPDATE.getOperationType(), "会后商务谈判会议记录",SopStage.SW_TP.getStageName()), 
		
	menu_meeting_5_add("/galaxy/meeting/save/"+UrlNumber.nine, OperType.ADD.getOperationType(), "投决会会议记录",SopStage.VOTE_DECISION_MEETING.getStageName()), 
	menu_meeting_5_update("/galaxy/meeting/save/"+UrlNumber.ten, OperType.UPDATE.getOperationType(), "投决会会议记录",SopStage.VOTE_DECISION_MEETING.getStageName()), 
	
	menu_meeting_1_del("/galaxy/meeting/del/"+UrlNumber.one, OperType.DELETE.getOperationType(), "内部评审会议记录",SopStage.INNER_REVIEW_SCHEDULE.getStageName()), 
	menu_meeting_2_del("/galaxy/meeting/del/"+UrlNumber.two, OperType.DELETE.getOperationType(), "CEO评审会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	menu_meeting_3_del("/galaxy/meeting/del/"+UrlNumber.three, OperType.DELETE.getOperationType(), "立项会会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	menu_meeting_4_del("/galaxy/meeting/del/"+UrlNumber.four, OperType.DELETE.getOperationType(), "投决会会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	menu_meeting_5_del("/galaxy/meeting/del/"+UrlNumber.five, OperType.DELETE.getOperationType(), "会后商务谈判会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	/* 2017.7 v1.5 sopfile-flow -----------------------------------------*/

	//projectProgress:4
	//	立项报告     fileWorktype:17  
	p5_f5_lxhbg_up("/galaxy/progressT/optProFlowFiles/"+UrlNumber.eleven, OperType.UPLOAD.getOperationType(), 
			"立项报告",SopStage.PROJECT_SCHEDULE.getStageName()),
	p5_f5_lxhbg_edit("/galaxy/progressT/optProFlowFiles/"+UrlNumber.twelve, OperType.UPDATE.getOperationType() , 
			"立项报告",SopStage.PROJECT_SCHEDULE.getStageName()),
	
	
	//projectProgress:5
	//	投资意向书     fileWorktype:5  UrlNumber.one  two
	p5_f5_tzxys_up("/galaxy/progressT/optProFlowFiles/"+UrlNumber.one, OperType.UPLOAD.getOperationType(), 
			"投资意向书",SopStage.INVESTMENT_INTENT.getStageName()),
	p5_f5_tzxys_edit("/galaxy/progressT/optProFlowFiles/"+UrlNumber.two, OperType.UPDATE.getOperationType() , 
			"投资意向书",SopStage.INVESTMENT_INTENT.getStageName()),
	
	
	//projectProgress:6
	//	业务尽职调查报告      fileWorktype:1   three
	//	人力资源尽职调查报告      fileWorktype:2
	//	法务尽职调查报告    fileWorktype:3
	//	财务尽职调查报告    fileWorktype:4
	p6_f1_ywjd_up("/galaxy/progressT/optProFlowFiles/"+UrlNumber.three, OperType.UPLOAD.getOperationType(), 
			"业务尽职调查报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	p6_f1_ywjd_edit("/galaxy/progressT/optProFlowFiles/"+UrlNumber.ten, OperType.UPDATE.getOperationType(), 
					"业务尽职调查报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//	尽职调查启动会报告    fileWorktype:18  four  five
	//	尽职调查总结会报告    fileWorktype:19	six   seven
	p6_f18_jzqd_up("/galaxy/progressT/optProFlowFiles/"+UrlNumber.four, OperType.UPLOAD.getOperationType(), 
			"尽职调查启动会报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	p6_f18_jzqd_edit("/galaxy/progressT/optProFlowFiles/"+UrlNumber.five, OperType.UPDATE.getOperationType(), 
			"尽职调查启动会报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	p6_f19_jzzj_up("/galaxy/progressT/optProFlowFiles/"+UrlNumber.six, OperType.UPLOAD.getOperationType(), 
			"尽职调查总结会报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	p6_f19_jzzj_edit("/galaxy/progressT/optProFlowFiles/"+UrlNumber.seven, OperType.UPDATE.getOperationType(), 
			"尽职调查总结会报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),

	
	//projectProgress:8  投资协议
	//	投资协议     fileWorktype:6     eight
	//	股权转让协议     fileWorktype:7  nine
	p8_f6_tzxy_up("/galaxy/progressT/optProFlowFiles/"+UrlNumber.eight, OperType.UPLOAD.getOperationType(), 
			"投资协议",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	p8_f6_gqzr_up("/galaxy/progressT/optProFlowFiles/"+UrlNumber.nine, OperType.UPLOAD.getOperationType(), 
			"股权转让协议",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	
	
	//projectProgress:9  股权交割
	//  工商转让凭证     fileWorktype:8
	//  资金拨付凭证     fileWorktype:9
	
	
	

	/*项目操作日志的配置-------------------开始-----------------------------------------------------------*/
	//接触访谈阶段
	//添加访谈的弹出框中点击‘保存’
	ADD_INTERVIEW("/galaxy/project/stageChange/"+UrlNumber.one, OperType.ADD.getOperationType(), "访谈记录",SopStage.TOUCH_INTERVIEW.getStageName()), 
	ADD_INTERVIEW_FILE_RECORD("/galaxy/project/progress/addFileInterview", OperType.ADD.getOperationType(), "访谈记录",SopStage.TOUCH_INTERVIEW.getStageName()), 
	ADD_INTERVIEW_RECORD("/galaxy/project/progress/addInterview", OperType.ADD.getOperationType(), "访谈记录",SopStage.TOUCH_INTERVIEW.getStageName()), 
	
	//点击‘启动内部评审’
	SUBMIT_INNER_REVIEW_SCHEDULE("/galaxy/progress/stageChange/"+UrlNumber.one, OperType.SUBMIT.getOperationType(), "内部评审",SopStage.TOUCH_INTERVIEW.getStageName()), 
	
	//内部评审阶段
	//添加会议记录的弹出框中点击‘保存’。
	INNER_REVIEW_SCHEDULE("/galaxy/project/stageChange/"+UrlNumber.two, OperType.ADD.getOperationType(), "会议记录",SopStage.INNER_REVIEW_SCHEDULE.getStageName()), 
	ADD_MEETING_FILE_ONE("/galaxy/project/progress/addfilemeet/"+UrlNumber.one, OperType.ADD.getOperationType(), "会议记录",SopStage.INNER_REVIEW_SCHEDULE.getStageName()),
	ADD_MEETING_ONE("/galaxy/project/progress/addmeet/"+UrlNumber.one, OperType.ADD.getOperationType(), "会议记录",SopStage.INNER_REVIEW_SCHEDULE.getStageName()), 

	//点击‘申请CEO评审排期’
	SUBMIT_CEO_SCHEDULE("/galaxy/progress/stageChange/"+UrlNumber.two, OperType.SUBMIT.getOperationType(), "CEO评审排期",SopStage.INNER_REVIEW_SCHEDULE.getStageName()), 
		
	
	//CEO评审阶段
	//添加会议记录的弹出框中点击‘保存’。
	CEO_REVIEW_SCHEDULE("/galaxy/project/stageChange/"+UrlNumber.three, OperType.ADD.getOperationType(), "会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	ADD_MEETING_FILE_TWO("/galaxy/project/progress/addfilemeet/"+UrlNumber.two, OperType.ADD.getOperationType(), "会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()),
	ADD_MEETING_TWO("/galaxy/project/progress/addmeet/"+UrlNumber.two, OperType.ADD.getOperationType(), "会议记录",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 

	//点击申请立项会排期
	ADD_PROJECT_SCHEDULE("/galaxy/progress/stageChange/"+UrlNumber.three, OperType.SUBMIT.getOperationType(), "立项会排期",SopStage.CEO_REVIEW_SCHEDULE.getStageName()), 
	
	//立项会阶段
	//添加会议记录的弹出框中点击‘保存’。
	ADD_MEETING_RECORD("/galaxy/project/stageChange/"+UrlNumber.four, OperType.ADD.getOperationType(), "会议记录",SopStage.PROJECT_SCHEDULE.getStageName()), 
	ADD_MEETING_FILE_THREE("/galaxy/project/progress/addfilemeet/"+UrlNumber.three, OperType.ADD.getOperationType(), "会议记录",SopStage.PROJECT_SCHEDULE.getStageName()),
	ADD_MEETING_THREE("/galaxy/project/progress/addmeet/"+UrlNumber.three, OperType.ADD.getOperationType(), "会议记录",SopStage.PROJECT_SCHEDULE.getStageName()), 

	//点击申请立项会排期
	//APPALY_PROJECT_SCHEDULE("", OperType.SUBMIT.getOperationType(), "立项会排期",SopStage.PROJECT_SCHEDULE.getStageName()), 
	
	//进入会后商务谈判
	TO_SWTP("/galaxy/progress/stageChange/"+UrlNumber.four, OperType.SUBMIT.getOperationType(), "会后商务谈判",SopStage.PROJECT_SCHEDULE.getStageName()), 
	
	//投资意向书阶段
	//下载投资意向书模版	
	DOWNLOAD_INVESTMENT_TEMPLATE("/galaxy/template/download/"+UrlNumber.five, OperType.DOWNLOAD.getOperationType(), "投资意向书模版",SopStage.INVESTMENT_INTENT.getStageName()),
	//上传投资意向书
	UPLOAD_INVESTMENT_TEMPLATE("/galaxy/project/stageChange/"+UrlNumber.five, OperType.UPLOAD.getOperationType(), "投资意向书",SopStage.INVESTMENT_INTENT.getStageName()),
	//更新投资意向书
	UPDATE_INVESTMENT_TEMPLATE("", OperType.UPDATE.getOperationType(), "投资意向书",SopStage.INVESTMENT_INTENT.getStageName()),
	//上传签署证明	
	SIGNED_CERTIFICATE("/galaxy/project/stageChange/"+UrlNumber.six, OperType.UPLOAD.getOperationType(), "签署证明",SopStage.INVESTMENT_INTENT.getStageName()),

	//尽职调查阶段
	//上传业务尽职调查报告	  
	UPLOAD_DUE_DILIGENCE_INVESTIGATION("/galaxy/project/stageChange/"+UrlNumber.seven, OperType.UPLOAD.getOperationType(), "业务尽职调查报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//点击申请投决会排期	
	APPLY_VOTE_SCHEDULE("/galaxy/progress/stageChange/"+UrlNumber.nine, OperType.SUBMIT.getOperationType(), "投决会排期",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//尽职调查报告催办
	REMIND_VOTE_SCHEDULE("", OperType.REMINDER.getOperationType(), "法务尽职调查报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	
	//投决会阶段
	//添加会议纪要的弹出框中点击“保存”
	ADD_VOTE_DECISION_MEETING_NOTE("/galaxy/project/stageChange/"+UrlNumber.eight, OperType.ADD.getOperationType(), "会议记录",SopStage.VOTE_DECISION_MEETING.getStageName()),
	ADD_MEETING_FILE_FOUR("/galaxy/project/progress/addfilemeet/"+UrlNumber.four, OperType.ADD.getOperationType(), "会议记录",SopStage.VOTE_DECISION_MEETING.getStageName()),
	ADD_MEETING_FOUR("/galaxy/project/progress/addmeet/"+UrlNumber.four, OperType.ADD.getOperationType(), "会议记录",SopStage.VOTE_DECISION_MEETING.getStageName()), 
	//APPLY_VOTE_DECISION_MEETING_SCHEDULE("", OperType.SUBMIT.getOperationType(), "投决会排期",SopStage.VOTE_DECISION_MEETING.getStageName()),
	
	//投资协议阶段
	//下载投资协议模版	
	DOWNLOAD_INVESTMENT_AGREEMENT_TEMPLATE("/galaxy/template/download/"+UrlNumber.eight, OperType.DOWNLOAD.getOperationType(), "投资协议模版",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//投资协议上传
	INVESTMENT_AGREEMENT_LOOK("/galaxy/project/stageChange/"+UrlNumber.nine, OperType.UPLOAD.getOperationType(), "投资协议",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//投资协议更新
	UPDATE_INVESTMENT_AGREEMENT("", OperType.UPDATE.getOperationType(), "投资协议",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//股权转让协议上传
	UPLOAD_EQUITY_TRANSFER_AGREEMENT("/galaxy/project/stageChange/"+UrlNumber.ten, OperType.UPLOAD.getOperationType(), "股权转让协议",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//股权转让协议更新
	UPDATE_EQUITY_TRANSFER_AGREEMENT("", OperType.UPDATE.getOperationType(), "股权转让协议",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//股权转让协议签署凭证上传
	UPLOAD_EQUITY_TRANSFER_AGREEMENT_SIGNED_CERTIFICATE("/galaxy/project/stageChange/"+UrlNumber.eleven, OperType.UPLOAD.getOperationType(), "股权转让协议签署凭证",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//股权转让协议签署凭证更新
	UPDATE_EQUITY_TRANSFER_AGREEMENT_SIGNED_CERTIFICATE("", OperType.UPDATE.getOperationType(), "股权转让协议签署凭证",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//投资协议签署凭证上传
	UPLOAD_EQUITY_INVESTMENT_SIGNED_CERTIFICATE("/galaxy/project/stageChange/"+UrlNumber.twelve, OperType.UPLOAD.getOperationType(), "投资协议签署凭证",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//投资协议签署凭证更新
	UPDATE_EQUITY_INVESTMENT_SIGNED_CERTIFICATE("", OperType.UPDATE.getOperationType(), "投资协议签署凭证",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	
	//股权交割阶段
	//点击资金拨付凭证催办列里的“催办”
	REMIND_FUNDS_SEND_CERTIFICATE("", OperType.REMINDER.getOperationType(), "资金拨付凭证",SopStage.EQUITY_DELIVERY_STAGE.getStageName()),
	//点击工商变更登记凭证催办列里的“催办”
	REMIND_COMPANY_CHANGE_CERTIFICATE("", OperType.REMINDER.getOperationType(), "工商变更登记凭证",SopStage.EQUITY_DELIVERY_STAGE.getStageName()),
	
	//否决项目日志
	BREAK_PROJECT("/galaxy/progress/reject", OperType.BREAK.getOperationType(), null,"否决项目"),
	
	 /**
     * 人，法，财操作项目日志添加开始
     * 
     */	
	//认领任务-人事完善简历
	UPLOAD_EQUITY_CLAIMT_SOPTASK_RS_JL("/galaxy/soptask/goClaimtcPage/"+UrlNumber.one, OperType.CLAIMT.getOperationType(), Target.DUE_DILIGENCE_PERFECTED_PINFO.getTargetName(),SopStage.TOUCH_INTERVIEW.getStageName()),
	//认领任务-人事尽职调查
	UPLOAD_EQUITY_CLAIMT_SOPTASK_RS_JD("/galaxy/soptask/goClaimtcPage/"+UrlNumber.two, OperType.CLAIMT.getOperationType(), Target.DUE_DILIGENCE_RS_JD.getTargetName(),SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//认领任务-财务尽职调查
	UPLOAD_EQUITY_CLAIMT_SOPTASK_CW_JD("/galaxy/soptask/goClaimtcPage/"+UrlNumber.three, OperType.CLAIMT.getOperationType(), Target.DUE_DILIGENCE_CW_JD.getTargetName(),SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//认领任务-财务付款凭证
	UPLOAD_EQUITY_CLAIMT_SOPTASK_CW_FKPZ("/galaxy/soptask/goClaimtcPage/"+UrlNumber.four, OperType.CLAIMT.getOperationType(), Target.DUE_DILIGENCE_CW_FKPZ.getTargetName(),SopStage.EQUITY_DELIVERY_STAGE.getStageName()),
	//认领任务-法务尽职调查
	UPLOAD_EQUITY_CLAIMT_SOPTASK_FW_JD("/galaxy/soptask/goClaimtcPage/"+UrlNumber.five, OperType.CLAIMT.getOperationType(), Target.DUE_DILIGENCE_FW_JD.getTargetName(),SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//认领任务-法务工商变更
	UPLOAD_EQUITY_CLAIMT_SOPTASK_FW_GSBG("/galaxy/soptask/goClaimtcPage/"+UrlNumber.six, OperType.CLAIMT.getOperationType(), Target.DUE_DILIGENCE_FW_GSBG.getTargetName(),SopStage.EQUITY_DELIVERY_STAGE.getStageName()),
	
	/**上传尽调报告**/
	UPLOAD_JD("/galaxy/taskprocess/uploadFile/"+UrlNumber.one, OperType.UPLOAD.getOperationType(), Target.DUE_DILIGENCE_RS_JD.getTargetName(), SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	/**更新尽调报告**/
	UPDATE_JD("/galaxy/taskprocess/uploadFile/"+UrlNumber.two, OperType.UPDATE.getOperationType(), Target.DUE_DILIGENCE_RS_JD.getTargetName(), SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	
	
	//完成任务-业务尽调
	UPLOAD_EQUITY_SUBMIT_SOPTASK_YW("/galaxy/soptask/submitTask/"+UrlNumber.seven, OperType.FINISH.getOperationType(), Target.DUE_DILIGENCE_YW.getTargetName(),SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
    //完成任务-人事完善简历
	UPLOAD_EQUITY_SUBMIT_SOPTASK_RS_JL("/galaxy/soptask/updateTaskStatus/"+UrlNumber.one, OperType.FINISH.getOperationType(), Target.DUE_DILIGENCE_PERFECTED_PINFO.getTargetName(),SopStage.TOUCH_INTERVIEW.getStageName()),
	//完成任务-人事尽职调查
	UPLOAD_EQUITY_SUBMIT_SOPTASK_RS_JD("/galaxy/soptask/submitTask/"+UrlNumber.two, OperType.FINISH.getOperationType(), Target.DUE_DILIGENCE_RS_JD.getTargetName(),SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//完成任务-财务尽职调查
	UPLOAD_EQUITY_SUBMIT_SOPTASK_CW_JD("/galaxy/soptask/submitTask/"+UrlNumber.three, OperType.FINISH.getOperationType(), Target.DUE_DILIGENCE_CW_JD.getTargetName(),SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//完成任务-财务付款凭证
	UPLOAD_EQUITY_SUBMIT_SOPTASK_CW_FKPZ("/galaxy/soptask/submitTask/"+UrlNumber.four, OperType.FINISH.getOperationType(), Target.DUE_DILIGENCE_CW_FKPZ.getTargetName(),SopStage.EQUITY_DELIVERY_STAGE.getStageName()),
	//完成任务-法务尽职调查
	UPLOAD_EQUITY_SUBMIT_SOPTASK_FW_JD("/galaxy/soptask/submitTask/"+UrlNumber.five, OperType.FINISH.getOperationType(), Target.DUE_DILIGENCE_FW_JD.getTargetName(),SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	//完成任务-法务工商变更
	UPLOAD_EQUITY_SUBMIT_SOPTASK_FW_GSBG("/galaxy/soptask/submitTask/"+UrlNumber.six, OperType.FINISH.getOperationType(), Target.DUE_DILIGENCE_FW_GSBG.getTargetName(),SopStage.EQUITY_DELIVERY_STAGE.getStageName()),
	/**
    * 人，法，财操作项目日志添加结束
    * 
    */
	/**
	 * 移交任务
	 */
	TRANSFER_TASK("/galaxy/soptask/transfer", OperType.APPLY.getOperationType(), Target.DUE_TBD.getTargetName(),SopStage.TBD.getStageName()),
	/**
	 * 放弃任务
	 */
	GIVE_UP_TASK("/galaxy/soptask/giveup", OperType.GIVEUP.getOperationType(), Target.DUE_TBD.getTargetName(),SopStage.TBD.getStageName()),
	/**
	 * 指派任务
	 */
	ASSIGN_TASK("/galaxy/soptask/assign", OperType.ASSIGN.getOperationType(), Target.DUE_TBD.getTargetName(),SopStage.TBD.getStageName()),
	
	//更新投资意向书 1
	UPDATE_INVESTMENT_INTENT_FILE("/galaxy/sopFile/commonUploadFile/"+UrlNumber.one, OperType.UPDATE.getOperationType() , "投资意向书",SopStage.INVESTMENT_INTENT.getStageName()),
	
	
	
	//总注资计划
	_14_1_1	    ("/galaxy/grant/total/addGrantTotal/"+UrlNumber.one,	 OperType.ADD.getOperationType() ,	    "总注资计划",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	_14_1_2	    ("/galaxy/grant/total/addGrantTotal/"+UrlNumber.two,	 "编辑" ,							    "总注资计划",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	_14_1_2_1	("/galaxy/grant/total/resetGrantTotal/"+UrlNumber.two,	 "编辑" ,								"总注资计划",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	_14_1_3_1   ("/galaxy/grant/total/deleteGrantTotal/"+UrlNumber.three,OperType.DELETE.getOperationType() ,	"总注资计划",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	
	//分期注资计划
	_14_2_1	    ("/galaxy/grant/part/addGrantPart/"+UrlNumber.one,		OperType.ADD.getOperationType() ,	    "分期注资计划",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	_14_2_2	    ("/galaxy/grant/part/addGrantPart/"+UrlNumber.two,		"编辑",									"分期注资计划",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	_14_2_3	    ("/galaxy/grant/part/delGrantPart/"+UrlNumber.three,	OperType.DELETE.getOperationType() ,	"分期注资计划",	SopStage.EQUITY_DELIVERY_END.getStageName()),

	//实际注资信息
	_14_3_1	    ("/galaxy/grant/actual/saveApprActual/"+UrlNumber.one,		OperType.ADD.getOperationType() ,	    "实际注资信息",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	_14_3_2	    ("/galaxy/grant/actual/saveApprActual/"+UrlNumber.two,		"编辑",									"实际注资信息",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	_14_3_3     ("/galaxy/grant/actual/deleteApprActual/"+UrlNumber.three,	OperType.DELETE.getOperationType() ,	"实际注资信息",	SopStage.EQUITY_DELIVERY_END.getStageName()),
	
	/*项目操作日志的配置------------------------------------结束----------------------------------------------------------*/
	
	
	//------------------------------------------------忧愁的分割线-----------------------------------------------------//
	
	
	/*创意操作日志的配置-------------------开始---------------------------------------------------------------------------*/
	
	//添加可信性报告
	CY_ADD_REPORT("/galaxy/idea/ideaUpReport/"+UrlNumber.two, OperType.ADD.getOperationType() , "可行性报告",SopStage.CY_DY.getStageName()),
	//更新
	CY_UPDATE_REPORT("/galaxy/idea/ideaUpReport/"+UrlNumber.one, OperType.UPDATE.getOperationType() , "可行性报告",SopStage.CY_DY.getStageName()),
	//启动立项会
	CY_START_LXH("/galaxy/idea/ideaStartMeet", OperType.SUBMIT.getOperationType() , "启动立项会",SopStage.CY_DY.getStageName()),
	
	
	//添加会议记录
	CY_ADD_MEET("/galaxy/idea/saveCyMeetRecord", OperType.ADD.getOperationType() , "会议记录",SopStage.CY_LXH.getStageName()),
	//创建项目
	CY_ADD_PRO("/galaxy/idea/createProject", OperType.ADD.getOperationType() , "项目",SopStage.CY_PRO.getStageName()),
	//编辑项目名称
	CY_EDIT_PRO("/galaxy/idea/editProjectName", OperType.UPDATE.getOperationType() , "项目",SopStage.CY_PRO.getStageName()),

	//认领创意
	CY_CLAIMT_IDEA("/galaxy/idea/updateIdea", OperType.UPDATE.getOperationType() , "创意",SopStage.CY_DY.getStageName()),	
	//添加创意
	CY_ADD_IDEA("/galaxy/idea/addIdea/"+UrlNumber.one, OperType.ADD.getOperationType() , "创意",SopStage.CY_INFO.getStageName()),
	//待认领阶段编辑创意
	CY_EDIT_DYL_IDEA("/galaxy/idea/addIdea/"+UrlNumber.two, OperType.UPDATE.getOperationType(), "创意",SopStage.CY_DRL.getStageName()),
	//调研阶段编辑创意
	CY_EDIT_DY_IDEA("/galaxy/idea/addIdea/"+UrlNumber.three, OperType.UPDATE.getOperationType(), "创意",SopStage.CY_DY.getStageName()),
	//创建立项会阶段编辑创意
	CY_EDIT_LXH_IDEA("/galaxy/idea/addIdea/"+UrlNumber.four, OperType.UPDATE.getOperationType(), "创意",SopStage.CY_LXH.getStageName()),
	//放弃创意
	CY_EDIT_FQ_IDEA("/galaxy/idea/giveUp", OperType.UPDATE.getOperationType(), "创意",SopStage.CY_DRL.getStageName()),
	//搁置阶段编辑创意
	CY_EDIT_GZ_IDEA("/galaxy/idea/addIdea/"+UrlNumber.five, OperType.UPDATE.getOperationType(), "创意",SopStage.CY_GZ.getStageName()),
	
	LX_REPORT_UPLOAD("/galaxy/sopFile/upload/"+UrlNumber.one, OperType.UPLOAD.getOperationType(), "项目立项报告",SopStage.PROJECT_SCHEDULE.getStageName()),
	LX_REPORT_UPDATE("/galaxy/sopFile/upload/"+UrlNumber.two, OperType.UPDATE.getOperationType(), "项目立项报告",SopStage.PROJECT_SCHEDULE.getStageName()),
	
	TRANSFER_PROJECT("/galaxy/projectTransfer/applyTransfer/"+UrlNumber.one,OperType.APPLY.getOperationType(), "移交项目","移交项目"),
	ASSIGN_PROJECT("/galaxy/projectTransfer/applyTransfer/"+UrlNumber.two,OperType.ASSIGN.getOperationType(), "指派项目","指派项目"),
	DELETE_PROJECT("/galaxy/project/deletePro",OperType.DELETE.getOperationType(), "删除项目","删除项目"),
	
	/**上传尽调启动报告**/
	JDQD_REPORT_UPLOAD("/galaxy/sopFile/upload/"+UrlNumber.three, OperType.UPLOAD.getOperationType(), "尽职调查启动会报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	/**更新尽调启动报告**/
	JDQD_REPORT_UPDATE("/galaxy/sopFile/upload/"+UrlNumber.four, OperType.UPDATE.getOperationType(), "尽职调查启动会报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	/**上传尽调总结报告**/
	JDZJ_REPORT_UPLOAD("/galaxy/sopFile/upload/"+UrlNumber.five, OperType.UPLOAD.getOperationType(), "尽职调查总结会报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),
	/**更新尽调总结报告**/
	JDZJREPORT_UPDATE("/galaxy/sopFile/upload/"+UrlNumber.six, OperType.UPDATE.getOperationType(), "尽职调查总结会报告",SopStage.DUE_DILIGENCE_INVESTIGATION.getStageName()),

	_19_1("/galaxy/operationalData/formAddOperationalData/"+UrlNumber.one, OperType.ADD.getOperationType(), "运营数据","运营记录"),
	_19_2("/galaxy/operationalData/formAddOperationalData/"+UrlNumber.two, "编辑", "运营数据","运营记录"),
	_19_3("/galaxy/operationalData/delOperationalData", OperType.DELETE.getOperationType(), "运营数据","运营记录"),
	
	//签订投资意向书（投资）
	TO_TZ("/galaxy/progress/stageChange/"+UrlNumber.five, OperType.SUBMIT.getOperationType(), "签订投资意向书（投资）",SopStage.SWTP.getStageName()),	
	//签订投资协议书（闪投）
	TO_ST("/galaxy/progress/stageChange/"+UrlNumber.six, OperType.SUBMIT.getOperationType(), "签订投资协议书（闪投）",SopStage.SWTP.getStageName()),	
	//签订投资意向书（投资）-> 尽职调查
	TZ2JADC("/galaxy/progress/stageChange/"+UrlNumber.seven, OperType.SUBMIT.getOperationType(), "尽职调查",SopStage.INVESTMENT_INTENT.getStageName()),	
	//签订投资协议书（闪投）-> 尽职调查
	ST2JADC("/galaxy/progress/stageChange/"+UrlNumber.eight, OperType.SUBMIT.getOperationType(), "尽职调查",SopStage.INVESTMENT_AGREEMENT.getStageName()),	
	//投决会->投资协议
	TJH2JADC("/galaxy/progress/stageChange/"+UrlNumber.ten, OperType.SUBMIT.getOperationType(), "签订投资协议书",SopStage.VOTE_DECISION_MEETING.getStageName()),
	//投资协议 -> 股权交割
	TZXY2GQJG("/galaxy/progress/stageChange/"+UrlNumber.eleven, OperType.SUBMIT.getOperationType(), "股权交割",SopStage.INVESTMENT_AGREEMENT.getStageName()),
	//投决会 -> 股权交割 
	TJH2GQJG("/galaxy/progress/stageChange/"+UrlNumber.twelve, OperType.SUBMIT.getOperationType(), "股权交割",SopStage.VOTE_DECISION_MEETING.getStageName()),
	//投后运营
	THYY("/galaxy/progress/stageChange/"+UrlNumber.thirteen, OperType.SUBMIT.getOperationType(), "投后运营",SopStage.EQUITY_DELIVERY_STAGE.getStageName()),
	;


	/*创意操作日志的配置-------------------结束---------------------------------------------------------------------------*/
		
	private OperationLogType(String uniqueKey, String type, String content, String sopstage) {
		this.uniqueKey = uniqueKey;
		this.type = type;
		this.content = content;
		this.sopstage = sopstage;
	}

	public static OperationLogType getObject(String uniqueKey) {
		OperationLogType[] types = OperationLogType.values();
		OperationLogType result = null;
		for (OperationLogType type : types) {
			if (type.getUniqueKey()!=null && type.getUniqueKey().trim().length()>0){
					
				String requestNum = uniqueKey.substring(uniqueKey.lastIndexOf("/"));
				String localNum = type.getUniqueKey().substring(type.getUniqueKey().lastIndexOf("/"));
				
				if(requestNum.equals(localNum)){
					if ( uniqueKey.substring(0,uniqueKey.lastIndexOf("/")).contains(type.getUniqueKey().substring(0,type.getUniqueKey().lastIndexOf("/")))) {
						result = type;
						break;
					}
				}else if ( uniqueKey.contains(type.getUniqueKey())) {
					result = type;
					break;
				}
			}
		}
		return result;
	}

	private String uniqueKey;
	private String type;//操作类型
	private String content;//内容
	private String sopstage;//sop阶段

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public String getSopstage() {
		return sopstage;
	}

}
