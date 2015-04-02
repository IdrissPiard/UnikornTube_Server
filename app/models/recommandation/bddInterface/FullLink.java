package models.recommandation.bddInterface;

public class FullLink {

	private Noeud noeud1;
	private Noeud noeud2;
	private String type;
	private String data1;
	private String data2;
	
	
	public FullLink(int IDnoeud1, int IDnoeud2, String type, String data1,
			String data2) {
		this(BddNoSql_Int.getBDD().getNoeudParID(IDnoeud1),
				BddNoSql_Int.getBDD().getNoeudParID(IDnoeud2),
				type,
				data1,
				data2
				);
	}
	
	public FullLink(Noeud noeud1, Noeud noeud2, String type, String data1,
			String data2) {
		super();
		this.noeud1 = noeud1;
		this.noeud2 = noeud2;
		this.type = type;
		this.data1 = data1;
		this.data2 = data2;
	}
	
	public EmptyLink toEmptyLink()
	{
		return new EmptyLink(noeud1.getId(), noeud2.getId(), type, data1, data2);
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
}
