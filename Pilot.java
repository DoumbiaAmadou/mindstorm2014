import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;


public class Pilot {
	TouchSensor bump;
	int originx;
	int originy;
	Pilot(){
		originx = 0;
		originy = 0;
		bump = new TouchSensor( SensorPort.S1);
	}
	
	public void mouvat(int x , int y){
		int choix_x= 0;
		int choix_y= 0;
		if ( x >originx ){
			choix_x = 1;
		}
		if ( y >originy ){
			choix_y = 1;
		}
		while (originx != x && originy != y ){
			//réencapsuler les mouvements de base pour modifié x et y
			//utiliser les flags pour le sens
		}
	}

	public void go() {
		while(!bump.isPressed()){
			this.mouvat(5, 5);
			
		}   
		Button.waitForAnyPress();
	}

	public static void main(String[] args) {
		Pilot traveler = new Pilot();
		Button.waitForAnyPress();
		traveler.go();
	}
}
