package utils.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Playlist;
import models.Video;

public class PlaylistDAO {
	
	private final static String _tableName = "playlists";
	private final static String[] _fieldsName = { "id_user", "title"};
	
	/**
	 * Ajoute une playlist 
	 * @param idUser
	 * @param title
	 * @return
	 * @throws SQLException
	 */
	public static int create(int idUser, String title) throws SQLException {
		
		StringBuilder locSb = new StringBuilder("INSERT INTO ");
		locSb.append(_tableName+" (");
		int i;
		for(i=0; i<_fieldsName.length-1; i++) {
			locSb.append(_fieldsName[i]+",");
		}
		locSb.append(_fieldsName[i]+") VALUES(");
		locSb.append(idUser+",");
		locSb.append(title+",");
		
		//Supprime la virgule de trop
		locSb.deleteCharAt(locSb.length()-1);
		
		locSb.append(")");
		
		ResultSet locRs = MysqlConnection.executeUpdateGetResult(locSb.toString());
		
		if(locRs.next()){
    		return 0;
		}
		
		return 1;
	}
	
	/**
	 * Ajoute une video à une playlist
	 * @param idVideo
	 * @param idPlaylist
	 * @return
	 * @throws SQLException
	 */
	public static int addVideoToPlaylist(int idVideo, int idPlaylist) throws SQLException {
		
		ResultSet locSearch = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = "+ idPlaylist);
		ResultSet locSearch2 = MysqlConnection.executeQuery("SELECT * FROM videos WHERE id = "+ idVideo);
//		ResultSet locSearch3 = MysqlConnection.executeQuery("SELECT * FROM playlist_video WHERE id_playlist="+ idPlaylist +", id_video = "+ idVideo);
		
		if(locSearch.next()){
			if(locSearch2.next()){
				ResultSet locRs = MysqlConnection.executeUpdateGetResult("INSERT INTO playlist_video (id_playlist, id_video) VALUES ("+ idPlaylist + ", "+ idVideo+")");
				if(locRs.next())
					return 0;
			} else {
				return 2;
			}
		} else {
			return 1;
		}
		
		return 3;
	}
	
	/**
	 * Trouve la playlist demandé
	 * @param idPlaylist
	 * @return
	 * @throws SQLException
	 */
	public static Playlist findPlaylist(int idPlaylist) throws SQLException {
		ResultSet locPlaylist = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = "+idPlaylist);
		
		
		if(locPlaylist.next()){
			List<Video> videos = new ArrayList<Video>();
			ResultSet locPV = MysqlConnection.executeQuery("SELECT * FROM videos WHERE id = (SELECT id_video FROM playlist_video WHERE id_playlist = "+locPlaylist.getInt(1)+")");
			while (locPV.next()) {
				videos.add(new Video(locPV.getInt(1), locPV.getString(2), locPV.getInt(3), locPV.getInt(4),
						locPV.getInt(5), locPV.getInt(6), locPV.getString(7), locPV.getString(8)));
			}
			return (new Playlist(locPlaylist.getInt(1), locPlaylist.getInt(2), locPlaylist.getString(3), videos));
		}
		
		return null;
			
	}
	
	/**
	 * Trouve toutes les playlists de l'utilisateur
	 * @param parIdUser
	 * @return Liste de playlists
	 * @throws SQLException
	 */
	public static List<Playlist> getUserPlaylists(int parIdUser) throws SQLException {
		ResultSet locPlaylists = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id_user = "+parIdUser);
		
		List<Playlist> playlists = new ArrayList<Playlist>();
		while(locPlaylists.next()) {
//			List<Video> videos = new ArrayList<Video>();
//			ResultSet locPV = MysqlConnection.executeQuery("SELECT * FROM videos WHERE id = (SELECT id_video FROM playlist_video WHERE id_playlist = "+locPlaylists.getInt(1)+")");
//			while (locPV.next()) {
//				videos.add(new Video(locPV.getInt(1), locPV.getString(2), locPV.getString(3), locPV.getInt(4), locPV.getInt(5),
//						locPV.getInt(6), locPV.getInt(7)));
//			}
			playlists.add(new Playlist(locPlaylists.getInt(1), locPlaylists.getInt(2), locPlaylists.getString(3), null));
		}
		return playlists;
    		
	}
	
	

}
