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

	public int getBi() {
		return bi;
	}
	
	public void setBi(int bi) {
		this.bi = bi;
	}
	
	public int getBs() {
		return bs;
	}
	
	public void setBs(int bs) {
		this.bs = bs;
	}
	
	public int getIntervalle_id() {
		return intervalle_id;
	}
	
	public boolean equals(Intervalle i) {
		if(this.bi == i.getBi() && this.bs == i.getBs()) {
			return true;
		}
		return false;
	}
}
