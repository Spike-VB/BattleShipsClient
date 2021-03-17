package com.spike.BattleShipsClient;

import java.util.*;

public class Ship {
	
	 ArrayList<int[]> ship = new ArrayList<int[]>();
	 
	 public void buildShip(int i, int j) {
		 int[] part = {i, j};
		 ship.add(part);
	 }
	 
	 public ArrayList<int[]> getShip() {
		 return ship;
	 }
	 
	 public void printShip() {
		 System.out.print(toString());
	 }
	 
	 public String toString() {
		 String s = "";
		 
		 for(int[] i : ship) {
			 for(int j : i) {
				 s = s + j + " ";
			 }
			 s = s + " ";
		 }
		 
		 return s;
	 }
}
