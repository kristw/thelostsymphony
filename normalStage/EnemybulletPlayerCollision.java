package normalStage;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/*
 * Created on 5 Á.¤. 2549
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
public class EnemybulletPlayerCollision extends AdvanceCollisionGroup{
	//S1=EnemyBullet,S2=Player
	public void collided(Sprite s1, Sprite s2) {
		s1.setActive(false);
		if(((Player) s2).getHealth()>1) {
			((Player) s2).setHealth(((Player) s2).getHealth()-((EnemyBullet)s1).damage);

		}
		else {
			((Player)s2).dead();
		}
	}

}
