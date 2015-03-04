package controllers;

import models.User;

public interface IDAO {

	public int addUser(User parUser);
	public User getUser(String parName);
	
}
