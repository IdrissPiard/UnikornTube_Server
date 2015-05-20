package models.recommandation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.recommandation.bddInterface.BddNoSqlFactory;
import models.recommandation.bddInterface.BddNoSql_Int;
import models.recommandation.bddInterface.EmptyLink;
import models.recommandation.bddInterface.Noeud;

public class Level2NoSQL {
	
	public static final int NBRECOMMANDATION = 5 ;
	
	static ArrayList <Integer> giveRecommandation ( int parIdVideo )
	{
		ArrayList <Integer> listeIdVideos = new ArrayList<Integer>();
		
		ArrayList <Integer> listeNbRecommanderVideo = new ArrayList<Integer>();
		
		List<Noeud> data = BddNoSqlFactory.getBDD().getNoeudParTypeEtData(BddNoSql_Int.NOEUX_TYPE_VIDEO, parIdVideo + "", "");
		
		int idVideoMoinsVu = -1 ;
		int nbRecoVideoMoinsVu = -1 ;
		
		if (data.size() > 0)
		{
			Noeud noeudVideo = data.get(0);
			ArrayList<EmptyLink> listeLink = noeudVideo.getListeLink() ;
			
			Noeud videoRecommande = null; 
			int idVideo =  -1 ;
			int nbRecommander = -1 ;
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
						nbRecommander = Integer.parseInt(videoRecommande.getData2());

						int index = 0 ;

						boolean doisAjouter = true ;

						while (doisAjouter && index < listeIdVideos.size())
						{
							if ( doisAjouter = (listeNbRecommanderVideo.get(index) <= nbRecommander) == false )
							{
								index++;
							}

						}

						if (index < NBRECOMMANDATION)
						{
							listeIdVideos.add(index, idVideo);
							listeNbRecommanderVideo.add(index, nbRecommander);
							
							while (listeIdVideos.size() > NBRECOMMANDATION)
							{
								listeIdVideos.remove(NBRECOMMANDATION);
								listeNbRecommanderVideo.remove(NBRECOMMANDATION);
							}
						}
						else
						{
							if (nbRecoVideoMoinsVu > nbRecommander)
							{
								idVideoMoinsVu = idVideo ;
								nbRecoVideoMoinsVu = nbRecommander ;
							}
						}
						
					}
					catch (NumberFormatException e)
					{
						
					}
				}
			}
		}
		
		if (idVideoMoinsVu > 0 )
		{
			listeIdVideos.add(idVideoMoinsVu);
		}
		
		return listeIdVideos;
	}
}
