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
	
	public ArrayList<Triplet> getTriplets() {
		return triplets;
	}
	
	public void setTriplets(ArrayList<Triplet> triplets) {
		this.triplets = triplets;
	}
	
	public Etat getEtat() {
		return etat;
	}
	
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	
	public int getId() {
		return id;
	}
	
	public float getSimilarity(Cas Pj) {
		return 1 - distance(Pj);
	}
	
	public float distance(Cas Pj) {
		int dist = 0;
		int size = this.triplets.size();
		
		if ( size != Pj.getTriplets().size() ) return 1;
		else {
			boolean equal = true;
			boolean notequal = true;
			
			for (int i = 0; i<size; i++) {
				if (!this.triplets.get(i).equals(Pj.getTriplets().get(i))) equal = false;
				if (this.triplets.get(i).equals(Pj.getTriplets().get(i))) notequal = false;
			}
			if (equal) return 0;
			if (notequal) return 1;
		}
		
		int somme = 0;
		for (int i = 0; i<size; i++) {
			somme += this.triplets.get(i).distance(Pj.getTriplets().get(i));
		}
		
		return somme/(3*size);
	}

}
