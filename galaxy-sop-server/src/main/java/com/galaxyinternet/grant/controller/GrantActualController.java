package com.galaxyinternet.grant.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Sort.Direction;

import com.galaxyinternet.bo.GrantActualBo;
import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.GrantActual;
import com.galaxyinternet.service.GrantActualService;


@Controller
@RequestMapping("/galaxy/grant/actual")
public class GrantActualController extends BaseControllerImpl<GrantActual, GrantActualBo> {
	
	private static final Logger _common_logger_ = LoggerFactory.getLogger(GrantActualController.class);
	
	@Autowired
	private GrantActualService grantActualService;
	
	@Override
	protected BaseService<GrantActual> getBaseService() {
		return this.grantActualService;
	}
	/**
	 * sop tab页面  日志 详情    /galaxy/project/proview/
	 */
	@RequestMapping(value = "/toApprActualPage", method = RequestMethod.GET)
	public String toApprActualPage(HttpServletRequest request) {
				return "project/tanchuan/appr_actual";
	}
	/**
	 * sop tab页面  日志 详情    /galaxy/project/proview/
	 */
	@RequestMapping(value = "/toApprActualLook", method = RequestMethod.GET)
	public String toApprActualLock(HttpServletRequest request) {
				return "project/tanchuan/appr_actual_look";
	}
	/**
	 * sop tab页面  日志 详情    /galaxy/project/proview/
	 */
	@RequestMapping(value = "/toApprActualAdd", method = RequestMethod.GET)
	public String toApprActualAdd(HttpServletRequest request) {
				return "project/tanchuan/appr_edit_actual";
	}
	
	
	/**
	 * 实际拨款记录列表查询
	 */
	@ResponseBody
	@RequestMapping(value = "/searchActualList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<GrantActual> searchActualList(@RequestBody GrantActual actual,
			HttpServletRequest request) {
		ResponseData<GrantActual> responseBody = new ResponseData<GrantActual>();
		if(actual.getPartGrantId() == null){
			responseBody.setResult(new Result(Status.ERROR,"error" , "必要的参数丢失!"));
			return responseBody;
		}
		try {
			Page<GrantActual> actualPage = grantActualService.queryPageList(actual,
					new PageRequest(actual.getPageNum(), 
							actual.getPageSize(), 
							Direction.fromString(actual.getDirection()), 
							actual.getProperty()));
			responseBody.setPageList(actualPage);
		} catch (Exception e) {
			_common_logger_.error("查询实际拨款列表失败！查询条件：" + actual, e);
		}
		return responseBody;
	}
	
}
