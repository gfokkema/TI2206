package nl.tudelft.ti2206.bubbleshooter.mode.conditions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BasicConditionTest.class, BelowLineConditionTest.class,
		EndingConditionTest.class, TimerConditionTest.class })
public class ConditionTests {

}
