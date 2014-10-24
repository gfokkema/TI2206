package nl.tudelft.ti2206.bubbleshooter.util;


/**
 * A class which creates a object for the HighScoresFile.
 * @author group-15
 *
 */
public class HighScore extends Score {
	private static final long serialVersionUID = -6790883413069087153L;
	private String name;
	
	/**
	 * A method to create a HighScore object.
	 * @param name		The name given by the player
	 * @param highscore	The score of the player
	 */
	public HighScore(Score score, String name) {
		super(score);
		this.name = name;
	}
	
	/**
	 * This method returns the name associated with this {@link HighScore}.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
