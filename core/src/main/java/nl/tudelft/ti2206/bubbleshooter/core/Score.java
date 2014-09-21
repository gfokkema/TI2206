package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.engine.Assets.TextureID;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

/**
 * The score class, responsible for keeping track of the score.
 * Each board has their own score.
 */
public class Score {
	
	int score;
	
	/**
	 * Initial score.
	 */
	public Score(){
		score = 0;
	}
	
	/**
	 * Set the score.
	 * @param score
	 */
	public void setScore(int score){
		this.score = score;
	}
	
	/**
	 * Get the score.
	 * @return current score.
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * Update the score of the board.
	 * @param bs
	 */
	public void update(Collection<Bubble> bs){
		bs.forEach((Bubble b) -> score+= b.getPoints());
	}
}
