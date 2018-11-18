package com.enroll.modules.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enroll.common.utils.R;
import com.enroll.modules.pojo.UploadFileEntity;
import com.enroll.modules.service.FileUploadService;

/**
 * 文件上传
 * 
 * @author hsc
 *
 *         Oct 7, 2017
 */
@RestController
@RequestMapping("/school/upload")
public class FileUploadController extends AbstractController {

	@Autowired
	private FileUploadService fileUploadService;

	/**
	 * 上传
	 * 
	 * @param file
	 * @return R
	 */
	@RequestMapping("/upload.do")
	public R upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

		R r = new R();
		UploadFileEntity uploadFileEntity = new UploadFileEntity();

		if (!file.isEmpty()) {
			uploadFileEntity.setOriginalName(file.getOriginalFilename());
			uploadFileEntity.setFileSize(file.getSize());
			uploadFileEntity.setFileType(file.getContentType());
			uploadFileEntity.setCreateTime(new Date());

			String newName = UUID.randomUUID().toString().replaceAll("-", "")
					+ file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
			// String realPath = "E://fileUpload//2//";
			String realPath = request.getSession().getServletContext().getRealPath("/upload/images/");
			String url = "/upload/images/" + newName;

			uploadFileEntity.setNewName(newName);
			uploadFileEntity.setUrl(url);

			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, newName));

			fileUploadService.save(uploadFileEntity);

			UploadFileEntity uploadFile = fileUploadService.queryByNewName(newName);

			r.put("uploadFile", uploadFile);

		} else {
			return R.error("上传失败！");
		}

		return r;
	}

	/**
	 * 重新上传
	 * 
	 * @param file
	 * @return R
	 */
	@RequestMapping("/reUpload.do")
	public R reUpload(@RequestParam MultipartFile file, Long uploadId, HttpServletRequest request) throws IOException {

		R r = new R();

		UploadFileEntity uf = fileUploadService.queryObject(uploadId);
		
		if (uf != null) {
			File file1 = new File(request.getSession().getServletContext().getRealPath("/upload/images/")
					+ uf.getNewName());
			if (file1.exists() && file1.isFile()) {
				file1.delete();
			} else {
				System.out.println("file not exists!");
			}
		}

		UploadFileEntity uploadFileEntity = new UploadFileEntity();
		uploadFileEntity.setId(uploadId);

		if (!file.isEmpty()) {
			uploadFileEntity.setOriginalName(file.getOriginalFilename());
			uploadFileEntity.setFileSize(file.getSize());
			uploadFileEntity.setFileType(file.getContentType());
			uploadFileEntity.setCreateTime(new Date());

			String newName = UUID.randomUUID().toString().replaceAll("-", "")
					+ file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
			// String realPath = "E://fileUpload//2//";
			String realPath = request.getSession().getServletContext().getRealPath("/upload/images/");
			String url = "/upload/images/" + newName;

			uploadFileEntity.setNewName(newName);
			uploadFileEntity.setUrl(url);

			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, newName));

			fileUploadService.update(uploadFileEntity);

			UploadFileEntity uploadFile = fileUploadService.queryByNewName(newName);

			r.put("uploadFile", uploadFile);

		} else {
			return R.error("上传失败！");
		}

		return r;
	}

}
