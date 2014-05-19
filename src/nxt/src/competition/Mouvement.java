package competition;

import java.io.IOException;

import competition.ecouteur.EcouterCouleur;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

/**
 * Classe gerant tous les mouvements du robot
 * 
 * @author yasminehamdane
 * 
 */
public class Mouvement {
	DifferentialPilot p;
	TouchSensor bump = new TouchSensor(SensorPort.S1);
	public int pince_etat;

	int distanceParcourue;
	Ressources maRessource;
	// Calibreur monCalibreur;
	EcouterCouleur monEcouteurCouleur;
	UltrasonicSensor capteurDistance = new UltrasonicSensor(SensorPort.S3);

	public Mouvement(Ressources rs) throws IOException {
		// TODO Auto-generated constructor stub
		p = new DifferentialPilot(5.4f, 11.6f, Motor.A, Motor.C);
		pince_etat = 0;
		Motor.B.setSpeed(135);
		maRessource = rs;
		this.setvitesse(20);
		monEcouteurCouleur = new EcouterCouleur(maRessource);
		monEcouteurCouleur.Lecture_du_fichier();

		maRessource.setCouleur(monEcouteurCouleur.getCouleur());

	}

	/**
	 * Robot avance d'une distance d
	 * @param d
	 */
	public void avancer(int d) {

		p.travel(d, true);
		while (p.isMoving()) {
		}

		maRessource.majPosition(maRessource.angle, p.getMovement()
				.getDistanceTraveled());
	}
	/**
	 * Avancer sur une couleur passée en parametre
	 * @param c couleur 
	 */
	public void avancer(Couleur c) {
		while (monEcouteurCouleur.couleurequal(monEcouteurCouleur.getCouleur(),c)) {
			p.travel(50, true);
			while (p.isMoving()
					&& (bump.isPressed() || !monEcouteurCouleur.couleurequal(
							monEcouteurCouleur.getCouleur(), c))) {
				arreter();
				break;
			}

			maRessource.majPosition(maRessource.angle, p.getMovement()			//mise a jour des ressources
					.getDistanceTraveled());
			if (bump.isPressed())
				break;
		}

	}

	public void setvitesse(int v) {
		p.setTravelSpeed(v);
	}

	public void arreter() {
		p.stop();
	}
	

	public void attrapper() {
		if (pince_etat == 0) {
			Motor.B.rotate(-65);
			pince_etat = 1;
		} else {
			this.relacher();
			Motor.B.rotate(-65);
			pince_etat = 1;
		}

	}

	public void relacher() {
		if (pince_etat == 1) {
			Motor.B.rotate(65);
			pince_etat = 0;
		} else {
			this.attrapper();
			Motor.B.rotate(65);
			pince_etat = 0;
		}
	}

	public void tourner(int a) {

		p.rotate(-a);

		maRessource.angle = maRessource.angle + a;
		if (maRessource.angle < 0)
			a = 360 + a; // valeur absolue
		maRessource.angle = (maRessource.angle) % 360;
		if (maRessource.angle <= 45 && maRessource.angle > 315)
			maRessource.setOrienrtation('E');
		if (maRessource.angle <= 135 && maRessource.angle > 45)
			maRessource.setOrienrtation('N');
		if (maRessource.angle <= 225 && maRessource.angle > 135)
			maRessource.setOrienrtation('O');
		if (maRessource.angle <= 315 && maRessource.angle > 225)
			maRessource.setOrienrtation('S');
	}

	public void avancerNonCouleur(Couleur c) {
		// TODO Auto-generated method stub
		while (!monEcouteurCouleur.couleurequal(
				monEcouteurCouleur.getCouleur(), c)) {
			p.travel(50, true);
			while (p.isMoving()
					&& monEcouteurCouleur.couleurequal(
							monEcouteurCouleur.getCouleur(), c)) {
				arreter();
				break;
			}

			maRessource.majPosition(maRessource.angle, p.getMovement()
					.getDistanceTraveled());
		}

	}

	public void limiteDroiteGauche() {
		// TODO Auto-generated method stub
		while (capteurDistance.getDistance() > 15) {
			p.travel(150, true);
			while (p.isMoving()) {
				if (capteurDistance.getDistance() < 20) {
					arreter();
					break;
				}
			}

			maRessource.majPosition(maRessource.angle, p.getMovement()
					.getDistanceTraveled());
			break;
		}
	}

	public void limiteBasHaut() {
		// TODO Auto-generated method stub
		while (capteurDistance.getDistance() > 15
				&& !monEcouteurCouleur.getCouleur().nom.equals("Noir")) {
			p.travel(150, true);
			while (p.isMoving()) {
				if ((monEcouteurCouleur.getCouleur().nom.equals("Noir"))) {
					arreter();
					break;
				}
			}
			maRessource.majPosition(maRessource.angle, p.getMovement()
					.getDistanceTraveled());
		}
	}

}
