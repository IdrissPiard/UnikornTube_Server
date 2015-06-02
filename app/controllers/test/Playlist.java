package controllers.test;

import java.util.List;

public class Playlist {
	public int id;
	public int idUser;
	public String title;
	public List<Video> videos;
	
	public Playlist(int id, int idUser, String title) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.title = title;
	}

	public Playlist(int id, int idUser, String title, List<Video> videos) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.title = title;
		this.videos = videos;
	}
	
	
	
}