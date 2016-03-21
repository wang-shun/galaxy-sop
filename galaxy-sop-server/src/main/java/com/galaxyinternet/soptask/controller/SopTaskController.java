package com.galaxyinternet.soptask.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.galaxyinternet.bo.SopTaskBo;
import com.galaxyinternet.common.constants.SopConstant;
import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.common.dictEnum.DictEnum;
import com.galaxyinternet.common.utils.ControllerUtils;
import com.galaxyinternet.exception.PlatformException;
import com.galaxyinternet.framework.core.constants.Constants;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.framework.core.utils.ExceptionMessage;
import com.galaxyinternet.framework.core.utils.StringEx;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.project.Project;
import com.galaxyinternet.model.soptask.SopTask;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.DepartmentService;
import com.galaxyinternet.service.ProjectService;
import com.galaxyinternet.service.SopTaskService;

@Controller
@RequestMapping("/galaxy/soptask")
public class SopTaskController extends BaseControllerImpl<SopTask, SopTaskBo> {
	final Logger logger = LoggerFactory.getLogger(SopTaskController.class);
	
	@Autowired
	private SopTaskService sopTaskService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	com.galaxyinternet.framework.cache.Cache cache;
	
	@Autowired
	SopTaskProcessController taskProcessController;

	@Override
	protected BaseService<SopTask> getBaseService() {
		return this.sopTaskService;
	}
	
	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request) {
		//flag判断该请求是不是人事，财务，法务角色登录的尽调报告或者完善简历菜单
		String flag=request.getParameter("flag");
		if(null!=flag&&!"".equals(flag)){
			request.setAttribute("flagUrl",flag);
		}else{
			request.setAttribute("flagUrl","");
		}
		return "soptask/tasklist";
	}
	/**
	 * 弹出页面
	 */
	 @com.galaxyinternet.common.annotation.Logger
	@RequestMapping(value = "/goClaimtcPage",method = RequestMethod.GET)
	public String goClaimtcPage(HttpServletRequest request) {

		//当前登录人
		User user = (User) request.getSession().getAttribute(
				Constants.SESSION_USER_KEY);
		SopTask sopTask=new SopTask();
		Result result = new Result();
		String id=request.getParameter("id");
		if(id!=null&&!"".equals(id)){
			sopTask.setId(Long.parseLong(id));
		}
		sopTask.setTaskStatus(DictEnum.taskStatus.待完工.getCode());
	
		try {
			SopTask queryById = sopTaskService.queryById(Long.parseLong(id));
			Project project = projectService.queryById(queryById.getProjectId());
			sopTask.setAssignUid(user.getId());
			 sopTaskService.updateById(sopTask);
			 request.setAttribute("taskid", id);	
			 ControllerUtils.setRequestParamsForMessageTip(request, project.getProjectName(), project.getId());
		} catch (PlatformException e) {
			result.addError(e.getMessage());
		} catch (Exception e) {
			result.addError("生成任务失败");
			logger.error("生成任务失败", e);
		}
		return "soptask/claimtc";
	}
	/**
	 * 弹出页面
	 */
	@RequestMapping(value = "/doTask",method = RequestMethod.GET)
	public ModelAndView doTask(Long taskId,HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("/taskProcess/task_info");
		try {
			SopTask task = sopTaskService.queryById(taskId);
			mv.addObject("taskId", taskId);
			mv.addObject("projectId", task.getProjectId());
			mv.addObject("taskFlag", task.getTaskFlag());
		} catch (Exception e) {
			throw new PlatformException(ExceptionMessage.QUERY_LIST_FAIL.getMessage(),e);
		}
		return mv;
	}
	

	/**
	 * @category 根据角色获取当前登录人所属角色的所有任务
	 * @author chenjianmei
	 * @serialData 2016-02-26
	 * @param pageable
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/taskListByRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<SopTaskBo> taskListByRole(@RequestBody SopTaskBo sopTaskBo,HttpServletRequest request) {
		ResponseData<SopTaskBo> responseBody = new ResponseData<SopTaskBo>();
		//SopTaskBo sopTaskBo=new SopTaskBo();
		//当前登录人
		User user = (User) request.getSession().getAttribute(
				Constants.SESSION_USER_KEY);
		sopTaskBo.setAssignUid(user.getId());
		if(null==sopTaskBo.getFlagUrl()){
			sopTaskBo.setFlagUrl("");
		}
     	//根据当前登录认查询部门
		Department Department=new Department();//
		Department.setId(user.getDepartmentId());
		Department queryOne = departmentService.queryOne(Department);
		if(!StringEx.isNullOrEmpty(queryOne)){
			sopTaskBo.setDepartmentId(queryOne.getId());
		}
		Result result = new Result();
		try {
			Page<SopTaskBo> list = sopTaskService.tasklist(new PageRequest(sopTaskBo.getPageNum(),sopTaskBo.getPageSize()), sopTaskBo,request);
			if(null==list.getContent()){
				List<SopTaskBo> SopTaskBoList = new ArrayList<SopTaskBo>();
				list.setTotal((long)0);
				list.setContent(SopTaskBoList);
			}
			responseBody.setPageList(list);	
			result.setStatus(Status.OK);
		} catch (PlatformException e) {
			result.addError(e.getMessage());
		} catch (Exception e) {
			result.addError("任务列表查询失败");
			logger.error("任务列表查询失败", e);
		}
		responseBody.setResult(result);
		return responseBody;
	}

	/**
	 * @category 根据任务id修改任务状态 1，待认领 2，待处理 3，已完成
	 * @author chenjianmei
	 * @serialData 2016-02-26
	 * @param pageable
	 * @return @PathVariable("taskId") String taskId
	 */
	@ResponseBody
	@RequestMapping(value = "/insertTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<SopTask> insertTask(@RequestBody SopTask entity, HttpServletRequest request) {
		ResponseData<SopTask> responseBody = new ResponseData<SopTask>();
		Result result = new Result();
		Object ob = request.getSession().getAttribute("sessionUser");
		if (ob == null) {
			responseBody.setResult(new Result(Status.ERROR, "no login status."));
			return responseBody;
		}
		try {
			Long id = sopTaskService.insertsopTask(entity);
			responseBody.setId(id);
			result.setStatus(Status.OK);
		} catch (PlatformException e) {
			result.addError(e.getMessage());
		} catch (Exception e) {
			result.addError("生成任务失败");
			logger.error("生成任务失败", e);
		}
		responseBody.setResult(result);
		return responseBody;

	}

	/**
	 * @category 根据任务id修改任务状态 1，待认领 2，待处理 3，已完成
	 * @author chenjianmei
	 * @serialData 2016-02-26
	 * @param pageable
	 * @return
	 *@PathVariable("taskId") String taskId
	 */
	@com.galaxyinternet.common.annotation.Logger
	@ResponseBody
	@RequestMapping(value = "/updateTaskStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<SopTask> updateTaskStatus( @RequestBody SopTask entity,HttpServletRequest request) {
		//当前登录人
				User user = (User) request.getSession().getAttribute(
						Constants.SESSION_USER_KEY);
				entity.setAssignUid(user.getId());
		ResponseData<SopTask> responseBody = new ResponseData<SopTask>();
		Result result = new Result();
		try {
		    sopTaskService.updateById(entity);
		     result.setStatus(Status.OK);
		     Project project = projectService.queryById(entity.getProjectId());
			ControllerUtils.setRequestParamsForMessageTip(request, project.getProjectName(), project.getId());
		} catch (PlatformException e) {
			result.addError(e.getMessage());
		} catch (Exception e) {
			result.addError("修改任务状态失败");
			logger.error("修改任务状态失败", e);
		}
		responseBody.setEntity(entity);
		responseBody.setResult(result);
		return responseBody;
	}

	/**
	 * @category 处理任务，人，财务，法务查询项目详情
	 * @author chenjianmei
	 * @serialData 2016-02-26
	 * @param pageable
	 * @return
	 *@PathVariable("taskId") String taskId
	 */
	@ResponseBody
	@RequestMapping(value = "/selectProjectByTaskId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<Project> selectProjectByTaskId( @PathVariable Long projectid,HttpServletRequest request) {
		ResponseData<Project> responseBody = new ResponseData<Project>();
		Result result = new Result();
	    Project queryProjectById = projectService.queryById(projectid);
	   //项目创建者用户ID与当前登录人ID是否一样
		if(queryProjectById == null ){
			responseBody.setResult(new Result(Status.ERROR, "获取项目详情失败!"));
			return responseBody;
		}
	    result.setStatus(Status.OK);
		responseBody.setResult(result);
		responseBody.setEntity(queryProjectById);
		return responseBody;
	}
	
	
	/**
	 * 当前待办总数
	 * @author zhaoying
	 * @param sopTaskBo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/totalMission",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> totalMission(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SopTaskBo sopTaskBo  = new SopTaskBo();
		//当前登录人
		User user = (User) request.getSession().getAttribute(
				Constants.SESSION_USER_KEY);
		sopTaskBo.setAssignUid(user.getId());
		sopTaskBo.setTaskStatus(SopConstant.TASK_MISSION_STATUS);
		Long total = 0l;
		try {
			total = sopTaskService.selectTotalMission(sopTaskBo);
			map.put("status", Status.OK);
			map.put("total",total);
		} catch (PlatformException e) {
			map.put("message","查询失败");
		} catch (Exception e) {
			map.put("message","查询总数失败");
			logger.error("查询待办任务失败", e);
		}
	
		return map;
	}
	
	/**
	 * 紧急总数
	 * @author zhaoying
	 * @param sopTaskBo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/totalUrgent", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> totalUrgent(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SopTaskBo sopTaskBo  = new SopTaskBo();
		//当前登录人
		User user = (User) request.getSession().getAttribute(
				Constants.SESSION_USER_KEY);
		sopTaskBo.setAssignUid(user.getId());
		sopTaskBo.setTaskOrder(SopConstant.TASK_URGENT_STATUS);
		Long total = 0l;
		try {
			total = sopTaskService.selectTotalUrgent(sopTaskBo);
			map.put("status", Status.OK);
			map.put("total",total);
		} catch (PlatformException e) {
			map.put("message","查询失败");
		} catch (Exception e) {
			map.put("message","查询总数失败");
			logger.error("查询紧急任务失败", e);
		}
	
		return map;
	}
	
}
