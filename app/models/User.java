package models;

public class User {
	private long _id;
	private String _name;
	private String _passwordHash;
	private String _email;
	private String _channelName;
	private boolean _connected;
	
	public User(String parName, String parPasswordHash, String parEmail){
		_name = parName;
		_passwordHash = parPasswordHash;
		_email = parEmail;
	}
	
	public User(long parId, String parName, String parPasswordHash, String parEmail, String parChannelName, boolean parConnected) {
		super();
		this._id = parId;
		this._name = parName;
		this._passwordHash = parPasswordHash;
		this._email = parEmail;
		this._channelName = parChannelName;
		this._connected = parConnected;
	}



	public long getId() {
		return _id;
	}

	public void setId(long parId) {
		this._id = parId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String parName) {
		this._name = parName;
	}

	public String getPasswordHash() {
		return _passwordHash;
	}

	public void setPasswordHash(String parPasswordHash) {
		this._passwordHash = parPasswordHash;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String parEmail) {
		this._email = parEmail;
	}

	public String getChannelName() {
		return _channelName;
	}

	public void setChannelName(String _channelName) {
		this._channelName = _channelName;
	}

	public boolean isConnected() {
		return _connected;
	}

	public void setConnected(boolean parConnected) {
		this._connected = parConnected;
	}
}
