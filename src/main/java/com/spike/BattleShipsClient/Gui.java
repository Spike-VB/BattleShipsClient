package com.spike.BattleShipsClient;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui {
	
	private int FIELD_SIZE;
	private boolean isStarted = false;
	private JFrame frame;
	private JPanel shipButtonPanel;
	private MyShipsPanel myShipsPanel;
	private JButton[][] shipButtonArray;
	private int[][] shipArray;
	
	public Gui (int fieldSize) {
		FIELD_SIZE = fieldSize;
		
		shipButtonArray = new JButton[FIELD_SIZE][FIELD_SIZE];
		shipArray = new int[FIELD_SIZE][FIELD_SIZE];
	}
	
	public int[][] getShipArray() {
		return shipArray;
	}
	
	public void buildGui() {
    	frame = new JFrame("Battle ships");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setBounds(400, 100, FIELD_SIZE * 100, FIELD_SIZE * 50);
    	
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout());
    	
    	JPanel centerPanel = new JPanel();
    	centerPanel.setLayout(new GridLayout(1, 2));
    	
    	myShipsPanel = new MyShipsPanel();
    	centerPanel.add(myShipsPanel);
    	
    	shipButtonPanel = new JPanel();
    	shipButtonPanel.setLayout(new GridLayout(FIELD_SIZE, FIELD_SIZE));
    	fillShipsPanel();
    	centerPanel.add(shipButtonPanel);
    	
    	mainPanel.add(BorderLayout.CENTER, centerPanel);
    	
    	JButton startButton = new JButton("Start");
    	startButton.addActionListener(new StartButtonListener());
    	mainPanel.add(BorderLayout.SOUTH, startButton);
    	
    	frame.setContentPane(mainPanel);
    	
    	frame.setVisible(true);
    }
	
	private void fillShipsPanel() {
		ShipButtonListener bL = new ShipButtonListener();
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				JButton b = new JButton(new SeaIcon());
				b.addActionListener(bL);
				shipButtonArray[i][j] = b;
				shipButtonPanel.add(b);
			}
		}
	}
	
	private class MyShipsPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		private int shipWidth;
		private int shipHeight;
		
		public void paintComponent(Graphics g) {
			
			g.setColor(this.getBackground());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            g.setColor(Color.white);
            
            int panelSize = this.getHeight() / FIELD_SIZE * FIELD_SIZE;
            
            if (this.getWidth() < this.getHeight()) {
                panelSize = Math.round(this.getWidth() / FIELD_SIZE) * FIELD_SIZE;
            }
            
            shipWidth = panelSize / FIELD_SIZE;
			shipHeight = panelSize / FIELD_SIZE;
            
            g.fillRect((this.getWidth() - panelSize) / 2, (this.getHeight() - panelSize) / 2, panelSize, panelSize);
            
            g.setColor(Color.gray);
            
            for(int i = 0; i < FIELD_SIZE; i++) {
            	for(int j = 0; j < FIELD_SIZE; j++) {
            		if(shipArray[i][j] == 1) {
            			g.fillRect((this.getWidth() - panelSize) / 2 + j * shipWidth, 
            					(this.getHeight() - panelSize) / 2 + i * shipHeight, shipWidth, shipHeight);
            		}
            	}
            }
		}
	}
	
	private class StartButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			
			for(int i = 0; i < FIELD_SIZE; i++) {
				for(int j = 0; j < FIELD_SIZE; j++) {
					JButton b = shipButtonArray[i][j];
					b.setEnabled(true);
					b.setIcon(new SeaIcon());
				}
			}
			
			isStarted = true;
			
			myShipsPanel.repaint();
			frame.revalidate();
		}
		
	}
	
	private class ShipButtonListener implements ActionListener {
		
		private ShipIcon s = new ShipIcon();
		private JButton b; 

		public void actionPerformed(ActionEvent ev) {
			if(!isStarted) {
				b = (JButton) ev.getSource();
				b.setEnabled(false);
				b.setIcon(s);
				fillShipArray();
			}
		}
		
		private void fillShipArray() {
			for(int i = 0; i < FIELD_SIZE; i++) {
				for(int j = 0; j < FIELD_SIZE; j++) {
					if(shipButtonArray[i][j].equals(b)) {
						shipArray[i][j] = 1;
					}
				}
			}
		}
		
	}
	
	private class SeaIcon implements Icon {
		
		private int width;
		private int height;

		public int getIconHeight() {
			return height;
		}

		public int getIconWidth() {
			return width;
		}

		public void paintIcon(Component arg0, Graphics g, int arg2, int arg3) {
			height = arg0.getHeight();
			width = arg0.getWidth();
			
			g.setColor(Color.blue);
            g.fillRect(0, 0, width, height);		
		}
		
	}
	
	private class ShipIcon implements Icon {
		
		private int height;
		private int width;

		public int getIconHeight() {
			return height;
		}

		public int getIconWidth() {
			return width;
		}

		public void paintIcon(Component arg0, Graphics g, int arg2, int arg3) {
			height = arg0.getHeight();
			width = arg0.getWidth();
			
			g.setColor(Color.gray);
            g.fillRect(0, 0, width, height);		
		}
		
	}
}
