/*
 * Created on 1 ¡.¾. 2549
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package normalStage;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

/**
 * @author Donut
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Warp extends Sprite {
	Panda owner;
	BufferedImage img;
	public Warp(Panda game,int x,int y){
		super(x,y);
		owner = game;
		img = game.getImage("resources/door.png");
		super.setImage(img);
	}
}
