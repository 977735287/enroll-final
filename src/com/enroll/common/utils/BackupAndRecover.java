package com.enroll.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * @author hsc
 *
 *         Feb 14, 2018
 */
public class BackupAndRecover {
	/*
	 * 备份数据库 1、读取配置文件 2、启动智能查询Mysql安装目录 3、备份数据库为sql文件
	 */
	@SuppressWarnings("static-access")
	public static void backup(String filepath, String sqlName) {

		Properties pros = getPprVue("dbBackup.properties");
		String username = pros.getProperty("username");
		String password = pros.getProperty("password");
		String databaseName = pros.getProperty("databaseName");
		String address = pros.getProperty("address");
		String sqlpath = pros.getProperty("sqlpath");

		File backupath = new File(sqlpath);
		if (!backupath.exists()) {
			backupath.mkdir();
		} else if (backupath.listFiles() != null) {
			try {
				FileUtils.cleanDirectory(backupath); // 清空当前文件夹内所有文件，不删除当前文件夹
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (new File(filepath).listFiles() != null) {
			try {
				FileUtils.cleanDirectory(new File(filepath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		CheckSoftware c = null;
		String mysqlpaths;
		try {
			mysqlpaths = c.check().toString() + "\\bin\\";

			StringBuffer sb = new StringBuffer();

			sb.append(mysqlpaths);
			sb.append("mysqldump ");
			sb.append("--opt ");
			sb.append("-h ");
			sb.append(address);
			sb.append(" ");
			sb.append("--user=");
			sb.append(username);
			sb.append(" ");
			sb.append("--password=");
			sb.append(password);
			sb.append(" ");
			sb.append("--lock-all-tables=true ");
			sb.append("--result-file=");
			sb.append(sqlpath);
			sb.append(sqlName);
			sb.append(" ");
			sb.append("--default-character-set=utf8 ");
			sb.append(databaseName);
			sb.append(" ");
			sb.append(" ");
			Runtime cmd = Runtime.getRuntime();
			try {
				Process p = cmd.exec(sb.toString());
				p.waitFor(); // 等待当前进程结束为止
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			FileUtils.copyFile(new File(sqlpath + sqlName), new File(filepath + sqlName));
			// if(new File(sqlpath).listFiles() != null){
			// FileUtils.cleanDirectory(new File(sqlpath));
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 表备份 1、读取配置文件 2、启动智能查询Mysql安装目录 3、备份数据库为sql文件
	 */
	@SuppressWarnings("static-access")
	public static void tableBackup(String sqlName, String tableName, String filepath) {

		Properties pros = getPprVue("dbBackup.properties");
		String username = pros.getProperty("username");
		String password = pros.getProperty("password");
		String databaseName = pros.getProperty("databaseName");
		String address = pros.getProperty("address");
		String sqlpath = pros.getProperty("sqlpath");

		File backupath = new File(sqlpath);
		if (!backupath.exists()) {
			backupath.mkdir();
		} else if (backupath.listFiles() != null) {
			try {
				FileUtils.cleanDirectory(backupath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		CheckSoftware c = null;
		String mysqlpaths;
		try {
			mysqlpaths = c.check().toString() + "\\bin\\";

			StringBuffer sb = new StringBuffer();

			sb.append(mysqlpaths);
			sb.append("mysqldump ");
			sb.append("--opt ");
			sb.append("-h ");
			sb.append(address);
			sb.append(" ");
			sb.append("--user=");
			sb.append(username);
			sb.append(" ");
			sb.append("--password=");
			sb.append(password);
			sb.append(" ");
			sb.append("--lock-all-tables=true ");
			sb.append("--result-file=");
			sb.append(sqlpath);
			sb.append(sqlName);
			sb.append(" ");
			sb.append("--default-character-set=utf8 ");
			sb.append(databaseName);
			sb.append(" ");
			sb.append(tableName);
			Runtime cmd = Runtime.getRuntime();
			try {
				Process p = cmd.exec(sb.toString());
				p.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			FileUtils.copyFile(new File(sqlpath + sqlName), new File(filepath + sqlName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 读取属性文件
	 */
	public static Properties getPprVue(String properName) {

		InputStream inputStream = BackupAndRecover.class.getClassLoader().getResourceAsStream(properName);
		Properties p = new Properties();

		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return p;

	}

	/*
	 * 根据备份文件恢复数据库
	 */
	@SuppressWarnings("static-access")
	public static void load(String filename) throws Exception {
		Properties pros = getPprVue("dbBackup.properties");
		String root = pros.getProperty("username");
		String pass = pros.getProperty("password");
		CheckSoftware c = null;
		String mysqlpaths = c.check().toString() + "\\bin\\";
		String sqlpath = pros.getProperty("sqlpath");
		String filepath = sqlpath + filename; // 备份的路径地址

		String stmt1 = mysqlpaths + "mysqladmin -u " + root + " -p " + pass + " create finacing"; // -p后面加的是你的密码
		String stmt2 = mysqlpaths + "mysql -u " + root + " -p " + pass + " finacing < " + filepath;
		System.out.println(stmt1 + "\n" + stmt2);
		String[] cmd = { "cmd", "/c", stmt2 };
		try {
			Runtime.getRuntime().exec(stmt1);
			Runtime.getRuntime().exec(cmd);
			// System.out.println("数据已从 " + filepath + " 导入到数据库中");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public static void restore(String uploadPath, String filename) {

		Properties pros = getPprVue("dbBackup.properties");
		String root = pros.getProperty("username");
		String pass = pros.getProperty("password");
		String address = pros.getProperty("address");
		String databaseName = pros.getProperty("databaseName");
		String sqlpath = pros.getProperty("sqlpath");
		String filepath = sqlpath + filename; // 备份的路径地址
		CheckSoftware c = null;
		String mysqlpaths = null;
		try {
			mysqlpaths = c.check().toString() + "\\bin\\";
			if (new File(sqlpath).listFiles() != null) {
				FileUtils.cleanDirectory(new File(sqlpath));
			}

			FileUtils.copyFile(new File(uploadPath + filename), new File(filepath));

			if (new File(uploadPath).listFiles() != null) {
				FileUtils.cleanDirectory(new File(uploadPath));
			}

			String stmt = mysqlpaths + "mysql -h" + address + " -u" + root + " -p" + pass
					+ " --default-character-set=utf8 " + databaseName;
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(stmt);
			OutputStream outputStream = process.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "utf-8"));
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = br.readLine()) != null) {
				sb.append(str + "\r\n");
			}
			str = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
			writer.write(str);
			writer.flush();
			outputStream.close();
			br.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
