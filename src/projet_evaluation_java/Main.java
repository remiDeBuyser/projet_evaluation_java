package projet_evaluation_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws IOException {
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
		while ((strCurrentLine = br.readLine()) != null && i <= 2) {
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
				System.out.println(strCurrentLine);
				i++;
			}
		}
		
		System.out.println("\n Donnees enregistrees dans l'app\n");
		
		for (Cas cas : liste_cas) {
			System.out.println(cas);
		}
		System.out.println();
		System.out.println("Similartite : ");
		System.out.println(liste_cas.get(0).getSimilarity(liste_cas.get(1),MAX));
	}

}
