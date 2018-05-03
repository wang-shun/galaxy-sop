package com.galaxyinternet.model.touhou;

import java.util.List;

import com.galaxyinternet.common.enums.EnumUtil;
import com.galaxyinternet.framework.core.model.PagableEntity;

import io.swagger.annotations.ApiModel;
@ApiModel(value="健康度对象",description="项目健康度")
public class ProjectHealth extends PagableEntity {
	private static final long serialVersionUID = 1L;

    private Long projectId;

    private Byte healthState;
    private String healthStateStr;

    private String rematk;

    private String userName;
    
    private Long createdUid;

    private Long updatedUid;

  //健康
  	private Integer healthGoodNum;
  	//健康预警
  	private Integer healthWarnNum;
  	//高于预期
  	private Integer healthHighNum;
  	
  	//高于预期
  	private String flagUrl;
  	
    private String projectName;
    
    private Long depId;
    
    private String projectCareerline;
    
   private Integer isDelete;
   
   private List<String> createUids;
    
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Byte getHealthState() {
        return healthState;
    }

    public void setHealthState(Byte healthState) {
    	if(healthState!=null){
    		if(healthState == 0){
    			healthStateStr = "风险";
    		}if(healthState == 1){
				healthStateStr = "优异";
			}else if(healthState == 2){
				healthStateStr = "健康";
			}else if(healthState == 3){
				healthStateStr = "关注";
			}else if(healthState == 4){
				healthStateStr = "损失";
			}
		}
        this.healthState = healthState;
    }

    public String getRematk() {
        return rematk;
    }

    public void setRematk(String rematk) {
        this.rematk = rematk == null ? null : rematk.trim();
    }


    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getCreatedUid() {
		return createdUid;
	}

	public void setCreatedUid(Long createdUid) {
		this.createdUid = createdUid;
	}

	public Long getUpdatedUid() {
        return updatedUid;
    }

    public void setUpdatedUid(Long updatedUid) {
        this.updatedUid = updatedUid;
    }

	public String getHealthStateStr() {
		return healthStateStr;
	}

	public void setHealthStateStr(String healthStateStr) {
		this.healthStateStr = healthStateStr;
	}

	public Integer getHealthGoodNum() {
		return healthGoodNum;
	}

	public void setHealthGoodNum(Integer healthGoodNum) {
		this.healthGoodNum = healthGoodNum;
	}

	public Integer getHealthWarnNum() {
		return healthWarnNum;
	}

	public void setHealthWarnNum(Integer healthWarnNum) {
		this.healthWarnNum = healthWarnNum;
	}

	public Integer getHealthHighNum() {
		return healthHighNum;
	}

	public void setHealthHighNum(Integer healthHighNum) {
		this.healthHighNum = healthHighNum;
	}

	public String getFlagUrl() {
		return flagUrl;
	}

	public void setFlagUrl(String flagUrl) {
		this.flagUrl = flagUrl;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.projectCareerline = EnumUtil.getCareerline(depId);

		this.depId = depId;
	}

	public String getProjectCareerline() {
		return projectCareerline;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public List<String> getCreateUids() {
		return createUids;
	}

	public void setCreateUids(List<String> createUids) {
		this.createUids = createUids;
	}

	


	
	
	
}