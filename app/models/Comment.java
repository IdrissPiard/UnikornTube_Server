package models;

public class Comment {
	private int id;
	private int id_video;
	private int id_user;
	private String comment;
	
	public Comment(int id, int idVideo, int idUser, String comment) {
		super();
		this.id = id;
		this.id_video = idVideo;
		this.id_user = idUser;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_video() {
		return id_video;
	}

	public void setId_video(int id_video) {
		this.id_video = id_video;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}