package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import util.Point;

public class DrawingArea extends JPanel{
	ArrayList<Point> centers;
	BufferedImage img;

	public DrawingArea(){
		centers = new ArrayList<Point>();
		img = null;
		
		setPreferredSize(new Dimension(420, 420));
		this.setBackground(new java.awt.Color(255, 255, 255));
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(img != null){
			g.drawImage(img, 0, 0, this);
		}
		if(!centers.isEmpty()){
			for(Point p : centers){
				g.fillOval(p.x-3, p.y-3, 6, 6);
			}
		}
	}
	
	public void addPoint(int x, int y){
		centers.add(new Point(x, y));
		repaint();
	}
	
	public void clearPoints(){
		centers.clear();
		img = null;
		repaint();
	}
	
	public void generateVornoi(boolean euclidian){
		if(!centers.isEmpty()){
			int width = this.getWidth();
			int height = this.getHeight();
			img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width; j++){
					Point closest = null;
					double dist = 999999;
					for(Point p : centers){
						double tempDist = 0;
						if(euclidian){
							tempDist = euclidianDist(p, j, i);
						}else{
							tempDist = manhattanDist(p, j, i);
						}
						if(tempDist < dist){
							closest = p;
							dist = tempDist;
						}
					}
					img.setRGB(j, i, closest.RGB);
				}
			}
			
			repaint();
		}
	}
	
	public double manhattanDist(Point p, int x, int y){
		return Math.abs(p.x - x) + Math.abs(p.y - y);
	}
	
	public double euclidianDist(Point p, int x, int y){
		int deltaX = Math.abs(p.x - x);
		int deltaY = Math.abs(p.y - y);
		
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	}
}
