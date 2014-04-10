import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Pilot {
	DifferentialPilot p;
	TouchSensor bump;
	int speed = 250;
	int sens = 0;
	int x;
	int y;
	Pilot(){
		x = 0;
		y = 0;
		bump = new TouchSensor( SensorPort.S1);
	}
	
	public void avancer(int d){
		//p.setTravelSpeed(speed);
			p.travel(d,true);
			while(p.isMoving());
			System.out.println(p.getMovement().getDistanceTraveled());
			if ((int)p.getMovement().getDistanceTraveled()==d ){
				System.out.println("true");
			}
			this.x+=d;
		}
		
	public void mouvat(int x , int y){
		int choix_x= 0;
		int choix_y= 0;
		if ( x >this.x ){
			choix_x = 1;
		}
		if ( y >this.y ){
			choix_y = 1;
		}
		while (this.x != x || this.y != y ){
			if (choix_x ==1){
				this.avancer(x-this.x);
			}
			//réencapsuler les mouvements de base pour modifié x et y
			//utiliser les flags pour le sens
		}
	}

	public void go() {
		this.mouvat(100,0);
		//while(!bump.isPressed()){
			
		//}
		   
		Button.waitForAnyPress();
	}

	public static void main(String[] args) {
		Pilot traveler = new Pilot();
		traveler.p= new DifferentialPilot(5.5f, 5.5f,Motor.A,Motor.C);
		Button.waitForAnyPress();
		traveler.go();
	}
}
