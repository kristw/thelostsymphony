package normalStage;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.Timer;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
import com.golden.gamedev.object.sprite.VolatileSprite;
/*
 * Created on 3 Á.¤. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Nut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class playerEnemyCollision extends AdvanceCollisionGroup {

	Panda owner;
	String img;
	int level;
	public playerEnemyCollision (Panda owner){
		this.owner = owner;	
	}
	//S1=Enemy,S2=Player
	public void collided(Sprite s2, Sprite s1) {
		level = ((Enemys) s1).getLevel();
		s1.setActive(false);
		((Player) s2).setHealth(((Player) s2).getHealth()-((Enemys)s1).getHealth());
		if(((Player) s2).getHealth()>0) {
			((Player) s2).setHealth(((Player) s2).getHealth()-((Enemys)s1).getHealth());
			switch(((Enemys)s1).getType()){
			case 1: img = "resources/level"+level+"/"+level+"dm_walking_die.png";break;
			case 2: img = "resources/level"+level+"/"+level+"dm_flying_die.png";break;
			case 3: img = "resources/level"+level+"/"+level+"dm_belch_dead.png";break;
			default: img = "resources/level"+level+"/"+level+"dm_walking_die.png";break;
		}
			BufferedImage[] images = owner.getImages(img, 8, 2);
			 
			VolatileSprite explosion = new VolatileSprite(images, s2.getX(), s2.getY());
			if(((Enemys) s1).getDirection()==Player.LEFT) explosion.setAnimationFrame(0,7); 
			if(((Enemys) s1).getDirection()==Player.RIGHT) explosion.setAnimationFrame(8,15);
			explosion.setAnimationTimer(new Timer(100));
			 
			owner.playfield.add(explosion);
		}
		if(((Player) s2).getHealth()<=0) {
			((Player)s2).setHealth(0);
			((Player)s2).addScore(((Enemys)s1).getScore());
			((Player)s2).dead();
		}
	}

}
