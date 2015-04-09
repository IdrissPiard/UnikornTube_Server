package models;

public class Video {
	private long _id;
	private String _title;
	private String _link;
	private int _nbLike;
	private int _nbDislike;
	private int _nbView;
	private long _idUser;
	
	public Video(long parId, String parTitle, String parLink, int parNbLike, int parNbDislike, int parNbView, long parIdUser) {
		super();
		this._id = parId;
		this._title = parTitle;
		this._link = parLink;
		this._nbLike = parNbLike;
		this._nbDislike = parNbDislike;
		this._nbView = parNbView;
		this._idUser = parIdUser;
	}


	public long getId() {
		return _id;
	}


	public void setId(long parId) {
		this._id = parId;
	}


	public String getTitle() {
		return _title;
	}


	public void setTitle(String parTitle) {
		this._title = parTitle;
	}


	public String getLink() {
		return _link;
	}


	public void setLink(String parLink) {
		this._link = parLink;
	}


	public int getNbLike() {
		return _nbLike;
	}


	public void setNbLike(int parNbLike) {
		this._nbLike = parNbLike;
	}


	public int getNbDislike() {
		return _nbDislike;
	}


	public void setNbDislike(int parNbDislike) {
		this._nbDislike = parNbDislike;
	}


	public int getNbView() {
		return _nbView;
	}


	public void setNbView(int parNbView) {
		this._nbView = parNbView;
	}


	public long getIdUser() {
		return _idUser;
	}


	public void setIdUser(long parIdUser) {
		this._idUser = parIdUser;
	}
	
	
}

