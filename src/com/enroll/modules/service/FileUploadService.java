package com.enroll.modules.service;

import java.util.List;
import java.util.Map;

import com.enroll.modules.pojo.UploadFileEntity;

/**
 * @author hsc
 *
 * Oct 22, 2017
 */
public interface FileUploadService {

	/**
	 * 保存文件
	 * @param uploadFileEntity
	 */
	void save(UploadFileEntity uploadFileEntity);
	
	/**
	 * 修改文件
	 * @param uploadFileEntity
	 */
	void update(UploadFileEntity uploadFileEntity);
	
	/**
	 * 删除文件
	 * @param id
	 */
	int deleteUF(Long id);
	
	/**
	 * 删除文件
	 */
	void deleteBatch(Long[] ids);
	
	/**
	 * 查询文件列表
	 * @param map
	 * @return
	 */
	List<UploadFileEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 根据文件ID，查询文件
	 * @param id
	 * @return
	 */
	UploadFileEntity queryObject(Long id);
	
	/**
	 * 根据文件名称，查询文件
	 * @param id
	 * @return
	 */
	UploadFileEntity queryByNewName(String newName);
}
