package competition;

/**
 * classe qui represente une couleur 
 * @author yasminehamdane
 *
 */
public class Couleur {
	 
	public String  nom =""; 
	public double rouge=  0 ;
	public double vert = 0;
	public double bleu = 0;
	
	public Couleur(String nomcouleur) {
	// TODO Auto-generated constructor stub
		this.nom =  nomcouleur ; 
	}
	public Couleur(int r , int v , int b ) {
		// TODO Auto-generated constructor stub
			this.rouge =  r ; 
			vert = v ; 
			bleu = b ; 
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


	
	
	

	

}
