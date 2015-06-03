package utils.bdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Comment;

public class CommentDAO {
	private final static String _tableName = "comments";
	private final static String[] _fieldsName = { "comment", "id_video", "id_user", "id_response"};
	
	public static int create(String comment, int idVideo, int idUser ){
		
		StringBuilder locSb = new StringBuilder("INSERT INTO ");
		locSb.append(_tableName+" (");
		int i;
		for(i=0; i<_fieldsName.length-2; i++) {
			locSb.append(_fieldsName[i]+",");
		}
		locSb.append(_fieldsName[i]+") VALUES(");
		locSb.append("'"+comment+"',");
		locSb.append(idVideo+",");
		locSb.append(idUser+",");
		
		//Supprime la virgule de trop
		locSb.deleteCharAt(locSb.length()-1);
		
		locSb.append(")");
		
		ResultSet locRs;
		try {
			locRs = MysqlConnection.executeUpdateGetResult(locSb.toString());
			
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
	 * Réponds à un commentaire
	 * @param comment
	 * @param idUser
	 * @param idResponse
	 * @return
	 * @throws SQLException
	 */
	public static int repToComment(String comment, int idUser, int idResponse){
	try{
		ResultSet locRs = MysqlConnection.executeUpdateGetResult("INSERT INTO comments (comment, id_user, id_response) VALUES ('"+comment+"', "+idUser+", "+idResponse);
		
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
	 * Trouve tous les commentaires d'une vidéo et les réponses à 1 niveau
	 * @param idVideo
	 * @return
	 * @throws SQLException
	 */
	public static List<Comment> findComments(int idVideo){
		try{
			ResultSet locSearch = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id = "+ idVideo);
			
			List<Comment> comments = new ArrayList<Comment>();
			while(locSearch.next()) {
				ResultSet locSearch2 = MysqlConnection.executeQuery("SELECT * FROM "+_tableName+" WHERE id_response = "+ locSearch.getInt(5));
				List<Comment> responses = new ArrayList<Comment>();
				while(locSearch2.next()) {
					responses.add(new Comment(locSearch2.getInt(1), locSearch2.getInt(3), locSearch2.getInt(4), locSearch2.getString(2), null));
				}
				comments.add(new Comment(locSearch.getInt(1), locSearch.getInt(3), locSearch.getInt(4), locSearch.getString(2), responses));
			}
			
			return comments;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}
