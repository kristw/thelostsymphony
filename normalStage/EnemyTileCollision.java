package normalStage;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;


/**
 * @author Nut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EnemyTileCollision extends AdvanceCollisionGroup {
	public void collided(Sprite s1, Sprite s2) {
			
			revertPosition1();
			
			 if (collisionSide == BOTTOM_TOP_COLLISION /*|| collisionSide == TOP_BOTTOM_COLLISION*/) {
			 	s1.setVerticalSpeed(0);
			 	if(((Enemys)s1).getType()==3)	s1.setHorizontalSpeed(0);
			 } else if (collisionSide == LEFT_RIGHT_COLLISION ||
			 			collisionSide == RIGHT_LEFT_COLLISION) {
			 	s1.setHorizontalSpeed(0);
			 }
	}
}
