package projet_evaluation_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int i = 0;
		int MAX = 0;
		File f = new File("test_regles.txt");
		String strCurrentLine;
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		ArrayList<Triplet> triplets = new ArrayList<>();
		ArrayList<Cas> liste_cas = new ArrayList<>();
		int id_intervalle = 0;
		int id_triplets = 0;
		int id_cas = 0;
		int id_etat = 0;
		while ((strCurrentLine = br.readLine()) != null) {
			if(!strCurrentLine.equals("")) {
				for (String triplet : strCurrentLine.split(" \\* ")) {
					triplet = triplet.replaceAll(" ", "");
					if(triplet.contains("[")) {
						try {
							Intervalle intervalle = new Intervalle(id_intervalle++, Integer.parseInt(triplet.split(",")[2].substring(1)),Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2)));
							triplets.add(new Triplet(id_triplets, triplet.split(",")[0].substring(1), triplet.split(",")[1],intervalle));
							if (Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2)) > MAX) MAX = Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					} else {
						try {
							triplets.add(new Triplet(id_triplets, triplet.split(",")[0].substring(1), triplet.split(",")[1]));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					id_triplets ++;
				}
				liste_cas.add(new Cas(id_cas++, triplets, new Normal(id_etat++)));
				triplets = new ArrayList<>();
				//System.out.println(strCurrentLine);
				i++;
			}
		}
		
		System.out.println("\n Donnees enregistrees dans l'app\n");
		
		//for (Cas cas : liste_cas) {
		//	System.out.println(cas);
		//}
		System.out.println("Veuillez entrer un nouveau cas : ");
		Cas new_cas = null;
		String new_line = sc.nextLine();
		if(!new_line.equals("")) {
			for (String triplet : new_line.split(" \\* ")) {
				triplet = triplet.replaceAll(" ", "");
				System.out.println(triplet);
				if(triplet.contains("[")) {
					try {
						Intervalle intervalle = new Intervalle(id_intervalle++, Integer.parseInt(triplet.split(",")[2].substring(1)),Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2)));
						triplets.add(new Triplet(id_triplets, triplet.split(",")[0].substring(1), triplet.split(",")[1],intervalle));
						if (Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2)) > MAX) MAX = Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				} else {
					try {
						triplets.add(new Triplet(id_triplets, triplet.split(",")[0].substring(1), triplet.split(",")[1]));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				id_triplets ++;
			}
			new_cas = new Cas(id_cas++, triplets, new Normal(id_etat++));
		}
		System.out.println("Nombre de triplets du cas : " + triplets.size());
		ArrayList<Cas> cas_nb_triplets = new ArrayList<>();
		for (Cas cas2 : liste_cas) {
			if(cas2.getTriplets().size() == triplets.size()) {
				cas_nb_triplets.add(cas2);
			}
		}
		System.out.println("Nombre de cas dans la base des cas avec autant de triplets : " + cas_nb_triplets.size());
		if(new_cas != null) {
			for(Cas cas : cas_nb_triplets) {
				System.out.println(cas);
				System.out.println("\tSimilarité : " + new_cas.getSimilarity(cas, MAX));
			}			
		}
	}

}
