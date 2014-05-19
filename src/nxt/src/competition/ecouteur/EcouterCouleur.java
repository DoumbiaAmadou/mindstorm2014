package competition.ecouteur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;

import competition.Couleur;
import competition.Ressources;
import competition.capture.InfoCap;

public class EcouterCouleur {
	InfoCap capteur;
	ArrayList<Couleur> moyen;
	ArrayList<Couleur> bleu;
	ArrayList<Couleur> blanc;
	ArrayList<Couleur> rouge;
	ArrayList<Couleur> noir;
	ArrayList<Couleur> jaune;
	ArrayList<Couleur> vert;
	ColorHTSensor s = new ColorHTSensor(SensorPort.S4);
	Ressources maRessource;

	public EcouterCouleur(Ressources rs) {
		maRessource = rs;
		moyen = new ArrayList<Couleur>();
		bleu = new ArrayList<Couleur>();
		blanc = new ArrayList<Couleur>();
		rouge = new ArrayList<Couleur>();
		noir = new ArrayList<Couleur>();
		jaune = new ArrayList<Couleur>();
		vert = new ArrayList<Couleur>();
	}

	Couleur relever() {
		Couleur c = new Couleur("");

		int rouge = s.getRGBComponent(Color.BLUE);
		int vert = s.getRGBComponent(Color.GREEN);
		int bleu = s.getRGBComponent(Color.BLUE);
		System.out.println("relever");

		c.setRouge(rouge);
		c.setVert(vert);
		c.setBleu(bleu);
		return c;

	}

	/**
	 * Retrouve la couleur dont le nom est passé en parametre. la recherche
	 * s'effectue dans la liste moyen
	 * 
	 * @param s
	 *            la couleur recherchée
	 * @return la bonne couleur si trouvée, sinon null
	 */
	public Couleur getCouleur(String s) {

		Couleur r = null;
		for (Couleur c : moyen) {
			if (c.getNom().equals(s))
				r = c;
		}
		return r;
	}

	public ArrayList<Couleur> Lecture_du_fichier() throws IOException {

		File f = new File("calibrage.txt");
		InputStream flux = new FileInputStream(f);
		InputStreamReader lecture = new InputStreamReader(flux);
		BufferedReader r = new BufferedReader(lecture);

		String s = "";

		for (int i = 0; i < 59; i++) {
			s = r.readLine();

			Couleur color = new Couleur("");
			extraire(color, s);

			if (color.getNom().equals("Rouge")) {
				rouge.add(color);
			}
			if (color.getNom().equals("Bleu")) {
				bleu.add(color);
			}
			if (color.getNom().equals("Vert")) {
				vert.add(color);
			}
			if (color.getNom().equals("Blanc")) {
				blanc.add(color);
			}
			if (color.getNom().equals("Noir")) {
				noir.add(color);
			}
			if (color.getNom().equals("Jaune")) {
				jaune.add(color);
			}

			// System.out.println(color.getNom()+"\\ "+color.getRouge()+"\\"+color.getVert()+"\\"+color.getBleu());
		}
		r.close();

		moyen.add(moyenCouleur(bleu));
		moyen.add(moyenCouleur(blanc));
		moyen.add(moyenCouleur(vert));
		moyen.add(moyenCouleur(noir));
		moyen.add(moyenCouleur(jaune));
		moyen.add(moyenCouleur(rouge));

		return moyen;
	}

	Couleur moyenCouleur(ArrayList<Couleur> s) {
		double r, g, b;
		r = g = b = 0;
		int cpt = 0;
		String nom = "";
		for (Couleur couleur : s) {
			nom = couleur.getNom();
			r += couleur.getRouge();
			g += couleur.getVert();
			b += couleur.getBleu();
			cpt++;
		}
		Couleur retour = new Couleur(nom);

		retour.setRouge(r /= cpt);
		retour.setVert(g /= cpt);
		retour.setBleu(b /= cpt);

		return retour;
	}

	public Couleur getCouleur() {
		Couleur c = relever();
		int index = 0;
		double[] tab;
		String[] tab1;
		tab = new double[6];
		tab1 = new String[6];
		int i = 0;

		for (Couleur d : moyen) {

			tab[moyen.indexOf(d)] = Math.sqrt(Math.pow(
					d.getRouge() - c.getRouge(), 2)
					+ Math.pow(d.getVert() - c.getVert(), 2)
					+ Math.pow(d.getBleu() - c.getBleu(), 2));
			i++;
		}

		double min = tab[0];

		for (int j = 0; j < tab.length; j++) {
			if (tab[j] < min) {
				min = tab[j];
				index = j;
			}
		}

		c.setNom(moyen.get(index).getNom());
		System.out.println("votre couleur  est le" + c.getNom());

		maRessource.setCouleur(c);
		return c;

	}

	public boolean couleurequal(Couleur courante, Couleur test) {

		return courante.getNom().equals(test.getNom());
	}

	public void extraire(Couleur color, String s) throws IOException {

		int result = s.lastIndexOf(":");
		color.setRouge(new Double(s.substring(result + 1)));

		s = s.substring(0, result);
		result = s.lastIndexOf(":");
		color.setBleu(new Double(s.substring(result + 1)));

		s = s.substring(0, result);
		result = s.lastIndexOf(":");
		color.setVert(new Double(s.substring(result + 1)));

		s = s.substring(0, result);

		color.setNom(s);

	}

}
