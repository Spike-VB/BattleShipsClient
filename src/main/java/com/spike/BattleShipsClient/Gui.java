package com.spike.BattleShipsClient;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import com.spike.BattleShipsLib.Ship;

public class Gui implements Runnable {
	
	private int FIELD_SIZE;
	private StartButtonListener sbListener;
	private JFrame frame;
	private JPanel shipButtonPanel;
	private MyShipsPanel myShipsPanel;
	private ArrayList<ShipButton> shipButtons;
	private JButton startButton;
	private int[] fieldArray;
	private int[] fireArray;
	private boolean firstShot;
	
	public Gui (int fieldSize) {
		FIELD_SIZE = fieldSize;
		firstShot = true;
		shipButtons = new ArrayList<ShipButton>();
		fieldArray = new int[FIELD_SIZE * FIELD_SIZE];
		fireArray = new int[FIELD_SIZE * FIELD_SIZE];
	}
	
	public boolean getFirsShot() {
		return firstShot;
	}
	
	public void updateFirstShot() {
		if(firstShot) {
			firstShot = false;
		}
	}
	
	public ArrayList<ShipButton> getShipButtons() {
		return shipButtons;
	}
	
	public JButton getStartButton() {
		return startButton;
	}
	
	public int getFieldSize() {
		return FIELD_SIZE;
	}
	
	public void setStartButtonListener(StartButtonListener sbl) {
		sbListener = sbl;
	}
	
	public void setField(int[] f) {
		fieldArray = f;
	}
	
	public void updateFireArray(int[] f) {
		fireArray[f[0] * FIELD_SIZE + f[1]] = 1;
	}
	
	public void blockShipButtons() {
		for(ShipButton s : shipButtons) {
			s.setEnabled(false);
		}
	}
	
	public void unblockShipButtons() {
		for(ShipButton s : shipButtons) {
			if(!s.isBlocked()) {
				s.setEnabled(true);
			}
		}
	}
	
	public void repaint() {
		myShipsPanel.repaint();
		frame.revalidate();
	}
	
	public void run() {
		buildGui();
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
    	
    	startButton = new JButton("Start");
    	startButton.addActionListener(sbListener);
    	mainPanel.add(BorderLayout.SOUTH, startButton);
    	
    	frame.setContentPane(mainPanel);
    	
    	frame.setVisible(true);
    }
	
	public void dispKilledShip(int[] pos) {
		ArrayList<Ship> ships = new ArrayList<Ship>();
		Ship tempShip = new Ship();
		Ship killedShip = new Ship();
		
		for(int j = 0; j < FIELD_SIZE; j++) {
			if(shipButtons.get(pos[0] * FIELD_SIZE + j).getIcon() instanceof FireIcon) {
				tempShip.buildShip(pos[0], j);
			}
			else {
				if(tempShip.getShip().size() > 0) {
					ships.add(tempShip);
					tempShip = new Ship();
				}
			}
		}
		
		if(!ships.contains(tempShip)) {
			ships.add(tempShip);
		}
		
		for(Ship s : ships) {
			if(s.isHit(pos)) {
				killedShip = s;
				break;
			}
		}
		
		if(killedShip.getShip().size() == 1) {
			ships = new ArrayList<Ship>();
			tempShip = new Ship();
			for(int i = 0; i < FIELD_SIZE; i++) {
				if(shipButtons.get(pos[1] + FIELD_SIZE * i).getIcon() instanceof FireIcon) {
					tempShip.buildShip(i, pos[1]);
				}
				else {
					if(tempShip.getShip().size() > 0) {
						ships.add(tempShip);
						tempShip = new Ship();
					}
				}
			}
			
			if(!ships.contains(tempShip)) {
				ships.add(tempShip);
			}
		}
		
		for(Ship s : ships) {
			if(s.isHit(pos)) {
				killedShip = s;
				break;
			}
		}
		
		for(int[] p : killedShip.getShip()) {
			shipButtons.get(p[0] * FIELD_SIZE + p[1]).setKilledIcon();
		}
		
		dispKillEmptyCells(killedShip);
	}
	
	private void dispKillEmptyCells(Ship ship) {
		ShipButton b;
		int top;
		int bottom;
		int left;
		int right;
		
		for(int[] shipPos : ship.getShip()) {
			top = shipPos[0] == 0 ? 0 : -1;
			bottom = shipPos[0] == FIELD_SIZE - 1 ? 1 : 2;
			left = shipPos[1] == 0 ? 0 : -1;
			right = shipPos[1] == FIELD_SIZE - 1 ? 1 : 2;

			for(int i = top; i < bottom; i++) {
				for(int j = left; j < right; j++) {
					b = shipButtons.get((shipPos[0] + i) * FIELD_SIZE + shipPos[1] + j);
					if(b.getIcon() instanceof SeaIcon) {
						setButtonEmptyIcon(b);
					}
				}
			}
		}
	}
	
	private void setButtonEmptyIcon(ShipButton b) {
		b.setEmptyIcon();
		b.setBlock(true);
		b.setEnabled(false);
	}
	
	private void fillShipsPanel() {
		
		ShipButtonListener sbL = new ShipButtonListener();
		
		for(int i = 0; i < FIELD_SIZE; i++) {
			for(int j = 0; j < FIELD_SIZE; j++) {
				ShipButton b = new ShipButton();
				b.setSeaIcon();
				b.setPosition(i, j);
				b.addActionListener(sbL);
				shipButtons.add(b);
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
            
            g.setColor(Color.blue);
            
            int panelSize = this.getHeight() / FIELD_SIZE * FIELD_SIZE;
            
            if (this.getWidth() < this.getHeight()) {
                panelSize = this.getWidth() / FIELD_SIZE * FIELD_SIZE;
            }
            
            shipWidth = panelSize / FIELD_SIZE;
			shipHeight = panelSize / FIELD_SIZE;
            
            g.fillRect((this.getWidth() - panelSize) / 2, (this.getHeight() - panelSize) / 2, panelSize, panelSize);

            for(int i = 0; i < fieldArray.length; i++) {
            	if(fieldArray[i] == 1 && fireArray[i] == 0) {
            		paintCell(g, Color.gray, panelSize, i);
            	}
            	else if(fieldArray[i] == 1 && fireArray[i] == 1) {
            		paintCell(g, Color.red, panelSize, i);
            	}
            	else if(fieldArray[i] == 0 && fireArray[i] == 1) {
            		paintCell(g, Color.cyan, panelSize, i);
            	}
            }
		}
		
		private void paintCell(Graphics g, Color c, int panelSize, int position) {
			g.setColor(c);
			g.fillRect((this.getWidth() - panelSize) / 2 + position % FIELD_SIZE * shipWidth, 
					(this.getHeight() - panelSize) / 2 + position / FIELD_SIZE * shipHeight, shipWidth, shipHeight);
		}
	}

}
