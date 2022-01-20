package projet_evaluation_java;

public class Triplet {
	private int id_triplet;
	private String er;
	private String ec;
	private Intervalle intervalle;

	/**
	 * Permet de définir un triplet avec un intervalle
	 * @param id_triplet
	 * @param er
	 * @param ec
	 * @param intervalle
	 * @throws Exception
	 */
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
	
	/**
	 * Permet de définir un triplet quand l'intervalle est égal à nct
	 * @param id_triplet
	 * @param er
	 * @param ec
	 * @throws Exception
	 */
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

	/**
	 * Permet de récupérer l'evenement référenciel er
	 * @return String, er
	 */
	public String getEr() {
		return er;
	}
	
	/**
	 * Permet de définir l'evenement référenciel er
	 * @param String, er
	 */
	public void setEr(String er) {
		this.er = er;
	}
	
	/**
	 * Permet de définir l'evenement contrainte ec
	 * @return String, ec
	 */
	public String getEc() {
		return ec;
	}
	
	/**
	 * Permet de définir l'evenement contrainte ec
	 * @param String, ec
	 */
	public void setEc(String ec) {
		this.ec = ec;
	}
	
	/**
	 * Permet de récupérer l'intervalle du triplet
	 * @return Intervalle, intervalle
	 */
	public Intervalle getIntervalle() {
		return intervalle;
	}
	
	/**
	 * Permet de définir l'intervalle du triplet
	 * @param Intervalle, intervalle
	 */
	public void setIntervalle(Intervalle intervalle) {
		this.intervalle = intervalle;
	}
	
	/**
	 * Permet de récupérer l'id du triplet
	 * @return int, id_triplet
	 */
	public int getId_triplet() {
		return id_triplet;
	}
	
	/**
	 * Permet de comparer 2 triplets
	 * @param t, le second triplet
	 * @return boolean, true s'ils sont égaux false sinon
	 */
	public boolean equals(Triplet t) {
		if(this.er.equals(t.getEr()) && this.ec.equals(t.getEc())) {
			if((this.intervalle != null && t.getIntervalle() != null && this.intervalle.equals(t.getIntervalle())) || (this.intervalle == null && t.getIntervalle() == null)) {
				return true;
			}
		}
		return false;
	} 
	
	/**
	 * Permet de calculer la distanc entre 2 triplets
	 * @param t, le second triplet
	 * @param MAX, dateMax de toute la base des cas
	 * @return float, la distance entre 0 et 1
	 */
	public float distance(Triplet t, int MAX) {
		float ipos;
		if(this.intervalle == null && t.getIntervalle() == null) ipos = 0;
		else if(this.intervalle == null || t.getIntervalle() == null) ipos = 1;
		else {
			ipos = IPOS(t.getIntervalle());
		}
		return distanceEvenement(this.er,t.getEr()) + distanceEvenement(this.ec,t.getEc()) + ipos  / MAX;
	}
	
	/**
	 * Permet de calculer la distanc eentre 2 evenements
	 * @param a, evenement 1
	 * @param b, evenement 2
	 * @return int, 1 si différent 0 si égaux 
	 */
	public int distanceEvenement(String a, String b) {
		if(a.equals(b))
			return 0;
		return 1;
	}
	
	/**
	 * Permet de calculer la distance entre les intervalles
	 * @param i, le second intervalle
	 * @return float, entre 0 et 1, plus c'est proche de 0 plus ils sont identiques
	 */
	public float IPOS(Intervalle i) {
		if (this.getIntervalle().getBi() >= Math.max(this.getIntervalle().getBi(), i.getBi()) && this.getIntervalle().getBi() <= Math.min(this.getIntervalle().getBs(), i.getBs())) {
			return 0;
		}
		return Math.min(pos(this.intervalle.getBi(), i), pos(this.intervalle.getBs(),i));
	}
	
	/**
	 * Permet de savoir si x est dans l'intervalle i
	 * @param x
	 * @param i, intervalle
	 * @return float, entre 0 et 1, plus c'est proche de 0 plus x est proche de l'intervalle (0 : x est dans l'intervalle)
	 */
	public float pos(int x, Intervalle i) {
		if(x >= i.getBi() && x <= i.getBs()) {
			return 0;
		}
		return Math.min(Math.abs(x-i.getBi()),Math.abs(x-i.getBs()));
	}
	
	public String toString() {
		String intervalle = "nct";
		if(this.intervalle != null)
			intervalle = this.intervalle.toString();
		return "("+this.er + ", " + this.ec + ", " + intervalle+")";
	}
}
