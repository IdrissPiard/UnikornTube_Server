package MYSQL;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.bdd.MysqlConnection;

public class Test {
	public static void main (String[] args)
	{
		try {
			ResultSet resultat = MysqlConnection.executeQuery("SELECT `username` FROM `users` WHERE 1") ;
			
			while ( resultat.next() ) {

			    System.out.println( resultat.getString(1) );

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
