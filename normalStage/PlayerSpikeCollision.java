package normalStage;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/*
 * Created on 21 Á.¤. 2549
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
public class PlayerSpikeCollision extends AdvanceCollisionGroup{
	
	//s1 = player
	//s2 = spike
	public void collided(Sprite s1, Sprite s2) {
		((Player)s1).dead();
		
	}

}
