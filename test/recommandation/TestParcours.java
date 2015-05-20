package recommandation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.recommandation.FrontRecommandation;
import models.recommandation.SauvegardeParcours;
import models.recommandation.bddInterface.BddNoSQLMere;
import models.recommandation.bddInterface.BddNoSqlFactory;
import models.recommandation.bddInterface.BddNoSql_Int;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Guigui
 *
 */
public class TestParcours {

	@Before
	public void init() {
		BddNoSqlFactory.getBDD().reset();
	}
	
	@Test
	public void vueDirecte() {
		
		//Ici l'utililsateur N 1 va regarder la vidéo 1 puis 2
		SauvegardeParcours.passageVideo(1, 1) ;
		
		SauvegardeParcours.passageVideo(2, 1) ;
		
		//Normalement
		
		ArrayList<Integer> data = FrontRecommandation.giveRecommandation(1, 1);
		
		assertTrue(data.contains(new Integer(2)));
		
	}
	
	@Test
	public void vueRecherche() {
				
		
		//Ici l'utililsateur N 1 va regarder la vidéo 1 puis 2
		SauvegardeParcours.passageRecherche("Les bananes ?", 1) ;
		
		SauvegardeParcours.passageVideo(3, 1) ;
		
		SauvegardeParcours.passageRecherche("Les bananes ?", 1) ;
		
		SauvegardeParcours.passageVideo(2, 1) ;
		
		//Normalement
		
		ArrayList<Integer> data = FrontRecommandation.giveRecommandation(3, 1);
		
		assertTrue(data.contains(new Integer(2)));
		
	}
	
	@Test
	public void calcule5Reco() {
	
	
		
		//Normalement
		
		ArrayList<Integer> data = FrontRecommandation.giveRecommandation(3, 1);
		
		assertTrue(data.contains(new Integer(2)));
		
	}
	

}
