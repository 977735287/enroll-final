package com.enroll.modules.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.utils.R;
import com.enroll.modules.pojo.ArticleEntity;
import com.enroll.modules.pojo.SysUserEntity;
import com.enroll.modules.service.ArticleService;
import com.enroll.modules.service.SysUserService;
import com.enroll.modules.service.SysUserTokenService;

@RestController
@RequestMapping("/school/student")
public class StudentController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysUserTokenService sysUserTokenService;
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 登录
	 */
	@RequestMapping("/login.do")
	public Map<String, Object> login(HttpSession session, HttpServletRequest request, String username, String password) {

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
//		session = request.getSession();
//		session.setAttribute("user", user);
//		System.out.println(session.getAttribute("user"));
		r.put("user", user);
		return r;
	}
	
	/**
	 * 查询公告列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/artList.do")
	public R artList(){
		
		R r = new R();
		
		List<ArticleEntity> articleList = articleService.queryList(new HashMap<String, Object>());
		r.put("articleList", articleList);
				
		return r;
	}
}
