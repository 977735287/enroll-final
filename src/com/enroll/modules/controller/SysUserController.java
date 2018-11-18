package com.enroll.modules.controller;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.annotation.SysLog;
import com.enroll.common.utils.PageUtils;
import com.enroll.common.utils.Query;
import com.enroll.common.utils.R;
import com.enroll.common.utils.UcpaasSender;
import com.enroll.common.validator.Assert;
import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.service.SysUserRoleService;
import com.enroll.modules.service.SysUserService;

/**
 * 系统用户
 *
 * @author hsc
 *
 *         Jul 20, 2017
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list.do")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 只有超级管理员，才能查看所有管理员列表
		// if (getUserId() != Constant.SUPER_ADMIN) {
		// params.put("createUserId", getUserId());
		// }

		// 查询列表数据
		Query query = new Query(params);
		List<SysUserEntity> userList = sysUserService.queryList(query);

		int total = sysUserService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info.do")
	public R info() {
		return R.ok().put("user", getUser());
	}

	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/password.do")
	public R password(String password, String newPassword) {
		Assert.isBlank(newPassword, "新密码不能为空");

		// sha256加密
		password = new Sha256Hash(password, getUser().getSalt()).toHex();
		// sha256加密
		newPassword = new Sha256Hash(newPassword, getUser().getSalt()).toHex();

		// 更新密码
		int count = sysUserService.updatePassword(getUserId(), password, newPassword);
		if (count == 0) {
			return R.error("原密码不正确");
		}

		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/all/info.do")
	@RequiresPermissions("sys:user:info")
	public R info(Long userId) {

		SysUserEntity user = sysUserService.queryObject(userId);

		// 获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping("/save.do")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user) {
//		ValidatorUtils.validateEntity(user, AddGroup.class);

		user.setCreateUserId(getUserId());

		sysUserService.save(user);

		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update.do")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user) {
//		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete.do")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds) {
		if (ArrayUtils.contains(userIds, 1L)) {
			return R.error("系统管理员不能删除");
		}

		if (ArrayUtils.contains(userIds, getUserId())) {
			return R.error("当前用户不能删除");
		}

		sysUserService.deleteBatch(userIds);

		return R.ok();
	}

	/**
	 * 检查用户是否存在
	 */
	@RequestMapping("/checkUsername.do")
	public R checkUsername(String username) {

		// 查询用户
		SysUserEntity user = sysUserService.queryByUserName(username);

		if (user == null) {
			return R.error("用户不存在");
		}

		return R.ok();
	}

	/**
	 * 核对用户的电子邮箱
	 */
	@RequestMapping("/checkEmail.do")
	public R checkEmail(String username, String email) {

		// 查询用户
		SysUserEntity user = sysUserService.queryByUserName(username);

		if (user == null) {
			return R.error(-1, "出现异常！请刷新页面重来");
		} else {
			if (!email.equals(user.getEmail())) {
				return R.error("请填写预留的电子邮箱");
			}
		}

		return R.ok();
	}

	/**
	 * 检查用户邮箱是否已被使用
	 */
	@RequestMapping("/checkEmailIsUse.do")
	public R checkEmailIsUse(String email) {

		// 查询用户
		SysUserEntity user = sysUserService.queryByEmail(email);

		if (user == null) {
			return R.ok();
		}
		return R.error("该邮箱已被注册，请更换邮箱！");
	}

	/**
	 * 检查手机是否已被使用
	 */
	@RequestMapping("/checkPhoneIsUse.do")
	public R checkPhoneIsUse(String phone) {

		// 查询用户
		SysUserEntity user = sysUserService.queryByPhone(phone);

		if (user == null) {
			return R.ok();
		}
		return R.error("该手机号码已被注册，请更换手机号码");
	}

	/**
	 * 发邮件重置密码
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/resetPassword.do")
	public R resetPassword(String username, String email) throws Exception {

		// 用户信息
		SysUserEntity user = sysUserService.queryByUserName(username);

		if (user == null) {
			return R.error("用户不存在");
		}

		if (!email.equals(user.getEmail())) {
			return R.error("请填写预留的电子邮箱");
		}

		String initPassword = randomNum();

		String sendMsg = user.getUsername() + " 您好，您的密码已重置，重置密码为：" + initPassword;

		int recall = sendEmail(email, sendMsg);

		if (recall == -1) {
			return R.error("密码重置失败！");
		} else if (recall == 1) {
			user.setPassword(initPassword);
			user.setRoleIdList(null);
			sysUserService.update(user);
			return R.ok("密码已重置，请登录邮箱查收！");
		} else {
			return R.error("出现异常，请联系管理员！");
		}
	}
	
	/**
	 * 找回登录密码
	 */
//	@SysLog("找回密码")
	@RequestMapping("/updatePassword.do")
	public R updatePassword(String phone, String newPassword) {
		
		SysUserEntity user = sysUserService.queryByPhone(phone);
		user.setPassword(newPassword);
		user.setRoleIdList(null);
		// 更新密码
		sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 发手机验证码
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/sendCode.do")
	public R sendCode(String phone) throws Exception {
		R r = new R();

		String code = randomNum();
		UcpaasSender.send(phone, code);
//		new SendPhoneCode().send(code, phone);

		r.put("currentCode", code);
		
		return r;
	}

	/*
	 * @return int 1:发送成功 -1:发送失败
	 */
	public int sendEmail(String email, String sendMsg) throws Exception {
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp.qq.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		// 使用JavaMail发送邮件的5个步骤
		// 1、创建session
		Session session = Session.getInstance(prop);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		session.setDebug(true);
		// 2、通过session得到transport对象
		Transport ts = session.getTransport();
		// 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
		// qq账号需要授权填写的密码为授权码，不是账号登录密码，其他如sina,163,sohu等邮箱为检测视情况而定
		// QQ邮箱需要使用SSL，端口号465或587
		ts.connect("smtp.qq.com", 587, "834066457", "tgavekvirqspbfja");
		// 4、创建邮件
		Message message;
		try {
			message = createSimpleMail(session, email, sendMsg);
			// 5、发送邮件
			ts.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ts.close();
		return 1;
	}

	public MimeMessage createSimpleMail(Session session, String email, String sendMsg) throws Exception {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("834066457@qq.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		// 邮件的标题
		message.setSubject("密码重置");
		// 邮件的文本内容
		message.setContent(sendMsg, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;

	}

	public String randomNum() {
		Random random = new Random();
		String num = "";
		for (int i = 0; i < 6; i++) {
			num += random.nextInt(10);
		}
		return num;
	}

}
