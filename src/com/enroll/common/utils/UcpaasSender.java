package com.enroll.common.utils;

import com.enroll.common.utils.ucpaas.UcpaasSDK;

/**
 * @author hsc
 *
 *         May 7, 2018
 */
public class UcpaasSender {

	private static final String sid = "ec44d1e058543f131fee98791d1ecd63";
	private static final String token = "24deba13f6f7459cae5e20d5fcf95049";
	private static final String appid = "ddaa6cc6a6394dd4aa990665fd849d40";
	private static final String uid = "2d92c6132139467b989d087c84a365d8";
	
	private static final String smsTemplate = "309414";

	public static String send(String phone, String... datas) {
		String param = "";
		for (String data : datas) {
			param += data;
		}
		return UcpaasSDK.sendSms(sid, token, appid, smsTemplate, param, phone, uid);
	}

//	public static void main(String[] args) {
//		send("15289599685", "309414", "868686");
//	}

}