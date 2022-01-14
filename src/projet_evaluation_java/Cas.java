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
}
