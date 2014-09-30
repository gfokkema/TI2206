package nl.tudelft.ti2206.bubbleshooter.mode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Board;
import nl.tudelft.ti2206.bubbleshooter.core.Bubble;
import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessor;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MultiPlayerModeTest {
	private MultiPlayerMode mode;
	
	@Mock Board board;
	@Mock Cannon cannon;
	@Mock Collection<Bubble> colorgroup;
	@Mock EndingCondition end;
	@Mock Projectile bubble;
	@Mock Projectile cbubble;
	@Mock StatsObserver obs;
	@Mock ObjectInputStream input;
	@Mock ObjectOutputStream output;
	
	
	@Before
	public void setUp() {
		mode = new MultiPlayerMode(end,input,output);
		Mockito.when(mode.getBoard()).thenReturn(board);


}
