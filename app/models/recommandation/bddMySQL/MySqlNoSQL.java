package models.recommandation.bddMySQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.recommandation.SauvegardeParcours;
import models.recommandation.bddInterface.BddNoSQLMere;
import models.recommandation.bddInterface.BddNoSql_Int;
import models.recommandation.bddInterface.EmptyLink;
import models.recommandation.bddInterface.Noeud;

/**
 * Dao pour Recommandation
 * @author Guigui
 *
 */
public class MySqlNoSQL extends BddNoSQLMere{

	public static MySqlNoSQL _Singleton = new MySqlNoSQL();

	private static String _url = 	"jdbc:mysql://localhost:3306/fauxnosql" ; //AppOptionsHelper.getKey("MYSQLurl");
	private static String _user = 	"root" ;// AppOptionsHelper.getKey("MYSQLuser");
	private static String _passwd = "" ;  //   AppOptionsHelper.getKey("MYSQLpasswd");

	private static Integer _LockSingleton = 1 ;

	private static Connection singletonSqlConnection ;

	private static void write(String Message)
	{
		System.out.println("FAUXNOSQL : " + Message);
	}

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
		write("executeQuery : \n" + parQuery + "\n");
		Statement st = MySqlNoSQL.getInstance().createStatement() ;
		return ( st.executeQuery(parQuery) ) ;
	}

	public static int executeUpdate ( String parUpdate ) throws SQLException
	{
		write("executeUpdate : \n" + parUpdate + "\n");
		Statement st = MySqlNoSQL.getInstance() .createStatement() ;
		return ( st.executeUpdate(parUpdate) ) ;
	}
	
	public static int executeOneInsert ( String parInsert ) throws SQLException
	{
		write("executeUpdate : \n" + parInsert + "\n");
		Statement st = MySqlNoSQL.getInstance() .createStatement() ;
		if ( st.executeUpdate(parInsert , Statement.RETURN_GENERATED_KEYS) > 0)
		{
			ResultSet rs = st.getGeneratedKeys();
			if ( rs.next())
			{
				return rs.getInt(1);
			}
		}
		return -1 ;
	}

	public void Close() throws SQLException
	{
		synchronized ( _LockSingleton ) {
			if (singletonSqlConnection == null)
			{
				return ;
			}
		}

		MySqlNoSQL.getInstance().close(); 
	}

	private List<EmptyLink> getLinkOfNoeud(int parIdNoeud) {

		ArrayList<EmptyLink> locArray = new ArrayList<EmptyLink>();

		try {
			ResultSet resultSet = MySqlNoSQL.executeQuery( 
					"SELECT `noeud1`, `noeud2`, `id`, `type`, `data1`, `data2` FROM `link` WHERE `noeud1` = "+ parIdNoeud
					+ " UNION "
					+ " SELECT `noeud1`, `noeud2`, `id`, `type`, `data1`, `data2` FROM `link` WHERE `noeud2` = " + parIdNoeud);

			while(resultSet.next()){
				locArray.add( new EmptyLink(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
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
			ResultSet resultSet = MySqlNoSQL.executeQuery( "SELECT `id`, `type`, `data1`, `data2` FROM `noeud` WHERE `id` = " + parIdNoeud);

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
	public int addNoeud(Noeud parAjouter) {
		try {
			System.out.println("Add : " + parAjouter );
			int value = MySqlNoSQL.executeOneInsert( "INSERT INTO `noeud`(`type`, `data1`, `data2`) VALUES ('"+parAjouter.getType()+"','"+parAjouter.getData1()+"','"+parAjouter.getData2()+"');" );
			System.out.println("New ID : " + value );
			if ( value == 0 ) 
			{
				return -1 ;
			}
			parAjouter.setId(value);
			return value ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public boolean removeNoeud(Noeud parRetirer) {

		String locWhereClause = "" ;

		if (parRetirer.getId() != -1)
		{
			locWhereClause += "`id` = " + parRetirer.getId();
		}
		else
		{
			if (parRetirer.getType() != null)
			{
				locWhereClause += "`type` = " + parRetirer.getType() + ",";
			}
			if (parRetirer.getData1() != null)
			{
				locWhereClause += "`data1` = " + parRetirer.getData1() + ",";
			}
			if (parRetirer.getData2() != null)
			{
				locWhereClause += "`data2` = " + parRetirer.getData2() + ",";
			}

			if (locWhereClause.equals("") == false)
			{
				locWhereClause.substring(0, locWhereClause.length() -1) ;
			}
		}

		if (locWhereClause.equals(""))
		{
			return false ;
		}

		try {


			int value = MySqlNoSQL.executeUpdate( "DELETE FROM `noeud` WHERE " + locWhereClause);

			if ( value == 0 )
			{
				return false ;
			}

			return true ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public int addLink(EmptyLink parAjouter) {
		try {
			ResultSet resultSet = MySqlNoSQL.executeQuery("SELECT max(`id`)+1 FROM `link` WHERE `noeud1` = '"+parAjouter.getIdNoeud1()+"' and `noeud2` = '"+parAjouter.getIdNoeud2()+"' " );
			
			if (resultSet.next())
			{
				parAjouter.setId(resultSet.getInt(1));
			}
			
			int value = MySqlNoSQL.executeOneInsert( "INSERT INTO `link`(`noeud1`, `noeud2`, `id`, `type`, `data1`, `data2`) "
					+ "VALUES ('"+parAjouter.getIdNoeud1()+"','"+parAjouter.getIdNoeud2()+"','"+parAjouter.getId()+"','"+parAjouter.getType()+"','"+parAjouter.getData1()+"','"+parAjouter.getData2()+"')");

			if ( value == 0 )
			{
				return -1 ;
			}

			return parAjouter.getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public boolean updateLink(EmptyLink parUpdate) {
		if (parUpdate.getId() != -1 && parUpdate.getIdNoeud1() != -1 && parUpdate.getIdNoeud2() != -1)
		{

			try {
				int value = MySqlNoSQL.executeUpdate( "UPDATE `link` SET `type`='"+parUpdate.getType()+"',`data1`='"+parUpdate.getData1()+"',`data2`='"+parUpdate.getData2()+"' "
						+ " WHERE "
						+ " `id` = " + parUpdate.getId()
						+ " AND `noeud1` = " + parUpdate.getIdNoeud1()
						+ " AND `noeud2` = " + parUpdate.getIdNoeud2());

				if ( value == 0 )
				{
					return false ;
				}

				return true ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean removeLink(EmptyLink parRetirer) {

		String locWhereClause = "" ;

		if (parRetirer.getId() != -1 && parRetirer.getIdNoeud1() != -1 && parRetirer.getIdNoeud2() != -1)
		{
			locWhereClause += "`id` = " + parRetirer.getId()
					+ ",`noeud1` = " + parRetirer.getIdNoeud1()
					+ ",`noeud2` = " + parRetirer.getIdNoeud2();
		}
		else
		{
			if (parRetirer.getType() != null)
			{
				locWhereClause += "`type` = " + parRetirer.getType() + ",";
			}
			if (parRetirer.getData1() != null)
			{
				locWhereClause += "`data1` = " + parRetirer.getData1() + ",";
			}
			if (parRetirer.getData2() != null)
			{
				locWhereClause += "`data2` = " + parRetirer.getData2() + ",";
			}

			if (locWhereClause.equals("") == false)
			{
				locWhereClause.substring(0, locWhereClause.length() -1) ;
			}
		}

		if (locWhereClause.equals(""))
		{
			return false ;
		}

		try {

			int value = MySqlNoSQL.executeUpdate( "DELETE FROM `link` WHERE " + locWhereClause);

			if ( value == 0 )
			{
				return false ;
			}

			return true ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Noeud> getNoeudParTypeEtData(String type, String data1,	String data2) {
		
		write("Recherche noeud avec Type = " + type + " d1 : " + data1 + " d2 : " + data2 );
		String whereClause = "";
		
		if (type != null)
		{
			if (type.contains(BddNoSql_Int.RECHERCHE_TOUT))
			{
				type.replace(BddNoSql_Int.RECHERCHE_TOUT, "%") ;
				whereClause += " type like ( '" +type+"')" ;
			}
			else
			{
				whereClause += " type = '" +type+"' " ;
			}
			
		}
		
		if (data1 != null)
		{
			if (whereClause.equals("") == false)
			{
				whereClause += " AND " ;
			}
			
			if (data1.contains(BddNoSql_Int.RECHERCHE_TOUT))
			{
				data1.replace(BddNoSql_Int.RECHERCHE_TOUT, "%") ;
				whereClause += " data1 like ( '" +data1+"')" ;
			}
			else
			{
				whereClause += " data1 = '" +data1+"' " ;
			}
			
		}
		
		if (data2 != null)
		{
			if (whereClause.equals("") == false)
			{
				whereClause += " AND " ;
			}
			
			if (data2.contains(BddNoSql_Int.RECHERCHE_TOUT))
			{
				data2.replace(BddNoSql_Int.RECHERCHE_TOUT, "%") ;
				whereClause += " data2 like ( '" +data2+"')" ;
			}
			else
			{
				whereClause += " data2 = '" +data2+"' " ;
			}
			
		}
		
		if (whereClause.equals(""))
			whereClause = " 1" ;
		
		ArrayList<Noeud> list = new ArrayList<Noeud>();
		Noeud locNoeud = null ;
		try {
			
			ResultSet resultSet = MySqlNoSQL.executeQuery( "SELECT id, type, data1, data2 FROM noeud WHERE " + whereClause);
			
			while (resultSet.next()){
				
				locNoeud = new Noeud(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
				locNoeud.getListeLink().addAll( this.getLinkOfNoeud(resultSet.getInt(1)));
				
				list.add(locNoeud);
			}
			resultSet.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			//TODO Deal with it
		}
		
		write("retour : " + list);
		return list;
	}

	@Override
	public List<EmptyLink> getLinkParTypeEtData(String type, String data1,
			String data2) {
		String whereClause = "";
		
		if (type != null)
		{
			if (type.contains(BddNoSql_Int.RECHERCHE_TOUT))
			{
				type.replace(BddNoSql_Int.RECHERCHE_TOUT, "%") ;
				whereClause += " `type` like ( '" +type+"')" ;
			}
			else
			{
				whereClause += " `type` = '" +type+"' " ;
			}
			
		}
		
		if (data1 != null)
		{
			if (whereClause.equals("") == false)
			{
				whereClause += " AND " ;
			}
			
			if (data1.contains(BddNoSql_Int.RECHERCHE_TOUT))
			{
				data1.replace(BddNoSql_Int.RECHERCHE_TOUT, "%") ;
				whereClause += " `data1` like ( '" +data1+"')" ;
			}
			else
			{
				whereClause += " `data1` = '" +data1+"' " ;
			}
			
		}
		
		if (data2 != null)
		{
			if (whereClause.equals("") == false)
			{
				whereClause += " AND " ;
			}
			
			if (data2.contains(BddNoSql_Int.RECHERCHE_TOUT))
			{
				data2.replace(BddNoSql_Int.RECHERCHE_TOUT, "%") ;
				whereClause += " `data2` like ( '" +data2+"')" ;
			}
			else
			{
				whereClause += " `data2` = '" +data2+"' " ;
			}
			
		}
		
		if (whereClause.equals(""))
			whereClause = " 1" ;
		
		ArrayList<EmptyLink> list = new ArrayList<EmptyLink>();
		
		try {
			ResultSet resultSet = MySqlNoSQL.executeQuery( "SELECT `noeud1`, `noeud2`, `id`, `type`, `data1`, `data2` FROM `link` WHERE " + whereClause);
			
			while (resultSet.next()){

				list.add( new EmptyLink(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
			}
			resultSet.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			//TODO Deal with it
		}
		return list;
	}
/*
 * type = LIEN_TYPE_PASSAGE
	 * 					data1 = parIdData1
	 * 					data2 = DatePlusRecente
 */
	@Override
	public Noeud getDernierNoeudVisite(int pardata1) {
		Noeud locNoeud = this.getNoeudIndex() ;
		try {
			ResultSet resultSet = MySqlNoSQL.executeQuery("SELECT `noeud2` as id FROM `link` WHERE "
					+ " `type` = '"+BddNoSql_Int.LIEN_TYPE_PASSAGE+"' AND "
					+ " `data1`= '"+pardata1+"' "
					+ " ORDER BY `data2` "
					+ "DESC LIMIT 0, 1 "
			  );
			
			if (resultSet.next()){
				locNoeud = this.getNoeudParID(resultSet.getInt(1));
			}
			resultSet.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locNoeud;
	}

	@Override
	public List<EmptyLink> getParcours(int pardata1, Noeud parNoeudDepart) {
		ArrayList<EmptyLink> locArrayList = new ArrayList<EmptyLink>() ;
		try {
			ResultSet resultSet = MySqlNoSQL.executeQuery("SELECT `noeud1`, `noeud2`, `id`, `type`, `data1`, `data2` FROM `link`"
					+ " WHERE "
					+ " `type` = '"+BddNoSql_Int.LIEN_TYPE_PASSAGE+"' AND"
					+ " `data1`= '"+pardata1+"' "
					+ " ORDER BY `data2` DESC"  );
			Noeud locNoeudIndex = this.getNoeudIndex();
			boolean continuer = true ;
			if (resultSet.next() && continuer){
				locArrayList.add( new EmptyLink(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
				continuer = resultSet.getInt(1) != locNoeudIndex.getId() ;
			}
			resultSet.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locArrayList;
	}

	@Override
	public List<EmptyLink> getLinkOfNoeud(int parIdNoeud, int parLevel) {
		if (parLevel < 2)
		{
			return this.getLinkOfNoeud(parIdNoeud);
		}
		return null;
	}
	
	public boolean flushBdd ()
	{
		
		long time = new Date().getTime() - BddNoSql_Int.TEMPS_FLUSH_MS ;
		
		String datemax = SauvegardeParcours.dateFormat.format(new Date(time));
		
		System.out.println(datemax);
		
		try {
			MySqlNoSQL.executeUpdate("DELETE FROM `link` WHERE"
							+ "type = '"+BddNoSql_Int.LIEN_TYPE_PASSAGE+"' "
							+ "	and"
							+ "	STR_TO_DATE(`data2`, '%Y-%m-%d %H:%i:%s') < STR_TO_DATE('"+datemax+"', '%Y-%m-%d %H:%i:%s')) " ) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false ;
		}
	
		
		return true;
	}


	public void reset()
	{
		try {
			MySqlNoSQL.executeUpdate(	"DELETE FROM `link` WHERE 1 ; ");
			MySqlNoSQL.executeUpdate(	"DELETE FROM `noeud` WHERE 1 ; " );
			System.out.println("\n\n");
			MySqlNoSQL.executeUpdate(	" INSERT INTO `noeud` (`id`, `type`, `data1`, `data2`) VALUES(1, 'Video', '1', ''); " );
			MySqlNoSQL.executeUpdate(	" INSERT INTO `noeud` (`id`, `type`, `data1`, `data2`) VALUES(2, 'Video', '2', ''); " );
			MySqlNoSQL.executeUpdate(	" INSERT INTO `noeud` (`id`, `type`, `data1`, `data2`) VALUES(3, 'Video', '3', ''); " );
			MySqlNoSQL.executeUpdate(	" INSERT INTO `noeud` (`id`, `type`, `data1`, `data2`) VALUES(4, 'Video', '4', ''); " );
			MySqlNoSQL.executeUpdate(	" INSERT INTO `noeud` (`id`, `type`, `data1`, `data2`) VALUES(5, 'Video', '5', ''); " );
			MySqlNoSQL.executeUpdate(	" INSERT INTO `noeud` (`id`, `type`, `data1`, `data2`) VALUES(6, 'Video', '6', ''); " );
			MySqlNoSQL.executeUpdate(	" INSERT INTO `noeud` (`id`, `type`, `data1`, `data2`) VALUES(9, 'PStand', 'Index', ''); "  );
		} catch (SQLException e) {
			System.err.println("la remise a zero de la base de donnée a échoué");
			e.printStackTrace();
		} 
		System.out.println("la remise a zero de la base de donnée a réussit ! ");
	}

}