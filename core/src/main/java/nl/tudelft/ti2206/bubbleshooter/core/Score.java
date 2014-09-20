package nl.tudelft.ti2206.bubbleshooter.core;

import java.util.Collection;

public class Score {
	
	int score;
	
	public Score(){
		score = 0;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public int getScore(){
		return this.score;
	}
	
	public void update(Collection<Bubble> bs){
		bs.forEach((Bubble b) -> score+= b.getPoints());
	}
}
