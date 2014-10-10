package nl.tudelft.ti2206.bubbleshooter.engine;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({	ArcadeBoardFactoryTest.class,
				AssetsTest.class,
				BoardFactoryTest.class,
				FileBoardFactoryTest.class,
				MPBoardFactoryTest.class,
				SoundEngineTest.class,
				ZenBoardFactoryTest.class })
public class EngineTests {

}
