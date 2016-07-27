package com.galaxyinternet.chart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.galaxyinternet.bo.chart.ChartDataBo;
import com.galaxyinternet.bo.project.MeetingRecordBo;
import com.galaxyinternet.bo.project.ProjectBo;
import com.galaxyinternet.common.enums.DictEnum;
import com.galaxyinternet.common.query.ChartKpiQuery;
import com.galaxyinternet.dao.chart.ChartDao;
import com.galaxyinternet.dao.project.MeetingRecordDao;
import com.galaxyinternet.dao.project.ProjectDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.framework.core.utils.DateUtil;
import com.galaxyinternet.model.chart.Chart;
import com.galaxyinternet.model.department.Department;
import com.galaxyinternet.model.project.MeetingRecord;
import com.galaxyinternet.model.project.Project;
import com.galaxyinternet.model.user.User;
import com.galaxyinternet.service.DepartmentService;
import com.galaxyinternet.service.UserService;
import com.galaxyinternet.service.chart.KpiService;


@Service("com.galaxyinternet.service.chart.KpiService")
public class KpiServiceImpl extends BaseServiceImpl<Chart>implements KpiService {

	public static final int PER_YEAR_PRO_TARGET = 500; // 每人 每年 项目目标

	
	@Autowired
	private ChartDao chartDao;

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private MeetingRecordDao meetingRecordDao;

	@Autowired
	private UserService userService;

	@Autowired
	private DepartmentService departmentService;
	

	@Override
	protected BaseDao<Chart, Long> getBaseDao() {
		return this.chartDao;
	}


	
	// 获取所有事业线(指定部门的信息)
	public List<Department> queryAllSYX(List<Long> departmentIdList){
		Department query = new Department();
		query.setType(1);
		if(departmentIdList!=null && !departmentIdList.isEmpty()){
			query.setIds(departmentIdList);
		}
		return departmentService.queryList(query);
	}
	
	
	// 获取部门下所有投资经理 ， 或公司所有
	public List<User> queryAllTZJL(Long depID){
		Map<String, Object> userQuery = new HashMap<String, Object>();
		if(depID!=null){
			userQuery.put("departmentId", depID);
		}
		userQuery.put("roleId", 4);
		//userQuery.setStatus("0"); 
		return userService.querytUserByParams(userQuery);
	}
	
	
	// 事业线(各部门) - 投资经理人数统计
	public Map<Long,Integer> queryDeptTzjlSum(List<Long> departmentIdList){
		Map<Long, Integer> deptTzjlNumMap = new HashMap<Long, Integer>();
		Map<String, Object> deptTzjlNumQuery = new HashMap<String, Object>();
		deptTzjlNumQuery.put("roleId", 4);
		if(departmentIdList!=null && !departmentIdList.isEmpty()){
			deptTzjlNumQuery.put("departmentIds", departmentIdList);
		}
		//userQuery.setStatus("0"); 
		List<User> userlist = userService.querytTzjlSum(deptTzjlNumQuery);
		if (userlist != null && !userlist.isEmpty()) {
			for (User au : userlist) {
				deptTzjlNumMap.put(au.getDepartmentId(), au.getUserTzjlSum());
			}
		}
		return deptTzjlNumMap;
	}
			

	// 事业线(各部门) - 合伙人
	public Map<Long,User> queryDeptOfHHR(List<Long> departmentIdList,Integer roleId){
		Map<Long, User> deptOfHHRMap = new HashMap<Long, User>();
		Map<String, Object> deptHHRQuery = new HashMap<String, Object>();
		deptHHRQuery.put("roleId", roleId);
		if(departmentIdList!=null && !departmentIdList.isEmpty()){
			deptHHRQuery.put("departmentIds", departmentIdList);
		}
		//deptHHRQuery.setStatus("0"); 
		List<User> userlist = userService.querytUserByParams(deptHHRQuery);
		if (userlist != null && !userlist.isEmpty()) {
			for (User au : userlist) {
				deptOfHHRMap.put(au.getDepartmentId(), au);
			}
		}
		return deptOfHHRMap;
	}	
	
	
	
	
	
	
	/**
	 * 获取投资经理KPI
	 * @param request
	 * @return
	 */
	public Page<ChartDataBo> userKpi(ChartKpiQuery query){
		
		boolean inCompany = query.getDeptid()==null;
		
		Long total = 0l;
		List<ChartDataBo> kpiDataList = new ArrayList<ChartDataBo>();
		Page<ChartDataBo> kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		
		PageRequest pageRequest = new PageRequest();
		Direction direction = Direction.DESC;
		String property = "completed"; 
		pageRequest = new PageRequest(query.getPageNum(),query.getPageSize(), direction,property);
		
		//Integer nyear = Integer.parseInt(DateUtil.getFormatDateTime(new Date(),"yyyy"));
		Integer syear = Integer.parseInt(DateUtil.dateFormat(query.getSdate().substring(0, 10),"yyyy"));
		Integer eyear = Integer.parseInt(DateUtil.dateFormat(query.getEdate().substring(0, 10),"yyyy"));
		int target = (eyear - syear + 1)*PER_YEAR_PRO_TARGET;
		
		Long yearNow = DateUtil.dateToLong(new Date());  //截至到当前日期
		
		
		// 事业线  id - name 对应
		Map<Long, String> departIdNameMap = new HashMap<Long, String>();    
		if(!inCompany){                                                    
			Department thisDept = departmentService.queryById(query.getDeptid());
			departIdNameMap.put(thisDept.getId(), thisDept.getName());
		}else{                                                              
			List<Department> departmentList = queryAllSYX(null);
			for(Department dep : departmentList) {
				departIdNameMap.put(dep.getId(), dep.getName());
			}
		}
		
		List<User> tzjlList = null;
		List<Long> tzjlIdList = new ArrayList<Long>();
		Set<Long> tzjlDeptIdList = new HashSet<Long>();
		Map<Long, User> uIdUserMap = new HashMap<Long, User>();
		//事业线内 用户信息查询、封装
		if(!inCompany){ 
			tzjlDeptIdList.add(query.getDeptid());
			tzjlList = queryAllTZJL(query.getDeptid());
			if(tzjlList == null || tzjlList.isEmpty()){
				return kpiPage;
			}
			for(User tzjl : tzjlList){
				tzjlIdList.add(tzjl.getId());      
				uIdUserMap.put(tzjl.getId(), tzjl); 
			}
		}
		
		//统计查询， 查询用户  -- 项目数 
		ProjectBo proQuery = new ProjectBo();
		proQuery.setStartTime(query.getStartTime());
		proQuery.setEndTime(query.getEndTime());
		proQuery.setProjectType(query.getProjectType());
		//proQuery.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		if(!inCompany) {
			proQuery.setProjectDepartid(query.getDeptid());
			proQuery.setCreatetUids(tzjlIdList);
		}
		List<Project> proList = projectDao.selectUserProNumOrderByNum(proQuery,pageRequest);
		
