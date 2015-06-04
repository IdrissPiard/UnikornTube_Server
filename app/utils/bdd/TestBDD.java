package utils.bdd;

import java.util.List;

import models.User;

public class TestBDD {
	
	public static void main(String[] args) {
		//User create
		UserDAO.create("test", "test", "test@example.com", "TESTYOLO");
		UserDAO.create("test2", "test2", "test@example.com", "TESTYOLOKIVAETREREMOVE");
		UserDAO.create("test3", "test2", "test@example.com", "TESTYOLOKIVAETREREMOVE");

		//User findAll
		List<User> lu = UserDAO.findAll();
		System.out.println("FindAll Complete");
		
		//User remove
		UserDAO.remove(lu.get(2).id);
		System.out.println("Remove Complete");
		
		//User find
		User u1 = UserDAO.find(lu.get(0).id);
		System.out.println("Find Complete username : "+u1.username);
		
		
		//User findUserLogin
		if(UserDAO.findUserLogin("test", "test") == 0)
			System.out.println("Login Complete");
		
		//User subscribe
		if(UserDAO.subscribe(lu.get(0).id, lu.get(1).id) == 0)
			System.out.println("Subscribe Complete");
		
		//User getUserSubscribe
		List<User> lu1 = UserDAO.getUserSubscribe(lu.get(0).id);
		System.out.println("getSubscribe Complete -> 1er sub : "+lu1.get(0).username);
		
	}

}
