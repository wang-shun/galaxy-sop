package com.galaxyinternet.model.hologram;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.galaxyinternet.framework.core.model.PagableEntity;
import com.galaxyinternet.framework.core.utils.GSONUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class InformationData extends PagableEntity {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("项目")
	private String projectId;
	
	private int flag;//0:正常数据编辑;1:表格数据处理;2:固定表格处理
	@ApiModelProperty("普通类型")
	private List<InformationModel> infoModeList;
	@ApiModelProperty("固定表格")
	private List<FixedTableModel> infoFixedTableList;
	@ApiModelProperty("动态表格-删除的记录")
	private Set<String> deletedRowIds;  //适用table表
	@ApiModelProperty("动态表格")
	private List<TableModel>     infoTableModelList;
	
	private Set<String> deletedResultTids;  //适用result表
	
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public List<InformationModel> getInfoModeList() {
		return infoModeList == null? new ArrayList<InformationModel>():infoModeList;
	}

	public void setInfoModeList(List<InformationModel> infoModeList) {
		this.infoModeList = infoModeList;
	}

	public List<FixedTableModel> getInfoFixedTableList() {
		return infoFixedTableList;
	}

	public void setInfoFixedTableList(List<FixedTableModel> infoFixedTableList) {
		this.infoFixedTableList = infoFixedTableList;
	}

	public List<TableModel> getInfoTableModelList() {
		return infoTableModelList;
	}

	public void setInfoTableModelList(List<TableModel> infoTableModelList) {
		this.infoTableModelList = infoTableModelList;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Set<String> getDeletedRowIds() {
		return deletedRowIds;
	}

	public void setDeletedRowIds(Set<String> deletedRowIds) {
		this.deletedRowIds = deletedRowIds;
	}

	public Set<String> getDeletedResultTids() {
		return deletedResultTids;
	}

	public void setDeletedResultTids(Set<String> deletedResultTids) {
		this.deletedResultTids = deletedResultTids;
	}

	@Override
	public String toString() {
		return GSONUtil.toJson(this);
	}

	
	
	

}
