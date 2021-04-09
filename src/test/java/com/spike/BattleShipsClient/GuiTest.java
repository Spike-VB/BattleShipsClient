package com.spike.BattleShipsClient;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;

import com.spike.BattleShipsLib.*;

public class GuiTest extends TestCase {
	
	@Mock private Connection con;

	public void testGui() {
		Gui gui = new Gui(10);
		
    	con = Mockito.mock(Connection.class);
    	MockitoAnnotations.initMocks(this);
    	
    	int[][] pos = {{0, 0}, {1, 0}, {0, 2}, {4, 4}};
    	OngoingStubbing<FireResponse> f = Mockito.when(con.getFireResponse());
    	f = f.thenReturn(new WaitingResponse(true, pos[0]));
    	f = f.thenReturn(new WaitingResponse(true, pos[1]));
    	f = f.thenReturn(new WaitingResponse(true, pos[2]));
    	f = f.thenReturn(new WaitingResponse(false, pos[3]));
    	f = f.thenReturn(new HitResponse(true, false));
    	
    	StartButtonListener sbListener = new StartButtonListener(gui, con);
    	gui.setStartButtonListener(sbListener);
    	
    	gui.buildGui();
    	con.connect();
    	
    	ArrayList<ShipButton> shipButtons = gui.getShipButtons();
    	ShipButton sButton;
    	for(int i = 0; i < 3; i++) {
	    	sButton = shipButtons.get(i * 2);
	    	sButton.doClick();
	    	sButton = shipButtons.get(i * 2 + 10);
	    	sButton.doClick();
	    	sButton = shipButtons.get(i * 2 + 20);
	    	sButton.doClick();
    	}
    	
    	gui.getStartButton().doClick();
    	
    	shipButtons.get(shipButtons.size() - 1).doClick();
    	
    	try {
    		Thread.sleep(500);
    	}
    	catch(InterruptedException ex) {
    		ex.printStackTrace();
    	}
    	
    	shipButtons.get(shipButtons.size() - 2).doClick();
    	
    	while(true) {
    		if(shipButtons.get(shipButtons.size() - 2).getIcon() instanceof FireIcon) {
    			break;
    		}
	    	try {
	    		Thread.sleep(500);
	    	}
	    	catch(InterruptedException ex) {
	    		ex.printStackTrace();
	    	}   	
    	}

	}

}
