package com.enroll.modules.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.common.utils.WordGenerator;

/**
 * @author hsc
 *
 *         Oct 10, 2017
 */
@RestController
@RequestMapping("/sys/print")
public class SysPrintController {

	@RequestMapping("/word.do")
	public String word(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> paramNames = request.getParameterNames();

		// 通过循环将表单参数放入键值对映射中
		while (paramNames.hasMoreElements()) {
			String key = paramNames.nextElement();
			String value = request.getParameter(key);
			map.put(key, value);
		}

		// 提示：在调用工具类生成word文档之前应当检查所有字段是否完整
		// 否则Freemarker的模板文件在处理时可能会因为找不到值而报错，这里暂时忽略这步
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 调用工具类WordGenerator的createDon方法生成Word文档
			file = WordGenerator.createDoc(map, "stuInfoTemplate");
			fin = new FileInputStream(file);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/msword");
			// 设置浏览器以下载的方式处理该文件默认名为***
			String filename = UUID.randomUUID().toString().replaceAll("-", "") + ".doc";
			response.addHeader("Content-Disposition", "attachment;filename=" + filename);

			out = response.getOutputStream();
			byte[] buffer = new byte[512];
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} finally {
			if (fin != null)
				fin.close();
			if (out != null)
				out.close();
			if (file != null)
				file.delete(); // 删除临时文件
		}

		return null;
	}
}
