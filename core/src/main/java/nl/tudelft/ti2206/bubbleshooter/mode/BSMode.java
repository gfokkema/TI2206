package nl.tudelft.ti2206.bubbleshooter.mode;

import java.util.Collection;

import nl.tudelft.ti2206.bubbleshooter.core.Cannon;
import nl.tudelft.ti2206.bubbleshooter.core.Projectile;
import nl.tudelft.ti2206.bubbleshooter.engine.BSDrawable;

public interface BSMode {
	public void update(float deltaTime);
	public Collection<BSDrawable> getDrawables();
	public Cannon getCannon();
	public Projectile getProjectile();
	public void setProjectile(Projectile projectile);
	
	// FUGLY, doesn't belong here...
	public void cannonLeft(boolean left);
	public void cannonRight(boolean right);
}
