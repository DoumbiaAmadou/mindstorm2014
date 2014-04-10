import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import java.io.*;


/*
 * distance de sécurité 17 pour le sona 
 * 
 */

public class Voiture {
	 DifferentialPilot pilot;
	  TouchSensor bump;
	  int wheel = 20 ; 	
	  int distance  =0;    
	   
private	UltrasonicSensor capteurDistence = new  UltrasonicSensor(SensorPort.S3);
	public Voiture() {
		// TODO Auto-generated constructor stub
		 //DifferentialPilot pilot;
		   bump = new TouchSensor(SensorPort.S1);
		   wheel = 5 ; 	
		   distance  =  capteurDistence.getDistance();
	} 

  public void go() {
		    while (!bump.isPressed()) {
		      pilot.travel(wheel, true);
		     // while (pilot.isMoving()){};  
		      distance =      capteurDistence.getDistance(); 
		     System.out.println(distance);
		    // wheel-=1;		      
		      //pilot.travel(wheel , true);
		    }
		 Motor.B.rotate(45);
	     Motor.B.rotate(-80);
		     
	     pilot.rotate(90);
	      

		 //   System.out.println(" "+pilot.getMovement().getDistanceTraveled());
		 //   pilot.travel(0);
            //pilot.rotate(90);
		 //   System.out.println(" "+pilot.getMovement().getDistanceTraveled());
		    Button.waitForAnyPress();
  }

		  public static void main(String[] args) {
			  Voiture traveler = new Voiture();
		    traveler.pilot = new DifferentialPilot( 5.4f,11.6f, Motor.A, Motor.C);
		    Button.waitForAnyPress();
		    traveler.go();
		  }
//
}

