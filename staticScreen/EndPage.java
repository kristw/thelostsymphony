package staticScreen;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import LostSymphony.LostSymphony;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.gui.toolkit.FrameWork;

public class EndPage extends elxGameObject {

	public EndPage(GameEngine ge) {
		super(ge);
		// TODO Auto-generated constructor stub
	}

	public void initResources() {
		// TODO Auto-generated method stub
		frame = new FrameWork(bsInput, getWidth(), getHeight());
	}

	public void update(long arg0) {
		// TODO Auto-generated method stub
		if(keyPressed(KeyEvent.VK_SPACE)){
			father.nextGameID=LostSymphony.GAMEID_CREDITS;
			finish();
		}
		frame.update();
	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		BufferedImage bg = getImage("scr_resources/end.jpg");
		g.drawImage(bg,null,0,0);
		frame.render(g);
	}

}
