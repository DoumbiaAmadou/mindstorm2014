import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;


public class Voiture {

	public Voiture() {
		// TODO Auto-generated constructor stub
	}


		  DifferentialPilot pilot;
		  TouchSensor bump = new TouchSensor(SensorPort.S1);
		  int wheel = 30 ; 	
		  public void go() {
			 
		   
		    while (wheel>0) {
		    
		      pilot.travel(wheel, true);
		      while(pilot.isMoving()){  if (bump.isPressed()) pilot.stop();
		      }
		      pilot.rotate(90);
		      wheel-=1;
		      //pilot.travel(wheel , true);
		    }
		 //   System.out.println(" "+pilot.getMovement().getDistanceTraveled());
		 //   pilot.travel(0);
            //pilot.rotate(90);
		    System.out.println(" "+pilot.getMovement().getDistanceTraveled());
		    Button.waitForAnyPress();
		  }

		  public static void main(String[] args) {
			  Voiture traveler = new Voiture();
		    traveler.pilot = new DifferentialPilot( 5.5f, 5.5f, Motor.A, Motor.C);
		    Button.waitForAnyPress();
		    traveler.go();
		  }
//
}