		/*
		SELECT create_uid, project_departId, COUNT(id) completed 
		FROM sop_project 
		WHERE 
			create_uid IN ( 104 , 105 , 130 ) 
			AND created_time >= 1451577600000 
			AND created_time <= 1469462399000 
		GROUP BY create_uid 
		ORDER BY completed DESC 
		LIMIT 0, 10 
		*/
		
		
		// 用户  -- 项目数   记录数
		total = projectDao.selectUserProNumRowCount(proQuery);
		
		
		//统计查询, 查询  条件内  公司创建的所有项目
		ProjectBo allProNumQ1 = new ProjectBo();
		allProNumQ1.setStartTime(query.getStartTime());
		allProNumQ1.setEndTime(query.getEndTime());
		allProNumQ1.setProjectType(query.getProjectType());
		//allProNumQ1.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		Long allProQueryNum = projectDao.selectCount(allProNumQ1);
		
		
		//结果集中的   用户信息查询、封装
		tzjlIdList.clear();
		for(Project pro : proList){
			tzjlIdList.add(pro.getCreateUid());
		}
		if(inCompany){
			User userQ = new User();
			userQ.setIds(tzjlIdList);
			tzjlList = userService.queryList(userQ);
			for(User tzjl : tzjlList){
				uIdUserMap.put(tzjl.getId(), tzjl); 
				tzjlDeptIdList.add(tzjl.getDepartmentId());
			}
		}
		
		//统计   个人的累计项目数   仅不受查询时间影响
		Map<Long, Integer> userCompletedProNuntMap = new HashMap<Long, Integer>();
		ProjectBo proOverCount = new ProjectBo();  
		proOverCount.setCreatetUids(tzjlIdList);
		proOverCount.setProjectType(query.getProjectType());
		proOverCount.setEndTime(yearNow);
		//proOverCount.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		List<Project> proUserOverList = projectDao.selectUserCompletedProNum(proOverCount);
		for(Project pro : proUserOverList){
			userCompletedProNuntMap.put(pro.getCreateUid(), pro.getCompleted()); 
		}
		/*
		SELECT 
			create_uid, project_departId, COUNT(id) completed 
		FROM 
			sop_project 
		WHERE 
			create_uid IN ( 104 , 105 ) 
			AND created_time <= 1469428248048 
		GROUP BY create_uid 
		*/
		
		//统计 查询条件下 部门已完成的项目数
		Map<Long, Integer> deptCompletedProNuntMap = new HashMap<Long, Integer>();
		ProjectBo proOverCount1 = new ProjectBo();  //条件内完成数
		proOverCount1.setDeptIdList(new ArrayList<Long>(tzjlDeptIdList));
		proOverCount1.setProjectType(query.getProjectType());
		proOverCount1.setStartTime(query.getStartTime());
		proOverCount1.setEndTime(query.getEndTime());
		//proOverCount1.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		List<Project> proDepOverList = projectDao.selectDeptCompletedProNum(proOverCount1);
		for(Project pro : proDepOverList){
			deptCompletedProNuntMap.put(pro.getProjectDepartid(), pro.getCompleted()); 
		}
		/*
		SELECT 
			project_departId, COUNT(id) completed 
		FROM 
			sop_project 
		WHERE 
			project_departId IN ( 13 ) 
			AND created_time >= 1451577600000 
			AND created_time <= 1469462399000 
		GROUP BY project_departId 
		*/
		
		
		//统计个人在部门内排名
		Map<Long, Integer> depIdNuntMap = new HashMap<Long, Integer>();                   
		for(Project pro : proList){
			int ordNum = 1;
			if(depIdNuntMap.containsKey(pro.getProjectDepartid())){
				ordNum = depIdNuntMap.get(pro.getProjectDepartid())+1;
				pro.setDepNumOrder(ordNum);
				depIdNuntMap.put(pro.getProjectDepartid(),ordNum);
			}else{
				pro.setDepNumOrder(ordNum);
				depIdNuntMap.put(pro.getProjectDepartid(),ordNum);
			}
		}
		
		
		/*===========     会议分割线      ===================*/
		
		//查询条件下   立项会结果为“通过”的项目id数
		MeetingRecordBo mquery1 = new MeetingRecordBo();
		mquery1.setStartTime(query.getSdate());
		mquery1.setEndTime(query.getEdate());
		mquery1.setProjectType(query.getProjectType());
		mquery1.setMeetingType(DictEnum.meetingType.立项会.getCode());
		mquery1.setMeetingResult(DictEnum.meetingResult.通过.getCode());
		Long lxhPassNum = meetingRecordDao.selectMeetCountByGHL(mquery1);
		
