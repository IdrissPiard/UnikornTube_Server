package models.recommandation;

import java.util.ArrayList;

public class FrontRecommandation {
	
	public static ArrayList <Integer> giveRecommandation ( int parIdVideo )
	{
		return Level1BDD.giveRecommandation ( parIdVideo ) ;
	}
}