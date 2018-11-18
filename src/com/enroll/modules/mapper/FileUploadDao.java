package com.enroll.modules.mapper;

import com.enroll.modules.pojo.UploadFileEntity;

/**
 * @author hsc
 *
 * Oct 22, 2017
 */
public interface FileUploadDao extends BaseDao<UploadFileEntity>{

	UploadFileEntity queryByNewName(String newName);
	
	int deleteUF(Object id);
}
