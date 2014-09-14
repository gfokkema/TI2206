package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.core.artifacts.CannonTest;
import nl.tudelft.ti2206.bubbleshooter.core.artifacts.PointerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class, BubbleTest.class, CannonTest.class, PointerTest.class,
				ProjectileTest.class })
public class AllTests {

}
