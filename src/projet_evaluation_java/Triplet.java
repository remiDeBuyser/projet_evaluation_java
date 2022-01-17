package projet_evaluation_java;

public class Triplet {
	private int id_triplet;
	private String er;
	private String ec;
	private Intervalle intervalle;

	public Triplet(int id_triplet, String er, String ec, Intervalle intervalle) throws Exception {
		super();
		this.id_triplet = id_triplet;
		String er_sub = er.substring(0, 2);
		if(!er_sub.equals("In") && !er_sub.equals("RE") && !er_sub.equals("FE")) {
			throw new Exception("Erreur évenement référence");
		}
		
		String ec_sub = ec.substring(0, 2);
		if(!ec_sub.equals("In") && !ec_sub.equals("RE") && !ec_sub.equals("FE")) {
			throw new Exception("Erreur évenement contrainte");
		}
		
		this.er = er;
		this.ec = ec;
		this.intervalle = intervalle;
	}
	
	public Triplet(int id_triplet, String er, String ec) throws Exception {
		super();
		this.id_triplet = id_triplet;
		String er_sub = er.substring(0, 2);

		if(!er_sub.equals("In") && !er_sub.equals("RE") && !er_sub.equals("FE")) {
			throw new Exception("Erreur évenement référence");
		}
		
		String ec_sub = ec.substring(0, 2);

		if(!ec_sub.equals("In") && !ec_sub.equals("RE") && !ec_sub.equals("FE")) {
			throw new Exception("Erreur évenement contrainte");
		}
		
		this.er = er;
		this.ec = ec;
		this.intervalle = null;
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
	
	public boolean equals(Triplet t) {
		if(this.er.equals(t.getEr()) && this.ec.equals(t.getEc())) {
			if((this.intervalle != null && t.getIntervalle() != null && this.intervalle.equals(t.getIntervalle())) || (this.intervalle == null && t.getIntervalle() == null)) {
				return true;
			}
		}
		return false;
	}
	
	public float distance(Triplet t, int MAX) {
		float ipos;
		if(this.intervalle == null && t.getIntervalle() == null) ipos = 0;
		else if(this.intervalle == null || t.getIntervalle() == null) ipos = 1;
		else ipos = IPOS(t.getIntervalle());

		return (distanceEvenement(this.er,t.getEr()) + distanceEvenement(this.ec,t.getEr()) + ipos ) / MAX;
	}
	
	
	public int distanceEvenement(String a, String b) {
		if(a.equals(b))
			return 0;
		return 1;
	}
	
	public float IPOS(Intervalle i) {
		if(this.intervalle == null || i == null) {
			return 1;
		}
		return Math.min(pos(this.intervalle.getBi(), i), pos(this.intervalle.getBs(),i));
	}
	
	public float pos(int x, Intervalle i) {
		if(x > i.getBi() && x < i.getBs()) {
			return 0;
		}
		return Math.min(Math.abs(x-i.getBi()),Math.abs(x-i.getBs()));
	}
	
	public String toString() {
		return this.er + " " + this.ec + " " + this.intervalle;
	}
}
