package utils.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Video;

public class VideoDAO implements DAO<Video> {
	
	private final String _tableName;
	private final String[] _fieldsName = { "title", "link", "nb_like", "nb_dislike", "nd_view", "id_user"};

	public VideoDAO() {
		super();
		this._tableName = "videos";		
	}
	
	/**
	 * In case you your table name is not "videos" thx to specify it
	 * @param parTableName
	 */
	public VideoDAO(String parTableName) {
		super();
		this._tableName = parTableName;
	}

	@Override
	public Video create(Video parObject) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Video parObject) throws SQLException {
		
		String locS = "DELETE FROM "+this._tableName+" WHERE id = "+parObject.getId();
		
		MysqlConnection.executeUpdate(locS);
	}

	@Override
	public Video update(Video parObject) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Video find(long parId) throws SQLException {
		
		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+this._tableName+" WHERE id = "+parId);
		
		if(locRs.next()){
    		return (new Video(locRs.getLong(1), locRs.getString(2), locRs.getString(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getLong(7)));
    	}
		
		return null;
	}

	@Override
	public List<Video> findAll() throws SQLException {
		
		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+this._tableName);
		
		List<Video> locAllVideos = new ArrayList<Video>();
		while(locRs.next())
			locAllVideos.add(new Video(locRs.getLong(1), locRs.getString(2), locRs.getString(3), locRs.getInt(4), locRs.getInt(5), locRs.getInt(6), locRs.getLong(7)));
		return locAllVideos;
	}

}
