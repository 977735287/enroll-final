package com.enroll.modules.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.modules.mapper.FileUploadDao;
import com.enroll.modules.pojo.UploadFileEntity;
import com.enroll.modules.service.FileUploadService;

/**
 * @author hsc
 *
 * Oct 22, 2017
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private FileUploadDao fileUploadDao;

	@Override
	public void save(UploadFileEntity uploadFileEntity) {
		fileUploadDao.save(uploadFileEntity);
	}

	@Override
	public void update(UploadFileEntity uploadFileEntity) {
		fileUploadDao.update(uploadFileEntity);
	}
	
	@Override
	public int deleteUF(Long id) {
		return fileUploadDao.deleteUF(id);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		fileUploadDao.deleteBatch(ids);
	}

	@Override
	public List<UploadFileEntity> queryList(Map<String, Object> map) {
		return fileUploadDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return fileUploadDao.queryTotal(map);
	}

	@Override
	public UploadFileEntity queryObject(Long id) {
		return fileUploadDao.queryObject(id);
	}
	
	@Override
	public UploadFileEntity queryByNewName(String newName) {
		return fileUploadDao.queryByNewName(newName);
	}

}
