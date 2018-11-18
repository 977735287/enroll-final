package com.enroll.common.install;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hsc
 *
 *         Aug 20, 2017
 */
public class Area {

	Map<String, String> getAreaList() {
		File file = new File("files/area.txt");
		Map<String, String> map = new LinkedHashMap<String, String>();
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(read);
			String s = null;
			while ((s = br.readLine()) != null) {
				String code = s.substring(s.length() - 13, s.length() - 1);
				String name = s.substring(0, s.length() - 14);
				if (code.subSequence(2, code.length()).equals("0000000000")) {
					map.put(code, name);
				}
				if (!code.subSequence(2, 4).equals("00") && code.subSequence(4, code.length()).equals("00000000")) {
					String sCode = code.substring(0, 2) + "0000000000";
					name = name.substring(map.get(sCode).length(), name.length());
					map.put(code, name);
				}
				if (!code.subSequence(4, 6).equals("00") && code.subSequence(6, code.length()).equals("000000")) {
					String sCode = code.substring(0, 4) + "00000000";
					if (map.get(sCode) != null) {
						name = name.substring(map.get(sCode).length(), name.length());
					} else {
						map.put(sCode, map.get(code.substring(0, 2) + "0000000000"));
						name = name.substring(map.get(code.substring(0, 2) + "0000000000").length(), name.length());
					}
					map.put(code, name);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	int level(String code){
		
		if (code.subSequence(2, code.length()).equals("0000000000")) {
			return 0;
		}
		if (!code.subSequence(2, 4).equals("00") && code.subSequence(4, code.length()).equals("00000000")) {
			return 1;
		}
		if (!code.subSequence(4, 6).equals("00") && code.subSequence(6, code.length()).equals("000000")) {
			return 2;
		}
		
		return -1;
	}
	
	String getAddress(String code, Map<String, String> map){
		
		if (code.subSequence(2, code.length()).equals("0000000000")) {
			return map.get(code);
		}
		if (!code.subSequence(2, 4).equals("00") && code.subSequence(4, code.length()).equals("00000000")) {
			return map.get(code.subSequence(0, 2) + "0000000000") + map.get(code);
		}
		if (!code.subSequence(4, 6).equals("00") && code.subSequence(6, code.length()).equals("000000")) {
			return map.get(code.subSequence(0, 2) + "0000000000") + map.get(code.subSequence(0, 4) + "00000000") + map.get(code);
		}
		
		return null;
	}
	
	boolean parent(String code){
		if(level(code) == 1 || level(code) == 2){
			return true;
		}else{
			return false;
		}
	}
	
	String getParentCode(String code){
		if(level(code) == 1){
			return code.substring(0, 2) + "0000000000";
		}else if(level(code) == 2){
			return code.substring(0, 4) + "00000000";
		}
		return null;
	}
}
