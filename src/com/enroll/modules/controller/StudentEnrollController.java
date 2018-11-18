package com.enroll.modules.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enroll.common.utils.R;
import com.enroll.modules.pojo.LengthsOfSchoolingEntity;
import com.enroll.modules.pojo.MajorEntity;
import com.enroll.modules.pojo.StuInfoEntity;
import com.enroll.modules.pojo.SysRoleEntity;
import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.pojo.UploadFileEntity;
import com.enroll.modules.service.FileUploadService;
import com.enroll.modules.service.LengthsOfSchoolService;
import com.enroll.modules.service.MajorService;
import com.enroll.modules.service.StudentEnrollService;
import com.enroll.modules.service.SysRoleService;
import com.enroll.modules.service.SysUserRoleService;
import com.enroll.modules.service.SysUserService;

/**
 * 报名信息
 * 
 * @author hsc
 *
 *         Sep 5, 2017
 */
@RestController
@RequestMapping("/school/enroll")
public class StudentEnrollController extends AbstractController {

	@Autowired
	private StudentEnrollService studentEnrollService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private MajorService majorService;

	@Autowired
	private LengthsOfSchoolService lengthsOfSchoolService;

	@Autowired
	private FileUploadService fileUploadService;

	/**
	 * 保存学生信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/save.do")
	// @RequiresPermissions("school:enroll:save")
	public R save(@RequestBody StuInfoEntity stuInfoEntity) {

		if (stuInfoEntity.getStuFamilyMember1().getName() != null) {
			studentEnrollService.save(stuInfoEntity.getStuFamilyMember1());
		} else {
			stuInfoEntity.setStuFamilyMember1(null);
		}
		if (stuInfoEntity.getStuFamilyMember2().getName() != null) {
			studentEnrollService.save(stuInfoEntity.getStuFamilyMember2());
		} else {
			stuInfoEntity.setStuFamilyMember2(null);
		}

		SysUserEntity sysUserEntity = new SysUserEntity();
		sysUserEntity.setUserId(getUserId());
		stuInfoEntity.setUser(sysUserEntity);
		studentEnrollService.save(stuInfoEntity);

		sysUserEntity.setEnrollStatus(0);
//		sysUserEntity.setMessage("尚未通过报名，请报名");
		sysUserService.update(sysUserEntity);

		return R.ok();
	}

	/**
	 * 更新学生信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/update.do")
	public R update(@RequestBody StuInfoEntity stuInfoEntity) {

		if (stuInfoEntity.getStuFamilyMember1().getId() != null) {
			studentEnrollService.update(stuInfoEntity.getStuFamilyMember1());
		} else {
			studentEnrollService.save(stuInfoEntity.getStuFamilyMember1());
		}
		if (stuInfoEntity.getStuFamilyMember2().getId() != null) {
			studentEnrollService.update(stuInfoEntity.getStuFamilyMember2());
		} else {
			studentEnrollService.save(stuInfoEntity.getStuFamilyMember2());
		}

		stuInfoEntity.setUser(null);
		studentEnrollService.update(stuInfoEntity);

		return R.ok();
	}

	/**
	 * 更新基础信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/updateUserInfo.do")
	public R updateUserInfo(String username, String email, String mobile) {

		SysUserEntity user = new SysUserEntity();

		user.setUserId(getUserId());
		user.setUsername(username);
		user.setEmail(email);
		user.setMobile(mobile);
		sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 学生信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/info.do")
	public R info(Long id) {
		
		StuInfoEntity stuInfoEntity = new StuInfoEntity();
		
		if(id == null){
			stuInfoEntity = studentEnrollService.queryByUserId(getUserId());
		}else{
			stuInfoEntity = studentEnrollService.queryObject(id);
		}

		
		if (stuInfoEntity != null) {
			if (stuInfoEntity.getStuUploadFile() != null)
				setUploadId(stuInfoEntity.getStuUploadFile().getId());
		}

		return R.ok().put("stuInfoEntity", stuInfoEntity);
	}

	/**
	 * 报名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/enroll.do")
	public R enroll() {

		SysUserEntity user = new SysUserEntity();

		user.setUserId(getUserId());
		user.setEnrollStatus(1);
//		user.setMessage("已经报名，请等待录取通知");

		sysUserService.update(user);
		
		SysUserEntity sysUserEntity = sysUserService.queryObject(getUserId());

		return R.ok().put("user", sysUserEntity);
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/userInfo.do")
	public R userInfo() {
		R r = new R();

		Map<String, Object> map = new HashMap<>();
		List<SysRoleEntity> roleList = sysRoleService.queryList(map);
		r.put("roleList", roleList);

		SysUserEntity user = sysUserService.queryObject(getUserId());

		// 获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(getUserId());
		user.setRoleIdList(roleIdList);
		r.put("userInfo", user);

		return r;
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/userList.do")
	public R userList() {
		R r = new R();

		Map<String, Object> map = new HashMap<>();
		List<SysUserEntity> userList = sysUserService.queryList(map);
		r.put("userList", userList);

		return r;
	}

	/**
	 * 信息
	 */
	@RequestMapping("/select.do")
	public R select() {
		R r = new R();

		List<MajorEntity> stuMajors = majorService.queryList(new HashMap<String, Object>());
		r.put("stuMajors", stuMajors);

		List<LengthsOfSchoolingEntity> stuLengths = lengthsOfSchoolService.queryList(new HashMap<String, Object>());
		r.put("stuLengths", stuLengths);

		return r;
	}

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

			setUploadId(uploadFile.getId());

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
	public R reUpload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {

		R r = new R();

		UploadFileEntity uf = fileUploadService.queryObject(getUploadId());

		if (uf != null) {
			String url = request.getSession().getServletContext().getRealPath("/upload/images/") + uf.getNewName();
			File file1 = new File(url);
			if (file1.exists() && file1.isFile()) {
				file1.delete();
			} else {
				System.out.println("file not exists!");
			}
		}

		UploadFileEntity uploadFileEntity = new UploadFileEntity();
		uploadFileEntity.setId(getUploadId());

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

			setUploadId(uploadFile.getId());

			r.put("uploadFile", uploadFile);

		} else {
			return R.error("上传失败！");
		}

		return r;
	}

}
