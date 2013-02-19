/*
 * Created on 1 ¡.¾. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package normalStage;

import LostSymphony.LostSymphony;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.AdvanceCollisionGroup;

/**
 * @author Donut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PlayerWarpCollision extends AdvanceCollisionGroup {
	Panda owner;
	//S1 = Player ,S2 = Warp point
	public PlayerWarpCollision(Panda game) {
		owner = game;
	}

	public void collided(Sprite S1, Sprite S2) {
		// TODO Auto-generated method stub
		S2.setActive(false);
		owner.parent.nextGameID = LostSymphony.GAMEID_BEAT;
		((LostSymphony)owner.parent).level++;
		owner.music.requestStop();
		((LostSymphony) owner.parent).setMaxHealth(owner.player.getMaxHealth());
		((LostSymphony) owner.parent).setMaxMana(owner.player.getMaxMana());
		((LostSymphony)owner.parent).setScore(owner.player.getScore());
		((LostSymphony) owner.parent).setHealth(owner.player.getHealth());

		
		owner.finish();
	}
	
}
