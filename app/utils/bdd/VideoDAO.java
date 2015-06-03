package utils.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Video;

public class VideoDAO {
	
	private final static String _tableName = "videos";
	private final static String[] _fieldsName = { "title", "nb_like", "nb_dislike", "nd_view", "id_user, uploaded"};

	/**
	 * Ajoute une video
	 * @param title
	 * @param description
	 * @param idUser
	 * @return 0 si ok et 1 si fail
	 * @throws SQLException
	 */
	public int create(String title, String description, int idUser) {
		Date dt = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			ResultSet locRs = MysqlConnection.executeUpdateGetResult("INSERT INTO "+_tableName+" ( title, description, nb_like, nb_dislike, nb_view, nb_view, id_user, uploaded) VALUES ('"+ title + "', '"+description+"', 0, 0, 0, 0, "+idUser+", "+ sdf.format(dt) +")");
			
			if(locRs.next()){
	    		return 0;
			}
			return 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -42;
		}
	}
	
	/**
	 * Supprime une video avec son id
	 * @param idVideo
	 * @throws SQLException
	 */
	public void remove(int idVideo) {
		
		String locS = "DELETE FROM "+_tableName+" WHERE id = "+idVideo;
		
		try {
			MysqlConnection.executeUpdate(locS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public Video update(Video parObject) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	/**
	 * Recherche
	 * @param parId
	 * @return
	 * @throws SQLException
	 */
	public Video find(int parId) {
		
		try {
		
			ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = "+parId);
			
			if(locRs.next()) {
	    		return (new Video(locRs.getInt(1), locRs.getString(2), locRs.getInt(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getString(7), locRs.getString(8)));
	    	}
			
			return null;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

//	public List<Video> findAll() throws SQLException {
//		
//		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName);
//		
//		List<Video> locAllVideos = new ArrayList<Video>();
////		while(locRs.next())
////			locAllVideos.add(new Video(locRs.getLong(1), locRs.getString(2), locRs.getString(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getLong(7)));
//		return locAllVideos;
//	}
	
	/**
	 * Augmente le nombre de vue
	 * @param idVideo
	 * @return
	 * @throws SQLException
	 */
	public static int addView(int idVideo) {
		
		try {
			ResultSet locSearch = MysqlConnection.executeQuery("SELECT * FROM videos WHERE id = "+ idVideo );
			
			if(locSearch.next()){
	    		return 1;
			}
			
			if(MysqlConnection.executeUpdate("UPDATE videos SET nbview = "+(locSearch.getInt(5)+1)+" WHERE idVideo = "+idVideo) > 0) {
				return 0;
			}
			
			return 2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -42;
		}
		
	}
	
	
	
	/**
	 * Vote sur une video +1 ou -1
	 * @param idVideo
	 * @param idUser
	 * @param vote
	 * @return 0 si ok, 1 si user inexistant, 2 si video inexistante, 3 si déjà voté, 4 si fail
	 * @throws SQLException
	 */
	public static int vote(int idVideo, int idUser, int vote) {
		
		try {
			ResultSet locSearch = MysqlConnection.executeQuery("SELECT * FROM videos WHERE id = "+ idVideo );
		
			if(locSearch.next()){
	    		return 1;
			}
			ResultSet locSearch2 = MysqlConnection.executeQuery("SELECT * FROM users WHERE id = "+ idUser );
			if(locSearch2.next()){
	    		return 2;
			}
			ResultSet locSearch3 = MysqlConnection.executeQuery("SELECT * FROM likes WHERE id_user = "+ idUser + ", id_video = "+idVideo);
			if(locSearch3.next()){
	    		return 3;
			}
			
			Date dt = new java.util.Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(MysqlConnection.executeUpdate("INSERT INTO comments (viewedtime, value, id_user, id_video) VALUES ("+sdf.format(dt)+", "+vote+", "+idUser+", "+idVideo) > 0) {
				if(vote == 1 && MysqlConnection.executeUpdate("UPDATE videos SET nblike = "+(locSearch.getInt(3)+1)+" WHERE idVideo = "+idVideo) > 0){
					return 0;
				} else {
					if(MysqlConnection.executeUpdate("UPDATE videos SET nbdislike = "+(locSearch.getInt(4)+1)+" WHERE idVideo = "+idVideo) > 0) {
						return 0;
					}
				}
				
			}
			
			return 4;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -42;
		}
		
	}
	
	/**
	 * Cherche les 15 dernières vidéos
	 * @param parId
	 * @return
	 */
	public List<Video> getLastVideo(int parId) {
		
		try {
			ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = "+parId+" ORDER BY updated DESC LIMIT 15");
		
		
			List <Video> videos = new ArrayList<Video>();
			
			while(locRs.next()) {
	    		videos.add(new Video(locRs.getInt(1), locRs.getString(2), locRs.getInt(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getString(7), locRs.getString(8)));
	    	}
			
			return videos;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Cherche les 15 vidéos les plus vues
	 * @param parId
	 * @return
	 */
	public List<Video> getPopularVideo(int parId) {
		
		try {
			ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = "+parId+" ORDER BY nb_view DESC LIMIT 15");
		
		
			List <Video> videos = new ArrayList<Video>();
			
			while(locRs.next()) {
	    		videos.add(new Video(locRs.getInt(1), locRs.getString(2), locRs.getInt(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getString(7), locRs.getString(8)));
	    	}
			
			return videos;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Recherche les videos correspondant
	 * @param search
	 * @return
	 */
	public List<Video> recherche(String search) {
		
		try {
			ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE title LIKE '%"+search+"%' LIMIT 15");
		
		
			List <Video> videos = new ArrayList<Video>();
			
			while(locRs.next()) {
	    		videos.add(new Video(locRs.getInt(1), locRs.getString(2), locRs.getInt(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getString(7), locRs.getString(8)));
	    	}
			
			return videos;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	

}
