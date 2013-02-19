package normalStage;
import java.awt.image.BufferedImage;

import normalStage.Player.Bullet;

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
public class BulletEnemyCollision extends AdvanceCollisionGroup{

	Panda owner;
	int random;
	int level;
	String img ;
	public BulletEnemyCollision (Panda owner){
		this.owner = owner;
	}
	public void collided(Sprite s1, Sprite s2) {
		random = (int)(Math.random()*5);
		level = ((Enemys) s2).getLevel();
		s1.setActive(false); 
        if(((Enemys) s2).getHealth()>0) ((Enemys) s2).setHealth(((Enemys) s2).getHealth()-((Bullet)s1).damage);
		if(((Enemys)s2).getHealth()<=0) {
			owner.playSound("resources/boom.wav");
			owner.player.addScore(((Enemys) s2).getScore());
			s2.setActive(false);
			switch(((Enemys)s2).getType()){
				case 1: img = "resources/level"+level+"/"+level+"dm_walking_die.png";break;
				case 2: img = "resources/level"+level+"/"+level+"dm_flying_die.png";break;
				case 3: img = "resources/level"+level+"/"+level+"dm_belch_dead.png";break;
				default: img = "resources/level"+level+"/"+level+"dm_walking_die.png";break;
			}
			
			
		    BufferedImage[] images = owner.getImages(img, 8, 2);
		 
		    VolatileSprite explosion = new VolatileSprite(images, s2.getX(), s2.getY());
		    if(((Enemys) s2).getDirection()==Player.LEFT) explosion.setAnimationFrame(0,7); 
		    if(((Enemys) s2).getDirection()==Player.RIGHT) explosion.setAnimationFrame(8,15);
		    explosion.setAnimationTimer(new Timer(100));
		 
		    owner.playfield.add(explosion);
		    System.out.println(random);
		    Item item = new Item(owner,s2.getX(),s2.getY()-30,random);
		    owner.ITEM.add(item);
		    owner.playfield.add(item);
		}
	}
} 