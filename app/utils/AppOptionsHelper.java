package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppOptionsHelper {

	private final static String PROPFILENAME = "prop.properties";

	public static  Properties getProperties(){
		Properties locProp = new Properties();

		InputStream locInput = null;

		try {
			locInput = AppOptionsHelper.class.getClassLoader().getResourceAsStream(PROPFILENAME);
			if(locInput==null){
				//TODO: replacer syso par logger
				System.out.println("Sorry, unable to find " + PROPFILENAME);
				throw new FileNotFoundException();
			}

			//load a properties file from class path, inside static method
			locProp.load(locInput);
			return locProp;
		} catch (IOException ex) {
			//TODO: logger printstacktrace
			ex.printStackTrace();
			return null;
		} finally{
			if(locInput!=null){
				try {
					locInput.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

	}

}
