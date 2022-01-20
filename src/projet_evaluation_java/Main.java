package projet_evaluation_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in); // pour pouvoir entrer des données dans la ligne de commande
		
		int MAX = 0; // la date max 
		File f = new File("reglesCN.txt"); // ouverture du fichier contenant les données
		String strCurrentLine;
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		ArrayList<Triplet> triplets = new ArrayList<>(); // les triplets de chacun des cas 
		ArrayList<Cas> liste_cas = new ArrayList<>(); // la liste de tous les cas
		
		// initialisation des ids de chacun des objets
		int id_intervalle = 0; 
		int id_triplets = 0;
		int id_cas = 0;
		int id_etat = 0;
		
		// tant qu'on a pas fini de parcourir le fichier on continue
		while ((strCurrentLine = br.readLine()) != null) {
			// si ce n'est pas une ligne vide on continue
			if(!strCurrentLine.equals("")) {
				// on traite le string pour pouvoir avoir un tableau des triplets au format string et on itère dedans
				for (String triplet : strCurrentLine.split(" \\* ")) {
					// on remplace les espaces par du rien
					triplet = triplet.replaceAll(" ", "");
					// si le string contient un "[" ça veut dire qu'il y a un intervalle 
					if(triplet.contains("[")) {
						// création de l'intervalle grâce au string puis création du triplet avec intervalle et ajout dans la liste des triplets
						try {
							Intervalle intervalle = new Intervalle(id_intervalle++, Integer.parseInt(triplet.split(",")[2].substring(1)),Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2)));
							triplets.add(new Triplet(id_triplets, triplet.split(",")[0].substring(1), triplet.split(",")[1],intervalle));
							// récupération de MAX, la dateMAX
							if (Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2)) > MAX) MAX = Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					} else {
						// création d'un triplet avec nct et ajout dans la liste des triplets du cas
						try {
							triplets.add(new Triplet(id_triplets, triplet.split(",")[0].substring(1), triplet.split(",")[1]));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					id_triplets ++;
				}
				// ajout du cas à la liste de tous les cas
				liste_cas.add(new Cas(id_cas++, triplets, new Normal(id_etat++)));
				// on remet à 0 la liste des triplets pour pouvoir l'utiliser pour le prochain cas
				triplets = new ArrayList<>();
			}
		}
		
		System.out.println("\n Donnees enregistrees dans l'app\n");
		
		System.out.println("Veuillez entrer un nouveau cas : ");
		// préparation du cas que nous allons entrer
		Cas new_cas = null;
		// lecture du nouveau cas
		String new_line = sc.nextLine();
		// on verifie qu'il n'est pas String = ""
		if(!new_line.equals("")) {
			// on split le string pour récupérer les triplets au format string
			for (String triplet : new_line.split(" \\* ")) {
				// on remplace les espaces par des chaines vide
				triplet = triplet.replaceAll(" ", "");
				// si le string contient un "[" ça veut dire qu'il y a un intervalle 
				if(triplet.contains("[")) {
					try {
						// création de l'intervalle grâce à la chaine de caractère du triplet 
						Intervalle intervalle = new Intervalle(id_intervalle++, Integer.parseInt(triplet.split(",")[2].substring(1)),Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2)));
						// création du triplet et ajout du triplet à la liste des triplets du cas
						triplets.add(new Triplet(id_triplets, triplet.split(",")[0].substring(1), triplet.split(",")[1],intervalle));

//						if (Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2)) > MAX) MAX = Integer.parseInt(triplet.split(",")[3].substring(0,triplet.split(",")[3].length()-2));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				} else {
					try {
						// création du triplet avec nct et ajout à la liste des triplets du cas 
						triplets.add(new Triplet(id_triplets, triplet.split(",")[0].substring(1), triplet.split(",")[1]));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				id_triplets ++;
			}
			// création de l'objet cas correspondant au nouveau cas
			new_cas = new Cas(id_cas++, triplets, new Normal(id_etat++));
		}
		System.out.println("Nombre de triplets du cas : " + triplets.size());
		
		// on récupère le nombre de cas qui ont autant de triplets que le nouveau cas
		ArrayList<Cas> cas_nb_triplets = new ArrayList<>();
		for (Cas cas2 : liste_cas) {
			if(cas2.getTriplets().size() == triplets.size()) {
				cas_nb_triplets.add(cas2);
			}
		}
		System.out.println("Nombre de cas dans la base des cas avec autant de triplets : " + cas_nb_triplets.size());
		
		// Affichage de la similarité de chacun des cas de la base avec le nouveau cas
		if(new_cas != null) {
			for(Cas cas : cas_nb_triplets) {
				System.out.println(cas);
				System.out.println("\tSimilarité : " + new_cas.getSimilarity(cas, MAX));
			}			
		}
		
		// création du fichier pour stocker les défaillances
		PrintWriter writer = new PrintWriter("defaillants.txt","UTF-8");
		
		// pour chacun des cas de la liste de cas, création des cas défaillants du cas
		for(Cas cas : liste_cas) {
			writer.println("Comportements défaillants du cas n°" +cas.getId());
			int k = 0;
			// création de tous les cas défaillants du cas en cours
			for(Triplet triplet: cas.getTriplets()) {
				// il faut que ce soit un triplet avec un intervalle 
				if(triplet.getIntervalle() != null) {
					// va nous permettre de récupérer l'evenement défaillant pour pouvoir le reporter sur les triplets suivants
					String event = "";
					for(int l = 0; l<cas.getTriplets().size();l++) {
						if(k == l) {
							// on récupère l'evenemnt qui va être défaillant
							event = cas.getTriplets().get(l).getEc();
							
							// on écrit dans le fichier le triplet, avec S_ avant l'evenement qui va être défaillant
							writer.print("("+cas.getTriplets().get(l).getEr()+", S_" + cas.getTriplets().get(l).getEc() + ", ["+cas.getTriplets().get(l).getIntervalle().getBs()+","+cas.getTriplets().get(l).getIntervalle().getBs()+"])");
						}
						else {
							// si le triplet contient l'évenement défaillant il faut le remplacer par S_+evenement
							if(!event.equals("") && cas.getTriplets().get(l).toString().contains(event)) {
								if(event.equals(cas.getTriplets().get(l).getEr())) {
									writer.print("(S_"+event+", " + cas.getTriplets().get(l).getEc() + ", ["+cas.getTriplets().get(l).getIntervalle().getBs()+","+cas.getTriplets().get(l).getIntervalle().getBs()+"])");									
								} else {
									writer.print("("+cas.getTriplets().get(l).getEr()+", S_" + event + ", ["+cas.getTriplets().get(l).getIntervalle().getBs()+","+cas.getTriplets().get(l).getIntervalle().getBs()+"])");									
								}
							} else {
								writer.print(cas.getTriplets().get(l));
							}
						}
						// affichage de l'étoile entre chacun des triplets 
						if(l != cas.getTriplets().size()-1) {
							writer.print(" * ");
						} else {// si c'est la fin du cas on affiche l'evenement défaillant
							writer.print(" -> absence de " + event);
						}
					}
					writer.println("\n");
				}
				k++;
			}
			writer.println("\n");
		}// fermeture du fichier
		writer.close();
	}
}
