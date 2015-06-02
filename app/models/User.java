package models;

public class User {
	private int id;
	private String username;
	private String email;
	private String channelName;
	private String profilImgUrl;
	
	/*
	 * Constructor without the url of the profile image
	 */
	public User(int parId, String parName, String parEmail, String parChannelName) {
		super();
		this.id = parId;
		this.username = parName;
		this.email = parEmail;
		this.channelName = parChannelName;
	}
	
	public User(int parId, String parName, String parEmail, String parChannelName, String parProfilImgUrl) {
		super();
		this.id = parId;
		this.username = parName;
		this.email = parEmail;
		this.channelName = parChannelName;
		this.profilImgUrl = parProfilImgUrl;
	}



	public int getId() {
		return id;
	}

	public void setId(int parId) {
		this.id = parId;
	}

	public String getName() {
		return username;
	}

	public void setName(String parName) {
		this.username = parName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String parEmail) {
		this.email = parEmail;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String _channelName) {
		this.channelName = _channelName;
	}

	public String getProfilImgUrl() {
		return profilImgUrl;
	}

	public void setProfilImgUrl(String profilImgUrl) {
		this.profilImgUrl = profilImgUrl;
	}
}
