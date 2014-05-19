package competition;

import java.io.IOException;
import java.util.ArrayList;

import lejos.nxt.Button;

/**
 * Classe gérant les strategies
 * 
 * @author yasminehamdane
 * 
 */
public class Cerveau extends Thread {
	Ressources ressource;
	boolean chercher;
	ArrayList<Integer> strategie;
	Mouvement Mouv;
	int courStrateg = 1;
	boolean listDeRecherche[][];
	int ligneObjectif = 0;
	int coloneObjectif = 0;

	public Cerveau(Ressources r) throws IOException {
		this.ressource = r;

		chercher = true;
		strategie = new ArrayList<Integer>();
		// stragie recherche
		strategie.add(1);
		// strategie ramene le palet a la maison
		strategie.add(2);
		// strategie Retrouver ma position ancienne
		strategie.add(3);

		Mouv = new Mouvement(this.ressource);

	}

	/*
	 * private void reconnaissance() { // TODO Auto-generated method stub
	 * Mouv.tourner(45); Mouv.limiteDroiteGauche(); double maxX =
	 * ressource.cordX; double maxY = ressource.cordY; ressource.cordX = 0;
	 * ressource.cordY = 0;
	 * 
	 * Mouv.tourner(-90); Mouv.limiteDroiteGauche(); if (ressource.cordX > maxX)
	 * maxX = ressource.cordX; if (ressource.cordY > maxY) maxY =
	 * ressource.cordY; ressource.totalCordX = maxX; ressource.totalCordY =
	 * maxY;
	 * 
	 * ressource.cordX = 0; ressource.cordY = 0; Mouv.tourner(45);
	 * 
	 * }
	 */

	@Override
	public void run() {
		// TODO Auto-generated method stub

		 trouverLigne();
		 suivreUneLigne();
		 Mouv.tourner(90);
		 while(Mouv.p.isMoving()){}
		 Mouv.limiteDroiteGauche();
		
		 while(Mouv.monEcouteurCouleur.getCouleur().getNom().equals("Blanc"))
		 trouverLigne();
		 suivreUneLigne();
		 if(Mouv.bump.isPressed())
		 placeChezMoi();
		 //a decommenter
		//quoifaire();
	}

	/**
	 * choix de la stratégie à executer par le robot
	 */
	private void quoifaire() {
		// TODO Auto-generated method stub
		int nbCol = 0;
		int nbPalet = 0;
		Mouv.tourner(45 - ressource.angle); // avant strategie
		Mouv.limiteDroiteGauche();
		Mouv.tourner(140 - ressource.angle);
		// Mouv.avancer(10);

		if (courStrateg == 1 && nbPalet < 3) {
			ligneObjectif = ressource.getLigneObjectif();
			coloneObjectif = ressource.getColonneObjectif();
			Mouv.avancer(10);

			trouverLigne();
			nbCol += 1;
			suivreUneLigne();
			if (Mouv.bump.isPressed())
				placeChezMoi();

			else
				courStrateg = 2;
			// for (int i = 0; i < ressource.listDeRecherche.length; i++) {
			// for (int j = 0; j < ressource.listDeRecherche[0].length; j++) {
			// if(ressource.listDeRecherche[i][j]==false)
			// courStrateg = 1 ;
			// }
			// }
			// coloneObjectif = (coloneObjectif+1)%4;
			nbPalet += nbPalet;
		}
		if (courStrateg == 2) {

			retrouverPosition();
			Mouv.tourner(100 - ressource.angle);

			trouverLigne();
			nbCol += 1;
			if (nbCol == 2) {
				Mouv.avancer(10);
				Mouv.tourner(90);
				courStrateg = 1;
			}

		}

		if (courStrateg == 3) {

		}

	}

	/**
	 * Attrapper, transporter le palet vers notre camp
	 */
	private void placeChezMoi() {
		// TODO Auto-generated method stub
		Mouv.attrapper();
		Mouv.tourner(180);
		Mouv.limiteBasHaut();
		Mouv.avancer(10);
		Mouv.arreter();
		Mouv.relacher();
		Mouv.avancer(-10);
		Mouv.tourner(180);

	}

