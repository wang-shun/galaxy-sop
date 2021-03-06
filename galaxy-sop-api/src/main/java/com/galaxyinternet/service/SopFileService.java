package com.galaxyinternet.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;

import com.galaxyinternet.bo.sopfile.SopFileBo;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.sopfile.SopDownLoad;
import com.galaxyinternet.model.sopfile.SopFile;
import com.galaxyinternet.model.sopfile.SopVoucherFile;

/**
 * @author zhongliangzhang
 *
 */
public interface SopFileService extends BaseService<SopFile> {

	
	public List<SopFile> selectByFileTypeList(SopFileBo sbo);
	
	
	/**
	 * 通过项目ID及业务分类获取唯一档案
	 * @param sf
	 * @return
	 */
	public SopFile selectByProjectAndFileWorkType(SopFile sf);
	
	/**
	 * 签署凭证上传时业务逻辑处理
	 * @param sopFile
	 * @param sopTask
	 * @param project
	 * @return
	 */
	public SopVoucherFile updateProve(SopVoucherFile sopVoucherFile);
	

	/**
	 * 文档更新:
	 * 		删除aliyun原文件
	 * 		上传新文件
	 * 		更新sopfile表记录
	 @param request
	 @param fid  file_id
	 @return Result.Status.ERROR\OK 失败\成功
	 * */
	public Result updateFile(HttpServletRequest request, Long fid) throws Exception;
	
	/**
	 * 文档更新:
	 @param sopFile
	 @return boolean true:成功 false 失败
	 * */
	public boolean updateFile(SopFile sopFile) throws Exception;
	
	
	/**
	 * 单纯文档上传
	 * 		仅上传到aliyun
	 * @param request 转为 MultipartFile，获取key=file
	 * @param fileKey 调用OSSHelper生成的key
	 * @return MultipartFile null=上传失败
	 */	
	public Map<String, Object> aLiColoudUpload(HttpServletRequest request, String fileKey) throws Exception;
	
	
	/**
	 * 文件下载接口
	 * @param request
	 * @param response
	 * @param tempfilePath 临时存储路径
	 * @param downloadEntity 下载实体类
	 * @throws Exception
	 */
	public void download(HttpServletRequest request,HttpServletResponse response,String tempfilePath,SopDownLoad downloadEntity) throws Exception;
	
	
	/**
	 * 文件查询
	 */
	public Page<SopFile> queryFileList(SopFile query, Pageable pageable);
	
	/**
	 * 批量打包下载
	 */
	public void downloadBatch(HttpServletRequest request,HttpServletResponse response,String tempfilePath,String type,List<SopDownLoad> downloadEntityList) throws Exception;
	
	
	
	/**
	 * 项目移交时修改文档部门ID
	 */
	int updateDepartmentId(SopFile f);
	
	Page<SopFile> selectHistory(SopFile query, Pageable pageable);
	
	Long insertHistory(SopFile entity);
	
	SopFile selectHistoryById(Long id);
}
