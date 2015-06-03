package models;

public class Video {
	public int id;
	public String title;
	public int nbLike;
	public int nbDislike;
	public int nbView;
	public int idUser;
	public String username;
	public String channel;
	public String description;
	public String[] tags;
	public String uploaded;
	
	public Video(int id, String title, int nbLike, int nbDislike,
			int nbView, int idUser, String description, String uploaded) {
		super();
		this.id = id;
		this.title = title;
		this.nbLike = nbLike;
		this.nbDislike = nbDislike;
		this.nbView = nbView;
		this.idUser = idUser;
		this.description = description;
		this.uploaded = uploaded;
	}
	
	
}