package com.galaxyinternet.model.hologram;

import com.galaxyinternet.framework.core.model.PagableEntity;
import com.galaxyinternet.framework.core.utils.CommonUtil;
import com.galaxyinternet.framework.core.utils.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
@ApiModel(value="动态表格处理",description="动态表格处理")
public class InformationListdata extends PagableEntity implements Cloneable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="分数",hidden=true)
    private Long id;
	@ApiModelProperty(value="分数",hidden=true)
    private Long projectId;

    private Long titleId;

    private Long parentId;

    private String code;

    private String field1;

    private String field2;

    private String field3;

    private String field4;

    private String field5;

    private String field6;

    private String field7;

    private String field8;

    private String field9;

    private String field10;
    
    private String field11;

    private String field12;

    private String field13;

    private String field14;

    private String field15;

    private String field16;

    private String field17;

    private String field18;

    private String field19;

    private String field20;

    private Integer isValid;
    @ApiModelProperty(value="分数",hidden=true)
    private String relateFileId;
    @ApiModelProperty(value="分数",hidden=true)
	private Long createTime;
	@ApiModelProperty(value="分数",hidden=true)
    private Long createId;
    @ApiModelProperty(value="分数",hidden=true)
    private Long updateTime;
    @ApiModelProperty(value="分数",hidden=true)
    private Long updateId;
    @ApiModelProperty(value="分数",hidden=true)
    private Set<String> titleIds;

    private List<InformationListdata> dataList;
    @ApiModelProperty(value="分数",hidden=true)
    private List<InformationListdata> workList;
    @ApiModelProperty(value="分数",hidden=true)
    private List<InformationListdata> studyList;
    @ApiModelProperty(value="分数",hidden=true)
    private List<InformationListdata> startupList;
    @ApiModelProperty(value="分数",hidden=true)
    private Long time;
    @ApiModelProperty(value="分数",hidden=true)
    private List<String> codes;
    @ApiModelProperty(value="分数",hidden=true)
    private List<InformationFile> fileList;
    @ApiModelProperty(value="分数",hidden=true)
	private Set<String> ids;
    @ApiModelProperty(value="分数",hidden=true)
    private String property;
    @ApiModelProperty(value="分数",hidden=true)
    private  String direction;
    @ApiModelProperty(value="分数",hidden=true)
    private String createUserName;
    @ApiModelProperty(value="分数",hidden=true)
    private String createTimeStr;
    @ApiModelProperty(value="分数",hidden=true)
    private String updateUserName;
    @ApiModelProperty(value="分数",hidden=true)
    private String updateTimeStr;

    //查询辅助
    @ApiModelProperty(value="分数",hidden=true)
    private Boolean field1NotNull;
    @ApiModelProperty(value="分数",hidden=true)
    private Boolean field2NotNull;
    @ApiModelProperty(value="分数",hidden=true)
    private Boolean field3NotNull;
    @ApiModelProperty(value="分数",hidden=true)
    private Boolean field4NotNull;
    @ApiModelProperty(value="分数",hidden=true)
    private Boolean field5NotNull;


    public Set<String> getTitleIds()
	{
		return titleIds;
	}

	public void setTitleIds(Set<String> titleIds)
	{
		this.titleIds = titleIds;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTitleId() {
        return titleId;
    }

    public Long getTime(){
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2){
    	if(field2 != null && StringUtils.isNotEmpty(field2)){
    		if(CommonUtil.isValidDate(field2)){
    			try {
					this.time = DateUtil.convertStringToDate(field2).getTime();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else{
    			this.time = 0l;
    		}
    	}
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public String getField6() {
        return field6;
    }

    public void setField6(String field6) {
        this.field6 = field6;
    }

    public String getField7() {
        return field7;
    }

    public void setField7(String field7) {
        this.field7 = field7;
    }

    public String getField8() {
        return field8;
    }

    public void setField8(String field8) {
        this.field8 = field8;
    }

    public String getField9() {
        return field9;
    }

    public void setField9(String field9) {
        this.field9 = field9;
    }

    public String getField10() {
        return field10;
    }

    public void setField10(String field10) {
        this.field10 = field10;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public List<InformationListdata> getWorkList() {
        return workList;
    }

    public void setWorkList(List<InformationListdata> workList) {
        this.workList = workList;
    }

    public List<InformationListdata> getStudyList() {
        return studyList;
    }

    public void setStudyList(List<InformationListdata> studyList) {
        this.studyList = studyList;
    }

    public List<InformationListdata> getStartupList() {
        return startupList;
    }

    public void setStartupList(List<InformationListdata> startupList) {
        this.startupList = startupList;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public List<String> getCodes() {
        return codes;
    }

	public Set<String> getIds() {
		return ids;
	}

	public void setIds(Set<String> ids) {
		this.ids = ids;
	}

    public void setDataList(List<InformationListdata> dataList) {
        this.dataList = dataList;
    }

    public List<InformationListdata> getDataList() {
        return dataList;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

	public String getCreateUserName()
	{
		return createUserName;
	}

	public void setCreateUserName(String createUserName)
	{
		this.createUserName = createUserName;
	}

	public String getCreateTimeStr()
	{
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr)
	{
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateUserName()
	{
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName)
	{
		this.updateUserName = updateUserName;
	}

	public String getUpdateTimeStr()
	{
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr)
	{
		this.updateTimeStr = updateTimeStr;
	}

    public Boolean getField1NotNull() {
        return field1NotNull;
    }

    public void setField1NotNull(Boolean field1NotNull) {
        this.field1NotNull = field1NotNull;
    }

    public Boolean getField2NotNull() {
        return field2NotNull;
    }

    public void setField2NotNull(Boolean field2NotNull) {
        this.field2NotNull = field2NotNull;
    }

    public Boolean getField3NotNull() {
        return field3NotNull;
    }

    public void setField3NotNull(Boolean field3NotNull) {
        this.field3NotNull = field3NotNull;
    }

    public Boolean getField4NotNull() {
        return field4NotNull;
    }

    public void setField4NotNull(Boolean field4NotNull) {
        this.field4NotNull = field4NotNull;
    }

    public Boolean getField5NotNull() {
        return field5NotNull;
    }

    public void setField5NotNull(Boolean field5NotNull) {
        this.field5NotNull = field5NotNull;
    }

    public List<InformationFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<InformationFile> fileList) {
		this.fileList = fileList;
	}

    public String getRelateFileId() {
		return relateFileId;
	}

	public void setRelateFileId(String relateFileId) {
		this.relateFileId = relateFileId;
	}

	
	public String getField11()
	{
		return field11;
	}

	public void setField11(String field11)
	{
		this.field11 = field11;
	}

	public String getField12()
	{
		return field12;
	}

	public void setField12(String field12)
	{
		this.field12 = field12;
	}

	public String getField13()
	{
		return field13;
	}

	public void setField13(String field13)
	{
		this.field13 = field13;
	}

	public String getField14()
	{
		return field14;
	}

	public void setField14(String field14)
	{
		this.field14 = field14;
	}

	public String getField15()
	{
		return field15;
	}

	public void setField15(String field15)
	{
		this.field15 = field15;
	}

	public String getField16()
	{
		return field16;
	}

	public void setField16(String field16)
	{
		this.field16 = field16;
	}

	public String getField17()
	{
		return field17;
	}

	public void setField17(String field17)
	{
		this.field17 = field17;
	}

	public String getField18()
	{
		return field18;
	}

	public void setField18(String field18)
	{
		this.field18 = field18;
	}

	public String getField19()
	{
		return field19;
	}

	public void setField19(String field19)
	{
		this.field19 = field19;
	}

	public String getField20()
	{
		return field20;
	}

	public void setField20(String field20)
	{
		this.field20 = field20;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {  
        return (InformationListdata)super.clone();  
    }  
    
}