package recommandationNoSQL;

import java.util.ArrayList;

import models.recommandation.FrontRecommandation;
import models.recommandation.SauvegardeParcours;
import models.recommandation.bddInterface.BddNoSql_Int;
import models.recommandation.bddMySQL.MySqlNoSQL;

public class Test {
	public static void main (String[] args)
	{
		try {
			
			ArrayList<Integer> data = FrontRecommandation.giveRecommandation(2,1);
			
			for (Integer integer : data) {
				System.out.println(" I recommande : " + integer);
			}
			
//			MySqlNoSQL nosql = (MySqlNoSQL)BddNoSql_Int.getBDD();
//			
//			nosql.flushBdd();
//			System.out.println("------------Test begin !");
//			//Utilisateur 1 arrive sur la chaine Toto
//			SauvegardeParcours.passagePage("ChaineToto", 1);
//			Thread.sleep(1000);
//			System.out.println("------------Done looking ChaineToto !");
//			SauvegardeParcours.passagePage("ChaineCoucou", 1);
//			Thread.sleep(1000);
////			//video 1
//			
//			SauvegardeParcours.passageVideo(1, 1);
//			Thread.sleep(1000);
////			//video 2
//			SauvegardeParcours.passageVideo(2, 1);
//			Thread.sleep(1000);
//			SauvegardeParcours.passageVideo(3, 1);
//			Thread.sleep(1000);
//			SauvegardeParcours.passageVideo(4, 1);
//			
//			nosql.Close();
		}
		catch (Exception e)
		{
			System.err.println("Ca a cacater");
		}
	}
}
