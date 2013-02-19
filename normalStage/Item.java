package normalStage;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

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
public class Item extends Sprite{
	BufferedImage img;
	Panda owner;
	int value;
	int type;
	public Item(Panda game,double x ,double y,int t){
		super(x,y);
		owner = game;
		type = t;
		switch(t){
		//increase health
		case 1: img = game.getImage("resources/item/bamboo.gif");
				value = 40;
				break;
		//increase mana
		case 2: img = game.getImage("resources/item/milk_mp1.gif");
				value = 40;
				break;
	 	//increase maxHealth
		case 5:img = game.getImage("resources/item/bambooWINK_.gif");
				value = 40;
				break;
		//increase maxMana
		case 6:img = game.getImage("resources/item/milk_mpmax1.gif");
				value = 40;
				break;
		default: img = game.getImage("resources/transparent.gif");
				value = 0;
				break;
		}
		
		super.setImage(img);
		//setVerticalSpeed(0.3);
	}
		public int getValue(){
			return value;
		}
}
