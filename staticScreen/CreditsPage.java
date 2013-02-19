package staticScreen;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import normalStage.MusicPlayer;

import LostSymphony.LostSymphony;

import com.golden.gamedev.GameEngine;
import com.golden.gamedev.gui.toolkit.FrameWork;

public class CreditsPage extends elxGameObject {
	
	private int imgCount=1;
	private int counter=0;
	MusicPlayer music;
	public CreditsPage(GameEngine ge) {
		super(ge);
		// TODO Auto-generated constructor stub
	}

	public void initResources() {
		// TODO Auto-generated method stub
		frame = new FrameWork(bsInput, getWidth(), getHeight());
		music = new MusicPlayer(7);
		music.start();
	}

	public void update(long arg0) {
		// TODO Auto-generated method stub
		if(keyPressed(KeyEvent.VK_SPACE)){
			father.nextGameID=LostSymphony.GAMEID_MENUPAGE;
			music.requestStop();
			finish();
		}
		
		counter++;
		if(counter==200){
			counter=0;
			if(imgCount<9)imgCount++;
			else imgCount=1;
		}
		frame.update();

	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		BufferedImage bg = getImage("scr_resources/credits/credits.gif");
		g.drawImage(bg,null,0,0);
		g.drawImage(getImage("scr_resources/credits/cdt"+imgCount+".gif"),null,100,230);
		frame.render(g);

	}

}
