package normalStage;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

/*
 * Created on 18 Á.¤. 2549
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
public class Spike extends Sprite{
	Panda owner;	
	BufferedImage img;
	public Spike(Panda game,int x,int y,int i){
			super(x,y);
			owner = game;
			switch(i){
			case 1:  img  = game.getImage("resources/spike/elx_spike_up.gif");break;
			case 2:  img  = game.getImage("resources/spike/elx_spike_down.gif");break;
			case 3:  img  = game.getImage("resources/spike/elx_spike_left.gif");break;
			case 4:  img  = game.getImage("resources/spike/elx_spike_right.gif");break;
			default: img  = game.getImage("resources/spike/elx_spike_up.gif");break;
			}
			super.setImage(img);
		}
}
