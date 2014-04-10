import java.io.*;
import java.util.*;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor.Color;


public class EcouterCouleur {
	InfoCap capteur;
	EcouterCouleur(){
		capteur = new InfoCap();
	}
	
	Couleur relever() {
		Couleur c = new Couleur("");
		System.out.println("Nouvelle prise de couleur appuyez pour continuez "+c.getNom());
		Button.waitForAnyPress();
		
		int rouge = capteur.getrouge();
		int vert = capteur.getvert();
		int bleu = capteur.getbleu();
		System.out.println("ROUGE  ->"+rouge);
		System.out.println("VERT   ->"+vert);
		System.out.println("BlEU   ->"+bleu);
		
		c.setRouge(rouge);
		c.setVert(vert);
		c.setBleu(bleu);
		return c;

	}
	
	public ArrayList<Couleur> Lecture_du_fichier() throws IOException{
		
		File f = new File("calibrage.txt");
		InputStream flux = new FileInputStream(f);
		InputStreamReader lecture = new InputStreamReader(flux);
		BufferedReader r = new BufferedReader(lecture);
		String s = "";
		
		ArrayList<ArrayList<Couleur>> ensemble = new ArrayList<ArrayList<Couleur>>();
		ArrayList<Couleur> moyen    = new ArrayList<Couleur>() ;
		ArrayList<Couleur> bleu 	= new ArrayList<Couleur>() ;
		ArrayList<Couleur> blanc 	= new ArrayList<Couleur>() ; 
		ArrayList<Couleur> rouge	= new ArrayList<Couleur>() ; 
		ArrayList<Couleur> noir		= new ArrayList<Couleur>() ; 
		ArrayList<Couleur> jaune 	= new ArrayList<Couleur>() ; 
		ArrayList<Couleur> vert		= new ArrayList<Couleur>() ; 
		
		ensemble.add(bleu); 
		ensemble.add(blanc); 
		ensemble.add(rouge); 
		ensemble.add(noir); 
		ensemble.add(jaune); 
		ensemble.add(vert); 
		
		for (ArrayList<Couleur>  als : ensemble) {
			for (Couleur color : als) {
				s= r.readLine();
				int  result =s.lastIndexOf(":");
				color.setRouge(new Integer(s.substring(result)));
				s=s.substring(0,result);
				result =s.lastIndexOf(":");
				color.setBleu(new Integer(s.substring(result)));
				s=s.substring(0,result);
				result =s.lastIndexOf(":");
				color.setVert(new Integer(s.substring(result)));
				s=s.substring(0,result);
				color.setNom(s.substring(result));
				als.add(color);
			}
		}
		for (ArrayList<Couleur>  als : ensemble) {
			 moyen.add(moyenCouleur(als));
		}
	return moyen;
	}
	
	Couleur moyenCouleur(ArrayList<Couleur> s ){
		 double  r , g , b ;
		 r = g = b = 0 ; 
		 int cpt =0;
		 String nom = "" ; 
		 for (Couleur couleur : s) {
			 nom = couleur.getNom(); 
			 r+=couleur.getRouge()  ;
			 g+=couleur.getVert()  ;
			 b+=couleur.getBleu()  ;
			 cpt++;
		 }
		 Couleur retour = new  Couleur(nom); 	
		
		 retour.setRouge( r/=cpt );
		 retour.setVert( g/=cpt  );
		 retour.setBleu(b/=cpt  );
		 
		 return retour ; 
	}
	public Couleur getCouleur(ArrayList<Couleur> moyen){
		Couleur c = relever();
		double [] tab ; 
		 tab = new double[6] ; 
		int i = 0 ; 
		 for (Couleur d : moyen) {
			tab[i] = Math.sqrt(Math.pow(d.getRouge()-c.getRouge(),2) +
					Math.pow(d.getVert()-c.getVert(),2)+ 
					Math.pow(d.getBleu()-c.getBleu(),2) ); 
			i++ ; 
		 }
		 double  min = tab[0] ; 
		 int index  = 0 ; 
		 for (int j = 0; j < tab.length; j++) {
			if(tab[j]<min)
			{
				min = tab[j]; 
				index = j ; 
			}
		}
		 c.setNom(moyen.get(index).getNom());
		 System.out.println("votre couleur  est le"+c.getNom());
		return c ; 
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
