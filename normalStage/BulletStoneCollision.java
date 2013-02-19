/*
 * Created on 20 ¡.¾. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package normalStage;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;

/**
 * @author Donut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BulletStoneCollision extends AdvanceCollisionGroup{
	Panda owner;
	String img ;
	public BulletStoneCollision (Panda owner){
		this.owner = owner;
		img = "resources/explosion.png";
	}
	//s1 = bullet, s2 = stone
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		s2.setActive(false);
		BufferedImage[] images = owner.getImages(img, 7, 1);
		VolatileSprite explosion = new VolatileSprite(images, s2.getX(), s2.getY());
		owner.playfield.add(explosion);
	}

}
