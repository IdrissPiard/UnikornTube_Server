package models.recommandation;

import java.util.ArrayList;
import java.util.List;

import models.recommandation.bddInterface.BddNoSqlFactory;
import models.recommandation.bddInterface.BddNoSql_Int;
import models.recommandation.bddInterface.EmptyLink;
import models.recommandation.bddInterface.Noeud;

public class Level2NoSQL {

	static ArrayList <Integer> giveRecommandation ( int parIdVideo )
	{
		ArrayList <Integer> recommandation = new ArrayList<Integer>();
		
		List<Noeud> data = BddNoSqlFactory.getBDD().getNoeudParTypeEtData(BddNoSql_Int.NOEUX_TYPE_VIDEO, parIdVideo + "", "");
		
		if (data.size() > 0)
		{
			Noeud noeudVideo = data.get(0);
			ArrayList<EmptyLink> listeLink = noeudVideo.getListeLink() ;
			
			
			Noeud videoRecommande = null;
			int idVideo =  -1 ;
			for (EmptyLink emptyLink : listeLink) {
				if (emptyLink.getType().equals(BddNoSql_Int.LIEN_TYPE_RECOMMANDATION))
				{
					if (emptyLink.getIdNoeud1()==noeudVideo.getId())
					{
						videoRecommande = emptyLink.toFullLink().getNoeud2() ;
					}
					else
					{
						videoRecommande = emptyLink.toFullLink().getNoeud1() ;
					}
					
					try {
						idVideo = Integer.parseInt(videoRecommande.getData1());
						recommandation.add(idVideo);
					}
					catch (NumberFormatException e)
					{
						
					}
				}
			}
		}
		
		return recommandation;
	}
}
