package nl.tudelft.ti2206.bubbleshooter.mode;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	BasicConditionTest.class,
				EndingConditionTest.class,
				SinglePlayerModeTest.class,
				TimerConditionTest.class })
public class ModeTests {

}
