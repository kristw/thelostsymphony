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
public class PlayerBlinkblockCollision extends AdvanceCollisionGroup {

	public void collided(Sprite s1, Sprite s2) {
		 if (collisionSide == BOTTOM_TOP_COLLISION ) {
	    	revertPosition1();
		 	s1.setVerticalSpeed(0);
	    	((Player) s1).jumpFlag = false;

	    } 
		 else if(collisionSide == TOP_BOTTOM_COLLISION){
		 	revertPosition1();
		 }
		 else if (collisionSide == LEFT_RIGHT_COLLISION ||
	    		   collisionSide == RIGHT_LEFT_COLLISION) {
	    	revertPosition1();
		}

	}

}
