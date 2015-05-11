package models.recommandation.bddInterface;

import java.util.List;

public abstract class BddNoSQLMere implements BddNoSql_Int {
	
	
	public Noeud getNoeudIndex()
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
}
