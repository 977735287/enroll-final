package com.enroll.common.install;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hsc
 *
 * Aug 21, 2017
 */
public class StuEntranceWay {

	List<String> getList(){
		File file = new File("files/stuEntranceWay.txt");
		List<String> list = new ArrayList<String>();
		InputStreamReader read;
		try {
			read = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(read);
			String entranceWay = null;
			while ((entranceWay = br.readLine()) != null) {
				list.add(entranceWay);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}	
	
}
