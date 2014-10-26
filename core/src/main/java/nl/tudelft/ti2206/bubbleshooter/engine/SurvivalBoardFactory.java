package nl.tudelft.ti2206.bubbleshooter.engine;

import nl.tudelft.ti2206.bubbleshooter.core.Grid;
import nl.tudelft.ti2206.bubbleshooter.core.bubbles.Bubble;

/**
 * This class creates an {@link SurvivalBoardFactory}.
 * The {@link SurvivalBoardFactory} creates an infinite iterator over
 * {@link Grid}s filled with 40 random {@link Bubble}s. 
 */
public class SurvivalBoardFactory extends ZenBoardFactory {

	/**
	 * The mode name of Survival Mode.
	 */
	private String modeName = "Survival";
	
	/**
	 * Returns the name of the survival mode.
	 * @return name of the this mode.
	 */
	@Override
	public String getModeName(){
		return this.modeName;
	}
}
