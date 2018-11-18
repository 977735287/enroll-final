package com.enroll.common.utils;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author hsc
 *
 * Jan 14, 2018
 */
public class SendPhoneCode {

	private static final String addr = "http://api.sms.cn/sms/";
	
	private static final String userId = "aaa834066457";

	private static final String pwd = "090775703dd2bdcd01b29fbd3e430b87";

	private static final String encode = "utf8";

	public void send(String code, String mobile) throws Exception {
		
		String msgContent = "您的短信验证码是" + code + "。您正在通过手机号重置登录密码，如非本人操作，请忽略该短信。【msg】";

		// 组建请求
		String straddr = addr + "?ac=send&uid=" + userId + "&pwd=" + pwd + "&mobile=" + mobile + "&encode=" + encode
				+ "&content=" + msgContent;

		StringBuffer sb = new StringBuffer(straddr);
//		System.out.println("URL:" + sb);

		// 发送请求
		URL url = new URL(sb.toString());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
//		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		// 返回结果
//		String inputline = in.readLine();
//		System.out.println("Response:" + inputline);

	}
	
}
