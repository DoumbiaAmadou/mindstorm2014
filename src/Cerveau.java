import java.io.*;
import java.util.ArrayList;

import javax.microedition.location.QualifiedCoordinates;


public class Cerveau extends Thread {
	Ressources ressource ; 
    boolean  chercher ; 
    ArrayList<Integer>  strategie ; 
    Mouvement Mouv  ; 
    int  courStrateg = 1 ; 
    
	
	public Cerveau( Ressources r ){
		this.ressource = r ; 
		
		
		chercher = true ;  
	    strategie =  new ArrayList<Integer>(); 
	    strategie.add( 1); 	 
	    Mouv = new Mouvement() ; 
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		quoifaire() ; 
	}
	
	private void quoifaire() {
		// TODO Auto-generated method stub
	
		
	}
	private trouverLigne () {
		while()
	}

	
}
