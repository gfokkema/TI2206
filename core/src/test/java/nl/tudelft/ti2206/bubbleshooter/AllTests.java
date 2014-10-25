package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.core.CoreTests;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.BubbleTests;
import nl.tudelft.ti2206.bubbleshooter.engine.EngineTests;
import nl.tudelft.ti2206.bubbleshooter.input.InputTests;
import nl.tudelft.ti2206.bubbleshooter.mode.ModeTests;
import nl.tudelft.ti2206.bubbleshooter.mode.conditions.ConditionTests;
import nl.tudelft.ti2206.bubbleshooter.score.ScoreTests;
import nl.tudelft.ti2206.bubbleshooter.util.UtilTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BubbleTests.class,
				ConditionTests.class,
				CoreTests.class,
				EngineTests.class,
				InputTests.class,
				ModeTests.class,
				ScoreTests.class,
				UtilTests.class })
public class AllTests {

}
