package models.recommandation.bddInterface;

/**
 * EmptyLink est une classe représentant un lien entre deux noeuds.
 * La difference avec un FullLink est qu'un EmptyLink ne possède que les ID des noeuds dont il fait la jointure
 * @author Guigui
 *
 */
public class EmptyLink {

	private int idNoeud1;
	private int idNoeud2;
	private int id;
	private String type;
	private String data1;
	private String data2;
	
	/**
	 * Creer un lien avec tout les paramètres
	 * @param IDnoeud1
	 * @param IDnoeud2
	 * @param id
	 * @param type
	 * @param data1
	 * @param data2
	 */
	public EmptyLink(int IDnoeud1, int IDnoeud2,int id, String type, String data1,
			String data2) {
		super();
		this.idNoeud1 = IDnoeud1;
		this.idNoeud2 = IDnoeud2;
		this.id = id;
		this.type = type;
		this.data1 = data1;
		this.data2 = data2;
	}
	
	/**
	 * Creer un lien vide sans id (id = -1)
	 * @param IDnoeud1
	 * @param IDnoeud2
	 * @param type
	 * @param data1
	 * @param data2
	 */
	public EmptyLink(int IDnoeud1, int IDnoeud2, String type,
			String data1, String data2) {
		this(IDnoeud1, IDnoeud2, -1, type, data1, data2);
	}

	public FullLink toFullLink()
	{
		return new FullLink(idNoeud1, idNoeud2,id, type, data1, data2);
	}


	public int getIdNoeud1() {
		return idNoeud1;
	}


	public void setIdNoeud1(int idNoeud1) {
		this.idNoeud1 = idNoeud1;
	}


	public int getIdNoeud2() {
		return idNoeud2;
	}


	public void setIdNoeud2(int idNoeud2) {
		this.idNoeud2 = idNoeud2;
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
		result = prime * result + idNoeud1;
		result = prime * result + idNoeud2;
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
			return false;
		EmptyLink other = (EmptyLink) obj;
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
		if (idNoeud1 != other.idNoeud1)
			return false;
		if (idNoeud2 != other.idNoeud2)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
	