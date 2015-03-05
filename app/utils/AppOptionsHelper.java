package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class AppOptionsHelper {

	private final static String PROPFILENAME = "prop.properties";

	private static Integer _LockSingleton = 1 ;
	private static Properties data = null ;
	
	private static Properties getData () 
	{
		synchronized ( _LockSingleton ) {
			if (data == null)
			{
				data = getProperties() ;
			}
		}
		
		return data ;
	}
	
	public static String getKey ( String key )
	{
		return getData().getProperty( key ) ;
	}
	
	private static  Properties getProperties(){
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
