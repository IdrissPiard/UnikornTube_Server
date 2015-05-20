package models.recommandation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.recommandation.bddInterface.BddNoSqlFactory;
import models.recommandation.bddInterface.BddNoSql_Int;
import models.recommandation.bddInterface.EmptyLink;
import models.recommandation.bddInterface.Noeud;

public class SauvegardeParcours {
	
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static void write(String message)
	{
		System.out.println("SauvegardeParcours : " + message );
	}
	
	public static int createVideo (int idVideo)
	{
		List<Noeud> data = BddNoSqlFactory.getBDD().getNoeudParTypeEtData(BddNoSql_Int.NOEUX_TYPE_VIDEO, idVideo + "", null);
		
		if (data.size() == 0)
		{
			return BddNoSqlFactory.getBDD().addNoeud(new Noeud( BddNoSql_Int.NOEUX_TYPE_VIDEO, idVideo + "", null));
		}
		
		return data.get(0).getId();
	}

	public static int createRecherche (String valeurRechercher)
	{
		List<Noeud> data = BddNoSqlFactory.getBDD().getNoeudParTypeEtData(BddNoSql_Int.NOEUX_TYPE_PAGE_RECHERCHE, valeurRechercher, null);
		
		if (data.size() == 0)
		{
			return BddNoSqlFactory.getBDD().addNoeud(new Noeud( BddNoSql_Int.NOEUX_TYPE_PAGE_RECHERCHE, valeurRechercher, null));
		}
		
		return data.get(0).getId(); 
	}
	
	public static int createPage(String nomPage)
	{
		List<Noeud> data = BddNoSqlFactory.getBDD().getNoeudParTypeEtData(BddNoSql_Int.NOEUX_TYPE_PAGE_STANDARD, nomPage, null);
		
		if (data.size() == 0)
		{
			return BddNoSqlFactory.getBDD().addNoeud(new Noeud( BddNoSql_Int.NOEUX_TYPE_PAGE_STANDARD, nomPage, null));
		}
		
		return data.get(0).getId();
	}
	
	public static int passagePage(String nomPage, int idUtilisateur)
	{
		int idNouvellePage = createPage(nomPage) ;
		
		if (idNouvellePage == -1)
		{
			write("Erreur sur la cr�ation de page " + nomPage);
			return -1;
		}
		
		Noeud anciennePage = BddNoSqlFactory.getBDD().getDernierNoeudVisite(idUtilisateur);
		
		EmptyLink nouveauPassage = new  EmptyLink(anciennePage.getId(), idNouvellePage, BddNoSql_Int.LIEN_TYPE_PASSAGE, idUtilisateur + "", getCurrentTime());
					
		return BddNoSqlFactory.getBDD().addLink(nouveauPassage);
	}
	
	public static int passageRecherche(String nomRecherche, int idUtilisateur)
	{
		int idNouvelleRecherche = createRecherche(nomRecherche) ;
		
		if (idNouvelleRecherche == -1)
		{
			return -1;
		}
		
		Noeud anciennePage = BddNoSqlFactory.getBDD().getDernierNoeudVisite(idUtilisateur);
		
		EmptyLink nouveauPassage = new  EmptyLink(anciennePage.getId(), idNouvelleRecherche, BddNoSql_Int.LIEN_TYPE_PASSAGE, idUtilisateur + "", getCurrentTime());
					
		return BddNoSqlFactory.getBDD().addLink(nouveauPassage);
	}
	
