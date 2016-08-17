package com.galaxyinternet.grant.service;

import org.springframework.stereotype.Service;

import com.galaxyinternet.dao.GrantActualDao;
import com.galaxyinternet.framework.core.dao.BaseDao;
import com.galaxyinternet.framework.core.service.impl.BaseServiceImpl;
import com.galaxyinternet.model.GrantActual;
import com.galaxyinternet.service.GrantActualService;

@Service("com.galaxyinternet.grant.GrantActualService")
public class GrantActualServiceImpl extends BaseServiceImpl<GrantActual> implements GrantActualService{
	
	private GrantActualDao grantActualDao;
	
	@Override
	protected BaseDao<GrantActual, Long> getBaseDao() {
		return this.grantActualDao;
	}

}
