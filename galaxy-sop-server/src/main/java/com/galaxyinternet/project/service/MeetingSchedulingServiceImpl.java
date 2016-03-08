package com.galaxyinternet.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxyinternet.bo.project.MeetingSchedulingBo;
import com.galaxyinternet.dao.project.MeetingSchedulingDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.project.MeetingScheduling;
import com.galaxyinternet.model.project.Project;
import com.galaxyinternet.service.MeetingSchedulingService;
import com.galaxyinternet.service.ProjectService;


@Service("com.galaxyinternet.service.MeetingSchedulingService")
public class MeetingSchedulingServiceImpl extends BaseServiceImpl<MeetingScheduling> implements MeetingSchedulingService {

	@Autowired
	private MeetingSchedulingDao meetingSchedulingDao;
	
	@Autowired
	private ProjectService projectService;
	
	@Override
	protected BaseDao<MeetingScheduling, Long> getBaseDao() {
		return this.meetingSchedulingDao;
	}

	@Override
	public int updateCountBySelective(MeetingScheduling entity) {
		return meetingSchedulingDao.updateCountBySelective(entity);
	}

	@Override
	public List<MeetingSchedulingBo> selectTop5ProjectMeetingByType(
			String type) {
		 List<MeetingSchedulingBo> meetingList = meetingSchedulingDao.selectTop5ProjectMeetingByType(type);
		 List<Project> projectList = projectService.queryAll();
		 for (MeetingSchedulingBo meeting : meetingList) {
			 for (Project project :projectList)   {
				 if (meeting.getProjectId() == project.getId()) {
					 meeting.setProjectName(project.getProjectName());
				 }
			 }
		 }
		 
		 return meetingList;
	}

	@Override
	public List<MeetingSchedulingBo> selectProjectMeetingByType(String type) {
		 List<MeetingSchedulingBo> meetingList = meetingSchedulingDao.selectProjectMeetingByType(type);
		 List<Project> projectList = projectService.queryAll();
		 for (MeetingSchedulingBo meeting : meetingList) {
			 for (Project project :projectList)   {
				 if (meeting.getProjectId() == project.getId()) {
					 meeting.setProjectName(project.getProjectName());
				 }
			 }
		 }
		 
		 return meetingList;
	}

}