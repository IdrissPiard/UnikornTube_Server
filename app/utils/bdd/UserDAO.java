package utils.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO implements DAO<User> {
	
	private final String _tableName;
	private final String[] _fieldsName = { "username", "password", "email", "channel_name", "connected"};

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
	public User create(User parObject) throws SQLException {
		
		StringBuilder locSb = new StringBuilder("INSERT INTO ");
		locSb.append(_tableName+" (");
		int i;
		for(i=0; i<this._fieldsName.length-1; i++) {
			locSb.append(this._fieldsName[i]+",");
		}
		locSb.append(this._fieldsName[i]+") VALUES(");
		locSb.append(parObject.getName()+",");
		locSb.append(parObject.getPasswordHash()+",");
		locSb.append(parObject.getEmail()+",");
		if(parObject.getChannelName() == null)
			locSb.append(",");
		else
			locSb.append(parObject.getChannelName()+",");
		locSb.append(0+")");
		
		ResultSet locRs = MysqlConnection.executeUpdateGetResult(locSb.toString());
		
		if(locRs.next()){
    		parObject.setId(locRs.getInt(1));
    		return parObject;
		}
		
		return null;
	}

	@Override
	public void remove(User parObject) throws SQLException {
		
		String locS = "DELETE FROM "+this._tableName+" WHERE id = "+parObject.getId();
		
		MysqlConnection.executeUpdate(locS);
		
	}

	@Override
	public User update(User parObject) throws SQLException {
		
		StringBuilder locSb = new StringBuilder("UPDATE "+this._tableName+" SET ");
		
		if(parObject.getName() != null && !parObject.getName().isEmpty())
			locSb.append(this._fieldsName[0]+"="+parObject.getName()+",");
		
		if(parObject.getPasswordHash() != null && !parObject.getPasswordHash().isEmpty())
			locSb.append(this._fieldsName[1]+"password="+parObject.getPasswordHash()+",");
		
		if(parObject.getEmail() != null && !parObject.getEmail().isEmpty())
			locSb.append(this._fieldsName[2]+"="+parObject.getEmail()+",");
		
		if(parObject.getChannelName() != null && !parObject.getChannelName().isEmpty())
			locSb.append(this._fieldsName[3]+"="+parObject.getChannelName()+",");
		
		if(parObject.isConnected())
			locSb.append(this._fieldsName[4]+"=1 ");
		else 
			locSb.append(this._fieldsName[4]+"=0 ");
		
		locSb.append("WHERE id = "+parObject.getId());
		
		ResultSet locRs = MysqlConnection.executeUpdateGetResult(locSb.toString());
		
		if(locRs.next()){
    		parObject.setId(locRs.getInt(1));
    		return parObject;
		}
		return null;
	}

	@Override
	public User find(long parId) throws SQLException {

		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+this._tableName+" WHERE id = "+parId);
		
		if(locRs.next()){
    		return (new User(locRs.getLong(1), locRs.getString(2), locRs.getString(3), locRs.getString(4), locRs.getString(5), locRs.getBoolean(6)));
    	}
		
		return null;
	}
	
	@Override
	public List<User> findAll() throws SQLException {
		
		ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+this._tableName);
		
		List<User> allUsers = new ArrayList<User>();
		while(locRs.next())
    		allUsers.add(new User(locRs.getLong(1), locRs.getString(2), locRs.getString(3), locRs.getString(4), locRs.getString(5), locRs.getBoolean(6)));
		return allUsers;
	}

}
