package models.recommandation.bddInterface;

public class EmptyLink {

	private int idNoeud1;
	private int idNoeud2;
	private String type;
	private String data1;
	private String data2;
	
	
	public EmptyLink(int IDnoeud1, int IDnoeud2, String type, String data1,
			String data2) {
		super();
		this.idNoeud1 = IDnoeud1;
		this.idNoeud2 = IDnoeud2;
		this.type = type;
		this.data1 = data1;
		this.data2 = data2;
	}
	
	public FullLink toFullLink()
	{
		return new FullLink(idNoeud1, idNoeud2, type, data1, data2);
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
	
	
}
	