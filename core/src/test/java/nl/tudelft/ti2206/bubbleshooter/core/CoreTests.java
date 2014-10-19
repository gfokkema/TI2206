package nl.tudelft.ti2206.bubbleshooter.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BackgroundTest.class, 
				CannonTest.class,
				GridTest.class,
				PointerTest.class })
public class CoreTests {

}
