package util;

import java.util.Random;

public class Point {
	public int x;
	public int y;
	
	public int RGB;
	
	// Default constructor
	public Point(){
		x = 0;
		y = 0;
		
		Random rand = new Random();
		RGB = rand.nextInt(16777215);
	}
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		
		Random rand = new Random();
		RGB = rand.nextInt(16777215);
	}
}
