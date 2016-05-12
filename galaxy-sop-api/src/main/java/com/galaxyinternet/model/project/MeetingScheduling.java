package com.galaxyinternet.model.project;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.galaxyinternet.framework.core.model.PagableEntity;
import com.galaxyinternet.framework.core.utils.DateUtil;

public class MeetingScheduling extends PagableEntity {
	private static final long serialVersionUID = 1L;

    private Long projectId;

    private String meetingType;

    private Integer meetingCount;

    private Date meetingDate;

    private String status;

    private String remark;
    private Integer scheduleStatus;
    private Timestamp applyTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reserveTimeStart;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reserveTimeEnd;
    
    private Timestamp lastTime;
    //排序字段名称
    private String sortName;
    //排序方式
    private String sortDirection;
    //过滤字段
    private String filterName;
    
    private  List<Long> deptIdList;
    private List<Long> projectIdList;
    //项目名称
  	private String projectName;
  	//投资事业线
  	private String projectCareerline;
  	//投资经理
    private String createUname;
    private String meetingDateStr; 
    private String applyTimeStr;
    
    private String reserveTimeStartStr;
    private String reserveTimeEndStr;
    //投资事业线部门id
    private Integer careline;
    
    
    //申请时间(申请立项会时间)
    private  Date meetTime;
    
    //项目代码
    private String projectCode;
    
    //权限部分
    private byte isEdit;
    
    
    private Double meetingRate;
    
   

	public Date getReserveTimeEnd() {
		return reserveTimeEnd;
	}

	public void setReserveTimeEnd(Date reserveTimeEnd) {
		this.reserveTimeEnd = reserveTimeEnd;
		if(reserveTimeEnd != null){
			reserveTimeEndStr = DateUtil.convertDateToStringForChina(reserveTimeEnd);
        }
	}

	public String getReserveTimeStartStr() throws ParseException {
		return reserveTimeStartStr;
	}

	public void setReserveTimeStartStr(String reserveTimeStartStr) throws ParseException {
		this.reserveTimeStartStr = reserveTimeStartStr;
	}

	public String getReserveTimeEndStr() {
		return reserveTimeEndStr;
	}

	public void setReserveTimeEndStr(String reserveTimeEndStr) {
		this.reserveTimeEndStr = reserveTimeEndStr;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getMeetingDateStr() {
		return meetingDateStr;
	}

	public void setMeetingDateStr(String meetingDateStr) {
		this.meetingDateStr = meetingDateStr;
	}

	public String getApplyTimeStr() {
		return applyTimeStr;
	}

	public void setApplyTimeStr(String applyTimeStr) {
		this.applyTimeStr = applyTimeStr;
	}


	public String getProjectCareerline() {
  		return projectCareerline;
  	}

  	public void setProjectCareerline(String projectCareerline) {
  		this.projectCareerline = projectCareerline;
  	}

  	public String getCreateUname() {
  		return createUname;
  	}

  	public void setCreateUname(String createUname) {
  		this.createUname = createUname;
  	}

  	public String getProjectName() {
  		return projectName;
  	}

  	public void setProjectName(String projectName) {
  		this.projectName = projectName;
  	}

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType == null ? null : meetingType.trim();
    }

    public Integer getMeetingCount() {
        return meetingCount;
    }

    public void setMeetingCount(Integer meetingCount) {
        this.meetingCount = meetingCount;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
        if(meetingDate != null){
        	meetingDateStr = DateUtil.convertDateToStringForChina(meetingDate);
        }
    }
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Integer getScheduleStatus() {
		return scheduleStatus;
	}

	public void setScheduleStatus(Integer scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

	public Timestamp getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
		if(applyTime != null){
			applyTimeStr = DateUtil.convertDateToStringForChina(applyTime);
        }
	}

	public Date getReserveTimeStart() {
		return reserveTimeStart;
	}

	public void setReserveTimeStart(Date reserveStartTime) {
		this.reserveTimeStart = reserveStartTime;
		if(reserveStartTime != null){
			reserveTimeStartStr = DateUtil.convertDateToStringForChina(reserveTimeStart);
        }
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public List<Long> getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(List<Long> deptIdList) {
		this.deptIdList = deptIdList;
	}

	public List<Long> getProjectIdList() {
		return projectIdList;
	}

	public void setProjectIdList(List<Long> projectIdList) {
		this.projectIdList = projectIdList;
	}

	public Date getMeetTime() {
		return meetTime;
	}

	public void setMeetTime(Date meetTime) {
		this.meetTime = meetTime;
	}
	public byte getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(byte isEdit) {
		this.isEdit = isEdit;
	}

	public Integer getCareline() {
		return careline;
	}

	public void setCareline(Integer careline) {
		this.careline = careline;
	}

	public Double getMeetingRate() {
		return meetingRate;
	}

	public void setMeetingRate(Double meetingRate) {
		this.meetingRate = meetingRate;
	}
	
	
}