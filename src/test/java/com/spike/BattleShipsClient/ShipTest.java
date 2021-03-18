package com.spike.BattleShipsClient;

import junit.framework.TestCase;

public class ShipTest extends TestCase {

	public void testPrintShip() {
		
		int[][] s = {{1, 2}, {1, 3}, {1, 4}};
		Ship ship = new Ship(s);
		
		ship.printShip();
		fail("Not yet implemented");
	}

}
