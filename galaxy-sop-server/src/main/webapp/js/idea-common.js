	function dateFormatter(val,row,index)
	{
		if(!isNaN(val))
		{
			return Number(val).toDate().format("yyyy-MM-dd");
		}
		return val;
	}
	function datetimeFormatter2(val,row,index)
	{	
		if(row.updatedTime != null){
			if(!isNaN(row.updatedTime)){
				return Number(row.updatedTime).toDate().format("yyyy-MM-dd hh:mm:ss");
			}
		}else if(row.createdTime != null){
			if(!isNaN(row.createdTime)){
				return Number(row.createdTime).toDate().format("yyyy-MM-dd hh:mm:ss");
			}
		}
		return val;
	}
	function datetimeFormatter(val,row,index)
	{
		if(!isNaN(val))
		{
			return Number(val).toDate().format("yyyy-MM-dd HH:mm:ss");
		}
		return val;
	}
	function progressFormatter(val,row,index)
	{
		if(val != null)
		{
			return $('[name="ideaProgress"] option[value="'+val+'"]').text();
		}
	}
	function ideaNameLinkFormatter(val,row,index)
	{
		return '<a href="#" class="blue" onclick="showIdeaDetail(\'' + row.id + '\')">'+val+'</a>';
	}
	
	
	function refreshIdeaList()
	{
		$("#data-table").bootstrapTable('refresh');
	}
	function getDepartment($depField)
	{
		sendGetRequest(
				platformUrl.getIdeaDepartment,
				null,
				function(data){
					if(data.result.status = 'OK')
					{
						$depField.empty();
						if(data.entityList.length >1)
						{
							$depField.append('<option value="">全部</option>');
						}
						$.each(data.entityList,function(){
							$depField.append('<option value="'+this.id+'">'+this.name+'</option>');
						});
					}
				}
		);
	}
	//基本信息  -- 数据展示
	function getIdeaInfo(id) {
		var idea = null;
			
		var url = platformUrl.detailIdea + "/"+id;
		sendGetRequest(url, {"id" : id}, 
			function(data){
			if(data.result.status == "Error")
			{
				alert(data.result.message );
				return;
			}
			idea = data.entity;
			stockTransfer = idea.stockTransfer;
			if(null!=idea.claimantUid&&""!=idea.claimantUid){
				$("[data-btn='claim']").hide();
			}
			$("#IdeaId").val(idea.id);
			$("#ideaDetail dd")
			.each(function(){
				var self = $(this);
				if(self.attr('id') != 'undefined')
				{
					var id = self.attr('id');
					var formatter = self.data('formatter');
					var text = idea[id]
					if($.isFunction(window[formatter]))
					{
						text = window[formatter].call(window,text);
					}
					self.text(text);
				}
				
			});
		});
		return idea;
	}
	
	
	/*	*//**创意阶段 - 待认领**//*
	public static final String IDEA_PROGRESS_DRL 			= "ideaProgress:1";
	*//**创意阶段 - 调研**//*
	public static final String IDEA_PROGRESS_DY 			= "ideaProgress:2";
	*//**创意阶段 - 创建立项会**//*
	public static final String IDEA_PROGRESS_CJLXH 			= "ideaProgress:3";
	*//**创意阶段 - 搁置**//*
	public static final String IDEA_PROGRESS_GZ				= "ideaProgress:4";
	*//**创意阶段 - 创建项目**//*
	public static final String IDEA_PROGRESS_CJXM 			= "ideaProgress:5";
*/
	//stage 弹出
	//
	var ideaInfo;
	function showIdeaDetail(ideaId)
	{
		var layerIndex = layer.load(2);
		var _url = platformUrl.ideaGoStage+"?id="+ideaId;
		$.getHtml({
			url:_url,//模版请求地址
			data:"",//传递参数
			okback:function(){
				
				$(".creativetc .tabtable").tabchange2();
				
				//基本信息  -- 数据展示  index =1
				ideaInfo = getIdeaInfo(ideaId);
				$(".idea-title").text(ideaInfo.ideaName);
				//解析元素id和项目阶段值，以便之后做控制
				var progress = ideaInfo.ideaProgress;
				if('ideaProgress:4' != progress)
				{
					$("[data-btn='history']").hide();
				}
				progress = progress.replace(":","_");
				var index = progress.substr("ideaProgress_".length);
				
				//按钮 bind 弹窗
				bindTcEvents(index);
				cyToProShow();
				
				for(var i = 1; i<6; i++){
					//当前阶段之后的tab变为不可用
					if(i > index){
						$("#ideaProgress_" + i).addClass("disabled");
					}
					
					//为Tab添加点击事件，用于重新刷新
					$("#ideaProgress_" + i).on("click",function(){
						var id = $(this).attr("id");
						var indexNum = id.substr(id.length-1,1);
						switch(indexNum){
						case '1':
							
							break;
						case '2' : 
							tiggerTable($("#ideaProgress_2_table"),3);
							break;
						case '3':
							
							break;
						case '5':
							
							break;
						default :
							break;
						}
					});
				}	
				
				//默认打开当前阶段 
				//$("#" + progress).addClass("on");
				//$("#" + progress + "_con").css("display","block");
				var id=$("#IdeaId").val();
				$("#editBtn").click(function(){
					sendGetRequest(platformUrl.detailIdea+"/"+id,null,initCallBack.getdetailIdeaInfoCallBack);
				});
				layer.close(layerIndex);
			}//end okback 模版反回成功执行	
			
		});
		return false;

	}
	
	
	//各阶段按钮绑定事件、弹窗
	//由项目当前阶段显示隐藏按钮
	function bindTcEvents(index){
		
		//基本信息 -->编辑
		$("[data-btn='edit']").on("click",function(){
			var $self = $(this);
			var $subling=$self.next().val();
			var _url = $self.attr("href");
			$.getHtml({
				url:_url,//模版请求地址
				data:"",//传递参数
				okback:function(){	
					getIdeaInfoEdit($subling,"edit")
				}//模版反回成功执行	
			});
			return false;
		});
		//基本信息 -->历史信息
		$("[data-btn='history']").on("click",function(){
			var $self = $(this);
			var _url = $self.attr("href");
			$.getHtml({
				url:_url//模版反回成功执行	
			});
			return false;
		});
		//基本信息 -->认领
		$("[data-btn='claim']").on("click",function(){
			var $self = $(this);
			var id=$self.next().next().val();
			var _url = $self.attr("href");
			$.getHtml({
				url:_url,//模版请求地址
				data:"",//传递参数
				okback:function(){
					claimFun(id);
					
				}//模版反回成功执行	
			});
			return false;
		});
		
		
		//调研
		
		//放弃btn
		$("[data-btn='abandon']").on("click",function(){
			var $self = $(this);
			var _url = $self.attr("href");
			$.getHtml({
				url:_url,//模版请求地址
				data:"",//传递参数
				okback:function(){							
				}//模版反回成功执行	
			});
			return false;
		});
		
		
		//创建立项会  --> 添加会员纪要
		$("[data-btn='meeting']").on("click",function(){
			var $self = $(this);
			var _url = $self.attr("href");
			$.getHtml({
				url:_url,//模版请求地址
				data:"",//传递参数
				okback:function(){							
				}//模版反回成功执行	
			});
			return false;
		});
		//创建立项会 --> 创建项目
		$("[data-btn='create']").on("click",function(){
			var $self = $(this);
			var _url = $self.attr("href");
			$.getHtml({
				url:_url,//模版请求地址
				data:{ieadId:ideaInfo.id},//传递参数
				okback:function(){							
				}//模版反回成功执行	
			});
			return false;
		});
		
		
		//创建项目 -->编辑项目名称
		$("[data-btn='edit_name']").on("click",function(){
			var $self = $(this);
			var _url = $self.attr("href");
			$.getHtml({
				url:_url,//模版请求地址
				data:"",//传递参数
				okback:function(){							
				}//模版反回成功执行	
			});
			return false;
		});
		
	}
	
	//创建项目  阶段，点击项目列表 - <a>项目名称</a> 弹出事件
	function cyToProShow(){
		
		//创建项目，点击项目列表 - <a>项目名称</a>
		$('#project_name').click(function(){
			$('.block').css({display: 'none',});;
			$(".aa").show();
			$('.tablink li').eq(0).addClass('on').siblings().removeClass('on');
		});
		$('#project_name').click(function(){
			$(".tabtable_con .block").eq(0).show().siblings().hide();
			$('.tablink li').eq(0).addClass('on').siblings().removeClass('on');
		});
	}
	
	
	
	///======= 调研     =====  ///
	function ideaOperateFormat(val,row,index){
		if(row.fileKey){
			return "<a  href=\"#\" onclick=\"showUploadPopup(\'e\'"+",\'"+row.projectProgress+"\',\'"+row.id+"\');\" class=\"blue\" >更新</a>" ;
		}
		return "-";
	}
	
	function ideaFileDownFormat(val,row,index){
		if(row.fileKey){
			return "<a href=\"#\"  onclick=\"filedown("+row.id+","+row.fileKey+");\" class=\"blue\" >"+row.fileName+"</a>" ;
		}
		return "-";
	}
	
	
	
	
	// mark :u/上传  e/更新    prograss：ideaProgress:2 projectId:ideaid  fileid:
	function showUploadPopup(mark,prograss,fileid){
		$.popup({
			txt:$("#upload-dialog").html(),
			showback:function(){
				var _this = this;
				if(upreport(_this,mark,prograss,fileid)){
					initIdeaUpload(_this);
				}
			}
		});
	}
	
	function upreport(_dialog,mark,prograss,fileid){
		var projectId = $("#ideaProgress_2_con").find("[data-id='ideaId']").val();
		$(_dialog.id).find("input[name='projectId']").val(projectId);
		$(_dialog.id).find("input[name='projectProgress']").val(prograss);
		if(mark == 'e'){
			$(_dialog.id).find("input[name='id']").val(fileid);
			$(_dialog.id).find("input[name='isEdit']").val("edit");
		}
		return true;
	}
	
	function getContion(){
		
	}
	
	function initIdeaUpload(_dialog){
		var ideaUploader = new plupload.Uploader({
			runtimes : 'html5,html4,flash,silverlight',
			browse_button : $(_dialog.id).find("#file-select-btn")[0], 
			url : platformUrl.ideaUpReport,
			multi_selection:false,
			filters : {
				max_file_size : '25mb',
				mime_types: paramsFilter(null)
			},

			init: {
				PostInit: function() {
					$(_dialog.id).find("#upload-btn").click(function(){
						if(ideaUploader.files.length==0){
							layer.msg("请选择文件.");
							return;
						}else{
							ideaUploader.start();
						}
						return false;
					});
				},

				FilesAdded: function(up, files) {
					if(ideaUploader.files.length >= 1){
						ideaUploader.splice(0, ideaUploader.files.length-1);
					} 
					$.each(files, function() {
						$(_dialog.id).find("input[name='fileName']").val(this.name);
						attrFileType($(_dialog.id).find("[name='fileType']"),this);
					});
				},
				BeforeUpload : function(up,file){
					var $form = $(_dialog.id).find("#upload-form");
					var data = JSON.parse($form.serializeObject());
					data['fileType']=$(_dialog.id).find("[name='fileType']").val();
					up.settings.multipart_params = data;
				},
				FileUploaded: function(up, files, rtn) {
					var data = $.parseJSON(rtn.response);
					if(data.result.status == 'OK')
					{
						layer.msg("上传成功.");
						$(_dialog.id).find("[data-close='close']").click();
						$("#ideaProgress_2_table").bootstrapTable('refresh');
					}
					else
					{	
						ideaUploader.splice(0, ideaUploader.files.length);
						$(_dialog.id).find("input[name='fileName']").val("");
						layer.msg(data.result.message);
					}
				},
				Error: function(up, err) {
					layer.msg(err.message);
				}
			}
		});

		ideaUploader.init();
		
	}
	
	
	function stratLxh(ideaId){
		var url = platformUrl.ideaStartMeet + "/"+ideaId;
		sendGetRequest(url, null, function(data){
			if(data.result.status == "ERROR"){
				layer.msg(data.result.message );
				return;
			}else if(data.result.status == "OK"){
				idea = data.entity;
				$("#data_table").bootstrapTable('refresh');
				$("#powindow,#popbg").remove();
				showIdeaDetail(ideaId);
			}
			
		});
		
	}
	
	/**
	 * 
	 * 查询创意详情set值
	 * @param id
	 */
  function getIdeaInfoEdit(id,flag)
  {
   var url = platformUrl.detailIdea+"/"+id;
    sendGetRequest(
	url,
	{"id":"${id}"},
	function(data){
		if(data.result.status == "Error")
		{
			alert(data.result.message );
			return;
		}
		var idea = data.entity;
		stockTransfer = idea.stockTransfer;
		var um = UM.getEditor('edit_idea_desc');
	    getDepartment($("#department"));
		um.setContent(idea.ideaDesc);
	     $("#ideaId").val(idea.id);
		$("#ideaEdit dd").each(function(){
		  var self =$(this).children();
		if(self.attr('id') != 'undefined')
			{
			var formatter = self.data('formatter');
				var id = self.attr('id');
				var text = idea[id];
			   if(id=="department"){
				   if(self.children().val()==idea.departmentId){
					   self.children().attr("selected","selected");
					   $("#depid").val(idea.departmentId);
				   }
				  }else if($.isFunction(window[formatter]))
				{
					text = window[formatter].call(window,text);
					self.val(text);
				}else{
				    self.val(text);
				}
				
			}
			
		});
	}
);
}
  
  /**
   * 更新项目信息
   */
  function update(){
	  var um = UM.getEditor('edit_idea_desc');
		var ideaDesc = um.getContent();
	  var date={
			"id": $("#ideaId").val(),
			"ideaName":$("#ideaName").val(),
			"ideaDesc":ideaDesc,
			"ideaDescHtml":ideaDesc,
			"ideaSource":$("#ideaSource").val(),
			"departmentId":$("#depid").val()
	  };
		if(pid != '' && companyLocation != ''){
			sendPostRequestByJsonObj(platformUrl.ideaUpdateIdea, {"id" : pid, "companyLocation" : companyLocation}, saveSuccess());
		}

  	
  }
  function claimFun(id){
	  if(id != ''){
			sendPostRequestByJsonObj(platformUrl.ideaUpdateIdea, {"id" : pid}, function(){
				
			});
		}

  }
 