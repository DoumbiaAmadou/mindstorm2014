import java.io.DataOutput;


public class Couleur {
	 
	String  nom =""; 
	double rouge=  0 ;
	double vert = 0;
	double bleu = 0;
	
	public Couleur(String nomcouleur) {
	// TODO Auto-generated constructor stub
		this.nom =  nomcouleur ; 
	}
	


	public double getRouge() {
		return rouge;
	}



	public void setRouge(double rouge) {
		this.rouge = rouge;
	}



	public double getVert() {
		return vert;
	}



	public void setVert(double  vert) {
		this.vert = vert;
	}



	public double getBleu() {
		return bleu;
	}



	public void setBleu(double bleu) {
		this.bleu = bleu;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getNom() {
		return nom;
	}


	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

	

}
