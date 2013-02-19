/*
 * Created on 19 ¡.¾. 2549
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
public class Cage extends Sprite{
	BufferedImage img;
	Panda owner;
	public Cage(Panda game,double x ,double y,int level){
		super(x,y);
		owner = game;
		img = game.getImage("resources/clock_tile.gif");
		
		super.setImage(img);
	}
	 
}
