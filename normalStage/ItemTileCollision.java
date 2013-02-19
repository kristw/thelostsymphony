package normalStage;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/*
 * Created on 7 Á.¤. 2549
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
public class ItemTileCollision extends AdvanceCollisionGroup{
	public void collided(Sprite s1, Sprite s2) {
		
		revertPosition1();
		
		 if (collisionSide == BOTTOM_TOP_COLLISION) {
		 	s1.setVerticalSpeed(0);
		 } else if (collisionSide == LEFT_RIGHT_COLLISION ||
		 			collisionSide == RIGHT_LEFT_COLLISION) {
		 	s1.setHorizontalSpeed(0);
		 }
}

}
