package nl.tudelft.ti2206.bubbleshooter;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Bubble {
	Vector2 direction;
	int velocity;
	
	public Projectile(Circle bounds, Vector2 direction, int velocity) {
		this.bounds = bounds;
		this.direction = direction;
		this.velocity = velocity;
	}
	
	public void move() {
		bounds.x += direction.x * velocity;
		bounds.y += direction.y * velocity;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
}
