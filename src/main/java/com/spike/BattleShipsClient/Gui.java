package com.spike.BattleShipsClient;

import java.awt.*;
import javax.swing.*;

public class Gui {
	
	private int FIELD_SIZE;
	private StartButtonListener sbListener;
	private JFrame frame;
	private JPanel shipButtonPanel;
	private MyShipsPanel myShipsPanel;
	private ShipButton[][] shipButtonMatrix;
	
	public Gui (int fieldSize) {
		FIELD_SIZE = fieldSize;
		shipButtonMatrix = new ShipButton[FIELD_SIZE][FIELD_SIZE];
	}
	
	public ShipButton[][] getShipButtonMatrix() {
		return shipButtonMatrix;
	}
	
	public int getFieldSize() {
		return FIELD_SIZE;
	}
	
	public void setStartButtonListener(StartButtonListener sbl) {
		sbListener = sbl;
	}
	
	public void repaint() {
		myShipsPanel.repaint();
		frame.revalidate();
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
    	startButton.addActionListener(sbListener);
    	mainPanel.add(BorderLayout.SOUTH, startButton);
    	
    	frame.setContentPane(mainPanel);
    	
    	frame.setVisible(true);
    }
	
	private void fillShipsPanel() {
		
		ShipButtonListener sbL = new ShipButtonListener();
		
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				ShipButton b = new ShipButton(new SeaIcon());
				b.addActionListener(sbL);
				shipButtonMatrix[i][j] = b;
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
                panelSize = this.getWidth() / FIELD_SIZE * FIELD_SIZE;
            }
            
            shipWidth = panelSize / FIELD_SIZE;
			shipHeight = panelSize / FIELD_SIZE;
            
            g.fillRect((this.getWidth() - panelSize) / 2, (this.getHeight() - panelSize) / 2, panelSize, panelSize);
            
            g.setColor(Color.gray);
            
            for(int i = 0; i < FIELD_SIZE; i++) {
            	for(int j = 0; j < FIELD_SIZE; j++) {
            		if(shipButtonMatrix[i][j].isShip()) {
            			g.fillRect((this.getWidth() - panelSize) / 2 + j * shipWidth, 
            					(this.getHeight() - panelSize) / 2 + i * shipHeight, shipWidth, shipHeight);
            		}
            	}
            }
		}
	}

}
