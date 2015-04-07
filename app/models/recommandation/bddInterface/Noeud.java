package models.recommandation.bddInterface;

import java.util.ArrayList;

public class Noeud {

	

	private int id;
	private String type;
	private String data1;
	private String data2;
	
	private ArrayList<EmptyLink> listeLink ;

	public Noeud(int id, String type, String data1, String data2) {
		super();
		this.id = id;
		this.type = type;
		this.data1 = data1;
		this.data2 = data2;
		this.listeLink = new ArrayList<EmptyLink>();
	}
	
	public Noeud (String type, String data1, String data2)
	{
		this(-1, type, data1, data2);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ArrayList<EmptyLink> getListeLink() {
		return listeLink;
	}
}