		//查询条件下   投资决策会 结果为“通过”的项目id数
		mquery1.setMeetingType(DictEnum.meetingType.投决会.getCode());
		Long tjhPassNum = meetingRecordDao.selectMeetCountByGHL(mquery1);
		/* 
		SELECT pro.id proid, meet.*	
		SELECT COUNT(*) 
		FROM 
			sop_meeting_record meet,sop_project pro 
		WHERE 
			meet.project_id = pro.id 
			AND meet.meeting_date >= '2016-01-01 00:00:00' 
			AND meet.meeting_date <= '2016-07-25 23:59:59'
			AND meet.meeting_type = 'meetingType:3' 
			AND meet.meeting_result = 'meetingResult:1'
			AND meet.record_type = 0 
		*/
		//投资经理查询条件下的项目     立项会结果为“通过”的项目id数
		Map<Long, Integer> userLxhPNum = new HashMap<Long, Integer>();  
		MeetingRecordBo mquery2 = new MeetingRecordBo();
		mquery2.setStartTime(query.getSdate());
		mquery2.setEndTime(query.getEdate());
		mquery2.setProjectType(query.getProjectType());
		mquery2.setMeetingType(DictEnum.meetingType.立项会.getCode());
		mquery2.setMeetingResult(DictEnum.meetingResult.通过.getCode());
		mquery2.setUserIdList(tzjlIdList);
		List<MeetingRecord> userslxhPass = meetingRecordDao.selectUserPassMeetNum(mquery2);
		for(MeetingRecord record : userslxhPass){
			userLxhPNum.put(record.getCreateUid(), record.getPassMeetNum());
		}
		/*
		SELECT 
			pro.create_uid, pro.project_departId departId, COUNT(meet.id) passMeetNum
		FROM 
			sop_meeting_record meet,sop_project pro 
		WHERE 
			meet.project_id = pro.id 
			AND pro.create_uid IN ( 104 , 105 ) 
			AND meet.meeting_date >= '2016-01-01 00:00:00' 
			AND meet.meeting_date <= '2016-07-25 23:59:59' 
			AND meet.meeting_type = 'meetingType:3'
			AND meet.meeting_result = 'meetingResult:1'
			AND meet.record_type = 0 
		GROUP BY pro.create_uid 
		*/
		//投资经理查询条件下的项目     立项会结果为“通过”的项目id数
		Map<Long, Integer> userTjhPNum = new HashMap<Long, Integer>();  
		mquery2.setMeetingType(DictEnum.meetingType.投决会.getCode());
		List<MeetingRecord> userstjhPass = meetingRecordDao.selectUserPassMeetNum(mquery2);
		for(MeetingRecord record : userstjhPass){
			userTjhPNum.put(record.getCreateUid(), record.getPassMeetNum());
		}
		
		
		int companyRank = 0;
		for(Project pro : proList){
			companyRank += 1;
			User user = uIdUserMap.get(pro.getCreateUid());
			
			ChartDataBo kpi = new ChartDataBo();
			
			kpi.setDepartmentId(pro.getProjectDepartid());
			kpi.setDepartmentName(departIdNameMap.get(pro.getProjectDepartid()));
			
			kpi.setUserId(pro.getCreateUid());
			kpi.setRealName(user.getRealName());
			
			kpi.setCompanyRank(companyRank);
			kpi.setDeptRank(pro.getDepNumOrder());
			
			kpi.setTarget(target);
			kpi.setCompleted(pro.getCompleted());   //项目数 ： 查询条件下，投资经理创建的项目数
			kpi.setCompletedAll(userCompletedProNuntMap.get(pro.getCreateUid())==null?0:userCompletedProNuntMap.get(pro.getCreateUid()));     //累计项目数： 仅不受查询时间影响
			
			//公司完成数占比  ：  项目数 /查询条件下公司所有新建项目
			double total_rate = 0;
			if(allProQueryNum!=null && allProQueryNum != 0 ){
				total_rate =   pro.getCompleted()*1.0 / allProQueryNum;
			}
			kpi.setTotalRate(total_rate);
			
			//部门完成数占比  ：  项目数 /查询条件下部门所有新建项目
			double dept_rate = 0;
			if(deptCompletedProNuntMap.get(pro.getProjectDepartid()) != null && deptCompletedProNuntMap.get(pro.getProjectDepartid()) != 0 ){
				dept_rate =  pro.getCompleted()*1.0 / deptCompletedProNuntMap.get(pro.getProjectDepartid());
			}
			kpi.setDeptRate(dept_rate);
			
			
			//立项会通过数
			kpi.setLxhPnumber(userLxhPNum.get(pro.getCreateUid())==null?0:userLxhPNum.get(pro.getCreateUid()));
			//过会率
			double ghlRate = 0;
			if(lxhPassNum!=null && lxhPassNum != 0 ){
				ghlRate =  kpi.getLxhPnumber()*1.0 / lxhPassNum ;
			}
			kpi.setGhlRate(ghlRate);
			
			
			//投资决策会通过数
			kpi.setTjhPnumber(userTjhPNum.get(pro.getCreateUid())==null?0:userTjhPNum.get(pro.getCreateUid()));
			//投决率
			double tjl_rate = 0;
			if(tjhPassNum!=null && tjhPassNum != 0 ){
				tjl_rate = kpi.getTjhPnumber()*1.0 / tjhPassNum ;
			}
			kpi.setTjlRate(tjl_rate);
			
			kpiDataList.add(kpi);                
		}
		kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		return kpiPage;
	}
	
	
	
	/**
	 * 获取部门KPI
	 * @param request
	 * @return
	 */
	public Page<ChartDataBo> deptkpi(ChartKpiQuery query){
		
		Long total = 0l;
		List<ChartDataBo> kpiDataList = new ArrayList<ChartDataBo>();
		Page<ChartDataBo> kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		
		PageRequest pageRequest = new PageRequest();
		Direction direction = Direction.DESC;
		String property = "completed"; 
		pageRequest = new PageRequest(query.getPageNum(),query.getPageSize(), direction,property);
		
		//Integer nyear = Integer.parseInt(DateUtil.getFormatDateTime(new Date(),"yyyy"));
		Integer syear = Integer.parseInt(DateUtil.dateFormat(query.getSdate().substring(0, 10),"yyyy"));
		Integer eyear = Integer.parseInt(DateUtil.dateFormat(query.getEdate().substring(0, 10),"yyyy"));
		int perTarget = (eyear - syear + 1)*PER_YEAR_PRO_TARGET;
		
		Long yearNow = DateUtil.dateToLong(new Date());  //截至到当前日期
		
		
		//按条件  统计查询， 查询   事业线 -- 项目数 
		ProjectBo proQuery = new ProjectBo();
		proQuery.setStartTime(query.getStartTime());
		proQuery.setEndTime(query.getEndTime());
		proQuery.setProjectType(query.getProjectType());
		//proQuery.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		List<Project> proList = projectDao.selectDeptProNumOrderByNum(proQuery,pageRequest);
		/*
		SELECT 
			project_departId, COUNT(id) completed 
		FROM 
			sop_project 
		WHERE 
			created_time >= 1451577600000 
			AND created_time <= 1469462399000 
		GROUP BY project_departId 
		ORDER BY completed 
		DESC LIMIT 0, 10
		*/
		// 事业线  -- 项目数   记录数
		total = projectDao.selectDeptProNumRowCount(proQuery);
		
		
		//结果集中的部门信息、封装
		// 事业线  id - name 对应
		Map<Long, String> departIdNameMap = new HashMap<Long, String>();  
		List<Long> deptIdList = new ArrayList<Long>();
		for(Project pro : proList){
			deptIdList.add(pro.getProjectDepartid());
		}
		List<Department> departmentList = queryAllSYX(deptIdList);
		for(Department dep : departmentList) {
			departIdNameMap.put(dep.getId(), dep.getName());
		}
				
		
		//统计查询, 查询  条件内  公司创建的所有项目
		ProjectBo allProNumQ1 = new ProjectBo();
		allProNumQ1.setStartTime(query.getStartTime());
		allProNumQ1.setEndTime(query.getEndTime());
		allProNumQ1.setProjectType(query.getProjectType());
		//allProNumQ1.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		Long allProQueryNum = projectDao.selectCount(allProNumQ1);
		
		
		//事业线 - projecttype项目数    内部创建("内部创建","projectType:2");
		double zjRate = 0;
		Map<Long, Integer> deptProNumByTypetMap = new HashMap<Long, Integer>();
		if(StringUtils.isBlank(query.getProjectType())){
			ProjectBo proCountByType = new ProjectBo();  
			proCountByType.setStartTime(query.getStartTime());
			proCountByType.setEndTime(query.getEndTime());
			proCountByType.setProjectType(DictEnum.projectType.内部创建.getCode());
			proCountByType.setDeptIdList(deptIdList);
			//proCountByType.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
			List<Project> deptProNumByTypetList = projectDao.selectDeptCompletedProNum(proCountByType);
			for(Project pro : deptProNumByTypetList){
				deptProNumByTypetMap.put(pro.getProjectDepartid(), pro.getCompleted()); 
			}
		}else if(query.getProjectType().equals(DictEnum.projectType.内部创建.getCode())){
			zjRate = 1;
		}
		/*
		SELECT 
			project_departId, COUNT(id) completed 
		FROM 
			sop_project 
		WHERE 
			project_type = 'projectType:2' 
			AND project_departId IN ( 11,12,13,24 ) 
			AND created_time >= 1451577600000 
			AND created_time <= 1469462399000 
		GROUP BY project_departId 
		*/
		
		
		//统计   部门的累计项目数   仅不受查询时间影响
		Map<Long, Integer> deptCompletedProNuntMap = new HashMap<Long, Integer>();
		ProjectBo proOverCount = new ProjectBo();  
		proOverCount.setDeptIdList(deptIdList);
		proOverCount.setProjectType(query.getProjectType());
		proOverCount.setEndTime(yearNow);
		//proOverCount.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		List<Project> proDeptOverList = projectDao.selectDeptCompletedProNum(proOverCount);
		for(Project pro : proDeptOverList){
			deptCompletedProNuntMap.put(pro.getProjectDepartid(), pro.getCompleted()); 
		}
		/*
		SELECT 
			project_departId, COUNT(id) completed 
		FROM 
			sop_project 
		WHERE 
			project_departId IN ( 11,12,13,24 ) 
			AND created_time <= 1469439266771 
		GROUP BY project_departId 
		*/
		
		/*===========     会议分割线      ===================*/
		
		//查询条件下   立项会结果为“通过”的项目id数
		MeetingRecordBo mquery1 = new MeetingRecordBo();
		mquery1.setStartTime(query.getSdate());
		mquery1.setEndTime(query.getEdate());
		mquery1.setProjectType(query.getProjectType());
		mquery1.setMeetingType(DictEnum.meetingType.立项会.getCode());
		mquery1.setMeetingResult(DictEnum.meetingResult.通过.getCode());
		Long lxhPassNum = meetingRecordDao.selectMeetCountByGHL(mquery1);
		
		//查询条件下   投资决策会 结果为“通过”的项目id数
		mquery1.setMeetingType(DictEnum.meetingType.投决会.getCode());
		Long tjhPassNum = meetingRecordDao.selectMeetCountByGHL(mquery1);
		
		
		//事业线  查询条件下的项目     立项会结果为“通过”的项目id数
		Map<Long, Integer> deptLxhPNum = new HashMap<Long, Integer>();  
		MeetingRecordBo mquery2 = new MeetingRecordBo();
		mquery2.setDeptIdList(deptIdList);
		mquery2.setStartTime(query.getSdate());
		mquery2.setEndTime(query.getEdate());
		mquery2.setProjectType(query.getProjectType());
		mquery2.setMeetingType(DictEnum.meetingType.立项会.getCode());
		mquery2.setMeetingResult(DictEnum.meetingResult.通过.getCode());
		List<MeetingRecord> deptlxhPass = meetingRecordDao.selectDeptPassMeetNum(mquery2);
		for(MeetingRecord record : deptlxhPass){
			deptLxhPNum.put(record.getDepartId(), record.getPassMeetNum());
		}
		/*  COUNT(meet.id)  ==  COUNT(pro.id) 
		SELECT 
			pro.project_departId departId, COUNT(meet.id) passMeetNum 
		FROM 
			sop_meeting_record meet,sop_project pro 
		WHERE 
			meet.project_id = pro.id 
			AND pro.project_departId IN ( 11,12,13,24 ) 
			AND meet.meeting_date >= '2016-01-01 00:00:00' 
			AND meet.meeting_date <= '2016-07-25 23:59:59'
			AND meet.meeting_type = 'meetingType:3'
			AND meet.meeting_result = 'meetingResult:1' 
			AND meet.record_type = 0
		GROUP BY pro.project_departId 
		*/
		//事业线   查询条件下的项目     立项会结果为“通过”的项目id数
		Map<Long, Integer> deptTjhPNum = new HashMap<Long, Integer>();  
		mquery2.setMeetingType(DictEnum.meetingType.投决会.getCode());
		List<MeetingRecord> deptTjhPass = meetingRecordDao.selectDeptPassMeetNum(mquery2);
		for(MeetingRecord record : deptTjhPass){
			deptTjhPNum.put(record.getDepartId(), record.getPassMeetNum());
		}
		
		
		//结果集中 各部门的投资经理的人数
		Map<Long, Integer> deptTzjlNumMap = queryDeptTzjlSum(deptIdList);
		//结果集中 各部门的 HHR
		Map<Long, User> deptOfHHRMap = queryDeptOfHHR(deptIdList,3);
		
		int companyRank = 0;
		for(Project pro : proList){
			companyRank += 1;
			
			ChartDataBo kpi = new ChartDataBo();
			
			kpi.setDepartmentId(pro.getProjectDepartid());
			kpi.setDepartmentName(departIdNameMap.get(pro.getProjectDepartid()));
			//合伙人 。。。
			if(deptOfHHRMap.get(pro.getProjectDepartid())!=null){
				kpi.setUserId(deptOfHHRMap.get(pro.getProjectDepartid()).getId());
				kpi.setRealName(deptOfHHRMap.get(pro.getProjectDepartid()).getRealName());
			}
			
			kpi.setCompanyRank(companyRank);
			
			// TODO 
			kpi.setTarget(deptTzjlNumMap.get(pro.getProjectDepartid())==null?0: deptTzjlNumMap.get(pro.getProjectDepartid())* perTarget);
			kpi.setCompleted(pro.getCompleted());   //项目数 ： 查询条件下，投资经理创建的项目数
			kpi.setCompletedAll(deptCompletedProNuntMap.get(pro.getProjectDepartid()));     //累计项目数： 仅不受查询时间影响
			
			//公司完成数占比  ：  项目数 /查询条件下公司所有新建项目
			double total_rate = 0;
			if(allProQueryNum!=null && allProQueryNum!=0){
				total_rate = pro.getCompleted()*1.0 / allProQueryNum;
			}
			kpi.setTotalRate(total_rate);
			
			//内部创建项目占比
			if(StringUtils.isBlank(query.getProjectType())){
				zjRate = deptProNumByTypetMap.get(pro.getProjectDepartid())*1.0 / pro.getCompleted();
			}
			kpi.setZjRate(zjRate);
			
			
			//立项会通过数
			kpi.setLxhPnumber(deptLxhPNum.get(pro.getProjectDepartid())==null?0:deptLxhPNum.get(pro.getProjectDepartid()));
			//过会率
			double ghlRate = 0;
			if(lxhPassNum!=null && lxhPassNum != 0 ){
				ghlRate = kpi.getLxhPnumber()*1.0 / lxhPassNum ;
			}
			kpi.setGhlRate(ghlRate);
			
			//投资决策会通过数
			kpi.setTjhPnumber(deptTjhPNum.get(pro.getProjectDepartid())==null?0:deptTjhPNum.get(pro.getProjectDepartid()));
			//投决率
			double tjl_rate = 0;
			if(tjhPassNum!=null && tjhPassNum != 0 ){
				tjl_rate = kpi.getTjhPnumber()*1.0 / tjhPassNum ;
			}
			kpi.setTjlRate(tjl_rate);
			
			kpiDataList.add(kpi);                
		}
		kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		return kpiPage;
	}
	
	
	
	
	
	
	
	/**
	 * 项目分析
	 * 项目数统计，高管调用    根据事业部分组统计  
	 * @param request
	 * @return
	 */
	public Page<ChartDataBo> ggLineChart(ChartKpiQuery query){
		
		Long total = 0l;
		List<ChartDataBo> kpiDataList = new ArrayList<ChartDataBo>();
		Page<ChartDataBo> kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		
		PageRequest pageRequest = new PageRequest();
		Direction direction = Direction.DESC;
		String property = "completed"; 
		if(!StringUtils.isBlank(query.getProjectType())){
			property = "type_completed"; 
		}
		pageRequest = new PageRequest(query.getPageNum(),query.getPageSize(), direction,property);
		
		//Integer nyear = Integer.parseInt(DateUtil.getFormatDateTime(new Date(),"yyyy"));
		Integer syear = Integer.parseInt(DateUtil.dateFormat(query.getSdate().substring(0, 10),"yyyy"));
		Integer eyear = Integer.parseInt(DateUtil.dateFormat(query.getEdate().substring(0, 10),"yyyy"));
		int perTarget = (eyear - syear + 1)*PER_YEAR_PRO_TARGET;
		
		
		//按条件  统计查询， 查询   事业线 -- 项目数 
		ProjectBo proQuery = new ProjectBo();
		proQuery.setStartTime(query.getStartTime());
		proQuery.setEndTime(query.getEndTime());
		if(StringUtils.isBlank(query.getProjectType())){
			proQuery.setProjectType(DictEnum.projectType.内部创建.getCode()); 
		}else {
			proQuery.setProjectType(query.getProjectType());
			proQuery.setResultNullFilter(0);
		}
		//proQuery.setDeptIdList(deptIdList);  //多余
		//proQuery.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		List<Project> proList = projectDao.selectDeptAllProNumAndByType(proQuery,pageRequest);
		
		// 事业线  -- 项目数   记录数
		total = (long) proList.size();
		
		/*
		SELECT 
			a.project_departId,a.completed completed,IFNULL(b.completed,0) type_completed 
		FROM 
			(
			 SELECT 
				project_departId, COUNT(id) completed 
			FROM 
				sop_project 
			WHERE 
				created_time >= 1451577600000 
				AND created_time <= 1469548799000 
			GROUP BY project_departId 
			) a 
			LEFT JOIN 
			( 
			SELECT 
				project_departId, COUNT(id) completed 
			FROM 
				sop_project 
			WHERE 
				project_type = 'projectType:1' 
				AND created_time >= 1451577600000
				AND created_time <= 1469548799000
			GROUP BY project_departId 
			) b 
			ON a.project_departId = b.project_departId 
		WHERE 
			b.completed != 0 
		ORDER BY type_completed DESC 
		*/
		
		// 分页处理
		int beginIndex =  query.getPageNum() * query.getPageSize();
		int endIndex = query.getPageNum() * query.getPageSize() + query.getPageSize();
		if (proList.size() < beginIndex) {
			return  kpiPage;
		}else if (proList.size() < endIndex) {
			endIndex = proList.size();
		}
		proList =  proList.subList(beginIndex, endIndex);
		
		
		// 结果集中的  事业线  id - name 对应
		Map<Long, String> departIdNameMap = new HashMap<Long, String>();  
		List<Long> deptIdList = new ArrayList<Long>();
		for(Project pro : proList){
			deptIdList.add(pro.getProjectDepartid());
		}
		List<Department> departmentList = queryAllSYX(deptIdList);
		for(Department dep : departmentList) {
			departIdNameMap.put(dep.getId(), dep.getName());
		}
		
		
		//结果集中 各部门的投资经理的人数
		Map<Long, Integer> deptTzjlNumMap = queryDeptTzjlSum(deptIdList);
		
		for(Project pro : proList){
			
			ChartDataBo kpi = new ChartDataBo();
			
			kpi.setDepartmentId(pro.getProjectDepartid());
			kpi.setDepartmentName(departIdNameMap.get(pro.getProjectDepartid()));
			// TODO  deptTzjlNumMap.get(pro.getProjectDepartid())==null?0: deptTzjlNumMap.get(pro.getProjectDepartid())* perTarget
			int target = (deptTzjlNumMap.get(pro.getProjectDepartid())==null?0: deptTzjlNumMap.get(pro.getProjectDepartid())) * perTarget;
			int completed = pro.getCompleted();
			int notcompleted = target - completed < 0 ? 0 : target - completed;
			
			
			int zj_completed = 0;
			int wb_completed = 0;
			double zj_rate = 0;
			double wb_rate = 0;
			
			if(StringUtils.isBlank(query.getProjectType()) || query.getProjectType().equals(DictEnum.projectType.内部创建.getCode())){
				zj_completed = pro.getType_completed();
				wb_completed = completed - zj_completed;
			}else if(query.getProjectType().equals(DictEnum.projectType.外部投资.getCode())){
				wb_completed = pro.getType_completed();
				zj_completed = completed - wb_completed;
			}  
			
			if(completed > 0){
				zj_rate = zj_completed*1.0 / completed;
				wb_rate = wb_completed*1.0 / completed;
			}
			
			double rate = 1;
			if(target != 0){
				if(query.getProjectType() == null){
					rate = completed*1.0 / target;
				}else if(DictEnum.projectType.内部创建.getCode().equals(query.getProjectType())){
					rate = zj_completed*1.0 / target;
				}else{
					rate = wb_completed*1.0 / target;
				}
			}
			
			kpi.setTarget(target);
			kpi.setCompleted(completed);
			kpi.setZjCompleted(zj_completed);
			kpi.setWbCompleted(wb_completed);
			kpi.setNotCompleted(notcompleted);
			
			kpi.setRate(rate);
			kpi.setZjRate(zj_rate);
			kpi.setWbRate(wb_rate);
			
			kpiDataList.add(kpi);                
		}
		kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		return kpiPage;
	}
	
	

	
	/**
	 * 项目分析
	 * 项目数统计，HHR调用    根据  事业线投资经理  统计  
	 * @param request
	 * @return
	 */
	public Page<ChartDataBo> hhrLineChart(ChartKpiQuery query){
		
		Long total = 0l;
		List<ChartDataBo> kpiDataList = new ArrayList<ChartDataBo>();
		Page<ChartDataBo> kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		
		PageRequest pageRequest = new PageRequest();
		Direction direction = Direction.DESC;
		String property = "completed"; 
		if(!StringUtils.isBlank(query.getProjectType())){
			property = "type_completed"; 
		}
		pageRequest = new PageRequest(query.getPageNum(),query.getPageSize(), direction,property);
		
		//Integer nyear = Integer.parseInt(DateUtil.getFormatDateTime(new Date(),"yyyy"));
		Integer syear = Integer.parseInt(DateUtil.dateFormat(query.getSdate().substring(0, 10),"yyyy"));
		Integer eyear = Integer.parseInt(DateUtil.dateFormat(query.getEdate().substring(0, 10),"yyyy"));
		int perTarget = (eyear - syear + 1)*PER_YEAR_PRO_TARGET;
		
		//  事业线  id - name 对应
		Department thisDept = departmentService.queryById(query.getDeptid());
			
		// 事业线内 用户信息查询、封装
		List<User> tzjlList = queryAllTZJL(query.getDeptid());
		List<Long> tzjlIdList = new ArrayList<Long>();
		Map<Long, User> uIdUserMap = new HashMap<Long, User>();
		if (tzjlList == null || tzjlList.isEmpty()) {
			return kpiPage;
		}
		for (User tzjl : tzjlList) {
			tzjlIdList.add(tzjl.getId());
			uIdUserMap.put(tzjl.getId(), tzjl);
		}
		
		//按条件  统计查询， 查询   事业线 投资经理 -- 项目数 
		ProjectBo proQuery = new ProjectBo();
		proQuery.setStartTime(query.getStartTime());
		proQuery.setEndTime(query.getEndTime());
		if(StringUtils.isBlank(query.getProjectType())){
			proQuery.setProjectType(DictEnum.projectType.内部创建.getCode()); 
		}else {
			proQuery.setProjectType(query.getProjectType());
			proQuery.setResultNullFilter(0);
		}
		proQuery.setProjectDepartid(query.getDeptid());
		proQuery.setCreatetUids(tzjlIdList);
		//proQuery.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		List<Project> proList = projectDao.selectTzjlAllProNumAndByType(proQuery,pageRequest);
		
		// 投资经理   -- 项目数   记录数
		total = (long) proList.size();
		/*
		SELECT 
			a.create_uid,a.completed completed,IFNULL(b.completed,0) type_completed 
		FROM 
			( 
			SELECT 
				create_uid, COUNT(id) completed 
			FROM 
				sop_project 
			WHERE 
				create_uid IN ( 104,105,130)
				AND project_departId = 13 
				AND created_time >= 1451577600000 
				AND created_time <= 1469548799000
			GROUP BY create_uid 
			) a 
			LEFT JOIN 
			( 
			SELECT 
				create_uid, COUNT(id) completed 
			FROM 
				sop_project 
			WHERE 
				create_uid IN ( 104,105,130)
				AND project_type = 'projectType:2' 
				AND project_departId = 13 
				AND created_time >= 1451577600000
				AND created_time <= 1469548799000
			GROUP BY create_uid 
			) b 
			ON a.create_uid = b.create_uid 
		WHERE 
			b.completed != 0
		ORDER BY type_completed DESC 
		*/
		
		// 分页处理
		int beginIndex =  query.getPageNum() * query.getPageSize();
		int endIndex = query.getPageNum() * query.getPageSize() + query.getPageSize();
		if (proList.size() < beginIndex) {
			return  kpiPage;
		}else if (proList.size() < endIndex) {
			endIndex = proList.size();
		}
		proList =  proList.subList(beginIndex, endIndex);
		
		
		for(Project pro : proList){
			
			ChartDataBo kpi = new ChartDataBo();
			
			kpi.setUserId(pro.getCreateUid());
			kpi.setRealName(uIdUserMap.get(pro.getCreateUid()).getRealName());
			
			kpi.setDepartmentId(pro.getProjectDepartid());
			kpi.setDepartmentName(thisDept.getName());
			
			int target = perTarget;
			int completed = pro.getCompleted();
			int notcompleted = target - completed < 0 ? 0 : target - completed;
			
			int zj_completed = 0;
			int wb_completed = 0;
			double zj_rate = 0;
			double wb_rate = 0;
			
			if(StringUtils.isBlank(query.getProjectType()) || query.getProjectType().equals(DictEnum.projectType.内部创建.getCode())){
				zj_completed = pro.getType_completed();
				wb_completed = completed - zj_completed;
			}else if(query.getProjectType().equals(DictEnum.projectType.外部投资.getCode())){
				wb_completed = pro.getType_completed();
				zj_completed = completed - wb_completed;
			}  
			zj_rate = zj_completed*1.0 / completed;
			wb_rate = wb_completed*1.0 / completed;
			
			
			double rate = 0;   
			if(query.getProjectType() == null){
				rate = completed*1.0 / target;
			}else if(DictEnum.projectType.内部创建.getCode().equals(query.getProjectType())){
				rate = zj_completed*1.0 / target;
			}else{
				rate = wb_completed*1.0 / target;
			}
			
			kpi.setTarget(target);
			kpi.setCompleted(completed);
			kpi.setZjCompleted(zj_completed);
			kpi.setWbCompleted(wb_completed);
			kpi.setNotCompleted(notcompleted);
			
			kpi.setRate(rate);
			kpi.setZjRate(zj_rate);
			kpi.setWbRate(wb_rate);
			
			kpiDataList.add(kpi);                
		}
		kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		return kpiPage;
	}
		
	
		
		
	
	
	
	/**
	 * 项目分析
	 * 过会率统计，投决率统计
	 * 高管调用    根据事业部分组统计 
	 * 
	 * 查询时间内    各部门中   开过会议的项目， 封装其结果集
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public Page<ChartDataBo> deptMeetPassRate(ChartKpiQuery query) throws Exception{
		
		Long total = 0l;
		List<ChartDataBo> kpiDataList = new ArrayList<ChartDataBo>();
		Page<ChartDataBo> kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		
		
		//查询时间内    各部门中  会议条件下的项目数
		MeetingRecordBo mquery2 = new MeetingRecordBo();
		mquery2.setStartTime(query.getSdate());
		mquery2.setEndTime(query.getEdate());
		mquery2.setMeetingType(query.getMeetingType());
		
		List<MeetingRecord> deptlxhHasNum = meetingRecordDao.selectDeptHasMeetProNumByParams(mquery2);
		
		mquery2.setMeetingResult(DictEnum.meetingResult.通过.getCode());
		List<MeetingRecord> deptlxhPassNum = meetingRecordDao.selectDeptHasMeetProNumByParams(mquery2);
		
		mquery2.setMeetingResult(DictEnum.meetingResult.否决.getCode());
		List<MeetingRecord> deptlxhVetoNum = meetingRecordDao.selectDeptHasMeetProNumByParams(mquery2);
		
		/*
		SELECT 
			a.departId, COUNT(a.project_id) sumProNum 
		FROM 
			( 
			SELECT 
				pro.id project_id, pro.create_uid,pro.project_departId departId 
			FROM 
				sop_meeting_record meet,sop_project pro 
			WHERE 
				meet.project_id = pro.id 
				AND meet.meeting_date >= '2016-01-01 00:00:00' 
				AND meet.meeting_date <= '2016-07-26 23:59:59'
				AND meet.meeting_type = 'meetingType:3' 
				AND meet.record_type = 0
			GROUP BY pro.id 
			) a 
		GROUP BY a.departId 
			*/
		
		//各部门中   开过会议的项目的 数据封装
		Map<Long, Map<String, Integer>> deptId_ProNumMap = new HashMap<Long, Map<String, Integer>>();
		for(MeetingRecord mrecord : deptlxhHasNum){
			Map<String, Integer>  meetTypeProNumMap = new HashMap<String, Integer>();
			meetTypeProNumMap.put("hasNum", mrecord.getSumProNum());
			deptId_ProNumMap.put(mrecord.getDepartId(), meetTypeProNumMap);
		}
		
		for(MeetingRecord mrecord : deptlxhPassNum){
			if(!deptId_ProNumMap.containsKey(mrecord.getDepartId())){
				throw new Exception("统计错误");
			}
			deptId_ProNumMap.get(mrecord.getDepartId()).put("passNum", mrecord.getSumProNum());
			
			/*Map<String, Integer>  meetTypeProNumMap = deptId_ProNumMap.get(mrecord.getDepartId());
			meetTypeProNumMap.put("passNum", mrecord.getSumProNum());
			deptId_ProNumMap.put(mrecord.getDepartId(), meetTypeProNumMap);*/
		}
		for(MeetingRecord mrecord : deptlxhVetoNum){
			if(!deptId_ProNumMap.containsKey(mrecord.getDepartId())){
				throw new Exception("统计错误");
			}
			deptId_ProNumMap.get(mrecord.getDepartId()).put("vetoNum", mrecord.getSumProNum());
		}
		
		//  记录数
		total = (long) deptId_ProNumMap.size();
		
		// 分页 预处理
		int beginIndex =  query.getPageNum() * query.getPageSize();
		int endIndex = query.getPageNum() * query.getPageSize() + query.getPageSize();
		if (total.intValue() < beginIndex) {
			return new Page<ChartDataBo>( new ArrayList<ChartDataBo>(),0l);
		}else if (total.intValue() < endIndex) {
			endIndex = total.intValue();
		}
		
		// 事业线  id - name 对应
		Map<Long, String> departIdNameMap = new HashMap<Long, String>();  
		List<Department> departmentList = queryAllSYX(null);
		for(Department dep : departmentList) {
			departIdNameMap.put(dep.getId(), dep.getName());
		}
		
		
		// 结果集 封装
		for(Entry<Long, Map<String, Integer>>  mapEntry : deptId_ProNumMap.entrySet()){
			
			ChartDataBo kpi = new ChartDataBo();
			
			kpi.setDepartmentId(mapEntry.getKey());
			kpi.setDepartmentName(departIdNameMap.get(mapEntry.getKey()));
			
			int meetAllProNum = mapEntry.getValue().get("hasNum")==null?0:mapEntry.getValue().get("hasNum");
			int meetPassProNum = mapEntry.getValue().get("passNum")==null?0:mapEntry.getValue().get("passNum");
			int meetVetoProNum = mapEntry.getValue().get("vetoNum")==null?0:mapEntry.getValue().get("vetoNum");
			int meetWaitProNum = meetAllProNum - meetPassProNum - meetVetoProNum;
			
			double ghl_rate = (double)meetPassProNum / meetAllProNum;
			
			kpi.setProMeetNum(meetAllProNum);
			kpi.setPassMeetProNumber(meetPassProNum);
			kpi.setVetoMeetProNumber(meetVetoProNum);
			kpi.setWaitMeetProNumber(meetWaitProNum);
			kpi.setRate(ghl_rate);
			
			kpiDataList.add(kpi);                
		}
		
		// 排序，从大到小
		for (int i = 0; i < kpiDataList.size() - 1; i++) {
			for (int j = 0; j < kpiDataList.size() - i - 1; j++) {
				if ( kpiDataList.get(j).getRate() < kpiDataList.get(j + 1).getRate()) {
					ChartDataBo temp =  kpiDataList.get(j);
					kpiDataList.set(j, kpiDataList.get(j + 1));
					kpiDataList.set(j + 1, temp);
				}
			}
		}
		
		kpiDataList =  kpiDataList.subList(beginIndex, endIndex);
		
		kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		return kpiPage;
	}
	
	
	
	 
	
	
	/**
	 * 项目分析
	 * 过会率统计，投决率统计
	 * 高管调用    根据事业部分组统计 
	 * 
	 * 查询时间内    所属部门内的投资经理   开过会议的项目， 封装其结果集
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public Page<ChartDataBo> tzjlMeetPassRate(ChartKpiQuery query) throws Exception{
		
		Long total = 0l;
		List<ChartDataBo> kpiDataList = new ArrayList<ChartDataBo>();
		Page<ChartDataBo> kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		
		
		// 事业线  信息
		Department thisDept = departmentService.queryById(query.getDeptid());
		
		//事业线内 用户信息查询、封装
		List<User> tzjlList = queryAllTZJL(query.getDeptid());
		List<Long> tzjlIdList = new ArrayList<Long>();
		Map<Long, User> uIdUserMap = new HashMap<Long, User>();
		
		if(tzjlList == null || tzjlList.isEmpty()){
			return kpiPage;
		}
		for(User tzjl : tzjlList){
			tzjlIdList.add(tzjl.getId());      
			uIdUserMap.put(tzjl.getId(), tzjl); 
		}
		
		
		//查询时间内    各部门中  会议条件下的项目数
		MeetingRecordBo mquery2 = new MeetingRecordBo();
		mquery2.setStartTime(query.getSdate());
		mquery2.setEndTime(query.getEdate());
		mquery2.setDepartId(query.getDeptid());
		mquery2.setUserIdList(tzjlIdList);
		mquery2.setMeetingType(query.getMeetingType());
		
		List<MeetingRecord> tzjlHasNum = meetingRecordDao.selectTzjlHasMeetProNumByParams(mquery2);
		
		mquery2.setMeetingResult(DictEnum.meetingResult.通过.getCode());
		List<MeetingRecord> tzjlPassNum = meetingRecordDao.selectTzjlHasMeetProNumByParams(mquery2);
		
		mquery2.setMeetingResult(DictEnum.meetingResult.否决.getCode());
		List<MeetingRecord> tzjlVetoNum = meetingRecordDao.selectTzjlHasMeetProNumByParams(mquery2);
		
		
		
		
		
		//各部门中   开过会议的项目的 数据封装
		Map<Long, Map<String, Integer>> deptId_ProNumMap = new HashMap<Long, Map<String, Integer>>();
		for(MeetingRecord mrecord : tzjlHasNum){
			Map<String, Integer>  meetTypeProNumMap = new HashMap<String, Integer>();
			meetTypeProNumMap.put("hasNum", mrecord.getSumProNum());
			deptId_ProNumMap.put(mrecord.getCreateUid(), meetTypeProNumMap);
		}
		
		for(MeetingRecord mrecord : tzjlPassNum){
			if(!deptId_ProNumMap.containsKey(mrecord.getCreateUid())){
				throw new Exception("统计错误");
			}
			deptId_ProNumMap.get(mrecord.getCreateUid()).put("passNum", mrecord.getSumProNum());
			
			/*Map<String, Integer>  meetTypeProNumMap = deptId_ProNumMap.get(mrecord.getDepartId());
			meetTypeProNumMap.put("passNum", mrecord.getSumProNum());
			deptId_ProNumMap.put(mrecord.getDepartId(), meetTypeProNumMap);*/
		}
		for(MeetingRecord mrecord : tzjlVetoNum){
			if(!deptId_ProNumMap.containsKey(mrecord.getCreateUid())){
				throw new Exception("统计错误");
			}
			deptId_ProNumMap.get(mrecord.getCreateUid()).put("vetoNum", mrecord.getSumProNum());
		}
		
		//  记录数
		total = (long) deptId_ProNumMap.size();
		
		// 分页 预处理
		int beginIndex =  query.getPageNum() * query.getPageSize();
		int endIndex = query.getPageNum() * query.getPageSize() + query.getPageSize();
		if (total.intValue() < beginIndex) {
			return new Page<ChartDataBo>( new ArrayList<ChartDataBo>(),0l);
		}else if (total.intValue() < endIndex) {
			endIndex = total.intValue();
		}
		
		
		
		// 结果集 封装
		for(Entry<Long, Map<String, Integer>>  mapEntry : deptId_ProNumMap.entrySet()){
			
			ChartDataBo kpi = new ChartDataBo();
			
			kpi.setUserId(mapEntry.getKey());
			kpi.setRealName(uIdUserMap.get(mapEntry.getKey()).getRealName());
			
			kpi.setDepartmentId(query.getDeptid());
			kpi.setDepartmentName(thisDept.getName());
			
			int meetAllProNum = mapEntry.getValue().get("hasNum")==null?0:mapEntry.getValue().get("hasNum");
			int meetPassProNum = mapEntry.getValue().get("passNum")==null?0:mapEntry.getValue().get("passNum");
			int meetVetoProNum = mapEntry.getValue().get("vetoNum")==null?0:mapEntry.getValue().get("vetoNum");
			int meetWaitProNum = meetAllProNum - meetPassProNum - meetVetoProNum;
			
			double ghl_rate = (double)meetPassProNum / meetAllProNum;
			
			kpi.setProMeetNum(meetAllProNum);
			kpi.setPassMeetProNumber(meetPassProNum);
			kpi.setVetoMeetProNumber(meetVetoProNum);
			kpi.setWaitMeetProNumber(meetWaitProNum);
			kpi.setRate(ghl_rate);
			
			kpiDataList.add(kpi);                
		}
		
		// 排序，从大到小
		for (int i = 0; i < kpiDataList.size() - 1; i++) {
			for (int j = 0; j < kpiDataList.size() - i - 1; j++) {
				if ( kpiDataList.get(j).getRate() < kpiDataList.get(j + 1).getRate()) {
					ChartDataBo temp =  kpiDataList.get(j);
					kpiDataList.set(j, kpiDataList.get(j + 1));
					kpiDataList.set(j + 1, temp);
				}
			}
		}
		
		kpiDataList =  kpiDataList.subList(beginIndex, endIndex);
		
		kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		return kpiPage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 数据简报
	 * 投资事业线目标完成对比
	 * @param request
	 * @return
	 */
	public Page<ChartDataBo> deptProTarget(ChartKpiQuery query){
		
		Long total = 0l;
		List<ChartDataBo> kpiDataList = new ArrayList<ChartDataBo>();
		Page<ChartDataBo> kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		
		//Integer nyear = Integer.parseInt(DateUtil.getFormatDateTime(new Date(),"yyyy"));
		Integer syear = Integer.parseInt(DateUtil.dateFormat(query.getSdate().substring(0, 10),"yyyy"));
		Integer eyear = Integer.parseInt(DateUtil.dateFormat(query.getEdate().substring(0, 10),"yyyy"));
		int target = (eyear - syear + 1)*PER_YEAR_PRO_TARGET;
		
		
		// 事业线  id - name 对应
		Map<Long, String> departIdNameMap = new HashMap<Long, String>();    
		List<Department> departmentList = queryAllSYX(null);
		for(Department dep : departmentList) {
			departIdNameMap.put(dep.getId(), dep.getName());
		}
		
		total = (long) departIdNameMap.size();
		
		
		//统计 查询条件下  部门已完成的项目数
		Map<Long, Integer> deptCompletedProNuntMap = new HashMap<Long, Integer>();
		ProjectBo proOverCount1 = new ProjectBo();  //条件内完成数
		proOverCount1.setStartTime(query.getStartTime());
		proOverCount1.setEndTime(query.getEndTime());
		//proOverCount1.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		List<Project> proDepOverList = projectDao.selectDeptCompletedProNum(proOverCount1);
		for(Project pro : proDepOverList){
			deptCompletedProNuntMap.put(pro.getProjectDepartid(), pro.getCompleted()); 
		}
		
		//结果集中 各部门的投资经理的人数
		Map<Long, Integer> deptTzjlNumMap = queryDeptTzjlSum(new ArrayList<Long>(departIdNameMap.keySet()));
		
		for(Long deptId : new ArrayList<Long>(departIdNameMap.keySet())){
			
			ChartDataBo kpi = new ChartDataBo();
			
			kpi.setDepartmentId(deptId);
			kpi.setDepartmentName(departIdNameMap.get(deptId));
			
			int completed = deptCompletedProNuntMap.get(deptId) == null ? 0 : deptCompletedProNuntMap.get(deptId);
			int dept_target = (deptTzjlNumMap.get(deptId) == null ? 0 : deptTzjlNumMap.get(deptId)) * target;
			
			kpi.setCompleted(completed);
			kpi.setTarget(dept_target);
			
			kpiDataList.add(kpi);                
		}
		
		// 排序，从大到小
		for (int i = 0; i < kpiDataList.size() - 1; i++) {
			for (int j = 0; j < kpiDataList.size() - i - 1; j++) {
				if ( kpiDataList.get(j).getCompleted() < kpiDataList.get(j + 1).getCompleted()) {
					ChartDataBo temp =  kpiDataList.get(j);
					kpiDataList.set(j, kpiDataList.get(j + 1));
					kpiDataList.set(j + 1, temp);
				}
			}
		}
		
		kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		return kpiPage;
	}

	
	
	/**
	 * 数据简报
	 * 投资事业线 的   投资经理  目标完成对比
	 * @param request
	 * @return
	 */
	public Page<ChartDataBo> tzjlProTarget(ChartKpiQuery query){
		
		Long total = 0l;
		List<ChartDataBo> kpiDataList = new ArrayList<ChartDataBo>();
		Page<ChartDataBo> kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		
		//Integer nyear = Integer.parseInt(DateUtil.getFormatDateTime(new Date(),"yyyy"));
		Integer syear = Integer.parseInt(DateUtil.dateFormat(query.getSdate().substring(0, 10),"yyyy"));
		Integer eyear = Integer.parseInt(DateUtil.dateFormat(query.getEdate().substring(0, 10),"yyyy"));
		int target = (eyear - syear + 1)*PER_YEAR_PRO_TARGET;
		
		
		// 事业线  信息
		Department thisDept = departmentService.queryById(query.getDeptid());
		
		//事业线内 用户信息查询、封装
		List<User> tzjlList = queryAllTZJL(query.getDeptid());
		Map<Long, User> uIdUserMap = new HashMap<Long, User>();
		if(tzjlList == null || tzjlList.isEmpty()){
			return kpiPage;
		}
		for(User tzjl : tzjlList){
			uIdUserMap.put(tzjl.getId(), tzjl); 
		}
		
		//总 记录数
		total = (long) uIdUserMap.size();
		
		//统计 查询条件下  部门的 投资经理 完成的项目数
		Map<Long, Integer> userCompletedProNuntMap = new HashMap<Long, Integer>();
		ProjectBo proOverCount = new ProjectBo(); 
		proOverCount.setStartTime(query.getStartTime());
		proOverCount.setEndTime(query.getEndTime());
		proOverCount.setCreatetUids(new ArrayList<Long>(uIdUserMap.keySet()));
		proOverCount.setProjectDepartid(query.getDeptid());
		//proOverCount.setResultCloseFilter(DictEnum.projectStatus.YFJ.getCode()); //过滤已否决
		List<Project> proUserOverList = projectDao.selectUserCompletedProNum(proOverCount);
		for(Project pro : proUserOverList){
			userCompletedProNuntMap.put(pro.getCreateUid(), pro.getCompleted()); 
		}
		
		// 结果集 封装
		for(Long userId : new ArrayList<Long>(uIdUserMap.keySet())){
			
			ChartDataBo kpi = new ChartDataBo();
			
			kpi.setUserId(userId);
			kpi.setRealName(uIdUserMap.get(userId).getRealName());
			
			kpi.setDepartmentId(thisDept.getId());
			kpi.setDepartmentName(thisDept.getName());
			
			int completed = userCompletedProNuntMap.get(userId) == null ? 0 : userCompletedProNuntMap.get(userId);
			
			kpi.setCompleted(completed);
			kpi.setTarget(target);
			
			kpiDataList.add(kpi);                
		}
		
		// 排序，从大到小
		for (int i = 0; i < kpiDataList.size() - 1; i++) {
			for (int j = 0; j < kpiDataList.size() - i - 1; j++) {
				if ( kpiDataList.get(j).getCompleted() < kpiDataList.get(j + 1).getCompleted()) {
					ChartDataBo temp =  kpiDataList.get(j);
					kpiDataList.set(j, kpiDataList.get(j + 1));
					kpiDataList.set(j + 1, temp);
				}
			}
		}
		
		kpiPage = new Page<ChartDataBo>(kpiDataList,total);
		return kpiPage;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
