package competition;

import java.io.IOException;

import competition.ecouteur.EcouterCouleur;
import competition.ecouteur.EcouteurPosition;

import lejos.nxt.Button;


/**
 * Premiere classe appellée ,
 *  lance toutes les classes necessaires pour le fonctionnement du robot
 * @author yasminehamdane
 *
 */

public class Lanceur {

	Ressources maRessource ; 
	Cerveau cerv ; 
	EcouteurPosition  ecouteposi ; 
	EcouterCouleur  ecouteCouleur ; 
	 
	public Lanceur() throws IOException {
	/*	try {
			Calibreur c = new Calibreur();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// TODO Auto-generated constructor stub
		*/
		System.out.println("appuyez sur un bouton ppour commencer");
		Button.waitForAnyPress();
		maRessource = new Ressources() ;
		cerv = new Cerveau(maRessource); 
		System.out.println("cerveau créé");
			//Button.waitForAnyPress();
			cerv.start(); 					//lancement du thread du cerveau
	}
	public static void main(String[] args) throws IOException {
		new Lanceur();
	}

}
