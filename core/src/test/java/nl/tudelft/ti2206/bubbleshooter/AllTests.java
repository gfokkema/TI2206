package nl.tudelft.ti2206.bubbleshooter;

import nl.tudelft.ti2206.bubbleshooter.core.BackgroundTest;
import nl.tudelft.ti2206.bubbleshooter.core.BoardTest;
import nl.tudelft.ti2206.bubbleshooter.core.BubbleTest;
import nl.tudelft.ti2206.bubbleshooter.core.CannonTest;
import nl.tudelft.ti2206.bubbleshooter.core.GridTest;
import nl.tudelft.ti2206.bubbleshooter.core.PointerTest;
import nl.tudelft.ti2206.bubbleshooter.core.ProjectileTest;
import nl.tudelft.ti2206.bubbleshooter.engine.ArcadeBoardFactoryTest;
import nl.tudelft.ti2206.bubbleshooter.engine.AssetsTest;
import nl.tudelft.ti2206.bubbleshooter.engine.BoardFactoryTest;
import nl.tudelft.ti2206.bubbleshooter.engine.FileBoardFactoryTest;
import nl.tudelft.ti2206.bubbleshooter.engine.MPBoardFactoryTest;
import nl.tudelft.ti2206.bubbleshooter.engine.SoundEngineTest;
import nl.tudelft.ti2206.bubbleshooter.engine.ZenBoardFactoryTest;
import nl.tudelft.ti2206.bubbleshooter.input.SinglePlayerProcessorTest;
import nl.tudelft.ti2206.bubbleshooter.mode.BasicConditionTest;
import nl.tudelft.ti2206.bubbleshooter.mode.EndingConditionTest;
import nl.tudelft.ti2206.bubbleshooter.mode.SinglePlayerModeTest;
import nl.tudelft.ti2206.bubbleshooter.mode.TimerConditionTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AssetsTest.class,
				BackgroundTest.class,
				BoardTest.class,
				BubbleTest.class,
				CannonTest.class,
				GridTest.class,
				PointerTest.class,
				ProjectileTest.class,
				EndingConditionTest.class,
				BasicConditionTest.class,
				TimerConditionTest.class,
				SinglePlayerModeTest.class,
				SinglePlayerProcessorTest.class,
				SoundEngineTest.class,
				ArcadeBoardFactoryTest.class,
				BoardFactoryTest.class,
				FileBoardFactoryTest.class,
				MPBoardFactoryTest.class,
				ZenBoardFactoryTest.class })
public class AllTests {

}
