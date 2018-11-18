package com.enroll.modules.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.utils.R;
import com.enroll.common.utils.ShiroUtils;
import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.service.SysUserService;
import com.enroll.modules.service.SysUserTokenService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * @author hsc
 *
 *         Jul 19, 2017
 */

@RestController
public class SysLoginController extends AbstractController {

	@Autowired
	private Producer producer;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserTokenService sysUserTokenService;

	/**
	 * 验证码
	 */
	@RequestMapping("captcha.do")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@RequestMapping("/sys/login.do")
	public Map<String, Object> login(String username, String password, String captcha) {

		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		
		if (!captcha.equalsIgnoreCase(kaptcha)) {
			return R.error("验证码不正确");
		}

		// 用户信息
		SysUserEntity user = sysUserService.queryByUserName(username);

		// 用户不存在
		if (user == null) {
			user = sysUserService.queryByPhone(username);
			if (user == null) {
				user = sysUserService.queryByEmail(username);
				if (user == null) {
					return R.error("该账号不存在");
				}
			}
		}

		password = new Sha256Hash(password, user.getSalt()).toHex();

		// 账号不存在、密码错误
		if (!user.getPassword().equals(password)) {
			return R.error("用户名或密码不匹配，请重新输入");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			return R.error("账号已被锁定,请联系管理员");
		}

		// 生成token，并保存到数据库
		R r = sysUserTokenService.createToken(user.getUserId());
		r.put("user", user);
		return r;
	}
	
	/**
	 * 游客访问
	 */
	@RequestMapping("/sys/touristVisit.do")
	public Map<String, Object> touristVisit(String phone) {
		System.out.println("/sys/touristVisit.do");
		R r = new R();
		SysUserEntity user = new SysUserEntity();
		List<Long> roleIdList = new ArrayList<Long>();
		roleIdList.add(3L);
		user.setUsername("游客(" + phone + ")");
		user.setPassword("123456");
		user.setMobile(phone);
		user.setEnrollStatus(0);
		user.setStatus(1);
		user.setCreateUserId(1L);
		user.setRoleIdList(roleIdList);
		sysUserService.save(user);
		user = sysUserService.queryByPhone(phone);
		r = sysUserTokenService.createToken(user.getUserId());
		r.put("user", user);
		return r;
	}

	/**
	 * 退出登录
	 */
	@RequestMapping("/sys/logout.do")
	public Map<String, Object> logout() {
		return R.ok();
	}

//	public static void main(String[] args){
//		System.out.println(new Random().nextInt(99999));
//	}
}
