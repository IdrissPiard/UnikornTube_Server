package models.recommandation.bddInterface;

import java.util.List;

import models.recommandation.bddMySQL.MySqlNoSQL;
/**
 * @author Guigui
 */
public interface BddNoSql_Int {
	
	public static final String NOEUX_TYPE_PAGE_STANDARD = "PStand";
	
	public static final String NOEUX_TYPE_PAGE_RECHERCHE = "PRech";
	public static final String NOEUX_TYPE_VIDEO = "Video";
	public static final String NOEUX_PAGE_INDEX_DATA1 = "Index";
	
	public static final String LIEN_TYPE_PASSAGE = "Passage";
	public static final String LIEN_TYPE_RECOMMANDATION = "Reco";
	
	public static final String RECHERCHE_TOUT = "*";
	
	public static final int TEMPS_FLUSH_HEURE = 24 ; 
	public static final long TEMPS_FLUSH_MS = TEMPS_FLUSH_HEURE * 3600000 ; 
	
	public static BddNoSql_Int getBDD()
	{
		return MySqlNoSQL._Singleton;
	}
	
	public Noeud getNoeudParID( int parIdNoeud) ;
	
	/**
	 * Renvoit une liste de noeud correspondant aux critères fournit
	 * 	Exemple : 
	 * 		type = "Page" + BddNoSql_Int.RECHERCHE_TOUT
	 * 		data1 = ""
	 * 		data2 = NULL
	 * 		=> renvoit une liste de noeud dont le type commence par "Page", data1 vaut "" et on ne se soucie pas de data2
	 * @param type Valeur du champ type ou null si on ne s'y interesse pas
	 * @param data1 Valeur du champ data1 ou null si on ne s'y interesse pas
	 * @param data2 Valeur du champ data2 ou null si on ne s'y interesse pas
	 * @return liste de noeud
	 */
	public List <Noeud> getNoeudParTypeEtData( String type, String data1, String data2) ;
	
	/**
	 * Renvoit une liste de lien correspondant aux critères fournit
	 * 	Exemple : 
	 * 		type = "Page" + BddNoSql_Int.RECHERCHE_TOUT
	 * 		data1 = ""
	 * 		data2 = NULL
	 * 		=> renvoit une liste de lien dont le type commence par "Page", data1 vaut "" et on ne se soucie pas de data2
	 * @param type Valeur du champ type ou null si on ne s'y interesse pas
	 * @param data1 Valeur du champ data1 ou null si on ne s'y interesse pas
	 * @param data2 Valeur du champ data2 ou null si on ne s'y interesse pas
	 * @return liste de lien
	 */
	public List <EmptyLink> getLinkParTypeEtData( String type, String data1, String data2) ;
	
	public default Noeud getNoeudIndex()
	{
		List<Noeud> data = this.getNoeudParTypeEtData(NOEUX_TYPE_PAGE_STANDARD, NOEUX_PAGE_INDEX_DATA1, "");
		if (data.size() == 0)
		{
			Noeud locNoeudIndex = new Noeud(NOEUX_TYPE_PAGE_STANDARD, NOEUX_PAGE_INDEX_DATA1, "");
			int id = this.addNoeud( locNoeudIndex ) ;
			if (id == -1)
			{
				System.err.println("Erreur d'ajout et de get de Page INDEX");
				return null ;
			}
			locNoeudIndex.setId(id);
			return locNoeudIndex ;
		}
		return data.get(0);
		
	}
	
	/**
	 * Renvoit la derniere page visité par un utilisateur
	 * 	=> Premier lien en bdd 
	 * 			avec 	type = LIEN_TYPE_PASSAGE
	 * 					data1 = parIdData1
	 * 					data2 = DatePlusRecente
	 * 
	 * => Par defaut renvoit le noeud Index
	 * @param data1
	 * @return
	 */
	public Noeud getDernierNoeudVisite(int pardata1);
	
	/**
	 * Renvoit les links de noeud partant de Noeud avec pour data1 : pardata1 visité par un utilisateur
	 * 
	 * @param data1
	 * @param parNoeudDepart
	 * @return
	 */
	public List <EmptyLink> getParcours(int pardata1, Noeud parNoeudDepart);
	
	public List <EmptyLink> getLinkOfNoeud ( int parIdNoeud , int parLevel ) ; 
	
	
	/**
	 * Rajoute un noeud a la base de donnée
	 * @param parAjouter noeud a ajouter
	 * @return -1 en cas d'erreur ou l'id du noeud
	 */
	public int addNoeud( Noeud parAjouter) ;
	
	public boolean removeNoeud( Noeud parRetirer) ;
	
	/**
	 * Rajoute un lien a la base de donnée
	 * @param parAjouter lien a ajouters
	 * @return -1 en cas d'erreur ou l'id du lien
	 */
	public int addLink( EmptyLink parAjouter) ; 
	
	public boolean removeLink( EmptyLink parRetirer) ; 
	
	public boolean updateLink ( EmptyLink parUpdate) ;
	
	public boolean flushBdd () ;
}