	/**
	 * cherche sa position ancienne avant PlacezChezMoi
	 */
	private void retrouverPosition() {
		// TODO Auto-generated method stub
		if (ressource.actuelligne > ligneObjectif) {
			Mouv.tourner(-(270 - ressource.angle));
		} else {
			Mouv.tourner(-(90 - ressource.angle));
		}
		while (ressource.actuelligne < ligneObjectif) {
			Mouv.avancer(76);
		}
		Mouv.avancer(10);

		if (ressource.couleurCourante.nom.equals("Blanc"))
			Mouv.avancer(15);

		if (ressource.actuelColonne > coloneObjectif) {
			Mouv.tourner(180 - ressource.angle);
		} else {
			Mouv.tourner(-ressource.angle);
		}
		while (ressource.actuelligne < ligneObjectif) {
			Mouv.avancer(51);
		}
		Mouv.avancer(5);

		Mouv.tourner(-ressource.angle);
	}

	/**
	 * Permet de s'arreter si le robot est sur une ligne diffente du blanc
	 */
	private void trouverLigne() {
		Mouv.setvitesse(10);

		Couleur c = Mouv.monEcouteurCouleur.getCouleur("Blanc");
		System.out.println(c.getNom());
		while (Mouv.monEcouteurCouleur.getCouleur().getNom() // avance tant que
																// la couleur
																// est blanche
				.equals("Blanc")) {
			Mouv.avancer(c);
		}
		Button.waitForAnyPress();
	}

	/**
	 * Rotation droite
	 * 
	 * @param degre
	 *            degré de rotation
	 */
	public void rotationDroite(int degre) {
		degre = degre < 0 ? degre * -1 : degre;

		Mouv.tourner(degre);
	}

	/**
	 * Rotation droite
	 * 
	 * @param degre
	 *            degré de rotation
	 */
	public void rotationGauche(int degre) {
		degre = degre > 0 ? degre * -1 : degre;
		Mouv.tourner(degre);
	}

	/**
	 * Indique au robot de suivre la ligne detectée, en faisant des micro
	 * rotations pour bien se positionner sur la ligne
	 */

	private void suivreUneLigne() {
		Mouv.setvitesse(10);
		int deltaRotation = 5;
		

		boolean gauche = true;
		Couleur couleurinitiale = Mouv.monEcouteurCouleur.getCouleur();
		while (!Mouv.bump.isPressed()
				|| Mouv.capteurDistance.getDistance() > 15) {
			if (!Mouv.monEcouteurCouleur.getCouleur().nom
					.equals(couleurinitiale.getNom())) {

				if (gauche == true) {

					rotationGauche(deltaRotation);

					if (!Mouv.monEcouteurCouleur.getCouleur().nom
							.equals(couleurinitiale.getNom())) {

						rotationDroite(deltaRotation);
					}

				} else {

					rotationDroite(deltaRotation);
					if (!Mouv.monEcouteurCouleur.getCouleur().nom
							.equals(couleurinitiale.getNom())) {
						rotationGauche(deltaRotation);
					}

				}
				deltaRotation += 2;
				gauche = !gauche;
			}
			if (Mouv.monEcouteurCouleur.getCouleur().nom.equals(couleurinitiale
					.getNom())) {
				Mouv.avancer(couleurinitiale);

			}
			if (deltaRotation > 30 || Mouv.bump.isPressed())
				break;

		}

		if (Mouv.bump.isPressed()) {
			//courStrateg = strategie.get(2);
			ressource.listDeRecherche[ligneObjectif][coloneObjectif] = true;
			ligneObjectif = (ligneObjectif + 1) % 4;

		} else {
			for (int i = 0; i < ressource.listDeRecherche.length; i++) {
				ressource.listDeRecherche[i][coloneObjectif] = true;
			}

		}
		// System.out.println("before trouver place");
		// Button.waitForAnyPress();
		// trouverPlacement();
		Mouv.setvitesse(20);
	}

}
