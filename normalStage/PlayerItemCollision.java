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
public class PlayerItemCollision extends AdvanceCollisionGroup{

	public void collided(Sprite s1, Sprite s2) {
		switch(((Item)s2).type){
		case 1 :((Player)s1).setHealth(((Player)s1).getHealth()+((Item)s2).getValue());
				break;
		case 2 :((Player)s1).setMana(((Player)s1).getMana()+((Item)s2).getValue()); 
				break;
		case 5 : ((Player)s1).setMaxHealth(((Player)s1).getMaxHealth()+((Item)s2).getValue());
				 ((Player)s1).setHealth(((Player)s1).getMaxHealth());
				break;
		case 6 : ((Player)s1).setMaxMana(((Player)s1).getMaxMana()+((Item)s2).getValue()); 
				 ((Player)s1).setMana(((Player)s1).getMaxMana()); 
				break;
		default:break;
		}
		s2.setActive(false);
		
	}
}
