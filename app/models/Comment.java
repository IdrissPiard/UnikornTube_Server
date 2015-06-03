package models;

import java.util.List;

public class Comment {
	public int id;
	public int idVideo;
	public int idUser;
	public String comment;
	public List<Comment> responses;
	
	public Comment(int id, int idVideo, int idUser, String comment, List<Comment> responses) {
		super();
		this.id = id;
		this.idVideo = idVideo;
		this.idUser = idUser;
		this.comment = comment;
		this.responses = responses;
	}
}