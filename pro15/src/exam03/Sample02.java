package exam03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Sample02 {

	public static void main(String[] args) {
		String userHome = System.getProperty("user.home");
		String pathName = "/eclipse/jee-2021-12/eclipse/configuration/config.ini";
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream(new File(userHome + pathName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println(prop);
		
		// 해당 키에 대한 값을 불러옴
		// get getProperty 같은거라 생각하면 됨
		System.out.println(prop.get("eclipse.product")); //object로 받음
		System.out.println(prop.getProperty("eclipse.product")); //String 으로 받음
		
		// put . setProperty 걍 같은거라 생각하면 됨
		prop.put("x", "100"); // object로 받음
		prop.put("prop", "content");
		prop.setProperty("set", "text"); //String으로 받음 
		// 새로운 properties 생성
		String newPath = "/eclipse/jee-2021-12/eclipse/configuration/config.copy";
		try {
			prop.store(new FileWriter(new File(userHome + newPath)), "Comment Write");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
