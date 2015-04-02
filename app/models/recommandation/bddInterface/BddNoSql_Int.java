package models.recommandation.bddInterface;

import java.util.List;

public interface BddNoSql_Int {

	public static BddNoSql_Int getBDD()
	{
		return null;
	}
	
	public Noeud getNoeudParID( int parIdNoeud) ;
	
	public List <EmptyLink> getLinkOfNoeud ( int parIdNoeud ) ; 
	
	public boolean addNoeud( Noeud parAjouter) ;
	
	public boolean removeNoeud( Noeud parRetirer) ;
	
	public boolean addLink( EmptyLink parAjouter) ; 
	
	public boolean removeLink( EmptyLink parRetirer) ; 
	
}
