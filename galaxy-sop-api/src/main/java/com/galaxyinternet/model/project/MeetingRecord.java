package com.galaxyinternet.model.project;

import java.text.ParseException;
import java.util.Date;

import com.galaxyinternet.framework.core.model.BaseEntity;
import com.galaxyinternet.framework.core.utils.DateUtil;

public class MeetingRecord  extends BaseEntity{
	private static final long serialVersionUID = 1L;

	 private Long projectId;

    private Long fileId;

    private Date meetingDate;
    private String meetingDateStr;

    private String meetingType;

    private String meetingResult;

    private String meetingNotes;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Date getMeetingDate() {
		if(meetingDate==null && meetingDateStr!=null && meetingDateStr.trim().length()>0){
			try {
				meetingDate = DateUtil.convertStringToDate(meetingDateStr);
			} catch (ParseException e) {
				meetingDate = null;
			}
		}
        return meetingDate;
    }
	
    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType == null ? null : meetingType.trim();
    }

    
    public String getMeetingResult() {
		return meetingResult;
	}

	public void setMeetingResult(String meetingResult) {
		this.meetingResult = meetingResult;
	}

	public String getMeetingNotes() {
        return meetingNotes;
    }

    public void setMeetingNotes(String meetingNotes) {
        this.meetingNotes = meetingNotes == null ? null : meetingNotes.trim();
    }

	public String getMeetingDateStr() {
		return meetingDateStr;
	}

	public void setMeetingDateStr(String meetingDateStr) {
		this.meetingDateStr = meetingDateStr;
	}

    
}