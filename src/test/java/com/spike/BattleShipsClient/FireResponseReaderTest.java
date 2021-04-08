package com.spike.BattleShipsClient;

import junit.framework.TestCase;

import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.*;

import com.spike.BattleShipsLib.*;

public class FireResponseReaderTest extends TestCase {
	
	private ShipButton b;
	@ Mock private Gui gui;
	@ Mock private Connection con;
	private boolean buttonsBlock;
	private int fireArrayUpdates = 0;
	
	public FireResponseReaderTest () {
		b = new ShipButton();
		gui = Mockito.mock(Gui.class);
		MockitoAnnotations.initMocks(this);
	}
	
	public void testDoneWaitingResponse() {
		int[] pos = {1, 1};
		FireResponse wt = new WaitingResponse(true, pos);
		FireResponse wf = new WaitingResponse(false, pos);
		
		FireResponse[] fResp = {wt, wt, wt, wf};
		
		con = Mockito.mock(Connection.class);
		OngoingStubbing<FireResponse> f = Mockito.when(con.getFireResponse());
		
		for(FireResponse fr : fResp) {
			f = f.thenReturn(fr);
		}
		
		buttonsBlock = true;
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock arg0) throws Throwable {
				buttonsBlock = false;
				return null;
			}}).when(gui).unblockShipButtons();
		
		fireArrayUpdates = 0;
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock arg0) throws Throwable {
				fireArrayUpdates++;
				return null;
			}}).when(gui).updateFireArray(pos);
		
		FireResponseReader reader = new FireResponseReader(gui, con, b);
		reader.execute();
		
		int testTime = 0;
		while(fireArrayUpdates < fResp.length) {
			testTime++;
			if(testTime >= 50) {
				fail();
			}
			try {
				Thread.sleep(100);
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		testTime = 0;
		while(buttonsBlock) {
			testTime++;
			if(testTime >= 50) {
				fail();
			}
			try {
				Thread.sleep(100);
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public void testDoneHitResponse() {
		int[] pos = {1, 1};
		FireResponse h = new HitResponse();
		FireResponse wt = new WaitingResponse(true, pos);
		
		FireResponse[] fResp = {wt, wt, wt, h};
		
		con = Mockito.mock(Connection.class);
		OngoingStubbing<FireResponse> f = Mockito.when(con.getFireResponse());
		
		for(FireResponse fr : fResp) {
			f = f.thenReturn(fr);
		}
		
		b.setSeaIcon();
		
		FireResponseReader reader = new FireResponseReader(gui, con, b);
		reader.execute();
		
		int testTime = 0;
		while(b.getIcon() instanceof SeaIcon) {
			testTime++;
			if(testTime >= 50) {
				fail();
			}
			try {
				Thread.sleep(100);
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	
	public void testDoInBackground() {
		int[] pos = {1, 1};
		FireResponse h = new HitResponse();
		FireResponse w = new WaitingResponse(true, pos);
		FireResponse[] fResp = {h, w, h, h, w, h};
		
		con = Mockito.mock(Connection.class);
		OngoingStubbing<FireResponse> f = Mockito.when(con.getFireResponse());
		FireResponseReader reader = new FireResponseReader(gui, con, b);
		
		for(FireResponse fr : fResp) {
			f = f.thenReturn(fr);
		}
		
		for(FireResponse fr : fResp) {
			assertEquals(fr, reader.doInBackground());
		}
	}
	
}
