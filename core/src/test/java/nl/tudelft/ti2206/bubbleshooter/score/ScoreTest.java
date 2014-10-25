package nl.tudelft.ti2206.bubbleshooter.score;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import nl.tudelft.ti2206.bubbleshooter.util.StatsObserver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class tests the {@link Score} class, which is used to keep track
 * of a players scored points.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScoreTest {
	private Level level;
	private Score score;
	
	@Mock StatsObserver statsObs;
	
	@Before
	public void setUp() {
		this.level = new Level(1, "test");
		this.score = new Score(0, level);
	}

	@Test
	public void testCreateScore() {
		assertEquals(level, score.getLevel());
		assertEquals(0, score.getScore());
		
		Score temp = new Score(100, new Level(10, "test"));
		score = new Score(temp);
		assertEquals(10, score.getLevel().getLevel());
		assertEquals("test", score.getLevel().getName());
		assertEquals(100, score.getScore());
		
		score = new Score(0);
		assertEquals(1, score.getLevel().getLevel());
		assertEquals("No level name", score.getLevel().getName());
	}

	@Test
	public void testAdd() {
		score.add(10);
		assertEquals(10, score.getScore());
		Mockito.verify(statsObs, never()).updateScore(any());
		
		score.addStatsObserver(statsObs);
		score.add(10);
		assertEquals(20, score.getScore());
		Mockito.verify(statsObs, times(1)).updateScore(score);
		
		score.add(100);
		assertEquals(120, score.getScore());
		Mockito.verify(statsObs, times(2)).updateScore(score);
	}
	
	@Test
	public void testSetLevel() {
		Level test;
		
		test = new Level(5, "test");
		score.setLevel(test);
		assertEquals(test, score.getLevel());
		Mockito.verify(statsObs, never()).updateScore(any());
		
		test = new Level(10, "test");
		score.addStatsObserver(statsObs);
		score.setLevel(test);
		assertEquals(test, score.getLevel());
		Mockito.verify(statsObs, times(1)).updateScore(score);
		
		test = new Level(15, "test");
		score.setLevel(test);
		assertEquals(test, score.getLevel());
		Mockito.verify(statsObs, times(2)).updateScore(score);
	}
	
	@Test
	public void testSetScore() {
		Score test;
		
		test = new Score(10, new Level(5, "test"));
		score.update(test);
		assertEquals(test.getLevel(), score.getLevel());
		assertEquals(10, score.getScore());
		Mockito.verify(statsObs, never()).updateScore(any());
		
		score.addStatsObserver(statsObs);
		test = new Score(20, new Level(10, "test"));
		score.update(test);
		assertEquals(test.getLevel(), score.getLevel());
		assertEquals(20, score.getScore());
		Mockito.verify(statsObs, times(1)).updateScore(score);
		
		test = new Score(100, new Level(15, "test"));
		score.update(test);
		assertEquals(test.getLevel(), score.getLevel());
		assertEquals(100, score.getScore());
		Mockito.verify(statsObs, times(2)).updateScore(score);
	}
	
	@Test
	public void testCompare() {
		Level level1 = new Level(1, "test");
		Level level2 = new Level(2, "test");
		Score score1, score2;
		
		score1 = new Score(10, level1);
		score2 = new Score(10, level1);
		assertEquals(0, score1.compareTo(score2));
		
		score1 = new Score(10, level1);
		score2 = new Score(20, level1);
		assertEquals(-1, score1.compareTo(score2));
		
		score1 = new Score(10, level2);
		score2 = new Score(20, level1);
		assertEquals(1, score1.compareTo(score2));
		
		score1 = new Score(10, level1);
		score2 = new Score(20, level2);
		assertEquals(-1, score1.compareTo(score2));
	}
}
