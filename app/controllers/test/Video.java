package controllers.test;

public class Video {
	public long id;
	public String title;
	public String link;
	public int nbLike;
	public int nbDislike;
	public int nbView;
	public long idUser;
	public Video(long id, String title, String link, int nbLike, int nbDislike,
			int nbView, long idUser) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
		this.nbLike = nbLike;
		this.nbDislike = nbDislike;
		this.nbView = nbView;
		this.idUser = idUser;
	}
	
	
}