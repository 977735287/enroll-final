package com.enroll.modules.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enroll.common.utils.BackupAndRecover;
import com.enroll.common.utils.R;

/**
 * @author hsc
 *
 * Feb 14, 2018
 */
@RestController
@RequestMapping("/school/backup")
public class DBBackupController {

	@RequestMapping("backup.do")
	public R backup(String fileName, HttpServletRequest request){
		
		fileName = fileName + ".sql";
		
		String filepath = request.getSession().getServletContext().getRealPath("/download/");
				
		BackupAndRecover.backup(filepath, fileName);
		
		return R.ok().put("url", "/download/" + fileName);
	}
	
	@RequestMapping("restore.do")
	public R restore(@RequestParam MultipartFile file, HttpServletRequest request){
		
		if(!file.isEmpty()){
			String realPath = request.getSession().getServletContext().getRealPath("/download/");
			String sqlName = file.getOriginalFilename();
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, sqlName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			BackupAndRecover.restore(realPath, sqlName);
		}

		return R.ok();
	}
	
}
