package models;

import java.util.List;


public class Playlist {
	private int id;
	private int idUser;
	private String title;
	private List<Video> videoList;
	
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
		this.videoList = videos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}
	
	
	
}
