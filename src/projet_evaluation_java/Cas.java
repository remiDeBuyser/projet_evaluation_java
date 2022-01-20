package projet_evaluation_java;

import java.util.ArrayList;

public class Cas {
	private int id;
	private ArrayList<Triplet> triplets;
	private Etat etat;
	
	public Cas(int id, ArrayList<Triplet> triplets, Etat etat) {
		super();
		this.id = id;
		this.triplets = triplets;
		this.etat = etat;
	}
	
	/**
	 * Permet de récupérer les triplets du cas
	 * @return triplets, liste des triplets du cas
	 */
	public ArrayList<Triplet> getTriplets() {
		return triplets;
	}
	
	/**
	 * Permet de définir les triplets du cas
	 * @param triplets, liste triplets 
	 */
	public void setTriplets(ArrayList<Triplet> triplets) {
		this.triplets = triplets;
	}
	
	/**
	 * Permet de récupéré l'état du cas
	 * @return etat, etat du cas
	 */
	public Etat getEtat() {
		return etat;
	}
	
	/**
	 * Permet de définir l'état
	 * @param etat, état du cas
	 */
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	/**
	 * Permet de récupérer l'id
	 * @return id du cas
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Permet de retourner la similarité entre 2 cas
	 * @param Pj, second cas
	 * @param MAX, dateMax de toute la base de cas
	 * @return float, entre 0 et 1
	 */
	public float getSimilarity(Cas Pj, int MAX) {
		return 1 - distance(Pj, MAX);
	}
	
	/**
	 * Permet de calculer la distance entre 2 cas
	 * @param Pj, le second cas
	 * @param MAX, dateMax de toute la base de cas
	 * @return float, entre 0 et 1
	 */
	public float distance(Cas Pj, int MAX) {
		int size = this.triplets.size();
		
		if ( size != Pj.getTriplets().size() ) return 1;
		else {
			boolean equal = true;
			boolean notequal = true;
			
			for (int i = 0; i<size; i++) {
				if (!this.triplets.get(i).equals(Pj.getTriplets().get(i))) 
					equal = false;
				if (this.triplets.get(i).equals(Pj.getTriplets().get(i))) 
					notequal = false;
			}
			if (equal) return 0;
			if (notequal) return 1;
		}
		
		float sum = 0;
		for (int i = 0; i<size; i++) {
			sum += this.triplets.get(i).distance(Pj.getTriplets().get(i), MAX);
		}
		
		return sum/(3*size);
	}
	
	public String toString() {
		String res  =  "Cas n°" + this.id + " avec un état de normal\n";
		for (Triplet triplet : triplets) {
			String ct = "";
			if(triplet.getIntervalle() == null) {
				ct+="nct";
			}
			else {
				ct+= "[" + triplet.getIntervalle().getBi() + ", " + triplet.getIntervalle().getBs() +"]";
			}
			res += "("+triplet.getEr() + ", " + triplet.getEc() + ", "+ ct +")";
		}	
		return res;
	}

}
