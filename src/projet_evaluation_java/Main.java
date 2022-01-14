package projet_evaluation_java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		File f = new File("reglesCN.txt");
		String strCurrentLine;
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		while ((strCurrentLine = br.readLine()) != null) {
		    System.out.println(strCurrentLine);
		}
		

	}

}
