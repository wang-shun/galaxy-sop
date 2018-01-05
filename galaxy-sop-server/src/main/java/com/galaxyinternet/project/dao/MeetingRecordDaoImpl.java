package com.galaxyinternet.project.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.galaxyinternet.bo.project.MeetingRecordBo;
import com.galaxyinternet.dao.project.MeetingRecordDao;
import com.galaxyinternet.framework.core.constants.SqlId;
import com.galaxyinternet.framework.core.dao.impl.BaseDaoImpl;
import com.galaxyinternet.framework.core.exception.DaoException;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.utils.BeanUtils;
import com.galaxyinternet.framework.core.utils.GSONUtil;
import com.galaxyinternet.model.project.MeetingRecord;


@Repository("meetingRecordDao")
public class MeetingRecordDaoImpl extends BaseDaoImpl<MeetingRecord, Long> implements MeetingRecordDao {


	@Override
	public Page<MeetingRecordBo> selectMeetPageList(MeetingRecordBo query, Pageable pageable) {
		try {
			List<MeetingRecordBo> contentList = sqlSessionTemplate.selectList(getSqlName("selectMeetPage") ,getParams(query, pageable));
			
			System.err.println("contentList==>>"+GSONUtil.toJson(contentList));
			
			return new Page<MeetingRecordBo>(contentList, pageable, selectMeetCount(query));
		} catch (Exception e) {
			throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName("selectMeetPage")), e);
		}
	}
	
	
	public Long selectMeetCount(MeetingRecordBo query) {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne(getSqlName("selectMeetCount"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName("selectMeetCount")), e);
		}
	}
	
	public Long selectMeetNumberByType(MeetingRecord query) {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne(getSqlName("selectMeetNumberByType"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName("selectMeetCount")), e);
		}
	}


	
	
	//=== report
	
	@Override
	public Long selectMeetCountByGHL(MeetingRecordBo mquery1) {
		Map<String, Object> params = BeanUtils.toMap(mquery1);
		return sqlSessionTemplate.selectOne(getSqlName("selectMeetCountByGHL"),params);
	}


	@Override
	public List<MeetingRecord> selectUserPassMeetNum(MeetingRecordBo mquery1) {
		Map<String, Object> params = BeanUtils.toMap(mquery1);
		return sqlSessionTemplate.selectList(getSqlName("selectUserPassMeetNum"),params);
	}

	@Override
	public List<MeetingRecord> selectDeptPassMeetNum(MeetingRecordBo mquery1) {
		Map<String, Object> params = BeanUtils.toMap(mquery1);
		return sqlSessionTemplate.selectList(getSqlName("selectDeptPassMeetNum"),params);
	}
	
	@Override
	public List<MeetingRecord> selectHasMeetPro(MeetingRecordBo query) {
		Map<String, Object> params = BeanUtils.toMap(query);
		return sqlSessionTemplate.selectList(getSqlName("selectDeptPassMeetNum"),params);
	}

	
	@Override
	public List<MeetingRecord> selectDeptHasMeetProNumByParams(MeetingRecordBo query) {
		Map<String, Object> params = BeanUtils.toMap(query);
		return sqlSessionTemplate.selectList(getSqlName("selectDeptHasMeetProNumByParams"),params);
	}
	
	@Override
	public List<MeetingRecord> selectTzjlHasMeetProNumByParams(MeetingRecordBo query) {
		Map<String, Object> params = BeanUtils.toMap(query);
		return sqlSessionTemplate.selectList(getSqlName("selectTzjlHasMeetProNumByParams"),params);
	}
	
	
	@Override
	public List<MeetingRecord> selectMeetFirstTimeAndPassTime(MeetingRecordBo query) {
		Map<String, Object> params = BeanUtils.toMap(query);
		return sqlSessionTemplate.selectList(getSqlName("selectMeetFirstTimeAndPassTime"),params);
	}
	
	@Override
	@Transactional
	public int updateByIdProjectId(MeetingRecord entity) {
		Assert.notNull(entity);
		appendUpdatedTime(entity);
		try {
			return sqlSessionTemplate.update(getSqlName("updateByIdProjectId"), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("根据项目ID更新对象某些属性出错！语句：%s", getSqlName("updateByIdProjectId")),
					e);
		}
	}
	private final void appendUpdatedTime(MeetingRecord entity) {
		entity.setUpdatedTime(new Date().getTime());
	}
}