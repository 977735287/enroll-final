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
public class Nationality {

	List<String> getList(){
		File file = new File("files/nationality.txt");
		List<String> list = new ArrayList<String>();
		InputStreamReader read;
		try {
			read = new InputStreamReader(new FileInputStream(file), "UTF-8");
			BufferedReader br = new BufferedReader(read);
			String nationality = null;
			while ((nationality = br.readLine()) != null) {
				list.add(nationality);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}	
}
