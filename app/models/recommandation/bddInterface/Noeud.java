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

	@Override
	public String toString() {
		return "Noeud [id=" + id + ", t=" + type + ", d1=" + data1
				+ ", d2=" + data2 + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noeud other = (Noeud) obj;
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
		if (listeLink == null) {
			if (other.listeLink != null)
				return false;
		} else if (!listeLink.equals(other.listeLink))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
