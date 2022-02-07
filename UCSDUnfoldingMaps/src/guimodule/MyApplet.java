package guimodule;

import processing.core.PApplet;
import processing.core.PImage;

public class MyApplet extends PApplet {
 PImage img;
	public void setup() {
		size(400,400);
		background(255);
		stroke(0);
		img=loadImage("http://cseweb.ucsd.edu/~minnes/palmTrees.jpg","jpg");
		img.resize(0,height);
		image(img,0,0);
	}
	
	public void draw() {
		
		int[] color =sunColorSec(second());
		fill(color[0],color[1],color[2]);
		ellipse(width/4, height/5, width/4, height/5);
	}
	
	public int[] sunColorSec(float seconds) {
		int[]rgb =new int[3];
		//	scale the brightness of the the yellow based on the seconds. 30 seconds
		//	is black. 0 seconds is bright yellow
		float diffFrom30 =Math.abs(30-seconds); //abs - round the number 
		
		float ratio= diffFrom30/30;
		rgb[0]= (int)(255*ratio);
		rgb[1]= (int)(255*ratio);
		rgb[3]= 0;
		
		//System.out.println("R "+rgb[0]+ " G"+rgb[1]+" B"+rgb[2]);
		return rgb;
		
	}
	public static void main(String[]args) {
		
	}
}
