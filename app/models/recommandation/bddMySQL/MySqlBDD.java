package models.recommandation.bddMySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.bdd.MysqlConnection;
import models.recommandation.bddInterface.BddNoSql_Int;
import models.recommandation.bddInterface.EmptyLink;
import models.recommandation.bddInterface.Noeud;

/**
 * Dao pour Recommandation
 * @author Guigui
 *
 */
public class MySqlBDD implements BddNoSql_Int{

	private static String _url = 	"jdbc:mysql://localhost:3306/fauxnosql" ; //AppOptionsHelper.getKey("MYSQLurl");
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
		Statement st = MySqlBDD.getInstance().createStatement() ;
		return ( st.executeQuery(parQuery) ) ;
	}
	
	public static int executeUpdate ( String parUpdate ) throws SQLException
	{
		Statement st = MySqlBDD.getInstance() .createStatement() ;
		return ( st.executeUpdate(parUpdate) ) ;
	}

	public void Close() throws SQLException
	{
		synchronized ( _LockSingleton ) {
			if (singletonSqlConnection == null)
			{
				return ;
			}
		}
		
		MySqlBDD.getInstance().close(); 
	}
	
	@Override
	public List<EmptyLink> getLinkOfNoeud(int parIdNoeud) {

		ArrayList<EmptyLink> locArray = new ArrayList<EmptyLink>();
	
		try {
			ResultSet resultSet = MySqlBDD.executeQuery( 
					"SELECT `noeud1`, `noeud2`, `id`, `type`, `data1`, `data2` FROM `link` WHERE `noeud1` = "+ parIdNoeud
					+ " UNION "
					+ " SELECT `noeud1`, `noeud2`, `id`, `type`, `data1`, `data2` FROM `link` WHERE `noeud2` = " + parIdNoeud);
			
			while(resultSet.next()){
				locArray.add( new EmptyLink(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return locArray;
	}

	@Override
	public Noeud getNoeudParID(int parIdNoeud) {
		Noeud locNoeud = null ;
		try {
			ResultSet resultSet = MySqlBDD.executeQuery( "SELECT `id`, `type`, `data1`, `data2` FROM `noeud` WHERE `id` = " + parIdNoeud);
			
			if (resultSet.next()){
				
				locNoeud = new Noeud(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
				locNoeud.getListeLink().addAll( this.getLinkOfNoeud(resultSet.getInt(1)));
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return locNoeud;
	}
	

	@Override
	public boolean addNoeud(Noeud parAjouter) {
		try {
			int value = MySqlBDD.executeUpdate( "INSERT INTO `noeud`(`type`, `data1`, `data2`) VALUES ('"+parAjouter.getType()+"','"+parAjouter.getType()+"','"+parAjouter.getType()+"')");
			
			return value == 1 ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return false;
	}

	@Override
	public boolean removeNoeud(Noeud parRetirer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addLink(EmptyLink parAjouter) {
		try {
			int value = MySqlBDD.executeUpdate( "INSERT INTO `link`(`noeud1`, `noeud2`, `id`, `type`, `data1`, `data2`) "
					+ "VALUES ([value-1],[value-2],a,[value-4],[value-5],[value-6])");
			
			return value == 1 ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean removeLink(EmptyLink parRetirer) {
		// TODO Auto-generated method stub
		return false;
	}

	
}