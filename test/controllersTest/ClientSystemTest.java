package controllersTest;

import models.User;

import org.junit.Before;

import controllers.ClientSystem;

public class ClientSystemTest {

	ClientSystem _clientSystem;
	User _userTest;
	
	@Before
	public void initClientSystem(){
		_clientSystem = new ClientSystem();
		_userTest = new User("toto","titi","tutu@toto.fr");
	}
	
	@Test
	public void addUserTest(){
		
	}
	
}
