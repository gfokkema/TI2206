package nl.tudelft.ti2206.bubbleshooter.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	BackgroundTest.class,
				BoardTest.class,
				BubbleTest.class,
				CannonTest.class,
				GridTest.class,
				PointerTest.class,
				ProjectileTest.class,
				MichaelBayBubbleTest.class,
				BomBubbleTest.class,
				StoneBubbleTest.class,
				BomBehaviourTest.class,
				MichaelBayBehaviourTest.class,
				StoneBubbleBehaviourTest.class})
public class CoreTests {

}
