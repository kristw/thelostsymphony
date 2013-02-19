package staticScreen;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import LostSymphony.LostSymphony;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.gui.toolkit.FrameWork;

/**
 * @author e-liXiR
 *
 */
public class TransitionPage extends elxGameObject {

	private int count;
	private BufferedImage bg;
	private BufferedImage[] panda;

	public TransitionPage(GameEngine ge) {
		super(ge);
		count=0;
	}

	public void initResources() {
		frame = new FrameWork(bsInput, getWidth(), getHeight());
		if(father.isBoss){
			bg = getImage("scr_resources/trans_bg.gif");
			panda = getImages("scr_resources/trans_panda2.gif",5,1);
		}
		else{
			bg = getImage("scr_resources/lv"+father.level+"/"+father.level+".jpg");
			panda = getImages("scr_resources/lv"+father.level+"/"+father.level+"_pan.jpg",3,1);
		}
	}

	public void update(long arg0) {
		if(father.ready){
			if(father.isBoss){
				//#continue to boss stage
				if(count==400){
					father.nextGameID=LostSymphony.GAMEID_PANDA;
					finish();
				}
				//#manually
				if(keyPressed(KeyEvent.VK_SPACE)){
					father.nextGameID=LostSymphony.GAMEID_PANDA;
					finish();
				}
			}
			else{
				//#show "press spacebar to continue" text
				if(count==300){
					bg = getImage("scr_resources/lv"+father.level+"/"+father.level+"_fin.jpg");
				}
				//#continue to next normal stage
				if(keyPressed(KeyEvent.VK_SPACE)){
					father.nextGameID=LostSymphony.GAMEID_PANDA;
					finish();
				}
			}
		}
		frame.update();
	}

	public void render(Graphics2D g) {
		//#draw background
		g.drawImage(bg,null,0,0);
		//#draw panda animation
		if(father.isBoss)g.drawImage(panda[(count%250)/50],null,350,255);
		else g.drawImage(panda[(count%150)/50],null,122,255);
		count++;
		frame.render(g);
	}

}
