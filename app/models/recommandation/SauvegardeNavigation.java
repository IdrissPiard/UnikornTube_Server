package models.recommandation;

import java.util.List;

import models.recommandation.bddInterface.BddNoSql_Int;
import models.recommandation.bddInterface.EmptyLink;
import models.recommandation.bddInterface.FullLink;
import models.recommandation.bddInterface.Noeud;

public class SauvegardeNavigation {
	
	public static int createVideo (int idVideo)
	{
		List<Noeud> data = BddNoSql_Int.getBDD().getNoeudParTypeEtData(BddNoSql_Int.NOEUX_TYPE_VIDEO, idVideo + "", null);
		
		if (data.size() == 0)
		{
			return BddNoSql_Int.getBDD().addNoeud(new Noeud( BddNoSql_Int.NOEUX_TYPE_VIDEO, idVideo + "", null));
		}
		
		return data.get(0).getId();
	}

	public static int createRecherche (String valeurRechercher)
	{
		List<Noeud> data = BddNoSql_Int.getBDD().getNoeudParTypeEtData(BddNoSql_Int.NOEUX_TYPE_PAGE_RECHERCHE, valeurRechercher, null);
		
		if (data.size() == 0)
		{
			return BddNoSql_Int.getBDD().addNoeud(new Noeud( BddNoSql_Int.NOEUX_TYPE_PAGE_RECHERCHE, valeurRechercher, null));
		}
		
		return data.get(0).getId();
	}
	
	public static int createPage(String nomPage)
	{
		List<Noeud> data = BddNoSql_Int.getBDD().getNoeudParTypeEtData(BddNoSql_Int.NOEUX_TYPE_PAGE_STANDARD, nomPage, null);
		
		if (data.size() == 0)
		{
			return BddNoSql_Int.getBDD().addNoeud(new Noeud( BddNoSql_Int.NOEUX_TYPE_PAGE_STANDARD, nomPage, null));
		}
		
		return data.get(0).getId();
	}
	
	public static int passagePage(String nomPage, int idUtilisateur)
	{
		int idNouvellePage = createPage(nomPage) ;
		
		if (idNouvellePage == -1)
		{
			return -1;
		}
		
		Noeud anciennePage = BddNoSql_Int.getBDD().getDernierLinkVisite(idUtilisateur);
		
		EmptyLink nouveauPassage = new  EmptyLink(anciennePage.getId(), idNouvellePage, BddNoSql_Int.LIEN_TYPE_PASSAGE, idUtilisateur + "", getCurrentTime());
					
		return BddNoSql_Int.getBDD().addLink(nouveauPassage);
	}
	
	public static int passageRecherche(String nomRecherche, int idUtilisateur)
	{
		int idNouvelleRecherche = createRecherche(nomRecherche) ;
		
		if (idNouvelleRecherche == -1)
		{
			return -1;
		}
		
		Noeud anciennePage = BddNoSql_Int.getBDD().getDernierLinkVisite(idUtilisateur);
		
		EmptyLink nouveauPassage = new  EmptyLink(anciennePage.getId(), idNouvelleRecherche, BddNoSql_Int.LIEN_TYPE_PASSAGE, idUtilisateur + "", getCurrentTime());
					
		return BddNoSql_Int.getBDD().addLink(nouveauPassage);
	}
	
	public static int passageVideo(int idVideo, int idUtilisateur)
	{
		int idNouvelleVideo = createVideo(idVideo) ;
		
		if (idNouvelleVideo == -1)
		{
			return -1;
		}
		
		Noeud anciennePage = BddNoSql_Int.getBDD().getDernierLinkVisite(idUtilisateur);
		
		EmptyLink Recommendation = null ;
		switch (anciennePage.getType())
		{
		case BddNoSql_Int.NOEUX_TYPE_VIDEO : 
			Recommendation = new  EmptyLink(anciennePage.getId(), idNouvelleVideo, BddNoSql_Int.LIEN_TYPE_RECOMMANDATION, getCurrentTime(), null);
			break ;
		case BddNoSql_Int.NOEUX_TYPE_PAGE_RECHERCHE :
			//TODO remonter d'un cran linker video wesh la !
			break;
		case BddNoSql_Int.NOEUX_TYPE_PAGE_STANDARD :
			//rien
			break;	
		default :
			//Rien	
				break;
		}
		
		if (Recommendation != null)
		{
			BddNoSql_Int.getBDD().addLink(Recommendation);
		}
		
		EmptyLink nouveauPassage = new  EmptyLink(anciennePage.getId(), idNouvelleVideo, BddNoSql_Int.LIEN_TYPE_PASSAGE, idUtilisateur + "", getCurrentTime());
					
		return BddNoSql_Int.getBDD().addLink(nouveauPassage);
	}
	
	public static String getCurrentTime()
	{
		// TODO ecrire la date actuelle
		return "2015/04/07 11h03;44";
	}
}