	public static int passageVideo(int idVideo, int idUtilisateur)
	{
		int idNouvelleVideo = createVideo(idVideo) ;
		
		if (idNouvelleVideo == -1)
		{
			return -1;
		}
		
		Noeud anciennePage = BddNoSqlFactory.getBDD().getDernierNoeudVisite(idUtilisateur);
		Noeud nouvellePage = BddNoSqlFactory.getBDD().getNoeudParID(idNouvelleVideo) ;
		
		switch (anciennePage.getType())
		{
			case BddNoSql_Int.NOEUX_TYPE_VIDEO : 
	
				//On arrive depuis une vidéo a une autre, il y a donc une recommandation entre les deux
				recommander(anciennePage,nouvellePage );
	
				ArrayList<EmptyLink> listeLink = anciennePage.getListeLink() ;
	
				write("Recherche dans les links ");
				Noeud autreNoeud ;
				for (EmptyLink emptyLink : listeLink) {
	
					//if ( emptyLink.getType() == BddNoSql_Int.LIEN_TYPE_PASSAGE && 
					if (emptyLink.getIdNoeud1() != anciennePage.getId())
					{
						autreNoeud = BddNoSqlFactory.getBDD().getNoeudParID(emptyLink.getIdNoeud1());
					}
					else
					{
						autreNoeud = BddNoSqlFactory.getBDD().getNoeudParID(emptyLink.getIdNoeud2());
					}
	
					write("links anciennePage : " + autreNoeud);
					if (autreNoeud.getType().equals(BddNoSql_Int.NOEUX_TYPE_VIDEO))
					{
						recommander(autreNoeud,nouvellePage);
					}
				}
	
				break ;
			case BddNoSql_Int.NOEUX_TYPE_PAGE_RECHERCHE :
	
				//On recherche la vidéo d'avant, s'il y a une recherche et on la recommande
				
				List<EmptyLink> parcours = BddNoSqlFactory.getBDD().getParcours(idUtilisateur, anciennePage);
	
				Noeud noeudsAvant = BddNoSqlFactory.getBDD().getNoeudParID( parcours.get(parcours.size() - 2).getIdNoeud1() );
				
				if (noeudsAvant.getType().equals(BddNoSql_Int.NOEUX_TYPE_VIDEO))
				{
					recommander(noeudsAvant,nouvellePage );
				}
				
				
				break;
			case BddNoSql_Int.NOEUX_TYPE_PAGE_STANDARD :
				//rien
				break;	
			default :
				//Rien	
				break;
		}
		
	
		
		EmptyLink nouveauPassage = new  EmptyLink(anciennePage.getId(), idNouvelleVideo, BddNoSql_Int.LIEN_TYPE_PASSAGE, idUtilisateur + "", getCurrentTime());
					
		return BddNoSqlFactory.getBDD().addLink(nouveauPassage);
	}
	
	public static String getCurrentTime()
	{
		return dateFormat.format(new Date());
	}
	
	private static boolean recommander(Noeud Video1, Noeud Video2)
	{
		write("Demande de recommander \nv1 : " + Video1 + "\net v2 : " + Video2);
		if (Video1.getType().equals(BddNoSql_Int.NOEUX_TYPE_VIDEO)==false || Video2.getType().equals( BddNoSql_Int.NOEUX_TYPE_VIDEO)==false)
		{
			write("Demande de recommander impossible pas deux video! ");
			System.out.println("v1 : " + Video1 );
			System.out.println("v2 : " + Video2 );
			return false;
		}
		
		EmptyLink link = null ;
		ArrayList<EmptyLink> data = Video1.getListeLink();
		for (EmptyLink emptyLink : data) {
			if (emptyLink.getType().equals(BddNoSql_Int.LIEN_TYPE_RECOMMANDATION) && ( emptyLink.getIdNoeud1() == Video2.getId() || emptyLink.getIdNoeud2() == Video2.getId()) )
			{
				link = emptyLink;
				break ;
			}
		}
		
		if (link == null)
		{
			EmptyLink Recommendation = new EmptyLink(Video1.getId(), Video2.getId(), BddNoSql_Int.LIEN_TYPE_RECOMMANDATION, "1" , getCurrentTime());
			if ( BddNoSqlFactory.getBDD().addLink(Recommendation) != -1 )
			{
				write (" nouvelle recommandation entre " + Video1.getId() + " et " + Video2.getId() + " => " + Recommendation.toString()) ;
				return true;
			}
			
		}
		else
		{
		
			int value   ;
			try
			{
				value = Integer.parseInt(link.getData1());
			}
			catch (NumberFormatException e)
			{
				write ("Valeur incorrecte") ;
				value = 0;
			}
			
			link.setData1( Integer.toString(++value) );
			if ( BddNoSqlFactory.getBDD().updateLink(link) )
			{
				write (" mise a jour recommandation entre " + Video1.getId() + " et " + Video2.getId() + " => " + link.toString()) ;
				return true;
			}
		}
		
		write("Recommandation refusee" );
		return false;
	}
}
