import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;


public class Mouv {
	
	DifferentialPilot p;
	public int  pince_etat;
	
	Mouv(){
		//bump= new TouchSensor(SensorPort.S1);
		p = new DifferentialPilot(5.4f,11.6f,Motor.A,Motor.C);
		pince_etat = 0;
		Motor.B.setSpeed(135);
	}
	
	public void avancer( int d ){
		p.travel(d,true);
	}
	public void setvitesse(int v){
		p.setTravelSpeed(v);
	}
	
	public void arreter(){
		p.stop();	
	}
	
	public void attrapper(){
		if ( pince_etat==0){
			Motor.B.rotate(-65);
			pince_etat=1;
		}else{
			this.relacher();
			Motor.B.rotate(-65);
			pince_etat=1;
		}
		
	}
	public void relacher(){
		if ( pince_etat==1){
			Motor.B.rotate(65);
			pince_etat=0;
		}else{
			this.attrapper();
			Motor.B.rotate(65);
			pince_etat=0;
		}
		
	}
	
	public void tourner(int a){
		p.rotate(a);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mouv traveler = new Mouv();
		Button.waitForAnyPress();
		traveler.avancer(-20);
		Button.waitForAnyPress();
	}

}
