package projet_evaluation_java;

public class Triplet {
	private int id_triplet;
	private String er;
	private String ec;
	private Intervalle intervalle;

	public Triplet(int id_triplet, String er, String ec, Intervalle intervalle) {
		super();
		this.id_triplet = id_triplet;
		this.er = er;
		this.ec = ec;
		this.intervalle = intervalle;
	}
	
	public String getEr() {
		return er;
	}
	
	public void setEr(String er) {
		this.er = er;
	}
	
	public String getEc() {
		return ec;
	}
	
	public void setEc(String ec) {
		this.ec = ec;
	}
	
	public Intervalle getIntervalle() {
		return intervalle;
	}
	
	public void setIntervalle(Intervalle intervalle) {
		this.intervalle = intervalle;
	}
	
	public int getId_triplet() {
		return id_triplet;
	}
}
