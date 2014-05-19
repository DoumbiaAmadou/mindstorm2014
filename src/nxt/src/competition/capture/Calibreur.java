package competition.capture; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;

import competition.Couleur;
public class Calibreur {
	
	
	File  calibrageFile = new File("calibrage.txt");
	FileOutputStream flux = new FileOutputStream(calibrageFile);
	OutputStreamWriter ecriture = new OutputStreamWriter(flux);
	BufferedWriter r = new BufferedWriter(ecriture);
	static ArrayList<Couleur> hm = new ArrayList<Couleur>() ; 
	ColorHTSensor s = new ColorHTSensor(SensorPort.S4) ; 
	
	public Calibreur() throws IOException {
//		// TODO Auto-generated constructor stub
//		hm = new ArrayList<Couleur>();
//		hm.add(new Couleur("rouge"));
//		hm.add(new Couleur("blanc"));
//		hm.add(new Couleur("bleu"));
//		hm.add(new Couleur("jaune"));
//		hm.add (new Couleur("vert"));
//		hm.add(new Couleur("noir"));
//		
//		for (int i = 0; i < 3; i++) {
//		for (Couleur  c :hm) {
//			
//				calibrer(c); 
//			}
//		}

		ArrayList<ArrayList<Couleur>> ensemble = new ArrayList<ArrayList<Couleur>>(); 
		
		ArrayList<Couleur> bleu 	= new ArrayList<Couleur>() ;
		
		ArrayList<Couleur> blanc 	= new ArrayList<Couleur>() ; 
		ArrayList<Couleur> rouge= new ArrayList<Couleur>() ; 
		ArrayList<Couleur> noir	= new ArrayList<Couleur>() ; 
		ArrayList<Couleur> jaune 	= new ArrayList<Couleur>() ; 
		ArrayList<Couleur> vert	= new ArrayList<Couleur>() ; 
		
		ensemble.add(bleu); 
		ensemble.add(blanc); 
		ensemble.add(rouge); 
		ensemble.add(noir); 
		ensemble.add(jaune); 
		ensemble.add(vert); 
		
		
			for (int i = 0; i <10; i++) {
				
				blanc.add(new Couleur("Blanc"));
				noir.add(new Couleur("Noir"));
				bleu.add(new Couleur("Bleu"));
				jaune.add(new Couleur("Jaune"));
				rouge.add(new Couleur("Rouge"));
				vert.add(new Couleur("Vert"));
			}
		for (ArrayList<Couleur>  als : ensemble) {
			for (Couleur color : als) {
				
				calibrer(color) ; 
			}
		}
		
		for (ArrayList<Couleur>  als : ensemble) {
			 hm.add(moyenCouleur(als));
		}
			
	r.close();
}

private void calibrer(Couleur c ) throws IOException {
	
	System.out.println("Nouvelle prise de couleur appuyez pour continuez "+c.getNom());
	Button.waitForAnyPress();
	
	int rouge = s.getRGBComponent(Color.RED);
	int vert = s.getRGBComponent(Color.GREEN); 
	int bleu = s.getRGBComponent(Color.BLUE);
	c.setRouge(rouge);
	c.setVert(vert);
	c.setBleu(bleu); 
	r.write(c.nom+":"+c.vert+":"+c.bleu+":"+c.rouge);
	r.newLine();
	r.flush();
	System.out.println("ROUGE  ->"+rouge);
	System.out.println("VERT   ->"+vert);
	System.out.println("BlEU   ->"+bleu);
	
	
	
	
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
 
 
 Couleur distanceMoyen (ArrayList<Couleur> moyen) throws IOException{
	 Couleur c = new Couleur(""); 
	 calibrer(c);
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
	 System.out.println("votre couleur  est le "+c.getNom());
	return c ;  
 }

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Au	to-generated method stub
		//Calibreur Cali = new Calibreur();
		
		/*Cali.distanceMoyen(hm);
		Button.waitForAnyPress();
		Cali.distanceMoyen(hm);
		Button.waitForAnyPress();
		Cali.distanceMoyen(hm);
		Button.waitForAnyPress();
		Cali.distanceMoyen(hm);
		Button.waitForAnyPress();
		Cali.distanceMoyen(hm);
		Button.waitForAnyPress();
		Cali.distanceMoyen(hm);
		Button.waitForAnyPress();
		Cali.distanceMoyen(hm);
		Button.waitForAnyPress();
		Cali.distanceMoyen(hm);
		Button.waitForAnyPress();
		Cali.distanceMoyen(hm);
		Button.waitForAnyPress();*/
		
	}

}
