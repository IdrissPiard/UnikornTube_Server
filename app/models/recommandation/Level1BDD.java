package models.recommandation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.bdd.MysqlConnection;

class Level1BDD {

	static ArrayList <Integer> giveRecommandation ( int parIdVideo , int parIdUser)
	{
		ArrayList <Integer> locListRecommandationTotal = new ArrayList <Integer> ();

		locListRecommandationTotal.addAll( recommandationByPlaylist(parIdVideo) ) ;
		locListRecommandationTotal.addAll( recommandationByTag(parIdVideo) ) ;
		locListRecommandationTotal.addAll( recommandationByChaine (  parIdVideo ,  parIdUser) ) ;
		ArrayList <Integer> locListRecommandationGood = new ArrayList <Integer> ();

		for (Integer integer : locListRecommandationTotal) {
			if ( locListRecommandationGood.contains(integer) == false)
			{
				locListRecommandationGood.add(integer);
			}
		}

		return locListRecommandationGood ;
	}

	static ArrayList <Integer> recommandationByTag ( int parIdVideo )
	{
		ArrayList <Integer> locListRecommandation = new ArrayList <Integer> ();

		try {

			ArrayList <Integer> locListTagCorrect = new ArrayList <Integer> ();

			ResultSet resultSet = MysqlConnection.executeQuery( "SELECT `id` , `tag` "
					+ " FROM `tags` "
					+ " WHERE `id` in ( SELECT DISTINCT `id_tag` "
					+ " FROM `tag_video`"
					+ " WHERE `id_video` = 10 ) ") ;
			ResultSet resultSet2 = null ;
			while(resultSet.next()){
				locListTagCorrect.add( resultSet.getInt(1) );

				resultSet2 = MysqlConnection.executeQuery( "SELECT `id_video`"
						+ " FROM `tag_video`"
						+ " WHERE `id_tag` IN ("
						+ " SELECT DISTINCT `id` FROM `tags` WHERE `tag` LIKE '%"+resultSet.getString(2)+"%'"
						+ ") " );
				while(resultSet2.next()){
					int anID = resultSet2.getInt(1) ;
					if (locListRecommandation.contains(anID) == false)
					{
						locListRecommandation.add(anID);
					}
				}
				resultSet2.close();

			}
			resultSet.close();

			if (locListTagCorrect.isEmpty() == false)
			{
				String tag = locListTagCorrect.get(0) + "";

				for (int i = 1; i < locListTagCorrect.size(); i++) {
					tag = "," + locListTagCorrect.get(i) ;
				}

				resultSet2 = MysqlConnection.executeQuery( "SELECT `id_video` FROM `tag_video` WHERE `id_tag` in ( "+tag+" ) ") ;

				while(resultSet2.next()){
					int anID = resultSet2.getInt(1) ;
					if (locListRecommandation.contains(anID) == false)
					{
						locListRecommandation.add(anID);
					}
				}
				resultSet2.close();

			}

		} catch (SQLException e) {
			//TODO LOG
			e.printStackTrace();
		}

		return locListRecommandation ;
	}

	static ArrayList <Integer> recommandationByPlaylist ( int parIdVideo )
	{
		ArrayList <Integer> locListRecommandation = new ArrayList <Integer> ();

		try {
			ResultSet resultSet = MysqlConnection.executeQuery( "SELECT `id_video` FROM `playlist_video` WHERE `id_playlist` in ( SELECT DISTINCT `id_playlist` FROM `playlist_video` WHERE `id_video` = "+parIdVideo+") and `id_video` <> "+parIdVideo+"" ) ;

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

		return locListRecommandation ;
	}

	static ArrayList <Integer> recommandationByChaine(int parIdVideo, int parIdUser) {
		ArrayList <Integer> locListRecommandation = new ArrayList <Integer> ();

		try {
			ResultSet resultSet = MysqlConnection.executeQuery(
					"SELECT `id` "
					+ "	FROM `videos`"
					+ "	WHERE `id` not in ( SELECT `id_video` FROM `likes` WHERE `id_user` =  " +parIdUser+ " )"
					+ "and `id_user` = ( select `id_user` FROM `videos`  where  `id` = " +parIdVideo+ " ) " );
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

		return locListRecommandation ;
	}
	
}