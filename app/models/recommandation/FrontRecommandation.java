package models.recommandation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.bdd.MysqlConnection;

public class FrontRecommandation {
	
	/**
	 * Renvoit une liste d'id vidéo recommandés en fonction d'une vidéo donnée
	 * @param parIdVideo > id de la vidéo 
	 * @return une liste d'id video recommandé
	 */
	public static ArrayList <Integer> giveRecommandation ( int parIdVideo , int parIdUser)
	{
		ArrayList<Integer> locListRecommandationTotal = Level1BDD.giveRecommandation ( parIdVideo , parIdUser ) ;
		locListRecommandationTotal.addAll( Level2NoSQL.giveRecommandation(parIdVideo) );
		
		ArrayList <Integer> locListRecommandationGood = new ArrayList <Integer> ();

		for (Integer integer : locListRecommandationTotal) {
			if ( locListRecommandationGood.contains(integer) == false)
			{
				locListRecommandationGood.add(integer);
			}
		}

		return locListRecommandationGood ;
	}
	
	/**
	 * Renvoit une liste d'id vidéo de vidéo d'une rubrique : " Regarder à nouveau " 
	 * @param parIdVideo > id de la vidéo 
	 * @return une liste d'id ( entre 0 et 5 ) video recommandé Regarder à nouveau
	 */
	public static ArrayList <Integer> giveReseenVideo( int parIDUser)
	{
		ArrayList <Integer> locListRecommandation = new ArrayList <Integer> ();
		
		 try {
				ResultSet resultSet = MysqlConnection.executeQuery( "SELECT `id_video`"
						+ " FROM `likes`"
						+ " WHERE `id_user` = "+parIDUser+" AND `value` != 2"
						+ " ORDER BY `viewedtime`"
						+ " LIMIT 0 , 5 " ) ;
				
				while(resultSet.next()){
					int anID = resultSet.getInt(1) ;
					if (locListRecommandation.contains(anID) == false)
					{
						locListRecommandation.add(anID);
					}
				}
				
				resultSet.close();
				
			} catch (SQLException e) {
				//TODO LOG
				e.printStackTrace();
			}
		
		return locListRecommandation;
	}
}