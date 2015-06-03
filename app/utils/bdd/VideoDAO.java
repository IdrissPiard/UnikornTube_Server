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


	public int create(String title, int idUser) throws SQLException {
		ResultSet locSearch = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE title = "+title);
		if(locSearch.next()){
    		return 1;
		}
		
		
		
		ResultSet locRs = MysqlConnection.executeUpdateGetResult("INSERT INTO "+_tableName+" ( title, link, nb_like, nb_dislike, nb_view, nb_view, id_user");
		
		if(locRs.next()){
    		return 0;
		}
		
		return 2;
	}

	public void remove(Video parObject) throws SQLException {
		
		String locS = "DELETE FROM "+_tableName+" WHERE id = "+parObject.id;
		
		MysqlConnection.executeUpdate(locS);
	}

	public Video update(Video parObject) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Video find(long parId) throws SQLException {
		
		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = "+parId);
		
		if(locRs.next()){
//    		return (new Video(locRs.getLong(1), locRs.getString(2), locRs.getString(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getLong(7)));
    	}
		
		return null;
	}

	public List<Video> findAll() throws SQLException {
		
		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName);
		
		List<Video> locAllVideos = new ArrayList<Video>();
//		while(locRs.next())
//			locAllVideos.add(new Video(locRs.getLong(1), locRs.getString(2), locRs.getString(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getLong(7)));
		return locAllVideos;
	}
	
	
	
	
	
	public static int vote(int idVideo, int idUser, int vote) throws SQLException {
		
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
			if(vote == 1 && MysqlConnection.executeUpdate("UPDATE videos SET nblike = "+(locSearch.getInt(4)+1)+" WHERE idVideo = "+idVideo) > 0){
				return 0;
			} else {
				if(MysqlConnection.executeUpdate("UPDATE videos SET nbdislike = "+(locSearch.getInt(5)+1)+" WHERE idVideo = "+idVideo) > 0) {
					return 0;
				}
			}
			
		}
		
		return 4;
		
	}

}
