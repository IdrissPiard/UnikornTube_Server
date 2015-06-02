package utils.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Playlist;
import models.User;
import models.Video;

public class UserDAO implements DAO<User> {
	
	private final String _tableName;
	private final String[] _fieldsName = { "username", "password", "email", "channel_name", "profil_img_url"};
	

	public UserDAO() {
		super();
		this._tableName = "users";		
	}
	
	/**
	 * In case you your table name is not "users" thx to specify it
	 * @param parTableName
	 */
	public UserDAO(String parTableName) {
		super();
		this._tableName = parTableName;
	}

	@Override
	public int create(User parObject) throws SQLException {
		
//		StringBuilder locSb = new StringBuilder("INSERT INTO ");
//		locSb.append(_tableName+" (");
//		int i;
//		for(i=0; i<this._fieldsName.length-1; i++) {
//			locSb.append(this._fieldsName[i]+",");
//		}
//		locSb.append(this._fieldsName[i]+") VALUES(");
//		locSb.append(parObject.username+",");
//		locSb.append(parObject.getPassword()+",");
//		locSb.append(parObject.getEmail()+",");
//		if(parObject.getChannelName() == null)
//			locSb.append(",");
//		else
//			locSb.append(parObject.getChannelName()+",");
//		locSb.append(parObject.getProfilImgUrl());
//		locSb.append(")");
//		
//		ResultSet locRs = MysqlConnection.executeUpdateGetResult(locSb.toString());
//		
//		if(locRs.next()){
//    		parObject.setId(locRs.getInt(1));
//    		return 0;
//		}
		
		return 1;
	}

	@Override
	public void remove(User parIdUser) throws SQLException {
		
		String locS = "DELETE FROM "+this._tableName+" WHERE id = "+parIdUser;
		
		MysqlConnection.executeUpdate(locS);
		
	}

	@Override
	public User update(User parObject) throws SQLException {
		
//		StringBuilder locSb = new StringBuilder("UPDATE "+this._tableName+" SET ");
//		
//		if(parObject.getName() != null && !parObject.getName().isEmpty())
//			locSb.append(this._fieldsName[0]+"="+parObject.getName()+",");
//		
////		if(parObject.getPassword() != null && !parObject.getPassword().isEmpty())
////			locSb.append(this._fieldsName[1]+"="+parObject.getPassword()+",");
//		
//		if(parObject.getEmail() != null && !parObject.getEmail().isEmpty())
//			locSb.append(this._fieldsName[2]+"="+parObject.getEmail()+",");
//		
//		if(parObject.getChannelName() != null && !parObject.getChannelName().isEmpty())
//			locSb.append(this._fieldsName[3]+"="+parObject.getChannelName()+",");
//		
//		if(parObject.getProfilImgUrl() != null && !parObject.getProfilImgUrl().isEmpty())
//			locSb.append(this._fieldsName[4]+"="+parObject.getProfilImgUrl()+",");
//		
//		//Supprime la virgule de trop
//		locSb.deleteCharAt(locSb.length()-1);
//		
////		if(parObject.isConnected())
////			locSb.append(this._fieldsName[4]+"=1 ");
////		else 
////			locSb.append(this._fieldsName[4]+"=0 ");
//		
//		
//		locSb.append(" WHERE id = "+parObject.getId());
//		
//		ResultSet locRs = MysqlConnection.executeUpdateGetResult(locSb.toString());
//		
//		if(locRs.next()){
//    		parObject.setId(locRs.getInt(1));
//    		return parObject;
//		}
		return null;
	}

	@Override
	public User find(long parId) throws SQLException {

		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+this._tableName+" WHERE id = "+parId);
		
		
		if(locRs.next()){
			// utilise le constructeur sans password
    		return (new User(locRs.getInt(1), locRs.getString(2), locRs.getString(4), locRs.getString(5), locRs.getString(6)));
    	}
		
		return null;
	}
	
	@Override
	public List<User> findAll() throws SQLException {
		
		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+this._tableName);
		
		List<User> allUsers = new ArrayList<User>();
		while(locRs.next())
			// utilise le constructeur sans password
    		allUsers.add(new User(locRs.getInt(1), locRs.getString(2), locRs.getString(4), locRs.getString(5), locRs.getString(6)));
		return allUsers;
	}
	
	public boolean findUserLogin(String parUsername, String parPassword) throws SQLException {

		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+this._tableName+" WHERE username = "+parUsername+" AND password = "+parPassword);
		
		if(locRs.next()){
    		return true;
    	}
		
		return false;
	}
	
	
	public List<Playlist> getPlaylist(int parIdUser) throws SQLException {
		ResultSet locPlaylists = MysqlConnection.executeQuery("SELECT * FROM playlists WHERE id_user = "+parIdUser);
		
		List<Playlist> playlists = new ArrayList<Playlist>();
		while(locPlaylists.next()) {
			List<Video> videos = new ArrayList<Video>();
			ResultSet locPV = MysqlConnection.executeQuery("SELECT * FROM videos WHERE id = (SELECT id_video FROM playlist_video WHERE id_playlist = "+locPlaylists.getInt(1)+")");
			while (locPV.next()) {
				videos.add(new Video(locPV.getInt(1), locPV.getString(2), locPV.getString(3), locPV.getInt(4), locPV.getInt(5),
						locPV.getInt(6), locPV.getInt(7)));
			}
			playlists.add(new Playlist(locPlaylists.getInt(1), locPlaylists.getInt(2), locPlaylists.getString(3), videos));
		}
		return playlists;
    		
	}
	

}
