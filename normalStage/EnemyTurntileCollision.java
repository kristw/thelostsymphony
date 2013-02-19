package normalStage;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;
/*
 * Created on 26 Á.¤. 2549
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
public class EnemyTurntileCollision extends AdvanceCollisionGroup{
	//s1=enemy s2=turntile
	public void collided(Sprite s1, Sprite s2) {
		revertPosition1();
		if(s1.getHorizontalSpeed()>0)((Enemys)s1).moveLeft(((Enemys)s1).getSpeed());
		else((Enemys)s1).moveRight(((Enemys)s1).getSpeed());
		
	}

}
