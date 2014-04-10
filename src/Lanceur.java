
public class Lanceur {
	
	
	Ressources ress ; 
	Cerveau cerv ; 
	EcouteurPosition  ecouteposi ; 
	EcouterCouleur  ecouteCouleur ; 
	 
	public Lanceur() {
		// TODO Auto-generated constructor stub
	
	ress = new Ressources() ; 
	
	cerv = new Cerveau(ress) ; 
	ecouteCouleur = new EcouterCouleur() ; 
	ecouteposi = new EcouteurPosition() ; 
	
	ecouteCouleur.start() ; 
	ecouteposi.start() ; 
	cerv.start() ; 
	
	}

}
