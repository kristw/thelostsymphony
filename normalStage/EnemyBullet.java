package normalStage;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

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
public class EnemyBullet extends Sprite {
	Panda owner;
	int damage;
	BufferedImage img;
	Enemys enemy;
	double bulletSpeed;
	public EnemyBullet(Enemys e,Panda game,int level) {
		owner = game;
		enemy = e;
		switch (level){
		case 1 : damage = 10;bulletSpeed = 0.1;break;
		case 2 : damage = 10;bulletSpeed = 0.2;break;
		case 3 : damage = 15;bulletSpeed = 0.2;break;
		case 4 : damage = 15;bulletSpeed = 0.2;break;
		case 5 : damage = 20;bulletSpeed = 0.2;break;
		case 6 : damage = 20;bulletSpeed = 0.3;break;
		default : damage = 15;bulletSpeed = 0.2; break;
		}
		img = owner.getImage("resources/Energy-Ball.gif");
		setImage(img);
	}
}
