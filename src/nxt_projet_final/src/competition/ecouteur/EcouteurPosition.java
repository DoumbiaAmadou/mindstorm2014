package competition.ecouteur;

import competition.Ressources;

/**
 * 
 * @author yasminehamdane
 *
 */
public class EcouteurPosition  extends Thread {
	Ressources maRessource ; 
	
	public	EcouteurPosition(Ressources maRessource) {
	
	
		this.maRessource = maRessource; 
	}
	
	@Override
	public void run() {
		
	}
}
