package competition.capture;
import java.io.IOException;

import competition.Couleur;
import competition.Ressources;
import competition.ecouteur.EcouterCouleur;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.ColorHTSensor;
import lejos.nxt.addon.GyroSensor;
import lejos.robotics.Color;


public class InfoCap extends Thread {
	TouchSensor bump;
	ColorHTSensor scolor;
	GyroSensor gyro;
	UltrasonicSensor sonar;
	Ressources maRessource ; 
	EcouterCouleur  monEcouterCouleur ; 
	
	InfoCap(Ressources rs   ) throws IOException{
		bump = new TouchSensor(SensorPort.S1);
		scolor = new ColorHTSensor(SensorPort.S4);
		gyro =  new GyroSensor(SensorPort.S2, 0);
		sonar = new UltrasonicSensor(SensorPort.S3);
		this.maRessource = rs ; 
	
	}
	
	public int getbleu(){
		return scolor.getRGBComponent(Color.BLUE);
	}
	
	public int getrouge(){
		return scolor.getRGBComponent(Color.RED);
	}
	
	public int getvert(){
		return scolor.getRGBComponent(Color.GREEN);
	}
	
	public double getDistance(){
		return sonar.getDistance();
	}
	public boolean ispressed(){
		return bump.isPressed();
	}
	@Override
	public void run() {
		Couleur c = monEcouterCouleur.getCouleur(); 
		maRessource.setCouleur(c);
	}

}
