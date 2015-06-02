package models;

public class Comment {
	public int id;
	public int idVideo;
	public int idUser;
	public String comment;
	
	public Comment(int id, int idVideo, int idUser, String comment) {
		super();
		this.id = id;
		this.idVideo = idVideo;
		this.idUser = idUser;
		this.comment = comment;
	}
}