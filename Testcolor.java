import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.ColorHTSensor;


public class Testcolor {
	TouchSensor bump;
	ColorHTSensor scolor;
	public Testcolor() {
		bump = new TouchSensor(SensorPort.S1);
		scolor = new ColorHTSensor(SensorPort.S4);
	}
	public void go() {
		while(!bump.isPressed()){
			System.out.println(scolor.getColorID());
			Button.waitForAnyPress();
		}   
		Button.waitForAnyPress();
	}

	public static void main(String[] args) {
		Testcolor traveler = new Testcolor();
		Button.waitForAnyPress();
		traveler.go();
	}
}

