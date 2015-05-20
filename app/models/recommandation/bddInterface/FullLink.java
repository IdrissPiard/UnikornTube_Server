package models.recommandation.bddInterface;

/**
 * FullLink est une classe representant un lien entre deux noeuds
 * Un fullLink possède des references vers les noeuds concrets existant
 * Les liens pouvant symbolisés n'importe quelle donnée, les champs sont stocké en string
 * @author Guigui
 *
 */
public class FullLink {

	private Noeud noeud1;
	private Noeud noeud2;
	private int id;
	private String type;
	private String data1;
	private String data2;
	
	
	public FullLink(int IDnoeud1, int IDnoeud2, int id, String type, String data1,
			String data2) {
		this(BddNoSqlFactory.getBDD().getNoeudParID(IDnoeud1),
				BddNoSqlFactory.getBDD().getNoeudParID(IDnoeud2),
				id,
				type,
				data1,
				data2
				);
	}
	
	public FullLink(Noeud noeud1, Noeud noeud2,int id, String type, String data1,
			String data2) {
		super();
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.id = id ;
		this.type = type;
		this.data1 = data1;
		this.data2 = data2;
	}
	
	public EmptyLink toEmptyLink()
	{
		return new EmptyLink(noeud1.getId(), noeud2.getId(), id, type, data1, data2);
	}
	
	public Noeud getNoeud1() {
		return noeud1;
	}
	public void setNoeud1(Noeud noeud1) {
		this.noeud1 = noeud1;
	}
	public Noeud getNoeud2() {
		return noeud2;
	}
	public void setNoeud2(Noeud noeud2) {
		this.noeud2 = noeud2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data1 == null) ? 0 : data1.hashCode());
		result = prime * result + ((data2 == null) ? 0 : data2.hashCode());
		result = prime * result + id;
		result = prime * result + ((noeud1 == null) ? 0 : noeud1.hashCode());
		result = prime * result + ((noeud2 == null) ? 0 : noeud2.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
		{
			if (obj instanceof EmptyLink)
			{
				return this.toEmptyLink().equals((EmptyLink)obj);
			}
			
			return false;
		}
		FullLink other = (FullLink) obj;
		if (data1 == null) {
			if (other.data1 != null)
				return false;
		} else if (!data1.equals(other.data1))
			return false;
		if (data2 == null) {
			if (other.data2 != null)
				return false;
		} else if (!data2.equals(other.data2))
			return false;
		if (id != other.id)
			return false;
		if (noeud1 == null) {
			if (other.noeud1 != null)
				return false;
		} else if (!noeud1.equals(other.noeud1))
			return false;
		if (noeud2 == null) {
			if (other.noeud2 != null)
				return false;
		} else if (!noeud2.equals(other.noeud2))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
