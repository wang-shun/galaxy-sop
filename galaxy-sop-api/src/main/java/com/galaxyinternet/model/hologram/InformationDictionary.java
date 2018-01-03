package com.galaxyinternet.model.hologram;

import java.util.List;

import com.galaxyinternet.framework.core.model.PagableEntity;

public class InformationDictionary extends PagableEntity {
	private static final long serialVersionUID = 1L;
	
	private Long parentId;

	private Long titleId;

	private String code;

	private String name;
	private String nameLike;

	private String value;

	private Integer sort;

	private int isValid;

	private Long createId;

	private Long updateId;

	private Boolean checked;
	
	private List<InformationDictionary> valueList;

	private String content1;
	private String content2;


	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value == null ? null : value.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public int getIsValid() {
		return isValid;
	}

	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public Long getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Long updateId) {
		this.updateId = updateId;
	}

	public List<InformationDictionary> getValueList() {
		return valueList;
	}

	public void setValueList(List<InformationDictionary> valueList) {
		this.valueList = valueList;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}
}
