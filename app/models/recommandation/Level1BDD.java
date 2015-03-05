package models.recommandation;

import java.util.ArrayList;

class Level1BDD {
	
	static ArrayList <Integer> giveRecommandation ( int parIdVideo )
	{
		ArrayList <Integer> locListRecommandation = new ArrayList <Integer> ();
		
		locListRecommandation.addAll( recommandationByPlaylist(parIdVideo) ) ;
		locListRecommandation.addAll( recommandationByTag(parIdVideo) ) ;
		
		return locListRecommandation ;
	}
	
	static ArrayList <Integer> recommandationByPlaylist ( int parIdVideo )
	{
		ArrayList <Integer> locListRecommandation = new ArrayList <Integer> ();
		
		// TODO Ask playlist
		
		return locListRecommandation ;
	}
	
	 static ArrayList <Integer> recommandationByTag ( int parIdVideo )
	{
		 ArrayList <Integer> locListRecommandation = new ArrayList <Integer> ();
		 
		// TODO Ask Tag
		 
		 return locListRecommandation ;
	}
	
}