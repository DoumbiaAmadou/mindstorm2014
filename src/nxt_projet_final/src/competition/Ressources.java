package competition;



public class Ressources {
	char orientation = 'N';
	boolean changementCouleur = false ; 
	
	double cordY =0; 
	double cordX =0; 

	double totalCordX = 0; 
	double totalCordY = 0; 
	
	int nbLigne = 6; 
	int nbcolone = 4 ; 	
	boolean [][] listDeRecherche = new boolean [6][4] ;
	Couleur couleurCourante ; 
	int actuelligne= 0 ; 
	int actuelColonne= 0 ;
	protected int angle; 
	
	public Ressources (){
		angle=90; 
		couleurCourante = null; 
		for (int i = 0; i < listDeRecherche.length; i++) {
			for (int j = 0; j < listDeRecherche[0].length; j++) {
				listDeRecherche[i][j]=false ; 
			}
		}
	}
	
	public void setObjectif(int i,int j,boolean b) {
		if(i<6 && j<4)this.listDeRecherche[i][j]=b ; 
	}
	 
	public boolean isChangementCouleur() {
		return changementCouleur;
	}
	public void setChangementCouleur(boolean changementCouleur) {
		this.changementCouleur = changementCouleur;
	}


	
	
	
 public Couleur getCouleurCourante() {
		return couleurCourante;
	}
 public void setCouleur (Couleur c ){
	 this.couleurCourante = c ; 
 }
 public char getOrienrtation (){
	return orientation; 
 }
 public void setOrienrtation (char o ){
	 orientation = o; 
 }
public boolean[][] getListDeRecherche() {
	return listDeRecherche;
}
public void setListDeRecherche(boolean[][] listDeRecherche) {
	this.listDeRecherche = listDeRecherche;
}

	public int getLigneObjectif() {
		
		for (int i = 0; i < listDeRecherche.length; i++) {
			for (int j = 0; j < listDeRecherche[0].length; j++) {
				if(listDeRecherche[i][j]==false)return i; 
			}
		}
		return -1 ;
	}

	public int getColonneObjectif() {
		
		for (int i = 0; i < listDeRecherche.length; i++) {
			for (int j = 0; j < listDeRecherche[0].length; j++) {
				if(listDeRecherche[i][j]==false)return j; 
			}
		}
		return -1 ;
	}

	public void majPosition(int angle, float distanceTraveled) {
		// TODO Auto-generated method stub
		if(angle<90 && angle>=0) {
			cordY += Math.sin(angle)*distanceTraveled; 
			cordX += Math.cos(angle)*distanceTraveled ; 
		}
		if(angle<180 && angle>=90) {
			cordX += Math.sin(angle)*distanceTraveled; 
			cordY += Math.cos(angle)*distanceTraveled ; 
			
		}
	}
}
