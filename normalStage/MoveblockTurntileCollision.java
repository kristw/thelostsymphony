/*
 * Created on 19 ¡.¾. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package normalStage;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * @author Donut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MoveblockTurntileCollision extends AdvanceCollisionGroup {
	//s1=MoveBlock s2=TurnTile
	public void collided(Sprite s1, Sprite s2) {
		double speed = Math.abs(s1.getHorizontalSpeed());
		//revertPosition1();
		if(s1.getHorizontalSpeed()>0) s1.setHorizontalSpeed(-speed);
		else s1.setHorizontalSpeed(speed);
	}

}
