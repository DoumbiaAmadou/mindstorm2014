import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.ColorHTSensor;
import lejos.nxt.addon.GyroSensor;
import lejos.robotics.Color;


public class InfoCap {
	TouchSensor bump;
	ColorHTSensor scolor;
	GyroSensor gyro;
	UltrasonicSensor sonar;
	
	InfoCap(){
		bump = new TouchSensor(SensorPort.S1);
		scolor = new ColorHTSensor(SensorPort.S4);
		gyro =  new GyroSensor(SensorPort.S2, 0);
		sonar = new UltrasonicSensor(SensorPort.S3);
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
	public static void main(String[] args) {
		InfoCap i = new InfoCap();
		Button.waitForAnyPress();
		System.out.println(i.getbleu());
		System.out.println(i.getvert());
		System.out.println(i.getrouge());
		System.out.println("---------b");
		Button.waitForAnyPress();
		System.out.println(i.getbleu());
		System.out.println(i.getvert());
		System.out.println(i.getrouge());
		System.out.println("---------bleu");
		Button.waitForAnyPress();
		System.out.println(i.getbleu());
		System.out.println(i.getvert());
		System.out.println(i.getrouge());
		System.out.println("---------r");
		Button.waitForAnyPress();
		System.out.println(i.getbleu());
		System.out.println(i.getvert());
		System.out.println(i.getrouge());
		System.out.println("---------n");
		Button.waitForAnyPress();
		System.out.println(i.getbleu());
		System.out.println(i.getvert());
		System.out.println(i.getrouge());
		System.out.println("---------v");
		Button.waitForAnyPress();
		System.out.println(i.getbleu());
		System.out.println(i.getvert());
		System.out.println(i.getrouge());
		System.out.println("---------j");
		Button.waitForAnyPress();

	}

}
