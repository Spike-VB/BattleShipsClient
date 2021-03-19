package com.spike.BattleShipsClient;

import com.spike.BattleShipsLib.*;

public class ShipsFactory {
	
	private Ship[] ships;
	
	public ShipsFactory(int i) {
		ships = new Ship[i];
	}
	
	public void setNumOfShips(int i) {
		ships = new Ship[i];
	}
	
	public Ship[] buildShips(int[][] shipsMatrix) {
		
		int[][] extMatrix = new int[shipsMatrix.length + 1][shipsMatrix[0].length + 1];
		
		for(int i = 0; i < shipsMatrix.length; i++) {
			for(int j = 0; j < shipsMatrix[i].length; j++) {
				extMatrix[i][j] = shipsMatrix[i][j];
			}
		}
		
		int shipsBuilded = 0;
		
		for(int i = 0; i < extMatrix.length - 1; i++) {
			for(int j = 0; j < extMatrix[i].length - 1; j++) {
				
				if(extMatrix[i][j] == 1) {
					extMatrix[i][j] = 2;
					Ship s = new Ship();
					ships[shipsBuilded] = s;
					shipsBuilded++;
					s.buildShip(i, j);
					
					int k = 1;
					
					if(extMatrix[i][j + k] == 1) {	
						while(extMatrix[i][j + k] == 1) {
							extMatrix[i][j + k] = 2;
							s.buildShip(i, j + k);
							k++;
						}
					}
					else if(extMatrix[i + k][j] == 1) {
						while(extMatrix[i + k][j] == 1) {
							extMatrix[i + k][j] = 2;
							s.buildShip(i + k, j);
							k++;
						}
					}
					
				}
			}
		}

		return ships;
	}

}
