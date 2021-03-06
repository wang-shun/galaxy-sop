package com.galaxyinternet.service.hologram;

import java.util.List;

import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.common.Node;
import com.galaxyinternet.model.hologram.InformationResult;
import com.galaxyinternet.model.project.MeetingRecord;
import com.galaxyinternet.model.project.Project;

public interface InformationResultService extends BaseService<InformationResult>{
	
	public int upResultByMeeting(MeetingRecord mr);
	
	public void updateOrInsert(InformationResult selectById,String contentChoose);
	
	public void updateRejestResut(Project project );
	
	public String meetingResult(Long projectId,String passResult,String meetingType);
	
	List<Node> selectResults(InformationResult ir);
	
	
    
}
