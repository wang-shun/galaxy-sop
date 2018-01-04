package com.galaxyinternet.model.DongNao;


import com.galaxyinternet.common.enums.DictEnum;
import com.galaxyinternet.framework.core.model.BaseEntity;
import com.galaxyinternet.framework.core.model.PagableEntity;
import com.galaxyinternet.framework.core.utils.DateUtil;
import com.galaxyinternet.framework.core.utils.NumberUtils;
import com.galaxyinternet.model.hologram.InformationTitle;
import com.galaxyinternet.model.project.FinanceHistory;
import com.galaxyinternet.model.project.JointDelivery;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.List;

public class DnProject extends BaseEntity {
	private static final long serialVersionUID = 1L;

	//升序:asc 降序:desc ,
	private String order;
	//排序字段
	private String orderBy;
	//当前页码
	private Integer pageNo;
	//每页记录数
	private Integer pageSize;

	//搜索关键字
	//private String keyword;

	//最新融资金额
	private String latestFinanceAmountStr;
	//最新融资时间
	private String latestFinanceDT;
	//最新融资轮次
	private String latestFinanceRound;


    //大脑项目id
	//private Long id;

	//项目logo    如无logo，显示项目通用的无图效果
	//项目图片地址:http://static.galaxyinternet.com/img/project/+projCode+.png ,
	//项目code
	private String projCode;
	private String projImage;

	private String compCode; //==sourceCode

	//项目名称 ：  项目标题 ,
	private String projTitle;

	//所在行业    没有则不显示
	private String industryName;

	//所在地区    没有则不显示
	private String districtSubName;


	//公司全称
	private String projCompanyName;

	//成立时间
	private String setupDT;


	/*
	 * 星河投项目id ：  projId
     * 报告题code ：   titleCode
	*/
	private Long projId;
	private String titleCode;


	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/*public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}*/

	public String getLatestFinanceAmountStr() {
		return latestFinanceAmountStr;
	}

	public void setLatestFinanceAmountStr(String latestFinanceAmountStr) {
		this.latestFinanceAmountStr = latestFinanceAmountStr;
	}

	public String getLatestFinanceDT() {
		return latestFinanceDT;
	}

	public void setLatestFinanceDT(String latestFinanceDT) {
		this.latestFinanceDT = latestFinanceDT;
	}

	public String getLatestFinanceRound() {
		return latestFinanceRound;
	}

	public void setLatestFinanceRound(String latestFinanceRound) {
		this.latestFinanceRound = latestFinanceRound;
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getProjImage() {
		return projImage;
	}

	public void setProjImage(String projImage) {
		this.projImage = projImage;
	}

	public String getProjTitle() {
		return projTitle;
	}

	public void setProjTitle(String projTitle) {
		this.projTitle = projTitle;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getDistrictSubName() {
		return districtSubName;
	}

	public void setDistrictSubName(String districtSubName) {
		this.districtSubName = districtSubName;
	}

	public String getProjCompanyName() {
		return projCompanyName;
	}

	public void setProjCompanyName(String projCompanyName) {
		this.projCompanyName = projCompanyName;
	}

	public String getSetupDT() {
		return setupDT;
	}

	public void setSetupDT(String setupDT) {
		this.setupDT = setupDT;
	}

	public String getCompCode() {
		return compCode;
	}

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public Long getProjId() {
		return projId;
	}

	public void setProjId(Long projId) {
		this.projId = projId;
	}

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
}