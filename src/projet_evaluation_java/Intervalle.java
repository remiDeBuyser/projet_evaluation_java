package projet_evaluation_java;

public class Intervalle {
	private int intervalle_id;
	private int bi;
	private int bs;
	
	public Intervalle(int intervalle_id, int bi, int bs) {
		super();
		this.intervalle_id = intervalle_id;
		this.bi = bi;
		this.bs = bs;
	}

	/**
	 * Permet de récupérer la borne inférieure
	 * @return int, bi
	 */
	public int getBi() {
		return bi;
	}
	
	/**
	 * Permet de définir la borne inférieure
	 * param int, la borne inférieur
	 */
	public void setBi(int bi) {
		this.bi = bi;
	}
	
	/**
	 * Permet de récupérer la borne supérieure
	 * @return int, bs
	 */
	public int getBs() {
		return bs;
	}
	
	/**
	 * Permet de définir la borne supérieure
	 * @return int, bs
	 */
	public void setBs(int bs) {
		this.bs = bs;
	}
	
	/**
	 * Permet de récupérer l'id de l'intervalle
	 * @return int, l'id
	 */
	public int getIntervalle_id() {
		return intervalle_id;
	}
	
	/**
	 * Permet de comparer 2 intervalles
	 * @return boolean, true s'ils sont pareil false sinon
	 */
	public boolean equals(Intervalle i) {
		if(this.bi == i.getBi() && this.bs == i.getBs()) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "[" + this.bi + ", " + this.bs + "]";
	}
}
