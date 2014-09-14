package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.core.BoardTest;
import nl.tudelft.ti2206.bubbleshooter.core.BubbleTest;
import nl.tudelft.ti2206.bubbleshooter.core.CannonTest;
import nl.tudelft.ti2206.bubbleshooter.core.PointerTest;
import nl.tudelft.ti2206.bubbleshooter.core.ProjectileTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BoardTest.class, BubbleTest.class, CannonTest.class, PointerTest.class,
				ProjectileTest.class })
public class AllTests {

}
