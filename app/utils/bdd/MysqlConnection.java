package utils.bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

//import utils.AppOptionsHelper;

/**
 * Dao
 * @author Guigui
 *
 */
public class MysqlConnection   {

	private static String _url = 	"jdbc:mysql://localhost:3306/unikorntubesql" ; //AppOptionsHelper.getKey("MYSQLurl");
	private static String _user = 	"root" ;// AppOptionsHelper.getKey("MYSQLuser");
	private static String _passwd = "" ;  //   AppOptionsHelper.getKey("MYSQLpasswd");

	private static Integer _LockSingleton = 1 ;
	
	private static Connection singletonSqlConnection ;
	
	public static Connection getInstance ()
	{
		synchronized ( _LockSingleton ) {
			if (singletonSqlConnection == null)
			{
				try {
					singletonSqlConnection = DriverManager.getConnection( _url, _user, _passwd ) ;
				} catch (Exception e) {
					e.printStackTrace();
					
					System.exit( -1 );
				}

			}
		}
		
		return singletonSqlConnection ;
	}
		
	public static ResultSet executeQuery ( String parQuery ) throws SQLException
	{
		Statement st = MysqlConnection.getInstance().createStatement() ;
		return ( st.executeQuery(parQuery) ) ;
	}
	
	public static int executeUpdate ( String parUpdate ) throws SQLException
	{
		Statement st = MysqlConnection.getInstance() .createStatement() ;
		return ( st.executeUpdate(parUpdate) ) ;
	}
	
	public static ResultSet executeUpdateGetResult ( String parUpdate ) throws SQLException
	{
		Statement st = MysqlConnection.getInstance() .createStatement() ;
		if(st.executeUpdate(parUpdate) > 0)
			return st.getGeneratedKeys();
		else return null;
	}

	public void Close() throws SQLException
	{
		synchronized ( _LockSingleton ) {
			if (singletonSqlConnection == null)
			{
				return ;
			}
		}
		
		MysqlConnection.getInstance().close(); 
	}
}