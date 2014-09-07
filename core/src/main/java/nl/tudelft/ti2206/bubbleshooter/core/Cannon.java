package nl.tudelft.ti2206.bubbleshooter.core;

import com.badlogic.gdx.math.Vector2;

public class Cannon {
	Vector2 vector;
	float coordinateX;
	float coordinateY;
	float angle;
	
	public Cannon(float x, float y, float angle) {
		this.setX(x);
		this.setY(y);
		
		vector = new Vector2(coordinateX,coordinateY);	
	}
	
	public float getX() {return coordinateX;}
	
	public float getY() {return coordinateY;}
	
	public void setX(float x) {coordinateX = x;}
	
	public void setY(float y) {coordinateY = y;}
	
	public float getAngle() {return angle;}
	
	public void setAngle(float degree) {
		angle = degree;
		vector.setAngle(angle);
	}
}
