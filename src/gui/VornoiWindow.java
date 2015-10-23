package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class VornoiWindow extends JFrame{
	// class GUI variables
	private DrawingArea diagram;
	private JPanel buttonArea;
	
	private JButton generateButton;
	private JButton clearPointsButton;
	
	private ButtonGroup radioButtons;
	private JRadioButton euclidianRadioButton;
	private JRadioButton manhattanRadioButton;
	
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;
	
	// Constructor
	public VornoiWindow(){
		setUpWindow();
		
		initGUIElements();
		
		addElements();
		
		addListeners();
		
		this.setResizable(false);
	}
	
	// Default window construction params
	public void setUpWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		this.setLocation(300, 300);
	}
	
	// Initialize class GUI variables
	public void initGUIElements(){
		diagram = new DrawingArea();
		buttonArea = new JPanel();
		buttonArea.setLayout(new FlowLayout());
		
		radioButtons = new ButtonGroup();
		
		generateButton = new JButton("Generate!");
		clearPointsButton = new JButton("Clear Points");
		
		euclidianRadioButton = new JRadioButton("Euclidian Distance");
		euclidianRadioButton.setSelected(true);
		manhattanRadioButton = new JRadioButton("Manhattan Distance");
	}
	
	// Add GUI elements into main window
	public void addElements(){
		buttonArea.add(clearPointsButton);
		buttonArea.add(generateButton);
		
		radioButtons.add(euclidianRadioButton);
		radioButtons.add(manhattanRadioButton);
		buttonArea.add(euclidianRadioButton);
		buttonArea.add(manhattanRadioButton);
		
		add(diagram, BorderLayout.CENTER);
		add(buttonArea, BorderLayout.SOUTH);
	}
	
	// Add button/mouse listeners
	public void addListeners(){
		diagram.addMouseListener(new PointAdderListener());
		DiagramButtonListener DBL = new DiagramButtonListener();
		clearPointsButton.addActionListener(DBL);
		generateButton.addActionListener(DBL);
	}
	
	class DiagramButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(generateButton)){
				// generate a vornoi diagram with the given points
				if(euclidianRadioButton.isSelected()){
					diagram.generateVornoi(true);
				}else if(manhattanRadioButton.isSelected()){
					diagram.generateVornoi(false);
				}
			}else if(e.getSource().equals(clearPointsButton)){
				// clear away the user placed points
				diagram.clearPoints();
			}
		}
		
	}
	
	class PointAdderListener implements MouseListener{

		@Override
		// Add in a point into the draw area
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			diagram.addPoint(x, y);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
}
