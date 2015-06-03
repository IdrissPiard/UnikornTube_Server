package utils.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserDAO {
	
	private final static String _tableName = "users";
	private final static String[] _fieldsName = { "username", "password", "email", "channel_name"};
	

	public UserDAO() {
		super();	
	}
	
	
	/**
	 * Ajoute un utilisateur
	 * @param parUsername
	 * @param parPassword
	 * @param parEmail
	 * @param parChannelName
	 * @param parProfilImgUrl
	 * @return 0 si ok 1 si existe déjà et 2 si fail
	 * @throws SQLException
	 */
	public static int create(String parUsername, String parPassword, String parEmail, String parChannelName){
		
		try{
			ResultSet locSearch = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE username = '"+parUsername+"'");
			if(locSearch.next()){
	    		return 1;
			}
			
			StringBuilder locSb = new StringBuilder("INSERT INTO ");
			locSb.append(_tableName+" (");
			int i;
			for(i=0; i<_fieldsName.length-1; i++) {
				locSb.append(_fieldsName[i]+",");
			}
			locSb.append(_fieldsName[i]+") VALUES(");
			locSb.append("'"+parUsername+"',");
			locSb.append("'"+parPassword+"',");
			locSb.append("'"+parEmail+"',");
			locSb.append("'"+parChannelName+"',");
			
			//Supprime la virgule de trop
			locSb.deleteCharAt(locSb.length()-1);
			
			locSb.append(")");
			
			ResultSet locRs = MysqlConnection.executeUpdateGetResult(locSb.toString());
			
			if(locRs.next()){
	    		return 0;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return -42;
		}
		
		return 2;
	}
	
	
	/**
	 * Supprime l'utilisateur
	 * @param parIdUser
	 * @throws SQLException
	 */
	public static void remove(User parIdUser){
		
		String locS = "DELETE FROM "+_tableName+" WHERE id = "+parIdUser;
		
		try {
			MysqlConnection.executeUpdate(locS);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return;
		}
		
	}

//	public User update(User parObject) throws SQLException {
//		
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
//		return null;
//	}
	
	/**
	 * Trouve l'utilisateur par l'id
	 * @param parId
	 * @return
	 * @throws SQLException
	 */
	public static User find(int parId){
		try{
			ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = "+parId);
			
			
			if(locRs.next()){
				// utilise le constructeur sans password
	    		return (new User(locRs.getInt(1), locRs.getString(2), locRs.getString(4), locRs.getString(5), locRs.getString(6)));
	    	}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * Trouve tes les utilisateurs
	 * @return
	 * @throws SQLException
	 */
	public static List<User> findAll(){
		try{
			ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName);
			
			List<User> allUsers = new ArrayList<User>();
			while(locRs.next()){
				// utilise le constructeur sans password
	    		allUsers.add(new User(locRs.getInt(1), locRs.getString(2), locRs.getString(4), locRs.getString(5), locRs.getString(6)));
			}
			return allUsers;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Login
	 * @param parUsername
	 * @param parPassword
	 * @return 0 si correct 1 sinon
	 * @throws SQLException
	 */
	public static int findUserLogin(String parUsername, String parPassword){
		try{
			ResultSet locRs = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE username = '"+parUsername+"' AND password = '"+parPassword+"'");
			
			if(locRs.next()){
	    		return 0;
	    	}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return -42;
		}
		return 1;
	}
	
	
	
	/**
	 * Trouve toutes les subs de l'utilisateur
	 * @param parIdUser
	 * @return Liste d'utilisateur
	 * @throws SQLException
	 */
	public static List<User> getUserSubscribe(int parIdUser){
	try{
		ResultSet locSubscribes = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = (SELECT id_sub FROM subscriptions WHERE id_user = "+parIdUser);
		
		List<User> subs = new ArrayList<User>();
		while(locSubscribes.next()) {
			subs.add(new User(locSubscribes.getInt(1), locSubscribes.getString(2), locSubscribes.getString(4), locSubscribes.getString(5), locSubscribes.getString(6)));
		}
		return subs;
	} catch (SQLException e) {
		
		e.printStackTrace();
		return null;
	}
		
	}
	
	/**
	 * Abonnement à une chaîne
	 * @param parIdUser l'utilisateur qui veut subscribe
	 * @param parIdSub l'utilisateur auquel il subcribe
	 * @return 0 si ok, 1 si existe déjà, 2 si fail
	 * @throws SQLException
	 */
	public static int subscribe(int parIdUser, int parIdSub){
	try{
		ResultSet locSearch = MysqlConnection.executeQuery("SELECT * FROM subscriptions WHERE id_user = "+parIdUser+", id_sub = "+parIdSub);
		if(locSearch.next()){
    		return 1;
		} 
		
		ResultSet locRs = MysqlConnection.executeUpdateGetResult("INSERT INTO subscriptions (id_user, id_sub) VALUES ("+ parIdUser + ", "+ parIdSub+")");
		
		if(locRs.next())
			return 0;
	} catch (SQLException e) {
		
		e.printStackTrace();
		return -42;
	}	
		
		return 2;
	}
	

}
