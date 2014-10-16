package nl.tudelft.ti2206.bubbleshooter.core.bubbles;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BomBehaviourTest.class, BomBubbleTest.class,
		BubbleBehaviourTest.class, BubbleTest.class,
		MichaelBayBehaviourTest.class, MichaelBayBubbleTest.class,
		ProjectileTest.class, StoneBubbleBehaviourTest.class,
		StoneBubbleTest.class })
public class BubbleTests {

}
