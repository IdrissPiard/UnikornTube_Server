package controllers.test;

public class User {
	int id;
	String username;
	String email;
	String channelName;
	String profilImgUrl;
	
	public User(int id, String username, String email, String channelName,
			String profilImgUrl) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.channelName = channelName;
		this.profilImgUrl = profilImgUrl;
	}
	
	public User(int id, String username, String email, String channelName) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.channelName = channelName;
	}
	
}