package models;

public class User {
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
